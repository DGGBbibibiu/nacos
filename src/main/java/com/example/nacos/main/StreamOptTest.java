package com.example.nacos.main;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamOptTest {

    private static final ArrayList<Apple> appleList;

    static {
        appleList = new ArrayList<>();//存放apple对象集合
        Apple apple1 =  new Apple(1,"苹果1",new BigDecimal("3.25"),10, "");
        Apple apple12 = new Apple(1,"苹果2",new BigDecimal("1.35"),20, "");
        Apple apple2 =  new Apple(2,"香蕉",new BigDecimal("2.89"),30, "");
        Apple apple3 =  new Apple(3,"荔枝",new BigDecimal("9.99"),40, "");

        appleList.add(apple1);
        appleList.add(apple12);
        appleList.add(apple2);
        appleList.add(apple3);
    }

    public static void main(String[] args) {
        Map<Integer, List<Apple>> groupBy = appleList.stream().collect(Collectors.groupingBy(Apple::getId));
        System.err.println("group: " + groupBy);
        // 根据ID排序
        //groupBy:{
        // 1=[Apple{id=1, name='苹果1', money=3.25, num=10},  Apple{id=1, name='苹果2', money=1.35, num=20}],
        // 2=[Apple{id=2, name='香蕉', money=2.89, num=30}],
        // 3=[Apple{id=3, name='荔枝', money=9.99, num=40}]}

        List<Apple> list1 = appleList.stream().filter(s -> s.getId() == 2).collect(Collectors.toList());
        for (Apple apple: list1)
            System.out.println(apple.toString());
        //filter：根据条件过滤
        // Apple{id=2, name='香蕉', money=2.89, num=30}

        OptionalDouble maxNum = appleList.stream().mapToDouble(Apple::getId).max();
        System.out.println(String.format("Apple ID的最大值是： %s", maxNum.getAsDouble()));
        //Apple ID的最大值是： 3.0

        List<String> list = Arrays.asList("a,b,c", "1,2,3");
        Stream<String> stringStream = list.stream().map(s -> s.replaceAll(",", ""));
        System.out.println("forEach:");
        stringStream.forEach(System.out::println);
        //forEach:
        //abc
        //123

        Map<String, BigDecimal> toMap = appleList.stream().collect(Collectors.toMap(Apple::getName, Apple::getMoney));
        toMap.forEach((key, vlue) ->
                System.out.println(String.format("key为: %s value为： %s", key, vlue))
                );
        //转成map,注:key不能相同，否则报错
        //map.forEach JDK1.8特性
        //result:
        //key为: 香蕉 value为： 2.89
        //key为: 苹果1 value为： 3.25
        //key为: 苹果2 value为： 1.35
        //key为: 荔枝 value为： 9.99

        String str1 = appleList.stream().map(Apple::getName).collect(Collectors.joining("-", "[", "]"));
        System.out.println(str1);
        //字符串分隔符连接
        //[苹果1-苹果2-香蕉-荔枝]

        Map<Boolean, List<Apple>> part = appleList.stream().collect(Collectors.partitioningBy(s -> s.getNum() > 20));
        part.forEach((k, v) ->{
            System.out.println(String.format("key为: %s value为： %s", k, v));
        });
        //key为: false value为： [Apple{id=1, name='苹果1', money=3.25, num=10, isLike=''}, Apple{id=1, name='苹果2', money=1.35, num=20, isLike=''}]
        //key为: true value为： [Apple{id=2, name='香蕉', money=2.89, num=30, isLike=''}, Apple{id=3, name='荔枝', money=9.99, num=40, isLike=''}]
        //分区 数量大于20 true 和 小于等于20 false
    }

    static class Apple{
        private Integer id;
        private String name;
        private BigDecimal money;
        private Integer num;
        private String isLike;
        public Apple(Integer id, String name, BigDecimal money, Integer num, String isLike) {
            this.id = id;
            this.name = name;
            this.money = money;
            this.num = num;
            this.isLike = isLike;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public BigDecimal getMoney() {
            return money;
        }

        public void setMoney(BigDecimal money) {
            this.money = money;
        }

        public Integer getNum() {
            return num;
        }

        public void setNum(Integer num) {
            this.num = num;
        }

        public String getIsLike() {
            return isLike;
        }

        public void setIsLike(String isLike) {
            this.isLike = isLike;
        }

        @Override
        public String toString() {
            return "Apple{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", money=" + money +
                    ", num=" + num +
                    ", isLike='" + isLike + '\'' +
                    '}';
        }
    }
}

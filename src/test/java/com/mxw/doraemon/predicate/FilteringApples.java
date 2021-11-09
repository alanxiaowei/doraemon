package com.mxw.doraemon.predicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @program: doraemon
 * @description:
 * @author: AlanMa
 * @create: 2021-10-14 17:06
 */
public class FilteringApples {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),

                new Apple(155, "green"),

                new Apple(120, "red"));

        List greenApples2 = filterApples(inventory, (Apple a) -> "green".equals(a.getColor()));

        System.out.println("~~~ green apples :" + greenApples2);

        // [Apple{color='green', weight=155}]

        List heavyApples2 = filterApples(inventory, (Apple a) -> a.getWeight() > 150);

        System.out.println("~~~ weight is bigger than 150 :" + heavyApples2);

        // []

        List weirdApples = filterApples(inventory, (Apple a) -> a.getWeight() < 80 ||

                "brown".equals(a.getColor()));

        System.out.println("~~~ weight is bigger than 150 :" + weirdApples);

    }

    public static List filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

}

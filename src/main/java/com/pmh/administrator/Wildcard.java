package com.pmh.administrator;

import java.util.ArrayList;
import java.util.List;

public class Wildcard {

    public static void main(String [] args) {

        List<Integer> ints = new ArrayList<>();
        ints.add(1); ints.add(2); ints.add(3); addInteger(ints);
        double sum = sum(ints);
        System.out.println("Sum of ints = "+sum);
    }

    public static double sum(List<? extends Number> list) {
        double sum = 0;
        for(Number n: list) {
            sum += n.doubleValue();
        }
        return sum;
    }

    public static void addInteger(List<? super Integer> list) {
        list.add(new Integer(30));
    }
}

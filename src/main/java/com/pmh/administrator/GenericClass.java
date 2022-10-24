package com.pmh.administrator;

import org.springframework.boot.SpringApplication;

import javax.servlet.http.PushBuilder;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericClass {

    // example 1: fail
//    public static void main(String[] args) {
//        List list = new ArrayList();
//        list.add("abc");
//
//        try {
//            System.out.println("add a number to list string");
//            list.add(new Integer(5));
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//
//        System.out.println("print list");
//        for(Object obj : list) {
//            try {
//                String str = (String) obj;
//                System.out.println(str);
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//        }
//        System.out.println("exit");
//    }

    // example 2: oke
//    public static void main(String args[]) {
//        GenericsType list = new GenericsType();
//        list.set("abc"); //valid
//        System.out.println(list.get());
//        list.set(5);
//        System.out.println(list.get());
//    }

    //public static class GenericsType<T> {
//
//    private T t;
//
//    public T get(){
//        return this.t;
//    }
//
//    public void set(T t1){
//        this.t = t1;
//    }
//}

    //example 3: oke
    public static void main(String[] args) {
        Stack arr = new Stack();
        arr.push("cho anh nhe");
        arr.push(11111);
        arr.push("pop element");
        arr.display();
        arr.pop();
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        arr.display();
    }

    public static class Stack<T> {

        private Object[] array = null;
        private final int capacity = 5; // fixed or pass it in the constructor
        private int pos = 0;

        public void push(T value) {
            if (value == null)
                throw new IllegalArgumentException("Stack does not accept nulls");

            if (array == null)
                array = new Object[capacity];

            if(pos == capacity)
                throw new IllegalStateException("push on full stack");

            array[pos++] = value;
        }

        public Object pop() throws IllegalStateException {
            if (pos == 0)
                throw new IllegalStateException("pop on empty stack");
            array[--pos] = null;
            return array;
        }

        public void display() {
            for (Object i : array) {
                System.out.println(i);
            }
        }
    }
}




package com.pmh.administrator;

public class Inheritance {
    public static void main(String[] args) {

        String str = "abc";
        Object obj = new Object();
        obj = str;

        MyClass<String> myClass1 = new MyClass<String>();
        MyClass<Object> myClass2 = new MyClass<Object>();

//        myClass2 = myClass1; error

        obj = myClass1;
        obj = myClass2;
    }

    public static class MyClass<T>{};
}

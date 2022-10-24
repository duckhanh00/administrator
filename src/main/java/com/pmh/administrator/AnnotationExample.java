package com.pmh.administrator;

import com.pmh.administrator.annotations.MethodInfo;
import lombok.SneakyThrows;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class AnnotationExample {

    @SneakyThrows
    public static void main(String[] args) {
        genericsTest();
    }

    @Override
    @MethodInfo(author = "Khanh", comments = "Main method", date = "Oct 24 2022", revision = 1)
    public String toString() {
        return "Overriden toString method";
    }

    @Deprecated
    @MethodInfo(comments = "deprecated method", date = "Oct 24 2022")
    public static void oldMethod() {
        System.out.println("old method, don't use it.");
    }

    @SuppressWarnings({ "unchecked", "deprecation" })
    @MethodInfo(author = "Khanh", comments = "Main method", date = "Oct 24 2022", revision = 10)
    public static void genericsTest() throws FileNotFoundException {
        List l = new ArrayList();
        l.add("abc");
        oldMethod();
    }
}

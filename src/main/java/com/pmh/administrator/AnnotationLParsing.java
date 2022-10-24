package com.pmh.administrator;

import com.pmh.administrator.annotations.MethodInfo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationLParsing {

    public static void main(String[] args) {
        try {
            for (Method method : AnnotationLParsing.class.getClassLoader().loadClass(("com.pmh.administrator.AnnotationExample")).getMethods()) {
                // checks if MethodInfo annotation is present for the method
//                System.out.println(method);
                if (method.isAnnotationPresent(com.pmh.administrator.annotations.MethodInfo.class)) {

                    try {
                        System.out.println("**** start **** method is annotation: " + method);
                        // iterates all the annotations available in the method
                        for (Annotation anno : method.getDeclaredAnnotations()) {
                            System.out.println("Annotation in Method '" + method + "' : " + anno);
                        }
                        MethodInfo methodAnno = method.getAnnotation(MethodInfo.class);
                        if (methodAnno.revision() == 1) {
                            System.out.println("Method with revision no 1 = " + method);
                        }
                        System.out.println("**** end ****");

                    } catch (Throwable ex) {
                        System.out.println("ex");
                        ex.printStackTrace();
                    }
                }
            }
        } catch (SecurityException | ClassNotFoundException e) {
            System.out.println("e");
            e.printStackTrace();
        }
    }
}

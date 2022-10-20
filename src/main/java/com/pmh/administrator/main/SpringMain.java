package com.pmh.administrator.main;

import com.pmh.administrator.service.EmployeeService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {
    public static void main(String[] args) {
        try {

            ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
            System.out.println("=========================");
            System.out.println("start ctx.getBean()");
            EmployeeService employeeService = ctx.getBean("employeeService", EmployeeService.class);
            System.out.println("end ctx.getBean()");
            System.out.println("=========================");

            System.out.println("=========================");
            System.out.println("start employeeService.getEmployee().getName()");
            System.out.println("employee name: " + employeeService.getEmployee().getName());
            System.out.println("end employeeService.getEmployee().getName()");
            System.out.println("=========================");

            System.out.println("=========================");
            System.out.println("start employeeService.getEmployee().setName()");
            employeeService.getEmployee().setName("Khanh");
            System.out.println("end employeeService.getEmployee().setName()");
            System.out.println("=========================");

            System.out.println("=========================");
            System.out.println("Dai ka: " + employeeService.getEmployee().getName());
            System.out.println("end employeeService.getEmployee().getName()");
            System.out.println("=========================");

            System.out.println("=========================");
            System.out.println("start employeeService.getEmployee().throwException()");
            employeeService.getEmployee().throwException();
            System.out.println("end employeeService.getEmployee().throwException()");
            System.out.println("=========================");

            ctx.close();
        }
        catch(Exception e) {
            System.out.println("errorrrrrrrrrrrrrrrrrrrrrr: " + e);
        }

    }
}

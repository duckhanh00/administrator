package com.pmh.administrator.service;

import com.pmh.administrator.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class EmployeeAutowiredByConstructorService {

    private Employee employee;

    @Autowired(required = false)
    public EmployeeAutowiredByConstructorService(@Qualifier("employee") Employee emp) {
        System.out.println("Autowiring by EmployeeAutowiredByConstructorService used");
        this.employee = emp;
    }

    public Employee getEmployee() {
        return this.employee;
    }
}

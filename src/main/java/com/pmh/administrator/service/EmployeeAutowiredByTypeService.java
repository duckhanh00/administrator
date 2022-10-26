package com.pmh.administrator.service;

import com.pmh.administrator.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeAutowiredByTypeService {

    @Autowired
    private Employee employee;

    public void setEmployee(Employee employee) {
        System.out.println("Autowiring by EmployeeAutowiredByTypeService used");
        this.employee = employee;
    }

    public Employee getEmployee() {
        return this.employee;
    }
}

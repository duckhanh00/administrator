package com.pmh.administrator.service;

import com.pmh.administrator.model.Employee;

public class EmployeeService {

    private Employee employee;

    public Employee getEmployee(){
        return this.employee;
    }

    public void setEmployee(Employee e){
        this.employee=e;
        System.out.println("EmployeeService.setEmployee() = " + e);
        System.out.println("Name of employee: " + e.getName());
    }
}
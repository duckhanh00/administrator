package com.pmh.administrator.service;

import com.pmh.administrator.model.Employee;

public class EmployeeService {

    private Employee employee;

    public EmployeeService(Employee employee) {
        System.out.println("Autowiring by constructor used");
        this.employee = employee;
    }

    public EmployeeService() {
        System.out.println("Default Contructor used");
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return this.employee;
    }
}

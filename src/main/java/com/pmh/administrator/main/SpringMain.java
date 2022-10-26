package com.pmh.administrator.main;

import com.pmh.administrator.service.EmployeeAutowiredByConstructorService;
import com.pmh.administrator.service.EmployeeAutowiredByTypeService;
import com.pmh.administrator.service.EmployeeService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        EmployeeService serviceByName = ctx.getBean("employeeServiceByName", EmployeeService.class);

        System.out.println("3. Autowiring byName. Employee Name="+serviceByName.getEmployee().getName());

        EmployeeService serviceByType = ctx.getBean("employeeServiceByType", EmployeeService.class);

        System.out.println("5. Autowiring byType. Employee Name="+serviceByType.getEmployee().getName());

        EmployeeService serviceByConstructor = ctx.getBean("employeeServiceConstructor", EmployeeService.class);

        System.out.println("7. Autowiring by Constructor. Employee Name="+serviceByConstructor.getEmployee().getName());

        //printing hashcode to confirm all the objects are of different type
        System.out.println("8. "+serviceByName.hashCode()+"::"+serviceByType.hashCode()+"::"+serviceByConstructor.hashCode());

        //Testing @Autowired annotations
        EmployeeAutowiredByTypeService autowiredByTypeService = ctx.getBean("employeeAutowiredByTypeService",EmployeeAutowiredByTypeService.class);

        System.out.println("10. @Autowired byType. Employee Name="+autowiredByTypeService.getEmployee().getName());

        EmployeeAutowiredByConstructorService autowiredByConstructorService = ctx.getBean("employeeAutowiredByConstructorService",EmployeeAutowiredByConstructorService.class);

        System.out.println("12. @Autowired by Constructor. Employee Name="+autowiredByConstructorService.getEmployee().getName());

        ctx.close();
    }
}

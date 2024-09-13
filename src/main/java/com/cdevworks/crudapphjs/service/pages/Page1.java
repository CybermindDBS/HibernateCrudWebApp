package com.cdevworks.crudapphjs.service.pages;

import com.cdevworks.crudapphjs.Table;
import com.cdevworks.crudapphjs.persistance.dao.EmployeeDao;
import com.cdevworks.crudapphjs.persistance.entity.Address;
import com.cdevworks.crudapphjs.persistance.entity.Employee;
import com.cdevworks.crudapphjs.persistance.entity.FullTimeEmployee;
import com.cdevworks.crudapphjs.persistance.entity.PartTimeEmployee;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Page1 {
    public static void handleGet(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("pageName", "Hibernate | Simple CRUD,  Pagination & OR-Mapping");
        EmployeeDao employeeDao = new EmployeeDao();
        System.out.println(employeeDao.getFirstNEmployeesFrom(1));
        List<Employee> employees = employeeDao.getFirstNEmployeesFrom(Integer.parseInt((String) request.getSession().getAttribute("section")));
        request.setAttribute("tables", List.of(new Table("Employee", Table.getRows(employees))));
    }

    public static void handlePost(HttpServletRequest request, HttpServletResponse response) {
        String operation = request.getParameter("type");
        switch (operation) {
            case "add": {
                EmployeeDao employeeDao = new EmployeeDao();
                String[] data = request.getParameter("data").split(",");
                List<String> parsedData = Arrays.stream(data).map(String::trim).collect(Collectors.toList());

                Employee employee;
                if (Integer.parseInt(parsedData.get(3)) > 1000) {
                    employee = new FullTimeEmployee();
                    ((FullTimeEmployee) employee).setSalary(Integer.parseInt(parsedData.get(3)));
                } else {
                    employee = new PartTimeEmployee();
                    ((PartTimeEmployee) employee).setHourly_wage(Integer.parseInt(parsedData.get(3)));
                }
                employee.setName(parsedData.get(0));
                employee.setAddress(new Address(parsedData.get(1), parsedData.get(2)));
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(parsedData.get(4), dateTimeFormatter);
                employee.setDoj(localDate);
                employeeDao.add(employee);

                List<Employee> employees = employeeDao.getFirstNEmployeesFrom(Integer.parseInt((String) request.getSession().getAttribute("section")));
                request.setAttribute("tables", List.of(new Table("Employee", Table.getRows(employees))));
                break;
            }
            case "get": {

                EmployeeDao employeeDao = new EmployeeDao();
                List<Employee> employeeList = List.of(employeeDao.getEmployeeWithId(Integer.parseInt(request.getParameter("data"))));
                List<Employee> employees = employeeDao.getFirstNEmployeesFrom(Integer.parseInt((String) request.getSession().getAttribute("section")));
                request.setAttribute("tables", List.of(new Table("Output", Table.getRows(employeeList)), new Table("Employee", Table.getRows(employees))));
                break;
            }
            case "update": {

                EmployeeDao employeeDao = new EmployeeDao();
                String[] data = request.getParameter("data").split(",");
                List<String> parsedData = Arrays.stream(data).map(String::trim).collect(Collectors.toList());
                Employee persistentEmployee = employeeDao.getEmployeeWithId(Integer.parseInt(parsedData.get(0)));
                persistentEmployee.setName(parsedData.get(1));
                persistentEmployee.setAddress(new Address(parsedData.get(2), parsedData.get(3)));
                if (persistentEmployee instanceof FullTimeEmployee)
                    ((FullTimeEmployee) persistentEmployee).setSalary(Integer.parseInt(parsedData.get(4)));
                else ((PartTimeEmployee) persistentEmployee).setHourly_wage(Integer.parseInt(parsedData.get(4)));
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(parsedData.get(5), dateTimeFormatter);
                persistentEmployee.setDoj(localDate);
                employeeDao.update(persistentEmployee);

                List<Employee> employees = employeeDao.getFirstNEmployeesFrom(Integer.parseInt((String) request.getSession().getAttribute("section")));
                request.setAttribute("tables", List.of(new Table("Employee", Table.getRows(employees))));
                break;
            }
            case "delete": {

                EmployeeDao employeeDao = new EmployeeDao();
                employeeDao.deleteEmployeeWithId(Integer.parseInt(request.getParameter("data")));
                List<Employee> employees;
                try {
                    employees = employeeDao.getFirstNEmployeesFrom(Integer.parseInt((String) request.getSession().getAttribute("section")));
                    request.setAttribute("tables", List.of(new Table("Employee", Table.getRows(employees))));
                } catch (Exception e) {
                    employees = employeeDao.getFirstNEmployeesFrom(Integer.parseInt((String) request.getSession().getAttribute("section")) - 1);
                    request.setAttribute("tables", List.of(new Table("Employee", Table.getRows(employees))));
                }
                break;
            }
        }

        request.setAttribute("pageName", "Hibernate | Simple CRUD,  Pagination & OR-Mapping");
    }
}

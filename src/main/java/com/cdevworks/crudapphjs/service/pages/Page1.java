package com.cdevworks.crudapphjs.service.pages;

import com.cdevworks.crudapphjs.Table;
import com.cdevworks.crudapphjs.persistance.dao.EmployeeDao;
import com.cdevworks.crudapphjs.persistance.entity.Employee;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Page1 {
    public static void handleGet(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("pageName", "Hibernate | Single Row Operations");
        EmployeeDao employeeDao = new EmployeeDao();
        List<Employee> employees = employeeDao.getAllEmployees();
        request.setAttribute("tables", List.of(new Table(Table.getName(employees), Table.getRows(employees))));
    }

    public static void handlePost(HttpServletRequest request, HttpServletResponse response) {
        String operation = request.getParameter("type");
        switch (operation) {
            case "add": {
                EmployeeDao employeeDao = new EmployeeDao();
                String[] data = request.getParameter("data").split(",");
                List<String> parsedData = Arrays.stream(data).map(String::trim).collect(Collectors.toList());

                Employee employee = new Employee();
                employee.setName(parsedData.get(0));
                employee.setSalary(Integer.parseInt(parsedData.get(1)));
                employee.setAddress(parsedData.get(2));
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(parsedData.get(3), dateTimeFormatter);
                employee.setDoj(localDate);
                employee.setSupervisor_id(Integer.parseInt(parsedData.get(4)));
                employeeDao.add(employee);

                List<Employee> employees = employeeDao.getAllEmployees();
                request.setAttribute("tables", List.of(new Table(Table.getName(employees), Table.getRows(employees))));
                break;
            }
            case "get": {

                EmployeeDao employeeDao = new EmployeeDao();
                List<Employee> employeeList = List.of(employeeDao.getEmployeeWithId(Integer.parseInt(request.getParameter("data"))));
                List<Employee> employees = employeeDao.getAllEmployees();
                request.setAttribute("tables", List.of(new Table("Output", Table.getRows(employeeList)), new Table(Table.getName(employees), Table.getRows(employees))));
                break;
            }
            case "update": {

                EmployeeDao employeeDao = new EmployeeDao();
                String[] data = request.getParameter("data").split(",");
                List<String> parsedData = Arrays.stream(data).map(String::trim).collect(Collectors.toList());

                Employee employee = new Employee();
                employee.setId(Integer.parseInt(parsedData.get(0)));
                employee.setName(parsedData.get(1));
                employee.setSalary(Integer.parseInt(parsedData.get(2)));
                employee.setAddress(parsedData.get(3));
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(parsedData.get(4), dateTimeFormatter);
                employee.setDoj(localDate);
                employee.setSupervisor_id(Integer.parseInt(parsedData.get(5)));
                employeeDao.update(employee);

                List<Employee> employees = employeeDao.getAllEmployees();
                request.setAttribute("tables", List.of(new Table(Table.getName(employees), Table.getRows(employees))));
                break;
            }
            case "delete": {

                EmployeeDao employeeDao = new EmployeeDao();
                employeeDao.deleteEmployeeWithId(Integer.parseInt(request.getParameter("data")));
                List<Employee> employees = employeeDao.getAllEmployees();
                request.setAttribute("tables", List.of(new Table(Table.getName(employees), Table.getRows(employees))));
                break;
            }
        }

        request.setAttribute("pageName", "Hibernate | Single Row Operations");
    }
}

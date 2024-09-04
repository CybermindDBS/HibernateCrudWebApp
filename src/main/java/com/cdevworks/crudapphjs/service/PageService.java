package com.cdevworks.crudapphjs.service;

import com.cdevworks.crudapphjs.Table;
import com.cdevworks.crudapphjs.persistance.dao.EmployeeDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class PageService {
    public static void page1Get(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("pageName", "Hibernate | Single Row Operations");
        EmployeeDao employeeDao = new EmployeeDao();
        List employees = employeeDao.getAllEmployees();
        request.setAttribute("tables", List.of(new Table(Table.getName(employees), Table.getRows(employees)), new Table(Table.getName(employees), Table.getRows(employees))));
    }

    public static void page1Post(HttpServletRequest request, HttpServletResponse response) {

    }

    public static void page2Get(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println("<html><body><h1>Under Construction</h1></body></html>");
    }

    public static void page2Post(HttpServletRequest request, HttpServletResponse response) {

    }
}

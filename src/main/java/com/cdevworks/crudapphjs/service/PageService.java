package com.cdevworks.crudapphjs.service;

import com.cdevworks.crudapphjs.Table;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PageService {
    public static void page1Get(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("pageName", "Hibernate | Single Row Operations");
        List<List<String>> table1 = new ArrayList<>();
        table1.add(Arrays.asList("a1", "a2", "a3"));
        table1.add(Arrays.asList("a4", "a5", "a6"));
        List<List<String>> table2 = new ArrayList<>();
        table2.add(Arrays.asList("b1", "b2", "b3"));
        table2.add(Arrays.asList("b4", "b5", "b6"));
        request.setAttribute("tables", List.of(new Table("Table A", table1), new Table("Table B", table2)));
    }

    public static void page1Post(HttpServletRequest request, HttpServletResponse response) {

    }

    public static void page2Get(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println("<html><body><h1>Under Construction</h1></body></html>");
    }

    public static void page2Post(HttpServletRequest request, HttpServletResponse response) {

    }
}

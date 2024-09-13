package com.cdevworks.crudapphjs.service;

import com.cdevworks.crudapphjs.service.pages.Page1;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class PageService {
    public static void page1Get(HttpServletRequest request, HttpServletResponse response) {
        Page1.handleGet(request, response);
    }

    public static void page1Post(HttpServletRequest request, HttpServletResponse response) {
        Page1.handlePost(request, response);
    }

    public static void page2Get(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("pageName", "Hibernate | Advanced OR-Mapping");
    }

    public static void page2Post(HttpServletRequest request, HttpServletResponse response) {

    }
}

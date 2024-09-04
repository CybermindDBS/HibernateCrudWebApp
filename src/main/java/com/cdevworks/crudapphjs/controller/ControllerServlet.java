package com.cdevworks.crudapphjs.controller;

import com.cdevworks.crudapphjs.service.PageService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/main")
public class ControllerServlet extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String pageId = request.getParameter("pageId");
        ;
        if (pageId != null) {
            switch (pageId) {
                case "1": {
                    PageService.page1Get(request, response);
                    break;
                }
                case "2": {
                    PageService.page2Get(request, response);
                    break;
                }
            }
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/main?pageId=1");
            requestDispatcher.forward(request, response);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("gui.jsp");
        requestDispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String pageId = request.getParameter("pageId");
        ;
        if (pageId != null) {
            switch (pageId) {
                case "1": {
                    PageService.page1Post(request, response);
                    break;
                }
                case "2": {
                    PageService.page2Post(request, response);
                    break;
                }
            }
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("gui.jsp");
        requestDispatcher.forward(request, response);
    }

    public void destroy() {
    }
}

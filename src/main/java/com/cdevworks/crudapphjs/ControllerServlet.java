package com.cdevworks.crudapphjs;

import java.io.*;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/main")
public class ControllerServlet extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String pageId = request.getParameter("pageId");;
        if(pageId != null)
        {
            switch (pageId)
            {
                case "1":
                {
                    PageService.page1Get(request, response);
                    break;
                }
                case "2":
                {
                    PageService.page2Get(request, response);
                    break;
                }
                default:
                {
                    PageService.page1Get(request, response);
                    break;
                }
            }
        }
        else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/main?pageId=1");
            requestDispatcher.forward(request, response);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("gui.jsp");
        requestDispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String pageId = request.getParameter("pageId");;
        if(pageId != null)
        {
            switch (pageId)
            {
                case "1":
                {
                    PageService.page1Post(request, response);
                    break;
                }
                case "2":
                {
                    PageService.page2Post(request, response);
                    break;
                }
                default:
                {
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

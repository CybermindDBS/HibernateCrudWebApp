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
                    request.setAttribute("pageName", "Hibernate | Single Row Operations");
                    request.setAttribute("tables", List.of(List.of(List.of(1,2,3,4,5), List.of(1,2,3,4,5)), List.of(List.of(1,2,3,4,5), List.of(1,2,3,4,5))));
                    break;
                }
                case "2":
                {
                    request.setAttribute("pageName", "Hibernate | Multiple Row Operations");
                    break;
                }
                default:
                {
                    request.setAttribute("pageName", "Page name");
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

    public void destroy() {
    }
}
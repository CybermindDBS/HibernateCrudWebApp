<%@ page import="java.util.List" %>
<%@ page import="java.lang.reflect.Array" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>CrudApp!</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>

<div class="navbar" id="navbar">
    <button onclick="closeNavbar()">x</button>
    <h2 class="heading2">Crud App - Hibernate & JEE</h2>
    <ul>
        <li><a href="/CrudAppHJS/main?pageId=1">Heading</a></li>
        <ul>
            <li><a href="/CrudAppHJS/main?pageId=2">Sub-Heading</a></li>
            <li><a href="/CrudAppHJS/main?pageId=3">Sub-Heading</a></li>
        </ul>
        <li><a href="/CrudAppHJS/main?pageId=4">Heading</a></li>
    </ul>
</div>

<div class="page">
    <div class="page-top">
        <button class="navbar-open-button" onclick="openNavbar()">
            <img src="assets/menu_icon.png" alt="menu-icon">
        </button>

        <h2 class="heading"><%=request.getAttribute("pageName")%>
        </h2>
    </div>

    <div class="container">
        <div class="left-section">
            <div class="container2">
                <h2>Table name</h2>
                
                <table class="table">
                    <thead>
                    <tr>
                        <th>c1</th>
                        <th>c2</th>
                        <th>c3</th>
                        <th>c4</th>
                        <th>c5</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>d1</td>
                        <td>d2</td>
                        <td>d3</td>
                        <td>d4</td>
                        <td>d5</td>
                    </tr>
                    <tr>
                        <td>d1</td>
                        <td>d2</td>
                        <td>d3</td>
                        <td>d4</td>
                        <td>d5</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="right-section">
            <%@include file="controls.jsp" %>
        </div>
    </div>
</div>

<script src="js/script.js"></script>
</body>
</html>
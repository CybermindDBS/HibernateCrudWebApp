<%@ page import="java.util.List" %>
<%@ page import="java.lang.reflect.Array" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.cdevworks.crudapphjs.Table" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="HJS" uri="/WEB-INF/hjs-tags.tld" %>

<!DOCTYPE html>
<html>
<head>
    <title>CrudApp!</title>
    <link rel="stylesheet" href="resources/css/styles.css">
</head>
<body>

<div class="navbar" id="navbar">
    <button onclick="closeNavbar()">x</button>
    <h2 class="heading2">Crud App - Hibernate & JEE</h2>
    <ul>
        <li><a href="/CrudAppHJS/main?pageId=1&section=1">Simple CRUD, Pagination & OR-Mapping</a></li>
    </ul>
</div>

<div class="page">
    <div class="page-top">
        <button class="navbar-open-button" onclick="openNavbar()">
            <img src="resources/images/menu_icon.png" alt="menu-icon">
        </button>

        <h2 class="heading"><%=request.getAttribute("pageName")%>
        </h2>
    </div>

    <div class="container">
        <div class="left-section">
            <HJS:populateTable tables="${tables}"/>
        </div>

        <div class="right-section">
            <HJS:includeHTML filePath="jsp/page1-controls.html"/>
        </div>
    </div>
</div>
<script src="resources/js/script.js"></script>
</body>
</html>

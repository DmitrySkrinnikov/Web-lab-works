<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="/Error.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${res.getString('title')}</title>
    <style>
        header {
            text-align: center;
        }
        footer {
            text-align: left;
        }
    </style>
</head>
<body>
<%
    String tmp = request.getParameter("done");
    if (tmp != null && !tmp.equals("Да") && !tmp.equals("Нет")){
        throw new IllegalArgumentException("Name expected");
    }
%>
<%@include file="header.jsp"%>
<main>
    <h1>
        ${res.getString("list_project")}
        <c:if test="${(customer != null)}">
            ${res.getString("customer")}: ${customer}
        </c:if>
        <c:if test="${(done != null)}">
            ${res.getString("done")}: ${done}
        </c:if>
    </h1>
    <table border='1'>
        <tr>
            <th><b>${res.getString('project')}</b></th>
            <th><b>${res.getString('customer')}</b></th>
            <th><b>${res.getString('done')}</b></th>
        </tr>
        <c:forEach var="p" items="${projects}">
            <c:if test="${(p.customer == customer || customer == null) && (p.done == done || done == null)}">
                <tr>
                    <td>${p.project}</td>
                    <td>${p.customer}</td>
                    <td>${p.done == 'Да' ? res.getString('yes') : res.getString('no')}</td>
                </tr>
            </c:if>
        </c:forEach>
    </table>
</main>
<%@include file="footer.jsp"%>
</body>
</html>
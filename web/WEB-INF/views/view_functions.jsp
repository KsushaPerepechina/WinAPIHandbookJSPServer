<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<style>
    <%@include file="/resources/style.css" %>
</style>

<head>
    <title>Content</title>
</head>

<body>
    <jsp:include page="_menu.jsp"/>
    <jsp:include page="_header.jsp"/>

    <div class="centered">
        <h3>Win API Functions</h3>
        <p class="error">${errorMessage}</p>
    </div>

    <table border="1" width="90%">
        <tr>
            <th>Name</th>
            <th>Params</th>
            <th>Return value</th>
            <th>Description</th>
            <th colspan="2"></th>
        </tr>

        <c:forEach items="${list}" var="t">
            <tr>
                <td>${t.name}</td>
                <td>${t.params}</td>
                <td>${t.returnValue}</td>
                <td>${t.description}</td>
                <td><a href="editFunction?id=${t.id}">Edit</a></td>
                <td><a href="deleteFunction?id=${t.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>

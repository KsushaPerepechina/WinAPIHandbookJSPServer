<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<style>
    <%@include file="/resources/style.css" %>
</style>
<head>
    <title>Edit Function</title>
</head>
<body>
    <jsp:include page="_menu.jsp"/>
    <jsp:include page="_header.jsp"/>

    <div class="centered">
        <h3>Edit Function Form</h3>
        <p class="error">${errorMessage}</p>
    </div>

    <c:if test="${not empty f}">
        <form method="POST" action="${pageContext.request.contextPath}/editFunction">
            <input type="hidden" name="id" value="${f.id}"/>
            <label> Name:
                <input type="text" name="name" value="${ f.name != null ? f.name : "" }"/>
            </label>
            <label> Params
                <textarea name="params" cols="40" rows="4">${f.params != null ? f.params : "" }</textarea>
            </label>
            <label> Return value
                <textarea name="return_value" cols="40" rows="4">${f.returnValue != null ? f.returnValue : "" }</textarea>
            </label>
            <label> Description
                <textarea name="description" cols="40" rows="4">${f.description != null ? f.description : "" }</textarea>
            </label>
            <div class="centered">
                <input type="submit" value="Submit"/>
            </div>
        </form>
    </c:if>
</body>
</html>

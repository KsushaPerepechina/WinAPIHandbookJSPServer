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

    <c:if test="${not empty t}">
        <form method="POST" action="${pageContext.request.contextPath}/editFunction">
            <input type="hidden" name="id" value="${t.id}"/>

            <label> Name:
                <input type="text" name="name" value="${ t.name != null ? t.name : "" }"/>
            </label>
            <label> Params
                <textarea name="params" cols="40" rows="4">${t.params != null ? t.params : "" }</textarea>
            </label>
            <label> Return value
                <textarea name="return_value" cols="40" rows="4">${t.returnValue != null ? t.returnValue : "" }</textarea>
            </label>
            <label> Description
                <textarea name="description" cols="40" rows="4">${t.description != null ? t.description : "" }</textarea>
            </label>
            <div class="centered">
                <input type="submit" value="Submit"/>
            </div>
        </form>
    </c:if>
</body>
</html>

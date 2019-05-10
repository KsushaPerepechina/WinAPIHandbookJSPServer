<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<style>
    <%@include file="/resources/style.css" %>
</style>
<head>
    <title>Add Function entry form</title>
</head>
<body>
    <jsp:include page="_menu.jsp"/>
    <jsp:include page="_header.jsp"/>

    <div class="centered">
        <h3>Add Function Form</h3>
        <p class="error">${errorMessage}</p>
    </div>

    <form method="POST" action="${pageContext.request.contextPath}/addFunction">
        <label> Name:
            <input type="text" name="name"/>
        </label>
        <label> Params
            <textarea name="params" cols="40" rows="4"></textarea>
        </label>
        <label> Return value
            <textarea name="return_value" cols="40" rows="4"></textarea>
        </label>
        <label> Description
            <textarea name="description" cols="40" rows="4"></textarea>
        </label>
        <div class="centered">
            <input type="submit" value="Submit"/>
        </div>
    </form>
</body>
</html>

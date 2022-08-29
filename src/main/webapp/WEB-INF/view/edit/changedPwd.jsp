<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022-08-25
  Time: 오전 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="change.pwd.title"/></title>
</head>
<body>
    <p><spring:message code="change.pwd.done"/></p>
    <p>
        <a href ="<c:url value ='/main'/>">
            [<spring:message code="go.main"/>]
        </a>
    </p>
</body>
</html>

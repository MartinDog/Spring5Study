<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022-08-29
  Time: 오후 3:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>회원정보</title>
</head>
<body>
    <p>아이디: ${member.id}</p>
    <p>이메일: ${member.email}</p>
    <p>이름: ${member.name}</p>
    <p>가입일: <tf:formatDateTime value="${member.registerDateTime}" pattern="yyyy-MM-dd HH:mm"/></p>
</body>
</html>

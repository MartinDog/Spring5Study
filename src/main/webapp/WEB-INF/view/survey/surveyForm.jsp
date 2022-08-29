<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022-08-19
  Time: 오후 3:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>설문조사</title>
</head>
<body>
    <h2>설문조사</h2>
    <form action = "survey/test1" method="get">
        <c:forEach var="q" items="${questions}" varStatus="status">
        <p>
            ${status.index+1}.${q.title}<br/>
                <c:if test="${q.choice}">
                    <c:forEach var="option" items="${q.options}">
                        <label><input type="radio" name="responses[${status.index}]"> value = "${option}"</label>
                    </c:forEach>
                </c:if>
                <c:if test="${! q.choice}">
                    <input type="text" name = "responses[${status.index}]">
                </c:if>
        </p>
        </c:forEach>
        <p>
            <label>응답자 나이:<br/>
                <input type="text" name="res.age">
            </label>
        </p>
        <p>
            <label>응답자 위치:<br/>
                <input type="text" name="res.location">
            </label>
        </p>
        <input type = "submit" value="전송">
    </form>
<%--    <form method = post>--%>
<%--        <p>what is your role?--%>
<%--        <label><input type="radio" name = "responses[0]" value ="server"></label>--%>
<%--        <label><input type="radio" name = "responses[0]" value ="front"></label>--%>
<%--        <label><input type="radio" name = "responses[0]" value ="full stack"></label>--%>
<%--        </p>--%>
<%--        <p>what is your prefered devtool?--%>
<%--        <label><input type="radio" name = "responses[1]" value ="Intellij"></label>--%>
<%--        <label><input type="radio" name = "responses[1]" value ="Eclipse"></label>--%>
<%--        <label><input type="radio" name = "responses[1]" value ="Sublime"></label>--%>
<%--        </p>--%>
<%--        <p>Anything you want to say?--%>
<%--        <input type = "text" name="responses[2]">--%>
<%--        </p>--%>

<%--        <p>Where are you now?--%>
<%--            <input type = "text" name="res.location">--%>
<%--        </p>--%>
<%--        <p>How old are you?--%>
<%--            <input type = "text" name="res.age">--%>
<%--        </p>--%>
<%--        <p>--%>
<%--            <input type = "submit" value="전송">--%>
<%--        </p>--%>
<%--    </form>--%>
</body>
</html>

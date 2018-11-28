<%--
  Created by IntelliJ IDEA.
  User: justin
  Date: 28/11/2018
  Time: 10:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Output</title>
</head>
<body>
    <h2>Echo Output</h2>
    <div>
        The input text is '<span><c:out value="${echoForm.text}" /></span>'.
    </div>
    <br>
    <div>
        <a href="<c:url value='/' />">Go to home</a>
    </div>
</body>
</html>

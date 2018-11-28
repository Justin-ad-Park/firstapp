<%--
  Created by IntelliJ IDEA.
  User: justin
  Date: 28/11/2018
  Time: 12:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Firstapp - Input</title>
</head>
<body>
    <h2>입력 화면</h2>
    <form:form modelAttribute="echoForm">
        <div>텍스트를 입력해 주세요:</div>
        <div>
            <form:input path="text" />
            <form:errors path="text" />
        </div>
        <div>
            <form:button>전송</form:button>
        </div>
    </form:form>
</body>
</html>

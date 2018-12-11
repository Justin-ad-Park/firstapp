<%@ page import="java.util.Collection" %><%--
  Created by IntelliJ IDEA.
  User: cjloveu
  Date: 10/12/2018
  Time: 7:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AOP Test</title>
</head>
<body>
<h2>AOP Test</h2>
<div>
    <a href="<c:url value='/' />">Go to home</a>
</div>

<%
    Collection<String> headers = response.getHeaderNames();

    if(headers.size() == 0)
    {
%>
    <div>헤더가 존재하지 않습니다.</div>
<%
    }

    for(String header:headers)
    {
%>
       <%=header + " : " + response.getHeader(header)%> <br>
<%  }
%>
</body>
</html>

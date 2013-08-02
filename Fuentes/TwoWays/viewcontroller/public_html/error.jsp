<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252" isErrorPage="true"
         import="java.io.CharArrayWriter, java.io.PrintWriter"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <title>error</title>
    <link href="./twoways.css" rel="stylesheet" type="text/css"/>    <link href="./twoways.css" rel="stylesheet" type="text/css"/>    <link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">     <link rel="icon" type="image/x-icon" href="img/favicon.ico"> 
  </head>
  <body>
  <jsp:include page="/WEB-INF/jspIncludes/menu.jsp" />
  
   <c:out value="${AuthMsj}" escapeXml="false"/>
    <%
      if (exception != null) 
      { 
        out.print("Ocurrio un error:<br/><pre>"); 
        out.println(exception.getMessage());
        CharArrayWriter charArrayWriter = new CharArrayWriter(); 
        PrintWriter printWriter = new PrintWriter(charArrayWriter, true); 
        exception.printStackTrace(printWriter); 
        out.println(charArrayWriter.toString()); 
        out.println("</pre>");
      } 
    %></body>
</html>
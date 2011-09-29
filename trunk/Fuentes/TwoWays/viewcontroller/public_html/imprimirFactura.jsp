<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ page errorPage="error.jsp" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <title>imprimirFactura</title>
  </head>
  <body>  
    <input type="hidden" id="accion" name="accion" value="<c:out value="${accion}"/>"></input>
    <input type="hidden" id="cliId" name="cliId" value="<c:out value="${cliId}"/>"></input>
  </body>
</html>
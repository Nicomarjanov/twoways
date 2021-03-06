<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <link href="./twoways.css" rel="stylesheet" type="text/css"/>   
    <link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">     
    <link rel="icon" type="image/x-icon" href="img/favicon.ico"> 
    <script type='text/javascript' src="./js/graficos.js">

    </script>    
    <title>Tabla Palabras por cliente</title>
  </head>
   <body>
    <jsp:include page="/WEB-INF/jspIncludes/menu.jsp" />
   <table width="100%" align="center">
     <thead>
      <tr>
        <th colspan="100%" class="tw_form">Tabla de palabras por cliente</th>
      </tr>
     </thead>
     <tbody>
      
        <c:choose>
            <c:when test="${not empty tablaxCliente}">   
            <c:forEach items="${tablaxCliente}" var="item">
                <tr>
                    <td colspan="100%" class="tw_subt"><c:out value="${item.key}" /></td>
                </tr>
                <tr style="background-color='transparent'" align="center">
                    <td width="7%" bgcolor="#ee9a98">Cliente</td>
                    <td width="7%" class="tw_pal">Enero</td>
                    <td width="7%" class="tw_pal">Febrero</td>
                    <td width="7%" class="tw_pal">Marzo</td>
                    <td width="7%" class="tw_pal">Abril</td>                    
                    <td width="7%" class="tw_pal">Mayo</td>
                    <td width="7%" class="tw_pal">Junio</td>
                    <td width="7%" class="tw_pal">Julio</td>
                    <td width="7%" class="tw_pal">Agosto</td>                    
                    <td width="7%" class="tw_pal">Septiembre</td>       
                    <td width="7%" class="tw_pal">Octubre</td>
                    <td width="7%" class="tw_pal">Noviembre</td>                    
                    <td width="7%" class="tw_pal">Diciembre</td>                       
                </tr>
                <tr>                    
                   <c:forEach items="${item.value}" var="item2">
                   <tr>
                         <td width="7%" bgcolor="#ffffff"  align="center"><c:out value="${item2.key}"/></td>                         
                         <c:forEach items="${item2.value}" var="item3" begin="0" end="12">
                             <td width="7%" bgcolor="#ffffff"  align="center"><c:out value="${item3}"/></td>
                        </c:forEach>
                   </tr>
                    </c:forEach>
                </tr>
                <tr>
                    <td colspan="100%" align="center">
                        <input type="button" id="ver" value="Ver gr�fico" onclick='mostrarGraficoCliente(<c:out value="${item.key}"/>)'>
                    </td>
                </tr>
              </c:forEach>
            </c:when>
            <c:otherwise>
                <tr><td colspan="100%">No existen datos</td></tr> 
            </c:otherwise> 
        </c:choose>

    </tbody>
    </table>
  </body>
</html>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="./twoways.css" rel="stylesheet" type="text/css"/>    
    <link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">     
    <link rel="icon" type="image/x-icon" href="img/favicon.ico"> 
    <script  type='text/javascript' src="./js/item.js"></script>
    <script type='text/javascript' src="./js/utils.js"></script>    
    <script type='text/javascript' src='/twoways/dwr/interface/towaysDWR.js'></script>
    <script type='text/javascript' src='/twoways/dwr/engine.js'></script>
    <script type='text/javascript' src='/twoways/dwr/util.js'></script>
    <title>Items</title>
  </head>
  <body>
  <jsp:include page="/WEB-INF/jspIncludes/menu.jsp" />
  <c:out value="${mensaje}" escapeXml="false"/>
  <form name="item" action="items" method="POST">
  <input type="hidden" id="accion" name="accion" value=""/>
  <input type="hidden" id="itmId" name="itmId" value="<c:out value="${item.itmId}"/>"/>
  <table width="100%">
  <tr>
    <th colspan="2" class="tw_form">Ingrese los campos con los datos del Item</th>
  </tr>
  <tr>
  </tr>
  <tr>
    <td nowrap align="right" width="25%">Nombre del &iacute;tem:</td>
    <td align="left"><input type="text" class="tw_form" id="itmNombre" name="itmNombre"  value="<c:out value="${item.itmName}"/>"  size="50" maxlength="100" onkeyup="buscarItems()"  onfocus="javascript:this.style.background='#FFFFFF';"></input></td>
  </tr>
  <tr>
    <td align="right" width="25%">Descripción:</td>
    <td align="left"><input type="text" class="tw_form" id="descItem"  name="descItem"  value="<c:out value="${item.itmDescription}"/>" size="100" maxlength="350"></input></td>    
  </tr>
  <tr>
    <td nowrap align="right" width="25%">Tipo de &iacute;tem:</td>
    <td align="left">
       <select name="tipoItem" id="tipoItem" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';">            
            <option value="" selected="selected">Seleccionar</option>
            <c:choose>
                <c:when test="${item.itmType != null}">
                    <c:if test="${item.itmType == 'Egresos'}">
                        <option value="Egresos" style="background-color:#A4BAC7;" selected="selected">Egresos</option> 
                        <option value="Ingresos" style="background-color:#A4BAC7;">Ingresos</option>
                        <option value="Saldo inicial" style="background-color:#A4BAC7;">Saldo inicial</option>
                    </c:if>
                    <c:if test="${item.itmType == 'Ingresos'}">
                        <option value="Egresos" style="background-color:#A4BAC7;" >Egresos</option> 
                        <option value="Ingresos" style="background-color:#A4BAC7;" selected="selected">Ingresos</option>
                        <option value="Saldo inicial" style="background-color:#A4BAC7;">Saldo inicial</option>                        
                    </c:if>
                    <c:if test="${item.itmType == 'Saldo inicial'}">
                        <option value="Egresos" style="background-color:#A4BAC7;" >Egresos</option> 
                        <option value="Ingresos" style="background-color:#A4BAC7;">Ingresos</option>
                        <option value="Saldo inicial" style="background-color:#A4BAC7;" selected="selected">Saldo inicial</option>
                    </c:if>                    
                </c:when>
                <c:otherwise>
                        <option value="Egresos" style="background-color:#A4BAC7;" >Egresos</option> 
                        <option value="Ingresos" style="background-color:#A4BAC7;">Ingresos</option>     
                        <option value="Saldo inicial" style="background-color:#A4BAC7;">Saldo inicial</option>                        
                </c:otherwise>
            </c:choose>
       </select> 
    </td>    
  </tr>
  </table>
  <br>
  <hr class="tw_hr">
  <table width="25%" align="center">
  <tr>
  <td align="right"><input type="button" id="aceptar" value="Aceptar" onclick="agregar()"/></td>   
  <td align="center"><input type="button" id="cancel" value="Limpiar" OnClick="cancelar()"/></td>   
  <td align="left"><input type="button" id="eliminar" value="Eliminar" OnClick="eliminarItem(<c:out value="${item.itmId}"/>)"/></td>
  </tr>
  </table>
  
  <div id="div-items" style="display:none" align="center">
  <br>
  <hr class="tw_hr">
  <h2 class="tw">Opciones encontradas</h2>
  <table id ="tabla-busqueda">
    <tr><th>Nombre Item</th><th>Tipo de Item</th><th>Descripción</th><th>&nbsp;</th></tr>
  </table>
  </div>
  </form>
  </body>
</html>
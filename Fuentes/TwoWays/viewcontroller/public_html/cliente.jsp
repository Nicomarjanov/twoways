<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ page errorPage="error.jsp" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
  <head>
   <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <link href="./twoways.css" rel="stylesheet" type="text/css"/>
    <script type='text/javascript' src="./js/cliente.js"></script>
    <script type='text/javascript' src="./js/utils.js"></script>
    <script type='text/javascript' src='/twoways/dwr/interface/towaysDWR.js'></script>
    <script type='text/javascript' src='/twoways/dwr/engine.js'></script>
    <script type='text/javascript' src='/twoways/dwr/util.js'></script>
    
    <title>Cliente</title>
    
  </head>
 
  
  <body>
  <jsp:include page="/WEB-INF/jspIncludes/menu.jsp" />
  <c:out value="${mensaje}" escapeXml="false"/>
  <form name="cliente" action="clientes" method="POST">
  <input type="hidden" id="accion" name="accion" value=""/>
  <input type="hidden" id="cliId" name="cliId" value="<c:out value="${cliente.cliId}"/>"/>
  <table width="100%">
  <tr>
    <th colspan="6" class="tw_form">Ingrese los campos con los datos de los clientes</th>
  </tr>
  <tr>
  </tr>
  <tr>
    <td>Nombre o Razón social:</td>
    <td><input type="text" class="tw_form" id="nomCliente" name="nomCliente"  value="<c:out value="${cliente.cliName}"/>"  size="50" maxlength="100"  onkeyup="buscarClientes()"  onfocus="javascript:this.style.background='#FFFFFF';"></input></td>
    <td>Descripción:</td>
    <td colspan=3><input type="text" class="tw_form" id="descCliente" name="descCliente"  value="<c:out value="${cliente.cliDescription}"/>" size="100" maxlength="250"></input></td>
  </tr>
  <tr>
    <td>Mail:</td>
    <td><input type="text" class="tw_form" id="mailCliente"  name="mailCliente"  value="<c:out value="${cliente.cliMail}"/>" size="30" maxlength="100"></input></td>
    <td>Teléfono:</td>
    <td><input type="text" class="tw_form" id="telCliente" name="telCliente"  value="<c:out value="${cliente.cliPhone}"/>"  size="25" maxlength="25"></input></td>
    <td>Moneda:</td>
    <td>
     <select name="listaMoneda" id="listaMoneda" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';">
                <option value="" >Seleccionar</option>
                <c:forEach items="${listaMoneda}" var="item">
                  <c:choose>
                    <c:when test="${item.curId == cliente.currencyTO.curId}">
                       <option value="<c:out value="${item.curId}" />" style="background-color:#A4BAC7;" selected="selected">
                        <c:out value="${item.curName}" />
                      </option> 
                    </c:when>
                    <c:otherwise>
                    <option value="<c:out value="${item.curId}" />" style="background-color:#A4BAC7;">
                        <c:out value="${item.curName}" />
                    </option>
                    </c:otherwise>
                    </c:choose>
                </c:forEach>
       </select> 
    </td>
  </tr>
  <tr>
    <td>Dirección:</td>
    <td><input type="text" class="tw_form" id="dirCliente" name="dirCliente"   value="<c:out value="${cliente.cliAddress}"/>" size="50" maxlength="250"></input></td>
    <td>Codigo Postal:</td>
    <td><input type="text" class="tw_form" id="cpCliente" name="cpCliente"  value="<c:out value="${cliente.cliPostalCode}"/>"  size="10" maxlength="10"></input></td>
    <td>País:</td>
    <td><input type="text" class="tw_form" id="paisCliente" name="paisCliente"  value="<c:out value="${cliente.cliCountry}"/>"  size="20" maxlength="100"></input></td>    
  </tr> 
  </table>
  <br>
  <hr class="tw_hr">
  <table width="100%">
  <tr>
  <td>Tarifas:</td>
  <td>
 
  <select name="listaTarifa" id="listaTarifa" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';">
               
                <option value=""  >Seleccionar</option>
                <c:forEach items="${listaTarifa}" var="item">
                   <c:out value="${item.ratId}" />
                   <c:choose>
                    <c:when test="${false}">
                       <option value="<c:out value="${item.ratId}" />" style="background-color:#A4BAC7;" selected="selected">
                        <c:out value="${item.ratName}" />
                      </option> 
                    </c:when>
                    <c:otherwise>
                    <option value="<c:out value="${item.ratId}" />" style="background-color:#A4BAC7;">
                        <c:out value="${item.ratName}" />
                    </option>
                    </c:otherwise>
                    </c:choose>
                </c:forEach>
       </select>
    </td>
  </tr>
  </table>
  <table width="100%">
  <tr>
  <td align="right"><input type="button" id="aceptar" value="Aceptar" onclick="agregar()"/></td>   
  <td align="left"><input type="button" id="cancel" value="Cancelar" OnClick="cancelar()"/></td>   
  </tr>
  </table>
  
  <div id="div-clientes"  >
  <table id ="tabla-clientes" >
  <tr><th>Nombre o Razón social</th><th>Decripción</th><th>Teléfono</th><th>Mail</th><th>&nbsp;</th></tr>
  </table>
  
  </div>
  </form>
  </body>
</html>
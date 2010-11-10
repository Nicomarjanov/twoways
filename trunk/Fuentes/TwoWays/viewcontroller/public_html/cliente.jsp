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
 
  
  <body >
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
    <td nowrap >Nombre o Razón social:</td>
    <td><input type="text" class="tw_form" id="nomCliente" name="nomCliente"  value="<c:out value="${cliente.cliName}"/>"  size="50" maxlength="100"  onkeyup="buscarClientes()"  onfocus="javascript:this.style.background='#FFFFFF';"></input></td>
    <td nowrap>Mail:</td>
    <td><input type="text" class="tw_form" id="mailCliente"  name="mailCliente"  value="<c:out value="${cliente.cliMail}"/>" size="30" maxlength="100"></input></td>
  </tr>
  <tr>
    <td nowrap>Teléfono:</td>
    <td><input type="text" class="tw_form" id="telCliente" name="telCliente"  value="<c:out value="${cliente.cliPhone}"/>"  size="25" maxlength="25"></input></td>
    <td nowrap >Moneda:</td>
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
  <tr><td ><img src="img/bottom.png" id="aMas" onclick="javascript:mostrarOpcionales();"  alt="Mas datos " width="15" height="15" /><img id="aMenos"  style="border:0;display:none"  onclick="javascript:ocultarOpcionales();" src="img/top.png" alt="Menos" width="15" height="15" /></td></tr>
  <tr id="trOpcionales1" style="display:none">
    <td nowrap >Dirección:</td>
    <td><input type="text" class="tw_form" id="dirCliente" name="dirCliente"   value="<c:out value="${cliente.cliAddress}"/>" size="50" maxlength="250"></input></td>
    <td nowrap>Codigo Postal:</td>
    <td nowrap ><input type="text" class="tw_form" id="cpCliente" name="cpCliente"  value="<c:out value="${cliente.cliPostalCode}"/>"  size="10" maxlength="10"></input>
    País:&nbsp;<input type="text" class="tw_form" id="paisCliente" name="paisCliente"  value="<c:out value="${cliente.cliCountry}"/>"  size="20" maxlength="100"></input></td>    
  </tr>
  <tr id="trOpcionales2" style="display:none" > 
  
    <td valign="top">Descripción:</td>
    <td colspan="100%" ><textarea  rows="3"  cols="90" class="tw_form" id="descCliente"  onkeyup="limitarArea()" name="descCliente" ><c:out value="${cliente.cliDescription}"/></textarea></td>
  </tr>
  </table>
  <br>
  <hr class="tw_hr">
  <table  id="aTar" ><tr><td><a href="javascript:vistaTarifas()" >Tarifas</a></td></Tr></table>
  <table id="tabla-tarifas"  style="display:none" >
  <tr>
  <td valign="top" ><a href="javascript:vistaTarifas()">Tarifas</a></td>
  <td valign="top">
 
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
    <td align="left" valign="top">
    <input type="text" id="tar_val" size=10  onkeydown="keyTarifa()" />
    
    </td>
    <td align="left" valign="top"><img  src="img/arrow_right.png" alt=">" width="20" height="20" title="Agregar Tarifa" onclick="agregarTarifa()" />
    </td>
  <td colspan="100%">
    <table cellpadding="0" cellspacing="0"  style="background:gray">
    <tr>
    <td>
     <table id="list-tarifas" >
     <tr><th width="190">Tarifa</th><th width="50">valor</th><th width="25"></th></tr>
     <tr><td width="190"></td><td width="50"></td><td width="25"></td></tr>
     </table>
     </td></tr><tr><td>
     <div style="width:100%;height:100px;overflow-x: hidden;overflow-y:auto ;" >
     <table id="list-tarifas-body" align="right" width="100%">
     <tr style="display:none"><th width="200">Tarifa</th><th width="50">valor</th><th width="25"></th></tr>
     <c:forEach items="${cliente.clientsRatesTOList}" var="item">
       <tr name="item-tarifa"  bgcolor="#FFFFFF" id="tarId-<c:out value="${item.ratesTO.ratId}" />" ><td width="200" ><c:out value="${item.ratesTO.ratName}" /></td><td width="60" align="right" ><c:out value="${item.clrValue}" /><input type="hidden" name="tarifas-hidden"  value="<c:out value="${item.ratesTO.ratId}" />#<c:out value="${item.clrValue}" />" /></td><td width="37"><img  src="img/Delete.png" height="25" width="25"  alt="Eliminar" onclick="eliminarTarifa('tarId-<c:out value="${item.ratesTO.ratId}" />')" onmouseover="this.style.cursor='hand';" /></td></tr>           
      </c:forEach>
     </table>
     </div>
     </td>
     </tr>
     </table>
  </td>
  </tr>
  </table>
  <table width="100%">
  <tr>
  <td align="right"><input type="button" id="aceptar" value="Aceptar" onclick="agregar()"/></td>   
  <td align="left"><input type="button" id="cancel" value="Cancelar" OnClick="cancelar()"/></td>   
  </tr>
  </table>

  <div id="div-clientes" style="display:none" align="center">
  <br>
  <hr class="tw_hr">
  <h2 class="tw">Opciones encontradas</h2>
  <table id ="tabla-busqueda">
    <tr><th>Nombre o Razón social</th><th>Decripción</th><th>Teléfono</th><th>Mail</th><th>&nbsp;</th></tr>
  </table>
  </div>
   </form>
  </body>
</html>
 <c:out value="${script}" escapeXml="false"/>
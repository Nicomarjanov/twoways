<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <link href="./twoways.css" rel="stylesheet" type="text/css"/>
    <script  type='text/javascript' src="./js/tarifa.js"></script>
    <script type='text/javascript' src="./js/utils.js"></script>    
    <script type='text/javascript' src='/twoways/dwr/interface/towaysDWR.js'></script>
    <script type='text/javascript' src='/twoways/dwr/engine.js'></script>
    <script type='text/javascript' src='/twoways/dwr/util.js'></script>
    <title>Tarifa</title>
  </head>
  <body>
  <c:out value="${mensaje}" escapeXml="false"/>
  <form name="tarifa" action="tarifas" method="POST">
  <input type="hidden" id="accion" name="accion" value=""/>
  <input type="hidden" id="ratId" name="ratId" value="<c:out value="${tarifa.ratId}"/>"/>
  <table width="100%">
  <tr>
    <th colspan="6" class="tw_form">Ingrese los campos con los datos de las tarifas</th>
  </tr>
  <tr>
  </tr>
  <tr>
    <td>Nombre de la tarifa:</td>
    <td><input type="text" class="tw_form" id="nomTarifa" name="nomTarifa"  value="<c:out value="${tarifa.ratName}"/>"  size="50" maxlength="100" onkeyup="buscarTarifas()"  onfocus="javascript:this.style.background='#FFFFFF';"></input></td>
    <td>Descripción:</td>
    <td colspan="5"><input type="text" class="tw_form" id="descTarifa"  name="descTarifa"  value="<c:out value="${tarifa.ratDescription}"/>" size="100" maxlength="350"></input></td>    
    </tr>
    <tr>
    <td>Tipo de tarifa:</td>
    <td>
           <select name="tipoTarifa" id="tipoTarifa" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';">
                <option value="" >Seleccionar</option>
                <c:forEach items="${tipoTarifa}" var="item">
                   <c:choose>
                    <c:when test="item.rtyName == tipoTarifa.rateTypesTO.rtyName">
                       <option value="<c:out value="${item.rtyName}" />" style="background-color:#A4BAC7;" selected="selected">
                        <c:out value="${item.rtyName}" />
                      </option> 
                    </c:when>
                    <c:otherwise>
                    <option value="<c:out value="${item.rtyName}" />" style="background-color:#A4BAC7;">
                        <c:out value="${item.rtyName}" />
                    </option>
                    </c:otherwise>
                    </c:choose>
                </c:forEach>
       </select> 
    </td>
    <td>Moneda:</td>
    <td>       
       <select name="listaMoneda" id="listaMoneda" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';">
                <option value="" >Seleccionar</option>
                <c:forEach items="${listaMoneda}" var="item">
                   <c:choose>
                    <c:when test="item.curId == tarifa.currencyTO.curId">
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
  </table>
  <br>
  <hr class="tw_hr">
  <table width="100%">
  <tr>
  <td align="right"><input type="button" id="aceptar" value="Aceptar" onclick="agregar()"/></td>   
  <td align="left"><input type="button" id="cancel" value="Cancelar" OnClick="cancelar()"/></td>   
  </tr>
  </table>
  
  <div id="div-tarifas"  >
  <table id ="tabla-tarifas" >
    <tr><th>Nombre Tarifa</th><th>Tipo de Tarifa</th><th>Moneda</th><th>Descripción</th><th>&nbsp;</th></tr>
  </table>
  </div>
  </form>
  </body>
</html>
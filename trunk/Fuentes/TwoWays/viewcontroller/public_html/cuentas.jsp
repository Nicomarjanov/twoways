<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <link href="./twoways.css" rel="stylesheet" type="text/css"/>
    <script  type='text/javascript' src="./js/cuentas.js"></script>
    <script type='text/javascript' src="./js/utils.js"></script>    
    <script type='text/javascript' src='/twoways/dwr/interface/towaysDWR.js'></script>
    <script type='text/javascript' src='/twoways/dwr/engine.js'></script>
    <script type='text/javascript' src='/twoways/dwr/util.js'></script>
    <title>Cuentas</title>
  </head>
  <body>
  <jsp:include page="/WEB-INF/jspIncludes/menu.jsp" />
  <c:out value="${mensaje}" escapeXml="false"/>
  <form name="cuenta" action="cuentas" method="POST">
  <input type="hidden" id="accion" name="accion" value=""/>
  <input type="hidden" id="accId" name="accId" value="<c:out value="${cuenta.accId}"/>"/>
  <table width="100%">
  <tr>
    <th colspan="2" class="tw_form">Ingrese los campos con los datos de la Cuenta</th>
  </tr>
  <tr>
  </tr>
  <tr>
    <td nowrap align="right" width="25%">Nombre identificador de la Cuenta:</td>
    <td align="left"><input type="text" class="tw_form" id="accNombre" name="accNombre"  value="<c:out value="${cuenta.accName}"/>"  size="50" maxlength="100" onkeyup="buscarCuentas()"  onfocus="javascript:this.style.background='#FFFFFF';"></input></td>
  </tr>
  <tr>
    <td align="right" width="25%">Dueño de la cuenta:</td>
    <td align="left"><input type="text" class="tw_form" id="accHolder"  name="accHolder"  value="<c:out value="${cuenta.accHolder}"/>" size="100" maxlength="250"></input></td>    
  </tr>  
  <tr>
    <td align="right" width="25%">Número de la cuenta:</td>
    <td align="left"><input type="text" class="tw_form" id="accNumber"  name="accNumber"  value="<c:out value="${cuenta.accNumber}"/>" size="100" maxlength="250"></input></td>    
  </tr>  
  <tr>
    <td nowrap align="right" width="25%">SWIFT code:</td>
    <td align="left"><input type="text" class="tw_form" id="accSwiftCode" name="accSwiftCode"  value="<c:out value="${cuenta.accSwiftCode}"/>"  size="25" maxlength="25" onfocus="javascript:this.style.background='#FFFFFF';"></input></td>
  </tr>
  <tr>
    <td align="right" width="25%">Wire transfer:</td>
    <td align="left"><input type="text" class="tw_form" id="accWireTransfer"  name="accWireTransfer"  value="<c:out value="${cuenta.accWireTransfer }"/>" size="25" maxlength="25"></input></td>       
  </tr>

  <tr>
    <td nowrap align="right" width="25%">Banco:</td>
    <td align="left"><input type="text" class="tw_form" id="accBank" name="accBank"  value="<c:out value="${cuenta.accBank}"/>"  size="30" maxlength="150" onfocus="javascript:this.style.background='#FFFFFF';"></input></td>
  </tr>
  <tr>
    <td align="right" width="25%">Codigo postal:</td>
    <td align="left"><input type="text" class="tw_form" id="accZipCode"  name="accZipCode"  value="<c:out value="${cuenta.accZipCode}"/>" size="20" maxlength="20"></input></td>       
  </tr>
  <tr>
    <td align="right" width="25%">Dirección:</td>
    <td align="left"><input type="text" class="tw_form" id="accDirection"  name="accDirection"  value="<c:out value="${cuenta.accDirection}"/>" size="100" maxlength="350"></input></td>    
  </tr>
  <tr>
    <td align="right" width="25%">País:</td>
    <td align="left"><input type="text" class="tw_form" id="accCountry"  name="accCountry"  value="<c:out value="${cuenta.accCountry}"/>" size="35" maxlength="150"></input></td>       
  </tr>  
  <tr>
    <td align="right" width="25%">Ciudad:</td>
    <td align="left"><input type="text" class="tw_form" id="accCity"  name="accCity"  value="<c:out value="${cuenta.accCity}"/>" size="35" maxlength="150"></input></td>       
  </tr>
  <tr>
    <td align="right" width="25%">Otros detalles:</td>
    <td align="left"><input type="text" class="tw_form" id="accDetails"  name="accDetails"  value="<c:out value="${cuenta.accDetails}"/>" size="100" maxlength="250"></input></td>       
  </tr>
  </table>
  <br>
  <hr class="tw_hr">
  <table width="25%" align="center">
  <tr>
  <td align="right"><input type="button" id="aceptar" value="Aceptar" onclick="agregar()"/></td>   
  <td align="center"><input type="button" id="cancel" value="Limpiar" OnClick="cancelar()"/></td>   
  <td align="left"><input type="button" id="eliminar" value="Eliminar" OnClick="eliminarCuenta(<c:out value="${cuenta.accId}"/>)"/></td>
  </tr>
  </table>
  
  <div id="div-cuentas" style="display:none" align="center">
  <br>
  <hr class="tw_hr">
  <h2 class="tw">Opciones encontradas</h2>
  <table id ="tabla-busqueda">
    <tr><th>Nombre Cuenta</th><th>Número de la Cuenta</th><th>Descripción</th><th>Detalle</th><th>&nbsp;</th></tr>
  </table>
  </div>
  </form>
  </body>
</html>
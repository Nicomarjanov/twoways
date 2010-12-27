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
  <form name="item" action="items" method="POST">
  <input type="hidden" id="accion" name="accion" value=""/>
  <input type="hidden" id="accId" name="accId" value="<c:out value="${item.accId}"/>"/>
  <table width="100%">
  <tr>
    <th colspan="2" class="tw_form">Ingrese los campos con los datos de la Cuenta</th>
  </tr>
  <tr>
  </tr>
  <tr>
    <td nowrap align="right" width="25%">Nombre de la Cuenta:</td>
    <td align="left"><input type="text" class="tw_form" id="accNombre" name="accNombre"  value="<c:out value="${item.accName}"/>"  size="50" maxlength="100" onkeyup="buscarCuentas()"  onfocus="javascript:this.style.background='#FFFFFF';"></input></td>
  </tr>
  <tr>
    <td nowrap align="right" width="25%">Número de la Cuenta:</td>
    <td align="left"><input type="text" class="tw_form" id="accNumero" name="accNumero"  value="<c:out value="${item.accNumero}"/>"  size="25" maxlength="25" onfocus="javascript:this.style.background='#FFFFFF';"></input></td>

  </tr>
  <tr>
    <td align="right" width="25%">Descripción:</td>
    <td align="left"><input type="text" class="tw_form" id="descAcc"  name="descAcc"  value="<c:out value="${item.accDescription}"/>" size="100" maxlength="350"></input></td>    
  </tr>
  <tr>
    <td align="right" width="25%">Detalles de la cuenta:</td>
    <td align="left"><input type="text" class="tw_form" id="detalleAcc"  name="detalleAcc"  value="<c:out value="${item.detalleAcc}"/>" size="100" maxlength="350"></input></td>       
  </tr>
  </table>
  <br>
  <hr class="tw_hr">
  <table width="25%" align="center">
  <tr>
  <td align="right"><input type="button" id="aceptar" value="Aceptar" onclick="agregar()"/></td>   
  <td align="center"><input type="button" id="cancel" value="Limpiar" OnClick="cancelar()"/></td>   
  <td align="left"><input type="button" id="eliminar" value="Eliminar" disabled OnClick="eliminarCuenta(<c:out value="${item.accId}"/>)"/></td>
  </tr>
  </table>
  
  <div id="div-items" style="display:none" align="center">
  <br>
  <hr class="tw_hr">
  <h2 class="tw">Opciones encontradas</h2>
  <table id ="tabla-busqueda">
    <tr><th>Nombre Cuenta</th><th>Número de la Cuenta</th><th>Descripción</th><th>&nbsp;</th></tr>
  </table>
  </div>
  </form>
  </body>
</html>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
  <head>
   <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <link href="./twoways.css" rel="stylesheet" type="text/css"/>
    <script type='text/javascript' src="./js/usuario.js"></script>
    <script type='text/javascript' src="./js/utils.js"></script>
    <script type='text/javascript' src='/twoways/dwr/interface/towaysDWR.js'></script>
    <script type='text/javascript' src='/twoways/dwr/engine.js'></script>
    <script type='text/javascript' src='/twoways/dwr/util.js'></script>
    
    <title>Usuario</title>
    
  </head>
 
  
  <body>
  <c:out value="${mensaje}" escapeXml="false"/>
  <form name="usuario" action="usuarios" method="POST">
  <input type="hidden" id="accion" name="accion" value=""/>
  <input type="hidden" id="usrId" name="usrId" value="<c:out value="${usuario.usrId}"/>"/>
  <table width="100%">
  <tr>
    <th colspan="6" class="tw_form">Ingrese los campos con los datos de los usuarios</th>
  </tr>
  <tr>
  </tr>
  <tr>
    <td>Nombre:</td>
    <td><input type="text" class="tw_form" id="usrFirstName" name="usrFirstName"  value="<c:out value="${usuario.usrFirstName}"/>"  size="50" maxlength="100"  onkeyup="buscarUsuarios()"  onfocus="javascript:this.style.background='#FFFFFF';"></input></td>
    <td>Apellido:</td>
    <td colspan=3><input type="text" class="tw_form" id="usrLastName" name="usrLastName"  value="<c:out value="${usuario.usrLastName}"/>" size="100" maxlength="250"></input></td>
    <td>Fecha de nacimiento:</td>
    <td><input type="text" class="tw_form" id="usrBirth" name="usrBirth"   value="<c:out value="${usuario.usrBirth}"/>" size="50" maxlength="250"></input></td>
  </tr>
  <tr>
    <td>Mail:</td>
    <td><input type="text" class="tw_form" id="usrMail"  name="usrMail"  value="<c:out value="${usuario.usrMail}"/>" size="30" maxlength="100"></input></td>
    <td>Teléfono movil:</td>
    <td><input type="text" class="tw_form" id="usrMobileNumber" name="usrMobileNumber"  value="<c:out value="${usuario.usrMobileNumber}"/>"  size="25" maxlength="25"></input></td>
    <td>Teléfono fijo:</td>
    <td><input type="text" class="tw_form" id="usrPhoneNumber" name="usrPhoneNumber"  value="<c:out value="${usuario.usrPhoneNumber}"/>"  size="25" maxlength="25"></input></td>
    <td>Teléfono oficina:</td>
    <td><input type="text" class="tw_form" id="usrOfficeNumber" name="usrOfficeNumber"  value="<c:out value="${usuario.usrOfficeNumber}"/>"  size="25" maxlength="25"></input></td>
  </tr>
  </table>
  <br>
  <hr class="tw_hr">
  <table width="100%">
  <tr>
  <td>Roles:</td>
  <td><select name="listaRoles" id="listaRoles" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';">
                <option value="" >Seleccionar</option>
                <c:forEach items="${listaRoles}" var="item">
                   <c:choose>
                    <c:when test="${item.rolId == usuario.rolesTO.rolId}">
                       <option value="<c:out value="${item.rolId}" />" style="background-color:#A4BAC7;" selected="selected">
                        <c:out value="${item.rolName}" />
                      </option> 
                    </c:when>
                    <c:otherwise>
                    <option value="<c:out value="${item.rolId}" />" style="background-color:#A4BAC7;">
                        <c:out value="${item.rolName}" />
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
  
  <div id="div-usuarios"  >
  <table id ="tabla-usuarios" >
  <tr><th>Nombre</th><th>Apellido</th><th>Mail</th><th>Fecha de nacimiento</th><th>Teléfono movil</th><th>Teléfono personal</th><th>Teléfono oficina</th><th>&nbsp;</th></tr>
  </table>
  
  </div>
  </form>
  </body>
</html>
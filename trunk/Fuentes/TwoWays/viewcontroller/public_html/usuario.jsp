<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
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
  <jsp:include page="/WEB-INF/jspIncludes/menu.jsp" />  
  <c:out value="${mensaje}" escapeXml="false"/>
  <form name="usuario" action="usuarios" method="POST">
  <input type="hidden" id="accion" name="accion" value=""/>
  <input type="hidden" id="userId" name="userId" value="<c:out value="${usuario.usrId}"/>"/>
  <div align="center">
  <table width="100%">
  <tr>
    <th colspan="6" class="tw_form">Ingrese los campos con los datos de los usuarios</th>
  </tr>
  <tr>
    <td align="right" width="15%" nowrap>Id. Usuario:</td>
    <td align="left" width="15%"><input type="text" class="tw_form" id="usrId" name="usrId"  value="<c:out value="${usuario.usrId}"/>"  size="10" maxlength="10"  onkeyup="buscarUsuarios()"  onfocus="javascript:this.style.background='#FFFFFF';"></input></td>
  </tr>
   <tr>
    <td align="right" width="15%">Contraseña:</td>
    <td align="left" width="15%"><input type="text" class="tw_form" id="usrPass" name="usrPass"  value="<c:out value="${usuario.usrPass}"/>"  size="10" maxlength="10"  onfocus="javascript:this.style.background='#FFFFFF';"></input></td>
   </tr>
   <tr>
    <td align="right" width="15%">Roles:</td>
    <td align="left" width="15%"><select name="listaRoles" id="listaRoles" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';">
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
  <tr>
    <td align="right" width="15%">Nombre:</td>
    <td align="left" width="15%"><input type="text" class="tw_form" id="usrFirstName" name="usrFirstName"  value="<c:out value="${usuario.usrFirstName}"/>"  size="20" maxlength="100"  onkeyup="buscarUsuarios()"  onfocus="javascript:this.style.background='#FFFFFF';"></input></td>
    <td align="right" width="15%">Apellido:</td>
    <td align="left" width="15%"><input type="text" class="tw_form" id="usrLastName" name="usrLastName"  value="<c:out value="${usuario.usrLastName}"/>" size="20" maxlength="100"></input></td>
  </tr>
  <tr>
    <td ><a id="aMas" href="javascript:mostrarOpcionales();">
        <img border="0" alt="Mas..." src="img/bottom.png" width="15px" height="15px"/></a><a  id="aMenos"  style="display:none"  href="javascript:ocultarOpcionales();">
        <img border="0" alt="Menos" src="img/top.png" width="15px" height="15px"/></a></td>
  </tr>
    <tr id="trOpcionales1" style="display:none">
    <td align="right" width="15%">Mail:</td>
    <td align="left"><input type="text" class="tw_form" id="usrMail"  name="usrMail"  value="<c:out value="${usuario.usrMail}"/>" size="20" maxlength="100"></input></td>
    <td align="right" nowrap>Fecha de nacimiento:</td>
    <td align="left"><input type="text" class="tw_form" id="usrBirth" name="usrBirth"   value="<fmt:formatDate value="${usuario.usrBirth}"    pattern="dd/MM/yyyy" />" size="10" maxlength="10"></input></td>
  </tr>
  <tr id="trOpcionales2" style="display:none" > 
    <td align="right" nowrap width="15%">Teléfono movil:</td>
    <td align="left" ><input type="text" class="tw_form" id="usrMobileNumber" name="usrMobileNumber"  value="<c:out value="${usuario.usrMobileNumber}"/>"  size="15" maxlength="25"></input></td>
    <td align="right" nowrap>Teléfono fijo:</td>
    <td align="left" ><input type="text" class="tw_form" id="usrPhoneNumber" name="usrPhoneNumber"  value="<c:out value="${usuario.usrPhoneNumber}"/>"  size="15" maxlength="25"></input></td>
    <td align="right" nowrap>Teléfono oficina:</td>
    <td align="left" ><input type="text" class="tw_form" id="usrOfficeNumber" name="usrOfficeNumber"  value="<c:out value="${usuario.usrOfficeNumber}"/>"  size="15" maxlength="25"></input></td>
  </tr>
  </table>
  </div>
  <br>
  <hr>
  <table width="25%" align="center">
   <tr>
      <td align="right"><input type="button" id="aceptar" value="Aceptar" onclick="agregar()"/></td>   
      <td align="center"><input type="button" id="cancel" value="Limpiar" OnClick="cancelar()"/></td> 
      <td align="left"><input type="button" id="eliminar" value="Eliminar" OnClick="eliminarUsuario('<c:out value="${usuario.usrId}"/>')"/></td>
    </tr>
  </table>
  
  <div id="div-usuarios" style="display:none" align="center">
  <table id ="tabla-busqueda" >
  <tr><th>Id.Usuario</th><th>Nombre</th><th>Apellido</th><th>Mail</th><th>Fecha de nacimiento</th><th>&nbsp;</th></tr>
  </table>
  
  </div>
  </form>
  </body>
</html>
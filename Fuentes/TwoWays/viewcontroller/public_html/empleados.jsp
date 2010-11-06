<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ page errorPage="error.jsp" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
  <head>
   <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <link href="./twoways.css" rel="stylesheet" type="text/css"/>
    <script type='text/javascript' src="./js/empleados.js"></script>
    <script type='text/javascript' src="./js/utils.js"></script>
    <script type='text/javascript' src='/twoways/dwr/interface/towaysDWR.js'></script>
    <script type='text/javascript' src='/twoways/dwr/engine.js'></script>
    <script type='text/javascript' src='/twoways/dwr/util.js'></script>
    
    <title>Cliente</title>
    
  </head>
 
  
  <body>
  <jsp:include page="/WEB-INF/jspIncludes/menu.jsp" />
  <c:out value="${mensaje}" escapeXml="false"/>
  <form name="empleados" action="empleados" method="POST">
  <input type="hidden" id="accion" name="accion" value=""/>
  <input type="hidden" id="empId" name="empId" value="<c:out value="${empleado.empId}"/>"/>
  <table width="100%">
  <tr>
    <th colspan="6" class="tw_form">Ingrese los campos con los datos de los empleados</th>
  </tr>
  <tr>
  </tr>
  <tr>
    <td nowrap >Nombre:</td>
    <td><input type="text" class="tw_form" id="empFirstName" name="empFirstName"  value="<c:out value="${empleado.empFirstName}"/>"  size="50" maxlength="100"  onkeyup="buscarClientes()"  onfocus="javascript:this.style.background='#FFFFFF';"></input></td>
    <td nowrap>Apellido:</td>
    <td colspan=2><input type="text" class="tw_form" id="empLastName" name="empLastName"  value="<c:out value="${empleado.empLastName}"/>" size="20" maxlength="100"></input></td>
  </tr>
  <tr>
    <td>Mail:</td>
    <td><input type="text" class="tw_form" id="empMail"  name="empMail"  value="<c:out value="${empleado.empMail}"/>" size="20" maxlength="100"></input></td>
    <td>Msn:</td>
    <td><input type="text" class="tw_form" id="empMsn"  name="empMsn"  value="<c:out value="${empleado.empMsn}"/>" size="20" maxlength="100"></input></td>        
    <td nowrap>Fecha de nacimiento:</td>
    <td><input type="text" class="tw_form" id="empBirth" name="empBirth"   value="<c:out value="${empleado.empBirth}"/>" size="10" maxlength="10"></input></td>
  </tr>
  <tr>
    <td nowrap>Teléfono movil:</td>
    <td><input type="text" class="tw_form" id="empMobileNumber" name="empMobileNumber"  value="<c:out value="${empleado.empMobileNumber}"/>"  size="15" maxlength="25"></input></td>
    <td nowrap>Teléfono fijo:</td>
    <td><input type="text" class="tw_form" id="empPhoneNumber" name="empPhoneNumber"  value="<c:out value="${empleado.empPhoneNumber}"/>"  size="15" maxlength="25"></input></td>
  </tr>   
  <tr>
    <td nowrap>Dirección:</td>
    <td colspan=2><input type="text" class="tw_form" id="empAddress" name="empAddress"  value="<c:out value="${empleado.empAddress}"/>"  size="15" maxlength="25"></input></td>  
    <td nowrap>Ubicación:</td>
    <td colspan=2><input type="text" class="tw_form" id="empLocation" name="empLocation"  value="<c:out value="${empleado.empLocation}"/>"  size="25" maxlength="25"></input></td>
  </tr>
  <tr>
    <td nowrap>Disponibilidad:</td>
    <td colspan=2><input type="text" class="tw_form" id="empAvailability" name="empAvailability"  value="<c:out value="${empleado.empAvailability}"/>"  size="10" maxlength="10"></input>
    <td nowrap>Observaciones:</td>
    <td colspan=2><input type="text" class="tw_form" id="empObservations" name="empObservations"  value="<c:out value="${empleado.empObservations}"/>"  size="20" maxlength="100"></input></td>    
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

  <div id="div-empleados" style="display:none" align="center">
  <br>
  <hr class="tw_hr">
  <h2 class="tw">Opciones encontradas</h2>
  <table id ="tabla-busqueda">
    <tr><th>Nombre</th><th>Apellido</th><th>Teléfono</th><th>Mail</th><th>Msn</th><th>&nbsp;</th></tr>
  </table>
  </div>
  </form>
  </body>
</html>
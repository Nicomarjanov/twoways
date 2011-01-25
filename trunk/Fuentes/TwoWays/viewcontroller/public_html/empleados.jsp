<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ page errorPage="error.jsp" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<html>
  <head>
   <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <link href="./twoways.css" rel="stylesheet" type="text/css"/>
    <script type='text/javascript' src="./js/empleados.js"></script>
    <script type='text/javascript' src="./js/utils.js"></script>
    <script type='text/javascript' src='/twoways/dwr/interface/towaysDWR.js'></script>
    <script type='text/javascript' src='/twoways/dwr/engine.js'></script>
    <script type='text/javascript' src='/twoways/dwr/util.js'></script>
    
    <title>Empleado</title>
    
  </head>
 
  
  <body>
  <jsp:include page="/WEB-INF/jspIncludes/menu.jsp" />
  <c:out value="${mensaje}" escapeXml="false"/>
  <form name="empleado" action="empleados" method="POST">
  <input type="hidden" id="accion" name="accion" value=""/>
  <input type="hidden" id="empId" name="empId" value="<c:out value="${empleado.empId}"/>"/>
  <input type="hidden" id="traId" name="traId" value="<c:out value="${traductor.traId}"/>"/>  
  <table width="100%" >
  <tr>
    <th colspan="6" class="tw_form">Ingrese los campos con los datos de los empleados</th>
  </tr>
  </table>
  <table align="center" width="50%">
        <tr>
            <th colspan=3 align="center" style="font-size:10px;">
                <b>Seleccione las especialidades del empleado:</b>
            </th>
        </tr>
        <tr>
            <td style="font-size:10px;" align="right"><b>Especialidades disponibles</b></td>
            <td></td>
            <td style="font-size:10px;"  align="left"><b>Especialidades asigandas</b></td>
        </tr>
        <tr>
           <td rowspan=3 align="right">
               <select multiple="multiple" size="4" name="listaTipoEmp" id="listaTipoEmp" style="border:solid 1px #005C8D;width:200px; font-size: 9px;" onfocus="javascript:this.style.background='#FFFFFF';">                                            
                <c:forEach items="${listaTipoEmp}" var="item">
                       <option value="<c:out value="${item.etyName}"/>" title="<c:out value="${item.etyDescription}"/>">
                        <c:out value="${item.etyName}" />
                      </option> 
                </c:forEach>
              </select>      
            </td>
            <td align="center">
            <table>
              <tr>
                <td>
                <a href="javascript: asignar();"><img style="border:0;" title="Asignar" alt=">" width="17" height="17" src="img/play.png" onmouseover="this.style.cursor='hand';"></img></a>
                </td>
              <tr>
                <td>
                <a href="javascript: desAsignar();"><img style="border:0;" title="Desasignar" alt="<" width="17" height="17" src="img/back.png" onmouseover="this.style.cursor='hand';"></img></a>
                </td>
              </tr>
              <tr>
                <td>
                <a href="javascript: desAsignarTodo();"><img style="border:0;" title="Desasignar todo" alt="|<" width="17" height="17" src="img/player_start.png" onmouseover="this.style.cursor='hand';"></img></a>
                </td>
              </tr>
            </table>
            <td rowspan=3 align="left">
                <select name="listaItemsSelect" id="listaItemsSelect" size="4" multiple="multiple" style="border:solid 1px #005C8D;width:200px; font-size: 9px;">                
                <c:forEach items="${empleado.employeesTypesTOList}" var="item">
                       <option value="<c:out value="${item.employeeTypeTO.etyName}"/>" title="<c:out value="${item.employeeTypeTO.etyDescription}"/>">
                        <c:out value="${item.employeeTypeTO.etyName}" />
                      </option> 
                </c:forEach>
                </select>
            </td>
         </tr>
  </table>
  <table width="100%" align="center">
  <tr>
    <td nowrap  align="right" width="15%" >Nombre:</td>
    <td align="left" width="30%"><input type="text" class="tw_form" id="empFirstName" name="empFirstName"  value="<c:out value="${empleado.empFirstName}"/>"  size="25" maxlength="100"  onkeyup="buscarEmpleados()"  onfocus="javascript:this.style.background='#FFFFFF';"></input></td>
    <td nowrap align="right" width="15%">Apellido:</td>
    <td colspan=2 align="left" width="15%"><input type="text" class="tw_form" id="empLastName" name="empLastName"  value="<c:out value="${empleado.empLastName}"/>" size="25" maxlength="100" onfocus="javascript:this.style.background='#FFFFFF';"></input></td>
  </tr>
  <tr>
    <td align="right" width="15%">Mail:</td>
    <td align="left" width="30%"><input type="text" class="tw_form" id="empMail"  name="empMail"  value="<c:out value="${empleado.empMail}"/>" size="20" maxlength="100" onfocus="javascript:this.style.background='#FFFFFF';"></input></td>
    <td align="right" width="15%">MSN:</td>
    <td align="left" width="15%"><input type="text" class="tw_form" id="empMsn"  name="empMsn"  value="<c:out value="${empleado.empMsn}"/>" size="20" maxlength="100" onfocus="javascript:this.style.background='#FFFFFF';"></input></td>        
    <td nowrap align="right" width="15%">Fecha de nacimiento:</td>
    <td align="left" width="15%"><input type="text" class="tw_form" id="empBirth" name="empBirth"   value="<c:out  value="${empleado.empBirth}"/>" size="10" maxlength="10" onkeypress="formatoFechaKeyPress(this); return validarSiNumeroInCh(event)" onBlur="validarFecha(this,'de nacimiento')"></input></td>
  </tr>
  <tr>
    <td nowrap align="right" width="15%">Teléfono movil:</td>
    <td align="left" width="30%"><input type="text" class="tw_form" id="empMobileNumber" name="empMobileNumber"  value="<c:out value="${empleado.empMobileNumber}"/>"  size="15" maxlength="25"></input></td>
    <td nowrap align="right" width="15%">Teléfono fijo:</td>
    <td align="left" width="15%"><input type="text" class="tw_form" id="empPhoneNumber" name="empPhoneNumber"  value="<c:out value="${empleado.empPhoneNumber}"/>"  size="15" maxlength="25"></input></td>
  </tr>   
   <tr>
    <td colspan="100%" align="left">
    <img src="img/bottom.png" id="aMas" onclick="javascript:mostrarOpcionales();"  title="Agregar más datos" alt="Mas datos " width="15" height="15" onmouseover="this.style.cursor='hand';"/>
    <img id="aMenos"  style="border:0;display:none"  onclick="javascript:ocultarOpcionales();" title="Contraer" src="img/top.png" alt="Menos" width="15" height="15" onmouseover="this.style.cursor='hand';"/></td>
  </tr>
  <tr id="trOpcionales1" style="display:none">    
    <td nowrap align="right" width="15%">Dirección:</td>
    <td align="left" width="30%"><input type="text" class="tw_form" id="empAddress" name="empAddress"  value="<c:out value="${empleado.empAddress}"/>"  size="40" maxlength="150"></input></td>  
    <td nowrap align="right" width="15%">Ubicación:</td>
    <td colspan=2 align="left"><input type="text" class="tw_form" id="empLocation" name="empLocation"  value="<c:out value="${empleado.empLocation}"/>"  size="25" maxlength="25"></input></td>
  </tr>
  <tr id="trOpcionales2" style="display:none" > 
    <td nowrap valign="top" align="right" width="15%">Disponibilidad:</td>
    <td valign="top" align="left" width="30%"><input type="text" class="tw_form" id="empAvailability" name="empAvailability"  value="<c:out value="${empleado.empAvailability}"/>"  size="40" maxlength="150"></input>
    <td nowrap valign="top" align="right" width="15%">Observaciones:</td>
    <td colspan="100%" ><textarea  rows="3" cols="40" class="tw_form" id="empObservations"  onkeyup="limitarArea()" name="empObservations" ><c:out value="${empleado.empObservations}"/></textarea></td>    
  </tr>
  </table>
  <br>
  <hr class="tw_hr">
  <table id="aTar">
    <tr>
        <td valign="top" align="left"><a href="javascript:vistaTarifas()" >
          <img id="tarifa+"  style="border:0;" title="Mostrar tarifa del empleado" src="img/currency_dollar green.png" alt="Tarifas" width="25" height="25" onmouseover="this.style.cursor='hand';"/></a>
        </td>
    </Tr>
  </table>
  <table id="tabla-tarifas"  style="display:none">
  <tr>
  <td valign="top" align="left"><a href="javascript:vistaTarifas()">
    <img id="tarifa-"  style="border:0;" title="Ocultar tarifa del empleado" src="img/currency_dollar blue.png" alt="Tarifas" width="25" height="25" onmouseover="this.style.cursor='hand';"/></a>
  </td>
  <td valign="top" align="right" width="35%">
        <select name="listaTipoEmpTar" id="listaTipoEmpTar" style="border:solid 1px #005C8D;width:200px; font-size: 13px;" onfocus="javascript:this.style.background='#FFFFFF';createDynamicDropdown('listaTipoEmpTar', 'listaTarifa', 'dropDown3');" onchange="createDynamicDropdown('listaTipoEmpTar', 'listaTarifa', 'dropDown3');">                                            
            <c:forEach items="${empleado.employeesTypesTOList}" var="item">
                   <option value="<c:out value="${item.employeeTypeTO.etyName}"/>" title="<c:out value="${item.employeeTypeTO.etyDescription}"/>">
                    <c:out value="${item.employeeTypeTO.etyName}" />
                  </option> 
            </c:forEach>
        </select> 
  </td>
  <td valign="top">
         <select name="listaTarifa" id="listaTarifa" style="display:none;">              
                <option value="">Seleccionar</option>
                <c:forEach items="${listaTarifa}" var="item">
                   <c:out value="${item.ratId}" />
                   <c:choose>
                    <c:when test="${false}">
                       <option name="<c:out value="${item.ratType}" />" value="<c:out value="${item.ratId}" />" style="background-color:#A4BAC7;" selected="selected">
                            <c:out value="${item.ratName}" />
                       </option> 
                    </c:when>
                    <c:otherwise>
                        <option  name="<c:out value="${item.ratType}" />" value="<c:out value="${item.ratId}" />" style="background-color:#A4BAC7;">
                            <c:out value="${item.ratName}" />
                        </option>
                    </c:otherwise>
                    </c:choose>
                </c:forEach>
       </select>
       <select id="dropDown3" name="dropDown3" style="border:solid 1px #005C8D;width:200px; font-size: 13px;" onfocus="createDynamicDropdown('listaTipoEmpTar', 'listaTarifa', 'dropDown3');">
                <c:choose>
                <c:when test="${empleado.employeesRatesTOList} == null">
                    <option>Cargando...</option>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${empleado.employeesRatesTOList}" var="item">                       
                       <option value="<c:out value="${item.ratesTO.ratName}"/>" title="<c:out value="${item.ratesTO.ratName}"/>">
                            <c:out value="${item.ratesTO.ratName}"/>
                        </option> 
                     </c:forEach>
                </c:otherwise>
                </c:choose>
       </select>
  </td> 
    <td align="left" valign="top">
    <input type="text" class="tw_form" id="tar_val" size=10  onkeydown="keyTarifa()" />
    
    </td>
    <td align="left" valign="top">
        <img  src="img/next.png" alt=">" width="20" height="20" title="Agregar Tarifa" onclick="agregarTarifa()" onmouseover="this.style.cursor='hand';"/>
    </td>
  <td colspan="100%">
    <table cellpadding="0" cellspacing="0"  style="background:gray">
    <tr>
    <td>
     <table id="list-tarifas" >
     <tr>
         <th width="130">Tipo</th><th width="130">Tarifa</th><th width="40">valor</th><th width="25"></th>
     </tr>
     <tr>
        <td width="130"></td><td width="130"></td><td width="40"></td><td width="25"></td>
     </tr>
     </table>
     </td></tr><tr><td>
     <div style="width:100%;height:100px;overflow-x: hidden;overflow-y:auto ;" >
     <table id="list-tarifas-body" align="right" width="100%">
     <tr style="display:none">
        <th width="130">Tipo</th><th width="130">Tarifa</th><th width="40">valor</th><th width="25"></th>
     </tr>
     <c:forEach items="${empleado.employeesRatesTOList}" var="item">
       <tr name="item-tarifa"  bgcolor="#FFFFFF" id="tarId-<c:out value="${item.ratesTO.ratId}" />" >
            <td width="130" ><c:out value="${item.ratesTO.rateTypesTO.rtyName}" /></td>
            <td width="130" ><c:out value="${item.ratesTO.ratName}" /></td>
            <td width="40" align="right" ><c:out value="${item.emrValue}" />
                <input type="hidden" name="tarifas-hidden"  value="<c:out value="${item.ratesTO.ratId}" />#<c:out value="${item.emrValue}" />" /></td>
            <td width="25" ><img  src="img/Delete.png" height="18" width="18"  alt="Eliminar" onclick="eliminarTarifa('tarId-<c:out value="${item.ratesTO.ratId}" />')" onmouseover="this.style.cursor='hand';" /></td>
        </tr>           
      </c:forEach>
     </table>
     </div>
     </td>
     </tr>
     </table>
  </td>
  </tr>
  </table>
  <hr>
  <table id="aTrad">
    <tr>
        <c:choose>
        <c:when test="${traductor.languaguesTO.lanId != null}">
            <td valign="top" align="left"><a href="javascript:vistaTraductor()" >
                    <img id="traductor+"  style="border:0;" title="Mostrar traductor del empleado" src="img/lang+.png" alt="Traductor" onmouseover="this.style.cursor='hand';"/></a>
            </td>
        </c:when>
        <c:otherwise>
            <td valign="top" align="left"><a href="javascript:vistaTraductor()" >
                    <img id="traductor+"  style="border:0;" title="Mostrar traductor del empleado" src="img/lang.png" alt="Traductor" onmouseover="this.style.cursor='hand';"/></a>
            </td>
        </c:otherwise>
        </c:choose>
    </Tr>
  </table>
  <table id="tabla-traductor"  style="display:none" align="center" width="100%">
  <tr>
      <td colspan=6 valign="top" align="left"><a href="javascript:vistaTraductor()">       
        <img id="traductor-"  style="border:0;" title="Ocultar traductor del empleado" src="img/lang-.png" alt="Traductor" onmouseover="this.style.cursor='hand';"/></a>
      </td>
  </tr>
  <tr>
      <td  width="20%">&nbsp;</td>
      <td colspan=4 align="center" style="font-size: 15px; font-weight:bold; color: #80211D;">Ingrese los datos del traductor
      </td>
      <td  width="20%">&nbsp;</td>
  </tr>
  <tr>
    <td  width="20%">&nbsp;</td>
    <td nowrap align="center" width="10%">Lengua nativa</td>
    <td nowrap align="center" width="10%">Lengua Principal</td>
    <td nowrap align="center" width="10%">Lengua Secundaria</td>        
    <td align="center" width="10%">Especialidad</td>
    <td  width="20%">&nbsp;</td>
   </tr>
    <tr>
    <td>&nbsp;</td>
    <td align="center">
       <select name="listaIdiomas" id="listaIdiomas" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';">
                <option value="" >Seleccionar</option>
                <c:forEach items="${listaIdiomas}" var="item">
                   <c:choose>
                    <c:when test="${item.lanId == traductor.languaguesTO.lanId}">
                       <option value="<c:out value="${item.lanId}" />" style="background-color:#A4BAC7;" selected="selected">
                        <c:out value="${item.lanName}" />
                      </option> 
                    </c:when>
                    <c:when test="${item.lanId == 2}">
                       <option value="<c:out value="${item.lanId}" />" style="background-color:#A4BAC7;" selected="selected">
                        <c:out value="${item.lanName}" />
                      </option> 
                    </c:when>
                    <c:otherwise>
                    <option value="<c:out value="${item.lanId}" />" style="background-color:#A4BAC7;">
                        <c:out value="${item.lanName}" />
                    </option>
                    </c:otherwise>
                    </c:choose>
                </c:forEach>
       </select>
    </td>
    <td align="center">
       <select name="listaLengua1" id="listaLengua1" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';">                
                <option value="" >Seleccionar</option>
                <c:forEach items="${listaLengua1}" var="item">
                   <c:choose>
                    <c:when test="${item.lanId == traductor.languaguesTO1.lanId}">
                       <option value="<c:out value="${item.lanId}" />" style="background-color:#A4BAC7;" selected="selected">
                        <c:out value="${item.lanName}" />
                      </option> 
                    </c:when>
                    <c:otherwise>
                    <option value="<c:out value="${item.lanId}" />" style="background-color:#A4BAC7;">
                        <c:out value="${item.lanName}" />
                    </option>
                    </c:otherwise>
                    </c:choose>
                </c:forEach>
       </select> 
    </td>    
    <td align="center">
       <select name="listaLengua2" id="listaLengua2" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';">                
                <option value="" >Seleccionar</option>
                <c:forEach items="${listaLengua2}" var="item">
                   <c:choose>
                    <c:when test="${item.lanId == traductor.languaguesTO2.lanId}">
                       <option value="<c:out value="${item.lanId}" />" style="background-color:#A4BAC7;" selected="selected">
                        <c:out value="${item.lanName}" />
                      </option> 
                    </c:when>
                    <c:otherwise>
                    <option value="<c:out value="${item.lanId}" />" style="background-color:#A4BAC7;">
                        <c:out value="${item.lanName}" />
                    </option>
                    </c:otherwise>
                    </c:choose>
                </c:forEach>
       </select> 
    </td>    
    <td align="center">
       <select name="tipoEspecialidad" id="tipoEspecialidad" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';">            
            <option value="" selected="selected">Seleccionar</option>
            <c:choose>
                <c:when test="${traductor.traSpecialization != null}">
                    <c:if test="${traductor.traSpecialization == 'Art/Literary'}">
                        <option value="Art/Literary" style="background-color:#A4BAC7;" selected="selected">Art/Literary</option> 
                        <option value="Bus/Financial" style="background-color:#A4BAC7;">Bus/Financial</option>
                        <option value="Law/Patents" style="background-color:#A4BAC7;">Law/Patents</option>
                        <option value="Marketing" style="background-color:#A4BAC7;">Marketing</option>
                        <option value="Medical" style="background-color:#A4BAC7;">Medical</option>
                        <option value="Science" style="background-color:#A4BAC7;">Science</option>
                        <option value="Social Sciences" style="background-color:#A4BAC7;">Social Sciences</option>
                        <option value="Tech/Engineering" style="background-color:#A4BAC7;">Tech/Engineering</option>
                        <option value="Other" style="background-color:#A4BAC7;">Other</option>
                    </c:if>
                    <c:if test="${traductor.traSpecialization == 'Bus/Financial'}">
                        <option value="Art/Literary" style="background-color:#A4BAC7;" >Art/Literary</option> 
                        <option value="Bus/Financial" style="background-color:#A4BAC7;" selected="selected">Bus/Financial</option>
                        <option value="Law/Patents" style="background-color:#A4BAC7;">Law/Patents</option>
                        <option value="Marketing" style="background-color:#A4BAC7;">Marketing</option>
                        <option value="Medical" style="background-color:#A4BAC7;">Medical</option>
                        <option value="Science" style="background-color:#A4BAC7;">Science</option>
                        <option value="Social Sciences" style="background-color:#A4BAC7;">Social Sciences</option>
                        <option value="Tech/Engineering" style="background-color:#A4BAC7;">Tech/Engineering</option>
                        <option value="Other" style="background-color:#A4BAC7;">Other</option>                        
                    </c:if>
                    <c:if test="${traductor.traSpecialization == 'Law/Patents'}">
                        <option value="Art/Literary" style="background-color:#A4BAC7;" >Art/Literary</option> 
                        <option value="Bus/Financial" style="background-color:#A4BAC7;">Bus/Financial</option>
                        <option value="Law/Patents" style="background-color:#A4BAC7;" selected="selected">Law/Patents</option>
                        <option value="Marketing" style="background-color:#A4BAC7;">Marketing</option>
                        <option value="Medical" style="background-color:#A4BAC7;">Medical</option>
                        <option value="Science" style="background-color:#A4BAC7;">Science</option>
                        <option value="Social Sciences" style="background-color:#A4BAC7;">Social Sciences</option>
                        <option value="Tech/Engineering" style="background-color:#A4BAC7;">Tech/Engineering</option>
                        <option value="Other" style="background-color:#A4BAC7;">Other</option>                        
                    </c:if>
                    <c:if test="${traductor.traSpecialization == 'Marketing'}">
                        <option value="Art/Literary" style="background-color:#A4BAC7;" >Art/Literary</option> 
                        <option value="Bus/Financial" style="background-color:#A4BAC7;">Bus/Financial</option>
                        <option value="Law/Patents" style="background-color:#A4BAC7;">Law/Patents</option>
                        <option value="Marketing" style="background-color:#A4BAC7;" selected="selected">Marketing</option>
                        <option value="Medical" style="background-color:#A4BAC7;">Medical</option>
                        <option value="Science" style="background-color:#A4BAC7;">Science</option>
                        <option value="Social Sciences" style="background-color:#A4BAC7;">Social Sciences</option>
                        <option value="Tech/Engineering" style="background-color:#A4BAC7;">Tech/Engineering</option>
                        <option value="Other" style="background-color:#A4BAC7;">Other</option>                        
                    </c:if>
                    <c:if test="${traductor.traSpecialization == 'Medical'}">
                        <option value="Art/Literary" style="background-color:#A4BAC7;" >Art/Literary</option> 
                        <option value="Bus/Financial" style="background-color:#A4BAC7;">Bus/Financial</option>
                        <option value="Law/Patents" style="background-color:#A4BAC7;">Law/Patents</option>
                        <option value="Marketing" style="background-color:#A4BAC7;">Marketing</option>
                        <option value="Medical" style="background-color:#A4BAC7;" selected="selected">Medical</option>
                        <option value="Science" style="background-color:#A4BAC7;">Science</option>
                        <option value="Social Sciences" style="background-color:#A4BAC7;">Social Sciences</option>
                        <option value="Tech/Engineering" style="background-color:#A4BAC7;">Tech/Engineering</option>
                        <option value="Other" style="background-color:#A4BAC7;">Other</option>                        
                    </c:if>
                    <c:if test="${traductor.traSpecialization == 'Science'}">
                        <option value="Art/Literary" style="background-color:#A4BAC7;" >Art/Literary</option> 
                        <option value="Bus/Financial" style="background-color:#A4BAC7;">Bus/Financial</option>
                        <option value="Law/Patents" style="background-color:#A4BAC7;">Law/Patents</option>
                        <option value="Marketing" style="background-color:#A4BAC7;">Marketing</option>
                        <option value="Medical" style="background-color:#A4BAC7;">Medical</option>
                        <option value="Science" style="background-color:#A4BAC7;" selected="selected">Science</option>
                        <option value="Social Sciences" style="background-color:#A4BAC7;">Social Sciences</option>
                        <option value="Tech/Engineering" style="background-color:#A4BAC7;">Tech/Engineering</option>
                        <option value="Other" style="background-color:#A4BAC7;">Other</option>                        
                    </c:if>
                    <c:if test="${traductor.traSpecialization == 'Social Sciences'}">
                        <option value="Art/Literary" style="background-color:#A4BAC7;" >Art/Literary</option> 
                        <option value="Bus/Financial" style="background-color:#A4BAC7;">Bus/Financial</option>
                        <option value="Law/Patents" style="background-color:#A4BAC7;">Law/Patents</option>
                        <option value="Marketing" style="background-color:#A4BAC7;">Marketing</option>
                        <option value="Medical" style="background-color:#A4BAC7;">Medical</option>
                        <option value="Science" style="background-color:#A4BAC7;">Science</option>
                        <option value="Social Sciences" style="background-color:#A4BAC7;" selected="selected">Social Sciences</option>
                        <option value="Tech/Engineering" style="background-color:#A4BAC7;">Tech/Engineering</option>
                        <option value="Other" style="background-color:#A4BAC7;">Other</option>                        
                    </c:if>
                    <c:if test="${traductor.traSpecialization == 'Tech/Engineering'}">
                        <option value="Art/Literary" style="background-color:#A4BAC7;" >Art/Literary</option> 
                        <option value="Bus/Financial" style="background-color:#A4BAC7;">Bus/Financial</option>
                        <option value="Law/Patents" style="background-color:#A4BAC7;">Law/Patents</option>
                        <option value="Marketing" style="background-color:#A4BAC7;">Marketing</option>
                        <option value="Medical" style="background-color:#A4BAC7;">Medical</option>
                        <option value="Science" style="background-color:#A4BAC7;">Science</option>
                        <option value="Social Sciences" style="background-color:#A4BAC7;">Social Sciences</option>
                        <option value="Tech/Engineering" style="background-color:#A4BAC7;"  selected="selected">Tech/Engineering</option>
                        <option value="Other" style="background-color:#A4BAC7;">Other</option>                        
                    </c:if>
                    <c:if test="${traductor.traSpecialization == 'Other'}">
                        <option value="Art/Literary" style="background-color:#A4BAC7;" >Art/Literary</option> 
                        <option value="Bus/Financial" style="background-color:#A4BAC7;">Bus/Financial</option>
                        <option value="Law/Patents" style="background-color:#A4BAC7;">Law/Patents</option>
                        <option value="Marketing" style="background-color:#A4BAC7;">Marketing</option>
                        <option value="Medical" style="background-color:#A4BAC7;">Medical</option>
                        <option value="Science" style="background-color:#A4BAC7;">Science</option>
                        <option value="Social Sciences" style="background-color:#A4BAC7;">Social Sciences</option>
                        <option value="Tech/Engineering" style="background-color:#A4BAC7;">Tech/Engineering</option>
                        <option value="Other" style="background-color:#A4BAC7;"  selected="selected">Other</option>                        
                    </c:if>                    
                </c:when>
                <c:otherwise>
                        <option value="Art/Literary" style="background-color:#A4BAC7;" >Art/Literary</option> 
                        <option value="Bus/Financial" style="background-color:#A4BAC7;">Bus/Financial</option>                
                        <option value="Law/Patents" style="background-color:#A4BAC7;">Law/Patents</option>
                        <option value="Marketing" style="background-color:#A4BAC7;">Marketing</option>
                        <option value="Medical" style="background-color:#A4BAC7;">Medical</option>
                        <option value="Science" style="background-color:#A4BAC7;">Science</option>
                        <option value="Social Sciences" style="background-color:#A4BAC7;">Social Sciences</option>
                        <option value="Tech/Engineering" style="background-color:#A4BAC7;">Tech/Engineering</option>
                        <option value="Other" style="background-color:#A4BAC7;">Other</option>                        
                </c:otherwise>
            </c:choose>
       </select> 
    </td>    
    <td>&nbsp;</td>
    </tr>
  </table> 
  <hr>
  <table width="25%" align="center">
  <tr>
  <td align="right"><input type="button" id="aceptar" value="Aceptar" onclick="agregar()"/></td>   
  <!--<td align="right"><input disabled type="button" id="traductor" value="Alta Traductor" onclick="altaTraductor()"/></td>   -->
  <td align="center"><input type="button" id="cancel" value="Limpiar" OnClick="cancelar()"/></td>    
  <td align="left"><input type="button" id="eliminar" value="Eliminar" OnClick="eliminarEmp()"/></td>    
  </tr>
  </table>

  <div id="div-empleados" style="display:none" align="center">
  <br>
  <h2 class="tw">Opciones encontradas</h2>
  <table id ="tabla-busqueda">
    <tr><th>Nombre</th><th>Apellido</th><th>Mail</th><th>Msn</th><th>Fecha nacimiento<th>&nbsp;</th></tr>
  </table>
  </div>
  
  </form>
  </body>
</html>
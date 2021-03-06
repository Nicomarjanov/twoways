<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <link href="./twoways.css" rel="stylesheet" type="text/css"/>    
    <link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">     
    <link rel="icon" type="image/x-icon" href="img/favicon.ico"> 
    <script  type='text/javascript' src="./js/listaEmpleados.js"></script>
    <script type='text/javascript' src="./js/utils.js"></script>    
    <script type='text/javascript' src='/twoways/dwr/interface/towaysDWR.js'></script>
    <script type='text/javascript' src='/twoways/dwr/engine.js'></script>    
    <script type='text/javascript' src='/twoways/dwr/util.js'></script>
    <script type='text/javascript' src="./js/CalendarPopup.js"></script>    
    <script>
        document.onkeydown = checkKeycode
        function checkKeycode(e) {
            var keycode;
            if (window.event) keycode = window.event.keyCode;
            else if (e) keycode = e.which;
            if( keycode == 13){
               buscarEmpleados();
            }    
        }
    </script>
    <title>Lista de Empleados</title>
  </head>
  <body>
    <jsp:include page="/WEB-INF/jspIncludes/menu.jsp" />
    <c:out value="${mensaje}" escapeXml="false"/>
    <form id="frmlistEmpleado" name="frmlistEmpleado" action="listaEmpleados" method="POST">
    <input type="hidden"  name="accion"  id="accion"  value=""   />
    <input type="hidden"  name="pageId"  id="pageId" value="<c:out value="${pageId}" />" >
    <table width="100%">
      <thead>
      <tr>
        <th class="tw_form" colspan="100%" >Ingrese los criterios de b�squeda para los empleados </th>
      </tr>
      </thead>
      <tbody>
       <tr>
        <td colspan="100%" >&nbsp;</td>
      </tr>
      <tr>
      <td id="mostrar-filtro" style="display:none" valign="top" ><img src="img/filter.png" width="20" height="20" alt="Filtros"  onclick="document.getElementById('table-filtros').style.display='block';document.getElementById('mostrar-filtro').style.display='none'" onmouseover="this.style.cursor='hand';"></img></td>
      <td  id="table-filtros" valign="top" align="left"  >  
       <div style="border: 1px solid;border-color:#FFFFFf;padding:2;spacing:2" >
       <table ><tr><td>
       <table  width="200px"  cellpadding="0" cellspacing="0">
       <thead >
       <tr ><td colspan="100%" style="font-size:1.1em;padding-top:5px;padding-bottom:4px;background-color:#80211D;color:#ffffff;" >Filtros de b�squeda</td><td style="font-size:1.1em;padding-top:5px;padding-bottom:4px;background-color:#80211D;color:#ffffff;" ><div style="background-color:Gray;width:20;height:20" onclick="document.getElementById('table-filtros').style.display='none';document.getElementById('mostrar-filtro').style.display='block'" onmouseover="this.style.cursor='hand';" >X</div></td> </tr>
       </thead>
       <tbody>
       <!--tr>
        <td nowrap>Nombre</td><td colspan="2" ><input type="text" class="tw_form" name="empFirstName"  id="empFirstName" style="width:100%"  value="<c:out value="${empFirstName}"/>"/></td>
       </tr>
       <tr>
        <td nowrap>Apellido</td><td colspan="2" ><input type="text" class="tw_form" name="empLastName"  id="empLastName" style="width:100%" value="<c:out value="${empLastName}"/>" /></td>
       </tr-->
        <tr>
        <td nowrap align="left" width="25%">Empleado</td>
        <td align="left" width="25%">
            <select name="listaEmpleadosSelect" id="listaEmpleadosSelect" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';">            
                <option value="" selected="selected">Seleccionar</option>
                <c:forEach items="${listaEmpleadosSelect}" var="item">
                   <c:choose>
                    <c:when test="${empId == item.empId}">
                       <option value="<c:out value="${item.empId}" />" style="background-color:#A4BAC7;" selected="selected">
                        <c:out value="${item.empFirstName}" /> <c:out value="${item.empLastName}" />
                      </option> 
                    </c:when>
                    <c:otherwise>
                    <option value="<c:out value="${item.empId}" />" style="background-color:#A4BAC7;">
                        <c:out value="${item.empFirstName}" /> <c:out value="${item.empLastName}" />
                    </option>
                    </c:otherwise>
                    </c:choose>
                </c:forEach>
           </select>
        </td>   
      </tr>
       <tr>
        <td >Especialidades</td> 
            <td colspan="2" nowrap>
               <table style="border-left-style:solid; border-left-width:1px; font-size:9px;">
                    <tr>
                        <td>Traducci�n</td>
                        <td>
                            <c:choose>
                                <c:when test="${not empty Traductor}">
                                    <input type="checkbox" id="Traductor" name="Traductor" value="Traducci�n" checked></input>
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" id="Traductor" name="Traductor" value="Traducci�n"></input>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>Edici�n</td>
                        <td>
                            <c:choose>
                                <c:when test="${not empty Editor}">
                                    <input type="checkbox" id="Editor" name="Editor" value="Edici�n" checked></input>
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" id="Editor" name="Editor" value="Edici�n"></input>
                                </c:otherwise>
                            </c:choose>                        
                        </td>
                    </tr>
                    <tr>
                        <td>Revisor Final</td>
                        <td>
                             <c:choose>
                                <c:when test="${not empty Revisor}">
                                    <input type="checkbox" id="Revisor" name="Revisor" value="Revisor" checked></input>
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox"  id="Revisor" name="Revisor" value="Revisor"></input>
                                </c:otherwise>
                            </c:choose>                                     
                        </td>
                        <td>Maquetaci�n</td>
                        <td>
                             <c:choose>
                                <c:when test="${not empty Maquetador}">
                                     <input type="checkbox" id="Maquetador" name="Maquetador" value="Maquetaci�n" checked></input>
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" id="Maquetador" name="Maquetador" value="Maquetaci�n"></input>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <td>PDTP</td>
                        <td>
                             <c:choose>
                                <c:when test="${not empty PDTP}">
                                    <input type="checkbox" id="PDTP" name="PDTP" value="PDTP" checked></input>
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" id="PDTP" name="PDTP" value="PDTP" ></input>                                
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>Proofer</td>
                        <td>
                           <c:choose>
                                <c:when test="${not empty Proofer}">
                                    <input type="checkbox" id="Proofer" name="Proofer" value="Proofer" checked></input>
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" id="Proofer" name="Proofer" value="Proofer"></input>
                                </c:otherwise>
                            </c:choose>                        
                        </td>
                    </tr>
               </table>    
            </td>
       </tr>
       <tr>
       <td nowrap >Proyecto</td>
       <td colspan="2" ><input type="text" class="tw_form" name="ProName"  id="ProName" style="width:100%" value="<c:out value="${proName}"/>" /></td> 
       </tr>
       <tr>
        <td nowrap >Fecha de Entrega </td><td  colspan="2"  nowrap>
        <select id="proFinishDateOpt" name="proFinishDateOpt" >
            
            <c:forEach items="${optionList}" var="item">
              <c:choose>
                <c:when test="${proFinishDateOpt == item}">
                   <option value="<c:out value="${item}" />" style="background-color:#A4BAC7;" selected="selected">
                    <c:out value="${item}" />
                  </option> 
                </c:when>
                <c:otherwise>
                <option value="<c:out value="${item}" />" style="background-color:#A4BAC7;">
                    <c:out value="${item}" />
                </option>
                </c:otherwise>
                </c:choose>
            </c:forEach>
        
        </select>
        <input type="text" class="tw_form" name="proFinishDate"  id="proFinishDate" value="<c:out value="${proFinishDate}" />" style="width:200"  /><div id="divHasta" style="background:#FFFFFF;position:absolute"  ></div> <img  onclick="cal1Hasta.select(document.forms[0].proFinishDate,'selHasta','dd/MM/yyyy'); return false;" NAME="selHasta" ID="selHasta"  height="20" width="20" alt="seleccion" src="img/cal.png" onmouseover="this.style.cursor='hand';"></img>
        </td>
        
        </tr>
       <tr  >
       <td colspan="100%"  valign="top" align="right" ><input type="button" value="Buscar" onclick="buscarEmpleados()"  /></td>
       </tr>
       
       </tbody>
       </table>
       </td></tr></table>
       </div>
       </td><td align="left" width="100%" valign="top" >
        <table id ="tabla-busqueda">
            <thead>
            <tr><th>Nombre</th><th>Apellido</th><th>Especialidad</th><th>Conteo palabras</th><th>Fecha asignaci�n</th><th>Fin asignaci�n</th><th>Proyecto</th><th>Estado proyecto</th><th>Fin proyecto</th></tr>
            </thead>
          <c:choose   >
          <c:when test="${not empty listaEmpleados}">
           <tbody>
            <c:set scope="page" var="color_row" value="${'#E8B6B5'}" />
            
            <c:forEach items="${listaEmpleados}" var="empleado" varStatus="status" >     
           
            <tr bgcolor="<c:out value="${color_row}"/>" >
                <td nowrap ><a href="empleados?empId=<c:out value="${empleado.empId}" />"><c:out value="${empleado.empFirstName}" /></a></td>
                <td nowrap ><c:out value="${empleado.empLastName}" /></td>
                <td nowrap ><c:out value="${empleado.employeeTypeTO.etyName}" /></td>
                <td nowrap ><c:out value="${empleado.projectAssignmentsTO.proAssigmentDetailsTO.padWCount}" /></td>
                <td nowrap ><fmt:formatDate value="${empleado.projectAssignmentsTO.praAssignDate}"    pattern="dd/MM/yyyy HH:mm" /></td>
                <td nowrap ><fmt:formatDate value="${empleado.projectAssignmentsTO.praFinishDate}"    pattern="dd/MM/yyyy HH:mm" /></td>
                <td nowrap ><a href="proyectos?ordId=<c:out value="${empleado.projectAssignmentsTO.projectsTO.ordersTO.ordId}" />"><c:out value="${empleado.projectAssignmentsTO.projectsTO.proName}" /></a></td>
                <td nowrap ><c:out value="${empleado.projectAssignmentsTO.projectsTO.statesTO.staId}" /></td>        
                <td nowrap ><fmt:formatDate value="${empleado.projectAssignmentsTO.projectsTO.proFinishDate}"    pattern="dd/MM/yyyy HH:mm" /></td>
            </tr> 
             <c:choose>
            <c:when test="${status.index % 2 == 0}">  
              <c:set scope="page" var="color_row" value="${'#FCEEED'}" />
            </c:when>
            <c:otherwise>
              <c:set scope="page" var="color_row" value="${'#E8B6B5'}" /> 
            </c:otherwise>
            </c:choose>
            
           </c:forEach>
           <tr>
              <td colspan="3" style="text-align:right;font-size:15px;font-weight:bold;" >Total:</td>
              <td colspan="8" style="text-align:left;font-size:15px;font-weight:bold;" ><c:out value="${totalPalabras}"/></td>
           </tr>
           <tr bgcolor="<c:out value="${color_row}"/>" >
              <td colspan="100%" align="center" >
               <table align="center">
                    <tr><td>
                        <c:if test="${page != 0}">
                            <img src="img/player_start.png" height="20" width="20" onclick="back()" alt="<"/>
                        </c:if>
                           </td><td>P�gina <fmt:formatNumber type="number" minFractionDigits="0" value="${page + 1 }" /> de <fmt:formatNumber type="number" minFractionDigits="0" value="${maxPage }" />
                           </td><td>
                           
                        <c:if test="${(page + 1) < maxPage}"> 
                         <img src="img/player_next.png" height="20" width="20" onclick="next()" alt=">" />
                        </c:if> 
                     </td></tr>
               </table>
               </td>
          </tr>
          <tr>
          <td width="100%" colspan="100%">
            <br>
              <hr class="tw_hr">
              <table width="100%" align="center">
                  <tr>
                      <c:if test="${not empty listaEmpleados}">
                          <td style="text-align:center;" ><input type="button" id="export" value="Exportar" OnClick="exportarCSV()"/></td>   
                      </c:if>
                  </tr>
              </table>
             </td> 
           </tr>
           </tbody>
          </c:when>
          <c:otherwise>
           <c:if test="${not empty accion}">
           <tbody>
            <tr><td colspan="100%">La b�squeda no arrojo resultados</td></tr> 
           </tbody>
           </c:if>
          </c:otherwise>
          </c:choose>
        
      </table>
      </td>
      </tr>
      </tbody>
   </table>
   
   </form>
   
  </body>
</html>
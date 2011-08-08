<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252" %>
<%@ page errorPage="error.jsp" %>
<%@ page import="java.util.*" %>
<%@ page isELIgnored="true" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

<html>
  <head>
   <meta http-equiv="Content-Type" content="multipart/form-data"/>
    <link href="./twoways.css" rel="stylesheet" type="text/css"/>
    <script type='text/javascript' src="./js/agregartarifa.js"></script>
    <script type='text/javascript' src="./js/agregardocumentos.js"></script>
    <script type='text/javascript' src="./js/utils.js"></script>
    <script type='text/javascript' src="./js/commons.js"></script>  
    <script type='text/javascript' src="./js/CalendarPopup.js"></script>  
    <script type='text/javascript' src='/twoways/dwr/interface/towaysDWR.js'></script>
    <script type='text/javascript' src='/twoways/dwr/engine.js'></script>
    <script type='text/javascript' src='/twoways/dwr/util.js'></script>
    <script type='text/javascript' src="./js/proyectos.js"></script>
    <script>
    writeSource('jscallDesde');
    writeSource('jscallHasta');
    </script>
    <title>Asignar</title>
    <c:out value="${ordersdocsOld}"   escapeXml="false" />
     
  </head>
  <body onunload="onClose();">
  <form action="asignaciones" method="POST" >
  <input type ="hidden" name="praId" id="praId" value="<c:out value="${projectAssignmentsTO.praId}" />" /> 
  <input type ="hidden" name="accion" id="accion" />
  <input type ="hidden" name="proId" id="proId" value="<c:out value="${projectAssignmentsTO.projectsTO.proId}" />"  />
  <input type ="hidden" name="projectStartDate" id="projectStartDate" value="<fmt:formatDate  value="${projectAssignmentsTO.projectsTO.proStartDate}"   pattern="dd/MM/yyyy HH:mm"  />"  />
  <input type ="hidden" name="projectFinishDate" id="projectFinishDate" value="<fmt:formatDate  value="${projectAssignmentsTO.projectsTO.proFinishDate}"   pattern="dd/MM/yyyy HH:mm"  />"  />
  <c:choose>
  <c:when  test="${projectAssignmentsTO.praId  != null &&    projectAssignmentsTO.praId != 0 }" >  
      <c:set value="${'disabled=disabled'}" scope="page" var="readOnly" /> 
  </c:when>
   <c:otherwise>
      <c:set value="${'class=tw_form'}" scope="page" var="readOnly" /> 
  </c:otherwise>
  </c:choose>
  <table align="center">
  <tr>
    <td>Fecha de Asignacion</td>
    <td colspan=3 nowrap ><input type="text" <c:out value="${readOnly}" />  name="proAssignStartDate" id="proAssignStartDate"   value="<fmt:formatDate value="${projectAssignmentsTO.praAssignDate}"    pattern="dd/MM/yyyy HH:mm" />" onfocus="javascript:this.style.background='#FFFFFF';"/><div id="divDesde" style="background:#FFFFFF;position:absolute"  ></div> <img  onclick="cal1Desde.select(document.forms[0].proAssignStartDate,'selDesde','dd/MM/yyyy'); return existeDisponbilidad();" NAME="selDesde" ID="selDesde"  height="20" width="20"   alt="seleccion" <c:out value="${readOnly}" /> src="img/cal.png"></img>
    </td>
  </tr> 
  <tr>
    <td>Fecha de Fin de Asignacion</td>
    <td colspan=3 nowrap ><input  type="text" class="tw_form" name="proAssignFinishDate" id="proAssignFinishDate"  value="<fmt:formatDate value="${projectAssignmentsTO.praFinishDate}"    pattern="dd/MM/yyyy HH:mm" />" onfocus="javascript:this.style.background='#FFFFFF';"/><div id="divHasta" style="background:#FFFFFF;position:absolute"  ></div> <img onclick="cal1Hasta.select(document.forms[0].proAssignFinishDate,'selHasta','dd/MM/yyyy'); return false;" NAME="selHasta" ID="selHasta"  height="20" width="20"  alt="seleccion"  src="img/cal.png"></img>
    </td>
  </tr> 
  <tr>
    <td>Servicio</td>
    <td colspan=3 >
      <select name="listaServices" id="listaServices" style="border:solid 1px #005C8D;"  <c:out value="${readOnly}" />  onchange="findEmployees()"  onfocus="javascript:this.style.background='#FFFFFF';">               
                <option value=""  >Seleccionar</option>
                <c:forEach items="${listaServices}" var="item">
                   <c:out value="${item.rtyName}" />
                   <c:choose>
                    <c:when test="${projectAssignmentsTO.serviceTO.rtyName == item.rtyName}">
                       <option value="<c:out value="${item.rtyName}" />"  selected="selected">
                        <c:out value="${item.rtyAlternativeName}" />
                      </option> 
                    </c:when>
                    <c:otherwise>
                    <option value="<c:out value="${item.rtyName}" />" >
                        <c:out value="${item.rtyAlternativeName}" />
                    </option>
                    </c:otherwise>
                    </c:choose>
                </c:forEach>
           </select>
     </td>
  </tr> 
  <tr>
    <td>Empleado</td>
    <td>
        <select name="listaEmployees" id="listaEmployees" <c:out value="${readOnly}" /> style="border:solid 1px #005C8D;"  onchange="changeEmployees()" onfocus="javascript:this.style.background='#FFFFFF';">               
            <option value=""  >Seleccionar</option>
            <c:forEach items="${listaEmployees}" var="item">
               <c:out value="${item.empId}" />
               <c:choose>
                <c:when test="${projectAssignmentsTO.employeesTO.empId == item.empId}">
                   <option value="<c:out value="${item.empId}" />"  selected="selected">
                    <c:out value="${item.empFirstName}" /> <c:out value="${item.empLastName}" />
                  </option> 
                </c:when>
                <c:otherwise>
                <option value="<c:out value="${item.empId}" />" >
                    <c:out value="${item.empFirstName}" /> <c:out value="${item.empLastName}" />
                </option>
                </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
  </td>
  <td  align="right" colspan="2">
   
  </td>
  </tr>
  <tr>
  <td  align="left" colspan="100%">
   <div id="msjEmpleado" style="color:red"  > </div> 
  </td>
  </tr>
  <tr>
  <td  align="left" colspan="100%">
   <div id="msjEmpleadoDisponible" style="color:red"  > </div> 
  </td
  </tr>
  <tr><td colspan="100%" >
  <table align="center" width="50%">
   <tr>
       <th colspan=3 align="center" style="font-size:12px;"><b>Seleccione los Documentos</b></th>
   </tr>
   <tr>
    <td valign="top"  rowspan=3 align="right" >
    <table cellpadding="0" cellspacing="0"  style="background:gray">
    <tr>
    <td >
     <table id="list-documento-body" align="right" width="100%">
      <thead>
         <tr><th width="400" align="center" colspan="2"  >Documento</th><th align="center">Tipo</th><th align="center">Lenguajes</th></tr>
      </thead>
      <tbody  style="width:100%;height:30px;overflow-x: hidden;overflow-y:auto ;" >
   
     <c:forEach items="${ordersTO.ordersDocsTOList}" var="item">
       <c:if test="${item.docType.dotId == 'Source' || item.docType.dotId == 'FTP' || item.docType.dotId == 'Other' }"  >  
                 <c:set scope="page" value="${''}" var="check"  />
                 <c:set scope="page" value="${'disabled =\"disabled\"'}" var="disa"  />
                 
                 <c:forEach items="${projectAssignmentsTO.proAssigmentsDetailsTO}" var="det">
                 
                    <c:if test="${det.ordersDocsTO.odoId == item.odoId}">
                       <c:set scope="page" value="${'checked=\"checked\"'}" var="check"  />
                       <c:if test="${projectAssignmentsTO.serviceTO.rtyName != 'Maquetador'}">
                            <c:set scope="page" value="${''}" var="disa"  />
                       </c:if>
                     <c:set scope="page" value="${det.pranslatorsLanguaguesTO.tlaId}" var="lanSel"  />
                    </c:if>
                    
                </c:forEach>
                
                
                
               <tr name="item-documento"  bgcolor="#FFFFFF" id="ordId-<c:out value="${item.odoName}" />" >
                     <td width="300" ><a href="/twoways/downloadfile?docId=<c:out value="${item.odoId}" />"  ><c:out value="${item.odoName}" /></a></td>
                     <td ><input type="checkbox"  <c:out value="${check}" /> name="listdocs-<c:out value="${item.odoId}" />"  id="listdocs-<c:out value="${item.odoId}" />"   onclick="cambioCheck('<c:out value="${item.odoId}" />')" /></td>
                     <td width="150" ><c:out value="${item.docType.dotId}" /></td>
               <td>
               
               <c:if test="${item.docType.dotId == 'Source' || item.docType.dotId == 'FTP' }"  >  
        
                    <select name="languagues" id="languagues<c:out value="${item.odoId}" />"  <c:out value="${disa}" />    >
                        <option value=""  >Seleccionar</option>
                         <c:forEach items="${languagues}" var="item">
                           <c:choose>
                            <c:when test="${lanSel == item['TLA_ID']}">
                               <option value="<c:out value="${item['TLA_ID']}" />"  selected="selected">
                                [<c:out value="${item['LAN_ORIGEN']}" />-<c:out value="${item['ACRON_ORIGEN']}" />] - [<c:out value="${item['LAN_DESTINO']}" />-<c:out value="${item['ACRON_DESTINO']}" />]
                              </option> 
                            </c:when>
                            <c:otherwise>
                            <option value="<c:out value="${item['TLA_ID']}" />" >
                                 [<c:out value="${item['LAN_ORIGEN']}" />-<c:out value="${item['ACRON_ORIGEN']}" />] - [<c:out value="${item['LAN_DESTINO']}" />-<c:out value="${item['ACRON_DESTINO']}" />]
                            </option>
                            </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </c:if>
               </td></tr>  
        </c:if>
      </c:forEach>
      </tbody>
      
     </table>
     
               </td>
             </tr>
            </table>    
   </td></tr>
         </table>
          </td>
             </tr>
            </table> 
   <table width="100%">
  <tr>
      <td align="right"><input type="button" id="aceptarButton" value="Aceptar" onclick="agregarAsignacion()"/></td>   
      <td align="left"><input type="button" id="cancelButton" value="Cerrar" onClick="cancelarAsignacion()"/></td>    
  </tr>
</table>
 <c:out value="${script}" escapeXml="false"/>
  </form>
  </body>
</html>
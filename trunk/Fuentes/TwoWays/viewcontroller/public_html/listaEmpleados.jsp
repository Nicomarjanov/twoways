<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <link href="./twoways.css" rel="stylesheet" type="text/css"/>
    <script  type='text/javascript' src="./js/listaEmpleados.js"></script>
    <script type='text/javascript' src="./js/utils.js"></script>    
    <script type='text/javascript' src='/twoways/dwr/interface/towaysDWR.js'></script>
    <script type='text/javascript' src='/twoways/dwr/engine.js'></script>    
    <script type='text/javascript' src='/twoways/dwr/util.js'></script>
    <title>Lista de Empleados</title>
  </head>
  <body>
    <jsp:include page="/WEB-INF/jspIncludes/menu.jsp" />
    <c:out value="${mensaje}" escapeXml="false"/>
    <form name="frmlistEmpleado" action="listaEmpleados" method="POST">
    <input type="hidden"  name="accion"  id="accion"  value=""   />
    <input type="hidden"  name="pageId"  id="pageId" value="<c:out value="${pageId}" />" >
    <table width="100%">
      <thead>
      <tr>
        <th class="tw_form" colspan="100%" >Ingrese los criterios de busqueda para los empleados </th>
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
       <tr ><td colspan="100%" style="font-size:1.1em;padding-top:5px;padding-bottom:4px;background-color:#80211D;color:#ffffff;" >Filtros de Busqueda</td><td style="font-size:1.1em;padding-top:5px;padding-bottom:4px;background-color:#80211D;color:#ffffff;" ><div style="background-color:Gray;width:20;height:20" onclick="document.getElementById('table-filtros').style.display='none';document.getElementById('mostrar-filtro').style.display='block'" onmouseover="this.style.cursor='hand';" >X</div></td> </tr>
       </thead>
       <tbody>
       <tr>
        <td nowrap>Nombre</td><td colspan="2" ><input type="text" class="tw_form" name="empFirstName"  id="empFirstName" style="width:100%"  /></td>
       </tr>
       <tr>
        <td nowrap>Apellido</td><td colspan="2" ><input type="text" class="tw_form" name="empLastName"  id="empLastName" style="width:100%"  /></td>
       </tr>   
       <tr>
        <td >Especialidades</td> 
            <td colspan="2" nowrap>
               <table style="border-left-style:solid; border-left-width:1px; font-size:9px;">
                    <tr>
                        <td>Traductor</td><td><input type="checkbox" name="Traductor" value="Traductor"></input></td>
                        <td>Editor</td><td><input type="checkbox" name="Editor" value="Editor"></input></td>
                    </tr>
                    <tr>
                        <td>Revisor Final</td><td><input type="checkbox" name="Revisor" value="Revisor"></input></td>
                        <td>Maquetador</td><td><input type="checkbox" name="Maquetador" value="Maquetador"></input></td>
                    </tr>
                    <tr>
                        <td>PDTP</td><td><input type="checkbox" name="PDTP" value="PDTP"></input></td>
                        <td>Proofer</td><td><input type="checkbox" name="Proofer" value="Proofer"></input></td>
                    </tr>
               </table>    
            </td>
       </tr>
       <tr>
       <td nowrap >Proyecto</td>
       <td colspan="2" ><input type="text" class="tw_form" name="ProName"  id="ProName" style="width:100%"  /></td> 
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
            <tr><th>Nombre</th><th>Apellido</th><th>Especialidad</th><th>Estado asignación</th><th>Fecha asignación</th><th>Fin asignación</th><th>Proyecto</th><th>Estado proyecto</th><th>Fin proyecto</th></tr>
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
                <td nowrap ><c:out value="${empleado.projectAssignmentsTO.statesTO.staId}" /></td>
                <td nowrap ><fmt:formatDate value="${empleado.projectAssignmentsTO.praAssignDate}"    pattern="dd/MM/yyyy HH:mm" /></td>
                <td nowrap ><fmt:formatDate value="${empleado.projectAssignmentsTO.praFinishDate}"    pattern="dd/MM/yyyy HH:mm" /></td>
                <td nowrap ><c:out value="${empleado.projectAssignmentsTO.projectsTO.proName}" /></td>
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
           <tr bgcolor="<c:out value="${color_row}"/>" >
              <td colspan="11" align="center" >
               <table align="center">
                    <tr><td>
                        <c:if test="${page != 0}">
                            <img src="img/player_start.png" height="20" width="20" onclick="back()" alt="<"/>
                        </c:if>
                           </td><td>Página <fmt:formatNumber type="number" minFractionDigits="0" value="${page + 1 }" /> de <fmt:formatNumber type="number" minFractionDigits="0" value="${maxPage }" />
                           </td><td>
                           
                        <c:if test="${page < maxPage}"> 
                         <img src="img/player_next.png" height="20" width="20" onclick="next()" alt=">" />
                        </c:if> 
                     </td></tr>
               </table>
               </td>
          </tr>
           
           </tbody>
          </c:when>
          <c:otherwise>
           <c:if test="${not empty accion}">
           <tbody>
            <tr><td colspan="100%">La búsqueda no arrojo resultados</td></tr> 
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
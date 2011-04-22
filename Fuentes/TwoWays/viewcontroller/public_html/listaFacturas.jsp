<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <link href="./twoways.css" rel="stylesheet" type="text/css"/>
    <script  type='text/javascript' src="./js/listaFacturas.js"></script>
    <script type='text/javascript' src="./js/utils.js"></script>    
    <script type='text/javascript' src='/twoways/dwr/interface/towaysDWR.js'></script>
    <script type='text/javascript' src='/twoways/dwr/engine.js'></script>    
    <script type='text/javascript' src='/twoways/dwr/util.js'></script>
    <title>Lista de Facturas</title>
  </head>
  <body>
    <jsp:include page="/WEB-INF/jspIncludes/menu.jsp" />
    <c:out value="${mensaje}" escapeXml="false"/>
    <form id="frmlistFacturas" name="frmlistFacturas" action="listaFacturas" method="POST">
    <input type="hidden"  name="accion"  id="accion"  value=""   />
    <input type="hidden"  name="pageId"  id="pageId" value="<c:out value="${pageId}" />" >
    <table width="100%">
      <thead>
      <tr>
        <th class="tw_form" colspan="100%" >Ingrese los criterios de busqueda de cobros realizados</th>
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
        <td nowrap align="right" width="50%">Número:</td>
        <td align="left" width="50%"><input type="text" maxlength="10" size="10" name="invNumber" value="" class="tw_form" /></td>
       </tr>
       <tr>
       <td nowrap align="right" width="50%">Cliente:</td>
       <td align="left" width="50%">
            <select name="listaClientes" id="listaClientes" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';">            
                <option value="" selected="selected">Seleccionar</option>
                <c:forEach items="${listaClientes}" var="item">
                   <c:choose>
                    <c:when test="${auxCliId == item.cliId}">
                       <option value="<c:out value="${item.cliId}" />#<c:out value="${item.cliName}" />" selected="selected">
                        <c:out value="${item.cliName}" />
                      </option> 
                    </c:when>
                    <c:otherwise>
                    <option value="<c:out value="${item.cliId}" />#<c:out value="${item.cliName}" />" style="background-color:#A4BAC7;">
                        <c:out value="${item.cliName}" /> 
                    </option>
                    </c:otherwise>
                    </c:choose>
                </c:forEach>
           </select>
        </td>
       </tr>
       <tr>
       <td owrap align="right" width="50%">Nombre Orden:</td>
        <td align="left" width="50%"><input type="text" maxlength="10" size="10" class="tw_form" name="ordName"  id="ordName" style="width:100%"  /></td> 
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
               <tr style="display:block; background-color='transparent';" align="center">
                    <th width="10%" bgcolor="#80211D">Cliente</th>
                    <th width="10%" bgcolor="#80211D">Nombre orden</th>                       
                    <th width="10%" bgcolor="#80211D">Fecha cobro</th>
                    <th width="10%" bgcolor="#80211D">PO #</th>
                    <th width="10%" bgcolor="#80211D">JOB #</th>                    
                    <th width="10%" bgcolor="#80211D">WO #</th>
                    <th width="10%" bgcolor="#80211D">JOB Name</th> 
                    <th width="10%" bgcolor="#80211D">Cuenta</th>
                    <th width="10%" bgcolor="#80211D">Moneda</th>                    
                    <th width="10%" bgcolor="#80211D">Total</th>
                    <th width="10%" bgcolor="#80211D">¿Facturado?</th>
                    <th width="2%" bgcolor="#80211D"></th>
                </tr>
            </thead>
          <c:choose   >
          <c:when test="${not empty listaFacturas}">
           <tbody>
            <c:set scope="page" var="color_row" value="${'#E8B6B5'}" />
            
            <c:forEach items="${listaFacturas}" var="factura" varStatus="status" >     
           
            <tr bgcolor="<c:out value="${color_row}"/>" >
                <td nowrap ><a href="cliente?cliId=<c:out value="${factura.cliId}" />"><c:out value="${factura.cliName}" /></a></td>
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
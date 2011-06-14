<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ page errorPage="error.jsp" %>
<%@ page isELIgnored="true" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

<html>
   <head>
   <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <link href="./twoways.css" rel="stylesheet" type="text/css"/>
    <title>Buscar Proyecto</title>
    <script type='text/javascript' src="./js/gestionarProyecto.js"></script>
    <script type='text/javascript' src="./js/utils.js"></script>
    <script type='text/javascript' src='/twoways/dwr/interface/towaysDWR.js'></script>
    <script type='text/javascript' src='/twoways/dwr/engine.js'></script>
    <script type='text/javascript' src='/twoways/dwr/util.js'></script>
    <script type='text/javascript' src="./js/commons.js"></script>  
    <script type='text/javascript' src="./js/CalendarPopup.js"></script>
    <script>
        writeSource('jscallDesde');
        writeSource('jscallHasta');
        document.onkeydown = checkKeycode
        function checkKeycode(e) {
            var keycode;
            if (window.event) keycode = window.event.keyCode;
             else if (e) keycode = e.which;
            if( keycode == 13){
              buscarProyectos();
            }
         }
        
        
    </script>
    
    
  </head>
  <body>
   <jsp:include page="/WEB-INF/jspIncludes/menu.jsp" />
   <form  id="frmGestionarProyecto" name="frmGestionarProyecto" action="gestionarProyecto" method="POST" >
   <input type="hidden"  name="accion"  id="accion"  value=""   />
   <input type="hidden"  name="pageId"  id="pageId" value="<c:out value="${pageId}" />" >
   <table width="100%">
  <thead>
  <tr>
    <th class="tw_form" colspan="100%" >Ingrese los criterios de búsqueda de proyectos</th>
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
   <tr ><td colspan="100%" style="font-size:1.1em;padding-top:5px;padding-bottom:4px;background-color:#80211D;color:#ffffff;" >Filtros de búsqueda</td><td style="font-size:1.1em;padding-top:5px;padding-bottom:4px;background-color:#80211D;color:#ffffff;" ><div style="background-color:Gray;width:20;height:20" onclick="document.getElementById('table-filtros').style.display='none';document.getElementById('mostrar-filtro').style.display='block'" onmouseover="this.style.cursor='hand';" >X</div></td> </tr>
   </thead>
   <tbody>
   <tr>
    <td nowrap>Nombre proyecto</td>
    <td colspan="2" ><input type="text" class="tw_form" name="projName"  id="projName" value="<c:out value="${project.proName}" />" onkeydown="keyTarifa()" style="width:100%"  /></td>
   </tr>
   <tr>
    <td nowrap>Nombre orden</td>
    <td colspan="2" ><input type="text" class="tw_form" name="ordName"  id="ordName" value="<c:out value="${project.ordersTO.ordName}" />" onkeydown="keyTarifa()" style="width:100%"  /></td>
   </tr>
   <tr>
   <td nowrap >Estado proyecto</td>
        <td colspan="2" nowrap>
           <table style="border-left-style:solid; border-left-width:1px; font-size:9px;">
                <tr>
                    <td>Iniciado</td>
                    <td>
                        <c:choose>
                            <c:when test="${not empty Iniciado}">
                                <input type="checkbox" name="Iniciado" value="Iniciado" checked></input>
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" name="Iniciado" value="Iniciado"></input>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <td>Entregado</td>
                    <td>
                        <c:choose>
                            <c:when test="${not empty Entregado}">
                                <input type="checkbox" name="Entregado" value="Entregado" checked></input>
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" name="Entregado" value="Entregado"></input>
                            </c:otherwise>
                        </c:choose>                        
                    </td>
                </tr>
                <tr>
                    <td>PO Enviado</td>
                    <td>
                         <c:choose>
                            <c:when test="${not empty POEnviado}">
                                 <input type="checkbox" name="POEnviado" value="PO Enviado" checked></input>
                            </c:when>
                            <c:otherwise>
                                <input type="checkbox" name="POEnviado" value="PO Enviado"></input>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
           </table>
        </td>
   <tr>
   <tr>
    <td >Fecha de Inicio</td> 
        <td colspan="2" nowrap>
           <select id="projDateOpt" name="projDateOpt" >
               <c:forEach items="${optionList}" var="item">
                   <c:choose>
                    <c:when test="${projDateOpt == item}">
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
            <input type="text" class="tw_form" name="projDate"  id="projDate" value="<c:out value="${projDate}" />" style="width:200"  onkeydown="keyTarifa()"/><div id="divDesde" style="background:#FFFFFF;position:absolute"  ></div> <img  onclick="cal1Desde.select(document.forms[0].projDate,'selDesde','dd/MM/yyyy'); return false;" NAME="selDesde" ID="selDesde"  height="20" width="20" alt="seleccion" src="img/cal.png" onmouseover="this.style.cursor='hand';"></img>
        </td>
   </tr>
   <tr>
       <td nowrap>Fecha de Fin</td>
       <td colspan="2" nowrap>
          <select id="projFinishDateOpt" name="projFinishDateOpt" >                   
                <c:forEach items="${optionList}" var="item">
                  <c:choose>
                    <c:when test="${projFinishDateOpt == item}">
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
           <input type="text" class="tw_form" name="projFinishDate" id="projFinishDate" value="<c:out value="${projFinishDate}" />" style="width:200"  onkeydown="keyTarifa()"/><div id="divHasta" style="background:#FFFFFF;position:absolute"  ></div> <img  onclick="cal1Hasta.select(document.forms[0].projFinishDate,'selHasta','dd/MM/yyyy'); return false;" NAME="selHasta" ID="selHasta"  height="20" width="20" alt="seleccion" src="img/cal.png" onmouseover="this.style.cursor='hand';"></img>
       </td>       
   </tr>
   <tr>
   <td nowrap >Cliente</td>
   <td colspan="2" >
        <select name="listaClientes" id="listaClientes" style="border:solid 1px #005C8D;width:100%"   onfocus="javascript:this.style.background='#FFFFFF';">               
            <option value=""  >Seleccionar</option>
            <c:forEach items="${listaCliente}" var="item">
               <c:out value="${item.cliId}" />
               <c:choose>
                    <c:when test="${project.ordersTO.clientsTO.cliId == item.cliId}">
                       <option value="<c:out value="${item.cliId}" />" style="background-color:#A4BAC7;" selected="selected">
                           <c:out value="${item.cliName}" />
                       </option> 
                    </c:when>
                    <c:otherwise>
                        <option value="<c:out value="${item.cliId}" />" style="background-color:#A4BAC7;">
                            <c:out value="${item.cliName}" />
                        </option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
    </td>
   </tr>
   <tr  >
       <td colspan="100%"  valign="top" align="right" ><input type="button" value="Buscar" onclick="buscarProyectos()"  /></td>
   </tr>   
   </tbody>
   </table>
   </td></tr></table>
   </div>
   </td><td align="left" width="100%" valign="top" >
   <table id ="tabla-busqueda" width="100%" >
    <thead>
      <tr>
        <th nowrap>Nombre</th>
        <th nowrap>Fecha de inicio</th>
        <th nowrap>Fecha de fin</th>
        <th nowrap>Orden</th>
        <th nowrap>Inicio orden</th>
        <th nowrap>Entrega orden</th>        
        <th nowrap>Cliente</th>
        <th nowrap>Estado</th>        
        <th nowrap>Usuario</th>
        <th nowrap></th>             
        <th nowrap></th>   
      </tr> 
    </thead>
      <c:choose   >
      <c:when test="${not empty listaProyectos}">
       <tbody>
        <c:set scope="page" var="color_row" value="${'#E8B6B5'}" />
        
        <c:forEach items="${listaProyectos}" var="project" varStatus="status" >  
            <c:choose>
                <c:when test="${project.statesTO.staId == 'Entregado'}">  
                  <c:set scope="page" var="color_row" value="${'#f60101'}" />
                </c:when>
                <c:when test="${project.statesTO.staId == 'Iniciado'}">  
                  <c:set scope="page" var="color_row" value="${'#4fb84f'}" />
                </c:when>         
                <c:when test="${project.statesTO.staId == 'PO Enviado'}">  
                  <c:set scope="page" var="color_row" value="${'#8f9c9f'}" />
                </c:when>        
                <c:otherwise>
                  <c:set scope="page" var="color_row" value="${'#ffffff'}" /> 
                </c:otherwise>
            </c:choose>
            <tr bgcolor="<c:out value="${color_row}"/>" >
                <td nowrap ><c:out value="${project.proName}" /></td>
                <td nowrap ><fmt:formatDate value="${project.proStartDate}" pattern="dd/MM/yyyy HH:mm" /></td>
                <td nowrap ><fmt:formatDate value="${project.proFinishDate}" pattern="dd/MM/yyyy HH:mm" /></td>
                <td nowrap ><c:out value="${project.ordersTO.ordName}" /></td>
                <td nowrap ><fmt:formatDate value="${project.ordersTO.ordStartDate}" pattern="dd/MM/yyyy HH:mm" /></td>
                <td nowrap ><fmt:formatDate value="${project.ordersTO.ordFinishDate}" pattern="dd/MM/yyyy HH:mm" /></td>
                <td nowrap ><c:out value="${project.ordersTO.clientsTO.cliName}" /></td>
                <td nowrap ><c:out value="${project.statesTO.staId}" /></td>
                <td nowrap ><c:out value="${project.usersUsrId}" /></td>
                <td nowrap align="center"><img src="img/edit.png" width=15 height=15 onmouseover="this.style.cursor='hand';" onclick="editarOrden('<c:out value="${project.ordersTO.ordId}" />')"  alt="Editar" ></td>
                <td align="center" nowrap ><img src="img/go.png" width=15 height=15 onmouseover="this.style.cursor='hand';" onclick="editarProyecto('<c:out value="${project.ordersTO.ordId}" />')" alt="Proyecto" ></td>
            </tr> 

        
       </c:forEach>
       <tr bgcolor="#FCEEED" >
          <td colspan="12" align="center" >
           <table align="center">
                <tr><td>
                    <c:if test="${page != 0}">
                        <img src="img/player_start.png" height="20" width="20" onclick="back()" alt="<"/>
                    </c:if>
                       </td><td>Pagina <fmt:formatNumber type="number" minFractionDigits="0" value="${page + 1 }" /> de <fmt:formatNumber type="number" minFractionDigits="0" value="${maxPage + 1 }" />
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
        <tr><td colspan="100%">La busqueda no arrojo resultados</td></tr> 
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
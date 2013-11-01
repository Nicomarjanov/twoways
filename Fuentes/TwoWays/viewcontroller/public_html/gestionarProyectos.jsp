<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ page errorPage="error.jsp"%>
<%@ page isELIgnored="true"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
  <link href="./twoways.css" rel="stylesheet" type="text/css"/>
  <link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico"> 
  <link rel="icon" type="image/x-icon" href="img/favicon.ico">   
  
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
 <body><jsp:include page="/WEB-INF/jspIncludes/menu.jsp"/><form id="frmGestionarProyecto"
                                                                name="frmGestionarProyecto"
                                                                action="gestionarProyecto"
                                                                method="POST">
   <input type="hidden" name="accion" id="accion" value=""/>
   <input type="hidden" name="pageId" id="pageId"
          value='<c:out value="${pageId}" />'></input>
   <input type="hidden" name="maxPage" id="maxPage"
          value='<c:out value="${maxPage + 1 }" />'></input>          
   <table width="100%">
    <thead>
     <tr>
      <th class="tw_form" colspan="100%">Ingrese los criterios de
                                         b&uacute;squeda de proyectos</th>
     </tr>
    </thead>
    <tbody>
     <tr>
      <td colspan="100%">&nbsp;</td>
     </tr>
     <tr>
      <td id="mostrar-filtro" style="display:none" valign="top">
       <img src="img/filter.png" width="20" height="20" alt="Filtros"
            onclick="document.getElementById('table-filtros').style.display='block';document.getElementById('mostrar-filtro').style.display='none'"
            onmouseover="this.style.cursor='hand';"></img>
      </td>
      <td id="table-filtros" valign="top" align="left">
       <div style="border: 1px solid;border-color:#FFFFFf;padding:2;spacing:2">
        <table>
         <tr>
          <td>
           <table width="200px" cellpadding="0" cellspacing="0">
            <thead>
             <tr>
              <td colspan="100%"
                  style="font-size:1.1em;padding-top:5px;padding-bottom:4px;background-color:#80211D;color:#ffffff;">Filtros
                                                                                                                     de
                                                                                                                     b&uacute;squeda</td>
              <td style="font-size:1.1em;padding-top:5px;padding-bottom:4px;background-color:#80211D;color:#ffffff;">
               <div style="background-color:Gray;width:20;height:20"
                    onclick="document.getElementById('table-filtros').style.display='none';document.getElementById('mostrar-filtro').style.display='block'"
                    onmouseover="this.style.cursor='hand';">X</div>
              </td>
             </tr>
            </thead>
            <tbody>
             <tr>
              <td nowrap="nowrap">Nombre proyecto</td>
              <td colspan="2">
               <input type="text" class="tw_form" name="projName" id="projName"
                      value='<c:out value="${project.proName}" />'
                      onkeydown="checkKeycode()" style="width:100%"/>
              </td>
             </tr>
             <tr>
              <td nowrap="nowrap">Nombre orden</td>
              <td colspan="2">
               <input type="text" class="tw_form" name="ordName" id="ordName"
                      value='<c:out value="${project.ordersTO.ordName}" />'
                      onkeydown="checkKeycode()" style="width:100%"/>
              </td>
             </tr>
             <tr>
              <td nowrap="nowrap">Estado proyecto</td>
              <td colspan="2" nowrap="nowrap">
               <table style="border-left-style:solid; border-left-width:1px; font-size:9px;">
                <tr>
                 <td>Iniciado</td>
                 <td>
                  <c:choose>
                   <c:when test="${not empty Iniciado}">
                    <input type="checkbox" name="Iniciado" value="Iniciado" id="Iniciado"
                           checked="checked"></input>
                   </c:when>
                   <c:otherwise>
                    <input type="checkbox" name="Iniciado" value="Iniciado" id="Iniciado"></input>
                   </c:otherwise>
                  </c:choose>
                 </td>
                </tr>
                <tr>
                 <td>Entregado</td>
                 <td>
                  <c:choose>
                   <c:when test="${not empty Entregado}">
                    <input type="checkbox" name="Entregado" value="Entregado" id="Entregado"
                           checked="checked"></input>
                   </c:when>
                   <c:otherwise>
                    <input type="checkbox" name="Entregado" value="Entregado" id="Entregado"></input>
                   </c:otherwise>
                  </c:choose>
                 </td>
                </tr>
                <tr>
                 <td>PO Enviado</td>
                 <td>
                  <c:choose>
                   <c:when test="${not empty POEnviado}">
                    <input type="checkbox" name="POEnviado" value="PO Enviado" id="POEnviado"
                           checked="checked"></input>
                   </c:when>
                   <c:otherwise>
                    <input type="checkbox" name="POEnviado" value="PO Enviado" id="POEnviado"></input>
                   </c:otherwise>
                  </c:choose>
                 </td>
                </tr>
               </table>
              </td>
             </tr>
             <tr>
              <td>Fecha de inicio</td>
              <td colspan="2" nowrap="nowrap">
               <select id="projDateOpt" name="projDateOpt">
                <c:forEach items="${optionList}" var="item">
                 <c:choose>
                  <c:when test="${projDateOpt == item}">
                   <option value='<c:out value="${item}" />'
                           style="background-color:#A4BAC7;"
                           selected="selected">
                    <c:out value="${item}"/>
                   </option>
                  </c:when>
                  <c:otherwise>
                   <option value='<c:out value="${item}" />'
                           style="background-color:#A4BAC7;">
                    <c:out value="${item}"/>
                   </option>
                  </c:otherwise>
                 </c:choose>
                </c:forEach>
               </select>
               <input type="text" class="tw_form" name="projDate" id="projDate"
                      value='<c:out value="${projDate}" />' style="width:200"
                      onkeydown="checkKeycode()"/>
               <div id="divDesde" style="background:#FFFFFF;position:absolute"></div>
               <img onclick="cal1Desde.select(document.forms[0].projDate,'selDesde','dd/MM/yyyy'); return false;"
                    name="selDesde" id="selDesde" height="20" width="20"
                    alt="seleccion" src="img/cal.png"
                    onmouseover="this.style.cursor='hand';"></img>
              </td>
             </tr>
             <tr>
              <td nowrap="nowrap">Fecha de fin</td>
              <td colspan="2" nowrap="nowrap">
               <select id="projFinishDateOpt" name="projFinishDateOpt">
                <c:forEach items="${optionList}" var="item">
                 <c:choose>
                  <c:when test="${projFinishDateOpt == item}">
                   <option value='<c:out value="${item}" />'
                           style="background-color:#A4BAC7;"
                           selected="selected">
                    <c:out value="${item}"/>
                   </option>
                  </c:when>
                  <c:otherwise>
                   <option value='<c:out value="${item}" />'
                           style="background-color:#A4BAC7;">
                    <c:out value="${item}"/>
                   </option>
                  </c:otherwise>
                 </c:choose>
                </c:forEach>
               </select>
               <input type="text" class="tw_form" name="projFinishDate"
                      id="projFinishDate"
                      value='<c:out value="${projFinishDate}" />'
                      style="width:200" onkeydown="checkKeycode()"/>
               <div id="divHasta" style="background:#FFFFFF;position:absolute"></div>
               <img onclick="cal1Hasta.select(document.forms[0].projFinishDate,'selHasta','dd/MM/yyyy'); return false;"
                    name="selHasta" id="selHasta" height="20" width="20"
                    alt="seleccion" src="img/cal.png"
                    onmouseover="this.style.cursor='hand';"></img>
              </td>
             </tr>
             <tr>
              <td nowrap="nowrap">Cliente</td>
              <td colspan="2">
               <select name="listaClientes" id="listaClientes"
                       style="border:solid 1px #005C8D;width:100%"
                       onfocus="javascript:this.style.background='#FFFFFF';">
                <option value="">Seleccionar</option>
                <c:forEach items="${listaCliente}" var="item">
                 <c:out value="${item.cliId}"/>
                 <c:choose>
                  <c:when test="${project.ordersTO.clientsTO.cliId == item.cliId}">
                   <option value='<c:out value="${item.cliId}" />'
                           style="background-color:#A4BAC7;"
                           selected="selected">
                    <c:out value="${item.cliName}"/>
                   </option>
                  </c:when>
                  <c:otherwise>
                   <option value='<c:out value="${item.cliId}" />'
                           style="background-color:#A4BAC7;">
                    <c:out value="${item.cliName}"/>
                   </option>
                  </c:otherwise>
                 </c:choose>
                </c:forEach>
               </select>
              </td>
             </tr>
             <tr>
              <td colspan="100%" valign="top" align="right">
               <input type="button" value="Buscar" onclick="buscarProyectos()"/>
              </td>
             </tr>
            </tbody>
           </table>
          </td>
         </tr>
        </table>
       </div>
      </td>
      <td align="left" width="100%" valign="top">
       <table id="tabla-busqueda" width="100%">
        <thead>
         <tr>
          <th nowrap="nowrap">Nombre</th>
          <th nowrap="nowrap">Fecha de inicio</th>
          <th nowrap="nowrap">Fecha de fin</th>
          <th nowrap="nowrap">Orden</th>
          <th nowrap="nowrap">Inicio orden</th>
          <th nowrap="nowrap">Entrega orden</th>
          <th nowrap="nowrap">Cliente</th>
          <th nowrap="nowrap">Estado</th>
          <th nowrap="nowrap">Usuario</th>
          <th nowrap="nowrap">
           &nbsp;
          </th>
          <th nowrap="nowrap">
           &nbsp;
          </th>
         </tr>
        </thead>
        <c:choose>
         <c:when test="${not empty listaProyectos}">
          <tbody>
           <c:set scope="page" var="color_row" value="${\'#E8B6B5\'}"/>
           <c:forEach items="${listaProyectos}" var="project"
                      varStatus="status">
            <c:choose>
             <c:when test="${project.statesTO.staId == \'Entregado\'}">
              <c:set scope="page" var="color_row" value="${\'#d24444\'}"/>
             </c:when>
             <c:when test="${project.statesTO.staId == \'Iniciado\'}">
              <c:set scope="page" var="color_row" value="${\'#1cb874\'}"/>
             </c:when>
             <c:when test="${project.statesTO.staId == \'PO Enviado\'}">
              <c:set scope="page" var="color_row" value="${\'#8f9c9f\'}"/>
             </c:when>
             <c:otherwise>
              <c:set scope="page" var="color_row" value="${\'#ffffff\'}"/>
             </c:otherwise>
            </c:choose>
            <tr bgcolor='<c:out value="${color_row}"/>'>
             <td nowrap="nowrap">
              <c:out value="${project.proName}"/>
             </td>
             <td nowrap="nowrap">
              <fmt:formatDate value="${project.proStartDate}"
                              pattern="dd/MM/yyyy HH:mm"/>
             </td>
             <td nowrap="nowrap">
              <fmt:formatDate value="${project.proFinishDate}"
                              pattern="dd/MM/yyyy HH:mm"/>
             </td>
             <td nowrap="nowrap">
              <c:out value="${project.ordersTO.ordName}"/>
             </td>
             <td nowrap="nowrap">
              <fmt:formatDate value="${project.ordersTO.ordStartDate}"
                              pattern="dd/MM/yyyy HH:mm"/>
             </td>
             <td nowrap="nowrap">
              <fmt:formatDate value="${project.ordersTO.ordFinishDate}"
                              pattern="dd/MM/yyyy HH:mm"/>
             </td>
             <td nowrap="nowrap">
              <c:out value="${project.ordersTO.clientsTO.cliName}"/>
             </td>
             <td nowrap="nowrap">
              <c:out value="${project.statesTO.staId}"/>
             </td>
             <td nowrap="nowrap">
              <c:out value="${project.usersUsrId}"/>
             </td>
             <td nowrap="nowrap" align="center">
              <img src="img/edit.png" width="15" height="15"
                   onmouseover="this.style.cursor='hand';"
                   onclick="editarOrden('<c:out value="${project.ordersTO.ordId}" />')"
                   alt="Editar"></img>
             </td>
             <td align="center" nowrap="nowrap">
              <img src="img/go.png" width="15" height="15"
                   onmouseover="this.style.cursor='hand';"
                   onclick="editarProyecto('<c:out value="${project.ordersTO.ordId}" />')"
                   alt="Proyecto"></img>
             </td>
            </tr>
           </c:forEach>
           <tr bgcolor="#FCEEED">
            <td colspan="12" align="center">
             <table align="center">
              <tr>
               <td>
                <c:if test="${page != 0}">
                 <img src="img/player_start.png" height="20" width="20"
                      onclick="back()" alt="<"/>
                </c:if>
               </td>
               <td>
                Página 
                <fmt:formatNumber type="number" minFractionDigits="0"
                                  value="${page + 1 }"/>
                de
                <fmt:formatNumber type="number" minFractionDigits="0"
                                  value="${maxPage + 1 }"/>
               </td>
               <td>
                <c:if test="${page < maxPage}">
                 <img src="img/player_next.png" height="20" width="20"
                      onclick="next()" alt=">"/>
                </c:if>
               </td>
              </tr>
            </table>
            <table align="center">
              <tr>
               <td>Ir a la página:</td>
               <td>
               <input type="text" class="tw_form" name="pageIr" id="pageIr"
                       value='<c:out value="${pageId + 1}" />' size="3"
                       maxlength="4"
                       onfocus="javascript:this.style.background='#FFFFFF';"/>
               </td>
               <td>
               
                <img src="img/player_fwd.png" height="20" width="20"
                     onclick="IrHasta()" alt=">>"/>

               </td>
              </tr>
              <!--tr>
                <td align="center" colspan="3"><input type="button" id="export" value="Exportar" OnClick="exportarListaCSV()"/></td>
              </tr-->
             </table>
            </td>
           </tr>
          </tbody>
         </c:when>
         <c:otherwise>
          <c:if test="${not empty accion}">
           <tbody>
            <tr>
             <td colspan="100%">La busqueda no arrojo resultados</td>
            </tr>
           </tbody>
          </c:if>
         </c:otherwise>
        </c:choose>
       </table>
      </td>
     </tr>
    </tbody>
   </table>
  </form></body>
</html>
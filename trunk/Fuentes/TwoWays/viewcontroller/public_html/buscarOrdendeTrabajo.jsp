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
    <title>Buscar Orden de Trabajo</title>
    <script type='text/javascript' src="./js/ordentrabajo.js"></script>
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
               buscarOrden();
            }
         }
        
        
    </script>
    
    
  </head>
  <body>
   <jsp:include page="/WEB-INF/jspIncludes/menu.jsp" />
   <form  id="frmbuscarOrden" name="frmbuscarOrden" action="buscarordentrabajo" method="POST" >
   <input type="hidden"  name="accion"  id="accion"  value=""   />
   <input type="hidden"  name="pageId"  id="pageId" value="<c:out value="${pageId}" />" >
   <table width="100%">
  <thead>
  <tr>
    <th class="tw_form" colspan="100%" >Ingrese los criterios de búsqueda para la orden </th>
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
    <td nowrap>Nombre</td><td colspan="2" ><input type="text" class="tw_form" name="ordName"  id="ordName" value="<c:out value="${order.ordName}" />" style="width:100%"  /></td>
   </tr>
   <tr>
    <td >Fecha de Inicio</td> 
        <td colspan="2" nowrap>
           <select id="ordDateOpt" name="ordDateOpt" >
               <c:forEach items="${optionList}" var="item">
                   <c:choose>
                    <c:when test="${ordDateOpt == item}">
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
            <input type="text" class="tw_form" name="ordStartDate"  id="ordStartDate" value="<c:out value="${ordStartDate}" />" style="width:200"  /><div id="divDesde" style="background:#FFFFFF;position:absolute"  ></div> <img  onclick="cal1Desde.select(document.forms[0].ordStartDate,'selDesde','dd/MM/yyyy'); return false;" NAME="selDesde" ID="selDesde"  height="20" width="20" alt="seleccion" src="img/cal.png" onmouseover="this.style.cursor='hand';"></img>
        </td>
   </tr>
   <tr>
   <td nowrap >Fecha de Entrega </td><td  colspan="2"  nowrap>
      <select id="ordFinishDateOpt" name="ordFinishDateOpt" >
                
                <c:forEach items="${optionList}" var="item">
                  <c:choose>
                    <c:when test="${ordFinishDateOpt == item}">
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
   <input type="text" class="tw_form" name="ordFinishDate"  id="ordFinishDate" value="<c:out value="${ordFinishDate}" />" style="width:200"  /><div id="divHasta" style="background:#FFFFFF;position:absolute"  ></div> <img  onclick="cal1Hasta.select(document.forms[0].ordFinishDate,'selHasta','dd/MM/yyyy'); return false;" NAME="selHasta" ID="selHasta"  height="20" width="20" alt="seleccion" src="img/cal.png" onmouseover="this.style.cursor='hand';"></img>
   </td>
   
   </tr>
   <tr>
   <td nowrap >Cliente</td>
   <td colspan="2" ><select name="listaClientes" id="listaClientes" style="border:solid 1px #005C8D;width:100%"   onfocus="javascript:this.style.background='#FFFFFF';">               
                <option value=""  >Seleccionar</option>
                <c:forEach items="${listaCliente}" var="item">
                   <c:out value="${item.cliId}" />
                   <c:choose>
                    <c:when test="${order.clientsTO.cliId == item.cliId}">
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
   <tr>
   <td nowrap >Proyecto</td>
   <td colspan="2" ><input type="text" class="tw_form" name="ordProjId"  id="ordProjId"  value="<c:out value="${order.ordProjId}" />" style="width:100%"  /></td> 
   </tr>
   <tr  >
   <td colspan="100%"  valign="top" align="right" ><input type="button" value="Buscar" onclick="buscarOrden()"  /></td>
   </tr>
   
   </tbody>
   </table>
   </td></tr></table>
   </div>
   </td><td align="left" width="100%" valign="top" >
   <table id ="tabla-busqueda" width="100%" >
    <thead>
      <tr>
        <th nowrap >Nombre</th>
        <th nowrap >Fecha de Inicio</th>
        <th nowrap >Fecha de Entrega</th>
        <th nowrap >Cliente</th>
        <th nowrap >Proyecto</th>
        <th nowrap >Editar</th>
        <th nowrap >Ir al Proyecto</th>
      </tr> 
    </thead>
      <c:choose   >
      <c:when test="${not empty listaOrden}">
       <tbody>
        <c:set scope="page" var="color_row" value="${'#E8B6B5'}" />
        
        <c:forEach items="${listaOrden}" var="order" varStatus="status" >          
            <tr bgcolor="<c:out value="${color_row}"/>" >
                <td nowrap ><c:out value="${order.ordName}" /></td>
                <td nowrap ><fmt:formatDate value="${order.ordStartDate}"    pattern="dd/MM/yyyy HH:mm" /></td>
                <td nowrap ><fmt:formatDate value="${order.ordFinishDate}"    pattern="dd/MM/yyyy HH:mm" /></td>
                <td nowrap ><c:out value="${order.clientsTO.cliName}" /></td>
                <td nowrap ><c:out value="${order.ordProjId}" /></td>
                <td nowrap align="center"><img src="img/edit.png" width=15 height=15 onmouseover="this.style.cursor='hand';" onclick="editarOrden('<c:out value="${order.ordId}" />')"  alt="Editar" ></td>
                <td nowrap align="center"><img src="img/go.png" width=15 height=15 onmouseover="this.style.cursor='hand';" onclick="editarProyecto('<c:out value="${order.ordId}" />')" alt="Proyecto" ></td>
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
          <td colspan="7" align="center" >
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
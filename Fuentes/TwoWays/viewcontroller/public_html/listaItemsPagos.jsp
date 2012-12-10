<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <link href="./twoways.css" rel="stylesheet" type="text/css"/>
    <script  type='text/javascript' src="./js/listaPagos.js"></script>
    <script type='text/javascript' src="./js/utils.js"></script>    
    <script type='text/javascript' src='/twoways/dwr/interface/towaysDWR.js'></script>
    <script type='text/javascript' src='/twoways/dwr/engine.js'></script>    
    <script type='text/javascript' src='/twoways/dwr/util.js'></script>

    <title>Lista de Items de Pago</title>
  </head>
  <body>
    <c:out value="${mensaje}" escapeXml="false"/>
    <form id="frmlistItemsPagos" name="frmlistItemsPagos" action="listaItemsPagos" method="POST">
    <input type="hidden"  name="accion"  id="accion"  value=""   />
    <input type="hidden"  name="pageId"  id="pageId" value="<c:out value="${pageId}" />" >
    <input type="hidden"  name="payId"  id="payId" value="<c:out value="${payId}" />" >    
    <table id ="tabla-busqueda">
    <thead>
       <!--<tr style="display:block; background-color='transparent';" align="center">                       -->
       <tr style="background-color='transparent'" align="center">
            <th width="10%" bgcolor="#80211D">Fecha asignación</th>
            <th width="10%" bgcolor="#80211D">Nombre proyecto</th> 
            <th width="10%" bgcolor="#80211D">Tipo tarifa</th>
            <th width="10%" bgcolor="#80211D">Monto tarifa</th>
            <th width="10%" bgcolor="#80211D">Total unidades</th>
            <th width="10%" bgcolor="#80211D">Total asignación</th>           
        </tr>
    </thead>
    <c:choose   >
    <c:when test="${not empty listaItemsPagos}">
    <tbody>
    <c:set scope="page" var="color_row" value="${'#E8B6B5'}" />
    
    <c:forEach items="${listaItemsPagos}" var="item" varStatus="status" >     
    
    <tr bgcolor="<c:out value="${color_row}"/>" >
        <td nowrap ><fmt:formatDate value="${item[\'ASSIGNDATE\']}"    pattern="dd/MM/yyyy" /></td>
        <td nowrap ><c:out value="${item[\'PRONAME\']}" /></td>
        <td nowrap ><c:out value="${item[\'RATNAME\']}" /></td>
        <td nowrap ><c:out value="${item[\'CURSYMBOL\']}" /> <c:out value="${item[\'RATE\']}" /></td>
        <td nowrap ><c:out value="${item[\'WCOUNT\']}" /></td>
        <td nowrap ><c:out value="${item[\'TOTAL\']}"/></td>                
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
      <td colspan="6" align="center" >
       <table align="center">
            <tr>
                <td>
                <c:if test="${page != 0}">
                    <img src="img/player_start.png" height="20" width="20" onclick="backItems()" alt="<"/>
                </c:if>
                   </td><td>Página <fmt:formatNumber type="number" minFractionDigits="0" value="${page + 1 }" /> de <fmt:formatNumber type="number" minFractionDigits="0" value="${maxPage}" />
                   </td><td>
                   
                <c:if test="${(page +1)< maxPage}"> 
                 <img src="img/player_next.png" height="20" width="20" onclick="nextItems()" alt=">" />
                </c:if> 
                </td>
             </tr>
       </table>
       </td>
    </tr>    
    </tbody>
    </c:when>
    <c:otherwise>
       <c:choose>
        <c:when test="${not empty accion}">
            <tbody>
                <tr><td colspan="100%">La búsqueda no arrojo resultados</td></tr> 
            </tbody>
         </c:when>
         <c:otherwise>
            <tbody>
                <tr><td colspan="100%">Seleccione un cliente para buscar las ordenes finalizadas a facturar</td></tr> 
            </tbody>
         </c:otherwise>
       </c:choose>  
    </c:otherwise>
    </c:choose>
    </table>
   
   </form>
   
  </body>
</html>
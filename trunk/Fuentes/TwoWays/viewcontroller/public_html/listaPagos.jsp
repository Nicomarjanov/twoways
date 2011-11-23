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
    <script type='text/javascript' src="./js/commons.js"></script>  
    <script type='text/javascript' src="./js/CalendarPopup.js"></script>  
    <script>
        writeSource('jscallDesde');
    
        document.onkeydown = checkKeycode
        function checkKeycode(e) {
            var keycode;
            if (window.event) keycode = window.event.keyCode;
             else if (e) keycode = e.which;
            if( keycode == 13){
              buscarPagos();
            }
         }        
    </script>
    <title>Lista de Pagos</title>
  </head>
  <body>
    <jsp:include page="/WEB-INF/jspIncludes/menu.jsp" />
    <c:out value="${mensaje}" escapeXml="false"/>
    <form id="frmlistPagos" name="frmlistPagos" action="listaPagos" method="POST">
    <input type="hidden"  name="accion"  id="accion"  value=""   />
    <input type="hidden"  name="empId"  id="empId"  value=""   />  
    <input type="hidden"  name="payId"  id="payId"  value=""   />  
    <input type="hidden"  name="mesId"  id="mesId"  value=""   /> 
    <input type="hidden"  name="anioId"  id="anioId"  value=""   /> 
    <input type="hidden"  name="payDate"  id="payDate"  value=""   />
    <input type="hidden"  name="payAmount"  id="payAmount"  value=""   />  
    <input type="hidden"  name="accId"  id="accId"  value=""   />
    <input type="hidden"  name="curSymbol"  id="curSymbol"  value=""   />    
    <input type="hidden"  name="pageId"  id="pageId" value="<c:out value="${pageId}" />" >
    <table width="100%">
      <thead>
      <tr>
        <th class="tw_form" colspan="100%" >Ingrese los criterios de búsqueda de los pagos realizados</th>
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
       <table  width="120px"  cellpadding="0" cellspacing="0">
       <thead >
       <tr ><td colspan="100%" style="font-size:1.1em;padding-top:5px;padding-bottom:4px;background-color:#80211D;color:#ffffff;" >Filtros de búsqueda</td><td style="font-size:1.1em;padding-top:5px;padding-bottom:4px;background-color:#80211D;color:#ffffff;" ><div style="background-color:Gray;width:20;height:20" onclick="document.getElementById('table-filtros').style.display='none';document.getElementById('mostrar-filtro').style.display='block'" onmouseover="this.style.cursor='hand';" >X</div></td> </tr>
       </thead>
       <tbody>
       <tr>
        <td nowrap align="left" width="50%">Número</td>
        <td colspan="2" align="left" width="50%"><input type="text" maxlength="10" size="10" name="payNumber" value="<c:out value="${payNumber}" />" class="tw_form" /></td>
       </tr>
       <tr>
       <td nowrap align="left" width="50%">Empleado</td>
       <td colspan="2" align="left" width="50%">
            <select name="listaEmpleados" id="listaEmpleados" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';">            
                <option value="" selected="selected">Seleccionar</option>
                <c:forEach items="${listaEmpleados}" var="item">
                   <c:choose>
                    <c:when test="${empId == item.empId}">
                       <option value="<c:out value="${item.empId}" />" selected="selected">
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
       <td nowrap align="left" width="50%">Fecha pago</td>
       <td colspan="2" nowrap>
           <select id="payDateOpt" name="payDateOpt" >
               <c:forEach items="${optionList}" var="item">
                   <c:choose>
                    <c:when test="${payDateOpt == item}">
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
            <input type="text" class="tw_form" name="fecha"  id="fecha" value="<c:out value="${fecha}" />" style="width:200" /><div id="divDesde" style="background:#FFFFFF;position:absolute"  ></div> <img  onclick="cal1Desde.select(document.forms[0].fecha,'selDesde','dd/MM/yyyy'); return false;" NAME="selDesde" ID="selDesde"  height="20" width="20" alt="seleccion" src="img/cal.png" onmouseover="this.style.cursor='hand';"></img>
        </td>      
        </tr>
       <tr  >
        <td width="50%"  valign="top" align="right" ><input type="button" value="Buscar" onclick="buscarPagos()"  /></td>
        <td width="50%"  valign="top" align="left" ><input type="button" value="Nueva Búsqueda" onclick="nuevaBusqueda()"  /></td>                     
       </tr>
       
       </tbody>
       </table>
       </td></tr></table>
       </div>
       </td><td align="left" width="100%" valign="top" >
        <table id ="tabla-busqueda">
            <thead>
               <tr style="display:block; background-color='transparent';" align="center">
                    <th width="10%" bgcolor="#80211D">#</th>
                    <th width="15%" bgcolor="#80211D">Nombre empleado</th>                       
                    <th width="10%" bgcolor="#80211D">Fecha pago</th>
                    <th width="10%" bgcolor="#80211D">Cuenta</th>
                    <th width="10%" bgcolor="#80211D">Moneda</th>                    
                    <th width="10%" bgcolor="#80211D">Total</th>
                    <th width="15%" bgcolor="#80211D">Usuario que lo realizó</th>                    
                    <th width="10%" bgcolor="#80211D">Ver detalle</th>  
                    <th width="10%" bgcolor="#80211D">Anular pago</th> 
                    <th width="10%" bgcolor="#80211D">Imprimir</th> 
                </tr>
            </thead>
          <c:choose   >
          <c:when test="${not empty listaPagos}">
           <tbody>
            <c:set scope="page" var="color_row" value="${'#E8B6B5'}" />
            
            <c:forEach items="${listaPagos}" var="Pago" varStatus="status" >     
           
            <tr bgcolor="<c:out value="${color_row}"/>" >
                <td nowrap ><c:out value="${Pago.payId}"/></td>
                <td nowrap ><c:out value="${Pago.employeeTO.empFirstName}" /> <c:out value="${Pago.employeeTO.empLastName}" />
                    <input type="hidden" name="empId" id="empId" value="<c:out value="${Pago.employeeTO.empId}"/>"/></td>
                <td nowrap ><fmt:formatDate value="${Pago.payDate}"    pattern="dd/MM/yyyy" /></td>
                <td nowrap ><c:out value="${Pago.accountsTO.accName}" /></td>
                <td nowrap ><c:out value="${Pago.currencyTO.curName}" /></td>
                <td nowrap ><c:out value="${Pago.payAmount}" /></td>
                <td nowrap ><c:out value="${Pago.userTO.usrId}"/></td>                
                <td nowrap align="center"><a href="listaItemsPagos?payId=<c:out value="${Pago.payId}"/>&pageId=0"  onclick="return verItemsPago(this)"><img border=0 src="img/detail.png" alt="+" width="32" height="32" title="Ver items Pagados"  onmouseover="this.style.cursor='hand';"/></a></td>
                <td nowrap align="center" ><img  src="img/edit-delete.png" alt="Anular Pago" width="32" height="32" title="Anular Pago" onclick="anularPago(<c:out value="${Pago.payId}"/>)" onmouseover="this.style.cursor='hand';"/></td> 
                <td nowrap align="center">
                    <img  src="img/print.png" alt="print" width="32" height="32" title="Imprimir Pago" onclick="imprimirPago(<c:out value="${Pago.payId}"/>,<c:out value="${Pago.employeeTO.empId}"/>,'<fmt:formatDate value="${Pago.payDate}"    pattern="dd/MM/yyyy" />',<c:out value="${Pago.accountsTO.accId}"/>,'<c:out value="${Pago.currencyTO.curId}"/>#<c:out value="${Pago.currencyTO.curSymbol}"/>','<c:out value="${Pago.payAmount}"/>')" onmouseover="this.style.cursor='hand';"/>
                </td>
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
                    <tr>
                        <td>
                        <c:if test="${page != 0}">
                            <img src="img/player_start.png" height="20" width="20" onclick="back()" alt="<"/>
                        </c:if>
                           </td><td>Página <fmt:formatNumber type="number" minFractionDigits="0" value="${page + 1 }" /> de <fmt:formatNumber type="number" minFractionDigits="0" value="${maxPage }" />
                           </td><td>
                           
                        <c:if test="${(page + 1) < maxPage}"> 
                         <img src="img/player_next.png" height="20" width="20" onclick="next()" alt=">" />
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
                        <tr><td colspan="100%">Seleccione un empleado para buscar los pagos realizados</td></tr> 
                    </tbody>
                 </c:otherwise>
               </c:choose>  
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
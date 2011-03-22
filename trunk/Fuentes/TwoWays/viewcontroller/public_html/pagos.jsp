<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <link href="./twoways.css" rel="stylesheet" type="text/css"/>
    <script  type='text/javascript' src="./js/pagos.js"></script>
    <script type='text/javascript' src="./js/utils.js"></script>    
    <script type='text/javascript' src='/twoways/dwr/interface/towaysDWR.js'></script>
    <script type='text/javascript' src='/twoways/dwr/engine.js'></script>
    <script type='text/javascript' src='/twoways/dwr/util.js'></script>
    <title>Pagos</title>
  </head>
  <body>
  <jsp:include page="/WEB-INF/jspIncludes/menu.jsp" />
  <c:out value="${mensaje}" escapeXml="false"/>
  <form name="pago" action="pagos" method="POST">
  <input type="hidden" id="accion" name="accion" value=""/>
  <input type="hidden" id="payId" name="payId" value="<c:out value="${pago.payId}"/>"/>
  <input type="hidden" id="empId" name="empId" value="<c:out value="${pago.employeeTO.empId}"/>"/>
  <input type="hidden" id="mesId" name="mesId" value="<c:out value="${empId}"/>"/>
  <table width="80%" align="center">
      <thead>
      <tr>
        <th colspan="6" class="tw_form">Ingrese los campos con los datos del pago a realizar</th>
      </tr>
      </thead>
      <tbody>
      <tr>
      </tr>
      <tr>
        <td nowrap align="right" width="25%">Empleado:</td>
        <td align="left" width="25%">
           <select name="listaEmpleados" id="listaEmpleados" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';">            
                <option value="" selected="selected">Seleccionar</option>
                <c:forEach items="${listaEmpleados}" var="item">
                   <c:choose>
                    <c:when test="${pago.employeesTO.empId == item.empId}">
                       <option value="<c:out value="${item.empId}" />" style="background-color:#A4BAC7;" selected="selected">
                        <c:out value="${item.empFirstName}" /> <c:out value="${item.empLastName}" />
                      </option> 
                    </c:when>
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
        <td nowrap align="right" width="25%">Mes de pago:</td>
        <td align="left" width="25%">
           <select name="listaMes" id="listaMes" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';" onchange="buscarAsignaciones()">            
                <option value="" selected="selected">Seleccionar</option>
                <option value="1" >Enero</option>
                <option value="2" >Febrero</option>
                <option value="3" >Marzo</option>
                <option value="4" >Abril</option>
                <option value="5" >Mayo</option>
                <option value="6" >Junio</option>
                <option value="7" >Julio</option>
                <option value="8" >Agosto</option>
                <option value="9" >Septiembre</option>
                <option value="10" >Octubre</option>
                <option value="11" >Noviembre</option>
                <option value="12" >Diciembre</option>
                
        </select>
        </td> 
         
        <td nowrap align="right" width="25%">Fecha de pago:</td>
        <td align="left" width="25%">
            <c:choose>
                <c:when test="${auxDate != null && auxDate !=''}"> 
                    <input type="text" class="tw_form" id="payDate" name="payDate"   value="<c:out  value="${auxDate}"/>" size="10" maxlength="10" onfocus="javascript:this.style.background='#FFFFFF';"></input>
                </c:when>
                <c:otherwise>
                    <input type="text" class="tw_form" id="payDate" name="payDate"   value="<c:out  value="${pago.payDate}"/>" size="10" maxlength="10" onfocus="javascript:this.style.background='#FFFFFF';"></input>
                </c:otherwise>
            </c:choose>
        </td> 
      </tr>
      <tr>
        <td nowrap align="right" width="25%">Descripción:</td>
        <td colspan="5" align="left">
              <input type="text" class="tw_form" id="payDescription" name="payDescription"   value="<c:out  value="${pago.payDescription}"/>" size="100" maxlength="250" onfocus="javascript:this.style.background='#FFFFFF';"></input>
        </td> 
      </tr>
      <tr>
        <td nowrap align="right" width="25%">Observación:</td>
        <td colspan="5" align="left">
              <textarea rows="3" cols="50" class="tw_form" id="payObservation" name="payObservation"><c:out value="${pago.payObservation}"/></textarea>
        </td> 
      </tr>
      <tr>
        <td colspan="6" align="center" width="70%">
         <div class="fixedHeaderTablePago">
             <table id="tabla-asignaciones" name="tabla-asignaciones" cellpadding="0" cellspacing="1" align="center" width="100%">
             <thead>     
               <tr style="display:block; background-color='transparent'">
                    <th width="10%" bgcolor="#80211D">Fecha asignación</th>
                    <th width="10%" bgcolor="#80211D">Nombre proyecto</th>
                    <th width="10%" bgcolor="#80211D">Tipo tarifa</th>
                    <th width="10%" bgcolor="#80211D">Monto tarifa</th>
                    <th width="10%" bgcolor="#80211D">Total unidades</th>                    
                    <th width="10%" bgcolor="#80211D">Total asignación</th>
                    <th width="2%" bgcolor="#80211D"></th>
                </tr>
             </thead>  
             <c:choose   >
               <c:when test="${not empty projectAssignnments}">
             <tbody>
               <c:forEach items="${projectAssignnments}" var="item">
               <tr name="item-idiomas" id="<c:out value="${item[\'PADID\']}"/>" >
                    <td width="10%" bgcolor="#FFFFF"><fmt:formatDate value="${item[\'PRAASSDATE\']}"  pattern="dd/MM/yyyy HH:mm" />
                       <input type="hidden" name="item-pago-hidden"  value="<c:out value="${item[\'PRAID\']}" />#<c:out value="${item[\'EMPID\']}" />#<c:out value="${item[\'PROJID\']}"/>"></input>
                       <input type="hidden" name="print-pago-hidden"  value="<c:out value="${item[\'PRAASSDATE\']}" />#<c:out value="${item[\'PROJNAME\']}" />#<c:out value="${item[\'RATNAME\']}"/>#<c:out value="${item[\'RATVALUE\']}" />#<c:out value="${item[\'WCOUNT\']}"/>#<c:out value="${item[\'PRATOTAL\']}"/>"</input></td>     
                    <td width="10%" bgcolor="#FFFFF"><a href="proyectos?ordId=<c:out value="${item[\'PROJID\']}" />"><c:out value="${item[\'PROJNAME\']}" /></a></td>     
                    <td width="10%" bgcolor="#FFFFF"><c:out value="${item[\'RATNAME\']}" /></td> 
                    <td width="10%" bgcolor="#FFFFF"><c:out value="${item[\'RATVALUE\']}" /></td>  
                    <td width="10%" bgcolor="#FFFFF"><c:out value="${item[\'WCOUNT\']}" /></td>       
                    <td width="10%" bgcolor="#FFFFF"><c:out value="${item[\'PRATOTAL\']}" /></td>                        
                    <td width="2%">
                        <img  src="img/del2.png" height="15" width="15"  alt="Eliminar" onclick="eliminarPagoAsignacion('<c:out value="${item[\'PADID\']}" />','<c:out value="${item[\'PRATOTAL\']}" />')" onmouseover="this.style.cursor='hand';" /></td>
                </tr>           
              </c:forEach>
              </tbody>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${not empty accion}">
                            <tbody>
                                <tr><td colspan="100%">No existen asignaciones de proyectos para el empleado</td></tr> 
                            </tbody>
                         </c:when>
                         <c:otherwise>
                            <tbody>
                                <tr><td colspan="100%">Seleccione un empleado para buscar las asignaciones de proyectos</td></tr> 
                            </tbody>
                         </c:otherwise>
                    </c:choose>
                </c:otherwise>
              </c:choose>
             </table>
         </div>
        </td>
    </tr>
    </tbody>
  </table>
  <hr>
  <table align="center" width="50%">
      <tr>
        <td nowrap align="right" width="10%"><b>Total:</b></td>
        <td width="20%" align="left"><input readonly type="text" class="tw_form" id="payAmount"  name="payAmount" value="<c:out value="${payAmount}" />" size="10" maxlength="15" style="text-align:right;"></input>
         <input type="hidden" name="print-total-hidden"  value="<c:out value="${payAmount}" />"></td>    
        
        <td nowrap align="right" width="10%">Moneda:</td>
        <td width="20%" align="leftx">
           <select name="listaMoneda" id="listaMoneda" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';">                
                    <option value="" >Seleccionar</option>
                    <c:forEach items="${listaMoneda}" var="item">
                        <option value="<c:out value="${item.curId}" />#<c:out value="${item.curName}" />" style="background-color:#A4BAC7;">
                            <c:out value="${item.curName}" />
                        </option>
                        
                    </c:forEach>
           </select>
        </td>
        <td nowrap align="right" width="10%">Cuenta:</td>        
        <td width="20%" align="left">
           <select name="listaCuentas" id="listaCuentas" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';">                
                    <option value="" >Seleccionar</option>
                    <c:forEach items="${listaCuentas}" var="item">
                        <option value="<c:out value="${item.accId}" />#<c:out value="${item.accName}" />" style="background-color:#A4BAC7;">
                            <c:out value="${item.accName}" />
                        </option>
                    </c:forEach>
           </select> 
        </td>        
      </tr>
  </table>
  <hr class="tw_hr">
      <table width="25%" align="center">
          <tr>
              <td align="right"><input type="button" id="aceptar" value="Aceptar" onclick="cargar()"/></td>   
              <td align="center"><input type="button" id="cancel" value="Limpiar" OnClick="cancelar()"/></td>   
              <td align="left"><input type="button" id="eliminar" value="Imprimir" OnClick="ImprimirPago()"/></td>
          </tr>
      </table>
  </form>
  </body>
</html>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
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

  <table width="80%" align="center">
      <thead>
      <tr>
        <th colspan="4" class="tw_form">Ingrese los campos con los datos del Pago a realizar</th>
      </tr>
      </thead>
      <tbody>
      <tr>
      </tr>
      <tr>
        <td nowrap align="right" width="25%">Empleado:</td>
        <td align="left" width="25%">
           <select name="listaEmpleados" id="listaEmpleados" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';" onchange="buscarAsignaciones()">            
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
        <td nowrap align="right" width="25%">Fecha:</td>
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
        <td colspan="3" align="left">
              <input type="text" class="tw_form" id="payDescription" name="payDescription"   value="<c:out  value="${pago.payDescription}"/>" size="100" maxlength="250" onfocus="javascript:this.style.background='#FFFFFF';"></input>
        </td> 
      </tr>
      <tr>
        <td nowrap align="right" width="25%">Observación:</td>
        <td colspan="3" align="left">
              <textarea rows="3" cols="40" class="tw_form" id="payObservation" name="payObservation"><c:out value="${pago.payObservation}"/></textarea>
        </td> 
      </tr>
      <tr>
        <td colspan="4" align="center" width="70%">
         <div class="fixedHeaderTablePago">
             <table cellpadding="0" cellspacing="1" align="center" width="100%">
             <thead>     
               <tr style="display:block; background-color='transparent'">
                    <th width="10%" bgcolor="#80211D">Fecha Asignación</th>
                    <th width="10%" bgcolor="#80211D">Estado Asignación</th>
                    <th width="10%" bgcolor="#80211D">Nombre Proyecto</th>
                    <th width="10%" bgcolor="#80211D">Estado Proyecto</th>
                    <th width="10%" bgcolor="#80211D">Nombre Orden</th>
                    <th width="10%" bgcolor="#80211D">Cliente</th>
                    <th width="10%" bgcolor="#80211D">Total Asignación</th>
                    <th width="2%" bgcolor="#80211D"></th>
                </tr>
             </thead>  
             <c:choose   >
               <c:when test="${not empty projectAssignnments}">
             <tbody>
               <c:forEach items="${projectAssignnments}" var="item">
               <tr name="item-idiomas" id="praId-<c:out value="${item[\'PRAID\']}"/>" >
                    <td width="10%" bgcolor="#FFFFF"><c:out value="${item[\'PRAASSDATE\']}" />
                       <input type="hidden" name="item-pago-hidden"  value="<c:out value="${item[\'PRAID\']}" />#<c:out value="${item[\'EMPID\']}" />#<c:out value="${item[\'PROJID\']}"/>"</td>     
                    <td width="10%" bgcolor="#FFFFF"><c:out value="${item[\'PRAASSSTATE\']}" /></td>     
                    <td width="10%" bgcolor="#FFFFF"><c:out value="${item[\'PROJNAME\']}" /></td>     
                    <td width="10%" bgcolor="#FFFFF"><c:out value="${item[\'PROJSTATE\']}" /></td> 
                    <td width="10%" bgcolor="#FFFFF"><c:out value="${item[\'ORDNAME\']}" /></td>  
                    <td width="10%" bgcolor="#FFFFF"><c:out value="${item[\'CLINAME\']}" /></td>       
                    <td width="10%" bgcolor="#FFFFF"><c:out value="${item[\'PRATOTAL\']}" /></td>                        
                    <td width="2%" bgcolor="#FFFFF">
                        <img  src="img/del2.png" height="15" width="15"  alt="Eliminar" onclick="eliminarPagoAsignacion('<c:out value="${item[\'PRAID\']}" />')" onmouseover="this.style.cursor='hand';" /></td>
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
        <td width="20%" align="left"><input type="text" class="tw_form" id="payAmount"  name="payAmount"  value="<c:out value="${gasto.payAmount}"/>" size="10" maxlength="10"></input></td>    
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
              <td align="left"><input type="button" id="eliminar" value="Eliminar" OnClick="eliminarPago(<c:out value="${payId}"/>)"/></td>
          </tr>
      </table>
  </form>
  </body>
</html>
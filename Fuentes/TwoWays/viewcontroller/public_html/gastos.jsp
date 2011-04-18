<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <link href="./twoways.css" rel="stylesheet" type="text/css"/>
    <script  type='text/javascript' src="./js/gastos.js"></script>
    <script type='text/javascript' src="./js/utils.js"></script>    
    <script type='text/javascript' src='/twoways/dwr/interface/towaysDWR.js'></script>
    <script type='text/javascript' src='/twoways/dwr/engine.js'></script>
    <script type='text/javascript' src='/twoways/dwr/util.js'></script>
    <script type='text/javascript' src="./js/commons.js"></script>  
    <script type='text/javascript' src="./js/CalendarPopup.js"></script>   
    <title>Gastos</title>
  </head>
  <body>
  <jsp:include page="/WEB-INF/jspIncludes/menu.jsp" />
  <c:out value="${mensaje}" escapeXml="false"/>
  <form name="gasto" action="gastos" method="POST">
  <input type="hidden" id="accion" name="accion" value=""/>
  <input type="hidden" id="itmId" name="itmId" value="<c:out value="${item.itmId}"/>"/>
  <input type="hidden" id="empId" name="empId" value="<c:out value="${gasto.EmployeesTO.empId}"/>"/>  
  <input type="hidden" id="itmDate" name="itmDate" value="<c:out value="${gasto.expDate}"/>"/>   
  <input type="hidden" id="expId" name="expId" value="<c:out value="${auxExpId}"/>"/>   
  <table width="50%" align="center">
  <tr>
    <th colspan="3" align="center" class="tw_form">Ingrese los campos con los datos de los Gastos</th>
  </tr>
  </table>
  <table width="30%" align="center">
  <tr>
    <td width="10%" align="center" style ="background-color:#80211D;color:#ffffff;">Fecha:</td>
    <td width="10%" align="center" style ="background-color:#80211D;color:#ffffff;">
        <c:choose>
            <c:when test="${auxDate != null && auxDate !=''}"> 
                <input type="text" class="tw_form" id="expFecha" name="expFecha"   value="<c:out  value="${auxDate}"/>" size="10" maxlength="10" onfocus="javascript:this.style.background='#FFFFFF';"></input>
            </c:when>
            <c:otherwise>
                <input type="text" class="tw_form" id="expFecha" name="expFecha"   value="<c:out  value="${gastos.expFecha}"/>" size="10" maxlength="10" onfocus="javascript:this.style.background='#FFFFFF';"></input><div id="divDesde" style="background:#FFFFFF;position:absolute"  ></div> <img  onclick="cal1Desde.select(document.forms[0].expFecha,'selDesde','dd/MM/yyyy'); return false;" NAME="selDesde" ID="selDesde"  height="20" width="20" alt="seleccion" src="img/cal.png" onmouseover="this.style.cursor='hand';"></img>
            </c:otherwise>
        </c:choose>
          <!--<div id="divDesde" style="background:#FFFFFF;position:absolute"  ></div> 
          <img  onclick="callDesde.select(document.forms[0].expFecha,'selGasto','dd/MM/yyyy'); return false;" NAME="selGasto" ID="selGasto"  height="20" width="20" alt="seleccion" src="img/cal.png"></img>
   --> </td>
   <!-- <td nowrap width="25%" align="right" style ="background-color:#80211D;color:#ffffff;align:left">Nombre del empleado:</td>
    <td width="25%" align="left" style ="background-color:#80211D;color:#ffffff;align:left">
        <select name="nombreEmp" id="nombreEmp" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';">            
            <option value="" selected="selected">Seleccionar</option>        
            <c:forEach items="${listaEmpleados}" var="item">
                <c:choose>
                    <c:when test="${auxEmp == item.empId}">
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
    </td>-->
    <td width="10%" align="center" style ="background-color:#80211D;color:#ffffff;"><input type="button" size="5" value="Buscar" id="BuscarBoton" onClick="BuscarItemFecha()"/>
    </td>
  </tr>
  </table>
<table id="tabla-gastos" align="center" width="53%">
 <tr>
 <td>  
  <table id="tabla-gastos-head" align="center" width="100%">
  <tr bgcolor="#E8B6B5">
    <th nowrap width="25%" align="center">Nombre del Item</th>
    <th width="15%" align="center">Moneda</th>
    <th width="10%" align="center">Monto</th>
    <th width="20%" align="center">Cuenta</th>
    <th width="20%" align="center">¿Quién lo cargó?</th>
    <th width="10%">&nbsp;</th>
  </tr>
  <tr>
    <td width="25%" align="center"  style ="background-color:#F8E0E0;color:#585858;align:left">
       <select name="listaItems" id="listaItems" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';">            
        <option value="" selected="selected">Seleccionar</option>
            <c:forEach items="${listaItems}" var="item">
                <option value="<c:out value="${item.itmId}" />#<c:out value="${item.itmName}" />" style="background-color:#A4BAC7;">
                    <c:out value="${item.itmName}" />
                </option>
            </c:forEach>
       </select> 
    </td>
    <td width="15%" align="center"  style ="background-color:#F8E0E0;color:#585858;align:left">       
       <select name="listaMoneda" id="listaMoneda" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';">                
                <option value="" >Seleccionar</option>
                <c:forEach items="${listaMoneda}" var="item">
                    <option value="<c:out value="${item.curId}" />#<c:out value="${item.curName}" />" style="background-color:#A4BAC7;">
                        <c:out value="${item.curName}" />
                    </option>
                    
                </c:forEach>
       </select> 
    </td>
    <td width="10%" align="center"  style ="background-color:#F8E0E0;color:#585858;align:left">
        <input type="text" size="7" id="expMonto" name="expMonto" class="tw_form" onfocus="javascript:this.style.background='#FFFFFF';" style="text-align:right;"></input>
    </td>
    <td width="20%" align="center"  style ="background-color:#F8E0E0;color:#585858;align:left">
       <select name="listaCuentas" id="listaCuentas" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';">                
                <option value="" >Seleccionar</option>
                <c:forEach items="${listaCuentas}" var="item">
                    <option value="<c:out value="${item.accId}" />#<c:out value="${item.accName}" />" style="background-color:#A4BAC7;">
                        <c:out value="${item.accName}" />
                    </option>
                </c:forEach>
       </select> 
    </td>
    <td width="20%" align="center"  style ="background-color:#F8E0E0;color:#585858;align:left">
        <input readonly size="15" type="text" id="expUsuario" name="expUsuario" class="tw_form" onfocus="javascript:this.style.background='#FFFFFF';" value="<c:out value="${expUsuario}" />" ></input>
        <input type="hidden" id="iteId" name="iteId" value=""/>   
    </td>
    <td width="10%" align="center"  style ="background-color:#F8E0E0;color:#585858;align:left">
        <img  src="img/Add.png" alt=">" width="20" height="20" title="Agregar Item de Gasto" onclick="agregarItemGasto()" onmouseover="this.style.cursor='hand';"/>
    </td>
  </tr>
 </table>
 </td>
 </tr>
 <tr>
 <td>
    <!--<c:choose>
      <c:when test="${empty requestScope.gastos.itemsExpensesTOList}">
        <table id="tabla-gastos-body" width="100%" style="display:none">
      </c:when>
      <c:otherwise>
         <table id="tabla-gastos-body" width="100%">
      </c:otherwise>
    </c:choose>-->
    <table id="tabla-gastos-body" width="100%" bgcolor="grey">
      <tr>
        <td>
        <div class="fixedHeaderTableGastos">
        <table id="list-gastos-body" align="center" width="100%" cellSpacing="1" >
        <thead>     
        <tr style="display:block; background-color='transparent'">
            <th width="25%"></th>
            <th width="15%"></th>
            <th width="10%"></th>
            <th width="20%"></th>
            <th width="20%"></th>
            <th width="10%"></th>
        </tr>
        </thead>  
        <tbody>
            <c:forEach items="${itemsExpense}" var="item">
            <tr name="item-gastos"  bgcolor="#FCEEED" id="<c:out value="${item[\'ITE_ID\']}" />" >            
                <td width="25%" ><c:out value="${item[\'ITM_NAME\']}" />
                  <input type="hidden" name="item-gasto-hidden"  value="<c:out value="${item[\'ITM_ID\']}" />#<c:out value="${item[\'CUR_ID\']}" />#<c:out value="${item[\'ITE_VALUE\']}" />#<c:out value="${item[\'ACC_ID\']}" />#<c:out value="${item[\'USR_ID\']}" />#<c:out value="${item[\'ITE_ID\']}" />"</td>
                <td width="15%" ><c:out value="${item[\'CUR_NAME\']}" /></td>
                <td width="10%" align="right"><c:out value="${item[\'ITE_VALUE\']}" /></td>
                <td width="20%" ><c:out value="${item[\'ACC_NAME\']}" /></td>
                <td width="20%" ><c:out value="${item[\'USR_ID\']}" /></td>                                   
                <td width="10%">
                    <img  src="img/del2.png" height="15" width="15"  alt="Eliminar" onclick="eliminarItemExp('<c:out value="${item[\'ITE_ID\']}" />')" onmouseover="this.style.cursor='hand';" />    <img  src="img/Edit2.png" height="15" width="15"  alt="Editar Item de Gasto" onclick="editarItemExp('<c:out value="${item[\'ITM_ID\']}"/>#<c:out value="${item[\'ITM_NAME\']}"/>*#<c:out value="${item[\'CUR_ID\']}"/>#<c:out value="${item[\'CUR_NAME\']}"/>*#<c:out value="${item[\'ITE_VALUE\']}"/>*#<c:out value="${item[\'ACC_ID\']}"/>#<c:out value="${item[\'ACC_NAME\']}"/>*#<c:out value="${item[\'USR_ID\']}"/>*#<c:out value="${item[\'ITE_ID\']}" />')" onmouseover="this.style.cursor='hand';" /></td>
            </tr>           
            </c:forEach>
        </tbody>
        </table>
        </div>
        </td>
     </tr>
    </table>
  </td>
  </tr>
  </table>
<!--</td>
</tr>
</table>-->
<br>
  <hr class="tw_hr">
  <table width="25%" align="center">
      <tr>
          <td align="right"><input type="button" id="aceptar" value="Aceptar" onclick="agregar()"/></td>   
          <td align="center"><input type="button" id="cancel" value="Limpiar" OnClick="cancelar()"/></td>   
          <td align="left"><input type="button" id="eliminar" value="Eliminar" OnClick="eliminarGasto('<c:out value="${auxExpId}"/>')"/></td>
      </tr>
  </table>
  </form>
  </body>
</html>
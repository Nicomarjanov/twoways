<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252" %>
<%@ page errorPage="error.jsp" %>
<%@ page isELIgnored="true" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>



<html>
  <head>
   <meta http-equiv="Content-Type" content="multipart/form-data"/>
    <link href="./twoways.css" rel="stylesheet" type="text/css"/>
    <script type='text/javascript' src="./js/proyectos.js"></script>
    <script type='text/javascript' src="./js/agregartarifa.js"></script>
    <script type='text/javascript' src="./js/agregardocumentos.js"></script>
    <script type='text/javascript' src="./js/utils.js"></script>
    <script type='text/javascript' src='/twoways/dwr/interface/towaysDWR.js'></script>
    <script type='text/javascript' src='/twoways/dwr/engine.js'></script>
    <script type='text/javascript' src='/twoways/dwr/util.js'></script>
    <script type='text/javascript' src="./js/commons.js"></script>  
    <script type='text/javascript' src="./js/CalendarPopup.js"></script>
     <script>
        writeSource('jscallDesde');
        writeSource('jscallHasta');
    </script>
    
    <title>Proyectos</title>
    
  </head>
 
  
  <body id="proyecto-body"   >
  <jsp:include page="/WEB-INF/jspIncludes/menu.jsp" /><c:out value="${mensaje}" escapeXml="false"/>
<form   id="formProyecto"  action="proyectos" method="POST" >  
<input type ="hidden" name="ordId" id="ordId" value="<c:out value="${project.ordersTO.ordId}" />" /> 
<input type ="hidden" name="accion" id="accion" />
<table width="100%">
  <tr>
    <th class="tw_form">Ingrese los campos con los datos del Proyecto</th>
  </tr>
  </table>
  
  <table align="center" width="70%">
     <tr>
        <td nowrap align="right" >Proyecto:</td><td colspan="7">
        <input type="text" size="145"   class="tw_form" name="proName" id="proName"  value="<c:out value="${project.proName}" />" /></td>
     </tr>
     <tr>
     <td nowrap align="right" >Orden:</td>
       <td  nowrap>
        <input type="text" size="40"  readonly="readonly" class="tw_form" name="ordName" id="ordName"  value="<c:out value="${project.ordersTO.ordName}" />" /><a href="ordentrabajo?ordId=<c:out value="${project.ordersTO.ordId}" />" >Ir a la orden</a> </td>
      <td nowrap align="right" >Cliente:</td><td >
         <input type="text" size="55" readonly="readonly"  class="tw_form" name="clientsName" id="ordName"  value="<c:out value="${project.ordersTO.clientsTO.cliName}" />" />
       </td>
       <td>&nbsp;</td>
     </tr>
     <tr>
      <td nowrap align="right" >
       Fecha Inicio Orden:</td> 
      <td>
      <input type="text" class="tw_form" name="ordDate" readonly="readonly" id="ordDate"  value="<fmt:formatDate value="${project.ordersTO.ordDate}"    pattern="dd/MM/yyyy HH:mm" />" /> </td><td nowrap align="right" >Fecha de Entrega Orden:</td><td ><input type="text" class="tw_form" name="ordFinishDate" readonly="readonly" id="ordFinishDate"  value="<fmt:formatDate value="${project.ordersTO.ordFinishDate}"    pattern="dd/MM/yyyy HH:mm" />" /> 
      </td>
      <td>&nbsp;</td>
    </tr>
    <tr>
    <tr>
      <td nowrap align="right" >
       Fecha Inicio Proyecto:</td> 
      <td>
      <input type="text" class="tw_form" name="proStartDate" id="proStartDate"  value="<fmt:formatDate value="${project.proStartDate}"    pattern="dd/MM/yyyy HH:mm" />" /><div id="divDesde" style="background:#FFFFFF;position:absolute"  ></div> <img  onclick="cal1Desde.select(document.forms[0].proStartDate,'selDesde','dd/MM/yyyy'); return false;" NAME="selDesde" ID="selDesde"  height="20" width="20" alt="seleccion" src="img/cal.png"></img> 
      </td>
      <td nowrap align="right" >
      Fecha de Entrega Proyecto:</td><td >
      <input type="text" class="tw_form" name="proFinishDate" id="proFinishDate"  value="<fmt:formatDate value="${project.proFinishDate}"    pattern="dd/MM/yyyy HH:mm" />" /><div id="divHasta" style="background:#FFFFFF;position:absolute"  ></div> <img onclick="cal1Hasta.select(document.forms[0].proFinishDate,'selHasta','dd/MM/yyyy'); return false;" NAME="selHasta" ID="selHasta"  height="20" width="20" alt="seleccion" src="img/cal.png"></img> 
      </td>
      <td>&nbsp;</td>
    </tr>
         <td nowrap align="right" >Descripción:</td>
         <td colspan="6"  ><textarea  rows="3" cols="109"  class="tw_form"  onkeyup="limitarArea()"  name="proDescription" id="proDescription" ><c:out value="${project.proDescription}" /></textarea> </td>
    </tr>
 </table>

<c:if test="${not empty project.proId}">
<div align="center" style="width:100%" >
<table width="80%" cellspacing="0" bgcolor="Gray"  border =1  >
  <tr >
      <th style ="background-color:#80211D;color:#ffffff;align:left" >Fecha de Asignación</th>
      <th style ="background-color:#80211D;color:#ffffff;align:left">Empleado</th> 
      <th style ="background-color:#80211D;color:#ffffff;align:left">Servicio</th>
      <th style ="background-color:#80211D;color:#ffffff;align:left" >Total<br>de<br>Palabras</th>
      <th style ="background-color:#80211D;color:#ffffff;align:left">Costo total</th> 
      <th style ="background-color:#80211D;color:#ffffff;align:right"><img id="NuevaAsignacion" alt="Nueva Asignación" align="right" title="Nueva Asignación" width="15" height="15"  src="img/asignar.png"  onclick="asignarProyecto('<c:out value="${project.proId}" />');"  onmouseover="this.style.cursor='hand';" /></th>   
  </tr>
  <c:forEach var="assi" items="${project.projectAssignmentsTOList}" >
     <tr bgcolor="#E8B6B5">
      <th> <fmt:formatDate value="${assi.praAssignDate}"   pattern="dd/MM/yyyy HH:mm"  /></th>
      <th><c:out value="${assi.employeesTO.empFirstName}" /> <c:out value="${assi.employeesTO.empLastName}" /></th> 
      <th><c:out value="${assi.serviceTO.rtyName}" /></th>
      <th align="center" ><input type="text"  style="WIDTH: 70px;text-align:right;"  name="praTotalAmount"  value="<c:out value="${assi.praTotalAmount}" />" /></th>
      <th align="center"> <input type="text"  style="WIDTH: 70px;text-align:right;"  name="totalAmount-<c:out value="${assi.praId}" />" readonly  id="totalAmount-<c:out value="${assi.praId}" />" value="0" />
      <th  nowrap align="right">
             <img alt="Editar"  src="img/edit.png" title="Editar Asignación"  onclick="editarAsignarProyecto(<c:out value="${assi.praId}" />,<c:out value="${project.proId}" />)" width="15" height="15" onmouseover="this.style.cursor='hand';"/></img>  
             <img alt="Eliminar"  src="img/Delete.png" title="Eliminar Asignación" onclick="quitarAsignacion(<c:out value="${assi.praId}" />,<c:out value="${project.proId}" />)" width="15" height="15" onmouseover="this.style.cursor='hand';"/></img>  
             <img src="img/bottom.png" id="aMas-<c:out value="${assi.praId}" />" onclick="javascript:mostrarDetalle(<c:out value="${assi.praId}" />);" title="Editar detalle" title="Mostrar detalle" alt="Mostrar detalle " width="15" height="15" onmouseover="this.style.cursor='hand';"/>
             <img id="aMenos-<c:out value="${assi.praId}" />"  style="border:0;display:none"  onclick="javascript:ocultarDetalle(<c:out value="${assi.praId}" />);" title="Contraer detalle" src="img/top.png" alt="Contraer detalle" width="15" height="15" onmouseover="this.style.cursor='hand';"/>
      </th>
      </tr>
      <tr>
      <td colspan="100%"   >
      <table id="tabla-<c:out value="${assi.praId}" />" cellspacing="0" width="100%"  style="display:none"  border =1  >
         <tr>
         <th style ="background-color:#F8E0E0;color:#585858;align:left">Documento</th>
         <th style ="background-color:#F8E0E0;color:#585858;align:left">Lenguajes</th>
         <th style ="background-color:#F8E0E0;color:#585858;align:left" >Unidades<br>de<br>servicio</th>
         <th style ="background-color:#F8E0E0;color:#585858;align:left">Tipo<br>de<br>Tafifa</th>
         <th style ="background-color:#F8E0E0;color:#585858;align:center" >Tarifa</th>
         <th style ="background-color:#F8E0E0;color:#585858;align:center" >Unidades<br>X<br>Tarifa</th>
       </tr>
       <c:choose>
       <c:when test="${ not empty assi.proAssigmentsDetailsTO  }">
       <c:forEach var="assiDet" items="${assi.proAssigmentsDetailsTO}" >
          <tr bgcolor="#FCEEED">
         <td><c:out value="${assiDet.ordersDocsTO.odoName}" /></td>
          <td> <c:if test="${not empty assiDet.pranslatorsLanguaguesTO}">
           [<c:out value="${assiDet.pranslatorsLanguaguesTO.langAcronymsTO.languaguesTO.lanName}" /> - <c:out value="${assiDet.pranslatorsLanguaguesTO.langAcronymsTO.laaAcronym}" />] - [<c:out value="${assiDet.pranslatorsLanguaguesTO.langAcronymsTO1.languaguesTO.lanName}" /> - <c:out value="${assiDet.pranslatorsLanguaguesTO.langAcronymsTO1.laaAcronym}" />]
           </c:if>
         </td>
         <td align="center" ><input type="text" title="Unidades"  onblur="calcularTotalDetalle('<c:out value="${assiDet.padId}" />','<c:out value="${assi.praId}" />')" style="WIDTH: 70px;text-align:right;"  name="padWCount-<c:out value="${assiDet.padId}" />" id="padWCount-<c:out value="${assiDet.padId}" />"  value="<c:out value="${assiDet.padWCount}" />" /></td>
         <td> <c:out value="${assiDet.employeesRatesTO.ratesTO.ratName}" /></td>
         <td align="center" ><input type="text" style="WIDTH: 70px;text-align:right;" onblur="calcularTotalDetalle('<c:out value="${assiDet.padId}" />','<c:out value="${assi.praId}" />')" name="padRate-<c:out value="${assiDet.padId}" />" id="padRate-<c:out value="${assiDet.padId}" />" value="<c:out value="${assiDet.padRate}" />" /></td>
         <td>
             <c:choose>
             <c:when test="${not empty assiDet.padWCount && not empty assiDet.padRate}">
                   <input type="text" style="WIDTH: 70px;text-align:right;" readonly  name="tarifXunid-<c:out value="${assi.praId}" />"  id="tarifXunid-<c:out value="${assiDet.padId}" />" value="<c:out value="${assiDet.padWCount * assiDet.padRate}" />" />
             </c:when>
             <c:otherwise>
                   <input type="text" style="WIDTH: 70px;text-align:right;" readonly  name="tarifXunid-<c:out value="${assi.praId}" />"  id="tarifXunid-<c:out value="${assiDet.padId}" />" value="<c:out value="${0}" />" />
             </c:otherwise>
             </c:choose>
          </td>
         </tr>
      </c:forEach>
      </c:when>
      <c:otherwise>
         <tr ><td colspan="100%"  style="color:#FFFFFF " >No hay Documentos Asignados</td> 
      </c:otherwise>
      </c:choose>
      </table>
      </td>
      </tr>
  </c:forEach>
</table>
</div>
 </c:if>
<table width="100%">
  <tr>
      <td align="right"><input type="button" id="aceptar" value="Aceptar" onclick="agregar()"/></td>   
      <td align="left"><input type="button" id="cancel" value="Limpiar" OnClick="cancelar()"/></td>    
  </tr>
</table>

</form>
</body>
<script>onloadOrder();</script>
</html>
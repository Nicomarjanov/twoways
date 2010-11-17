<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ page errorPage="error.jsp" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
  <head>
   <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <link href="./twoways.css" rel="stylesheet" type="text/css"/>
    <script type='text/javascript' src="./js/ordentrabajo.js"></script>
    <script type='text/javascript' src="./js/agregartarifa.js"></script>
    <script type='text/javascript' src="./js/utils.js"></script>
    <script type='text/javascript' src='/twoways/dwr/interface/towaysDWR.js'></script>
    <script type='text/javascript' src='/twoways/dwr/engine.js'></script>
    <script type='text/javascript' src='/twoways/dwr/util.js'></script>
    
    <title>Orden de Trabajo</title>
    
  </head>
 
  
  <body>
  <jsp:include page="/WEB-INF/jspIncludes/menu.jsp" />
   <c:out value="${mensaje}" escapeXml="false"/>
<form action="ordentrabajo" >  
<input type ="hidden" name="ordId" id="ordId" /> 
<table width="100%" class="tw_form">
  <tr>
    <th class="tw_form">Ingrese los campos con los datos de la orden</th>
  </tr>
  </table>
  <table align="center" width="50%">
        <tr>
         <td nowrap align="right" >Nombre </td><td><input type="text" class="tw_form" name="ordName" id="ordName" /></td>
         <td nowrap align="right" >Fecha </td><td colspan="5"><input type="text" class="tw_form" name="ordDate" id="ordDate" /> </td>
        </tr>
        <tr>
        <td nowrap align="right" >Cliente </td><td >
        <select name="listaClientes" id="listaClientes" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';">
               
                <option value=""  >Seleccionar</option>
                <c:forEach items="${listaCliente}" var="item">
                   <c:out value="${item.cliId}" />
                   <c:choose>
                    <c:when test="${false}">
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
        </td></tr>
        <tr>
            <td colspan="100%" align="left">
            <img style="position:absolute"  src="img/bottom.png" id="aMas" onclick="javascript:mostrarOpcionales();"  title="Agregar más datos" alt="Mas datos " width="15" height="15" onmouseover="this.style.cursor='hand';"/>
            <img id="aMenos"  style="border:0;display:none;position:absolute"  onclick="javascript:ocultarOpcionales();" title="Contraer" src="img/top.png" alt="Menos" width="15" height="15" onmouseover="this.style.cursor='hand';"/></td>
        </tr>
        <tr id="trOpcionales1" style="display:none">
         <td nowrap align="right" >Proyecto </td><td><input type="text" class="tw_form" name="ordProjId" id="ordProjId" /> </td>
         <td nowrap align="right" >WO Number</td><td><input type="text" class="tw_form" name="ordWoNumber" id="ordWoNumber" size="10" /> </td>
         <td nowrap align="right" >Job Id</td><td><input type="text" class="tw_form" name="ordJobId" id="ordJobId" /> </td>
         <td nowrap align="right" >Job Name</td><td><input type="text" class="tw_form" name="ordJobName" id="ordJobName" /></td></tr>
        <tr id="trOpcionales2" style="display:none"><td nowrap align="right" >Job Description</td><td colspan="7"><textarea  rows="3" cols="90"  class="tw_form" name="ordJobDescription" id="ordJobDescription" ></textarea></td></tr>
        <tr> <td nowrap align="right" >Servicio </td><td>
         <select name="listaServices" id="listaServices" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';">
               
                <option value=""  >Seleccionar</option>
                <c:forEach items="${listaService}" var="item">
                   <c:out value="${item.serId}" />
                   <c:choose>
                    <c:when test="${false}">
                       <option value="<c:out value="${item.serId}" />" style="background-color:#A4BAC7;" selected="selected">
                        <c:out value="${item.serName}" />
                      </option> 
                    </c:when>
                    <c:otherwise>
                    <option value="<c:out value="${item.serId}" />" style="background-color:#A4BAC7;">
                        <c:out value="${item.serName}" />
                    </option>
                    </c:otherwise>
                    </c:choose>
                </c:forEach>
       </select>
        </td></tr> 
        <tr>
         <td nowrap align="right" >Descripción </td><td colspan="7"  ><textarea  rows="3" cols="90"  class="tw_form" name="ordDescription" id="ordDescription"> </textarea> </td>
         </tr>
         <tr>
         <td colspan="100%" style="padding:0;spacing:0" >
         <jsp:include page="/WEB-INF/jspIncludes/agregartarifas.jsp" />
         </td>
         </tr>
        </table>
        
        <table width="100%">
  <tr>
  <td align="right"><input type="button" id="aceptar" value="Aceptar" onclick="agregar()"/></td>   
  <td align="left"><input type="button" id="cancel" value="Limpiar" OnClick="cancelar()"/></td>    
  </tr>
  </table>
  </form>
  </body>
</html>
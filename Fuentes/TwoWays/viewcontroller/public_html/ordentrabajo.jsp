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
    <script type='text/javascript' src="./js/ordentrabajo.js"></script>
    <script type='text/javascript' src="./js/agregartarifa.js"></script>
    <script type='text/javascript' src="./js/agregartarifaOrden.js"></script>
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
    
    <title>Orden de Trabajo</title>
    
  </head>
 
  
  <body ><jsp:include page="/WEB-INF/jspIncludes/menu.jsp" /><c:out value="${mensaje}" escapeXml="false"/>
<form action="ordentrabajo" enctype="multipart/form-data" method="POST" >  
<input type ="hidden" name="ordId" id="ordId" value="<c:out value="${order.ordId}" />" /> 
<input type ="hidden" name="accion" id="accion" />
<table width="100%">
  <tr>
    <th class="tw_form">Ingrese los campos con los datos de la orden</th>
  </tr>
  </table>
  <table align="center" width="50%">
    <tr>
        <td nowrap align="right" >Nombre:</td>
        <td colspan="7">
            <c:choose>
                <c:when test="${auxOrdName != null}">
                    <input type="text" size="145"  class="tw_form" name="ordName" id="ordName"  value="<c:out value="${auxOrdName}" />" />                
                </c:when>
                <c:otherwise>
                    <input type="text" size="145"  class="tw_form" name="ordName" id="ordName"  value="<c:out value="${order.ordName}" />" />
                </c:otherwise>
            </c:choose>
        </td>
     </tr>
     <tr>
        <td nowrap align="right" >Fecha Inicio:</td>
        <td>
           <c:choose>
                <c:when test="${auxOrdDate != null}">        
                    <input type="text" class="tw_form" name="ordStartDate" id="ordStartDate"  value="<c:out value="${auxOrdDate}"/>" />
                    <td align="left" ><div id="divDesde" style="background:#FFFFFF;position:absolute"  ></div> <img  onclick="cal1Desde.select(document.forms[0].ordStartDate,'selDesde','dd/MM/yyyy'); return false;" NAME="selDesde" ID="selDesde"  height="20" width="20" alt="seleccion" src="img/cal.png"></img></td>
                </c:when>
                <c:otherwise>     
                    <input type="text" class="tw_form" name="ordStartDate" id="ordStartDate"  value="<fmt:formatDate value="${order.ordStartDate}"    pattern="dd/MM/yyyy HH:mm" />" />
                    <td align="left"><div id="divDesde" style="background:#FFFFFF;position:absolute"  ></div> <img  onclick="cal1Desde.select(document.forms[0].ordStartDate,'selDesde','dd/MM/yyyy'); return false;" NAME="selDesde" ID="selDesde"  height="20" width="20" alt="seleccion" src="img/cal.png"></img></td>
                </c:otherwise>
            </c:choose> 
        </td>
        <td nowrap align="right" >Fecha de Entrega:</td>
        <td>
            <c:choose>
                <c:when test="${auxFinishDate != null}">        
                    <input type="text" class="tw_form" name="ordFinishDate" id="ordFinishDate"  value="<c:out value="${auxFinishDate}" />" />
                    <td align="left"><div id="divHasta" style="background:#FFFFFF;position:absolute"  ></div> <img  onclick="cal1Hasta.select(document.forms[0].ordFinishDate,'selHasta','dd/MM/yyyy'); return false;" NAME="selHasta" ID="selHasta"  height="20" width="20" alt="seleccion" src="img/cal.png"></img></td>
                </c:when>
                <c:otherwise>     
                    <input type="text" class="tw_form" name="ordFinishDate" id="ordFinishDate"  value="<fmt:formatDate value="${order.ordFinishDate}"    pattern="dd/MM/yyyy HH:mm" />" />
                    <td align="left"><div id="divHasta" style="background:#FFFFFF;position:absolute"  ></div> <img  onclick="cal1Hasta.select(document.forms[0].ordFinishDate,'selHasta','dd/MM/yyyy'); return false;" NAME="selHasta" ID="selHasta"  height="20" width="20" alt="seleccion" src="img/cal.png"></img></td>
                </c:otherwise>
            </c:choose>                
        </td>
    </tr>
    <tr>
        <td nowrap align="right" >Cliente:</td>
        <td align="left" colspan="2">
            <select name="listaClientes" id="listaClientes" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';" onchange="findResponsables();">               
                <option value=""  >Seleccionar</option>
                <c:forEach items="${listaCliente}" var="item">
                   <c:out value="${item.cliId}" />
                   <c:choose>
                   <c:when test="${auxCliId == item.cliId}">
                       <option value="<c:out value="${item.cliId}" />" style="background-color:#A4BAC7;" selected="selected">
                        <c:out value="${item.cliName}" />
                      </option> 
                    </c:when>
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
        <td nowrap align="right" >Responsable:</td>
        <td align="left" colspan="2">
            <select name="listaRespClientes" id="listaRespClientes" >                            
                <option selected="selected">Seleccionar</option>
                    <c:forEach items="${listaRespClientes}" var="item">
                       <c:choose>
                            <c:when test="${order.cliResponsableTO.creId == item.creId}">
                               <option value="<c:out value="${item.creId}" />" style="background-color:#A4BAC7;" selected="selected">
                                <c:out value="${item.creFirstName}" /> <c:out value="${item.creLastName}" />
                              </option> 
                            </c:when>
                            <c:otherwise>
                            <option value="<c:out value="${item.creId}" />" style="background-color:#A4BAC7;">
                                <c:out value="${item.creFirstName}" /> <c:out value="${item.creLastName}" />
                            </option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
           </select>
        </td>
    </tr>
    <tr>
         <td nowrap align="right" >Descripción:</td>
         <td colspan="9"  ><textarea  rows="3" cols="109"  class="tw_form"  onkeyup="limitarArea()"  name="ordDescription" id="ordDescription" ><c:out value="${order.ordDescription}" /></textarea> </td>
    </tr>
    <tr>
         <td colspan="9" align="left">
            <img style="position:absolute;display:none;"  src="img/bottom.png" id="aMas" onclick="javascript:mostrarOpcionales();"  title="Agregar más datos" alt="Mas datos " width="15" height="15" onmouseover="this.style.cursor='hand';"/>
            <img id="aMenos"  style="border:0;display:block;position:absolute"  onclick="javascript:ocultarOpcionales();" title="Contraer" src="img/top.png" alt="Menos" width="15" height="15" onmouseover="this.style.cursor='hand';"/>
         </td>
    </tr>
    <tr id="trOpcionales1" style="display:block">
         <td nowrap align="right" >Proyecto:</td><td><input type="text" class="tw_form" name="ordProjId" id="ordProjId"  value="<c:out value="${order.ordProjId}" />"  /> </td>
         <td nowrap align="right" >WO Number</td><td><input type="text" class="tw_form" name="ordWoNumber" id="ordWoNumber" size="10"  value="<c:out value="${order.ordWoNumber}" />"  /> </td>
         <td nowrap align="right" >Job Id:</td><td><input type="text" class="tw_form" name="ordJobId" id="ordJobId"  value="<c:out value="${order.ordJobId}" />" /> </td>
         <td nowrap align="right" >Job Name:</td><td><input type="text" class="tw_form" name="ordJobName" id="ordJobName"  value="<c:out value="${order.ordJobName}" />" /></td>
    </tr>
    <tr id="trOpcionales2" style="display:block">
        <td nowrap align="right" >Job Description:</td>
        <td colspan="9"><textarea  rows="3" cols="109"   onkeyup="limitarArea()" class="tw_form" name="ordJobDescription" id="ordJobDescription" ><c:out value="${order.ordJobDescription}" /></textarea></td>
    </tr>    
    <tr>
        <td colspan="10">
            <table align="center" width="50%">
            <tr>
                <th colspan=3 align="center" style="font-size:12px;"><b>Seleccione los servicios</b></th>
            </tr>
            <tr>
                <td style="font-size:12px;" align="right"><b>Servicios disponibles</b></td>
                <td></td>
                <td style="font-size:12px;"  align="left"><b>Servicios asigandas</b></td>
            </tr>
            <tr>
               <td rowspan=3 align="right">
                   <select multiple="multiple" size="4"  name="listaServices" id="listaServices" style="border:solid 1px #005C8D;width:200px; font-size: 9px;" onfocus="javascript:this.style.background='#FFFFFF';">                                            
                    <c:forEach items="${listaService}" var="item">
                        <option value="<c:out value="${item.rtyName}" />" style="background-color:#A4BAC7;">
                            <c:out value="${item.rtyAlternativeName}" />
                        </option> 
                    </c:forEach>
                  </select>      
               </td>
               <td align="center">
                    <table>
                      <tr>
                        <td><a href="javascript: asignar();"><img style="border:0;" title="Asignar" alt=">" width="17" height="17" src="img/play.png" onmouseover="this.style.cursor='hand';"></img></a></td>
                      <tr>
                        <td><a href="javascript: desAsignar();"><img style="border:0;" title="Desasignar" alt="<" width="17" height="17" src="img/back.png" onmouseover="this.style.cursor='hand';"></img></a></td>
                      </tr>
                      <tr>
                        <td><a href="javascript: desAsignarTodo();"><img style="border:0;" title="Desasignar todo" alt="|<" width="17" height="17" src="img/player_start.png" onmouseover="this.style.cursor='hand';"></img></a></td>
                      </tr>
                    </table>
                </td>
                <td rowspan=3 align="left">
                    <select name="listaItemsSelect" id="listaItemsSelect" size="4" multiple="multiple" style="border:solid 1px #005C8D;width:200px; font-size: 9px;" >
                    <c:forEach items="${order.servicesTOList}" var="item">
                           <option value="<c:out value="${item.rtyName}" />" title="<c:out value="${item.rtyName}"/>">
                             <c:out value="${item.rtyAlternativeName}" />
                          </option> 
                    </c:forEach>
                    </select>
                </td>
             </tr>
            </table>       
        </td>
    </tr> 
    <tr>
        <td colspan="10" style="padding:0;spacing:0" >
         <br>   
         <hr class="tw_hr">
         <c:set scope="request"     var="ratesTOList" value="${order.orderRatesTOList}" /> 
         <jsp:include page="/WEB-INF/jspIncludes/agregartarifasOrden.jsp"/>
        </td>
    </tr>
    <tr>
         <td colspan="10" style="padding:0;spacing:0" >
         <br>   
         <hr class="tw_hr">
         <c:set scope="request"     var="docList" value="${order.ordersDocsTOList}" /> 
          <jsp:include page="/WEB-INF/jspIncludes/agregardocumentos.jsp"/>
         </td>
    </tr>
</table>
 <br>   
 <hr class="tw_hr">        
<table width="100%">
  <tr>
      <td align="right"><input type="button" id="aceptar" value="Aceptar" onclick="agregar()"/></td>   
      <td align="center" width="20px">
        <c:if test="${not empty order.ordId && order.ordId != ''}">
          <input type="button" id="proyecto" value="Proyecto" onclick="window.location.href='proyectos?ordId=<c:out value="${order.ordId}" />'" />
        </c:if>
      </td>   
      <td align="left"><input type="button" id="cancel" value="Limpiar" OnClick="cancelar()"/></td>    
  </tr>
</table>
</form>
</body>

 <c:out value="${script}" escapeXml="false"/>

</html>
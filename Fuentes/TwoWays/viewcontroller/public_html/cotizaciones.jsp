<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <link href="./twoways.css" rel="stylesheet" type="text/css"/>
    <script  type='text/javascript' src="./js/cotizacion.js"></script>
    <script type='text/javascript' src="./js/utils.js"></script>    
    <script type='text/javascript' src='/twoways/dwr/interface/towaysDWR.js'></script>
    <script type='text/javascript' src='/twoways/dwr/engine.js'></script>
    <script type='text/javascript' src='/twoways/dwr/util.js'></script>
    <script type='text/javascript' src="./js/commons.js"></script>  
    <script type='text/javascript' src="./js/CalendarPopup.js"></script>
    <script>
     writeSource('jscallDesde');
     </script>
    <title>Cotizaciones</title>
  </head>
  <body>
  <jsp:include page="/WEB-INF/jspIncludes/menu.jsp" />
  <c:out value="${mensaje}" escapeXml="false"/>
  <form name="cotizacion" action="cotizaciones" method="POST">
  <input type="hidden" id="accion" name="accion" value=""/>
  <table width="100%">
  <tr>
    <th colspan="2" class="tw_form">Ingrese los campos con los datos de la Moneda</th>
  </tr>
  <tr>
  </tr>
  <tr>
    <td align="right" width="50%">Fecha:</td>
    <td align="left" width="50%"><input type="text" class="tw_form" name="cotDate"  id="cotDate" size="20" maxsize="20" value="<c:out  value="${auxDate}"/>" /><div id="divDesde" style="background:#FFFFFF;position:absolute"  ></div> <img  onclick="cal1Desde.select(document.forms[0].cotDate,'selDesde','dd/MM/yyyy'); return false;" NAME="selDesde" ID="selDesde"  height="20" width="20" alt="seleccion" src="img/cal.png" onmouseover="this.style.cursor='hand';"></img></td>    
  </tr>  
  <tr>
    <td nowrap align="right" width="50%">Moneda:</td>
    <td align="left" width="50%">
       <select name="listaMoneda" id="listaMoneda" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';" onchange="buscarCotizaciones()";>            
            <option value="" selected="selected">Seleccionar</option>
                <c:forEach items="${listaMoneda}" var="item">
                    <option value="<c:out value="${item.curId}" />" style="background-color:#A4BAC7;">
                        <c:out value="${item.curName}" />
                    </option>
                </c:forEach>
       </select>
    </td> 
  </tr>
  <tr>
    <td nowrap align="right" width="50%">Valor:</td>
    <td align="left" width="50%"><input type="text" class="tw_form" id="cotValue" name="cotValue"  size="10" maxlength="10"  onfocus="javascript:this.style.background='#FFFFFF';"></input></td>
  </tr>
  </table>
  <br>
  <hr class="tw_hr">
  <table width="25%" align="center">
  <tr>
      <td align="right"><input type="button" id="aceptar" value="Aceptar" onclick="agregar()"/></td>   
      <td align="center"><input type="button" id="cancel" value="Limpiar" OnClick="cancelar()"/></td>   
  </tr>
  </table>
  <div id="div-cotizaciones" style="display:none" align="center">
  <br>
  <hr class="tw_hr">
  <h2 class="tw">Cotizaciones anteriores</h2>
  <table id ="tabla-busqueda">
    <tr><th>Moneda</th><th>Fecha</th><th>Valor</th><th>&nbsp;</th></tr>
  </table>
  </div>  
  </form>
  </body>
</html>
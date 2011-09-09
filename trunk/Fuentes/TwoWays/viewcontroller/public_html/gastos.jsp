<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
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
    <title>Gastos/Ingresos</title>
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
  <input type="hidden" id="mesId" name="mesId" value="<c:out value="${mesId}"/>"/>
  <input type="hidden" id="anioId" name="anioId" value="<c:out value="${anioId}"/>"/>     
  <table width="100%">
  <thead>
  <tr>
    <th colspan="3" align="center" class="tw_form">Ingrese los campos con los datos de los Gastos/Ingresos</th>
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
      <table>
      <tr>
        <td>
          <table  width="150px"  cellpadding="0" cellspacing="0">
          <thead>
           <tr>
            <td colspan="100%" style="font-size:1.1em;padding-top:5px;padding-bottom:4px;background-color:#80211D;color:#ffffff;" >Filtros de búsqueda</td>
            <td style="font-size:1.1em;padding-top:5px;padding-bottom:4px;background-color:#80211D;color:#ffffff;" ><div style="background-color:Gray;width:20;height:20" onclick="document.getElementById('table-filtros').style.display='none';document.getElementById('mostrar-filtro').style.display='block'" onmouseover="this.style.cursor='hand';" >X</div></td> 
           </tr>
          </thead>
          <tbody>
           <tr>
            <td nowrap align="left" width="50%" >Mes</td>
            <td align="left" width="50%" >
                    <select name="listaMes" id="listaMes" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';"> 
                        <option value="01" >Enero</option>
                        <option value="02" >Febrero</option>
                        <option value="03" >Marzo</option>
                        <option value="04" >Abril</option>
                        <option value="05" >Mayo</option>
                        <option value="06" >Junio</option>
                        <option value="07" >Julio</option>
                        <option value="08" >Agosto</option>
                        <option value="09" >Septiembre</option>
                        <option value="10" >Octubre</option>
                        <option value="11" >Noviembre</option>
                        <option value="12" >Diciembre</option>            
                    </select>
                    <c:if test="${mesId != null}">
                        <script type="text/javascript">
                            for (var i=1; i < 12; i++){
                                if (document.getElementById('listaMes').options[i].value =="<c:out value="${mesId}"/>"){
                                    document.getElementById('listaMes').selectedIndex=i;
                                }
                            }
                        </script>
                    </c:if>
            </td>
         </tr>
         <tr>
            <td nowrap align="left" width="50%" >Año</td>
            <td align="left" width="50%">
                <select name="listaAnio" id="listaAnio" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';" > 
                    <option value="2011" >2011</option>
                    <option value="2012" >2012</option>
                    <option value="2013" >2013</option>
                    <option value="2014" >2014</option>
                    <option value="2015" >2015</option>
                    <option value="2016" >2016</option>
                    <option value="2017" >2017</option>
                    <option value="2018" >2018</option>
                    <option value="2019" >2019</option>
                    <option value="2020" >2020</option>
                
                </select>
                <c:if test="${anioId != null}">
                    <script type="text/javascript">
                        for (var i=1; i < 10; i++){
                            if (document.getElementById('listaAnio').options[i].value =="<c:out value="${anioId}"/>"){
                                document.getElementById('listaAnio').selectedIndex=i;
                            }
                        }
                    </script>
                </c:if>
            </td>
          </tr>
          <tr>
            <td align="center" colspan="100%" ><input type="button" size="5" value="Buscar" id="BuscarBoton" onClick="BuscarItemFecha()"/>
            </td>
          </tr>
        </tbody>
        </table>
       </td>
     </tr>
     </table>
     </div>
   </td>
   <td align="left" width="100%" valign="middle" >
     <table id="tabla-gastos" align="center" width="100%">
    <!-- <tr>
     <td>  
      <table id="tabla-gastos-head" align="center" width="100%">-->
      <thead>
      <tr bgcolor="#E8B6B5">
        <th nowrap width="10%" align="center">Fecha</th>      
        <th width="5%" align="center">Tipo Item</th>
        <th nowrap width="30%" align="center">Seleccione Item</th>
        <th width="15%" align="center">Seleccione Moneda</th>
        <th width="15%" align="center">Ingrese Monto</th>
        <th width="13%" align="center">Seleccione Cuenta</th>
        <th width="10%" align="center">Responsable</th>
        <th width="2%">Agregar</th>
      </tr>
      </thead>
      <tbody>
     <tr>   
            <td nowrap width="15%" align="center"  style ="background-color:#F8E0E0;color:#585858;align:left">
                  <input type="text" class="tw_form" id="expFecha" name="expFecha"   value="<c:out  value="${auxDate}"/>" size="10" maxlength="10" onfocus="javascript:this.style.background='#FFFFFF';" onblur="actualizarListaGastos()"></input>
                  <div id="divDesde" style="background:#FFFFFF;position:absolute"  ></div> 
                  <img  onclick="cal1Desde.select(document.forms[0].expFecha,'selDesde','dd/MM/yyyy'); return false;actualizarListaGastos();" NAME="selDesde" ID="selDesde"  height="20" width="20" alt="seleccion" src="img/cal.png" onmouseover="this.style.cursor='hand';" ></img>
            </td>
            <td width="15%" align="center"  style ="background-color:#F8E0E0;color:#585858;align:left">       
               <select name="tipoItem" id="tipoItem" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';" onchange="createDynamicDropdown('tipoItem', 'listaItemsAux','listaItems')">     
                        <option value="" >Seleccionar</option>
                        <option value="Gastos" >Gastos</option>
                        <option value="Ingresos" >Ingresos</option>
               </select> 
            </td> 
            <td width="30%" align="center"  style ="background-color:#F8E0E0;color:#585858;align:left">
               <select name="listaItemsAux" id="listaItemsAux" style="display:none;">            
                <option value="-1"></option>
                    <c:forEach items="${listaItemsAux}" var="item">
                        <option name="<c:out value="${item.itmType}" />" value="<c:out value="${item.itmId}" />#<c:out value="${item.itmName}" />" style="background-color:#A4BAC7;">
                            <c:out value="${item.itmName}" />
                        </option>
                    </c:forEach>
               </select> 
               <select name="listaItems" id="listaItems" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';">
                     <option value="-1">Cargando...</option>                   
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
            <td width="15%" align="center"  style ="background-color:#F8E0E0;color:#585858;align:left">
                <input type="text" size="7" id="expMonto" name="expMonto" class="tw_form" onfocus="javascript:this.style.background='#FFFFFF';" style="text-align:right;"></input>
            </td>
            <td width="13%" align="center"  style ="background-color:#F8E0E0;color:#585858;align:left">
               <select name="listaCuentas" id="listaCuentas" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';">                
                        <option value="" >Seleccionar</option>
                        <c:forEach items="${listaCuentas}" var="item">
                            <option value="<c:out value="${item.accId}" />#<c:out value="${item.accName}" />" style="background-color:#A4BAC7;">
                                <c:out value="${item.accName}" />
                            </option>
                        </c:forEach>
               </select> 
            </td>
            <td width="10%" align="center"  style="background-color:#F8E0E0;color:#585858;align:left">
                <input readonly size="15" type="text" id="expUsuario" name="expUsuario" class="tw_form" onfocus="javascript:this.style.background='#FFFFFF';" value="<c:out value="${expUsuario}" />" ></input>
                <input type="hidden" id="iteId" name="iteId" value=""/>   
            </td>
            <td width="2px" align="center"  style ="background-color:#F8E0E0;color:#585858;align:left">
                <img  src="img/Add.png" alt=">" width="20" height="20" title="Agregar Item de Gasto" onclick="agregarItemGasto()" onmouseover="this.style.cursor='hand';"/>
            </td>
          </tr>
         </tbody>
         </table>
        </td>
        </tr>
        <tr>
          <td >&nbsp;</td>
            <td>
                <table id="tabla-gastos-body" width="100%" bgcolor="grey">
                <tr>
                    <td>
                        <div class="fixedHeaderTableGastos">
                        <table id="list-gastos-body" align="center" width="100%" cellSpacing="1" >
                        <thead >     
                        <tr style="display:block; background-color:'transparent';">
                            <th nowrap width="15%" align="center" style="border:solid 1px #FFFFFF;">Fecha</th>
                            <th nowrap width="30%" align="center" style="border:solid 1px #FFFFFF;">Nombre del Item</th>
                            <th width="15%" align="center" style="border:solid 1px #FFFFFF;">Moneda</th>
                            <th width="15%" align="center" style="border:solid 1px #FFFFFF;">Monto</th>
                            <th width="13%" align="center" style="border:solid 1px #FFFFFF;">Cuenta</th>
                            <th width="10%" align="center" style="border:solid 1px #FFFFFF;">¿Quién lo cargó?</th>
                            <th width="2%" align="center" style="border:solid 1px #FFFFFF;">Opciones</th>
                        </tr>
                        </thead>  
                        <tbody>
                            <c:set scope="page" var="color_row" value="${'#E8B6B5'}" />
                            <c:forEach items="${itemsExpense}" var="item">
                            <c:choose>
                             <c:when test="${item[\'ITM_TYPE\'] == 'Gastos'}">  
                                  <c:set scope="page" var="color_row" value="${'#d24444'}" />
                                </c:when>        
                                <c:otherwise>
                                  <c:set scope="page" var="color_row" value="${'#1cb874'}" /> 
                                </c:otherwise>
                            </c:choose>
                            <tr name="item-gastos" id="<c:out value="${item[\'ITE_ID\']}" />" bgcolor="<c:out value="${color_row}"/>" > 
                                <td width="15%" ><fmt:formatDate value="${item[\'EXP_DATE\']}" pattern="dd/MM/yyyy" /></td>
                                <td width="30%" ><c:out value="${item[\'ITM_NAME\']}" />
                                  <input type="hidden" name="item-gasto-hidden"  value="<c:out value="${item[\'ITM_ID\']}" />#<c:out value="${item[\'CUR_ID\']}" />#<c:out value="${item[\'ITE_VALUE\']}" />#<c:out value="${item[\'ACC_ID\']}" />#<c:out value="${item[\'USR_ID\']}" />#<c:out value="${item[\'ITE_ID\']}" />"</td>
                                <td width="15%" ><c:out value="${item[\'CUR_NAME\']}" /></td>
                                <td width="15%" align="right"><c:out value="${item[\'ITE_VALUE\']}" /></td>
                                <td width="13%" ><c:out value="${item[\'ACC_NAME\']}" /></td>
                                <td width="10%" ><c:out value="${item[\'USR_ID\']}" /></td>                                   
                                <td width="2%">
                                    <img  src="img/del2.png" height="15" width="15"  alt="Eliminar" onclick="eliminarItemExp('<c:out value="${item[\'ITE_ID\']}" />')" onmouseover="this.style.cursor='hand';" />    <img  src="img/Edit2.png" height="15" width="15"  alt="Editar Item de Gasto" onclick="editarItemExp('<c:out value="${item[\'ITM_ID\']}"/>#<c:out value="${item[\'ITM_NAME\']}"/>*#<c:out value="${item[\'CUR_ID\']}"/>#<c:out value="${item[\'CUR_NAME\']}"/>*#<c:out value="${item[\'ITE_VALUE\']}"/>*#<c:out value="${item[\'ACC_ID\']}"/>#<c:out value="${item[\'ACC_NAME\']}"/>*#<c:out value="${item[\'USR_ID\']}"/>*#<c:out value="${item[\'ITE_ID\']}" />*#<c:out value="${item[\'ITM_TYPE\']}" />')" onmouseover="this.style.cursor='hand';" /></td>
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
        </tbody>
      </table>
      </td>
     </tr>
      </tbody>
    </table>

<br>
  <hr class="tw_hr">
  <table width="25%" align="center">
      <tr>
          <td align="right"><input type="button" id="aceptar" value="Aceptar" onclick="agregar()"/></td>   
          <td align="left"><input type="button" id="cancel" value="Limpiar" OnClick="cancelar()"/></td>   
          <!--<td align="left"><input type="button" id="eliminar" value="Eliminar" OnClick="eliminarGasto('<c:out value="${auxExpId}"/>')"/></td>-->
      </tr>
  </table>
  </form>
  </body>
</html>
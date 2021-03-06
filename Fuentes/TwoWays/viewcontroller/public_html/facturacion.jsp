<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ page errorPage="error.jsp" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <link href="./twoways.css" rel="stylesheet" type="text/css"/>   <link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">     <link rel="icon" type="image/x-icon" href="img/favicon.ico"> 
    
    <script  type='text/javascript' src="./js/facturacion.js"></script>
    <script type='text/javascript' src="./js/utils.js"></script>    
    <script type='text/javascript' src='/twoways/dwr/interface/towaysDWR.js'></script>
    <script type='text/javascript' src='/twoways/dwr/engine.js'></script>
    <script type='text/javascript' src='/twoways/dwr/util.js'></script>
    <script type='text/javascript' src="./js/commons.js"></script>  
    <script type='text/javascript' src="./js/CalendarPopup.js"></script>    
    <title>Facturacion</title>
  </head>
  <body >
  <jsp:include page="/WEB-INF/jspIncludes/menu.jsp" />
  <c:out value="${mensaje}" escapeXml="false"/>
  <form name="facturacion" action="facturacion" method="POST">
  <input type="hidden" id="accion" name="accion" value=""/>  
  <input type="hidden" id="facturar" name="facturar" value="<c:out value="${facturar}"/>"/> 
  <input type="hidden" id="invId" name="invId" value="<c:out value="${invId}"/>"/>
  <input type="hidden" id="cliId" name="cliId" value="<c:out value="${cliId}"/>"/>
  <input type="hidden" id="accId" name="accId" value="<c:out value="${accId}"/>"/>
  <input type="hidden" id="invoiceId" name="invoiceId" value="<c:out value="${invoiceId}"/>"/>  
  <input type="hidden" id="mesId" name="mesId" value="<c:out value="${mesId}"/>"/>
  <input type="hidden" id="anioId" name="anioId" value="<c:out value="${anioId}"/>"/>   
  <input type="hidden" id="curSymbol" name="curSymbol" value="<c:out value="${curSymbol}"/>"/>
  <table width="100%" align="center">
      <thead>
      <tr>
        <th colspan="2" class="tw_form">Ingrese los campos con los datos del cobro a realizar</th>
      </tr>
      </thead>
      <tbody>
      <tr>
      </tr>
      <tr>
          <td id="mostrar-filtro" style="display:none" valign="top" ><img src="img/filter.png" width="20" height="20" alt="Filtros"  onclick="document.getElementById('table-filtros').style.display='block';document.getElementById('mostrar-filtro').style.display='none'" onmouseover="this.style.cursor='hand';"></img>
          </td>
          <td id="table-filtros" valign="top" align="left"  >  
              <div style="border: 1px solid;border-color:#FFFFFf;padding:2;spacing:2" >
               <table >
                   <tr>
                    <td>
                       <table  width="200px"  cellpadding="0" cellspacing="0">
                           <thead >
                                <tr ><td colspan="100%" style="font-size:1.1em;padding-top:5px;padding-bottom:4px;background-color:#80211D;color:#ffffff;" >Filtros de b�squeda</td><td style="font-size:1.1em;padding-top:5px;padding-bottom:4px;background-color:#80211D;color:#ffffff;" ><div style="background-color:Gray;width:20;height:20" onclick="document.getElementById('table-filtros').style.display='none';document.getElementById('mostrar-filtro').style.display='block'" onmouseover="this.style.cursor='hand';" >X</div></td> </tr>
                           </thead>
                           <tbody>
                               <tr>
                                <td nowrap align="left" width="50%">Cliente</td>
                                <td align="left" width="50%">
                                <c:choose>
                                    <c:when test="${not empty finishedOrders}">
                                        <select disabled="disabled" name="listaClientes" id="listaClientes" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';">            
                                    </c:when>
                                    <c:otherwise>
                                        <select name="listaClientes" id="listaClientes" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';">            
                                    </c:otherwise>
                                </c:choose>
                                        <option value="" selected="selected">Seleccionar</option>
                                        <c:forEach items="${listaClientes}" var="item">
                                           <c:choose>
                                            <c:when test="${cliId == item.cliId}">
                                               <option value="<c:out value="${item.cliId}" />" selected="selected">
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
                             </tr>
                             <tr>
                                <td nowrap align="left" width="25%">Mes de pago</td>
                                <td align="left" width="25%">
                                <c:choose>
                                    <c:when test="${not empty finishedOrders}">                        
                                        <select disabled="disabled" name="listaMes" id="listaMes" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';"> 
                                    </c:when>
                                    <c:otherwise>
                                        <select name="listaMes" id="listaMes" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';"> 
                                    </c:otherwise>
                                </c:choose>
                                        <option value="" selected="selected">Seleccionar</option>
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
                                            for (var i=1; i < 13; i++){
                                                if (document.getElementById('listaMes').options[i].value =="<c:out value="${mesId}"/>"){
                                                    document.getElementById('listaMes').selectedIndex=i;
                                                }
                                            }
                                        </script>
                                    </c:if>
                                </td>
                              </tr>
                              <tr>
                                <td nowrap align="left" width="25%">A�o de pago</td>
                                <td>
                                <c:choose>
                                    <c:when test="${not empty finishedOrders}">                                             
                                        <select disabled="disabled" name="listaAnio" id="listaAnio" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';"> 
                                    </c:when>
                                    <c:otherwise>
                                        <select name="listaAnio" id="listaAnio" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';" > 
                                    </c:otherwise>
                                </c:choose>
                                        <option value="" selected="selected">Seleccionar</option>
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
                                            for (var i=1; i < 11; i++){
                                                if (document.getElementById('listaAnio').options[i].value =="<c:out value="${anioId}"/>"){
                                                    document.getElementById('listaAnio').selectedIndex=i;
                                                }
                                            }
                                        </script>
                                    </c:if>
                                </td>
                             </tr>
                             <tr>
                               <c:choose>
                                    <c:when test="${not empty finishedOrders}">
                                        <td width="50%"  valign="top" align="right" ><input disabled="disabled" type="button" value="Buscar" onclick="buscarOrdenes()"  /></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td width="50%"  valign="top" align="right" ><input type="button" value="Buscar" onclick="buscarOrdenes()"  /></td>
                                    </c:otherwise>
                                </c:choose>
                               <td width="50%"  valign="top" align="left" ><input type="button" value="Nueva B�squeda" onclick="nuevaBusqueda()"  /></td>                     
                             </tr>       
                           </tbody>
                       </table>        
                      </td>
                    </tr>
                </table>
               </div>
            </td>
            <td align="left" width="100%" valign="top" >
               <div class="fixedHeaderTablePago">
                 <table id="tabla-ordenes" name="tabla-ordenes" cellpadding="0" cellspacing="1" align="center" width="100%" hight="100%">
                 <thead align="center">     
                   <!--<tr style="display:block; background-color='transparent';" align="center">-->
                   <tr style="background-color='transparent';" align="center">
                        <th width="10%" bgcolor="#80211D">Nombre orden</th>
                        <th width="10%" bgcolor="#80211D">Nombre responsable</th>                        
                        <th width="10%" bgcolor="#80211D">Fecha entrega</th>
                        <th width="10%" bgcolor="#80211D">WO number</th>
                        <th width="10%" bgcolor="#80211D">JOB ID</th>
                        <th width="10%" bgcolor="#80211D">JOB Name</th>
                        <th width="10%" bgcolor="#80211D">Tipo Tarifa</th>
                        <th width="10%" bgcolor="#80211D">Monto Tarifa</th>
                        <th width="10%" bgcolor="#80211D">Unidades</th>                        
                        <th width="10%" bgcolor="#80211D">Total orden</th>
                        <th width="2%" bgcolor="#80211D"></th>
                    </tr>
                 </thead>  
                 <c:choose   >
                   <c:when test="${not empty finishedOrders}">
                 <tbody align="center">
                  <c:set var="bandera1" value="1"/>
                   <c:set var="subtotal" value="0.0"/>
                   <c:forEach items="${finishedOrders}" var="item">                                           

                       <c:if test="${(bandera != item[\'ORDID\']) && (bandera1 == 0)}">
                          <tr id="rowsubT+<c:out value="${bandNom}"/>">
                            <td colspan="9" bgcolor="#CCCCCC" style="text-align:right;">Subtotal <c:out value="${bandNom}"/>:</td>
                            <td bgcolor="#CCCCCC" style="text-align:right;">
                               <input type="text" id="subT+<c:out value="${bandNom}"/>" value="<c:out value="${subtotal}"/>" readonly="readonly" /></td>
                            
                            <c:set var="subtotal" value="0.0"/>
                            <c:set var="bandera1" value="1"/>                            
                          </tr>
                        </c:if>                          
                           <c:set var="subtotal" value="${subtotal + item[\'ORDTOTAL\']}"/>
                           <c:set var="bandera" value="${item[\'ORDID\']}"/>  
                           <c:set var="bandNom" value="${item[\'ORDNAME\']}"/>
                           <c:set var="bandera1" value="0"/>
                           
                           <tr name="item-idiomas" id="<c:out value="${item[\'ORDID\']}"/><c:out value="${item[\'RATID\']}"/>" >
                                <td width="10%" bgcolor="#FFFFF"><a href="ordentrabajo?ordId=<c:out value="${item[\'ORDID\']}" />" ><c:out value="${item[\'ORDNAME\']}" /></a></td>
                                <td width="10%" bgcolor="#FFFFF"><c:out value="${item[\'CRENAME\']}" />  <c:out value="${bandera}"/></td>                         
                                <td width="10%" bgcolor="#FFFFF"><fmt:formatDate value="${item[\'ORDFINISHDATE\']}"  pattern="dd/MM/yyyy" />
                                   <input type="hidden" name="item-ordenes-hidden"   value="<c:out value="${item[\'ORDID\']}" />#<c:out value="${item[\'RATID\']}"/>#<c:out value="${item[\'CURID\']}" />#<c:out value="${item[\'ORDTOTAL\']}" />"></input>
                                   <!--<input type="hidden" name="print-ordenes-hidden"  value="<c:out value="${item[\'ORDPROJID\']}" />#<c:out value="${item[\'ORDJOBID\']}" />#<c:out value="${item[\'ORDWONUMBER\']}"/>#<c:out value="${item[\'ORDJOBNAME\']}"/>#<c:out value="${item[\'ORDJOBDESCRIPTION\']}" />#<c:out value="${item[\'ORRWCOUNT\']}"/>#<c:out value="${item[\'CURSYMBOL\']}" /> <c:out value="${item[\'ORRVALUE\']}"/>#<c:out value="${item[\'ORDTOTAL\']}"/>#<c:out value="${item[\'CRENAME\']}" />"</input>--></td>     
                                <td width="10%" bgcolor="#FFFFF"><c:out value="${item[\'ORDWONUMBER\']}" /></td> 
                                <td width="10%" bgcolor="#FFFFF"><c:out value="${item[\'ORDJOBID\']}" /></td>  
                                <td width="10%" bgcolor="#FFFFF"><c:out value="${item[\'ORDJOBNAME\']}" /></td>       
                                <td width="10%" bgcolor="#FFFFF"><c:out value="${item[\'RATETYPENAME\']}" />-<c:out value="${item[\'RATNAME\']}" /></td>                        
                                <td width="10%" bgcolor="#FFFFF" style="text-align:right;"><c:out value="${item[\'CURSYMBOL\']}" /> <c:out value="${item[\'ORRVALUE\']}" /></td> 
                                <td width="10%" bgcolor="#FFFFF"><c:out value="${item[\'ORRWCOUNT\']}" /></td>                         
                                <td width="10%" bgcolor="#FFFFF" style="text-align:right;"><c:out value="${item[\'ORDTOTAL\']}" /></td>
                                <td width="2%">
                                    <c:if test="${accion !='imprimir'}">
                                        <img  src="img/del2.png" height="15" width="15"  alt="Eliminar" onclick="eliminarOrden('<c:out value="${item[\'ORDID\']}"/><c:out value="${item[\'RATID\']}"/>','<c:out value="${item[\'ORDTOTAL\']}" />','<c:out value="${item[\'CURID\']}" />','<fmt:formatDate value="${item[\'ORDFINISHDATE\']}"  pattern="dd/MM/yyyy HH:mm" />','<c:out value="${item[\'ORDNAME\']}" />')" onmouseover="this.style.cursor='hand';" />
                                    </c:if>
                                </td>
                             </tr> 

                    
                  </c:forEach>
                  <tr id="rowsubT+<c:out value="${bandNom}"/>">
                    <td colspan="9" bgcolor="#CCCCCC" style="text-align:right;">Subtotal <c:out value="${bandNom}"/>:</td>
                    
                    <td bgcolor="#CCCCCC" style="text-align:right;">
                        
                        <input type="text" id="subT+<c:out value="${bandNom}"/>" value="<c:out value="${subtotal}"/>" readonly="readonly" /></td>
                  </tr>
                  </tbody>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${not empty accion}">
                                <tbody>
                                    <tr><td colspan="100%">No existen ordenes para el cliente. Realice una nueva b�squeda.</td></tr> 
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
               </div>
            </td>
        </tr>
    </tbody>
  </table>
 <c:choose>
   <c:when test="${not empty finishedOrders}">
        <table id="datos-extra" align="center" width="60%">
   </c:when>
   <c:otherwise>
        <table id="datos-extra" align="center" width="60%" style="display:none">
   </c:otherwise>
 </c:choose>
      <tr>
        <td nowrap align="right" width="25%">Fecha:</td>
        <td align="left" width="25%">
            <c:choose>
                <c:when test="${not empty invoiceDate}">
                    <input readonly type="text" class="tw_form" id="invDate" name="invDate"   value="<c:out  value="${invoiceDate}"/>" size="10" maxlength="10" onfocus="javascript:this.style.background='#FFFFFF';"></input>
                </c:when>
                <c:otherwise>
                    <input type="text" class="tw_form" id="invDate" name="invDate"   value="<c:out  value="${auxDate}"/>" size="10" maxlength="10" onfocus="javascript:this.style.background='#FFFFFF';"></input><div id="divDesde" style="background:#FFFFFF;position:absolute"  ></div> <img  onclick="cal1Desde.select(document.forms[0].invDate,'selDesde','dd/MM/yyyy'); return false;" NAME="selDesde" ID="selDesde"  height="20" width="20" alt="seleccion" src="img/cal.png" onmouseover="this.style.cursor='hand';"></img>
                </c:otherwise>
            </c:choose>
        </td> 
        <td nowrap align="right" width="10%">Cuenta:</td>        
        <td width="20%" align="left">
           <c:choose>
                <c:when test="${not empty accId}">
                   <select name="listaCuentas" id="listaCuentas" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';">                
                        <c:forEach items="${listaCuentas}" var="item">
                        <c:choose>
                            <c:when test="${item.accId == accId}">
                                <option value="<c:out value="${item.accId}" />" style="background-color:#A4BAC7;" selected="selected">
                                    <c:out value="${item.accName}" />
                                </option>
                            </c:when>
                        </c:choose>
                        </c:forEach>
                   </select> 
                </c:when>
                <c:otherwise>
                   <select name="listaCuentas" id="listaCuentas" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';">                
                        <option value="" >Seleccionar</option>
                        <c:forEach items="${listaCuentas}" var="item">
                            <option value="<c:out value="${item.accId}" />" style="background-color:#A4BAC7;">
                                <c:out value="${item.accName}" />
                            </option>
                        </c:forEach>
                   </select> 
                </c:otherwise>
            </c:choose>
        </td>    
        <td nowrap align="right" width="10%">Moneda:</td>
        <td width="20%" align="leftx">
            <c:choose>
                <c:when test="${not empty curId}">
                   <select name="listaMoneda" id="listaMoneda" style="border:solid 1px #005C8D;" onfocus="guardarValor(this.value)" onchange="cambioValorTotal(this.value)">                                                        
                        <c:forEach items="${listaMoneda}" var="item">
                            <c:choose>
                                <c:when test="${item.curId == curId}">
                                    <option value="<c:out value="${item.curId}" />#<c:out value="${item.curSymbol}" />" style="background-color:#A4BAC7;" selected="selected">
                                        <c:out value="${item.curName}" />
                                    </option>
                                </c:when>                                   
                            </c:choose>
                        </c:forEach>
                   </select>
                </c:when>
                <c:otherwise>
                   <select name="listaMoneda" id="listaMoneda" style="border:solid 1px #005C8D;" onfocus="guardarValor(this.value)" onchange="cambioValorTotal(this.value)">                                    
                            <c:forEach items="${listaMoneda}" var="item">
                                <c:choose>
                                    <c:when test="${item.curId == 4}">
                                        <option value="<c:out value="${item.curId}" />#<c:out value="${item.curSymbol}" />" style="background-color:#A4BAC7;" selected="selected">
                                            <c:out value="${item.curName}" />
                                        </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="<c:out value="${item.curId}" />#<c:out value="${item.curSymbol}" />" style="background-color:#A4BAC7;">
                                            <c:out value="${item.curName}" />
                                        </option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                   </select>
                </c:otherwise>
            </c:choose>
        </td>
        <td nowrap align="right" width="10%"><b>Total:</b></td>
        <td width="20%" align="left">
            <c:choose>
                <c:when test="${not empty invoiceTotal}">
                     <input readonly type="text" class="tw_form" id="invTotal"  name="invTotal" value="<c:out value="${invoiceTotal}" />" size="10" maxlength="15" style="text-align:right;"></input>
                     <input type="hidden" name="print-total-hidden"  value="<c:out value="${invoiceTotal}" />"></input>
                </c:when>
                <c:otherwise>
                     <input readonly type="text" class="tw_form" id="invTotal"  name="invTotal" value="<c:out value="${invTotal}" />" size="10" maxlength="15" style="text-align:right;"></input>
                     <input type="hidden" name="print-total-hidden"  value="<c:out value="${invTotal}" />"></input>
                </c:otherwise>
            </c:choose>
             <input type="hidden" name="curIdOrigen" id="curIdOrigen" value="<c:out value="${curIdOrigen}" />">
        </td>
      </tr>
      <c:if test="${accion =='imprimir'}">
          <tr>
            <td colspan="8" width="100%" align="center">
                <textarea rows="5" cols="50" name="datosAdicionales" id="datosAdicionales">
                    CUIT: 27-25613979-6
                    CUIT: 27-26555803-3
                </textarea>
            </td>
         </tr>
      </c:if>
  </table>
  <hr class="tw_hr">
      <table width="20%" align="center">
          <tr>
              <td align="right">
                <c:choose>
                    <c:when test="${accion == 'imprimir'}">
                        <input disabled type="button" id="aceptar" value="Aceptar" onclick="cargar()"/>
                        <input type="button" id="imprimir" value="Imprimir" onclick="imprimirFactura(<c:out value="${invId}"/>,<c:out value="${cliId}"/>,'<c:out value="${invDate}"/>',<c:out value="${accId}"/>,'<c:out value="${curSymbol}"/>',<c:out value="${invTotal}" />)"/>
                    </c:when>
                    <c:when test="${accion == 'noFacturar'}">
                        <input disabled type="button" id="aceptar" value="Aceptar" onclick="cargar()"/>
                        <input disabled type="button" id="imprimir" value="Imprimir" onclick="imprimirFactura()"/>      
                    </c:when>
                    <c:otherwise>
                        <input type="button" id="aceptar" value="Aceptar" onclick="cargar()"/>
                        <input disabled type="button" id="imprimir" value="Imprimir" onclick="imprimirFactura()"/>
                    </c:otherwise>
                </c:choose>
                </td>   
              <td align="left"><input type="button" id="cancel" value="Limpiar" OnClick="cancelar()"/></td>   
          </tr>
      </table>
  </form>
  </body>
</html>
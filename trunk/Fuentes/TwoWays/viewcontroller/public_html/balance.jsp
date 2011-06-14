<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <link href="./twoways.css" rel="stylesheet" type="text/css"/>
    <script  type='text/javascript' src="./js/balance.js"></script>
      
    <title>Ingresos y Egresos</title>
  </head>
  <body>
    <jsp:include page="/WEB-INF/jspIncludes/menu.jsp" />
    <c:out value="${mensaje}" escapeXml="false"/>
    <form id="frmlistBalance" name="frmlistBalance" action="balance" method="POST">
    <input type="hidden"  name="accion"  id="accion"  value=""   />
    <input type="hidden" id="mesId" name="mesId" value="<c:out value="${mesId}"/>"/>
    <input type="hidden" id="anioId" name="anioId" value="<c:out value="${anioId}"/>"/>         
    
  <table width="100%" align="center">
      <thead>
      <tr>
        <th colspan="2" class="tw_form">Tabla de Ingresos/Egresos</th>
      </tr>
      </thead>
      <tbody>
      <tr>
      </tr>
      <tr>
        <c:choose>
            <c:when test="${not empty listaIngresos}">                                             
                <td id="mostrar-filtro" style="display:''" valign="top" ><img src="img/filter.png" width="20" height="20" alt="Filtros"  onclick="document.getElementById('table-filtros').style.display='block';document.getElementById('mostrar-filtro').style.display='none'" onmouseover="this.style.cursor='hand';"></img>
                 </td>
            </c:when>
            <c:otherwise>
                <td id="mostrar-filtro" style="display:none" valign="top" ><img src="img/filter.png" width="20" height="20" alt="Filtros"  onclick="document.getElementById('table-filtros').style.display='block';document.getElementById('mostrar-filtro').style.display='none'" onmouseover="this.style.cursor='hand';"></img>
                 </td>
            </c:otherwise>
        </c:choose>
 <!--         <td id="mostrar-filtro" style="display:none" valign="top" ><img src="img/filter.png" width="20" height="20" alt="Filtros"  onclick="document.getElementById('table-filtros').style.display='block';document.getElementById('mostrar-filtro').style.display='none'" onmouseover="this.style.cursor='hand';"></img>-->
        <c:choose>
            <c:when test="${not empty listaIngresos}">                      
                <td id="table-filtros" valign="top" align="left" style="display:none" > 
            </c:when>
            <c:otherwise>
                <td id="table-filtros" valign="top" align="left" style="display:''" > 
            </c:otherwise>
        </c:choose>
              <div style="border: 1px solid;border-color:#FFFFFf;padding:2;spacing:2" >
               <table >
                   <tr>
                    <td>
               <table  width="200px"  cellpadding="0" cellspacing="0">
                   <thead >
                        <tr ><td colspan="100%" style="font-size:1.1em;padding-top:5px;padding-bottom:4px;background-color:#80211D;color:#ffffff;" >Filtros de búsqueda</td><td style="font-size:1.1em;padding-top:5px;padding-bottom:4px;background-color:#80211D;color:#ffffff;" ><div style="background-color:Gray;width:20;height:20" onclick="document.getElementById('table-filtros').style.display='none';document.getElementById('mostrar-filtro').style.display='block'" onmouseover="this.style.cursor='hand';" >X</div></td> </tr>
                   </thead>
                   <tbody>

                      <tr>
                        <td nowrap align="left" width="25%">Mes</td>
                        <td align="left" width="25%">
                        <c:choose>
                            <c:when test="${not empty listaIngresos or not empty listaEgresos or not empty totalBeneficios}">                     
                                <select disabled="disabled" name="listaMes" id="listaMes" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';"> 
                            </c:when>
                            <c:otherwise>
                                <select name="listaMes" id="listaMes" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';"> 
                            </c:otherwise>
                        </c:choose>
                                <option value="0" selected="selected">Seleccionar</option>
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
                        <td nowrap align="left" width="25%">Año</td>
                        <td>
                        <c:choose>
                            <c:when test="${not empty listaIngresos or not empty listaEgresos or not empty totalBeneficios}">                                       
                                <select disabled="disabled" name="listaAnio" id="listaAnio" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';"> 
                            </c:when>
                            <c:otherwise>
                                <select name="listaAnio" id="listaAnio" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';" > 
                            </c:otherwise>
                        </c:choose>
                                <option value="0" selected="selected">Seleccionar</option>
                                <option value="2010" >2010</option>
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
                           <c:when test="${not empty listaIngresos or not empty listaEgresos or not empty totalBeneficios}">
                                <td width="50%"  valign="top" align="right" ><input disabled="disabled" type="button" id="buscar" value="Buscar" onclick="BuscarBalance()"  /></td>
                            </c:when>
                            <c:otherwise>
                                <td width="50%"  valign="top" align="right" ><input type="button" id="buscar" value="Buscar" onclick="BuscarBalance()"  /></td>
                            </c:otherwise>
                    </c:choose>
                       <td width="50%"  valign="top" align="left" ><input type="button" id="nueva" value="Nueva Búsqueda" onclick="nuevaBusqueda()"  /></td>                     
                     </tr>       
                   </tbody>
               </table>        
                      </td></tr></table>
               </div>
            </td>
            <td align="left" width="100%" valign="top" >
               <div class="fixedHeaderTableIngreso">
                 <table id="tabla-ingresos" name="tabla-ingresos" cellpadding="3" cellspacing="1" align="center" width="100%">
                 <thead align="center">     
                   <tr style="display:block; background-color='transparent';" align="center">
                        <th width="10%" bgcolor="#ee9a98">Item</th>
                        <th width="10%" bgcolor="#80211D">Enero</th>
                        <th width="10%" bgcolor="#80211D">Febrero</th>
                        <th width="10%" bgcolor="#80211D">Marzo</th>
                        <th width="10%" bgcolor="#80211D">Abril</th>                    
                        <th width="10%" bgcolor="#80211D">Mayo</th>
                        <th width="10%" bgcolor="#80211D">Junio</th>
                        <th width="10%" bgcolor="#80211D">Julio</th>
                        <th width="10%" bgcolor="#80211D">Agosto</th>                    
                        <th width="10%" bgcolor="#80211D">Septiembre</th>       
                        <th width="10%" bgcolor="#80211D">Octubre</th>
                        <th width="10%" bgcolor="#80211D">Noviembre</th>                    
                        <th width="10%" bgcolor="#80211D">Diciembre</th>   
                        <th width="10%" bgcolor="#ee9a98">Total</th>
                    </tr>
                 </thead>  
                 <tbody align="center">
                 <c:choose >
                   <c:when test="${not empty listaIngresos or not empty listaEgresos or not empty totalBeneficios}">
                     <tr>
                        <td bgcolor="#FFFFF">Ingresos</td>
                        <td colspan="13" bgcolor="#FFFFF">&nbsp;</td>
                     </tr>                   
                     <c:forEach items="${listaIngresos}" var="item" varStatus="status">
                     <tr  >
                       <c:choose>
                           <c:when test="${not status.last}">
                              <c:forEach items="${item}" var="celda" varStatus="status" >
                                <c:choose>
                                    <c:when test="${status.first}">
                                        <td width="10%" bgcolor="#8c8686"><c:out value="${celda}" /></td> 
                                    </c:when>
                                    <c:when test="${status.last}" >
                                        <td width="10%" bgcolor="#8c8686"><c:out value="${celda}" /></td> 
                                    </c:when>
                                    <c:otherwise>
                                        <td nowrap width="10%" bgcolor="#FFFFFF"><c:out value="${celda}" /></td> 
                                    </c:otherwise>
                                </c:choose>
                             </c:forEach>
                           </c:when>
                           <c:otherwise>
                              <c:forEach items="${item}" var="celda" varStatus="status" >
                                <c:choose>
                                    <c:when test="${status.last}">
                                        <td width="10%" bgcolor="#9a9a9a"><c:out value="${celda}" /></td> 
                                    </c:when>
                                    <c:otherwise>
                                        <td width="10%" bgcolor="#cccccc"><c:out value="${celda}" /></td> 
                                    </c:otherwise>
                                </c:choose>
                             </c:forEach>
                            </c:otherwise>
                        </c:choose>
                  </tr>    
                  </c:forEach>
                  <tr>
                    <td colspan="14">&nbsp;</td>
                  </tr>  
                  
                  <tr>
                    <td bgcolor="#FFFFF">Egresos</td>
                    <td colspan="13" bgcolor="#FFFFF">&nbsp;</td>
                  </tr>
                  <c:forEach items="${listaEgresos}" var="item">
                  <tr >
                        <td width="10%" bgcolor="#8c8686"><c:out value="${item.key} "  /></td>
                        <c:forEach items="${item.value}" var="valorMes" varStatus="loop">
                            <td width="10%" bgcolor="#FFFFF"><c:out value="${valorMes}" /></td>     
                        </c:forEach>                        
                  </tr>      

                  </c:forEach>
                   <tr >
                      <c:forEach items="${totalEgresos}" var="item" varStatus="status" >
                        <c:choose>
                            <c:when test="${status.last}">
                                <td nowrap width="10%" bgcolor="#9a9a9a"><c:out value="${item}" /></td> 
                            </c:when>
                            <c:otherwise>
                                <td nowrap width="10%" bgcolor="#cccccc"><c:out value="${item}"  /></td>    
                            </c:otherwise>
                        </c:choose>                                                          
                      </c:forEach>   
                  </tr>
                  <tr>
                    <td colspan="14">&nbsp;</td>
                  </tr>             
                  <tr>
                    <td bgcolor="#FFFFF">Beneficios</td>
                    <td colspan="13" bgcolor="#FFFFF">&nbsp;</td>
                  </tr>                    
                   <tr >
                      <c:forEach items="${totalBeneficios}" var="item" varStatus="status" >
                        <c:choose>
                            <c:when test="${status.last}">
                                <td nowrap width="10%" bgcolor="#9a9a9a"><c:out value="${item}" /></td> 
                            </c:when>
                            <c:otherwise>
                                <td nowrap width="10%" bgcolor="#cccccc"><c:out value="${item}"  /></td> 
                            </c:otherwise>
                        </c:choose>                                                                  
                      </c:forEach>   
                    </tr>  
                   <tr >
                      <c:forEach items="${benefAcum}" var="item" varStatus="status" >
                         <td nowrap width="10%" bgcolor="#cccccc"><c:out value="${item}"  /></td>                                                        
                      </c:forEach>   
                    </tr>                      
                  </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${not empty accion}">
                                <tbody>
                                    <tr><td colspan="100%">No existen ingresos/egresos para las fechas ingresadas. Realice una nueva búsqueda.</td></tr> 
                                </tbody>
                             </c:when>
                             <c:otherwise>
                                <tbody>
                                    <tr><td colspan="100%">Seleccione la fecha para buscar los ingresos/egresos o presione buscar para obtener todos los ingresos/egresos</td></tr> 
                                </tbody>
                             </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                   </c:choose>
                  </tbody>

                 </table>
               </div>
            </td>
        </tr>
    </tbody>
  </table>
  </form>
  </body>
</html>
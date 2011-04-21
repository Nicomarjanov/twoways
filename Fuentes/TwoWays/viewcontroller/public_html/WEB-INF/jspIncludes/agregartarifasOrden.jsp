<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ page isELIgnored="true" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<table  id="aTar" >
    <tr>
        <td><a href="javascript:vistaTarifas()" ><img id="tarifa+"  style="border:0;" title="Mostrar tarifa del cliente" src="img/currency_dollar green.png" alt="Tarifas" width="25" height="25" onmouseover="this.style.cursor='hand';"/></a></td>
    </Tr>
  </table>
  <table id="tabla-tarifas"  style="display:none" >
  <tr>
    <td valign="top" ><a href="javascript:vistaTarifas()"><img id="tarifa-"  style="border:0;" title="Ocultar tarifa del cliente" src="img/currency_dollar blue.png" alt="Tarifas" width="25" height="25" onmouseover="this.style.cursor='hand';"/></a></td>
  </tr>
  </table>
  <table id="tabla-tarifas-body"  style="display:none" align="center">
  <tr>
    <td valign="top" >
    <table >
    <tr>
    <td valign="top">
      <select name="listaTarifa" id="listaTarifa" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';">            
                <option value=""  >Seleccionar</option>
                <c:forEach items="${listaTarifa}" var="item">
                   <c:out value="${item.ratId}" />
                   <c:choose>
                    <c:when test="${false}">
                       <option value="<c:out value="${item.ratId}" />" style="background-color:#A4BAC7;" selected="selected">
                        <c:out value="${item.ratName}" /> <c:out value="${item.currencyTO.curSymbol}" />
                      </option> 
                    </c:when>
                    <c:otherwise>
                    <option value="<c:out value="${item.ratId}" />" style="background-color:#A4BAC7;">
                        <c:out value="${item.ratName}" /> <c:out value="${item.currencyTO.curSymbol}" />
                    </option>
                    </c:otherwise>
                    </c:choose>
                </c:forEach>
       </select>       
    </td>
   
    <td align="left" valign="top">
        <input type="text" class="tw_form" id="tar_val" size=10   />    
    </td>
   </tr>
   <tr>
    <td align="left" valign="top">
      Cantidad de Palabras   
    </td>
      <td align="left" valign="top">
        <input type="text" class="tw_form" id="tar_wordCount" size=10   />    
    </td>
    </tr>
    </table>
    </td>
    <td align="left" valign="top"><img  src="img/next.png" alt=">" width="20" height="20" title="Agregar Tarifa" onclick="agregarTarifaOrden()" onmouseover="this.style.cursor='hand';"/>
    </td>
    <td colspan="100%">
        <table cellpadding="0" cellspacing="0"  style="background:gray">
        <tr>
        <td>
     <!--table id="list-tarifas" >
     <tr><th width="190">Tarifa</th><th width="50">valor</th><th width="25"></th></tr>
     <tr><td width="190"></td><td width="50"></td><td width="25"></td></tr>
     </table>
     </td></tr><tr><td-->
     <!--div style="width:100%;height:100px;overflow-x: hidden;overflow-y:auto ;" -->
        <table id="list-tarifas-body" align="right" heigth="40px" width="100%">
         <thead>
            <tr style="display:block"><th width="200">Tarifa</th><th >valor</th><th >Cant. Palabras </th><th></th></tr>
         </thead>
         <tbody style="width:100%;height:30px;overflow-x: hidden;overflow-y:auto ;">
             <c:forEach items="${requestScope.ratesTOList}" var="item">
               <tr name="item-tarifa"  bgcolor="#FFFFFF" id="tarId-<c:out value="${item.ratesTO.ratId}" />" >
                    <td width="200" ><c:out value="${item.ratesTO.ratName}" /> <c:out value="${item.ratesTO.currencyTO.curSymbol}" /></td>
                    <td width="60" align="right" ><c:out value="${item.clrValue}" />
                        <input type="hidden" name="tarifas-hidden"  value="<c:out value="${item.ratesTO.ratId}" />#<c:out value="${item.clrValue}" />#<c:out value="${item.orrWcount}" />" />
                    <td width="60" align="right" ><c:out value="${item.orrWcount}" /> <input type="hidden" name="wordCount-hidden"  value="<c:out value="${item.orrWcount}" />" /> </td></td>    
                    <td width="37" ><img  src="img/Delete.png" height="25" width="25"  alt="Eliminar" onclick="eliminarTarifaOrden('tarId-<c:out value="${item.ratesTO.ratId}" />')" onmouseover="this.style.cursor='hand';" /></td>
                </tr>           
              </c:forEach>
        </tbody>
        </table>
       </td>
     </tr>
     </table>
  </td>
  </tr>
  </table>
 
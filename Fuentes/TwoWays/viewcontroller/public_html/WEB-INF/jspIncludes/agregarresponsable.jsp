<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<c:choose>
  <c:when test="${empty requestScope.cliente.clientResponsableTOList}">
        <table  id="aResp" >
            <tr>
                <td><a href="javascript:vistaResponsablesCliente()" ><img id="responsable+"  style="border:0;" title="Mostrar responsables del cliente" src="img/Person-add-white.png" alt="Responsable" width="25" height="25" onmouseover="this.style.cursor='hand';"/></a></td>
            </Tr>
        </table>
  </c:when>
  <c:otherwise>
        <table  id="aResp" >
            <tr>
                <td><a href="javascript:vistaResponsablesCliente()" ><img id="responsable+"  style="border:0;" title="Mostrar responsables del cliente" src="img/Person-add.png" alt="Responsable" width="25" height="25" onmouseover="this.style.cursor='hand';"/></a></td>
            </Tr>
        </table>
  </c:otherwise>
</c:choose>
<table id="dResp" style="display:none" >
    <tr>
        <th><a href="javascript:vistaResponsablesCliente()"><img id="responsable-"  style="border:0;" title="Ocultar responsables del cliente" src="img/Person-add-black.png" alt="Responsable" width="25" height="25" onmouseover="this.style.cursor='hand';"/></a></th>
    </tr>
</table>
<table id="tabla-responsables" align="center" width="73%" style="display:none">
 <tr>
 <td>
 <table id="tabla-responsables-head" align="center" width="100%" style="display:none">
    <tr>
        <th width="15%">Nombre</th>
        <th width="15%">Apellido</th>
        <th width="15%">Mail</th>
        <th width="15%">Teléfono</th>
        <th width="15%">MSN</th>
        <th width="15%">Skype</th>
        <th width="10%">&nbsp;</th>
    </tr>
    <tr>
        <td valign="top">
            <input type="text" class="tw_form"  name="NomResponsable" id="NomResponsable" onfocus="javascript:this.style.background='#FFFFFF';">       
        </td>
        <td valign="top">
            <input type="text" class="tw_form"  name="ApeResponsable" id="ApeResponsable" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';">       
        </td>
        <td valign="top">
            <input type="text" class="tw_form"  name="MailResponsable" id="MailResponsable" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';">       
        </td>
        <td valign="top">
            <input type="text" class="tw_form"  name="PhoneResponsable" id="PhoneResponsable" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';">       
        </td>
        <td valign="top">
            <input type="text" class="tw_form"  name="MsnResponsable" id="MsnResponsable" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';">       
        </td>
        <td valign="top">
            <input type="text" class="tw_form"  name="SkypeResponsable" id="SkypeResponsable" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';">         
        </td>  
        <td align="left" valign="top">
            <img  src="img/Add.png" alt=">" width="20" height="20" title="Agregar Responsable" onclick="agregarResponsable()" onmouseover="this.style.cursor='hand';"/>
        </td>
    </tr>
 </table>
 </td>
 </tr>
 <tr>
 <td>
    <c:choose>
      <c:when test="${empty requestScope.cliente.clientResponsableTOList}">
        <table id="tabla-responsables-body" width="100%" style="display:none">
      </c:when>
      <c:otherwise>
         <table id="tabla-responsables-body" width="100%">
      </c:otherwise>
    </c:choose>
      <tr>
        <td>
        <div class="fixedHeaderTableIdiom">
        <table id="list-responsables-body" align="center" width="100%" cellSpacing="1" >
        <thead>     
        <!--<tr style="display:block; background-color='transparent'">-->
        <tr style="background-color='transparent'">
            <th width="15%"></th>
            <th width="15%"></th>
            <th width="15%"></th>
            <th width="15%"></th>
            <th width="15%"></th>
            <th width="15%"></th>
            <th width="6%"></th>
        </tr>
        </thead>  
        <tbody>
            <c:forEach items="${requestScope.cliente.clientResponsableTOList}" var="item">
            <tr name="item-responsable"  bgcolor="#FFFFF" id="creId-<c:out value="${item.creFirstName}"/>#<c:out value="${item.creLastName}"/>" >            
                <td width="15%" ><c:out value="${item.creFirstName}" />                   
                   <input type="hidden" name="responsable-hidden"  value="<c:out value="${item.creFirstName}"/>#<c:out value="${item.creLastName}"/>#<c:out value="${item.creEmail}"/>#<c:out value="${item.crePhoneNumber}" />#<c:out value="${item.creMsn}"/>#<c:out value="${item.creSkype}"/>"</td>
                <td width="15%" ><c:out value="${item.creLastName}" /></td>
                <td width="15%" ><c:out value="${item.creEmail}" /></td>
                <td width="15%" ><c:out value="${item.crePhoneNumber}" /></td>                        
                <td width="15%" ><c:out value="${item.creMsn}" /></td>     
                <td width="15%" ><c:out value="${item.creSkype}" /></td>                
                <td width="6%" bgcolor="transparent">
                    <img  src="img/del2.png" height="15" width="15"  alt="Eliminar" onclick="eliminarResponsable('creId-<c:out value="${item.creFirstName}"/>#<c:out value="${item.creLastName}" />')" onmouseover="this.style.cursor='hand';" />    <img  src="img/Edit2.png" height="15" width="15"  alt="Editar" onclick="editarResponsable('<c:out value="${item.creFirstName}"/>#<c:out value="${item.creLastName}"/>#<c:out value="${item.creEmail}"/>#<c:out value="${item.crePhoneNumber}"/>#<c:out value="${item.creMsn}"/>#<c:out value="${item.creSkype}"/>#')" onmouseover="this.style.cursor='hand';" /></td>
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
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<table  id="aResp" >
    <tr>
        <td><a href="javascript:vistaResponsablesCliente()" ><img id="responsable+"  style="border:0;" title="Mostrar responsables del cliente" src="img/Person-add-white.png" alt="Responsable" width="25" height="25" onmouseover="this.style.cursor='hand';"/></a></td>
    </Tr>
  </table>
  <table id="tabla-responsables"  style="display:none" >
  <tr><th><a href="javascript:vistaResponsablesCliente()"><img id="responsable-"  style="border:0;" title="Ocultar responsables del cliente" src="img/Person-add-black.png" alt="Responsable" width="25" height="25" onmouseover="this.style.cursor='hand';"/></a></th><th width="20">Nombre</th><th width="20">Apellido</th><th width="20">Mail</th><th width="20">Teléfono</th><th width="20">Mobil</th></tr>
  <tr>
  <td valign="top" ><!--<a href="javascript:vistaResponsablesCliente()"><img id="responsable-"  style="border:0;" title="Ocultar responsables del cliente" src="../../img/Person-add-black.png" alt="Responsable" width="25" height="25" onmouseover="this.style.cursor='hand';"/></a>-->&nbsp;</td>
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
    <input type="text" class="tw_form"  name="MobilResponsable" id="MobilResponsable" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';">       
  </td>
  <td align="left" valign="top">
    <img  src="img/Add.png" alt=">" width="20" height="20" title="Agregar Responsable" onclick="agregarResponsable()" onmouseover="this.style.cursor='hand';"/>
  </td>
  </tr>
  </table>
  <!--<td colspan="100%">-->
    <table cellpadding="0" cellspacing="0"  width="100%">
    <tr>
    <td>
     <!--<table id="list-responsables" >

     <tr><td width="20"></td><td width="20"></td><td width="20"></td><td width="20"></td><td width="20"></td><td width="37"></td></tr>
     </table>
     </td></tr><tr><td
     <div style="width:100%;height:100px;overflow-x: hidden;overflow-y:auto ;background:gray;" >-->
     <table id="list-responsables-body" align="right" width="100%">
     <thead>     
       <tr style="display:block">
            <th width="20"></th><th width="20"></th><th width="20"></th><th width="20"></th><th width="20"></th><th width="37"></th></tr>
     </thead>  
     <tbody style="width:600%;height:30px;overflow-x: hidden;overflow-y:auto ;">
     <c:forEach items="${requestScope.clientResponsableTOList}" var="item">
       <tr name="item-responsable"  bgcolor="#FFFFFF" id="creId-<c:out value="${item.clientResponsableTO.creId}" />" >            
            <td width="20" ><c:out value="${item.clientResponsableTO.creFirstName}" />
               <input type="hidden" name="responsable-hidden"  value="<c:out value="${item.clientResponsableTO.creId}"/>#<c:out value="${item.clientResponsableTO.creFirstName}"/>#<c:out value="${item.clientResponsableTO.creLastName}"/>#<c:out value="${item.clientResponsableTO.creEmail}"/>#<c:out value="${item.clientResponsableTO.crePhoneNumber}" />#<c:out value="${item.clientResponsableTO.creMobileNumber}" />"</td>
            <td width="20" ><c:out value="${item.clientResponsableTO.creLastName}" /></td>
            <td width="20" ><c:out value="${item.clientResponsableTO.creEmail}" /></td>
            <td width="20" ><c:out value="${item.clientResponsableTO.crePhoneNumber}" /></td>                        
            <td width="20" ><c:out value="${item.clientResponsableTO.creMobileNumber}" /></td>            
            <td width="37" ><img  src="img/Delete.png" height="25" width="25"  alt="Eliminar" onclick="eliminarResponsable('creId-<c:out value="${item.clientResponsableTO.creFirstName}"/>#<c:out value="${item.clientResponsableTO.creLastName}" />')" onmouseover="this.style.cursor='hand';" /></td>
        </tr>           
      </c:forEach>
      </tbody>
     </table>

     </td>
     </tr>
     </table>
 <!--</td>
  </tr>
  </table>-->
 
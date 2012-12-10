<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ page isELIgnored="true" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<table  id="aDoc" >
    <tr>
        <td><a href="javascript:vistaDocumentos()" ><img id="documento+"  style="border:0;" title="Mostrar documentos del cliente" src="img/folder_red.png" alt="Documentos" width="25" height="25" onmouseover="this.style.cursor='hand';"/></a></td>
    </tr>
  </table>
  <table id="tabla-documentos"  style ="display:none" >
  <tr>
  <td valign="top" ><a href="javascript:vistaDocumentos()"><img id="documento-"  style="border:0;" title="Ocultar documentos del cliente" src="img/folder_red_open.png" alt="Documentos" width="25" height="25" onmouseover="this.style.cursor='hand';"/></a></td>
  <td valign="top">
    <table >
      <tr>
         <td>
           <select name="listaDocTypes" id="listaDocTypes" style="border:solid 1px #005C8D;" onfocus="javascript:this.style.background='#FFFFFF';" onchange="cambioTipo()">                
                <option value="" >Seleccionar</option>
                <c:forEach items="${listaDocTypes}" var="item">
                    <option value="<c:out value="${item.dotId}" />" style="background-color:#A4BAC7;">
                        <c:out value="${item.dotName}" />
                    </option>
                </c:forEach>
           </select> 
         </td>
         <td>Documento:</td><td id="tdIn" ><input type="file" class="tw_form" id="doc_name" size=30 onchange="agregarDocumento()" /> </td>   
      </tr>
    </table>
  </td>

      <!--td align="left" valign="top"><img  src="img/next.png" alt=">"   width="20" height="20" title="Agregar Documento" onclick="agregarDocumento()" onmouseover="this.style.cursor='hand';"/-->
  <td colspan="100%">
    <table cellpadding="0" cellspacing="0" style="background:gray">
    <tr>
    <td>
     <table id="list-documento-body" align="right" width="100%">
     <thead>
     <tr><th width="400" colspan="2" >Documento</th><th>Tipo</th></tr>
      </thead>
      <tbody  style="width:100%;height:30px;overflow-x: hidden;overflow-y:auto ;" >
     <c:forEach items="${docList}" var="item">
       <tr name="item-documento"  bgcolor="#FFFFFF" id="ordId-<c:out value="${item.odoName}" />" >
             <td width="46" align="right" ><img  src="img/Delete.png" height="25" width="25"  alt="Eliminar" onclick="eliminarDocumento('ordId-<c:out value="${item.odoName}" />')" onmouseover="this.style.cursor='hand';" /></td>
             <td width="300" ><a href="/twoways/downloadfile?docId=<c:out value="${item.odoId}" />&docType=orderDoc"  ><c:out value="${item.odoName}" /><input type="hidden"  name="exdoc" value="<c:out value="${item.odoName}" />"  ></a>
             </td>
             <td>
             <c:out value="${item.docType.dotName}" />
             </td>
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
 
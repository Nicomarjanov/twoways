<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <title>Conectar a TWOWAYS</title>
    <link href="./twoways.css" rel="stylesheet" type="text/css"/>
 
    <script language="JavaScript">
        document.onkeydown = checkKeycode
        function checkKeycode(e) {
        var keycode;
        if (window.event) keycode = window.event.keyCode;
        else if (e) keycode = e.which;
        if( keycode == 13){
           conectar();
        }
    }
        

     function conectar(){
      var msj ="";
      var form = document.getElementById('loginForm');
      if(form.usuario.value.length  == 0 ){
         msj = '<br>*Ingrese el usuario';
      }
      if(form.password.value.length  == 0  ){
         msj+= '<br>*Ingrese el la clave'; 
      } 
      if(msj.length > 0){ 
        document.getElementById('mensajeError').innerHTML= msj; 
        return false; 
      }else {
        
       form.submit();
        
      }
      
     }
    </script>
  </head>
  <body>
  <div style="width:100%;heigth:100%" >
  <table width="100%" >
<tr>
    <td align="center" ><img src="img/logo_bajo_relieve_azul.jpg" alt="TwO WAYS" /> 
    </td>
</tr>
<tr>
<td align="center" valign="middle" >
  <form method="POST" action="login"  id="loginForm" >
  <table>
  <tr><th colspan="2" class="tw_form">Conectar a WOWAYS</th></tr>
  <tr><td colspan="2" id="mensajeError"  style="color:red"><c:out value="${requestScope.mensajeError}"/></td></tr>
  <tr><td><b>Usuario: </b> </td><td><input type="text" name="usuario"/></td></tr>
  <tr><td><b>Contraseña: </b> </td><td><input type="password" name="password" onkeypress="conectar"/></td></tr> 
  <tr><td>&nbsp; </td><td align="right" ><input type="button" onclick="conectar()"  value="Conectar" /></td></tr>
  </table >
  </form>
  </td>
  </tr>
  </table>
  </div>
  </body>
 
</html>

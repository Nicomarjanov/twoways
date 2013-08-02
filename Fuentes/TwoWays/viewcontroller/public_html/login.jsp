<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <title>Conectar a TWOWAYS</title>
    <link href="./twoways.css" rel="stylesheet" type="text/css"/>
   <link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">
   <link rel="icon" type="image/x-icon" href="img/favicon.ico">    
 
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
    
    function cursor(){
       document.getElementById('usuario').focus();
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
  <body class="login" onLoad="cursor()">
  <!--<div id="div_login">-->
  <table width="100%" height="100%" >
<tr>
    <td align="center"><img src="img/2ways_logo2.jpg" alt="TwO WAYS"  /> 
    </td>
</tr>
<tr>
<td align="center" valign="middle" style="padding-top:15px">
  <form method="POST" action="login"  id="loginForm" >
  <table class="login">
  <tr><th colspan="2" class="tw_form_login">Conectar a TWO WAYS</th></tr>
  <tr><td colspan="2" id="mensajeError"  style="color:red"><c:out value="${requestScope.mensajeError}"/></td></tr>
  <tr><td style="padding:10px"><b>Usuario: </b> </td><td style="padding:10px"><input class="tw_form" type="text" name="usuario" id="usuario"/></td></tr>
  <tr><td  style="padding:10px"><b>Contraseña: </b> </td><td style="padding:10px"><input class="tw_form" type="password" name="password" onkeypress="conectar"/></td></tr> 
  <tr><td colspan="2" align="right" style="padding:10px 15px 10px 0px; border-top:1px solid;" ><input type="button" onclick="conectar()"  value="Conectar" /></td></tr>
  </table >
  </form>
  </td>
  </tr>
  </table>
 <!-- </div>-->
  </body>
 
</html>

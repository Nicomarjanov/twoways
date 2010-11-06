<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<Html>
<Head>
<Title>Menu</title>

<style type="text/css">


/* Estilos de configuraci�n del men� desplegable.*/

#dhtmlgoodies_menu ul li ul{
display:none; /* Necesario para visualizarse bien en opera */
}

#dhtmlgoodies_menu{
visibility:hidden; 
}
#dhtmlgoodies_menu ul{
margin:0px; /* Sin sangria */
padding:0px; /* Sin sangria */
}
#dhtmlgoodies_menu li{
list-style-type:none; /* Sin iconos */
} 
#dhtmlgoodies_menu a{
margin:0px;
padding:0px;
}

/* Estilos de la est�tica del men�. Aqu� puedes modificarlos de acuerdo al estilo de tu web
Estilos que se aplican a todo el men�*/

#dhtmlgoodies_menu ul{
border:1px solid #000;
background-color:#FFF;
padding:1px;
}
#dhtmlgoodies_menu ul.menuBlock1{ /* Barra de men� - elementos del menu principal*/
border:0px;
padding:1px;
border:1px solid #317082;
background-color:#E2EBED;
overflow:visible;
}
#dhtmlgoodies_menu ul.menuBlock2{ /* Barra de men� - elementos del menu principal*/
border:0px;
padding:1px;
border:1px solid #555;
}
#dhtmlgoodies_menu a{
color: #000;
text-decoration:none;
padding-left:2px;
padding-right:2px;
}

/* Estilos del men� principal. El que esta siempre visible */

#dhtmlgoodies_menu .currentDepth1{
padding-left:5px;
padding-right:5px;
border:1px solid #E2EBED;
}
#dhtmlgoodies_menu .currentDepth1over{
padding-left:5px;
padding-right:5px;
background-color:#317082;
border:1px solid #000;
}
#dhtmlgoodies_menu .currentDepth1 a{
font-weight:bold;
}
#dhtmlgoodies_menu .currentDepth1over a{ 
color:#FFF;
font-weight:bold;
}

/* Estilos submenu nivel 1 */

#dhtmlgoodies_menu .currentDepth2{
padding-right:2px;
border:1px solid #FFF;
}
#dhtmlgoodies_menu .currentDepth2over{
padding-right:2px;
background-color:#E2EBED;
border:1px solid #000;
} 
#dhtmlgoodies_menu .currentDepth2over a{
color:#000;
} 

/* Estilos submenu nivel 2 */

#dhtmlgoodies_menu .currentDepth3{
padding-right:2px;
border:1px solid #FFF;
}
#dhtmlgoodies_menu .currentDepth3over{
padding-right:2px;
background-color:#EDE3EB;
border:1px solid #000;
}

/* Estilos submenu nivel 3 */

#dhtmlgoodies_menu .currentDepth4{
padding-right:2px;
border:1px solid #FFF;
}
#dhtmlgoodies_menu .currentDepth4over{
padding-right:2px;
background-color:#EBEDE3;
border:1px solid #000;
} 

</style>

<script type="text/javascript" src="js/menu.js" ></script>


</head>

<Body>
<table width="100%">
<tr>
    <td><img src="img/logo_bajo_relieve_azul.jpg" alt="TwO WAYS" /> 
    </td>
</tr>
<tr>
    <td><b>Usuario:&nbsp;</b>&nbsp;<c:out value="${ sessionScope.userSession.usrLastName}" />,&nbsp;<c:out value="${ sessionScope.userSession.usrFirstName}" /> 
    </td>
</tr>
<tr>
    <td><div id="dhtmlgoodies_menu">
 <ul>
     <c:choose>
     <c:when test="${sessionScope.userSession.rolesTO.rolName == 'Administrador'}">  
      <jsp:include page="/WEB-INF/jspIncludes/menuAdmin.jsp" />
     </c:when>
     <c:when test="${sessionScope.userSession.rolesTO.rolName == 'Usuario'}">
      <jsp:include page="/WEB-INF/jspIncludes/menuUser.jsp" />
     </c:when>
     <c:otherwise>
      <jsp:include page="/WEB-INF/jspIncludes/menuConectar.jsp" />
     </c:otherwise>
     </c:choose>
 </ul> 
 </div>
 </td>
 </tr>
 </table></body>
</html>
var mensajeCampoAlert;

function cancelar()
{
    if(confirm('¿Desea cancelar la carga del usuario?'))
    {   
        document.getElementById("accion").value='cancelar';
        document.forms[0].submit();
    }
}

/******************************************************************************/
//   metodos utilizados para dar de alta los item
/******************************************************************************/
function agregar()
{
    //trabar(idsPantalla());
    
    document.getElementById('usrId').style.background  = '#FFFFFF';
    document.getElementById('usrPass').style.background  = '#FFFFFF';
    document.getElementById('usrFirstName').style.background  = '#FFFFFF';
    document.getElementById('usrLastName').style.background  = '#FFFFFF';
    document.getElementById('usrBirth').style.background = '#FFFFFF';
    document.getElementById('usrMail').style.background    = '#FFFFFF';
    document.getElementById('usrMobileNumber').style.background = '#FFFFFF';
    document.getElementById('usrPhoneNumber').style.background = '#FFFFFF';
    document.getElementById('usrOfficeNumber').style.background = '#FFFFFF';
    
    document.getElementById('usrId').value       = trim(document.getElementById('usrId').value);
    document.getElementById('usrPass').value       = trim(document.getElementById('usrPass').value);    
    document.getElementById('usrFirstName').value     = trim(document.getElementById('usrFirstName').value);
    document.getElementById('usrLastName').value     = trim(document.getElementById('usrLastName').value);
    document.getElementById('usrBirth').value    = trim(document.getElementById('usrBirth').value);
    document.getElementById('usrMobileNumber').value       = trim(document.getElementById('usrMobileNumber').value);
    document.getElementById('usrPhoneNumber').value       = trim(document.getElementById('usrPhoneNumber').value);
    document.getElementById('usrOfficeNumber').value       = trim(document.getElementById('usrOfficeNumber').value);    
    
    if(validarCampos())
    {
        alert(mensajeCampoAlert);
        //destrabar(idsPantalla()); 
    }
    else
        grabar(false);
        //BpmAdm.isExisteItem(document.getElementById("itemNombre").value,grabar);
}

function grabar(existe)
{
    if(existe)
    {
        alert(mensajeExisteItem);
        destrabar(idsPantalla());
    }
    else
    {
        document.getElementById("accion").value='guardar';
        document.forms[0].submit();                   
    }
}


function idsPantalla()
{
    return 'itemNombre,itemTitulo,itemToolTip,itemLink,listaBandeja,listaGrupos,btnAgregar,btnCancelar';
}

function buscarUsuarios(){
     
     var usrId= document.getElementById('usrId').value;
     var nomUsr= document.getElementById('usrFirstName').value;
     
     
     if(usrId== '' &&  nomUsr.length >2 ){ 
        
        document.getElementById('div-usuarios').style.display='';
        towaysDWR.buscarUsuarios(nomCliente,buscarUsuariosCallBack); 
     } 
    
}

function limitarArea(){

 var desc=  document.getElementById('descCliente');  
 if(desc.value.length > 250){
    desc.value= desc.value.substring(0,250); 
 }

}

function cargarDatosColumna(row,data){
    
   row.cells[0].innerHTML=(data.usrId==null)?'':'<a href="clientes?usrId='+data.usrId+'" >'+data.usrId+'</a>';
   row.cells[1].innerHTML=(data.usrPass==null)?'':data.usrPass;   
   row.cells[2].innerHTML=(data.usrFirstName==null)?'':data.usrFirstName;   
   row.cells[3].innerHTML=(data.usrLastName==null)?'':data.usrLastName;     
   row.cells[4].innerHTML=(data.usrBirth==null)?'':data.usrBirth;
   row.cells[5].innerHTML=(data.usrMobileNumber==null)?'':data.usrMobileNumber;
   row.cells[6].innerHTML=(data.usrPhoneNumber==null)?'':data.usrPhoneNumber;
   row.cells[7].innerHTML=(data.usrOfficeNumber==null)?'':data.usrOfficeNumber;   
   var editar = '<img src="img/edit.png"  height="25" width="25"  alt="Editar" onclick="javascript:window.location.href=\'clientes?cliId='+data.usrId+'\'" onmouseover="this.style.cursor=\'hand\';" /> ';
   var eliminar = '<img  src="img/Delete.png" height="25" width="25"  alt="Eliminar" onclick="eliminarUsuario('+data.cliId+')" onmouseover="this.style.cursor=\'hand\';" />'
   //row.cells[3].innerHTML='<a href="clientes?cliId='+data.cliId+'" ><img src="img/Edit-Contact.png" height="25" width="25"  alt="Editar" /></a>';
   row.cells[8].innerHTML= editar + ' ' + eliminar;
}


function  eliminarUsuario(usrId){
 
 if (confirm('¿Esta seguro que desea eliminar el usuario?') ){
 
    towaysDWR.deleteUser(usrId,postEliminar); 
 }

}

function postEliminar(data){
  
   var tablaUsuarios= document.getElementById('tabla-busqueda'); 
   if(data){
      alert('El usuario se elimino con exito ');
      borrarFilas(tablaUsuarios);
   }else{
      alert('El usuario no se pudo eliminar ');
   }
}

function buscarUsuariosCallBack(data){
 
  var tablaUsuarios= document.getElementById('tabla-busqueda');
  borrarFilas(tablaClientes);
  for(var i=0 ; i<   data.length; i++){
    
    insertarFila(tablaUsuarios,data[i]);
    
  } 
}

function validarCampos()
{
    var banderaMensajeFaltante=false;
    mensajeCampoAlert='';
    mensajeFaltanteAlert = 'Se tiene que completar los siguientes campos: \n';
    
    /************************************************/
    // valido el que los campos no esten vacíos
    /************************************************/
    
    if( document.getElementById("usrId").value == '')
    {
        document.getElementById("usrId").style.background='Red';
        mensajeFaltanteAlert+= ' * Identificador del usuario \n';
        banderaMensajeFaltante=true;
    }
    
    if(document.getElementById("listaRoles").selectedIndex==0)
    {
        document.getElementById("listaRoles").style.background='red';
        mensajeFaltanteAlert=' * Seleccionar un rol del combo \n';    
        banderaMensajeFaltante=true;
    }

    if( document.getElementById("usrPass").value != '')
    {
        if (document.getElementById("usrPass").value=='')
        {
        document.getElementById("usrPass").style.background='Red';
        mensajeFaltanteAlert+= ' * El Password del usuario \n';
        banderaMensajeFaltante=true;
        }
    }
    
    if( document.getElementById("usrFirstName").value != '')
    {
        if (!(validarEmail(document.getElementById("usrFirstName").value)))
        {
        document.getElementById("usrFirstName").style.background='Red';
        mensajeFaltanteAlert+= ' * El primer nombre del usuario \n';
        banderaMensajeFaltante=true;
        }
    }

    if(banderaMensajeFaltante)
        mensajeCampoAlert=mensajeFaltanteAlert + '\n';    
    
    if (banderaMensajeFaltante)
        return true;
    else
        return false;
}




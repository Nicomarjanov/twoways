var mensajeCampoAlert;

function cancelar()
{
    if(confirm('�Desea cancelar la carga del usuario?'))
    {   
        document.getElementById("accion").value='cancelar';
        document.forms[0].submit();
    }
}

function ocultarOpcionales(){
   var btnMas=document.getElementById('aMas'); 
   var btnMenos=document.getElementById('aMenos'); 
   var op1=document.getElementById('trOpcionales1'); 
   var op2=document.getElementById('trOpcionales2'); 
   btnMas.style.display='';
   btnMenos.style.display='none';
   op1.style.display='none';
   op2.style.display='none';


}

function mostrarOpcionales(){
   var btnMas=document.getElementById('aMas'); 
   var btnMenos=document.getElementById('aMenos'); 
   var op1=document.getElementById('trOpcionales1'); 
   var op2=document.getElementById('trOpcionales2'); 
   btnMas.style.display='none';
   btnMenos.style.display='';
   op1.style.display='';
   op2.style.display='';

}
/******************************************************************************/
//   metodos utilizados para dar de alta los item
/******************************************************************************/
function agregar()
{
   
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
    }
    else
        grabar(false);
}

function grabar(existe)
{
    if(existe)
    {
        alert(mensajeExisteItem);

    }
    else
    {
        document.getElementById("accion").value='guardar';
        document.forms[0].submit();                   
    }
}

function buscarUsuarios(){
     
     var userId= document.getElementById('userId').value;
     var usrId= document.getElementById('usrId').value;
     var nomUsr= document.getElementById('usrFirstName').value;

     if(userId== '' &&  (nomUsr.length >2 && usrId=='') || (usrId.length > 2 && nomUsr=='')){     
        if (nomUsr.length >2){
            towaysDWR.buscarUsuarios(nomUsr,buscarUsuariosCallBack);
        }
        else{
            towaysDWR.buscarUsuariosId(usrId,buscarUsuariosCallBack);
        }
     }
}

function limitarArea(){

 var desc=  document.getElementById('descCliente');  
 if(desc.value.length > 250){
    desc.value= desc.value.substring(0,250); 
 }
}

function cargarDatosColumna(row,data){
    
   row.cells[0].innerHTML=(data.usrId==null)?'':'<a href="usuarios?userId='+data.usrId+'" >'+data.usrId+'</a>';   
   row.cells[1].innerHTML=(data.usrFirstName==null)?'':data.usrFirstName;   
   row.cells[2].innerHTML=(data.usrLastName==null)?'':data.usrLastName;     
   row.cells[3].innerHTML=(data.usrMail==null)?'':data.usrMail;   
   row.cells[4].innerHTML=(data.usrBirth==null)?'':data.usrBirth;   

   var editar = '<img src="img/edit.png"  height="20" width="20"  alt="Editar" onclick="javascript:window.location.href=\'usuarios?userId='+data.usrId+'\'" onmouseover="this.style.cursor=\'hand\';" /> ';
   var eliminar = '';
   var eliminado = '';
   if (data.usrEraseDate != null){
      eliminado = '<img  src="img/Erase.png" height="20" width="20"  alt="Usuario eliminado el d�a: \''+data.usrEraseDate+'\'"  />'      
   }
   else {
      eliminar = '<img  src="img/Delete.png" height="20" width="20"  alt="Eliminar" onclick="eliminarUsuario(\''+data.usrId+'\')" onmouseover="this.style.cursor=\'hand\';" />';
   }
   
   row.cells[5].innerHTML= editar + ' ' + eliminar + eliminado;
}

function  eliminarUsuario(usuarioId){

if(usuarioId != null){ 
     if (confirm('�Esta seguro que desea eliminar el usuario?') ){
     
        towaysDWR.deleterUsuario(usuarioId,postEliminar); 
     }
}
else {
 alert('Debe seleccionar un usuario antes de eliminar');
 }
}

function postEliminar(data){
  
   var tablaUsuarios= document.getElementById('tabla-busqueda'); 
   if(data){
      alert('El usuario se elimino con exito');
      borrarFilas(tablaUsuarios);
      window.location.href='usuarios';
   }else{
      alert('El usuario no se pudo eliminar ');
   }
}

function buscarUsuariosCallBack(data){
  if (data.length > 0) {
      document.getElementById('div-usuarios').style.display='';
      var tablaUsuarios= document.getElementById('tabla-busqueda');
      borrarFilas(tablaUsuarios);
      for(var i=0 ; i<   data.length; i++){
        
        insertarFila(tablaUsuarios,data[i]);    
      }
  }
  else {
     document.getElementById('div-usuarios').style.display='none';
  }
}

function validarCampos()
{
    var banderaMensajeFaltante=false;
    mensajeCampoAlert='';
    mensajeFaltanteAlert = 'Se tiene que completar los siguientes campos: \n';
    
    /************************************************/
    // valido el que los campos no esten vac�os
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
        mensajeFaltanteAlert+=' * Seleccionar un rol del combo \n';    
        banderaMensajeFaltante=true;
    }

   if (document.getElementById("usrPass").value =='')
    {
        document.getElementById("usrPass").style.background='Red';
        mensajeFaltanteAlert+= ' * El Password del usuario \n';
        banderaMensajeFaltante=true;        
    }
    
    if( document.getElementById("usrFirstName").value == '')
    {
        document.getElementById("usrFirstName").style.background='Red';
        mensajeFaltanteAlert+= ' * El primer nombre del usuario \n';
        banderaMensajeFaltante=true;
    }
    
    if( document.getElementById("usrMail").value != '')
    {
        if (!(validarEmail(document.getElementById("usrMail").value)))
        {
        document.getElementById("usrMail").style.background='Red';
        mensajeFaltanteAlert+= ' * La direcci�n de email es incorrecta \n';
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




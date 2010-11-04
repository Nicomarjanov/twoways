var mensajeCampoAlert;

function cancelar()
{
    if(confirm('¿Desea cancelar la carga del cliente?'))
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
    
    document.getElementById('nomCliente').style.background  = '#FFFFFF';
    document.getElementById('descCliente').style.background  = '#FFFFFF';
    document.getElementById('mailCliente').style.background = '#FFFFFF';
    document.getElementById('telCliente').style.background    = '#FFFFFF';
    document.getElementById('listaMoneda').style.background = '#FFFFFF';
    
    document.getElementById('nomCliente').value     = trim(document.getElementById('nomCliente').value);
    document.getElementById('descCliente').value     = trim(document.getElementById('descCliente').value);
    document.getElementById('mailCliente').value    = trim(document.getElementById('mailCliente').value);
    document.getElementById('telCliente').value       = trim(document.getElementById('telCliente').value);
    document.getElementById('dirCliente').value       = trim(document.getElementById('dirCliente').value);
    document.getElementById('cpCliente').value       = trim(document.getElementById('cpCliente').value);    
    document.getElementById('paisCliente').value       = trim(document.getElementById('paisCliente').value);
    
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
        
        /*BpmAdm.setItem( document.getElementById('itemLink').value   , document.getElementById("listaBandeja").value , document.getElementById("itemNombre").value   , 
                        document.getElementById('itemTitulo').value , document.getElementById('itemToolTip').value  , document.getElementById("listaGrupos").value  ,
                        document.getElementById('rolUserConect').value,grabarResultado);
           */             
    }
}


function idsPantalla()
{
    return 'itemNombre,itemTitulo,itemToolTip,itemLink,listaBandeja,listaGrupos,btnAgregar,btnCancelar';
}

function buscarClientes(){
     
     var cliId= document.getElementById('cliId').value;
     var nomCliente= document.getElementById('nomCliente').value;
     
     
     if(cliId== '' &&  nomCliente.length >2 ){ 
        
        towaysDWR.buscarClientes(nomCliente,buscarClientesCallBack); 
     } 
    
}


function cargarDatosColumna(row,data){

    
   row.cells[0].innerHTML=(data.cliName==null)?'':data.cliName;
   row.cells[1].innerHTML=(data.cliDescription==null)?'':data.cliDescription;
   row.cells[2].innerHTML=(data.cliPhone==null)?'':data.cliPhone;
   row.cells[3].innerHTML=(data.cliMail==null)?'':data.cliMail;
   var editar = '<img src="img/Edit-Contact.png"  height="25" width="25"  alt="Editar" onclick="javascript:window.location.href=\'clientes?cliId='+data.cliId+'\'" onmouseover="this.style.cursor=\'hand\';" /> ';
   var eliminar = '<img  src="img/Rem_Contact.png" height="25" width="25"  alt="Eliminar" onclick="eliminarCliente('+data.cliId+')" onmouseover="this.style.cursor=\'hand\';" />'
   //row.cells[3].innerHTML='<a href="clientes?cliId='+data.cliId+'" ><img src="img/Edit-Contact.png" height="25" width="25"  alt="Editar" /></a>';
   row.cells[4].innerHTML= editar + ' ' + eliminar;
}


function  eliminarCliente(cliId){
 
 if (confirm('¿Esta seguro que desea eliminar el cliente?') ){
 
    towaysDWR.deleterCliente(cliId,postEliminar); 
 }

}

function postEliminar(data){
  
   var tablaClientes= document.getElementById('tabla-clientes'); 
   if(data){
      alert('El cliente se elimino con exito ');
      borrarFilas(tablaClientes);
   }else{
      alert('El cliente no se pudo eliminar ');
   }
}

function buscarClientesCallBack(data){
 
  var tablaClientes= document.getElementById('tabla-clientes');
  borrarFilas(tablaClientes);
  for(var i=0 ; i<   data.length; i++){
    
    insertarFila(tablaClientes,data[i]);
    
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
    
    if( document.getElementById("nomCliente").value == '')
    {
        document.getElementById("nomCliente").style.background='Red';
        mensajeFaltanteAlert+= ' * Nombre del cliente \n';
        banderaMensajeFaltante=true;
    }
    
    if(document.getElementById("listaMoneda").selectedIndex==0)
    {
        document.getElementById("listaMoneda").style.background='red';
        mensajeFaltanteAlert=' * Seleccionar una moneda del combo \n';    
        banderaMensajeFaltante=true;
    }

    if( document.getElementById("telCliente").value != '')
    {
        if (!(isNumber(document.getElementById("telCliente").value)))
        {
        document.getElementById("telCliente").style.background='Red';
        mensajeFaltanteAlert+= ' * El Teléfono debe ser numerico unicamente \n';
        banderaMensajeFaltante=true;
        }
    }
    
    if( document.getElementById("mailCliente").value != '')
    {
        if (!(validarEmail(document.getElementById("mailCliente").value)))
        {
        document.getElementById("mailCliente").style.background='Red';
        mensajeFaltanteAlert+= ' * La dirección de email es incorrecta \n';
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






var mensajeCampoAlert;


 function keyTarifa(e) {
        var keycode;
        if (window.event) keycode = window.event.keyCode;
        else if (e) keycode = e.which;
        if( keycode == 13){
           agregarTarifa();
        }
        
 }       
function init(){
    
    if (  document.getElementById('cliId').value != ''){
    
         vistaTarifas(); 
         mostrarOpcionales();
    
    }

}
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


function agregar()
{

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

function buscarClientes(e){
     
    var keycode;
    var cliId= document.getElementById('cliId').value;
    var nomCliente= document.getElementById('nomCliente').value;
    if (window.event) keycode = window.event.keyCode;
    else if (e) keycode = e.which;
    
    if( keycode == 40 &&  document.getElementById('selectCli').options.length > 0 ){
      document.getElementById('selectCli').options[0].selected = true;
      document.getElementById('selectCli').focus();
      document.getElementById('selectCli').style.display='';
      
      return;
    }else if(keycode == 9 || keycode==8 ){
      return;
    }else if( keycode == 13){
        
        document.getElementById('nomCliente').value= document.getElementById('selectCli').options[0].text;
        document.getElementById('selectCli').display='none';
        document.getElementById('nomCliente').onblur();
    }else if(cliId== '' &&  nomCliente.length >2 ){ 
               
        towaysDWR.buscarClientes(nomCliente,buscarClientesCallBack); 
      
    }
}



function cargarDatosColumna(row,data){

    
   row.cells[0].innerHTML=(data.cliName==null)?'':'<a href="clientes?cliId='+data.cliId+'" >'+data.cliName+'</a>';
   row.cells[1].innerHTML=(data.cliDescription==null)?'':data.cliDescription;
   row.cells[2].innerHTML=(data.cliPhone==null)?'':data.cliPhone;
   row.cells[3].innerHTML=(data.cliMail==null)?'':data.cliMail;
   var editar = '<img src="img/edit.png"  height="25" width="25"  alt="Editar" onclick="javascript:window.location.href=\'clientes?cliId='+data.cliId+'\'" onmouseover="this.style.cursor=\'hand\';" /> ';
   var eliminar = '<img  src="img/Delete.png" height="25" width="25"  alt="Eliminar" onclick="eliminarCliente('+data.cliId+')" onmouseover="this.style.cursor=\'hand\';" />'
   //row.cells[3].innerHTML='<a href="clientes?cliId='+data.cliId+'" ><img src="img/Edit-Contact.png" height="25" width="25"  alt="Editar" /></a>';
   row.cells[4].innerHTML= editar + ' ' + eliminar;
}

function  eliminarCliente(cliId){
 
 if (confirm('¿Esta seguro que desea eliminar el cliente?') ){
 
    towaysDWR.deleterCliente(cliId,postEliminar); 
 }

}

function postEliminar(data){
  
   var tablaClientes= document.getElementById('tabla-busqueda'); 
   if(data){
      alert('El cliente se elimino con exito ');
      borrarFilas(tablaClientes);
   }else{
      alert('El cliente no se pudo eliminar ');
   }
}

function seleccionCliente(){

   
    var selectCli = document.getElementById('selectCli');
    var seleccion = selectCli.options[selectCli.selectedIndex].text;
    var cliente = document.getElementById('nomCliente');
    cliente.value= seleccion;
    selectCli.display='none';
    
    
}
function buscarClientesCallBack(data){
   if (data.length > 0) {
      var tablaClientes= document.getElementById('tabla-busqueda');
      var selectCli = document.getElementById('selectCli');
      selectCli.options.length=0;
      borrarFilas(tablaClientes);
      document.getElementById('div-clientes').style.display='';
      for(var i=0 ; i<   data.length; i++){
        
        var option = document.createElement('option')
        option.value= data[i].cliId;
        option.text= data[i].cliName;
                
        if(MSIE){
          selectCli.add(option);
        }else{
           selectCli.add(option,null);
        }
        insertarFila(tablaClientes,data[i]);
        
        
      } 
     selectCli.style.display=''; 
     selectCli.size=data.length;    
    }
     else {
     document.getElementById('div-clientes').style.display='none';
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
        mensajeFaltanteAlert+=' * Seleccionar una moneda del combo \n';    
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






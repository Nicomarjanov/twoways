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

function vistaTarifas(){

   var tabla=document.getElementById('tabla-tarifas'); 
   if(tabla.style.display =='none'){
       tabla.style.display='';
       document.getElementById('aTar').style.display='none';
       
   }else{
       tabla.style.display='none';
        document.getElementById('aTar').style.display='';
   }
}

function agregarTarifa(){

   if( document.getElementById('listaTarifa').selectedIndex == 0){ 
      alert('Seleccione una tarifa');  
      document.getElementById('listaTarifa').focus();
     return;
   }
   if( document.getElementById('tar_val').value == '' || !isFloat(document.getElementById('tar_val').value)){ 
      alert('Ingrese una tarifa válida');  
      document.getElementById('tar_val').focus();
     return;
   }
   
   if(document.getElementById('tarId-'+document.getElementById('listaTarifa').options[document.getElementById('listaTarifa').selectedIndex].value)){
   
     alert('La tarifa ya se encuentra en la lista');  
   }else{
   
       var tablaTarifas= document.getElementById('list-tarifas-body');
       var index = tablaTarifas.rows.length;
       var newRow = tablaTarifas.insertRow(index);  
       newRow.bgColor = "#FFFFFF";
       insertarColumnas(tablaTarifas.rows[index],tablaTarifas.rows[0].cells.length); 
       cargarItemTarifa(tablaTarifas.rows[index]);
       document.getElementById('listaTarifa').focus();
       
   }
   
}

function cargarItemTarifa(row){
   
   var optionSelected=document.getElementById('listaTarifa').options[document.getElementById('listaTarifa').selectedIndex];
   var tarVal= document.getElementById('tar_val').value;
   row.cells[0].innerHTML= optionSelected.text; 
   row.name = 'item-tarifa'; 
   
   row.id= 'tarId-'+ optionSelected.value;
   row.cells[1].innerHTML= tarVal + '<input type="hidden" name="tarifas-hidden"  value="'+optionSelected.value+'#'+tarVal+'" />';
   row.cells[2].innerHTML= '<img  src="img/Delete.png" height="25" width="25"  alt="Eliminar" onclick="eliminarTarifa(\''+row.id+'\')" onmouseover="this.style.cursor=\'hand\';" />';
   row.cells[0].width=203;
   row.cells[1].width=60;
   row.cells[1].align='right';
   
   
}

function eliminarTarifa(id){

   var tablaTarifas= document.getElementById('list-tarifas-body');
   var row = document.getElementById(id);
   
 
   document.getElementById('tar_val').value= row.cells[1].innerHTML.substring(0, row.cells[1].innerHTML.indexOf('<INPUT'));
   
   for(var i = 0 ; i <   document.getElementById('listaTarifa').length ;i++){
      if(document.getElementById('listaTarifa').options[i].value == row.id.substring(6)){
         document.getElementById('listaTarifa').options[i].selected= true;
      }
   }
   tablaTarifas.deleteRow(row.rowIndex);
  
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

function buscarClientes(){
     
     var cliId= document.getElementById('cliId').value;
     var nomCliente= document.getElementById('nomCliente').value;
     
     if(cliId== '' &&  nomCliente.length >2 ){ 
                
        towaysDWR.buscarClientes(nomCliente,buscarClientesCallBack); 
     } 
    
}

function limitarArea(){

 var desc=  document.getElementById('descCliente');  
 if(desc.value.length > 250){
    desc.value= desc.value.substring(0,250); 
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

function buscarClientesCallBack(data){
   if (data.length > 0) {
      var tablaClientes= document.getElementById('tabla-busqueda');
      borrarFilas(tablaClientes);
      document.getElementById('div-clientes').style.display='';
      for(var i=0 ; i<   data.length; i++){
        
        insertarFila(tablaClientes,data[i]);
        
      } 
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






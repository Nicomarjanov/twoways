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

   var tablaTarifas= document.getElementById('list-tarifas');
   var index = tablaTarifas.rows.length;
   var newRow = tablaTarifas.insertRow(index);  
   if (index % 2 !=0)newRow.bgColor = "#FCEEED";
   else newRow.bgColor = "#E8B6B5";
   insertarColumnas(tablaTarifas.rows[index],tablaTarifas.rows[0].cells.length); 
   cargarItemTarifa(tablaTarifas.rows[index]);
}


function cargarItemTarifa(row){
   
   row.cells[0].innerHTML= document.getElementById('listaTarifa').options[document.getElementById('listaTarifa').selectedIndex].text; 
   row.name = 'item-tarifa'; 
   row.id= document.getElementById('listaTarifa').options[document.getElementById('listaTarifa').selectedIndex].value;
   row.cells[1].innerHTML= document.getElementById('tar_val').value;
   
}

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




function buscarClientes(){
     
     var cliId= document.getElementById('cliId').value;
     var nomCliente= document.getElementById('nomCliente').value;
     
     
     if(cliId== '' &&  nomCliente.length >2 ){ 
        
         document.getElementById('div-clientes').style.display='';
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
 
  var tablaClientes= document.getElementById('tabla-busqueda');
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






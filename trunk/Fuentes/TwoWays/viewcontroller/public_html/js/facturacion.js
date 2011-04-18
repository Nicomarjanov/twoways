var mensajeCampoAlert;
var curIdOrigen=0;
function cancelar()
{
    if(confirm('¿Desea cancelar la carga del pago?'))
    {   
        document.getElementById("accion").value='cancelar';
        document.getElementById("cliId").value="";       
        document.forms[0].submit();
    }
}

function nuevaBusqueda()
{ 
    document.getElementById("accion").value='cancelar';
    document.getElementById("cliId").value="";    
    document.forms[0].submit();
    
}
function buscarOrdenes(){

    var banderaMensajeFaltante=false;
    mensajeCampoAlert='';
    mensajeFaltanteAlert = 'Se tiene que completar los siguientes campos: \n';
    var cliId=document.getElementById('listaClientes').options[document.getElementById('listaClientes').selectedIndex].value;

   if(cliId == ""){
        document.getElementById("listaClientes").style.background='red';
        mensajeFaltanteAlert+=' * Seleccionar un cliente de la lista \n';    
        banderaMensajeFaltante=true;
    }
   if (banderaMensajeFaltante){
        var mensajeCampoAlert2 = mensajeFaltanteAlert + '\n';
        alert(mensajeCampoAlert2);
    }else{
       document.getElementById('cliId').value =cliId;
       document.getElementById('accion').value='buscarOrdenes';  
       document.forms[0].submit();
    }
}

function cargar(){
    document.getElementById('invDate').value     = trim(document.getElementById('invDate').value);
    document.getElementById('invTotal').value    = trim(document.getElementById('invTotal').value);

    if(validarCampos())
        grabar(true);
    else
        grabar(false);
}

function validarCampos(){

    var banderaMensajeFaltante=false;
    mensajeCampoAlert='';
    mensajeFaltanteAlert = 'Se tiene que completar los siguientes campos: \n';
    
   if (document.getElementById("invTotal").value =='')
    {
        document.getElementById("invTotal").style.background='Red';
        mensajeFaltanteAlert+= ' * Monto total a facturar \n';
        banderaMensajeFaltante=true;        
    }
       
   if(document.getElementById("listaClientes").options[document.getElementById("listaClientes").selectedIndex].value == ""){
        document.getElementById("listaClientes").style.background='red';
        mensajeFaltanteAlert+=' * Seleccionar un cliente de la lista \n';    
        banderaMensajeFaltante=true;
    }
    
   if(document.getElementById("listaMoneda").options[document.getElementById("listaMoneda").selectedIndex].value == ""){
        document.getElementById("listaMoneda").style.background='red';
        mensajeFaltanteAlert+=' * Seleccionar una moneda de la lista \n';    
        banderaMensajeFaltante=true;
    }
   if(document.getElementById("listaCuentas").options[document.getElementById("listaCuentas").selectedIndex].value == ""){
        document.getElementById("listaCuentas").style.background='red';
        mensajeFaltanteAlert+=' * Seleccionar una cuenta de la lista \n';    
        banderaMensajeFaltante=true;
    }
        
    var fecha = document.getElementById("invDate");

    if(fecha.value != '')
    {
        if (!(isDate(fecha.value)))
        {
        fecha.style.background='Red';
        mensajeFaltanteAlert+= ' * La fecha debe ser dd/mm/aaaa \n';
        banderaMensajeFaltante=true;
        }
    }else{
        document.getElementById("invDate").style.background='Red';
        mensajeFaltanteAlert+= ' * Fecha de pago \n';
        banderaMensajeFaltante=true;
    }
    
    if(document.getElementById("item-ordenes-hidden").value == null){
        mensajeFaltanteAlert+= ' * No exite ninguna orden a facturar para el cliente seleccionado \n';
        banderaMensajeFaltante=true;
    }
    
    var ordClients =document.getElementsByName("listaItems");
    if (ordClients.length > 0){
        for(i=0; i < ordClients.length; i++){
            if (ordClients[i].value == "0"){
                ordClients[i].style.background='red';
                mensajeFaltanteAlert+= " * Debe seleccionar todos los items a facturar de la lista. Si no desea cargar los datos de facturación de alguna order eliminela de la lista";
                banderaMensajeFaltante=true;
            }
        }
    }else{
        mensajeFaltanteAlert+= ' * No exite ninguna orden a facturar para el cliente seleccionado \n';
        banderaMensajeFaltante=true;
    }        
        
    if(banderaMensajeFaltante)
        mensajeCampoAlert=mensajeFaltanteAlert + '\n';    
    
    if (banderaMensajeFaltante)
        return true;
    else
        return false;
}

function grabar(existe)
{
    if(existe)
    {
        alert(mensajeFaltanteAlert);
    }
    else
    {
        document.getElementById("accion").value='guardar';
        document.getElementById("cliId").value=document.getElementById('cliId').value;
        if(confirm("¿Desea imprimir la factura?")){
            document.getElementById("imprimir").value='imprimirFactura';
        }
        document.forms[0].submit();                
    }    
}

function eliminarOrden (rowId, ordTotal, curIdDesde, fecha){
   var tabla = document.getElementById('tabla-ordenes');
   var row = document.getElementById(rowId);   
   var curHasta = document.getElementById("listaMoneda").value;
   var curIdHasta = curHasta.split("#");
   
   tabla.deleteRow(row.rowIndex);
   if (ordTotal > 0){
        towaysDWR.cotizar(fecha, curIdDesde, curIdHasta[0], ordTotal, postEliminar);        
   }
}

function postEliminar (data){

   var total = document.getElementById("invTotal").value;
   total = total.replace(",",".");
   document.getElementById("invTotal").value= (total - data).toFixed(2);
}

function guardarValor(valor){

    curIdOrigen = valor;
}

function cambioValorTotal(valor){

    var total = document.getElementById("invTotal").value;    
    var fecha = document.getElementById("invDate").value;  

    var curIdHasta = valor.split("#");
    var curIdDesde = curIdOrigen.split("#");
    total=total.replace(",",".");
  
    towaysDWR.cotizar(fecha, curIdDesde[0], curIdHasta[0], total, valorCotizacioncallBack);
}

function valorCotizacioncallBack(data){

    document.getElementById("invTotal").value=data.toFixed(2);
}

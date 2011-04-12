var mensajeCampoAlert;

function cancelar()
{
    if(confirm('¿Desea cancelar la carga del pago?'))
    {   
        document.getElementById("accion").value='cancelar';
        document.getElementById("empId").value="";
        document.getElementById("payId").value="";        
        document.forms[0].submit();
    }
}

function nuevaBusqueda()
{
    document.getElementById("accion").value='cancelar';
    document.getElementById("empId").value="";
    document.getElementById("payId").value="";        
    document.forms[0].submit();
    
}

function buscarAsignaciones(){

    var banderaMensajeFaltante=false;
    mensajeCampoAlert='';
    mensajeFaltanteAlert = 'Se tiene que completar los siguientes campos: \n';

    var empArray=document.getElementById('listaEmpleados').options[document.getElementById('listaEmpleados').selectedIndex].value;
    var mes=document.getElementById('listaMes').options[document.getElementById('listaMes').selectedIndex].value;
    var anio=document.getElementById('listaAnio').options[document.getElementById('listaAnio').selectedIndex].value;    
    
   if(empArray == ""){
        document.getElementById("listaEmpleados").style.background='red';
        mensajeFaltanteAlert+=' * Seleccionar un empleado de la lista \n';    
        banderaMensajeFaltante=true;
    }

   if(mes == ""){
        document.getElementById("listaMes").style.background='red';
        mensajeFaltanteAlert+=' * Seleccionar un mes de la lista \n';    
        banderaMensajeFaltante=true;
    }    
    
   if(anio == ""){
        document.getElementById("listaAnio").style.background='red';
        mensajeFaltanteAlert+=' * Seleccionar un año de la lista \n';    
        banderaMensajeFaltante=true;
    }  
    
    if (banderaMensajeFaltante){
        var mensajeCampoAlert2 = mensajeFaltanteAlert + '\n';
        alert(mensajeCampoAlert2);
    }else{

        var empId = empArray.split('#');

        document.getElementById('accion').value='buscarAsignaciones';  
        document.getElementById('empId').value=empId[0];
        document.getElementById('empName').value=empId[1];
        document.getElementById('mesId').value=mes;
        document.getElementById('anioId').value=anio;
        document.forms[0].submit();
    }
}

function cargar(){
   
    document.getElementById('payDate').value     = trim(document.getElementById('payDate').value);
    document.getElementById('payAmount').value    = trim(document.getElementById('payAmount').value);

    if(validarCampos())
        grabar(true);
    else
        grabar(false);
}

function validarCampos(){

    var banderaMensajeFaltante=false;
    mensajeCampoAlert='';
    mensajeFaltanteAlert = 'Se tiene que completar los siguientes campos: \n';
    
   if (document.getElementById("payAmount").value =='')
    {
        document.getElementById("payAmount").style.background='Red';
        mensajeFaltanteAlert+= ' * Monto total de pago \n';
        banderaMensajeFaltante=true;        
    }
       
   if(document.getElementById("listaEmpleados").options[document.getElementById("listaEmpleados").selectedIndex].value == ""){
        document.getElementById("listaEmpleados").style.background='red';
        mensajeFaltanteAlert+=' * Seleccionar un empleado de la lista \n';    
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
        
    var fecha = document.getElementById("payDate");

    if(fecha.value != '')
    {
        if (!(isDate(fecha.value)))
        {
        fecha.style.background='Red';
        mensajeFaltanteAlert+= ' * La fecha debe ser dd/mm/aaaa \n';
        banderaMensajeFaltante=true;
        }
    }else{
        document.getElementById("payDate").style.background='Red';
        mensajeFaltanteAlert+= ' * Fecha de pago \n';
        banderaMensajeFaltante=true;
    }
    
    if(document.getElementById("item-pago-hidden").value == null){
        mensajeFaltanteAlert+= ' * No exite ningún proyecto a pagar para el empleado seleccionado \n';
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
        alert(mensajeExisteItem);
    }
    else
    {                
        document.getElementById("accion").value='guardar';
        document.getElementById("empId").value=document.getElementById('empId').value;
        if(confirm("¿Desea imprimir el recibo de pago?")){
            document.getElementById("imprimir").value='imprimirPago';
        }
        document.forms[0].submit();                
    }
}

function eliminarPagoAsignacion(padId,praTotal){
   var tabla = document.getElementById('tabla-asignaciones');
   var row = document.getElementById(padId);   
   var total = document.getElementById("payAmount").value;
   total = total.replace(",",".");

   tabla.deleteRow(row.rowIndex);
   if (praTotal > 0){
        document.getElementById("payAmount").value= (total - praTotal).toFixed(2);
   }
}

function cotizar(){
    var total = document.getElementById("payAmount").value;
    var curIdDesde = document.getElementById("curIdOrigen").value;    
    var mes = document.getElementById("mesId").value;
    var anio = document.getElementById("anioId").value;    
    var currency = document.getElementById("listaMoneda").value;
    var curIdHasta = currency.split("#");

    total=total.replace(",",".");
    for (var i=1; i < 13; i++){
        if (document.getElementById('listaMes').options[i].value ==mes){
            if (i < 10) var mesId ='0'+i;
            else var mesId =i;
        }
    }
    alert(mesId);
    towaysDWR.cotizarPago(mesId, anio, curIdDesde, curIdHasta[0], total, valorCotizacioncallBack);
    document.getElementById("curIdOrigen").value=curId[0];
}

function valorCotizacioncallBack(data){

    document.getElementById("payAmount").value=data.toFixed(2);
}

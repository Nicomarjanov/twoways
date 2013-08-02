var mensajeCampoAlert;
var curIdOrigen;

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
{  // document.getElementById('buscar').disabled=false;
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
        alert(mensajeFaltanteAlert);
    }
    else
    {                
        document.getElementById("accion").value='guardar';
        document.getElementById("empId").value=document.getElementById('empId').value;
        document.forms[0].submit();                
    }
}

function eliminarPagoAsignacion(rowId, praTotal, curIdDesde, fecha){
   var tabla = document.getElementById('tabla-asignaciones');
   var row = document.getElementById(rowId);   
   var curHasta = document.getElementById("listaMoneda").value;
   var curIdHasta = curHasta.split("#");
   
   tabla.deleteRow(row.rowIndex);
   if (praTotal > 0){
        towaysDWR.cotizar(fecha, curIdDesde, curIdHasta[0], praTotal, postEliminar);
   }
}

function postEliminar(data){
   var total = document.getElementById("payAmount").value;
   total = total.replace(",",".");
   document.getElementById("payAmount").value= (total - data).toFixed(2);

}

function guardarValor(valor){
    curIdOrigen = valor;
}
function cambioValorTotal(valor){

    var total = document.getElementById("payAmount").value;    
    var fecha = document.getElementById("payDate").value;  
    var curIdHasta = valor.split("#");
    var curIdDesde = curIdOrigen.split("#");
    total=total.replace(",",".");
        
    towaysDWR.cotizar(fecha, curIdDesde[0], curIdHasta[0], total, valorCotizacioncallBack);

}

function valorCotizacioncallBack(data){
    var aux = document.getElementById("listaMoneda").value;
    var simbolo = aux.split("#");
    alert('La moneda ha cambiado. El nuevo Total a pagar es: '+simbolo[1]+' '+data.toFixed(2));
    document.getElementById("payAmount").value=data.toFixed(2);
}

function imprimirPago(payId,empId,payDate,curSymbol,payAmount){
   if(confirm("¿Desea imprimir el recibo de pago?")){
      var currencyId= curSymbol.substring(0,1);
      var currencySymbol= curSymbol.substring(2);
      popupWindow = window.open('imprimirPagos?payId='+payId+'&empId='+empId+'&payAmount='+payAmount+'&currencyId='+currencyId+'&currencySymbol='+currencySymbol,'Imprimir pago: '+payId,'height=1400,width=1600,left=10,top=10,resizable=yes,scrollbars=yes,toolbar=yes,menubar=no,location=no,directories=no,status=yes');

    }
    
}
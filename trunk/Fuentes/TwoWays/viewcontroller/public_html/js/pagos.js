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

function buscarAsignaciones(){

    var empId=document.getElementById('listaEmpleados').options[document.getElementById('listaEmpleados').selectedIndex].value;
    document.getElementById('accion').value='buscarAsignaciones';  
    document.getElementById('empId').value=empId;
    document.forms[0].submit();
}

function cargar(){
   
    document.getElementById('payDate').value     = trim(document.getElementById('payDate').value);
    document.getElementById('payAmount').value    = trim(document.getElementById('payAmount').value);

    if(validarCampos())
        alert(mensajeCampoAlert);
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
        document.getElementById("empId").value=document.getElementById('listaEmpleados').options[document.getElementById('listaEmpleados').selectedIndex].value;
        document.forms[0].submit();                
    }
}
function nuevaBusqueda()
{  
    document.getElementById("accion").value='cancelar';
    document.getElementById('empId').value="";
    document.getElementById('mesId').value="";
    document.getElementById('anioId').value="";
    document.getElementById('curId').value=""; 
    document.forms[0].submit();    
}

function buscarIngresos(){


    var empId=document.getElementById('listaEmpleados').options[document.getElementById('listaEmpleados').selectedIndex].value;
    var mes=document.getElementById('listaMes').options[document.getElementById('listaMes').selectedIndex].value;
    var anio=document.getElementById('listaAnio').options[document.getElementById('listaAnio').selectedIndex].value;    
    var curId=document.getElementById('listaMoneda').options[document.getElementById('listaMoneda').selectedIndex].value;    

    document.getElementById('accion').value='buscar';  
    document.getElementById('empId').value=empId;
    document.getElementById('mesId').value=mes;
    document.getElementById('anioId').value=anio;
    document.getElementById('curId').value=curId;        
    document.forms[0].submit();

}
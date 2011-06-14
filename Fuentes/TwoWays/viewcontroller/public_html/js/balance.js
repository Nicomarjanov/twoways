function nuevaBusqueda()
{  
    document.getElementById("accion").value='cancelar';
    document.getElementById('mesId').value="";
    document.getElementById('anioId').value="";
    document.forms[0].submit();    
}

function BuscarBalance(){

    var mes=document.getElementById('listaMes').options[document.getElementById('listaMes').selectedIndex].value;
    var anio=document.getElementById('listaAnio').options[document.getElementById('listaAnio').selectedIndex].value;      
    
    document.getElementById('accion').value='buscar';  
    document.getElementById('mesId').value=mes;
    document.getElementById('anioId').value=anio;      
    document.forms[0].submit();

}
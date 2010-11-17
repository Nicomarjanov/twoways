function isAlfabeto(texto)
{
    validar=/^[A-z]*$/;
    if(validar.test(texto))
        return true;
    else
        return false;
}

function isAlfabetoConEspacion(texto)
{
    validar=/^[A-z|\s]*$/;
    if(validar.test(texto))
        return true;
    else
        return false;
}

function trim(s) 
{   
    s=s.replace(/^\s+/, "");
    s=s.replace(/\s+$/, "");
    return (s);
}

function trabar(id)
{	
        array=id.split(',');
        for(i=0;i<array.length;i++)
                document.getElementById(array[i]).disabled=true;
        
}

function destrabar(id)
{
        array=id.split(',');
        for(i=0;i<array.length;i++)
                document.getElementById(array[i]).disabled=false;
}



function borrarFilas(tabla){
   
   var filasInicial = tabla.rows.length;
   for(var i=1 ; i<  filasInicial; i++){
        tabla.deleteRow(1);
   } 
   
}

function insertarFila(tabla,data){
   var index = tabla.rows.length;
   var newRow = tabla.insertRow(index); 
   if (index % 2 !=0)newRow.bgColor = "#FCEEED";
   else newRow.bgColor = "#E8B6B5";
   insertarColumnas(tabla.rows[index],tabla.rows[0].cells.length); 
   cargarDatosColumna(tabla.rows[index],data);
}

function insertarColumnas(row, size){

  for(var i=0 ; i<   size; i++){
        row.insertCell(i);
  } 
    
}

function isNumber(campo){
    if(/^[0-9]+$/.test(campo)) return true;
    return false;
}

function validarEmail(valor) {
    if (/^\w+([\.\-\_]?\w+)*@\w+([\.-]?\w+)*(\.\D{2,4})+$/.test(valor)){
        return true;
    } 
    else {
        return false;
    }
}

function isFloat(campo){

  return (/^\d+(\.\d+)?$/.test(campo));
}



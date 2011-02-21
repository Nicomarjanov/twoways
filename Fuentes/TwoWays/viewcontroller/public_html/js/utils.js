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

  return (/^\d+(\.\d+)?$/.test(trim(campo)));
}


function limitarArea(){

 
 
 var descArray=  document.getElementsByTagName('textarea');  
 
 if(descArray != null){ 

     for(var i =0 ; i< descArray.length; i++ ){ 
         var desc= descArray[i];
         if(desc.value.length > 250){
            desc.value= desc.value.substring(0,250); 
         }
     }
 }

}

function formatoFechaKeyPress(fecha)
{
    if(fecha.value.length ==2 )
    {  
        fecha.value += "/"; 
    }
    if(fecha.value.length ==5 )
    {  
        fecha.value += "/"; 
    }
}

function isDate(campo){
    
    
    
    if(/^((0[1-9]|[1-2][0-9]|3[0-1])[/](0[1-9]|1[0-2])[/](\d{4}) (0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]))|((0[1-9]|[1-2][0-9]|3[0-1])[/](0[1-9]|1[0-2])[/](\d{4}))$/.test(campo)){
      
       var dia = campo.substring(0,2);
       var mes = campo.substring(3,5);
       var anio = campo.substring(6,10);
       
       if (parseInt(mes) > 12 ){
         return false; 
       }else if ( parseInt(dia) <= 31 && mes == '01' || mes == '03' || mes == '05'  || mes== '07' || mes == '08'  || mes == '10' || mes == '12' ){
          
           return true; 
       }else if ( parseInt(dia) <= 30 && ( mes == '04' || mes =='06'  || mes == '09'|| mes == '11'  )){
           return true; 
           
       }else if (parseInt(dia) <= 28 && mes == '02'  ||   (parseInt(dia) <= 29 && mes == '02' && parseInt(anio) % 4 == 0 )) {
        
        return true; 
       
       } 
       else return false;
      }else return false;
 }

function validarSiNumeroInCh(e)
{
    var keynum;
    var keychar;
    var numcheck;
    if(window.event) // IE
    {
        keynum = e.keyCode;
    }
    else if(e.which) // Netscape/Firefox/Opera
    {
        keynum = e.which;
    }
        
    keychar = String.fromCharCode(keynum);
    numcheck = /\d/;
    return numcheck.test(keychar);
}


function validarFecha(fecha,origen)
{    
    var fechaCadena = fecha.value.split("/");
    
    
    if(fecha.value != null && fecha.value.length != 0)
    {
        if(fecha.value.length < 10)
        {
            alert('Debe ingresar la fecha '+ origen +' en formato válido (dd/mm/yyyy).');          
            fecha.focus();
            fecha.select();
            return false;
        }
        else
        {
            var fechaValidacion=fecha.value.split("/");
            if (fechaValidacion[2]==null)
            {
                alert('Debe ingresar la fecha '+ origen +' en formato válido (dd/mm/yyyy).');                 
                fecha.focus(); 
                fecha.select();               
                return false;
            }
            var anioValidacion = fechaValidacion[2].split(" ");
            if ( isNaN(fechaValidacion[0])|| fechaValidacion[0].length > 2 || fechaValidacion[1].length > 2 || anioValidacion[0].length > 4 || isNaN(anioValidacion[0]) || fechaValidacion[0] == null || fechaValidacion[1] == null || anioValidacion[0] == null)
            {
                alert('Debe ingresar la fecha '+ origen +' en formato válido (dd/mm/yyyy).');                
                fecha.focus(); 
                fecha.select();
                return false;
            }
            else
            {          
                if (fechaValidacion[0]<1 || fechaValidacion[0]>31 || isNaN(fechaValidacion[0]))
                {
                    alert('El día debe estar entre 1 y 31.');                  
                    fecha.focus(); 
                    fecha.select();
                    return false;
                } 
                else
                {
                    //hasta aca el dia es correcto
                    var dia= "dd";
                    if ( isNaN(fechaValidacion[1])||fechaValidacion[1]<1 || fechaValidacion[1]>12 ||isNaN(fechaValidacion[1])) 
                    {
                        alert('El mes debe estar entre 1 y 12.');                             
                        fecha.focus(); 
                        fecha.select();
                        return false;
                    }
                    else 
                    {
                        //hasta aca el mes es correcto
                        var mes= "mm";
                        if ( isNaN(anioValidacion[0])||anioValidacion[0] <1900 || anioValidacion[0] > 9999 ) 
                        {
                            alert('El año debe estar entre 1900 y 9999.');                          
                            fecha.focus(); 
                            fecha.select();
                            return false;
                        }
                        else
                        {
                            //hasta aca el año es correcto
                            var anio="aa";
                            if (fechaValidacion[0]>30 && (fechaValidacion[1]==2 || fechaValidacion[1]==4 || fechaValidacion[1]==6 || fechaValidacion[1]==9 || fechaValidacion[1]==11)) 
                            {
                                alert('Dichos meses no tienen 31 días.');
                                document.getElementById('LimpiarFiltros').disabled=false,
                                document.getElementById('verRep').disabled=false;                                       
                                fecha.focus(); 
                                fecha.select();
                                return false;
                            }
                            else
                            {
                                if(fechaValidacion[0]>29 && fechaValidacion[1]==2 )
                                {
                                    alert('El mes de febrero no tiene '+fechaValidacion[0]+' días');                                  
                                    fecha.focus(); 
                                    fecha.select();
                                    return false;
                                }
                                else
                                {
                                    if( fechaValidacion[1]==2 && fechaValidacion[0]>28 && anioValidacion[0]%4!=0 )
                                    {
                                        alert('Dicho año no es bisiesto.');                                         
                                        fecha.focus(); 
                                        fecha.select();
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    return true;
}


String.prototype.startsWith = function(str)
{return (this.match("^"+str)==str)}



function arrayFecha(fecha1){ 
   var result = new Array(); 
   var fecha= trim(fecha1).split('/');
    result[2]= parseInt(fecha[0]);
    result[1]= parseInt(fecha[1]); 
    aux = fecha[2].split(' ');
    result[0]= parseInt(aux[0]);
  
   if(aux != null && aux.length > 0 && aux[1] != null  && aux[1] != '' ){
      
      var hora = aux[1].split(':');
      result[3]= parseInt(hora[0]);
      result[4]= parseInt(hora[1]);
      
   }else{
      result[3]= 0;
      result[4]= 0;
   }
   
  
   return result;
}

function compararFecha(fecha1 , fecha2){

    var auxFecha1 = arrayFecha(fecha1);
    var auxFecha2 = arrayFecha(fecha2);
    
    for(var i=0 ; i < auxFecha1.length;i++ ){
         
                 
         if(auxFecha1[i] > auxFecha2[i]){
            return 1
         }else if(auxFecha1[i] == auxFecha2[i]){
             
         }else{
         
           return -1
         }
         
    }
    
    return 0;

}


function cancelar()
{
    if(confirm('¿Desea cancelar la carga de la cotización?'))
    {   
        document.getElementById("accion").value='cancelar';
        document.forms[0].submit();
    }
}

function agregar()
{
  
    document.getElementById('listaMoneda').style.background  = '#FFFFFF';
    document.getElementById('cotValue').style.background  = '#FFFFFF';
    document.getElementById('cotValue').style.background  = '#FFFFFF';
    
    document.getElementById('cotDate').value     = trim(document.getElementById('cotDate').value);
    document.getElementById('cotValue').value     = trim(document.getElementById('cotValue').value).replace(".",",");
    
    if(validarCampos())
    {
        alert(mensajeCampoAlert);
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

/******************************************************************************/
//   metodos de validacion de campos
/******************************************************************************/
function validarCampos()
{
    var banderaMensajeFaltante=false;
    mensajeCampoAlert='';
    mensajeFaltanteAlert = 'Se tiene que completar los siguientes campos: \n';
    
    /************************************************/
    // valido el que los campos no esten vacíos
    /************************************************/

    if( document.getElementById("cotDate").value != '')
    {if (!(isDate(document.getElementById("cotDate").value)))
        {
        document.getElementById("cotDate").style.background='Red';
        mensajeFaltanteAlert+= ' * La fecha debe ser dd/mm/aaaa \n';
        banderaMensajeFaltante=true;
        }        
    }else{
        document.getElementById("cotDate").style.background='Red';
        mensajeFaltanteAlert+= '* Fecha de la cotización \n';
        banderaMensajeFaltante=true;
    }
        
    if( document.getElementById("cotValue").value != '')
    {if( document.getElementById("cotValue").value <= 0)
        {
        document.getElementById("cotValue").style.background='Red';
        mensajeFaltanteAlert+= '* El valor de la cotización debe ser mayor a 0 \n';
        banderaMensajeFaltante=true;
        }
    }
      else{
        document.getElementById("cotValue").style.background='Red';
        mensajeFaltanteAlert+= '* Valor de la cotización \n';
        banderaMensajeFaltante=true;
    }
    
    if(document.getElementById("listaMoneda").selectedIndex==0)
    {
        document.getElementById("listaMoneda").style.background='red';
        mensajeFaltanteAlert+='* Seleccionar una moneda del combo \n';    
        banderaMensajeFaltante=true;
    }

    if(banderaMensajeFaltante)
        mensajeCampoAlert=mensajeFaltanteAlert + '\n';    
    
    if (banderaMensajeFaltante)
        return true;
    else
        return false;
}

function buscarCotizaciones(){
     
     var curId= document.getElementById('listaMoneda').value;

     if(curId != ''){ 
        towaysDWR.buscarCotizaciones(curId,buscarCotizacionesCallBack); 
     }     
}

function cargarDatosColumna(row,data){
    var month=new Array(12);
    month[0]="01";
    month[1]="02";
    month[2]="03";
    month[3]="04";
    month[4]="05";
    month[5]="06";
    month[6]="07";
    month[7]="08";
    month[8]="09";
    month[9]="10";
    month[10]="11";
    month[11]="12";
   row.cells[0].innerHTML=(data.currencyTO.curName==null)?'':data.currencyTO.curName;
   var aux = (data.cucDate==null)?'':data.cucDate;   

   if (aux != '' || aux.length > 0){
        var fecha = new Date(aux);
        var dia = fecha.getDate();
        if ( dia< 10) dia="0"+dia;
        row.cells[1].innerHTML = dia+'/'+month[fecha.getMonth()]+'/'+fecha.getFullYear();
   }

   row.cells[2].innerHTML=(data.cucValue==null)?'':data.cucValue;      
   if (data.cucEraseDate != null){
         row.cells[3].innerHTML ='<img  src="img/Erase.png" height="20" width="20"  alt="Cotización eliminada el día: \''+data.cucEraseDate+'\'"  />';
    }else{
         row.cells[3].innerHTML ='<img  src="img/Delete.png" height="20" width="20"  alt="Eliminar cotización" onclick="eliminarCotizacion(\''+data.cucId+'\');" onmouseover="this.style.cursor=\'hand\';"  />';
    }
}

function buscarCotizacionesCallBack(data){

 if (data != null && data.length > 0) {
    document.getElementById('div-cotizaciones').style.display='';
    var tablaBusqueda= document.getElementById('tabla-busqueda');
    borrarFilas(tablaBusqueda);
  
    for(var i=0 ; i<   data.length; i++){
        insertarFila(tablaBusqueda,data[i]);    
    }
 }
 else {
     document.getElementById('div-cotizaciones').style.display='none';
 }
}

function  eliminarCotizacion(cucId){

    if (cucId != null){
         if (confirm('¿Esta seguro que desea eliminar la cotización de la moneda?') ){
                towaysDWR.deleteCotizacion(cucId,postEliminar); 
         }
    }
}

function postEliminar(data){
  
   var tablaBusqueda= document.getElementById('tabla-busqueda'); 
   if(data){
      alert('La cotización de la moneda se eliminó con éxito ');
      borrarFilas(tablaBusqueda);
      window.location.href='cotizaciones';
   }else{
      alert('La cotización de la moneda no se pudo eliminar ');
   }
}
var mensajeCampoAlert;

function cancelar()
{
    if(confirm('¿Desea cancelar la carga de la tarifa?'))
    {   
        document.getElementById("accion").value='cancelar';
        document.forms[0].submit();
    }
}

/******************************************************************************/
//   metodos utilizados para dar de alta los item
/******************************************************************************/
function agregar()
{
    //trabar(idsPantalla());
    
    document.getElementById('nomTarifa').style.background  = '#FFFFFF';
    document.getElementById('descTarifa').style.background  = '#FFFFFF';
    document.getElementById('tipoTarifa').style.background    = '#FFFFFF';
    document.getElementById('listaMoneda').style.background = '#FFFFFF';
    
    document.getElementById('nomTarifa').value     = trim(document.getElementById('nomTarifa').value);
    document.getElementById('descTarifa').value     = trim(document.getElementById('descTarifa').value);
    
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
    
    if( document.getElementById("nomTarifa").value == '')
    {
        document.getElementById("nomTarifa").style.background='Red';
        mensajeFaltanteAlert+= '* Nombre de la tarifa \n';
        banderaMensajeFaltante=true;
    }
    
    if(document.getElementById("tipoTarifa").selectedIndex==0)
    {
        document.getElementById("tipoTarifa").style.background='red';
        mensajeFaltanteAlert+='* Seleccionar un tipo de tarifa del combo \n';    
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

function buscarTarifas()
{
     
     
     var ratId= document.getElementById('ratId').value;
     var nomTarifa= document.getElementById('nomTarifa').value;
     
     
     if(ratId== '' &&  nomTarifa.length >2 ){ 

        towaysDWR.buscarTarifas(nomTarifa,buscarTarifasCallBack); 
     } 
    
}

function cargarDatosColumna(row,data){

    
   row.cells[0].innerHTML=(data.ratName==null)?'':'<a href="tarifas?ratId='+data.ratId+'" >'+data.ratName+'</a>';
   row.cells[1].innerHTML=(data.rateTypesTO.rtyName==null)?'':data.rateTypesTO.rtyName;   
   row.cells[2].innerHTML=(data.currencyTO.curName==null)?'':data.currencyTO.curName;      
   row.cells[3].innerHTML=(data.ratDescription==null)?'':data.ratDescription;
   var editar = '<img src="img/edit.png"  height="20" width="20" alt="Editar" onclick="javascript:window.location.href=\'tarifas?ratId='+data.ratId+'\'" onmouseover="this.style.cursor=\'hand\';"/> ';
   var eliminado='';
   var eliminar='';
   if (data.ratEraseDate != null ){
      eliminado = '<img  src="img/Erase.png" height="20" width="20" alt="Empleado eliminado el día: \''+data.ratEraseDate+'\'"  />' ;
   }
   else {
      eliminar = '<img  src="img/Delete.png" height="20" width="20" alt="Eliminar" onclick="eliminarTarifa('+data.ratId+')" onmouseover="this.style.cursor=\'hand\';"/>'
   }
   row.cells[4].innerHTML= editar + ' ' + eliminar + eliminado;
   
}


function  eliminarTarifa(ratId){
if (ratId != null){
     if (confirm('¿Esta seguro que desea eliminar la tarifa?') ){
     
        towaysDWR.deleterTarifa(ratId,postEliminar); 
     }
}
else {
     alert('Debe seleccionar una tarifa antes de eliminar');
     }
}

function postEliminar(data){
  
   var tablaTarifas= document.getElementById('tabla-busqueda'); 
   if(data){
      alert('La tarifa se eliminó con éxito ');
      borrarFilas(tablaTarifas);
      window.location.href='tarifas';
   }else{
      alert('La tarifa no se pudo eliminar ');
   }
}

function buscarTarifasCallBack(data){
  
  if (data.length > 0) {
      document.getElementById('div-tarifas').style.display='';
      var tablaTarifas= document.getElementById('tabla-busqueda');
      borrarFilas(tablaTarifas);
      for(var i=0 ; i<   data.length; i++){
        
        insertarFila(tablaTarifas,data[i]);        
      }
   }else {
     document.getElementById('div-tarifas').style.display='none';
  }
}


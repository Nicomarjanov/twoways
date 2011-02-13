function cancelar()
{
    if(confirm('¿Desea cancelar la carga de la tarifa?'))
    {   
        document.getElementById("accion").value='cancelar';
        document.forms[0].submit();
    }
}

function agregar()
{
  
    document.getElementById('itmNombre').style.background  = '#FFFFFF';
    document.getElementById('descItem').style.background  = '#FFFFFF';
    
    document.getElementById('itmNombre').value     = trim(document.getElementById('itmNombre').value);
    document.getElementById('descItem').value     = trim(document.getElementById('descItem').value);
    
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
    
    if( document.getElementById("itmNombre").value == '')
    {
        document.getElementById("itmNombre").style.background='Red';
        mensajeFaltanteAlert+= '* Nombre del item \n';
        banderaMensajeFaltante=true;
    }
    
    if(document.getElementById("tipoItem").selectedIndex==0)
    {
        document.getElementById("tipoItem").style.background='red';
        mensajeFaltanteAlert+='* Seleccionar un tipo de item del combo \n';    
        banderaMensajeFaltante=true;
    }

    if(banderaMensajeFaltante)
        mensajeCampoAlert=mensajeFaltanteAlert + '\n';    
    
    if (banderaMensajeFaltante)
        return true;
    else
        return false;
}

function buscarItems(){
     
     var itmId= document.getElementById('itmId').value;
     var itmNombre= document.getElementById('itmNombre').value;     

     if(itmId== '' &&  itmNombre.length >2 ){ 
        
        towaysDWR.buscarItems(itmNombre,buscarItemsCallBack); 
     }     
}

function cargarDatosColumna(row,data){
    
   row.cells[0].innerHTML=(data.itmId==null)?'':'<a href="items?itmId='+data.itmId+'" >'+data.itmName+'</a>';
   row.cells[1].innerHTML=(data.itmType==null)?'':data.itmType;   
   row.cells[2].innerHTML=(data.itmDescription==null)?'':data.itmDescription;      

   var editar = '<img src="img/edit.png"  height="20" width="20" alt="Editar" onclick="javascript:window.location.href=\'items?itmId='+data.itmId+'\'" onmouseover="this.style.cursor=\'hand\';"/> ';
   var eliminado='';
   var eliminar='';
   if(data.itmEraseDate != null){
       eliminado = '<img  src="img/Erase.png" height="20" width="20"  alt="Item eliminado el día: \''+data.itmEraseDate+'\'"  />';
   }
   else {
       eliminar = '<img  src="img/Delete.png" height="20" width="20" alt="Eliminar" onclick="eliminarItem('+data.itmId+')" onmouseover="this.style.cursor=\'hand\';"/>'
   }
   row.cells[3].innerHTML= editar + ' ' + eliminar + eliminado;
}

function  eliminarItem(itmId){
 
 if (itmId != null){
     if (confirm('¿Esta seguro que desea eliminar el item?') ){
        
        towaysDWR.deleterItem(itmId,postEliminar); 
     }
 }
 else{ alert('Debe seleccionar un item antes de eliminar');
 }
}

function postEliminar(data){
  
   var tablaTarifas= document.getElementById('tabla-busqueda'); 
   if(data){
      alert('El item se elimino con exito ');
      borrarFilas(tablaTarifas);
      window.location.href='items';
   }else{
      alert('El item no se pudo eliminar ');
   }
}

function buscarItemsCallBack(data){

 if (data.length > 0) {
    document.getElementById('div-items').style.display='';
    var tablaBusqueda= document.getElementById('tabla-busqueda');
    borrarFilas(tablaBusqueda);
  
    for(var i=0 ; i<   data.length; i++){
        insertarFila(tablaBusqueda,data[i]);    
    }
 }
 else {
     document.getElementById('div-items').style.display='none';
 }
}

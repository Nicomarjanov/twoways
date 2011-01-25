function cancelar()
{
    if(confirm('�Desea cancelar la carga de la cuenta?'))
    {   
        document.getElementById("accion").value='cancelar';
        document.forms[0].submit();
    }
}

function agregar()
{
  
    document.getElementById('accNombre').style.background  = '#FFFFFF';
    document.getElementById('descAcc').style.background  = '#FFFFFF';
    document.getElementById('accNumero').style.background  = '#FFFFFF';
    document.getElementById('detalleAcc').style.background  = '#FFFFFF';
    document.getElementById('accNombre').value     = trim(document.getElementById('accNombre').value);
    document.getElementById('descAcc').value     = trim(document.getElementById('descAcc').value);
    document.getElementById('accNumero').value     = trim(document.getElementById('accNumero').value);
    document.getElementById('detalleAcc').value     = trim(document.getElementById('detalleAcc').value);   
    
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
    // valido que los campos no esten vac�os
    /************************************************/
    
    if( document.getElementById("accNombre").value == '')
    {
        document.getElementById("accNombre").style.background='Red';
        mensajeFaltanteAlert+= '* Nombre de la cuenta \n';
        banderaMensajeFaltante=true;
    }
    
    if(banderaMensajeFaltante)
        mensajeCampoAlert=mensajeFaltanteAlert + '\n';    
    
    if (banderaMensajeFaltante)
        return true;
    else
        return false;
}

function buscarCuentas(){

     var accId= document.getElementById('accId').value;
     var accNombre= document.getElementById('accNombre').value;     

     if(accId == '' &&  accNombre.length >2 ){ 
        towaysDWR.buscarCuentas(accNombre,buscarCuentasCallBack); 
     }     
}

function cargarDatosColumna(row,data){
    
   row.cells[0].innerHTML=(data.accId==null)?'':'<a href="cuentas?accId='+data.accId+'" >'+data.accName+'</a>';
   row.cells[1].innerHTML=(data.accNumber==null)?'':data.accNumber;   
   row.cells[2].innerHTML=(data.accDescription==null)?'':data.accDescription;      
   row.cells[3].innerHTML=(data.accDetails==null)?'':data.accDetails;
   
   var editar = '<img src="img/edit.png"  height="25" width="25" alt="Editar" onclick="javascript:window.location.href=\'cuentas?accId='+data.accId+'\'" onmouseover="this.style.cursor=\'hand\';"/> ';
   var eliminar = '<img  src="img/Delete.png" height="25" width="25" alt="Eliminar" onclick="eliminarCuenta('+data.accId+')" onmouseover="this.style.cursor=\'hand\';"/>'
   row.cells[4].innerHTML= editar + ' ' + eliminar;
}

function  eliminarCuenta(accId){

 if (accId != null){ 
     if (confirm('�Esta seguro que desea eliminar la cuenta?') ){    
        towaysDWR.deleterAccount(accId,postEliminar); 
     }
  }
  else {
     alert('Debe seleccionar una cuenta antes de eliminar');
 }
}

function postEliminar(data){
  
   var tablaBusqueda= document.getElementById('tabla-busqueda'); 
   if(data){
      alert('La cuenta se elimin� con �xito ');
      borrarFilas(tablaBusqueda);
      window.location.href='cuentas';
   }else{
      alert('La cuenta no se pudo eliminar ');
   }
}

function buscarCuentasCallBack(data){

 if (data.length > 0) {
 
    document.getElementById('div-cuentas').style.display='';
    var tablaBusqueda= document.getElementById('tabla-busqueda');
    borrarFilas(tablaBusqueda);
  
    for(var i=0 ; i<   data.length; i++){
        insertarFila(tablaBusqueda,data[i]);    
    }
 }
 else {
     document.getElementById('div-cuentas').style.display='none';
 }
}

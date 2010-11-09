var mensajeCampoAlert;

function cancelar()
{
    if(confirm('¿Desea cancelar la carga del empleado?'))
    {   
        document.getElementById("accion").value='cancelar';
        document.forms[0].submit();
    }
}

function ocultarOpcionales(){
   var btnMas=document.getElementById('aMas'); 
   var btnMenos=document.getElementById('aMenos'); 
   var op1=document.getElementById('trOpcionales1'); 
   var op2=document.getElementById('trOpcionales2'); 
   btnMas.style.display='';
   btnMenos.style.display='none';
   op1.style.display='none';
   op2.style.display='none';


}

function mostrarOpcionales(){
   var btnMas=document.getElementById('aMas'); 
   var btnMenos=document.getElementById('aMenos'); 
   var op1=document.getElementById('trOpcionales1'); 
   var op2=document.getElementById('trOpcionales2'); 
   btnMas.style.display='none';
   btnMenos.style.display='';
   op1.style.display='';
   op2.style.display='';

}
/******************************************************************************/
//   metodos utilizados para dar de alta los item
/******************************************************************************/
function agregar()
{
   
    document.getElementById('empFirstName').style.background  = '#FFFFFF';
    document.getElementById('empLastName').style.background  = '#FFFFFF';
    document.getElementById('empMail').style.background  = '#FFFFFF';
    document.getElementById('empMsn').style.background  = '#FFFFFF';
    document.getElementById('empBirth').style.background = '#FFFFFF';
    document.getElementById('empMobileNumber').style.background    = '#FFFFFF';
    document.getElementById('empPhoneNumber').style.background = '#FFFFFF';
    document.getElementById('empAddress').style.background = '#FFFFFF';
    document.getElementById('empLocation').style.background = '#FFFFFF';
    document.getElementById('empAvailability').style.background = '#FFFFFF';
    document.getElementById('empObservations').style.background = '#FFFFFF';    
    
    document.getElementById('empFirstName').value       = trim(document.getElementById('empFirstName').value);
    document.getElementById('empLastName').value       = trim(document.getElementById('empLastName').value);    
    document.getElementById('empMail').value     = trim(document.getElementById('empMail').value);
    document.getElementById('empMsn').value       = trim(document.getElementById('empMsn').value); 
    document.getElementById('empBirth').value       = trim(document.getElementById('empBirth').value);     
    document.getElementById('empAddress').value     = trim(document.getElementById('empAddress').value);
    document.getElementById('empLocation').value    = trim(document.getElementById('empLocation').value);
    document.getElementById('empAvailability').value       = trim(document.getElementById('empAvailability').value);
    document.getElementById('empObservations').value       = trim(document.getElementById('empObservations').value);
   
    
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

function buscarEmpleados(){
     
     var empId= document.getElementById('empId').value;
     var nomEmp= document.getElementById('empFirstName').value;

     if(empId== '' &&  nomEmp.length >2 )
            towaysDWR.buscarEmpleados(nomEmp,buscarEmpleadosCallBack);        
}

function cargarDatosColumna(row,data){
    
   row.cells[0].innerHTML=(data.empFirstName==null)?'':'<a href="empleados?empId='+data.empId+'" >'+data.empFirstName+'</a>';   
   row.cells[1].innerHTML=(data.empLastName==null)?'':data.empLastName;   
   row.cells[2].innerHTML=(data.empMail==null)?'':data.empMail;     
   row.cells[3].innerHTML=(data.empMsn==null)?'':data.empMsn;   
   row.cells[4].innerHTML=(data.empBirth==null)?'':data.empBirth;   
   /*row.cells[5].innerHTML=(data.usrMobileNumber==null)?'':data.usrMobileNumber;
   row.cells[6].innerHTML=(data.usrPhoneNumber==null)?'':data.usrPhoneNumber;
   row.cells[7].innerHTML=(data.usrOfficeNumber==null)?'':data.usrOfficeNumber;   */
   var editar = '<img src="img/edit.png"  height="25" width="25"  alt="Editar" onclick="javascript:window.location.href=\'empleados?empId='+data.empId+'\'" onmouseover="this.style.cursor=\'hand\';" /> ';
   var eliminar = '<img  src="img/Delete.png" height="25" width="25"  alt="Eliminar" onclick="eliminarEmpleado('+data.empId+')" onmouseover="this.style.cursor=\'hand\';" />'

   row.cells[5].innerHTML= editar + ' ' + eliminar;
}

function  eliminarEmpleado(empId){
 
 if (confirm('¿Esta seguro que desea eliminar el empleado?') ){
    
    towaysDWR.deleterEmpleado(empId,postEliminar); 
 }
}

function postEliminar(data){
  
   var tablaBusqueda= document.getElementById('tabla-busqueda'); 
   if(data){
      alert('El empleado se elimino con exito ');
      borrarFilas(tablaBusqueda);
      window.location.href='empleados';
   }else{
      alert('El empleado no se pudo eliminar ');
   }
}

function buscarEmpleadosCallBack(data){
  if (data.length > 0) {
      document.getElementById('div-empleados').style.display='';
      var tablaBusqueda= document.getElementById('tabla-busqueda');
      borrarFilas(tablaBusqueda);
      for(var i=0 ; i<   data.length; i++){
        
        insertarFila(tablaBusqueda,data[i]);    
      }
  }
}

function validarCampos()
{
    var banderaMensajeFaltante=false;
    mensajeCampoAlert='';
    mensajeFaltanteAlert = 'Se tiene que completar los siguientes campos: \n';
    
    /************************************************/
    // valido el que los campos no esten vacíos
    /************************************************/
    
    if( document.getElementById("empFirstName").value == '')
    {
        document.getElementById("empFirstName").style.background='Red';
        mensajeFaltanteAlert+= ' * Nombre del empleado \n';
        banderaMensajeFaltante=true;
    }
    

   if (document.getElementById("empLastName").value =='')
    {
        document.getElementById("empLastName").style.background='Red';
        mensajeFaltanteAlert+= ' * El apellido del empleado \n';
        banderaMensajeFaltante=true;        
    }
       
    if( document.getElementById("empMail").value != '')
    {
        if (!(validarEmail(document.getElementById("empMail").value)))
        {
        document.getElementById("empMail").style.background='Red';
        mensajeFaltanteAlert+= ' * La dirección de email es incorrecta \n';
        banderaMensajeFaltante=true;
        }
    }
    
        if( document.getElementById("empMsn").value != '')
    {
        if (!(validarEmail(document.getElementById("empMsn").value)))
        {
        document.getElementById("empMsn").style.background='Red';
        mensajeFaltanteAlert+= ' * La dirección MSN es incorrecta \n';
        banderaMensajeFaltante=true;
        }
    }
    
    if(banderaMensajeFaltante)
        mensajeCampoAlert=mensajeFaltanteAlert + '\n';    
    
    if (banderaMensajeFaltante)
        return true;
    else
        return false;
}




var mensajeCampoAlert;

function keyTarifa(e) {
        var keycode;
        if (window.event) keycode = window.event.keyCode;
        else if (e) keycode = e.which;
        if( keycode == 13){
           agregarTarifa();
        }    
}       
 
function init(){
    
    if (  document.getElementById('cliId').value != ''){
    
         vistaTarifas(); 
         mostrarOpcionales();
    
    }
}

function vistaTarifas(){

   var tabla=document.getElementById('tabla-tarifas'); 
   if(tabla.style.display =='none'){
       tabla.style.display='';
       document.getElementById('aTar').style.display='none';
       
   }else{
       tabla.style.display='none';
        document.getElementById('aTar').style.display='';
   }
}

function agregarTarifa(){
   
   if( document.getElementById('listaTarifa').selectedIndex == 0){ 
      alert('Seleccione una tarifa');  
      document.getElementById('listaTarifa').focus();
     return;
   }
   if( document.getElementById('tar_val').value == '' || !isFloat(document.getElementById('tar_val').value)){ 
      alert('Ingrese una tarifa válida');  
      document.getElementById('tar_val').focus();
     return;
   }
   
   if(document.getElementById('tarId-'+document.getElementById('listaTarifa').options[document.getElementById('listaTarifa').selectedIndex].value)){
   
     alert('La tarifa ya se encuentra en la lista');  
   }else{
   
       var tablaTarifas= document.getElementById('list-tarifas-body');
       var index = tablaTarifas.rows.length;
       var newRow = tablaTarifas.insertRow(index);  
       newRow.bgColor = "#FFFFFF";
       insertarColumnas(tablaTarifas.rows[index],tablaTarifas.rows[0].cells.length); 
       cargarItemTarifa(tablaTarifas.rows[index]);
       document.getElementById('listaTarifa').focus();    
   }
   
}


function cargarItemTarifa(row){
   
   var optionSelected=document.getElementById('listaTarifa').options[document.getElementById('listaTarifa').selectedIndex];
   var tarVal= document.getElementById('tar_val').value;
   row.cells[0].innerHTML= optionSelected.text; 
   row.name = 'item-tarifa'; 
   
   row.id= 'tarId-'+ optionSelected.value;
   row.cells[1].innerHTML= tarVal + '<input type="hidden" name="tarifas-hidden"  value="'+optionSelected.value+'#'+tarVal+'" />';
   row.cells[2].innerHTML= '<img  src="img/Delete.png" height="18" width="18"  alt="Eliminar" onclick="eliminarTarifa(\''+row.id+'\')" onmouseover="this.style.cursor=\'hand\';" />';
   row.cells[0].width=190;
   row.cells[1].width=50;
   row.cells[1].align='right';
  
}

function eliminarTarifa(id){

   var tablaTarifas= document.getElementById('list-tarifas-body');
   var row = document.getElementById(id);
 
   document.getElementById('tar_val').value= row.cells[1].innerHTML.substring(0, row.cells[1].innerHTML.indexOf('<INPUT'));
   
   for(var i = 0 ; i <   document.getElementById('listaTarifa').length ;i++){
      if(document.getElementById('listaTarifa').options[i].value == row.id.substring(6)){
         document.getElementById('listaTarifa').options[i].selected= true;
      }
   }
   tablaTarifas.deleteRow(row.rowIndex);  
}

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

function asignar()
{
    var quitar = '';
    for(var i=0;i<document.getElementById("listaTipoEmp").options.length;i++)
    {
        if(document.getElementById("listaTipoEmp").options[i].selected)
        {
            var asig = document.getElementById("listaItemsSelect");
            var option =  new Option(document.getElementById("listaTipoEmp").options[i].text,document.getElementById("listaTipoEmp").options[i].value);
            asig.options[asig.length] = option;
            quitar = quitar+document.getElementById("listaTipoEmp").options[i].value+'-'; 
        }
    }
    var arrai = quitar.split('-');
    for(var j=0;j<arrai.length;j++)
    {
        for(var i=0;i<document.getElementById("listaTipoEmp").options.length;i++)
        {
            if(document.getElementById("listaTipoEmp").options[i].value == arrai[j])
                document.getElementById("listaTipoEmp").options[i] = null;    
        }
    }
    
    sortSelect(document.getElementById("listaItemsSelect"));
}

function desAsignar()
{
    var quitar = '';
    for(var i=0;i<document.getElementById("listaItemsSelect").options.length;i++)
    {
        if(document.getElementById("listaItemsSelect").options[i].selected)
        {
            var asig = document.getElementById("listaTipoEmp");
            var option =  new Option(document.getElementById("listaItemsSelect").options[i].text,document.getElementById("listaItemsSelect").options[i].value);
            asig.options[asig.length] = option;
            quitar = quitar+document.getElementById("listaItemsSelect").options[i].value+'-'; 
        }
    }
    var arrai = quitar.split('-');
    for(var j=0;j<arrai.length;j++)
    {
        for(var i=0;i<document.getElementById("listaItemsSelect").options.length;i++)
        {
            if(document.getElementById("listaItemsSelect").options[i].value == arrai[j])
                document.getElementById("listaItemsSelect").options[i] = null;    
        }
    }
    sortSelect(document.getElementById("listaTipoEmp"));
}

function desAsignarTodo()
{
    for(var i=0;i<document.getElementById("listaItemsSelect").options.length;i++)
    {
        var asig = document.getElementById("listaTipoEmp");
        var option =  new Option(document.getElementById("listaItemsSelect").options[i].text,document.getElementById("listaItemsSelect").options[i].value);
        asig.options[asig.length] = option;
    }
    document.getElementById("listaItemsSelect").length = 0;
    sortSelect(document.getElementById("listaTipoEmp"));
}

function sortSelect(obj)
{
    var o = new Array();
    for (var i=0; i<obj.options.length; i++){
        o[o.length] = new Option(obj.options[i].text, obj.options[i].value, obj.options[i].defaultSelected, obj.options[i].selected);
    }
    o = o.sort(
        function(a,b){ 
            if ((a.text+"") < (b.text+"")) { return -1; }
            if ((a.text+"") > (b.text+"")) { return 1; }
            return 0;
        } 
    );

    for (var i=0; i<o.length; i++){
        obj.options[i] = new Option(o[i].text, o[i].value, o[i].defaultSelected, o[i].selected);
        obj.options[i].title = o[i].text;
    }
}


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
   
   var optionTipoSelected=document.getElementById('listaTipoEmpTar').options[document.getElementById('listaTipoEmpTar').selectedIndex];
   var optionSelected=document.getElementById('dropDown3').options[document.getElementById('dropDown3').selectedIndex];
   
  /* if( document.getElementById('dropDown3').selectedIndex == 0){ 
      alert('Seleccione una tarifa');  
      document.getElementById('dropDown3').focus();
     return;
   }*/
   if( document.getElementById('tar_val').value == '' || !isFloat(document.getElementById('tar_val').value)){ 
      alert('Ingrese una tarifa válida');  
      document.getElementById('tar_val').focus();
     return;
   }
   
   if(document.getElementById('tarId-'+optionTipoSelected+optionSelected)){
   
     alert('La tarifa ya se encuentra en la lista');  
   }else{
   
       var tablaTarifas= document.getElementById('list-tarifas-body');
       var index = tablaTarifas.rows.length;
       var newRow = tablaTarifas.insertRow(index);  
       newRow.bgColor = "#FFFFFF";
       insertarColumnas(tablaTarifas.rows[index],tablaTarifas.rows[0].cells.length); 
       cargarItemTarifa(tablaTarifas.rows[index]);
       document.getElementById('dropDown3').focus();    
   }
   
}


function cargarItemTarifa(row){
   
   var optionTipoSelected=document.getElementById('listaTipoEmpTar').options[document.getElementById('listaTipoEmpTar').selectedIndex];
   var optionSelected=document.getElementById('dropDown3').options[document.getElementById('dropDown3').selectedIndex];
   var tarVal= document.getElementById('tar_val').value;
   row.cells[0].innerHTML= optionTipoSelected.text; 
   row.cells[1].innerHTML= optionSelected.text; 
   row.name = 'item-tarifa'; 
   
   row.id= 'tarId-'+optionTipoSelected.value+optionSelected.value;
   
   row.cells[2].innerHTML= tarVal + '<input type="hidden" name="tarifas-hidden"  value="'+optionSelected.value+'#'+tarVal+'" />';
   row.cells[3].innerHTML= '<img  src="img/Delete.png" height="18" width="18"  alt="Eliminar" onclick="eliminarTarifa(\''+row.id+'\')" onmouseover="this.style.cursor=\'hand\';" />';
   row.cells[0].width=130;
   row.cells[1].width=130;
   row.cells[2].width=40;
   row.cells[2].align='right';
  
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
        sortSelect(document.getElementById("listaItemsSelect"));
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
   var editar = '<img src="img/edit.png"  height="25" width="25"  alt="Editar" onclick="javascript:window.location.href=\'empleados?empId='+data.empId+'\';" onmouseover="this.style.cursor=\'hand\';" /> ';
   var eliminar = '<img  src="img/Delete.png" height="25" width="25"  alt="Eliminar" onclick="eliminarEmpleado('+data.empId+')" onmouseover="this.style.cursor=\'hand\';" />'

   row.cells[5].innerHTML= editar + ' ' + eliminar;
}

function  eliminarEmpleado(empId){
 
 if (confirm('¿Esta seguro que desea eliminar el empleado?') ){
        sortSelect(document.getElementById("listaItemsSelect"));
        document.getElementById("accion").value='eliminar';
        document.getElementById("empId").value=empId;
        document.forms[0].submit();  
    //towaysDWR.deleterEmpleado(empId,postEliminar); 
 }
}

function  eliminarEmp(){
 
 if (confirm('¿Esta seguro que desea eliminar el empleado?') ){
        sortSelect(document.getElementById("listaItemsSelect"));
        document.getElementById("accion").value='eliminar';
        document.forms[0].submit();                   
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
   else {
     document.getElementById('div-empleados').style.display='none';
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
            var asigTar = document.getElementById("listaTipoEmpTar");
           
            var option =  new Option(document.getElementById("listaTipoEmp").options[i].text,document.getElementById("listaTipoEmp").options[i].value);
            var option2 =  new Option(document.getElementById("listaTipoEmp").options[i].text,document.getElementById("listaTipoEmp").options[i].value);
            asigTar.options[asigTar.length] = option;
            asig.options[asig.length] = option2;
            quitar = quitar+document.getElementById("listaTipoEmp").options[i].value+'-'; 
            createDynamicDropdown('listaTipoEmpTar', 'listaTarifa', 'dropDown3');
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
    
   // sortSelect(document.getElementById("listaItemsSelect"));
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
               // deselectDynamicDropDown ();
                document.getElementById("listaItemsSelect").options[i] = null;  

        }
        for(var i=0;i<document.getElementById("listaTipoEmpTar").options.length;i++)
        {
            if(document.getElementById("listaTipoEmpTar").options[i].value == arrai[j])                
               // 
                document.getElementById("listaTipoEmpTar").options[i] = null;  

        }
        
    }
    deselectDynamicDropDown ();
    createDynamicDropdown("listaTipoEmpTar", "listaTarifa", "dropDown3");
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
    document.getElementById("listaTipoEmpTar").length = 0;
    deselectDynamicDropDown ();
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
        obj.options[i].selected = true;
    }
}

function opcionSeleccionadas(id)
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
        obj.options[i].selected = true;
    }
}

function seleccionarOpciones(array,idDisponible,idAsignado)
{
    var listaArray = array.split(',');
    elementDisp = document.getElementById(idDisponible);
    elementSelect = document.getElementById(idAsignado);
    var quitar = '';
    for(var i=0;i<elementDisp.options.length;i++)
    {
       for(var j=0;j<listaArray.length;j++)
       {
            if(elementDisp.options[i].value == listaArray[j])
            {
                var asig = elementSelect;
                var option =  new Option(elementDisp.options[i].text,elementDisp.options[i].value);
                asig.options[asig.length] = option;
                quitar = quitar+elementDisp.options[i].value+'-';
            }
       }
    }
    var arrai = quitar.split('-');
    for(var j=0;j<arrai.length;j++)
    {
        for(var i=0;i<elementDisp.options.length;i++)
        {
            if(elementDisp.options[i].value == arrai[j])
                elementDisp.options[i] = null;    
        }
    }
    sortSelect(elementSelect);
}

function deSeleccionarTodoOpciones(id)
{
    elementSelect = document.getElementById(id);
    for(b=0;b<elementSelect.options.length;b++)
        elementSelect.options[b].selected=false;
}

function createDynamicDropdown(listaTipoEmpTar, listaTarifa, dropDown3) {

/*  dropdown1 = lists all the countries 
    dropdown2 = this drop down is not used by users. Think of it as just a struture that holds ALL the cities for ALL countries from dropdown1. 
    dropdown3 = is a dynamically generated dropdown list which changes based on what is selected in dropdown1. the <option> nodes are copied out from dropdown2 and dynamically rendered in dropdown3.
*/
        var dropDown1 = document.getElementById(listaTipoEmpTar);
        var dropDown2 = document.getElementById(listaTarifa);
        var dropDown3 = document.getElementById(dropDown3);
        var allDropDown2Elements = dropDown2.childNodes; // 'childNodes' used so you can also include <optgroup label="xxxxxxx" name="xxx"/> in dropDown2 if required
  
        // remove all <option>s in dropDown3
        while (dropDown3.hasChildNodes()){
            dropDown3.removeChild(dropDown3.firstChild);
        }  
 
        // loop though and insert into dropDown3 all of the city <option>s in dropdown2 that relate to the country value selected in dropdown1
        for(var i = 0; i < allDropDown2Elements.length; i++){
 
                if (allDropDown2Elements[i].nodeType == 1 && allDropDown2Elements[i].getAttribute("name") == dropDown1.value) {
 
                    newDropDown3Element = allDropDown2Elements[i].cloneNode(true);
                    dropDown3.appendChild(newDropDown3Element);
                }    
 
        } // END - for loop
 
        /* if '-- Country --' is selected insert the 'default' node into dropDown3 
        if(dropDown1.value == 0) {
              dropDown3.options[0] = new Option("Por favor seleccione una tarifa", "0")
        }*/
 
        // (if you have server side logic that adds selected="selected" in dropdown2) extra code for IE to display the correct 'slected="selected"' value in the select box dropdown3
        if (navigator.userAgent.indexOf('MSIE') !=-1){
 
            for (var i=0; i < dropDown3.length; i++) {
                if(dropDown3[i].value == dropDown2.value) {
                    dropDown3[i].selected = true;
                }
            } 
        }  
}

function deselectDynamicDropDown () {

       var dropDown3 = document.getElementById("dropDown3");
       // alert(dropDown3);
       while (dropDown3.hasChildNodes()){
       //alert("child");
            dropDown3.removeChild(dropDown3.lastChild);
        }
       
}
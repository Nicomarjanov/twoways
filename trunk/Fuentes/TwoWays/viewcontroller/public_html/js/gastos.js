var mensajeCampoAlert;
rowId=0;

function keyTarifa(e) {
        var keycode;
        if (window.event) keycode = window.event.keyCode;
        else if (e) keycode = e.which;
        if( keycode == 13){
           agregarTarifa();
        }    
}     

function cancelar()
{
    if(confirm('¿Desea cancelar la carga del item de egreso?'))
    {   
        document.getElementById("accion").value='cancelar';
        document.forms[0].submit();
    }
}

function agregarItemGasto(){
    if (validarCampos()){
        alert(mensajeCampoAlert);
    }else {
       var tablaGastos = document.getElementById('list-gastos-body');
       
       var index = tablaGastos.rows.length;       

       var newRow = tablaGastos.insertRow(index);  
       newRow.bgColor ="#FCEEED";
       insertarColumnas(tablaGastos.rows[index],tablaGastos.rows[0].cells.length); 
       cargarItemGasto(tablaGastos.rows[index]);
       document.getElementById('tipoItem').value="";
       document.getElementById('listaItems').value="-1";
       document.getElementById('listaMoneda').value="";
       document.getElementById('expMonto').value="";
       document.getElementById('expComentario').value="";       
       document.getElementById('listaCuentas').value="";
       document.getElementById('iteId').value="";
       
       document.getElementById('listaItems').focus();
       
    }
}

function validarCampos()
{
    var banderaMensajeFaltante=false;
    mensajeCampoAlert='';
    mensajeFaltanteAlert = 'Se tiene que completar los siguientes campos: \n';
   
    if( document.getElementById('expFecha').value == '' ){
        document.getElementById("expFecha").style.background='Red';
        mensajeFaltanteAlert+= '* Fecha \n';
        banderaMensajeFaltante=true;
   } 

   if( document.getElementById('listaMoneda').selectedIndex==0) {
        document.getElementById("listaMoneda").style.background='red';
        mensajeFaltanteAlert+='* Seleccione una moneda del combo \n';    
        banderaMensajeFaltante=true;
   }
   
   if( document.getElementById('expMonto').value == '' ){
        document.getElementById("expMonto").style.background='Red';
        mensajeFaltanteAlert+= '* Ingrese un monto \n';
        banderaMensajeFaltante=true;
   }
   
   if( document.getElementById('listaCuentas').selectedIndex==0) {
        document.getElementById("listaCuentas").style.background='red';
        mensajeFaltanteAlert+='* Seleccione una cuenta del combo \n';    
        banderaMensajeFaltante=true;
   }


   
    if(banderaMensajeFaltante)
        mensajeCampoAlert=mensajeFaltanteAlert + '\n';    
    
    if (banderaMensajeFaltante)
        return true;
    else
        return false;
}
 

function cargarItemGasto(row){

   var tipoItem= document.getElementById('tipoItem').options[document.getElementById('tipoItem').selectedIndex].value;
   var nomItem= document.getElementById('listaItems').options[document.getElementById('listaItems').selectedIndex].value.split('#');
   var nomMoneda= document.getElementById('listaMoneda').options[document.getElementById('listaMoneda').selectedIndex].value.split('#');
   var nomCuenta= document.getElementById('listaCuentas').options[document.getElementById('listaCuentas').selectedIndex].value.split('#');
   var valorMonto= trim((document.getElementById('expMonto').value == null)?'':document.getElementById('expMonto').value);
   var comentarioItem= trim((document.getElementById('expComentario').value == null)?'':document.getElementById('expComentario').value);
   var numUser= trim((document.getElementById('expUsuario').value == null)?'':document.getElementById('expUsuario').value);
   var iteId= trim((document.getElementById('iteId').value == null)?'':document.getElementById('iteId').value);
   var fecha = document.getElementById('expFecha').value;

   row.name = 'item-gasto'; 

   if (document.getElementById('list-gastos-body').rows.length > 1){
            document.getElementById('tabla-gastos-body').style.display='';      
    }
    var auxId = '';
    var valorEditar ='';
    var valor='';

    if (iteId == null || typeof iteId == 'undefined' || iteId == '' || iteId.length ==0 ){
        rowId = rowId + 1;
        auxId = rowId +' actual'
        valorEditar=nomItem[0]+'#'+nomItem[1]+'*#'+nomMoneda[0]+'#'+nomMoneda[1]+'*#'+valorMonto+'*#'+nomCuenta[0]+'#'+nomCuenta[1]+'*#'+numUser+'*#'+auxId+'*#'+tipoItem+'*#'+fecha+'*#'+comentarioItem;   
        valor=nomItem[0]+'#'+nomMoneda[0]+'#'+valorMonto+'#'+nomCuenta[0]+'#'+numUser+'#'+comentarioItem+'#'+fecha;
    }else{
        valorEditar=nomItem[0]+'#'+nomItem[1]+'*#'+nomMoneda[0]+'#'+nomMoneda[1]+'*#'+valorMonto+'*#'+nomCuenta[0]+'#'+nomCuenta[1]+'*#'+numUser+'*#'+iteId+'*#'+tipoItem+'*#'+fecha+'*#'+comentarioItem;
        valor=nomItem[0]+'#'+nomMoneda[0]+'#'+valorMonto+'#'+nomCuenta[0]+'#'+numUser+'#'+comentarioItem+'#'+fecha+'#'+iteId;
        auxId = iteId
    }
    row.id= auxId;

    if(tipoItem == 'Egresos'){
        row.style.background="#d24444";
    }
    else {  
        row.style.background="#1cb874";
    }
    
    row.cells[0].innerHTML= fecha;
    row.cells[1].innerHTML= nomItem[1] + '<input type="hidden" name="item-gasto-hidden"  value="'+valor+'" />';
    row.cells[2].innerHTML= nomMoneda[1];
    row.cells[3].innerHTML= valorMonto;
    row.cells[4].innerHTML= nomCuenta[1];
    row.cells[5].innerHTML= comentarioItem;    
    row.cells[6].innerHTML= numUser;
    row.cells[7].innerHTML= '<img src="img/del2.png" height="15" width="15"  alt="Eliminar" onclick="eliminarItemExp(\''+row.id+'\')" onmouseover="this.style.cursor=\'hand\';" />    <img  src="img/Edit2.png" height="15" width="15"  alt="Editar Item de Egreso" onclick="editarItemExp(\''+valorEditar+'\')" onmouseover="this.style.cursor=\'hand\';" />';
    row.cells[0].width="15%";
    row.cells[1].width="30%";
    row.cells[2].width="15%";
    row.cells[3].width="15%";
    row.cells[3].align="right";
    row.cells[4].width="13%";
    row.cells[5].width="15%";    
    row.cells[6].width="10%";
    row.cells[7].width="2%";
    
   document.getElementById("accion").value='guardar';
   document.forms[0].submit();   
}


function eliminarItemExp(id){

   var tabla = document.getElementById('list-gastos-body');
   var row = document.getElementById(id);   

   tabla.deleteRow(row.rowIndex);
   if (tabla.rows.length == 1){
       document.getElementById('tabla-gastos-body').style.display='none';      
   }
   document.getElementById("accion").value='guardar';
   document.forms[0].submit();      
}

function editarItemExp(string){

       var listaArray = string.split('*#');

       document.getElementById('listaItemsAux').options.value=listaArray[0];
       document.getElementById('listaMoneda').value=listaArray[1];
       document.getElementById('expMonto').value=listaArray[2];
       document.getElementById('listaCuentas').value=listaArray[3];
       document.getElementById('expUsuario').value=listaArray[4];
       var itmExpId= listaArray[5];
       document.getElementById('iteId').value=itmExpId;
       document.getElementById('tipoItem').value=listaArray[6];
       document.getElementById('expFecha').value=listaArray[7];
       document.getElementById('expComentario').value=listaArray[8];

       createDynamicDropdown('tipoItem', 'listaItemsAux','listaItems');        
       eliminarItemExpEdicion(itmExpId);
}

function eliminarItemExpEdicion(id){

   var tabla = document.getElementById('list-gastos-body');
   var row = document.getElementById(id);   

   tabla.deleteRow(row.rowIndex);
   if (tabla.rows.length == 1){
       document.getElementById('tabla-gastos-body').style.display='none';      
   }
}
function agregar()
{
   document.getElementById("accion").value='guardar';
   document.forms[0].submit();                   
}

function BuscarItemFecha(){

   var mesId = document.getElementById("listaMes").value;  
   var anioId = document.getElementById("listaAnio").value;  
   
//   document.getElementById('tipoItemBusqueda').value=tipoItemBusqueda;
   document.getElementById('mesId').value=mesId;
   document.getElementById('anioId').value=anioId;
   document.getElementById("accion").value='buscarItemFecha';
   document.forms[0].submit(); 
}

function eliminarGasto(expId)
{
    if(confirm('¿Desea eliminar la planilla de egresos completa?'))
    {   
        document.getElementById("accion").value='eliminar';
        document.getElementById("expId").value=expId;
        document.forms[0].submit();
    }
}

function createDynamicDropdown(dropDown1, dropDown2, dropDown3) {

/*  dropdown1 = lists all the countries 
    dropdown2 = this drop down is not used by users. Think of it as just a struture that holds ALL the cities for ALL countries from dropdown1. 
    dropdown3 = is a dynamically generated dropdown list which changes based on what is selected in dropdown1. the <option> nodes are copied out from dropdown2 and dynamically rendered in dropdown3.
*/
        var dropDown1 = document.getElementById(dropDown1);
        var dropDown2 = document.getElementById(dropDown2);
        var dropDown3 = document.getElementById(dropDown3);
        var allDropDown2Elements = dropDown2.childNodes; // 'childNodes' used so you can also include <optgroup label="xxxxxxx" name="xxx"/> in dropDown2 if required

        // remove all <option>s in dropDown3
        while (dropDown3.hasChildNodes()){
            dropDown3.removeChild(dropDown3.firstChild);
        }  

        // loop though and insert into dropDown3 all of the city <option>s in dropdown2 that relate to the country value selected in dropdown1
        for(var i = 0; i < allDropDown2Elements.length; i++){

                  //  var valorArray = dropDown1.value.split('#');
                    var valorDrop1 = dropDown1.value;

                if (allDropDown2Elements[i].nodeType == 1 && allDropDown2Elements[i].getAttribute("name") == valorDrop1) {
 
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

function actualizarListaGastos(){

   var mesId = document.getElementById("expFecha").value.substring(3,5);
   var anioId = document.getElementById("expFecha").value.substring(6);
   var auxDate = document.getElementById("expFecha").value;

   document.getElementById('mesId').value=mesId;
   document.getElementById('anioId').value=anioId;
   document.getElementById("accion").value='buscarItemFecha';
   document.forms[0].submit(); 
}
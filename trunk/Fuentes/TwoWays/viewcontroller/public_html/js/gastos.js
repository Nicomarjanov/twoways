var mensajeCampoAlert;
rowId=0;

function cancelar()
{
    if(confirm('¿Desea cancelar la carga del item de gasto?'))
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
       document.getElementById('listaItems').value="";
       document.getElementById('listaMoneda').value="";
       document.getElementById('expMonto').value="";
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
    
   if( document.getElementById('listaItems').selectedIndex==0) {
        document.getElementById("listaItems").style.background='red';
        mensajeFaltanteAlert+='* Seleccionar un nombre de item del combo \n';    
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

   var nomItem= document.getElementById('listaItems').options[document.getElementById('listaItems').selectedIndex].value.split('#');
   var nomMoneda= document.getElementById('listaMoneda').options[document.getElementById('listaMoneda').selectedIndex].value.split('#');
   var nomCuenta= document.getElementById('listaCuentas').options[document.getElementById('listaCuentas').selectedIndex].value.split('#');
   var valorMonto= trim((document.getElementById('expMonto').value == null)?'':document.getElementById('expMonto').value);
   var numUser= trim((document.getElementById('expUsuario').value == null)?'':document.getElementById('expUsuario').value);
   var iteId= trim((document.getElementById('iteId').value == null)?'':document.getElementById('iteId').value);

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
        valorEditar=nomItem[0]+'#'+nomItem[1]+'*#'+nomMoneda[0]+'#'+nomMoneda[1]+'*#'+valorMonto+'*#'+nomCuenta[0]+'#'+nomCuenta[1]+'*#'+numUser+'*#'+rowId+'*#'+' ';   
        valor=nomItem[0]+'#'+nomMoneda[0]+'#'+valorMonto+'#'+nomCuenta[0]+'#'+numUser+'#';
    }else{
        valorEditar=nomItem[0]+'#'+nomItem[1]+'*#'+nomMoneda[0]+'#'+nomMoneda[1]+'*#'+valorMonto+'*#'+nomCuenta[0]+'#'+nomCuenta[1]+'*#'+numUser+'*#'+rowId+'*#'+iteId;
        valor=nomItem[0]+'#'+nomMoneda[0]+'#'+valorMonto+'#'+nomCuenta[0]+'#'+numUser+'#'+iteId;
        auxId = iteId
    }
    row.id= auxId;

    row.cells[0].innerHTML= nomItem[1] + '<input type="hidden" name="item-gasto-hidden"  value="'+valor+'" />';
    row.cells[1].innerHTML= nomMoneda[1];
    row.cells[2].innerHTML= valorMonto;
    row.cells[3].innerHTML= nomCuenta[1];
    row.cells[4].innerHTML= numUser;
    row.cells[5].innerHTML= '<img src="img/del2.png" height="15" width="15"  alt="Eliminar" onclick="eliminarItemExp(\''+row.id+'\')" onmouseover="this.style.cursor=\'hand\';" />    <img  src="img/Edit2.png" height="15" width="15"  alt="Editar Item de Gasto" onclick="editarItemExp(\''+valorEditar+'\')" onmouseover="this.style.cursor=\'hand\';" />';
    row.cells[0].width="25%";
    row.cells[1].width="15%";
    row.cells[2].width="10%";
    row.cells[2].align="right";
    row.cells[3].width="20%";
    row.cells[4].width="20%";
    row.cells[5].width="10%";
}


function eliminarItemExp(id){

   var tabla = document.getElementById('list-gastos-body');
   var row = document.getElementById(id);   

   tabla.deleteRow(row.rowIndex);
   if (tabla.rows.length == 1){
       document.getElementById('tabla-gastos-body').style.display='none';      
   }
}

function editarItemExp(string){

       var listaArray = string.split('*#');

       document.getElementById('listaItems').options.value=listaArray[0];
       document.getElementById('listaMoneda').value=listaArray[1];
       document.getElementById('expMonto').value=listaArray[2];
       document.getElementById('listaCuentas').value=listaArray[3];
       document.getElementById('expUsuario').value=listaArray[4];
       var itmExpId= listaArray[5];
       document.getElementById('iteId').value=itmExpId;
       
       eliminarItemExp(itmExpId);
}


function agregar()
{
   document.getElementById("accion").value='guardar';
   document.forms[0].submit();                   
}

function BuscarItemFecha(){

   var expFecha = document.getElementById("expFecha").value;  
   
   document.getElementById("itmDate").value=expFecha;
   document.getElementById("accion").value='buscarItemFecha';
   document.forms[0].submit(); 
}

function eliminarGasto(expId)
{
    if(confirm('¿Desea eliminar la planilla de gastos completa?'))
    {   
        document.getElementById("accion").value='eliminar';
        document.getElementById("expId").value=expId;
        document.forms[0].submit();
    }
}
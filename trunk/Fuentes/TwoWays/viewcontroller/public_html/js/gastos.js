var mensajeCampoAlert;

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
    
   if( document.getElementById('nombreEmp').value == '' ){
        document.getElementById("nombreEmp").style.background='Red';
        mensajeFaltanteAlert+= '* Nombre del empelado \n';
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
   
   row.name = 'item-gasto'; 

   if (document.getElementById('list-gastos-body').rows.length > 1){
            document.getElementById('tabla-gastos-body').style.display='';      
    }
    
    var valor=nomItem[0]+'#'+nomMoneda[0]+'#'+nomCuenta[0]+'#'+valorMonto+'#'+numUser+'#';
    var valorEditar=nomItem[1]+'#'+nomMoneda[1]+'#'+nomCuenta[1]+'#'+valorMonto+'#'+numUser+'#';
    alert(valorEditar);
    row.id= 'itmExpId-'+ valor;   

    row.cells[0].innerHTML= nomItem[1] + '<input type="hidden" name="item-gasto-hidden"  value="'+valor+'" />';
    row.cells[1].innerHTML= nomMoneda[1];
    row.cells[2].innerHTML= valorMonto;
    row.cells[3].innerHTML= nomCuenta[1];
    row.cells[4].innerHTML= numUser;
    row.cells[5].innerHTML= '<img src="img/del2.png" height="15" width="15"  alt="Eliminar" onclick="eliminarGasto(\''+row.id+'\')" onmouseover="this.style.cursor=\'hand\';" />    <img  src="img/Edit2.png" height="15" width="15"  alt="Editar Gasto" onclick="editarGasto(\''+valorEditar+'\')" onmouseover="this.style.cursor=\'hand\';" />';
    row.cells[0].width="25%";
    row.cells[1].width="15%";
    row.cells[2].width="10%";
    row.cells[2].style.align="rigth";
    row.cells[3].width="20%";
    row.cells[4].width="20%";
    row.cells[5].width="10%";
}


function eliminarGasto(id){

   var tabla = document.getElementById('list-gastos-body');
   var row = document.getElementById(id);   
 
  // document.getElementById('tar_val').value= row.cells[1].innerHTML.substring(0, row.cells[1].innerHTML.indexOf('<INPUT'));
  /* 
   for(var i = 0 ; i <   document.getElementById('listaTarifa').length ;i++){
      if(document.getElementById('listaTarifa').options[i].value == row.id.substring(6)){
         document.getElementById('listaTarifa').options[i].selected= true;
      }
   }*/
   tabla.deleteRow(row.rowIndex);
   if (tabla.rows.length == 1){
       document.getElementById('tabla-gastos-body').style.display='none';      
   }
}

function editarGasto(string){

       var listaArray = string.split('#');

       document.getElementById('listaItems').value=listaArray[0];
       document.getElementById('nomMoneda').value=listaArray[1];
       document.getElementById('nomCuenta').value=listaArray[2];
       document.getElementById('valorMonto').value=listaArray[3];

       var itmExpId= 'itmExpId-'+listaArray[0] +'#'+ listaArray[1]+listaArray[2] +'#'+ listaArray[3];
       eliminarGasto(itmExpId);
}


function agregar()
{
   document.getElementById("accion").value='guardar';
   document.forms[0].submit();                   
}

function BuscarItemEmpleado(){
   var empId = document.getElementById("nombreEmp").value;
   var expFecha = document.getElementById("expFecha").value;  
   
   document.getElementById("empId").value=empId;
   document.getElementById("itmDate").value=expFecha;
   document.getElementById("accion").value='buscarItemEmpleado';
   document.forms[0].submit(); 
}
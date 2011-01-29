
 function keyTarifa(e) {
        var keycode;
        if (window.event) keycode = window.event.keyCode;
        else if (e) keycode = e.which;
        if( keycode == 13){
           agregarResponsable();
        }
        
 }  
 
function vistaResponsablesCliente(){

   var tabla=document.getElementById('tabla-responsables'); 
   if(tabla.style.display =='none'){
       tabla.style.display='';
       document.getElementById('aResp').style.display='none';
       
   }else{
       tabla.style.display='none';
       document.getElementById('aResp').style.display='';
   }
}

function agregarResponsable(){

   if( document.getElementById('NomResponsable').value == ''){ 
      alert('Ingrese un nombre para el responsable');  
      document.getElementById('NomResponsable').focus();
     return;
   }
   if( document.getElementById('ApeResponsable').value == ''){ 
      alert('Ingrese un apellido para el responsable');  
      document.getElementById('ApeResponsable').focus();
     return;
   }
   
   if( document.getElementById('MailResponsable').value == '' && document.getElementById('PhoneResponsable').value == '' && document.getElementById('MobilResponsable').value == ''){ 
      alert('Debe ingresar algún medio de comunicación con el responsable: Mail, teléfono o mobil');  
      document.getElementById('MailResponsable').focus();
     return;
   }else{
   
       var tablaResponsables = document.getElementById('list-responsables-body');
       var index = tablaResponsables.rows.length;       
       var newRow = tablaResponsables.insertRow(index);  
       newRow.bgColor = "#FFFFFF";
       insertarColumnas(tablaResponsables.rows[index],tablaResponsables.rows[0].cells.length); 
       cargarItemResponsable(tablaResponsables.rows[index]);
       document.getElementById('NomResponsable').focus();
       
   }   
}

function cargarItemResponsable(row){
   
   var nomResp= document.getElementById('NomResponsable').value;
   var apeResp= document.getElementById('ApeResponsable').value;
   var mailResp= document.getElementById('MailResponsable').value;
   var telResp= document.getElementById('PhoneResponsable').value;
   var mobResp= document.getElementById('MobilResponsable').value;     
   row.name = 'item-responsable'; 

   if (mailResp == null || mailResp == ''){
        mailResp=" ";
   }
   if (telResp == null || telResp == ''){
        telResp=" ";
   }
   if (mobResp == null || mobResp == ''){
        mobResp=" ";
   }
   row.id= 'creId-'+ nomResp +'#'+ apeResp;   
   row.cells[0].innerHTML= nomResp + '<input type="hidden" name="responsable-hidden"  value="'+nomResp+'#'+apeResp+'#'+mailResp+'#'+telResp+'#'+mobResp+'#" />';
   row.cells[1].innerHTML= apeResp;
   row.cells[2].innerHTML= mailResp;
   row.cells[3].innerHTML= telResp;
   row.cells[4].innerHTML= mobResp;   
   row.cells[5].innerHTML= '<img  src="img/Delete.png" height="25" width="25"  alt="Eliminar" onclick="eliminarResponsable(\''+row.id+'\')" onmouseover="this.style.cursor=\'hand\';" />';
   row.cells[0].width=20;
   row.cells[1].width=20;
   row.cells[2].width=20;
   row.cells[3].width=20;
   row.cells[4].width=20;   
   row.cells[5].width=37;
   
   //row.cells[1].align='right';      
}

function eliminarResponsable(id){

   var tabla = document.getElementById('list-responsables-body');
   var row = document.getElementById(id);   
 
   document.getElementById('tar_val').value= row.cells[1].innerHTML.substring(0, row.cells[1].innerHTML.indexOf('<INPUT'));
  /* 
   for(var i = 0 ; i <   document.getElementById('listaTarifa').length ;i++){
      if(document.getElementById('listaTarifa').options[i].value == row.id.substring(6)){
         document.getElementById('listaTarifa').options[i].selected= true;
      }
   }*/
   tabla.deleteRow(row.rowIndex);
}
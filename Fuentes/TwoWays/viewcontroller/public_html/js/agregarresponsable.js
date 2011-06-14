function keyResponsable(e) {
        var keycode;
        if (window.event) keycode = window.event.keyCode;
        else if (e) keycode = e.which;
        if( keycode == 13){
           agregarResponsable();
        }
        
 }  
 
function vistaResponsablesCliente(){

   var tabla=document.getElementById('dResp'); 
   if(tabla.style.display =='none'){
       tabla.style.display='';
       document.getElementById('tabla-responsables').style.display='';
       document.getElementById('tabla-responsables-head').style.display='';       
       document.getElementById('tabla-responsables-body').style.display='';
       document.getElementById('aResp').style.display='none';
       
       
   }else{
       tabla.style.display='none';
       document.getElementById('aResp').style.display='';
       document.getElementById('tabla-responsables').style.display='none';
       document.getElementById('tabla-responsables-head').style.display='none';       
       document.getElementById('tabla-responsables-body').style.display='none';
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
   if(document.getElementById('MailResponsable').value != ''){ 
       if(!(validarEmail(document.getElementById('MailResponsable').value))){
           mensajeFaltanteAlert+= 'La dirección de email es incorrecta';
           document.getElementById('MailResponsable').focus();
           return; 
       }
   }
   if( document.getElementById('MailResponsable').value == '' && document.getElementById('PhoneResponsable').value == '' && document.getElementById('MsnResponsable').value == '' && document.getElementById('SkypeResponsable').value == ''){ 
         alert('Debe ingresar algún medio de comunicación con el responsable: Mail, teléfono, msn o skype');  
         document.getElementById('MailResponsable').focus();
         return;      
   }else{
       var tablaResponsables = document.getElementById('list-responsables-body');
       
       var index = tablaResponsables.rows.length;       

       var newRow = tablaResponsables.insertRow(index);  
       newRow.bgColor = "transparent";
       insertarColumnas(tablaResponsables.rows[index],tablaResponsables.rows[0].cells.length); 
       cargarItemResponsable(tablaResponsables.rows[index]);
       document.getElementById('NomResponsable').value="";
       document.getElementById('ApeResponsable').value="";
       document.getElementById('MailResponsable').value="";
       document.getElementById('PhoneResponsable').value="";
       document.getElementById('MsnResponsable').value="";  
       document.getElementById('SkypeResponsable').value=""; 
       //document.getElementById('CreResponsable').value="";
       document.getElementById('NomResponsable').focus();
       
   }   
}

function cargarItemResponsable(row){

   var nomResp= ((document.getElementById('NomResponsable').value == null)?"":document.getElementById('NomResponsable').value); 
   var apeResp= ((document.getElementById('ApeResponsable').value == null)?"":document.getElementById('ApeResponsable').value);
   var mailResp= ((document.getElementById('MailResponsable').value == null)?" ":document.getElementById('MailResponsable').value);
   var telResp= ((document.getElementById('PhoneResponsable').value == null)?" ":document.getElementById('PhoneResponsable').value);
   var msnResp= ((document.getElementById('MsnResponsable').value == null)?" ":document.getElementById('MsnResponsable').value);  
   var skypeResp= ((document.getElementById('SkypeResponsable').value == null)?" ":document.getElementById('SkypeResponsable').value);  
   //var creResp= document.getElementById('CreResponsable').value;
   row.name = 'item-responsable'; 
   
   /*if (mailResp == null && mailResp.length == 0 ){
        mailResp = " ";
   }
   if (telResp == null && telResp.length == 0){
        telResp = " ";
   }
   if (msnResp == null && msnResp.length == 0){
        msnResp = " ";
   }*/
   if (skypeResp == null || skypeResp.length == 0){
        skypeResp = " ";

   }

    if(document.getElementById('creId-'+ nomResp +'#'+ apeResp)){
       alert('Ya existe un responsable con ese Nombre y Apellido'); 
       document.getElementById('list-responsables-body').deleteRow(row.rowIndex);       
  }else{
 
    if (document.getElementById('list-responsables-body').rows.length > 1){
       document.getElementById('tabla-responsables-body').style.display='';      
    }
    var valor=nomResp+'#'+apeResp+'#'+mailResp+'#'+telResp+'#'+msnResp+'#'+skypeResp+'#';

    row.id= 'creId-'+ nomResp +'#'+ apeResp;   
 
    row.cells[0].innerHTML= nomResp + '<input type="hidden" name="responsable-hidden"  value="'+nomResp+'#'+apeResp+'#'+mailResp+'#'+telResp+'#'+msnResp+'#'+skypeResp+'#" />';
    row.cells[1].innerHTML= apeResp;
    row.cells[2].innerHTML= mailResp;
    row.cells[3].innerHTML= telResp;
    row.cells[4].innerHTML= msnResp;   
    row.cells[5].innerHTML= skypeResp;    
    row.cells[6].innerHTML= '<img src="img/del2.png" height="15" width="15"  alt="Eliminar" onclick="eliminarResponsable(\''+row.id+'\')" onmouseover="this.style.cursor=\'hand\';" />    <img  src="img/Edit2.png" height="15" width="15"  alt="Editar Responsable" onclick="editarResponsable(\''+valor+'\')" onmouseover="this.style.cursor=\'hand\';" />';
    row.cells[0].bgColor="#fffff";
    row.cells[1].bgColor="#fffff";
    row.cells[2].bgColor="#fffff";
    row.cells[3].bgColor="#fffff";
    row.cells[4].bgColor="#fffff";
    row.cells[5].bgColor="#fffff";
   }
}

function eliminarResponsable(id){

   var tabla = document.getElementById('list-responsables-body');
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
       document.getElementById('tabla-responsables-body').style.display='none';      
   }
}

function editarResponsable(string){

       var listaArray = string.split('#');

       document.getElementById('NomResponsable').value=listaArray[0];
       document.getElementById('ApeResponsable').value=listaArray[1];
       document.getElementById('MailResponsable').value=listaArray[2];
       document.getElementById('PhoneResponsable').value=listaArray[3];
       document.getElementById('MsnResponsable').value=listaArray[4];
       document.getElementById('SkypeResponsable').value=listaArray[5];
       //document.getElementById('CreResponsable').value=listaArray[6];

       var creId= 'creId-'+listaArray[0] +'#'+ listaArray[1];
       eliminarResponsable(creId);
}
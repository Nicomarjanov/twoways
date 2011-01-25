
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
   var respHidden = document.getElementById('responsable-hidden');
   if(tabla.style.display =='none'){
       tabla.style.display='';
       document.getElementById('aResp').style.display='none';
       if (typeof respHidden == 'object'){
           if(respHidden != null){
              document.getElementById('tabla-resultados').style.display='';
           }
        }
   }else{
       tabla.style.display='none';
       document.getElementById('aResp').style.display='';
       document.getElementById('tabla-resultados').style.display='none';
   }
}

function agregarResponsable(){

   if (validarCamposResponsable())
   {
        alert(mensajeCampoAlert);
        //destrabar(idsPantalla()); 
   }else{
       var tabla=document.getElementById('tabla-resultados'); 
       if(tabla.style.display =='none'){
           tabla.style.display='';
       }
       var tablaResponsables = document.getElementById('list-responsables-body');
       var index = tablaResponsables.rows.length;       
       var newRow = tablaResponsables.insertRow(index);  
       newRow.bgColor = "transparent";
       insertarColumnas(tablaResponsables.rows[index],tablaResponsables.rows[0].cells.length); 
       cargarItemResponsable(tablaResponsables.rows[index]);
       document.getElementById('NomResponsable').value = '';
       document.getElementById('ApeResponsable').value = '';
       document.getElementById('MailResponsable').value = '';
       document.getElementById('PhoneResponsable').value = '';
       document.getElementById('MobilResponsable').value = '';
       document.getElementById('NomResponsable').focus();
       
   }   
}

function validarCamposResponsable(){
  
  var banderaMensajeFaltante=false;
  mensajeCampoAlert='';
  mensajeFaltanteAlert = 'Se tiene que completar los siguientes campos: \n';
    
  if( document.getElementById('NomResponsable').value == ''){ 
      mensajeFaltanteAlert+=' * Nombre para el responsable'; 
      document.getElementById('NomResponsable').style.background='Red';
      banderaMensajeFaltante=true;
   }
   if( document.getElementById('ApeResponsable').value == ''){ 
      mensajeFaltanteAlert+=' * Apellido para el responsable';  
      document.getElementById('ApeResponsable').style.background='Red';
      banderaMensajeFaltante=true;
   }   
   if( document.getElementById('MailResponsable').value == '' && document.getElementById('PhoneResponsable').value == '' && document.getElementById('MobilResponsable').value == ''){ 
      mensajeFaltanteAlert+=' Debe ingresar algún medio de comunicación con el responsable: Mail, teléfono o mobil';  
      document.getElementById('MailResponsable').focus();
      banderaMensajeFaltante=true;
   }
   if( document.getElementById('MailResponsable').value != ''){
        if (!(validarEmail(document.getElementById("MailResponsable").value)))
            {
            document.getElementById("MailResponsable").style.background='Red';
            mensajeFaltanteAlert+= ' * La dirección de email es incorrecta \n';
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
   //row.cells[0].innerHTML= "<span style='background-color:transparent;'/>";
   row.cells[1].innerHTML= nomResp + '<input type="hidden" name="responsable-hidden"  value="'+nomResp+'#'+apeResp+'#'+mailResp+'#'+telResp+'#'+mobResp+'#" />';
   row.cells[2].innerHTML= apeResp;
   row.cells[3].innerHTML= mailResp;
   row.cells[4].innerHTML= telResp;
   row.cells[5].innerHTML= mobResp;   
   row.cells[6].innerHTML= '<img  src="img/del2.png" height="15" width="15"  alt="Eliminar responsable" onclick="eliminarResponsable(\''+row.id+'\')" onmouseover="this.style.cursor=\'hand\';" />';
   row.cells[1].bgColor="#fffff";
   row.cells[2].bgColor="#fffff";
   row.cells[3].bgColor="#fffff";
   row.cells[4].bgColor="#fffff";
   row.cells[5].bgColor="#fffff";   

   //row.cells[1].align='right';      
}

function eliminarResponsable(id){

   var tabla = document.getElementById('list-responsables-body');
   var row = document.getElementById(id);   
 
   /*document.getElementById('NomResponsable').value= row.cells[1].innerHTML.substring(0, row.cells[1].innerHTML.indexOf('<INPUT'));

   for(var i = 0 ; i <   document.getElementById('listaTarifa').length ;i++){
      if(document.getElementById('listaTarifa').options[i].value == row.id.substring(6)){
         document.getElementById('listaTarifa').options[i].selected= true;
      }
   }*/
   tabla.deleteRow(row.rowIndex);
}
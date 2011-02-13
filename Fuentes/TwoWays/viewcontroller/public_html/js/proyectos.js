var mensajeExisteItem='';

function cancelar()
{
    if(confirm('¿Desea cancelar la carga del Proyecto ?'))
    {   
        document.getElementById("accion").value='cancelar';
        document.forms[0].submit();
    }
}

function agregar()
{

    document.getElementById('proName').style.background  = '#FFFFFF';
    document.getElementById('proStartDate').style.background  = '#FFFFFF';
    
    document.getElementById('proName').value     = trim(document.getElementById('proName').value);
    document.getElementById('proStartDate').value     = trim(document.getElementById('proStartDate').value);
    
    
    if(validarCampos())
    {
        alert(mensajeCampoAlert);
        //destrabar(idsPantalla()); 
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

function validarCampos()
{
    var banderaMensajeFaltante=false;
    mensajeCampoAlert='';
    mensajeFaltanteAlert = 'Se tiene que completar los siguientes campos: \n';
    
    /************************************************/
    // valido el que los campos no esten vacíos
    /************************************************/
   // alert(document.getElementById("proName").value);
    
    if( document.getElementById("proName").value == '')
    {
        document.getElementById("proName").style.background='Red';
        mensajeFaltanteAlert+= ' * Nombre de la Proyecto \n';
        banderaMensajeFaltante=true;
    }
    
    
    var fecha = document.getElementById("proStartDate");
    
    
    if(fecha.value != '')
    {
        if (!(isDate(fecha.value)))
        {
        fecha.style.background='Red';
        mensajeFaltanteAlert+= ' * La fecha debe ser dd/mm/aaaa \n';
        banderaMensajeFaltante=true;
        }
    }else{
      
       document.getElementById("proStartDate").style.background='Red';
       mensajeFaltanteAlert+= ' * Fecha de la Orden \n';
       banderaMensajeFaltante=true;
    
    }
    
var proFinishDate = document.getElementById("ordFinishDate");
    
    
    if(proFinishDate.value != '')
    {
        if (!(isDate(proFinishDate.value)))
        {
        ordFinishDate.style.background='Red';
        mensajeFaltanteAlert+= ' * La fecha de entrega debe ser dd/mm/aaaa \n';
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


function onloadOrder(){

   
    var fecha = document.getElementById("proStartDate");
    
    
    if(fecha.value == '')
    {
        
        var date = new Date();
        fecha.value = ((date.getDate() < 10)?'0'+date.getDate():date.getDate())  + '/' + ((date.getMonth()+1 < 10)?''+date.getMonth()+1:(date.getMonth() +1))+ '/'+ date.getYear() +' '+ ((date.getHours()+1 < 10)?''+date.getHours()+1:(date.getHours() +1)) +':'+  ((date.getMinutes()+1 < 10)?''+date.getMinutes()+1:(date.getMinutes() +1)) ;
        //alert(fecha.value);
       
    }
    

}


function asignarProyecto(proId){

    var inputs =  document.getElementsByTagName('input');
    for (var i=0 ; i < inputs.length; i++ ){
          inputs[i].disabled=true; 
    }
    var img =  document.getElementsByTagName('img');
    for (var i=0 ; i < img.length; i++ ){
          img[i].disabled=true; 
    }
    
    window.open ("/twoways/asignaciones?proId="+proId,"AsignarProyecto"+ new Date().getTime(),"menubar=NO,resizable=NO,location=NO,directories=NO,status=NO,scrollbars=1,width=500,height=350");
 
}

function editarAsignarProyecto(praId,proId){

    var inputs =  document.getElementsByTagName('input');
    for (var i=0 ; i < inputs.length; i++ ){
          inputs[i].disabled=true; 
    }
    var img =  document.getElementsByTagName('img');
    for (var i=0 ; i < img.length; i++ ){
          img[i].disabled=true; 
    }
    
    window.open ("/twoways/asignaciones?proId="+proId+"&praId="+praId,"AsignarProyecto"+ new Date().getTime(),"menubar=NO,resizable=NO,location=NO,directories=NO,status=NO,scrollbars=1,width=500,height=350");
 
}


function onClose(){

    var proId= document.getElementsById('proId').value;
    
    var inputs =  window.opener.document.getElementsByTagName('input');
    for (var i=0 ; i < inputs.length; i++ ){
          inputs[i].disabled=false; 
    }
    
    var imgs = window.opener.document.getElementsByTagName('img');
    for (var i=0 ; i < imgs.length; i++ ){
          imgs[i].disabled=false; 
    }
    
    
}



function agregarAsignacion(){

    document.getElementById('proStartDate').style.background= '#FFFFFF';
    document.getElementById('proFinishDate').style.background= '#FFFFFF';
    document.getElementById('listaEmployees').style.background= '#FFFFFF';
    document.getElementById('listaServices').style.background= '#FFFFFF';
    var languaguesList = document.getElementsByName('languagues');
   
    for (var j=0 ; j < languaguesList.length; j++){
       var languagues = languaguesList[j];
       languagues.style.background= '#FFFFFF';
    }
    document.getElementById('proStartDate').value     = trim(document.getElementById('proStartDate').value);
    document.getElementById('proFinishDate').value     = trim(document.getElementById('proFinishDate').value);
    
    
    if(validarAsignacion())
    {
        alert(mensajeCampoAlert);
        //destrabar(idsPantalla()); 
    }else if (document.getElementById('praId').value ==''){
    
        var praDate=document.getElementById('proStartDate').value;  
        var listaEmployees = document.getElementById('listaEmployees');
        var empId= listaEmployees.options[listaEmployees.selectedIndex].value; 
        var proId=document.getElementById('proId').value; 
        var listaServices = document.getElementById('listaServices');
        var serv= listaServices.options[listaServices.selectedIndex].value; 
         
        towaysDWR.existeAssignacion(praDate,empId,serv,proId,verificarAsignacionCallBack);
        
    }else{
       verificarAsignacionCallBack(null)
    }
     
}

 
function verificarAsignacionCallBack(data){
    
   
    if(data == null){
       prepararLanguagues();
       grabar(false);
    }else{
    
      if (confirm('La asignación ya ha sido cargada anteriormente. \n¿ Desea abrirla para modificación?')){
        var proId=document.getElementById('proId').value; 
        window.location.href='/twoways/asignaciones?praId='+data+'&proId='+proId; 
      }
    }

}
 
 
function cancelarAsignacion(){
 alert(document.getElementById('proId').value); 
 window.opener.location.href='/twoways/proyectos?proId='+ document.getElementById('proId').value;
 window.close();
}



function findEmployees(){
  
    var listaServices = document.getElementById('listaServices');
    var rateName= listaServices.options[listaServices.selectedIndex].value; 
    towaysDWR.getEmpByRatesName(rateName,buscarClientesCallBack); 
    habilitarLanguagues();
  
}


function changeEmployees(){
    
    var listaEmployees = document.getElementById('listaEmployees');
    var empId= listaEmployees.options[listaEmployees.selectedIndex].value; 
    towaysDWR.getTranslatorsLanguaguesTOByEmpId(empId,changeEmployeesCallBack);
    towaysDWR.existenTarifas(empId,verificarTarifasCallBack);

}

function verificarTarifasCallBack(data){
    var aceptarButton = document.getElementById('aceptarButton');
    var msjEmpleado = document.getElementById('msjEmpleado'); 
    if (data < 1 && document.getElementById('listaEmployees').selectedIndex > 0 ){
       aceptarButton.disabled=true;
       msjEmpleado.innerHTML = (document.getElementById('listaEmployees').options[document.getElementById('listaEmployees').selectedIndex].innerHTML.toUpperCase()+' no cuenta con tarifas asignadas.<br>Por favor cargue las tarifas en el Abm de clientes para poder realizar la asignación  ');
    }else{
       msjEmpleado.innerHTML = '';
       aceptarButton.disabled=false;
    }
}


function changeEmployeesCallBack(data){
   
   var languaguesList = document.getElementsByName('languagues');
   
   for (var j=0 ; j < languaguesList.length; j++){
       var languagues = languaguesList[j];
       
       //alert(languagues.id);
       dwr.util.removeAllOptions(languagues.id);
       languagues.add(new Option('Seleccionar', '0' )); 
      
        if (data != null){
           
            for(var i =0; i< data.length ; i++){
                var texto ='[';
                texto += (data[i].LAN_ORIGEN!= null)?data[i].LAN_ORIGEN:'';
                texto +='-';
                texto += (data[i].ACRON_ORIGEN!= null)?data[i].ACRON_ORIGEN:'';
                texto +='][';
                texto += (data[i].LAN_DESTINO!= null)?data[i].LAN_DESTINO:'';
                texto +='-';
                texto += (data[i].ACRON_DESTINO!= null)?data[i].ACRON_DESTINO:'';
                texto +=']';
                languagues.add(new Option(texto, data[i].TLA_ID )); 
                
            }
       }
       
       
   
   }
   
   
   
}

function buscarClientesCallBack(data){
    
    dwr.util.removeAllOptions('listaEmployees');
    
    var listaEmployees = document.getElementById('listaEmployees');
   
    listaEmployees.add(new Option('Seleccionar', '0' )); 
    for (var i=0 ; i< data.length;i++){
    
        var emp = data[i]; 
        listaEmployees.add(new Option(emp.empFirstName +' '+emp.empLastName,emp.empId )) 
        
    }
    
   

}


function validarAsignacion()
{
    var banderaMensajeFaltante=false;
    mensajeCampoAlert='';
    mensajeFaltanteAlert = 'Se tiene que completar los siguientes campos: \n';
    
    /************************************************/
    // valido el que los campos no esten vacíos
    /************************************************/
   // alert(document.getElementById("proName").value);
    
    if( document.getElementById("listaServices").selectedIndex == -1  || document.getElementById("listaServices").selectedIndex == 0)
    {
        document.getElementById("listaServices").style.background='Red';
        mensajeFaltanteAlert+= ' * Servicio \n';
        banderaMensajeFaltante=true;
    }
    
    if( document.getElementById("listaEmployees").selectedIndex == -1  || document.getElementById("listaEmployees").selectedIndex == 0)
    {
        document.getElementById("listaEmployees").style.background='Red';
        mensajeFaltanteAlert+= ' * Empleado \n';
        banderaMensajeFaltante=true;
        
    }
    
    if( document.getElementById("listaServices").selectedIndex != -1  && document.getElementById("listaServices").selectedIndex != 0){
        
            var serv= document.getElementById("listaServices").options[document.getElementById("listaServices").selectedIndex].value;
           
            
            if (serv=='Traductor'){
                 
                 
                  var languaguesList = document.getElementsByName('languagues');
                  var msj = true; 
                  for (var j=0 ; j < languaguesList.length; j++){ 
                      
                     var languagues = languaguesList[j];
                     var check = document.getElementById(languagues.id.replace('languagues','listdocs-')); 
                      if(check.checked && (languagues.selectedIndex == -1  || languagues.selectedIndex == 0)){
                       
                       
                       languagues.style.background='Red';
                       if(msj){ 
                          mensajeFaltanteAlert+= '* Par de Lenguages\n';
                       }else{
                        msj=false;
                       }
                       banderaMensajeFaltante=true;
                       
                      } 
                }  
           }
     }
    
    
    
    var fecha = document.getElementById("proStartDate");
    
    
    if(fecha.value != '')
    {
        if (!(isDate(fecha.value)))
        {
        fecha.style.background='Red';
        mensajeFaltanteAlert+= ' * La fecha debe ser dd/mm/aaaa \n';
        banderaMensajeFaltante=true;
        }else if( compararFecha(fecha.value,document.getElementById('projectStartDate').value )== -1){
           fecha.style.background='Red';
           mensajeFaltanteAlert+= ' * La fecha de inicio debe ser mayor a la fecha de inicio del proyecto\n';
           banderaMensajeFaltante=true;
        }
        
        
    }else{
      
       document.getElementById("proStartDate").style.background='Red';
       mensajeFaltanteAlert+= ' * Fecha de inicio del proyecto \n';
       banderaMensajeFaltante=true;
    
    }
    
    
    fecha = document.getElementById("proFinishDate");
    
    
    if(fecha.value != '')
    {
        if (!(isDate(fecha.value)))
        {
        fecha.style.background='Red';
        mensajeFaltanteAlert+= ' * La fecha debe ser dd/mm/aaaa \n';
        banderaMensajeFaltante=true;
        }else if( compararFecha(fecha.value,document.getElementById('proStartDate').value )== -1){
           fecha.style.background='Red';
           mensajeFaltanteAlert+= ' * La fecha de fin de la asignación debe ser mayor a la fecha de inicio de la asignación\n';
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


function cambioCheck(id){
   
   var serv= document.getElementById("listaServices").options[document.getElementById("listaServices").selectedIndex].value;
   var praId= document.getElementById('praId').value;
   var check = document.getElementById('listdocs-'+id);
   
   
   for(var s=0;s < ordersdocsOld.length; s++){
       if(ordersdocsOld[s]== id && check.checked ==false){
          if(confirm('¿Esta seguro que desea eliminar la asignacion de este documento ?\n Esta operacion no puede deshacerse y se eliminaran todos los datos asociados a la misma ')){
              towaysDWR.quitarDetalle(ordersdocsOld[s],praId, resultadoEliminacionDetalleCallBack); 
          }else{
           check.checked =true;
          }
          break; 
       }
   }
   
   
   if (serv=='Traductor'){
       var combo = document.getElementById('languagues'+id);
       
       if( check.checked ==true || check.checked =='checked' ){
           combo.disabled=false;
       }else{
       
           combo.disabled=true;
       }
   }
}

function resultadoEliminacionDetalleCallBack(data){
   if(data ==''){
        alert('El detalle se elimino con exito');
   }else{
        alert('El detalle no pudo eliminarse por : '+ data);
   }
}             

function habilitarLanguagues(){
  
  
   var languaguesList = document.getElementsByName('languagues');
   
   var serv= document.getElementById("listaServices").options[document.getElementById("listaServices").selectedIndex].value;
   
   if (serv=='Traductor'){
   
   
        for (var j=0 ; j < languaguesList.length; j++){
           var languagues = languaguesList[j];
           var check = document.getElementById(languagues.id.replace('languagues','listdocs-')); 
           
           if(check.checked && (languagues.selectedIndex == -1  || languagues.selectedIndex == 0)){
              languagues.disabled=false;
           }   
        }
   
   }else{
       for (var j=0 ; j < languaguesList.length; j++){
           var languagues = languaguesList[j];
           languagues.disabled=true;
              
        }
   
   }

}



function prepararLanguagues(){
  
   var languaguesList = document.getElementsByName('languagues');
   var serv= document.getElementById("listaServices").options[document.getElementById("listaServices").selectedIndex].value;
   if (serv=='Traductor'){
         for (var j=0 ;j <languaguesList.length  ;  j++){
           var languagues = languaguesList[j];
           var check = document.getElementById(languagues.id.replace('languagues','listdocs-')); 
           if(check.checked && (languagues.selectedIndex != -1  && languagues.selectedIndex != 0)){
              languagues.name= languagues.id;
           }   
        }
   }

}


function mostrarDetalle(id){
   var btnMas=document.getElementById('aMas-'+id); 
   var btnMenos=document.getElementById('aMenos-'+id); 
   var tabla=document.getElementById('tabla-'+id); 
   
   btnMas.style.display='none';
   btnMenos.style.display='';
   tabla.style.display='';
   
}


function ocultarDetalle(id){
   var btnMas=document.getElementById('aMas-'+id); 
   var btnMenos=document.getElementById('aMenos-'+id); 
   var tabla=document.getElementById('tabla-'+id); 
   btnMas.style.display='';
   btnMenos.style.display='none';
   tabla.style.display='none';
   
}



var mensajeExisteItem='';

function cancelar()
{
    if(confirm('�Desea cancelar la carga del Proyecto ?'))
    {   
        document.getElementById("accion").value='cancelar';
        window.location.href="/twoways/index.jsp";
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
         
        parametrizar();
        document.forms[0].submit();                
    }
}

function validarCampos()
{
    var banderaMensajeFaltante=false;
    mensajeCampoAlert='';
    mensajeFaltanteAlert = 'Se tiene que completar los siguientes campos: \n';
    var listaMoneda = document.getElementById('listaMoneda'); 
    listaMoneda.style.background='#FFFFFF';
    
    var curId= listaMoneda.options[listaMoneda.selectedIndex].value; 
        
    /************************************************/
    // valido el que los campos no esten vac�os
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
        }else{        
           if(compararFecha(fecha.value,document.getElementById('ordStartDate').value )== -1){
               fecha.style.background='Red';
               mensajeFaltanteAlert+= ' * La fecha de inicio del proyecto debe ser mayor a la fecha de inicio de la orden\n';
               banderaMensajeFaltante=true;
           }

           if(compararFecha(fecha.value,document.getElementById('ordFinishDate').value )== 1){
               fecha.style.background='Red';
               mensajeFaltanteAlert+= ' * La fecha de inicio del proyecto debe ser menor a la fecha de entrega de la orden\n';
               banderaMensajeFaltante=true;
           }   
           
           if(compararFecha(fecha.value,document.getElementById('proFinishDate').value )== 1){
               fecha.style.background='Red';
               mensajeFaltanteAlert+= ' * La fecha de inicio del proyecto debe ser menor a la fecha de fin del proyecto\n';
               banderaMensajeFaltante=true;
           }
        }
        
    }else{
      
       document.getElementById("proStartDate").style.background='Red';
       mensajeFaltanteAlert+= ' * Fecha de inicio del proyecto \n';
       banderaMensajeFaltante=true;
    
    }
    
   var proFinishDate = document.getElementById("proFinishDate");
        
    if(proFinishDate.value != '')
    {
        if (!(isDate(proFinishDate.value)))
        {
            proFinishDate.style.background='Red';
            mensajeFaltanteAlert+= ' * La fecha de entrega debe ser dd/mm/aaaa \n';
            banderaMensajeFaltante=true;
        }else{
          
           if(compararFecha(proFinishDate.value,document.getElementById('ordStartDate').value )== -1){
               proFinishDate.style.background='Red';
               mensajeFaltanteAlert+= ' * La fecha de entrega del proyecto debe ser mayor a la fecha de inicio de la orden \n';
               banderaMensajeFaltante=true;
           }

           if( compararFecha(proFinishDate.value,document.getElementById('ordFinishDate').value )== 1){
               proFinishDate.style.background='Red';
               mensajeFaltanteAlert+= ' * La fecha de entrega del proyecto debe ser menor a la fecha de entrega de la orden\n';
               banderaMensajeFaltante=true;
           }
        
        }
    }else{
      
       document.getElementById("proFinishDate").style.background='Red';
       mensajeFaltanteAlert+= ' * Fecha de entrega del proyecto \n';
       banderaMensajeFaltante=true;
    
    }

   
    if(proFinishDate.value != '' &&  fecha.value != '' && (isDate(fecha.value)) && (isDate(proFinishDate.value)) && compararFecha(fecha.value,document.getElementById('ordFinishDate').value )== 1)
    {
    
       document.getElementById("proStartDate").style.background='Red';
       document.getElementById("proFinishDate").style.background='Red';
       mensajeFaltanteAlert+= ' * La fecha de entrega de la orden debe ser mayor a la fecha de inicio del proyecto\n';
       banderaMensajeFaltante=true;
       
    
    }
    
    if( document.getElementById("listaMoneda").selectedIndex == -1  || document.getElementById("listaMoneda").selectedIndex == 0)
    {
        document.getElementById("listaMoneda").style.background='Red';
        mensajeFaltanteAlert+= '* Moneda \n';
        banderaMensajeFaltante=true;
    }
     
     
    
    var result =  normatizarCantidades(  banderaMensajeFaltante,  mensajeFaltanteAlert );
    banderaMensajeFaltante= result[0];
    mensajeFaltanteAlert=result[1];
    
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


function quitarAsignacion(praId,proId){

    if(confirm('�Esta seguro de eliminar la asignaci�n?')){
        var uaid=document.getElementById('uaid').value;
        towaysDWR.quitarAsignacion(uaid,praId,proId,quitarAsignacionCallBack );   
         
    }
}

function quitarAsignacionCallBack(data){

   if(data ==''){
        alert('La asignaci�n se elimin� con �xito');
   }else{
        alert('La asignaci�n no pudo eliminarse: '+ data);
   }
   window.location.reload(true);
}


function onClose(){

//    var proId= document.getElementsById('proId').value;
    
    var inputs =  window.opener.document.getElementsByTagName('input');
    for (var i=0 ; i < inputs.length; i++ ){
          inputs[i].disabled=false; 
    }
    
    var imgs = window.opener.document.getElementsByTagName('img');
    for (var i=0 ; i < imgs.length; i++ ){
          imgs[i].disabled=false; 
    }
    
    
}

function existeDisponbilidad(){

    var praDate=document.getElementById('proAssignStartDate').value;  
       
      
        if(isDate(praDate.value) ){
        var listaEmployees = document.getElementById('listaEmployees');
        var empId= listaEmployees.options[listaEmployees.selectedIndex].value; 
        towaysDWR.existeDisponbilidad(praDate,empId,existeDisponbilidadCallBack);
        }
    
}

function existeDisponbilidadCallBack(data){
   var aceptarButton = document.getElementById('aceptarButton');
   var msjEmpleado = document.getElementById('msjEmpleadoDisponible'); 
  // alert(data); 
  if(!((data == null || (data !=null && data == 0)) && document.getElementById('listaEmployees').selectedIndex > 0 )){
       aceptarButton.disabled=true;
       msjEmpleado.innerHTML = (document.getElementById('listaEmployees').options[document.getElementById('listaEmployees').selectedIndex].innerHTML.toUpperCase()+' Ya se encuentra asignado en la fecha');
    }else{
       msjEmpleado.innerHTML = '';
       aceptarButton.disabled=false;
    }

}


function agregarAsignacion(){

    document.getElementById('proAssignStartDate').style.background= '#FFFFFF';
    document.getElementById('proAssignFinishDate').style.background= '#FFFFFF';
    document.getElementById('listaEmployees').style.background= '#FFFFFF';
    document.getElementById('listaServices').style.background= '#FFFFFF';
    var languaguesList = document.getElementsByName('languagues');
    
    var fin =languaguesList.length;
    for (var j=0 ; j < fin ; j++){
     
       var languagues = languaguesList[j];
       languagues.style.background= '#FFFFFF';
    }
    
    document.getElementById('proAssignStartDate').value     = trim(document.getElementById('proAssignStartDate').value);
    document.getElementById('proAssignFinishDate').value     = trim(document.getElementById('proAssignFinishDate').value);
    
    
    if(validarAsignacion())
    {
        alert(mensajeCampoAlert);
        //destrabar(idsPantalla()); 
    }else if (document.getElementById('praId').value ==''){
    
        var praDate=document.getElementById('proAssignStartDate').value;  
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
         /*var languaguesList = document.getElementsByName('languagues');
         var condCorte=languaguesList.length;

         for (var j=0 ;j < condCorte  ;  j++){

           prepararLanguagues(languaguesList[j]);
        }
*/
       prepararLanguagues();
    
       grabar(false);
    }else{
    
      if (confirm('La asignaci�n ya ha sido cargada anteriormente. \n� Desea abrirla para modificaci�n?')){
        var proId=document.getElementById('proId').value; 
        window.location.href='/twoways/asignaciones?praId='+data+'&proId='+proId; 
      }
    }

}
  
function cancelarAsignacion(){
 //alert(document.getElementById('proId').value); 
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
    var listaServices = document.getElementById('listaServices');
    var rateName= listaServices.options[listaServices.selectedIndex].value; 
    var listaEmployees = document.getElementById('listaEmployees');
    var empId= listaEmployees.options[listaEmployees.selectedIndex].value; 
    towaysDWR.getTranslatorsLanguaguesTOByEmpId(empId,changeEmployeesCallBack);
    towaysDWR.existenTarifas(empId,rateName,verificarTarifasCallBack);
    existeDisponbilidad();

}

function verificarTarifasCallBack(data){
    var aceptarButton = document.getElementById('aceptarButton');
    var msjEmpleado = document.getElementById('msjEmpleado'); 
    if (data < 1 && document.getElementById('listaEmployees').selectedIndex > 0 ){
       aceptarButton.disabled=true;
       msjEmpleado.innerHTML = (document.getElementById('listaEmployees').options[document.getElementById('listaEmployees').selectedIndex].innerHTML.toUpperCase()+' no cuenta con tarifas asignadas.<br>Por favor cargue las tarifas en el Abm de clientes para poder realizar la asignaci�n  ');
    }else{
       msjEmpleado.innerHTML = '';
       aceptarButton.disabled=false;
    }
}


function changeEmployeesCallBack(data){
   
   var languaguesList = document.getElementsByName('languagues');
   
   for (var j=0 ; j < languaguesList.length; j++){
       var languagues = languaguesList[j];
       
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
    // valido el que los campos no esten vac�os
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
                       
            if (serv!='Maquetador'){                 
                 
                  var languaguesList = document.getElementsByName('languagues');
                  var msj = true; 
                  var condCorte = languaguesList.length;              
                  for (var j=0 ; j < condCorte ; j++){ 
                      
                     var languagues = languaguesList[j];                    
                     if(typeof languagues != "undefined"){                            
                         var check = document.getElementById(languagues.id.replace('languagues','listdocs-')); 
                        
                        
                         if(check.checked && languagues.text == "Seleccionar"){                     
                           
                           languagues.style.background='Red';
                           if(msj){ 
                              mensajeFaltanteAlert+= '* Par de Lenguages\n';
                           }else{
                            msj=false;
                           }
                           banderaMensajeFaltante=true;
                           
                          }else {
                            languagues.name=languagues.id;
                           }
                    }                      
                }  
           }
     }
        
    var fecha = document.getElementById("proAssignStartDate");    

    if(fecha.value != '')
    {
        if (!(isDate(fecha.value)))
        {
        fecha.style.background='Red';
        mensajeFaltanteAlert+= ' * La fecha debe ser dd/mm/aaaa \n';
        banderaMensajeFaltante=true;
        }else{
        
           if(compararFecha(fecha.value,document.getElementById('projectStartDate').value )== -1){
           fecha.style.background='Red';
           mensajeFaltanteAlert+= ' * La fecha inicio de la asignaci�n debe ser mayor a la fecha de inicio del proyecto\n';
           banderaMensajeFaltante=true;
           }

           if(compararFecha(fecha.value,document.getElementById('projectFinishDate').value )== 1){
           fecha.style.background='Red';
           mensajeFaltanteAlert+= ' * La fecha inicio de la asignaci�n debe ser menor a la fecha de entrega del proyecto\n';
           banderaMensajeFaltante=true;
           }
        }
        
    }else{
      
       document.getElementById("proAssignStartDate").style.background='Red';
       mensajeFaltanteAlert+= ' * Fecha de inicio del proyecto \n';
       banderaMensajeFaltante=true;
    
    }
    
var proFinishDate = document.getElementById("proAssignFinishDate");
    
    if(proFinishDate.value != '')
    {
        if (!(isDate(proFinishDate.value)))
        {
        ordFinishDate.style.background='Red';
        mensajeFaltanteAlert+= ' * La fecha de entrega debe ser dd/mm/aaaa \n';
        banderaMensajeFaltante=true;
        }else{

           if(compararFecha(proFinishDate.value,document.getElementById('projectStartDate').value )== -1){
           proFinishDate.style.background='Red';
           mensajeFaltanteAlert+= ' * La fecha fin de la asignaci�n debe ser mayor a la fecha de inicio del proyecto \n';
           banderaMensajeFaltante=true;
           }

           if(compararFecha(proFinishDate.value,document.getElementById('projectFinishDate').value )== 1){
           proFinishDate.style.background='Red';
           mensajeFaltanteAlert+= ' * La fecha fin de la asignaci�n debe ser menor a la fecha de fin del proyecto \n';
           banderaMensajeFaltante=true;
           }
        
        }
    }else{
      
       document.getElementById("proAssignFinishDate").style.background='Red';
       mensajeFaltanteAlert+= ' * Fecha de entrega del proyecto \n';
       banderaMensajeFaltante=true;
    
    }

   
    if(proFinishDate.value != '' &&  fecha.value != '' && (isDate(fecha.value)) && (isDate(proFinishDate.value)) && compararFecha(proFinishDate.value,fecha.value )== -1)
    {
    
       document.getElementById("proAssignStartDate").style.background='Red';
       document.getElementById("proAssignFinishDate").style.background='Red';
       mensajeFaltanteAlert+= ' * La fecha fin de la asignacion debe ser mayor a la fecha de inicio de la asignaci�n \n';
       banderaMensajeFaltante=true;
       
    
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
          if(confirm('�Esta seguro que desea eliminar la asignaci�n de este documento ?\n Esta operaci�n no puede deshacerse y se eliminaran todos los datos asociados a la misma ')){
              towaysDWR.quitarDetalle(ordersdocsOld[s],praId, quitarDetalleCallBack); 
          }else{
           check.checked =true;
          }
          break; 
       }
   }
 
   if (serv !='Maquetador'){
       var combo = document.getElementById('languagues'+id);
       if( check.checked ==true || check.checked =='checked' ){
           combo.disabled=false;
           combo.selectedIndex=0;
       }else{
       
           combo.disabled=true;
       }
   }
}

function quitarDetalleCallBack(data){
   if(data ==''){
        alert('El detalle se elimin� con �xito');
   }else{
        alert('El detalle no pudo eliminarse: '+ data);
   }
}             

function habilitarLanguagues(){
  
  
   var languaguesList = document.getElementsByName('languagues');
   
   var serv= document.getElementById("listaServices").options[document.getElementById("listaServices").selectedIndex].value;
   
   if (serv!='Maquetador'){
   
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
   var condDis=true;
   var languagues; 
   var serv= document.getElementById("listaServices").options[document.getElementById("listaServices").selectedIndex].value;
   if (serv!='Maquetador'){
        var condCorte=languaguesList.length;
        for (var l=0 ;l < condCorte  ;  l++){
           languagues = languaguesList[l];
           var check = document.getElementById(languagues.id.replace('languagues','listdocs-')); 
           if(check.checked && (languagues.selectedIndex != -1  && languagues.selectedIndex != 0)){

              languagues.name= languagues.id;
            }
        }
        
    }

/*  var lanArray=new Array();
   var languaguesList = document.getElementsByName('languagues');
   var condDis=true;

   var serv= document.getElementById("listaServices").options[document.getElementById("listaServices").selectedIndex].value;
   if (serv!='Maquetador'){

         var condCorte=languaguesList.length;
         
         for (var l=0 ;l < condCorte  ;  l++){

           lanArray[l] = languaguesList[l].id;
           condDis = languaguesList[l].disabled;
           if (!condDis){


               var check = document.getElementById(lanArray[l].replace('languagues','listdocs-')); 

               if(check.checked && (lanArray[l] != -1  && lanArray[l] != 0)){

                  languagues.name= lanArray[l];
               }   
           }else{alert('no');}
        }
   }*/

}


function mostrarDetalle(id){
  
   var btnMas=document.getElementById('aMas-'+id); 
   var btnMenos=document.getElementById('aMenos-'+id); 
   var tabla=document.getElementById('tabla-'+id); 
   if(btnMas!= null){
    btnMas.style.display='none';
   }
   if(btnMenos!= null){
    btnMenos.style.display='';
   }
   if(tabla!= null){
    tabla.style.display='';
   }
   
}

function ocultarDetalle(id){
   var btnMas=document.getElementById('aMas-'+id); 
   var btnMenos=document.getElementById('aMenos-'+id); 
   var tabla=document.getElementById('tabla-'+id); 
   if(btnMas!= null){
    btnMas.style.display='';
   }
   if(btnMenos!= null){
    btnMenos.style.display='none';
   }
  
   if(tabla!= null){
    tabla.style.display='none';
   }
   
}


function calcularTotalDetalle(id,praId){
   var padWCount=document.getElementById('padWCount-'+id); 
   var padRate=document.getElementById('padRate-'+id); 
   var tarifXunid=document.getElementById('tarifXunid-'+id); 
   var banderaMensajeFaltante=false;
   var mensajeFaltanteAlert='';
   padWCount.style.background  = '#FFFFFF';
   padRate.style.background  = '#FFFFFF';
      
  
  if (padWCount.value !='' && !(isFloat(trim(padWCount.value.replace(',','.')))))
        {
        padWCount.style.background='Red';
        mensajeFaltanteAlert+= ' * La cantidad de palabras debe ser numerica \n';
        banderaMensajeFaltante=true;
        }
       
  if ( padRate.value != '' && !(isFloat(trim(padRate.value.replace(',','.')))) )
        {
        padRate.style.background='Red';
        mensajeFaltanteAlert+= ' * La tarifa debe ser numerica ';
        banderaMensajeFaltante=true;
        }
    
    if(banderaMensajeFaltante){
            alert(mensajeFaltanteAlert);
            return ;
    }
    if(padWCount.value==''){
      padWCount.value=0;
    }
    
     tarifXunid.value = Math.round((parseFloat(padWCount.value.replace(',','.'))* parseFloat(padRate.value.replace(',','.')))*100)/100; 
    
    calcularTotal(praId);  
   
}


function calcularTotalPalabras(praId){
    
    var padWCount= document.getElementsByName('padWCount-'+praId);
    
    var praTotalAmount=document.getElementById('praTotalAmount-'+praId); 
    
    var acum =0; 
    
    for(var i=0; i< padWCount.length; i++){
       acum+=parseFloat((padWCount[i].value!='')?padWCount[i].value.replace(',','.'):0);  
    }
    
    acum = Math.round(acum* 100)/100;
    
    praTotalAmount.value = acum;
}

function parametrizar(){

    var inputs= document.getElementsByTagName("input");
  
    for(var i=0; i< inputs.length; i++){
    
      inputs[i].name=inputs[i].id;  
    }
  
}

function calcularTotal(praId){

    var tarifXunid= document.getElementsByName('tarifXunid-'+praId);
    var totalAmount=document.getElementById('totalAmount-'+praId); 
    var acum =0; 
    var curCotiz =document.getElementById('curCotiz').value;
    
    for(var i=0; i< tarifXunid.length; i++){
       var id = tarifXunid[i].id.split('-');
       var cotiB= parseFloat(document.getElementById("curId-"+ id[1] ).value);
       var aux =  convertirCotiz(curCotiz,cotiB,parseFloat(tarifXunid[i].value));
       acum+=aux;  
    }
    
    acum =  Math.round(acum* 100)/100;
    totalAmount.value =acum;
}


function convertirCotiz(cotiA,cotiB, acum){

     return (cotiB* acum)/cotiA; 
}

function normatizarCantidades(  banderaMensajeFaltante,  mensajeFaltanteAlert ){

    var inputs= document.getElementsByTagName('input');
    var tablas =document.getElementsByTagName('table'); 
    
    var result = new Array();
    var aux = true; 
    
    for(var i=0; i< inputs.length; i++){
       
       if (inputs[i].value=='' && inputs[i].name != 'proName' && inputs[i].name != 'proStartDate' && inputs[i].name != 'proFinishDate'){
         inputs[i].value=0;
       
      }
      
      if( ( inputs[i].id.startsWith('tarifXunid') || inputs[i].id.startsWith('padWCount') || inputs[i].id.startsWith('totalAmount') || inputs[i].id.startsWith('praTotalAmount') || inputs[i].id.startsWith('padRate') || inputs[i].id.startsWith('tarifXunid') ) && !isFloat(trim(inputs[i].value.replace(',','.'))) ){
      
          alert(inputs[i].value);
          inputs[i].style.background  = 'red';
          var auxArr=inputs[i].name.split('-');
          if(auxArr.length > 1 ){ 
             
              mostrarDetalle(auxArr[1]);
          }
          banderaMensajeFaltante=true;
          if(aux){
            mensajeFaltanteAlert +='* Por favor complete todas los valores requeridos.';
            aux=false;
          }
         
      }/*else{
         inputs[i].style.background  = '#FFFFFF';
      }*/
    }
    result[0]=banderaMensajeFaltante;
    result[1]=mensajeFaltanteAlert; 
    
    return result;
}

function enviarAsignacionOpen(praId){

     document.getElementById('parIdMail').value=praId;
      document.getElementById('divMail').style.display = 'block'; 
}


function cerrarEnviarAsignacion(){

     document.getElementById('parIdMail').value='';
      document.getElementById('divMail').style.display = 'none'; 
}

function enviarAsignacion(){
          
     var praId=document.getElementById('parIdMail').value;
     var mensaje=document.getElementById('messageMail').value;
     var uaid=document.getElementById('uaid').value;
     var otrosDestinatarios= document.getElementById('otrosDestinatarios').value; 
     document.getElementById('divMail').style.display = 'none'; 
     document.getElementById('progress').style.display = '';
     document.body.style.cursor='wait';
     disableForm();
     towaysDWR.enviarAsignacion(praId,mensaje,uaid,otrosDestinatarios,enviarAsignacionCallback);

}


function enviarAsignacionCallback(data){
  document.getElementById('progress').style.display = 'none';
  document.body.style.cursor='auto';
  enableForm();
  alert(data); 
}

function disableForm() {
  var theform = document.getElementById('formProyecto');
        if (document.all || document.getElementById) {
                for (i = 0; i < theform.length; i++) {
                var formElement = theform.elements[i];
                        if (true) {
                                formElement.disabled = true;
                        }
                }
        }
    }

function enableForm() {
  var theform = document.getElementById('formProyecto');
        if (document.all || document.getElementById) {
                for (i = 0; i < theform.length; i++) {
                var formElement = theform.elements[i];
                        if (true) {
                                formElement.disabled = false;
                        }
                }
        }
    }

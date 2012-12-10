
function cancelar()
{
    if(confirm('�Desea cancelar la carga de la orden ?'))
    {   
        document.getElementById("accion").value='cancelar';
        document.forms[0].submit();
    }
}

function buscarProyectos(){
  
    var projFinishDate = document.getElementById('projFinishDate');
    var projDate =document.getElementById('projDate');
    var pageIr = document.getElementById('pageIr');
    var banderaMensajeFaltante=false;
    var mensajeCampoAlert='';
    var mensajeFaltanteAlert = 'Se tiene que completar los siguientes campos: \n';
   
    if(projDate.value != '')
    {
       if (!(isDate(projDate.value)))
        {
        //ordDate.style.background='Red';
        mensajeFaltanteAlert+= ' * La fecha de inicio debe ser dd/mm/aaaa \n';
        banderaMensajeFaltante=true;
        }
    }
    
    if(projFinishDate.value != '')
    {
        if (!(isDate(projFinishDate.value)))
        {
        //ordFinishDate.style.background='Red';
        mensajeFaltanteAlert+= ' * La fecha de fin debe ser dd/mm/aaaa \n';
        banderaMensajeFaltante=true;
        }
    }
     
    if(banderaMensajeFaltante){
        mensajeCampoAlert=mensajeFaltanteAlert + '\n';  
        alert(mensajeCampoAlert);
        return; 
    }
    
  document.getElementById('accion').value='buscar';  
  var frmGestionarProyecto = document.getElementById('frmGestionarProyecto');
  frmGestionarProyecto.submit();
}

function back(){
  
  var pageId = document.getElementById('pageId');  
  pageId.value =parseInt(pageId.value) -1;
  buscarProyectos();
}


function next(){
  var pageId = document.getElementById('pageId');   
  pageId.value = parseInt(pageId.value) +1;
  buscarProyectos();
}

function IrHasta(){
  var pageIr = document.getElementById('pageIr'); 

  if (isNumber(pageId.value)){
      pageId.value = parseInt(pageIr.value);

      buscarProyectos();    
  }else {
    alert("Debe ingresar un n�mero entero");
    document.getElementById('pageIr').style.background='red';
    return;
  }
}








function exportarListaCSV()
{
    if(confirm('�Desea exportar la tabla a un archivo?'))
    {   
        //document.getElementById("accion").value='exportar';
        //document.forms[0].submit();
        var listaClientes=document.getElementById('listaClientes').options[document.getElementById('listaClientes').selectedIndex].value;        
        var projName=document.getElementById('projName').value;
        var ordName=document.getElementById("ordName").value;
        var Iniciado=document.getElementById("Iniciado").value;
        var Entregado=document.getElementById("Entregado").value;
        var POEnviado=document.getElementById("POEnviado").value;
        var projFinishDate=document.getElementById("projFinishDate").value;
        var projFinishDateOpt=document.getElementById("projFinishDateOpt").value;
        var projDate=document.getElementById("projDate").value;
        var projDateOpt=document.getElementById("projDateOpt").value;
        var projFinishDate=document.getElementById("projFinishDate").value;
        
        
        window.open("/twoways/downloadfile?docId=Lista_Proyectos.csv&listaClientes="+listaClientes+"&projName="+projName+"&ordName="+ordName+"&Iniciado="+Iniciado+"&Entregado="+Entregado+"&POEnviado="+POEnviado+"&projFinishDate="+projFinishDate+"&projFinishDateOpt="+projFinishDateOpt+"&projDate="+projDate+"&projDateOpt="+projDateOpt+"&projFinishDate="+projFinishDate+"&docType='proyectosListaDoc'");
    }
}






function editarOrden(ord){

   window.location.href = '/twoways/ordentrabajo?ordId='+ord;
}

function editarProyecto(ord){

   window.location.href = '/twoways/proyectos?ordId='+ord;
}

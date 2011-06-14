
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
  
  var pageId = document.getElementById('pageId')   
  pageId.value =parseInt(pageId.value) -1;
  buscarProyectos();
}


function next(){
  var pageId = document.getElementById('pageId')   
  pageId.value = parseInt(pageId.value) +1;
  buscarProyectos();
}

function editarOrden(ord){

   window.location.href = '/twoways/ordentrabajo?ordId='+ord;
}

function editarProyecto(ord){

   window.location.href = '/twoways/proyectos?ordId='+ord;
}

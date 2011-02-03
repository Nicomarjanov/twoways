
function ocultarOpcionales(){
   var btnMas=document.getElementById('aMas'); 
   var btnMenos=document.getElementById('aMenos'); 
   var op1=document.getElementById('trOpcionales1'); 
   var op2=document.getElementById('trOpcionales2'); 
   btnMas.style.display='';
   btnMenos.style.display='none';
   op1.style.display='none';
   op2.style.display='none';

}

function mostrarOpcionales(){
   var btnMas=document.getElementById('aMas'); 
   var btnMenos=document.getElementById('aMenos'); 
   var op1=document.getElementById('trOpcionales1'); 
   var op2=document.getElementById('trOpcionales2'); 
   btnMas.style.display='none';
   btnMenos.style.display='';
   op1.style.display='';
   op2.style.display='';

}
function cancelar()
{
    if(confirm('¿Desea cancelar la carga de la orden ?'))
    {   
        document.getElementById("accion").value='cancelar';
        document.forms[0].submit();
    }
}


function asignar()
{
    var quitar = '';
    for(var i=0;i<document.getElementById("listaServices").options.length;i++)
    {
        if(document.getElementById("listaServices").options[i].selected)
        {
            var asig = document.getElementById("listaItemsSelect");
            //var asigTar = document.getElementById("listaTipoEmpTar");
           
            var option =  new Option(document.getElementById("listaServices").options[i].text,document.getElementById("listaServices").options[i].value);
            var option2 =  new Option(document.getElementById("listaServices").options[i].text,document.getElementById("listaServices").options[i].value);
            //asigTar.options[asigTar.length] = option;
            asig.options[asig.length] = option2;
            quitar = quitar+document.getElementById("listaServices").options[i].value+'-'; 
        }
    }
    var arrai = quitar.split('-');
    for(var j=0;j<arrai.length;j++)
    {
        for(var i=0;i<document.getElementById("listaServices").options.length;i++)
        {
            if(document.getElementById("listaServices").options[i].value == arrai[j])
                document.getElementById("listaServices").options[i] = null;    
        }
    }
    
   // sortSelect(document.getElementById("listaItemsSelect"));
}

function desAsignar()
{
    var quitar = '';
    for(var i=0;i<document.getElementById("listaItemsSelect").options.length;i++)
    {
        if(document.getElementById("listaItemsSelect").options[i].selected)
        {
            var asig = document.getElementById("listaServices");
            var option =  new Option(document.getElementById("listaItemsSelect").options[i].text,document.getElementById("listaItemsSelect").options[i].value);
            asig.options[asig.length] = option;
            quitar = quitar+document.getElementById("listaItemsSelect").options[i].value+'-';
            
        }
    }
    var arrai = quitar.split('-');
    for(var j=0;j<arrai.length;j++)
    {
        for(var i=0;i<document.getElementById("listaItemsSelect").options.length;i++)
        {
            if(document.getElementById("listaItemsSelect").options[i].value == arrai[j])                
               // deselectDynamicDropDown ();
                document.getElementById("listaItemsSelect").options[i] = null;  

        }
             
    }
    sortSelect(document.getElementById("listaServices"));
}



function desAsignarTodo()
{
    for(var i=0;i<document.getElementById("listaItemsSelect").options.length;i++)
    {
         var asig = document.getElementById("listaServices");
        var option =  new Option(document.getElementById("listaItemsSelect").options[i].text,document.getElementById("listaItemsSelect").options[i].value);
        asig.options[asig.length] = option;
    }
    document.getElementById("listaItemsSelect").length = 0;
    sortSelect(asig);
}

function sortSelect(obj)
{
    var o = new Array();
    for (var i=0; i<obj.options.length; i++){
        o[o.length] = new Option(obj.options[i].text, obj.options[i].value, obj.options[i].defaultSelected, obj.options[i].selected);
       
    }
    o = o.sort(
        function(a,b){ 
            if ((a.text+"") < (b.text+"")) { return -1; }
            if ((a.text+"") > (b.text+"")) { return 1; }
            return 0;
        } 
    );

    for (var i=0; i<o.length; i++){

        obj.options[i] = new Option(o[i].text, o[i].value, o[i].defaultSelected, o[i].selected);
        obj.options[i].title = o[i].text;
        obj.options[i].selected = true;
    }
}

function agregar()
{

    document.getElementById('ordName').style.background  = '#FFFFFF';
    document.getElementById('ordDate').style.background  = '#FFFFFF';
    document.getElementById('ordDescription').style.background = '#FFFFFF';
    document.getElementById('listaClientes').style.background = '#FFFFFF';
    
    document.getElementById('ordName').value     = trim(document.getElementById('ordName').value);
    document.getElementById('ordDate').value     = trim(document.getElementById('ordDate').value);
    document.getElementById('ordDescription').value    = trim(document.getElementById('ordDescription').value);
    
    
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
         
        for(var i=0;i<document.getElementById("listaItemsSelect").options.length;i++)
        {
           document.getElementById("listaItemsSelect").options[i].selected =true;
           
        }
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
    
    if( document.getElementById("ordName").value == '')
    {
        document.getElementById("ordName").style.background='Red';
        mensajeFaltanteAlert+= ' * Nombre de la Orden \n';
        banderaMensajeFaltante=true;
    }
    
    if(document.getElementById("listaClientes").selectedIndex==0)
    {
        document.getElementById("listaClientes").style.background='red';
        mensajeFaltanteAlert+=' * Seleccionar un cliente del combo \n';    
        banderaMensajeFaltante=true;
    }

    
    if( document.getElementById("ordDescription").value == '')
    {
       
       document.getElementById("ordDescription").style.background='Red';
        mensajeFaltanteAlert+= ' * Descripción de la Orden \n';
        banderaMensajeFaltante=true;
    }

    var fecha = document.getElementById("ordDate");
    
    
    if(fecha.value != '')
    {
        if (!(isDate(fecha.value)))
        {
        fecha.style.background='Red';
        mensajeFaltanteAlert+= ' * La fecha debe ser dd/mm/aaaa \n';
        banderaMensajeFaltante=true;
        }
    }else{
      
       document.getElementById("ordDate").style.background='Red';
       mensajeFaltanteAlert+= ' * Fecha de la Orden \n';
       banderaMensajeFaltante=true;
    
    }
    
var ordFinishDate = document.getElementById("ordFinishDate");
    
    
    if(ordFinishDate.value != '')
    {
        if (!(isDate(ordFinishDate.value)))
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


function cambioCliente(){

   var cliente = document.getElementById('listaClientes');
   towaysDWR.getTarifaClienteById(cliente.value,agregarTarifaCliente); 
   var tabla=document.getElementById('tabla-tarifas'); 
   tabla.style.display='';
   document.getElementById('aTar').style.display='none';
   
}

function onloadOrder(){

   
    var fecha = document.getElementById("ordDate");
    
    
    if(fecha.value == '')
    {
        
        var date = new Date();
        fecha.value = ((date.getDate() < 10)?'0'+date.getDate():date.getDate())  + '/' + ((date.getMonth()+1 < 10)?''+date.getMonth()+1:(date.getMonth() +1))+ '/'+ date.getYear() +' '+ ((date.getHours()+1 < 10)?''+date.getHours()+1:(date.getHours() +1)) +':'+  ((date.getMinutes()+1 < 10)?''+date.getMinutes()+1:(date.getMinutes() +1)) ;
        //alert(fecha.value);
       
    }else{
     vistaDocumentos();
     vistaTarifas();      
    }
    
   


}

function buscarOrden(){
  
  
    var ordFinishDate = frmbuscarOrden.ordFinishDate;
    var ordDate = frmbuscarOrden.ordDate;
    var banderaMensajeFaltante=false;
    var mensajeCampoAlert='';
    var mensajeFaltanteAlert = 'Se tiene que completar los siguientes campos: \n';
    
    
    
    if(ordDate.value != '')
    {
        if (!(isDate(ordDate.value)))
        {
        //ordDate.style.background='Red';
        mensajeFaltanteAlert+= ' * La fecha de inicio debe ser dd/mm/aaaa \n';
        banderaMensajeFaltante=true;
        }
    }
    
    if(ordFinishDate.value != '')
    {
        if (!(isDate(ordFinishDate.value)))
        {
        //ordFinishDate.style.background='Red';
        mensajeFaltanteAlert+= ' * La fecha de entrega debe ser dd/mm/aaaa \n';
        banderaMensajeFaltante=true;
        }
    }
    
     
    if(banderaMensajeFaltante){
        mensajeCampoAlert=mensajeFaltanteAlert + '\n';  
        alert(mensajeCampoAlert);
        return; 
    }
    
    
  frmbuscarOrden.accion.value="buscar";  
  frmbuscarOrden.submit();
}

function back(){

  frmbuscarOrden.pageId.value =parseInt(frmbuscarOrden.pageId.value) -1;
  buscarOrden();
}


function next(){
  frmbuscarOrden.pageId.value = parseInt(frmbuscarOrden.pageId.value) +1;
  buscarOrden();
}

function editarOrden(ord){

   window.location.href = '/twoways/ordentrabajo?ordId='+ord;
}

function editarProyecto(ord){

   window.location.href = '/twoways/proyectos?ordId='+ord;
}
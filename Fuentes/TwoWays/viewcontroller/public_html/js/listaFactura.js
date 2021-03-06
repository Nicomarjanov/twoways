

function verItemsFactura(mylink){

if (! window.focus)return true;
var href;
if (typeof(mylink) == 'string')
   href=mylink;
else
   href=mylink.href;
window.open(href, '_blank', 'width=640,height=400,scrollbars=yes');
return false;

}

function imprimirFactura(invId,cliId,invDate,accId,curSymbol,invTotal){

  var currencyId= curSymbol.substring(0,1);
  var currencySymbol= curSymbol.substring(2);
  var datosAdicionales="";
  popupWindow = window.open('imprimirFacturas?cliId='+cliId+'&invId='+invId+'&invDate='+invDate+'&accId='+accId+'&invTotal='+invTotal+'&curId='+currencyId+'&curSymbol='+currencySymbol+'&datos='+datosAdicionales,'Imprimir factura: '+invId,'height=400,width=400,left=10,top=10,resizable=no,scrollbars=no,toolbar=no,menubar=no,location=no,directories=no,status=yes');

  document.getElementById('accion').value='imprimir';  
  document.getElementById('invId').value=invId;
  document.getElementById('cliId').value=cliId;
  document.getElementById('invTotal').value=invTotal;
  document.getElementById('invDate').value=invDate;  
  document.getElementById('accId').value=accId;  
  document.getElementById('curSymbol').value=curSymbol;  
  //var frmlistFacturas = document.getElementById('frmlistFacturas');
  //frmlistFacturas.submit();
}

function buscarFacturas(){
    var searchDate =document.getElementById('searchDate');
    var banderaMensajeFaltante=false;
    var mensajeCampoAlert='';
    var mensajeFaltanteAlert = 'Se tiene que completar los siguientes campos: \n';
   
    if(searchDate.value != '')
    {
       if (!(isDate(searchDate.value)))
        {
        //ordDate.style.background='Red';
        mensajeFaltanteAlert+= ' * La fecha de cobro debe ser dd/mm/aaaa \n';
        banderaMensajeFaltante=true;
        }
    }
             
    if(banderaMensajeFaltante){
        mensajeCampoAlert=mensajeFaltanteAlert + '\n';  
        alert(mensajeCampoAlert);
        return; 
    }    
  document.getElementById('accion').value='buscar';  
  var frmlistFacturas = document.getElementById('frmlistFacturas');
  frmlistFacturas.submit();

}

function back(){
  
  var pageId = document.getElementById('pageId')   
  pageId.value =parseInt(pageId.value) -1;
  buscarFacturas();
}

function next(){
  var pageId = document.getElementById('pageId')   
  pageId.value = parseInt(pageId.value) +1;
  buscarFacturas();
}

function backItems(){
  
  var pageId = document.getElementById('pageId')   
  var invId = document.getElementById('invId') 
  pageId.value =parseInt(pageId.value) -1;
  var frmlistItemsFactura = document.getElementById('frmlistItemsFactura');
  frmlistItemsFactura.submit();

}

function nextItems(){
  var pageId = document.getElementById('pageId')   
  var invId = document.getElementById('invId') 
  pageId.value = parseInt(pageId.value) +1;
  var frmlistItemsFactura = document.getElementById('frmlistItemsFactura');
  frmlistItemsFactura.submit();
}
function nuevaBusqueda()
{ 
    document.getElementById("accion").value="";
    document.forms[0].submit();
    
}

function anularFactura(invId){
   towaysDWR.anularFactura(invId,anularFacturaBack); 
}

function anularFacturaBack(data){

   if (data == "ok"){
        alert("La factura ha sido anulada correctamente");
        buscarFacturas();
   }else{
        alert("No se pudo anular la factura debido al siguiente error: "+data);
   }
}
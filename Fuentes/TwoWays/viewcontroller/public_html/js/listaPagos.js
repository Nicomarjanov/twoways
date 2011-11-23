

function verItemsPago(mylink){

if (! window.focus)return true;
var href;
if (typeof(mylink) == 'string')
   href=mylink;
else
   href=mylink.href;
window.open(href, '_blank', 'width=640,height=400,scrollbars=yes');
return false;

}


function imprimirPago(payId,empId,payDate,accId,curSymbol,payAmount){
  document.getElementById('accion').value='imprimir';  
  document.getElementById('payId').value=payId;
  document.getElementById('empId').value=empId;
  document.getElementById('payAmount').value=payAmount;
  document.getElementById('payDate').value=payDate;
  document.getElementById('mesId').value=payDate.substring(3,5);
  document.getElementById('anioId').value=payDate.substring(6);
  document.getElementById('accId').value=accId;  
  document.getElementById('curSymbol').value=curSymbol;  
  var frmlistPagos = document.getElementById('frmlistPagos');
  frmlistPagos.submit();
}

function buscarPagos(){
    var payDate =document.getElementById('fecha');

    var banderaMensajeFaltante=false;
    var mensajeCampoAlert='';
    var mensajeFaltanteAlert = 'Se tiene que completar los siguientes campos: \n';
   
    if(payDate.value != '')
    {
       if (!(isDate(payDate.value)))
        {
        //ordDate.style.background='Red';
        mensajeFaltanteAlert+= ' * La fecha de pago debe ser dd/mm/aaaa \n';
        banderaMensajeFaltante=true;
        }
    }
             
    if(banderaMensajeFaltante){
        mensajeCampoAlert=mensajeFaltanteAlert + '\n';  
        alert(mensajeCampoAlert);
        return; 
    }    
  document.getElementById('accion').value='buscar';  
  var frmlistPagos = document.getElementById('frmlistPagos');
  frmlistPagos.submit();

}

function back(){
  
  var pageId = document.getElementById('pageId')   
  pageId.value =parseInt(pageId.value) -1;
  buscarPagos();
}

function next(){
  var pageId = document.getElementById('pageId')   
  pageId.value = parseInt(pageId.value) +1;
  buscarPagos();
}

function backItems(){
  
  var pageId = document.getElementById('pageId')   
  var payId = document.getElementById('payId') 
  pageId.value =parseInt(pageId.value) -1;
  var frmlistItemsPagos = document.getElementById('frmlistItemsPagos');
  frmlistItemsPagos.submit();

}

function nextItems(){
  var pageId = document.getElementById('pageId')   
  var payId = document.getElementById('payId') 
  pageId.value = parseInt(pageId.value) +1;
  var frmlistItemsPagos = document.getElementById('frmlistItemsPagos');
  frmlistItemsPagos.submit();
}

function nuevaBusqueda()
{ 
    document.getElementById("accion").value="";
    document.forms[0].submit();
    
}

function anularPago(payId){
   towaysDWR.anularPago(payId,anularPagoBack); 
}

function anularPagoBack(data){

   if (data == "ok"){
        alert("El pago ha sido anulado correctamente");
        buscarPagos();
   }else{
        alert("No se pudo anular el pago debido al siguiente error: "+data);
   }
}
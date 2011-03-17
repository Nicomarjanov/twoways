
function buscarEmpleados(){

     
    /*var empFirstName = document.getElementById('empFirstName');
    var ordDate =document.getElementById('ordDate');
    var banderaMensajeFaltante=false;
    var mensajeCampoAlert='';
    var mensajeFaltanteAlert = 'Se tiene que completar los siguientes campos: \n';
    
    
    if(empFirstName.value != '')
    {
       if (!(isDate(empFirstName.value)))
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
    
    */
  document.getElementById('accion').value='buscar';  
  var frmlistEmpleado = document.getElementById('frmlistEmpleado');
  frmlistEmpleado.submit();

}

function buscarEmpleadosCallBack(data){
  
  if (data != null && data.length > 0) {
      document.getElementById('div-empleados').style.display='';
      var tablaBusqueda= document.getElementById('tabla-busqueda');
      borrarFilas(tablaBusqueda);
      alert(typeof(tablaBusqueda));
      for(var i=0 ; i<data.length; i++){
          insertarFila(tablaBusqueda,data[i]);    
      }
  }
   else {
     document.getElementById('div-empleados').style.display='none';
  }
}

function cargarDatosColumna(row,data){

   var month=new Array(12);
    month[0]="01";
    month[1]="02";
    month[2]="03";
    month[3]="04";
    month[4]="05";
    month[5]="06";
    month[6]="07";
    month[7]="08";
    month[8]="09";
    month[9]="10";
    month[10]="11";
    month[11]="12";

   row.cells[0].innerHTML=(data.EMPFIRSTNAME==null)?'':'<a href="empleados?empId='+data.EMPID+'" >'+data.EMPFIRSTNAME+'</a>';   
   row.cells[1].innerHTML=(data.EMPLASTNAME==null)?'':data.EMPLASTNAME;   
   row.cells[2].innerHTML=(data.EMPMAIL==null)?'':data.EMPMAIL;     
   row.cells[3].innerHTML=(data.EMPMOBILE==null)?'':data.EMPMOBILE;   
   row.cells[4].innerHTML=(data.EMPTYPE==null)?'':data.EMPTYPE;     
   row.cells[5].innerHTML=(data.EMPSTATE==null)?'':data.EMPSTATE;   
   var aux = (data.EMPASSDATE==null)?'':data.EMPASSDATE;   
   if (aux != '' || aux.length > 0){
        var fecha = new Date(aux);
        row.cells[6].innerHTML = fecha.getDate()+'/'+month[fecha.getMonth()]+'/'+fecha.getFullYear();
   }
   var au1x = (data.EMPFINDATE==null)?'':data.EMPFINDATE;   
   if (au1x != '' || au1x.length > 0){
        var fecha = new Date(au1x);
        row.cells[7].innerHTML = fecha.getDate()+'/'+month[fecha.getMonth()]+'/'+fecha.getFullYear();
   }
   row.cells[8].innerHTML=(data.PROJNAME==null)?'':data.PROJNAME;     
   row.cells[9].innerHTML=(data.PROJSTATE==null)?'':data.PROJSTATE;   
   var au2x = (data.PROJSTARTDATE==null)?'':data.PROJSTARTDATE;   
   if (au2x != '' || au2x.length > 0){
        var fecha = new Date(au2x);
        row.cells[10].innerHTML = fecha.getDate()+'/'+month[fecha.getMonth()]+'/'+fecha.getFullYear();
   }

}
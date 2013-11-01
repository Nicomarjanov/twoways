function buscarEmpleados(){
    
  document.getElementById('accion').value='buscar';  
  var frmlistEmpleado = document.getElementById('frmlistEmpleado');
  frmlistEmpleado.submit();

}

/*function buscarEmpleadosCallBack(data){
  
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
*/
function back(){
  
  var pageId = document.getElementById('pageId')   
  pageId.value =parseInt(pageId.value) -1;
  buscarEmpleados();
}

function next(){
  var pageId = document.getElementById('pageId')   
  pageId.value = parseInt(pageId.value) +1;
  buscarEmpleados();
}


function exportarCSV()
{
    if(confirm('¿Desea exportar la tabla a un archivo?'))
    
    {var today = new Date();
     var dd = today.getDate();
     var mm = today.getMonth()+1; //January is 0!
        
     var yyyy = today.getFullYear();
     if(dd<10){dd='0'+dd} 
     if(mm<10){mm='0'+mm} 
     today = yyyy+mm+dd;
     var url = "/twoways/downloadfile?docId=Lista_Empleados_"+today+".csv";
     var empId=document.getElementById('listaEmpleadosSelect').options[document.getElementById('listaEmpleadosSelect').selectedIndex].value;
    
       /* if( document.getElementById("empFirstName").value != '' ){
            url = url + "&empFirstName=" + document.getElementById("empFirstName").value;
        }
        if( document.getElementById("empLastName").value != '' ){
            url = url + "&empLastName=" + document.getElementById("empLastName").value;
        }*/
        if( empId != '' ){
            url = url + "&empId=" + empId;
        }
        if( document.getElementById("ProName").value != '' ){
            url = url + "&ProName=" + document.getElementById("ProName").value;
        }
        if( document.getElementById("Traductor").checked ){
            url = url + "&Traductor=" + document.getElementById("Traductor").value;
        }        
        if( document.getElementById("Editor").checked ){
            url = url + "&Editor=" + document.getElementById("Editor").value;
        }        
        if( document.getElementById("Revisor").checked ){
            url = url + "&Revisor=" + document.getElementById("Revisor").value;
        }
        if( document.getElementById("Maquetador").checked ){
            url = url + "&Maquetador=" + document.getElementById("Maquetador").value;
        }
        if( document.getElementById("PDTP").checked ){
            url = url + "&PDTP=" + document.getElementById("PDTP").value;
        }        
        if( document.getElementById("Proofer").checked ){
            url = url + "&Proofer=" + document.getElementById("Proofer").value;
        }

        if( document.getElementById("proFinishDate").value != null ){
            url = url + "&proFinishDate=" + document.getElementById("proFinishDate").value;
            url = url + "&proFinishDateOpt=" + document.getElementById('proFinishDateOpt').options[document.getElementById('proFinishDateOpt').selectedIndex].value;
        }        
        url = url + "&docType=listaEmpleadosEnProyectosDoc";

        window.open(url);
    }
}

function agregarIdiomas(){

   if (validarCamposIdiomas())
   {
        alert(mensajeCampoAlert);
        //destrabar(idsPantalla()); 
   }else{
       var tabla=document.getElementById('tabla-result-idiomas'); 
       if(tabla.style.display =='none'){
           tabla.style.display='';
       }
       var tablaIdiomas = document.getElementById('list-idiomas-body');
       var index = tablaIdiomas.rows.length;       
       var newRow = tablaIdiomas.insertRow(index);  
       newRow.bgColor = "transparent";
       insertarColumnas(tablaIdiomas.rows[index],tablaIdiomas.rows[0].cells.length); 
       cargarItemIdiomas(tablaIdiomas.rows[index]);

       document.getElementById('listaIdiomas').focus();       
   }   
}

function validarCamposIdiomas(){
  
  var banderaMensajeFaltante=false;
  mensajeCampoAlert='';
  mensajeFaltanteAlert = 'Se tiene que completar los siguientes campos: \n';
  var idiomaOrigen = document.getElementById('listaIdiomas').options[document.getElementById('listaIdiomas').selectedIndex].value;
  var idiomaDestino = document.getElementById('listaLengua1').options[document.getElementById('listaLengua1').selectedIndex].value;   

  if (idiomaOrigen != '' && idiomaOrigen != null){
    idiomaOrigenArr = idiomaOrigen.split('#');
  }
  else
    {
     mensajeFaltanteAlert+=' * Seleccione un idioma origen'; 
     document.getElementById('listaIdiomas').style.background='Red';
     banderaMensajeFaltante=true;
  }
  if (idiomaDestino != '' && idiomaDestino != null){
    idiomaDestinoArr = idiomaDestino.split('#');
  }
  else
    {mensajeFaltanteAlert+=' * Seleccione un idioma destinos'; 
     document.getElementById('listaLengua1').style.background='Red';
     banderaMensajeFaltante=true;
  } 

   if(banderaMensajeFaltante)
        mensajeCampoAlert=mensajeFaltanteAlert + '\n';    
    
    if (banderaMensajeFaltante)
        return true;
    else
        return false;     
}

function cargarItemIdiomas(row){
   
   var idiomaOrigen = document.getElementById('listaIdiomas').options[document.getElementById('listaIdiomas').selectedIndex].value;
   var idiomaDestino = document.getElementById('listaLengua1').options[document.getElementById('listaLengua1').selectedIndex].value;   
   var acronimoOrigen = document.getElementById('listaAcronOrigen').options[document.getElementById('listaAcronOrigen').selectedIndex].value;
   var acronimoDestino = document.getElementById('listaAcronDestino').options[document.getElementById('listaAcronDestino').selectedIndex].value;
   row.name = 'item-idiomas'; 

   idiomaOrigenArr = idiomaOrigen.split('#');
   idiomaDestinoArr = idiomaDestino.split('#');   
   
   if (document.getElementById('listaAcronOrigen').disabled == false){
      idiomaOrigenArr[2]=acronimoOrigen;
   }else idiomaOrigenArr[2] = ' ';
   if (document.getElementById('listaAcronDestino').disabled == false){
      idiomaDestinoArr[2]=acronimoDestino;
   }else idiomaDestinoArr[2] = ' ';
    
   if(document.getElementById('tlaId-'+idiomaOrigenArr[1]+'#'+idiomaOrigenArr[2]+'#'+idiomaDestinoArr[1]+'#'+idiomaDestinoArr[2])){
       alert('La relación de idiomas ya se encuentra en la lista');  
  }else{
       row.id= 'tlaId-'+idiomaOrigenArr[1]+'#'+idiomaOrigenArr[2]+'#'+idiomaDestinoArr[1]+'#'+idiomaDestinoArr[2];   
       row.cells[0].innerHTML= idiomaOrigenArr[1] + '<input type="hidden" name="lenguas-hidden"  value="'+idiomaOrigenArr[0]+'#'+idiomaOrigenArr[2]+'#'+idiomaDestinoArr[0]+'#'+idiomaDestinoArr[2]+'" />';
       row.cells[1].innerHTML= idiomaOrigenArr[2]
       row.cells[2].innerHTML= idiomaDestinoArr[1]; 
       row.cells[3].innerHTML= idiomaDestinoArr[2]; 
       row.cells[4].innerHTML= '<img  src="img/del2.png" height="15" width="15"  alt="Eliminar responsable" onclick="eliminarIdiomas(\''+row.id+'\')" onmouseover="this.style.cursor=\'hand\';" />';
       row.cells[0].bgColor="#fffff";
       row.cells[1].bgColor="#fffff";
       row.cells[2].bgColor="#fffff";
       row.cells[3].bgColor="#fffff";
   }
}

function eliminarIdiomas(id){

   var tabla = document.getElementById('list-idiomas-body');
   var row = document.getElementById(id);   

   tabla.deleteRow(row.rowIndex);

   if (tabla.rows.length == 1){
       document.getElementById('tabla-result-idiomas').style.display='none';      
   }
}

function cambiarAcronimo(valor, lista){
  var idiomaArr = valor.split('#');
  var variable = document.getElementById(lista);
  if (idiomaArr[1] == 'Spanish' || idiomaArr[1] == 'English' ){
     variable.disabled=false;
  }
  else variable.disabled=true;
}


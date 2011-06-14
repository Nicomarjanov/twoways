
 function keyTarifa(e) {
        var keycode;
        if (window.event) keycode = window.event.keyCode;
        else if (e) keycode = e.which;
        if( keycode == 13){
           agregarTarifa();
        }
        
 }  
 
function vistaTarifas(){

   var tabla=document.getElementById('tabla-tarifas'); 
   if(tabla.style.display =='none'){
       tabla.style.display='';
       document.getElementById('tabla-tarifas-body').style.display='';
       document.getElementById('aTar').style.display='none';
       
   }else{
       tabla.style.display='none';
       document.getElementById('tabla-tarifas-body').style.display='none';
       document.getElementById('aTar').style.display='';
   }
}

function agregarTarifa(){

   if( document.getElementById('listaTarifa').selectedIndex == 0){ 
      alert('Seleccione una tarifa');  
      document.getElementById('listaTarifa').focus();
     return;
   }
   if( document.getElementById('tar_val').value == '' || !isFloat(document.getElementById('tar_val').value)){ 
      alert('Ingrese una tarifa válida');  
      document.getElementById('tar_val').focus();
     return;
   }
   
   if(document.getElementById('tarId-'+document.getElementById('listaTarifa').options[document.getElementById('listaTarifa').selectedIndex].value)){
   
     alert('La tarifa ya se encuentra en la lista');  
   }else{
   
       var tablaTarifas= document.getElementById('list-tarifas-body');
       var index = tablaTarifas.rows.length;
       var newRow = tablaTarifas.insertRow(index);  
       newRow.bgColor = "#FFFFFF";
       insertarColumnas(tablaTarifas.rows[index],tablaTarifas.rows[0].cells.length); 
       cargarItemTarifa(tablaTarifas.rows[index]);
       document.getElementById('listaTarifa').focus();
       
   }
   
}


function agregarTarifaCliente(data){
  
       
       var tablaTarifas= document.getElementById('list-tarifas-body');
       
       while( tablaTarifas.rows.length > 1 ){ 
         tablaTarifas.deleteRow(1);
       }
       
        for( var i =0 ; i < data.length ; i++){ 
         
         var index = tablaTarifas.rows.length;
         var newRow = tablaTarifas.insertRow(index);  
         newRow.bgColor = "#FFFFFF";
     
         insertarColumnas(tablaTarifas.rows[index],tablaTarifas.rows[0].cells.length); 
         
         cargarItemTarifaCliente(tablaTarifas.rows[index],data[i] );
                  
       }
}



function cargarItemTarifa(row){
   
   var optionSelected=document.getElementById('listaTarifa').options[document.getElementById('listaTarifa').selectedIndex];
   var tarVal= document.getElementById('tar_val').value;

   row.cells[0].innerHTML= optionSelected.text; 
   row.name = 'item-tarifa'; 
   
   row.id= 'tarId-'+ optionSelected.value;
   row.cells[1].innerHTML= tarVal + '<input type="hidden" name="tarifas-hidden"  value="'+optionSelected.value+'#'+tarVal+'" />';
   row.cells[2].innerHTML= '<img  src="img/Delete.png" height="25" width="25"  alt="Eliminar" onclick="eliminarTarifa(\''+row.id+'\')" onmouseover="this.style.cursor=\'hand\';" />';
   row.cells[0].width=203;
   row.cells[1].width=60;
   row.cells[1].align='right';
   
   
}


function cargarItemTarifaCliente(row,tarifa){
   
   //alert(dwr.util.toDescriptiveString(tarifa));
   var tarVal= tarifa.clrValue;
   
   row.cells[0].innerHTML=tarifa.ratesTO.rateTypesTO.rtyAlternativeName+'-' + tarifa.ratesTO.ratName +' '+ tarifa.ratesTO.currencyTO.curSymbol; 
   row.name = 'item-tarifa'; 
   
   row.id= 'tarId-'+ tarifa.ratesTO.ratId;
   row.cells[1].innerHTML= tarVal + '<input type="hidden" name="tarifas-hidden"  value="'+tarifa.ratesTO.ratId+'#'+tarVal+'" />';
   row.cells[2].innerHTML= '<input type="text" id="cantPalabras" name="cantPalabras" size="8" maxlength="8" value="" style="font-size:10px;font-family:verdana;"/>';
   row.cells[3].innerHTML= '<img  src="img/Delete.png" height="25" width="25"  alt="Eliminar" onclick="eliminarTarifa(\''+row.id+'\')" onmouseover="this.style.cursor=\'hand\';" />';
   row.cells[0].width=203;
   row.cells[1].width=60;
   row.cells[2].width=60;
   row.cells[1].align='right';
   
   
}

function eliminarTarifa(id){

   var tablaTarifas= document.getElementById('list-tarifas-body');
   var row = document.getElementById(id);
   
 
   document.getElementById('tar_val').value= row.cells[1].innerHTML.substring(0, row.cells[1].innerHTML.indexOf('<INPUT'));
   
   for(var i = 0 ; i <   document.getElementById('listaTarifa').length ;i++){
      if(document.getElementById('listaTarifa').options[i].value == row.id.substring(6)){
         document.getElementById('listaTarifa').options[i].selected= true;
      }
   }
   tablaTarifas.deleteRow(row.rowIndex);
  
}



function agregarTarifaOrden(){

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
  /* if( document.getElementById('tar_wordCount').value == '' || !isNumber(document.getElementById('tar_wordCount').value)){ 
      alert('Ingrese una cantidad de palabras válida');  
      document.getElementById('tar_wordCount').focus();
     return;
   }
   */
   
   if(document.getElementById('tarId-'+document.getElementById('listaTarifa').options[document.getElementById('listaTarifa').selectedIndex].value)){
   
     alert('La tarifa ya se encuentra en la lista');  
   }else{
   
       var tablaTarifas= document.getElementById('list-tarifas-body');
      
       var row = document.getElementById("totalOrdenRow");

       tablaTarifas.deleteRow(row.rowIndex);
      
       var index = tablaTarifas.rows.length;
       var newRow = tablaTarifas.insertRow(index);  
       newRow.bgColor = "#FFFFFF";
       insertarColumnas(tablaTarifas.rows[index],tablaTarifas.rows[0].cells.length); 
       cargarItemTarifaOrden(tablaTarifas.rows[index]);
       document.getElementById('listaTarifa').focus();

       index = tablaTarifas.rows.length;
       tablaTarifas.insertRow(tablaTarifas.rows.length);
       insertarColumnas(tablaTarifas.rows[index],tablaTarifas.rows[0].cells.length - 1);  
       agregarTotalTabla(tablaTarifas.rows[index]);
       
   }
   
}
 
function cargarItemTarifaOrden(row){
   
   var optionSelected=document.getElementById('listaTarifa').options[document.getElementById('listaTarifa').selectedIndex];
   var tarVal= document.getElementById('tar_val').value;
   //var tarWordCount= document.getElementById('tar_wordCount').value;
   row.cells[0].innerHTML= optionSelected.text; 
   row.name = 'item-tarifa'; 

   row.id= 'tarId-'+ optionSelected.value;
   row.cells[1].innerHTML= '<input type="text" id="clrValue" name="clrValue" value=\''+tarVal+'\' size="4" maxlength="4" readonly style="font-size:10px;font-family:verdana; border: 0; background-color: #fff;"/>' + '<input type="hidden" name="tarifas-hidden"  value="'+optionSelected.value+'#'+tarVal+'" />';
   row.cells[2].innerHTML= '<input type="text" id="cantPalabras" name="cantPalabras" size="8" maxlength="8" value="" style="font-size:10px;font-family:verdana;" onblur="sumarMonto()"/>';//<input type="hidden" name="wordCount-hidden"  value="'+optionSelected.value+'#'+tarWordCount+'" />';
   row.cells[3].innerHTML= '<img  src="img/Delete.png" height="25" width="25"  alt="Eliminar" onclick="eliminarTarifaOrden(\''+row.id+'\')" onmouseover="this.style.cursor=\'hand\';" />';
   row.cells[0].width=203;
   row.cells[1].width=60;
   row.cells[2].width=60;
   row.cells[1].align='right';
   row.cells[2].align='right';
   
   
}


function eliminarTarifaOrden(id){

   var tablaTarifas= document.getElementById('list-tarifas-body');
   var row = document.getElementById(id);

   document.getElementById('tar_val').value= row.cells[1].innerHTML.substring(0, row.cells[1].innerHTML.indexOf('<INPUT'));
   //document.getElementById('tar_wordCount').value= row.cells[2].innerHTML.substring(0, row.cells[2].innerHTML.indexOf('<INPUT'));

   for(var i = 0 ; i <   document.getElementById('listaTarifa').length ;i++){
      if(document.getElementById('listaTarifa').options[i].value == row.id.substring(6)){
         document.getElementById('listaTarifa').options[i].selected= true;
      }
   }
   tablaTarifas.deleteRow(row.rowIndex);

   sumarMonto();
  
}



function vistaDocumentos(){

   var tabla=document.getElementById('tabla-documentos'); 
   if(tabla.style.display =='none'){
       tabla.style.display='';
       document.getElementById('aDoc').style.display='none';
       
   }else{
       tabla.style.display='none';
        document.getElementById('aDoc').style.display='';
   }
}

function agregarDocumento(){

   var nameDoc=document.getElementById('doc_name');
   var idDoc= nameDoc.value.substring( nameDoc.value.lastIndexOf("\\")+1) ;
   
   if( nameDoc.value == ''){ 
      alert('Ingrese un nombre para el documento');  
      nameDoc.focus();
     return;
   }
   
    
    
   if(document.getElementById('ordId-'+idDoc)){
   
     alert('El documento ya se encuentra en la lista');  
   }else{
   
       var tablaDocumentos= document.getElementById('list-documento-body');
       var index = tablaDocumentos.rows.length;
       var newRow = tablaDocumentos.insertRow(index);  
        
       newRow.bgColor = "#FFFFFF";
       insertarColumnas(tablaDocumentos.rows[index],2); 
       cargarItemDocumento(tablaDocumentos.rows[index]);
     
       
   }
   
}

function cargarItemDocumento(row){
   
   var nameDoc=document.getElementById('doc_name');
   var idDoc= nameDoc.value.substring( nameDoc.value.lastIndexOf("\\")+1) ;
   row.name = 'item-documento'; 
   row.id= 'ordId-'+ idDoc;
   row.cells[1].innerHTML= idDoc + '<span style="display:none" ><input type="text" name="documentos-hidden"  value="'+nameDoc.value+'" /></span> ';
   nameDoc.id='file'+idDoc;
   nameDoc.name='file'+idDoc;
   nameDoc.style.display='none';
   row.cells[1].appendChild(nameDoc); 
   row.cells[0].innerHTML='<img  src="img/Delete.png" height="25" width="25"  alt="Eliminar" onclick="eliminarDocumento(\''+row.id+'\')" onmouseover="this.style.cursor=\'hand\';" />';
   row.cells[1].width=300;
   row.cells[0].width=46;
   row.cells[0].align='right';
   nameDoc.src='';
   var tdIn=document.getElementById('tdIn');
   tdIn.innerHTML='<input type="file"   onchange="agregarDocumento()" class="tw_form" id="doc_name" size=30    /> '; 
  
   
}

function eliminarDocumento(id){

   var tablaDocumentos= document.getElementById('list-documento-body');
   var row = document.getElementById(id);
   //document.getElementById('doc_name').value= row.cells[1].innerHTML.substring(0, row.cells[1].innerHTML.indexOf('<INPUT'));
   
   tablaDocumentos.deleteRow(row.rowIndex);
  
}
var mensajeCampoAlert;

function isAlfabeto(texto)
{
    validar=/^[A-z]*$/;
    if(validar.test(texto))
        return true;
    else
        return false;
}

function isAlfabetoConEspacion(texto)
{
    validar=/^[A-z|\s]*$/;
    if(validar.test(texto))
        return true;
    else
        return false;
}

function trim(s) 
{   
    s=s.replace(/^\s+/, "");
    s=s.replace(/\s+$/, "");
    return (s);
}

function trabar(id)
{	
        array=id.split(',');
        for(i=0;i<array.length;i++)
                document.getElementById(array[i]).disabled=true;
        
}

function destrabar(id)
{
        array=id.split(',');
        for(i=0;i<array.length;i++)
                document.getElementById(array[i]).disabled=false;
}


function cancelar()
{
    if(confirm('¿Desea cancelar la carga del cliente?'))
    {   
        document.getElementById("accion").value='cancelar';
        document.forms[0].submit();
    }
}

/******************************************************************************/
//   metodos utilizados para dar de alta los item
/******************************************************************************/
function agregar()
{
    //trabar(idsPantalla());
    
    document.getElementById('nomCliente').style.background  = '#FFFFFF';
    document.getElementById('descCliente').style.background  = '#FFFFFF';
    document.getElementById('mailCliente').style.background = '#FFFFFF';
    document.getElementById('telCliente').style.background    = '#FFFFFF';
    document.getElementById('listaMoneda').style.background = '#FFFFFF';
    
    document.getElementById('nomCliente').value     = trim(document.getElementById('nomCliente').value);
    document.getElementById('descCliente').value     = trim(document.getElementById('descCliente').value);
    document.getElementById('mailCliente').value    = trim(document.getElementById('mailCliente').value);
    document.getElementById('telCliente').value       = trim(document.getElementById('telCliente').value);
    document.getElementById('dirCliente').value       = trim(document.getElementById('dirCliente').value);
    document.getElementById('cpCliente').value       = trim(document.getElementById('cpCliente').value);    
    document.getElementById('paisCliente').value       = trim(document.getElementById('paisCliente').value);
    
    if(validarCampos())
    {
        alert(mensajeCampoAlert);
        //destrabar(idsPantalla()); 
    }
    else
        grabar(false);
        //BpmAdm.isExisteItem(document.getElementById("itemNombre").value,grabar);
}

function grabar(existe)
{
    if(existe)
    {
        alert(mensajeExisteItem);
        destrabar(idsPantalla());
    }
    else
    {
        
         
        document.getElementById("accion").value='guardar';
        document.forms[0].submit();
        
        /*BpmAdm.setItem( document.getElementById('itemLink').value   , document.getElementById("listaBandeja").value , document.getElementById("itemNombre").value   , 
                        document.getElementById('itemTitulo').value , document.getElementById('itemToolTip').value  , document.getElementById("listaGrupos").value  ,
                        document.getElementById('rolUserConect').value,grabarResultado);
           */             
    }
}

function grabarResultado(resultado)
{
    if(resultado == 'exito')
    {
        alert(mensajeGrabo);
        document.getElementById("estadoEjecutar").value='buscarItem';
        document.forms[0].submit();
    }
    else
    {
        if(resultado == 'errorSistema')
            alert(mensajeErrorSistema);
       destrabar(idsPantalla()); 
    }
}

function idsPantalla()
{
    return 'itemNombre,itemTitulo,itemToolTip,itemLink,listaBandeja,listaGrupos,btnAgregar,btnCancelar';
}
/******************************************************************************/
//   metodos de validacion de campos
/******************************************************************************/
function validarCampos()
{
    var banderaMensajeFaltante=false;
    mensajeCampoAlert='';
    mensajeFaltanteAlert = 'Se tiene que completar los siguientes campos: \n';
    
    /************************************************/
    // valido el que los campos no esten vacíos
    /************************************************/
    
    if( document.getElementById("nomCliente").value == '')
    {
        document.getElementById("nomCliente").style.background='Red';
        mensajeFaltanteAlert+= ' Nombre del cliente \n';
        banderaMensajeFaltante=true;
    }
    
    if(document.getElementById("listaMoneda").selectedIndex==0)
    {
        document.getElementById("listaMoneda").style.background='red';
        mensajeFaltanteAlert=' Seleccionar una moneda del combo \n';    
        banderaMensajeFaltante=true;
    }

    if(banderaMensajeFaltante)
        mensajeCampoAlert=mensajeFaltanteAlert + '\n';    
    
    if (banderaMensajeFaltante)
        return true;
    else
        return false;
}

function 
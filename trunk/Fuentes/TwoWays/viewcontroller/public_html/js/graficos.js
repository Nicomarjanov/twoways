function mostrarGraficoPalabras() {
        var d = new Date();
        //var anioActual = document.getElementById("anioId");
        var anioActual=d.getFullYear();
        var anios=new Array(); 
        var anioInicial=2012;
        for(var i=0; ; i++){
            if(anioInicial <= anioActual){
                anios[i]=anioInicial;
                anioInicial++;
            }else break;
        }
        popupWindow = window.open('graficoPalabrasxAnio?anios='+anios.join(','),'Gr�fico palabras por a�o','height=800,width=1000,left=10,top=10,resizable=yes,scrollbars=yes,toolbar=yes,menubar=no,location=no,directories=no,status=yes');
    }
    
function mostrarGraficoCliente(anio) {
        popupWindow = window.open('graficoPalabrasxCliente?anio='+anio,'Gr�fico palabras por cliente a�o '+anio,'height=1400,width=1600,left=10,top=10,resizable=yes,scrollbars=yes,toolbar=yes,menubar=no,location=no,directories=no,status=yes');
    }    
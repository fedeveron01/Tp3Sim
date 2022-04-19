package com.tp.tp3.Graficador.Controladores;

import com.tp.tp3.Generador.Controladores.GeneradorAleatorios;
import com.tp.tp3.Generador.SingletonGenerador;
import com.tp.tp3.Graficador.Modelos.Exponencial;
import com.tp.tp3.Graficador.Modelos.IDistribucion;
import com.tp.tp3.Graficador.Modelos.Intervalo;
import com.tp.tp3.Graficador.Modelos.Normal;
import com.tp.tp3.Graficador.Pantallas.PantallaGraficador;

import java.util.ArrayList;
import java.util.HashMap;

public class GestorGraficador {
    private ArrayList<Intervalo> intervalos = new ArrayList<Intervalo>();
    private ArrayList<Intervalo> intervalosAgrupados = new ArrayList<Intervalo>();
    public void graficarExponencial(int n,int intervalos, double lambda, PantallaGraficador pantalla)
    {
        Exponencial exponencial = new Exponencial();
        this.generarIntervalos(n,intervalos, exponencial ,lambda,0,1);
        var numeros = SingletonGenerador
                .getSingletonGenerador(new GeneradorAleatorios())
                .getGeneradorAleatorios()
                .generarExponencial(n,lambda);
        this.contarFrecuenciaIntervalo(numeros);
        this.calcularChiCuadrado();

    }
    public void graficarNormal(int n,int intervalos, double media,double desviacion, PantallaGraficador pantalla){
        Normal normal = new Normal();
        this.generarIntervalos(n,intervalos, normal ,-1,desviacion,media);
        var numeros = SingletonGenerador
                .getSingletonGenerador(new GeneradorAleatorios())
                .getGeneradorAleatorios()
                .generarNormal(n,desviacion,media);
        this.contarFrecuenciaIntervalo(numeros);
        this.calcularChiCuadrado();
    }

    private void agrupar(){
        /*
        double frecuenciaAcumulada = 0;
         double frecuenciaEsperadaAcumulada = 0;
         double desde = 99999999;
         double hasta = 0;
         intervalos.add(intervalo);
         for(var i=0;i<intervalos.size();i++){
             frecuenciaAcumulada+=intervalos.get(i).getFrecuenciaObservada();
             frecuenciaEsperadaAcumulada += intervalos.get(i).getFrecuenciaEsperada();
             if(intervalos.get(i).getHasta() > hasta) hasta = intervalos.get(i).getHasta();
             if(intervalos.get(i).getDesde() < desde ) desde = intervalos.get(i).getDesde();

         }
         if(frecuenciaEsperadaAcumulada > 5)
         {
             Intervalo intervaloAgrupado = new Intervalo(desde,hasta,frecuenciaEsperadaAcumulada);
             intervaloAgrupado.setFrecuenciaObservada(frecuenciaAcumulada);
             this.intervalosAgrupados.add(intervaloAgrupado);
             return new ArrayList<>();

         }else{
             return intervalos;
         }*/
        ArrayList<Intervalo> paraAgrupar = new ArrayList<>();
        double sumaFrecEs=0,sumaFrecOb=0, desde=0,hasta=0;
        int ultimo=0, cant=0;
        //comienza a rrecorrer todos los intervalos
        for(int i=0;i<this.intervalos.size();i++){
            if (this.intervalos.get(i).getFrecuenciaEsperada()<5){
                //si es mnor a 5 y no tinee ninguno para agrupar lo agrega al arreglo axiliar (paraAgrupar)
                if(paraAgrupar.isEmpty()){
                    paraAgrupar.add(this.intervalos.get(i));
                    sumaFrecEs +=this.intervalos.get(i).getFrecuenciaEsperada();
                    sumaFrecOb +=this.intervalos.get(i).getFrecuenciaObservada();
                    desde=this.intervalos.get(i).getDesde();
                    ultimo=i-1;
                }
                else {
                    sumaFrecEs+=this.intervalos.get(i).getFrecuenciaEsperada();
                    sumaFrecOb +=this.intervalos.get(i).getFrecuenciaObservada();
                    //si tiene otros para argrupar y la suma total de estos mas el nuevo es 5 crea un nuevo intervalo lo agrtega al arreglo final de intervalos
                    //y limpia las variables auxiliares
                    if (sumaFrecEs>5){
                        hasta=this.intervalos.get(i).getHasta();
                        Intervalo intervaloAgrupado = new Intervalo(desde,hasta,sumaFrecEs,sumaFrecOb);
                        this.intervalosAgrupados.add(intervaloAgrupado);
                        paraAgrupar.clear();
                        sumaFrecEs=0;
                        sumaFrecOb=0;
                        desde=0;
                        hasta=0;
                        cant+=1;
                    }
                    //si no supera los 5 lo agrega al arreglo auxiliar y continua
                    else {
                        paraAgrupar.add(this.intervalos.get(i));
                        sumaFrecEs +=this.intervalos.get(i).getFrecuenciaEsperada();
                        sumaFrecOb +=this.intervalos.get(i).getFrecuenciaObservada();
                        hasta=this.intervalos.get(i).getHasta();
                    }
                }
            }
            //si la frecuencia esperada es mayor  a 5 lo agrega
            else {
                    this.intervalosAgrupados.add(this.intervalos.get(i));
                    cant+=1;
            }
        }
        //al terminar el recorrido verifica si tiene intervalos dentro del arreglo auxiliar, si los tiene los suma al ultimo intervalo
        //que se agrego pisandolo en el arreglo
        if (!paraAgrupar.isEmpty()){
            desde=this.intervalos.get(ultimo).getDesde();
            sumaFrecEs+=this.intervalos.get(ultimo).getFrecuenciaEsperada();
            sumaFrecOb+=this.intervalos.get(ultimo).getFrecuenciaObservada();
            Intervalo intervaloAgrupado = new Intervalo(desde,hasta,sumaFrecEs,sumaFrecOb);
            this.intervalosAgrupados.add(cant-1,intervaloAgrupado);
            paraAgrupar.clear();
            sumaFrecEs=0;
            sumaFrecOb=0;
            desde=0;
            hasta=0;
            cant+=1;
        }
    }

    private boolean calcularChiCuadrado() {
        double cAcumulado = 0.0;
        boolean rechazar = true;
        //agrupa los intervalos para poder calcular el chi
        this.agrupar();
        for(var i=0;i<this.intervalosAgrupados.size();i++){
            var fo = this.intervalosAgrupados.get(i).getFrecuenciaObservada();
            var fe = this.intervalosAgrupados.get(i).getFrecuenciaEsperada();
            var c = (double) Math.pow((fo-fe),2) / (fe);
            cAcumulado += c;
            this.intervalosAgrupados.get(i).setC(c);
            this.intervalosAgrupados.get(i).setcAcumulado(cAcumulado);
        }
        /* agrupar

        ArrayList<Intervalo> acumulados = new ArrayList<>();
        for(var j=this.intervalos.size()-1;j>=0;j--)
        {
            if(this.intervalos.get(j).getFrecuenciaEsperada() < 5){
                acumulados = this.agrupar(acumulados,this.intervalos.get(j));
            }else if(acumulados.size()>0){
                acumulados = this.agrupar(acumulados,this.intervalos.get(j));
            }else{
                this.intervalosAgrupados.add(this.intervalos.get(j));
            }

        }
        */

        if (cAcumulado < 67.5){
            rechazar=false;
        }

        if(this.intervalosAgrupados.size()>1){
            System.out.println("ok");
        }
        return rechazar;
    }


    public void contarFrecuenciaIntervalo(HashMap<Integer,Double> numeros){
        for (var i=0;i<numeros.size();i++)
        {
            Double RND = numeros.get(i);
            for (Intervalo intervalo: intervalos)
            {
                if(RND >= intervalo.getDesde() && RND < intervalo.getHasta())
                {
                    intervalo.setFrecuenciaObservada((int) (intervalo.getFrecuenciaObservada()+1));

                }

            }

        }
    }
    public void generarIntervalos(int n, int cantidadIntervalos, IDistribucion distribucion,double lambda,double desviacion,double media ){
        this.intervalos = new ArrayList<Intervalo>();
        double desde = 0;
        double tamaño = ((double) (media / (double) cantidadIntervalos) );
        double hasta= tamaño;

        for(var i=0;i<cantidadIntervalos;i++)
        {

            double frecuenciaEsperada = distribucion.calcularFrecuenciaEsperada( lambda,desviacion , media, desde, hasta,n);
            Intervalo intervalo = new Intervalo(desde,hasta - 0.001,frecuenciaEsperada);

            desde = hasta;
            hasta += tamaño;
            this.intervalos.add(intervalo);

        }

    }
}

package com.tp.tp3.Graficador.Controladores;

import com.tp.tp3.Generador.Controladores.GeneradorAleatorios;
import com.tp.tp3.Generador.Generador;
import com.tp.tp3.Graficador.Modelos.Exponencial;
import com.tp.tp3.Graficador.Modelos.IDistribucion;
import com.tp.tp3.Graficador.Modelos.Intervalo;
import com.tp.tp3.Graficador.Pantallas.PantallaGraficador;

import java.util.ArrayList;
import java.util.HashMap;

public class GestorGraficador {
    private ArrayList<Intervalo> intervalos = new ArrayList<Intervalo>();
    public void graficarExponencial(int n,int intervalos, double lambda, PantallaGraficador pantalla)
    {
        Exponencial exponencial = new Exponencial();
        this.generarIntervalos(n,intervalos, exponencial ,lambda,-1,-1);
        var numeros = Generador.getGenerador().generarExponencial(n,lambda);
        this.contarFrecuenciaIntervalo(numeros);
        this.calcularChiCuadrado();

    }

    private boolean calcularChiCuadrado() {
        double cAcumulado = 0.0;
        boolean rechazar = true;
        for(var i=0;i<this.intervalos.size();i++){
            var fo = this.intervalos.get(i).getFrecuenciaObservada();
            var fe = this.intervalos.get(i).getFrecuenciaEsperada();
            var c = (double) Math.pow((fo-fe),2) / (fe);
            cAcumulado += c;
            this.intervalos.get(i).setC(c);
            this.intervalos.get(i).setcAcumulado(cAcumulado);
        }
        if (cAcumulado < 67.5){
            rechazar=false;
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
        double tamaño = ((double) (1.0 / (double) cantidadIntervalos) );
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

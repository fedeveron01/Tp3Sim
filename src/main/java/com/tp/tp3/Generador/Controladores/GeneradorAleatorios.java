package com.tp.tp3.Generador.Controladores;

import com.tp.tp3.Generador.Modelos.Exponencial;
import com.tp.tp3.Generador.Modelos.Linea;
import com.tp.tp3.Generador.Modelos.Normal;
import com.tp.tp3.Generador.Modelos.Poisson;

import java.util.ArrayList;
import java.util.HashMap;

public class GeneradorAleatorios
{
    public HashMap<Integer,Double> generarExponencial(int n, double lambda)
    {
        HashMap<Integer,Double> lineas = new HashMap<Integer,Double>();
        for(var i=0;i<n;i++)
        {
            lineas.put(i,Exponencial.generarVariable(Math.random(),lambda));

        }
        return lineas;
    }
    public HashMap<Integer,Double> generarNormal(int n, double desviacion,double media)
    {
        HashMap<Integer,Double> lineas = new HashMap<Integer,Double>();
        for(var i=0;i<n;i+=2)
        {
            var RND1= Math.random();
            var RND2 = Math.random();

            lineas.put(i, Normal.generarN1(RND1,RND2,desviacion,media));
            lineas.put(i+1, Normal.generarN2(RND1,RND2,desviacion,media));

        }
        return lineas;
    }

    public HashMap<Integer,Integer> generarPoisson(int n, double lambda)
    {
        HashMap<Integer,Integer> lineas = new HashMap<Integer,Integer>();
        for(var i=0;i<n;i++)
        {
            lineas.put(i, (int) Poisson.generarRND(lambda));

        }
        return lineas;
    }

    public HashMap<Integer,Double> generarUniforme(int n)
    {
        HashMap<Integer,Double> lineas = new HashMap<Integer,Double>();
        for(var i=0;i<n;i++)
        {
            lineas.put(i, Math.random());

        }
        return lineas;
    }



}

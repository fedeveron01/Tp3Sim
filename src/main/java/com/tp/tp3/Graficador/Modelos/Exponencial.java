package com.tp.tp3.Graficador.Modelos;

public class Exponencial implements IDistribucion {

    @Override
    public double calcularFrecuenciaEsperada(double lambda, double desviacion, double media, double a, double b,int n) {
        return ((1-Math.exp(-lambda*b))-(1-Math.exp(-lambda*a)))  *n;
    }
}

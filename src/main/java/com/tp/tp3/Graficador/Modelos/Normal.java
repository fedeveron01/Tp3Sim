package com.tp.tp3.Graficador.Modelos;

public class Normal implements IDistribucion{
    @Override
    public double calcularFrecuenciaEsperada(double lambda, double desviacion, double media, double a, double b, int n) {
        var marca = (a+b)/2;
        return ((Math.exp(-0.5*((marca-media))/ ( Math.pow(desviacion,2)))/(desviacion*2*Math.PI)))*(b-a);
    }
}

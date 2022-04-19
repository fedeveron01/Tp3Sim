package com.tp.tp3.Graficador.Modelos;

public class Intervalo {
    private int i;
    private double desde;
    private double hasta;
    private double frecuenciaEsperada;
    private double frecuenciaObservada;
    private double marcaDeClase;
    private double c;
    private double cAcumulado;

    public void setFrecuenciaEsperada(double frecuenciaEsperada) {
        this.frecuenciaEsperada = frecuenciaEsperada;
    }

    public void setFrecuenciaObservada(double frecuenciaObservada) {
        this.frecuenciaObservada = frecuenciaObservada;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double getcAcumulado() {
        return cAcumulado;
    }

    public void setcAcumulado(double cAcumulado) {
        this.cAcumulado = cAcumulado;
    }

    public Intervalo(double desde, double hasta, double frecuenciaEsperada, double frecuenciaObservada) {
        this.desde = desde;
        this.hasta = hasta;
        this.frecuenciaEsperada = frecuenciaEsperada;
        this.frecuenciaObservada = frecuenciaObservada;
    }
    public Intervalo(double desde, double hasta, double frecuenciaEsperada) {
        this.desde = desde;
        this.hasta = hasta;
        this.frecuenciaEsperada = frecuenciaEsperada;

    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public double getDesde() {
        return desde;
    }

    public void setDesde(double desde) {
        this.desde = desde;
    }

    public double getHasta() {
        return hasta;
    }

    public void setHasta(double hasta) {
        this.hasta = hasta;
    }

    public double getFrecuenciaEsperada() {
        return frecuenciaEsperada;
    }

    public void setFrecuenciaEsperada(int frecuenciaEsperada) {
        this.frecuenciaEsperada = frecuenciaEsperada;
    }

    public double getFrecuenciaObservada() {
        return frecuenciaObservada;
    }

    public void setFrecuenciaObservada(int frecuenciaObservada) {
        this.frecuenciaObservada = frecuenciaObservada;
    }

    public double getMarcaDeClase() {
        return marcaDeClase;
    }

    public void setMarcaDeClase(double marcaDeClase) {
        this.marcaDeClase = marcaDeClase;
    }
}

package com.tp.tp3.Generador;

import com.tp.tp3.Generador.Controladores.GeneradorAleatorios;

public class SingletonGenerador {
    private static SingletonGenerador singletonGenerador;
    private GeneradorAleatorios generadorAleatorios;

    public SingletonGenerador(GeneradorAleatorios generadorAleatorios)
    {
        this.generadorAleatorios = generadorAleatorios;
    }

    public static SingletonGenerador getSingletonGenerador(GeneradorAleatorios generadorAleatorios)
    {
        if(singletonGenerador == null)
        {
            singletonGenerador = new SingletonGenerador(generadorAleatorios);
        }

        return singletonGenerador;
    }


    public GeneradorAleatorios getGeneradorAleatorios() {
        return generadorAleatorios;
    }

    public void setGeneradorAleatorios(GeneradorAleatorios generadorAleatorios) {
        this.generadorAleatorios = generadorAleatorios;
    }
}

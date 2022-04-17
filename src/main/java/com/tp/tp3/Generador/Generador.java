package com.tp.tp3.Generador;

import com.tp.tp3.Generador.Controladores.GeneradorAleatorios;

public class Generador {
    static GeneradorAleatorios generadorAleatorios = new GeneradorAleatorios();
    public static GeneradorAleatorios getGenerador(){
        return generadorAleatorios;
    }
}

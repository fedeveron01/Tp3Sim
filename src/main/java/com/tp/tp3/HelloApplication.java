package com.tp.tp3;

import com.tp.tp3.Generador.Controladores.GeneradorAleatorios;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        GeneradorAleatorios generadorAleatorios = new GeneradorAleatorios();
        var listaUniforme = generadorAleatorios.generarUniforme(1000);
        var listaExp = generadorAleatorios.generarExponencial(1000,10);
        var listaNormal = generadorAleatorios.generarNormal(1000,5,40);
        var listaPoisson = generadorAleatorios.generarPoisson(1000,10);

        System.out.println("Ok");

    }

    public static void main(String[] args) {
        launch();
    }
}
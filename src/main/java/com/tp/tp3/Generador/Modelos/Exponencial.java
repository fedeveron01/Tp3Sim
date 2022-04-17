package com.tp.tp3.Generador.Modelos;

 public class Exponencial {
    public static double generarVariable(double RND,double lambda){
        return (-1/lambda)*Math.log(1-RND);
    }
}

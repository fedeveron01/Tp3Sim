package com.tp.tp3.Generador.Modelos;

import java.util.ArrayList;

public class Normal {
    public static double generarN1(double RND1,double RND2,double desviacion,double media)
    {

        return ( ( Math.sqrt((-2)*Math.log(RND1)) ) * Math.cos(2*Math.PI*RND2) ) * desviacion + media ;
    }

     public static double generarN2(double RND1,double RND2,double desviacion,double media)
     {

         return ( ( Math.sqrt((-2)*Math.log(RND1)) ) * Math.sin(2*Math.PI*RND2) ) * desviacion + media ;
     }

     public static double generarRNDConvolucion(ArrayList<Double> randoms, double desviacion, double media){
        double z = 0;
        double suma = 0;
        for(double random : randoms){
                 suma += random;
         }
        z = (suma-6) * desviacion + media ;
        return z;
     }

}

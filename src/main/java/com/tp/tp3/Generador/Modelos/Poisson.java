package com.tp.tp3.Generador.Modelos;

 public class Poisson {
    public static int generarRND(double lambda)
    {
        double  p = 1 ;
        int x = -1;
        double a = Math.exp(-lambda);
        do{
            var u = Math.random();
            p = p*u;
            x += 1;

        }while(p>=a);

        return x;


    }


}

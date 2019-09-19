/*
E/15/208-E/15/211
CO225-Software Construction
Fractals: Natures building blocks
*/

import java.util.Arrays;    //use to compare, sort, and search thearguments given in arrays to the main method

public class Fractal {

  public static void main(String [] args) {

    if (args.length == 0) {       //return if a set is not initialized
      System.out.println("Select Set To Plot");
      return;
    }

    String set = args[0];     //store the argument 1 to a string variable 'set'

    if (set.equals("Julia")) {    //compare whether the argument 1 equals to Julia


      double cRex = -0.4;       //default value for real part of C
  		double cImy = 0.6;        //default value for imaginary part of C
      int iterations = 1000;      //default value for iterations


          if (args.length == 3) {       //compare the argument length.If it equals to three, update each to the variables below to the argument values respectively by making string arguments to doubles and integers appropriately
            cRex = Double.parseDouble(args[1]);
            cImy = Double.parseDouble(args[2]);
          }

          if (args.length == 4) {        //compare the argument length.If it equals to four, update each to the variables below to the argument values respectively by making string arguments to doubles and integers appropriately
            cRex = Double.parseDouble(args[1]);
            cImy = Double.parseDouble(args[2]);
            iterations = Integer.parseInt(args[3]);
          }

      new Julia(cRex, cImy, iterations);    //calling the set Julia by making a new object of class julia
    }


    if (set.equals("Mandelbrot")) {       //compare whether the argument 1 equals to Mandelbrot
     double riMinRex=-1;          //default minimum value for real region
     double riMaxRex=1;           //default maximum value for real region
     double riMinImy=-1;          //default minimum value for imaginary region
     double riMaxImy=1;           //default maximum value for imaginary region
     int iterations=1000;         //default value for iterations


         if (args.length == 5) {              //compare the argument length.If it equals to five, update each to the variables below to the argument values respectively by making string arguments to doubles and integers appropriately
           riMinRex  = Double.parseDouble(args[1]);
           riMaxRex  = Double.parseDouble(args[2]);
           riMinImy  = Double.parseDouble(args[3]);
           riMaxImy = Double.parseDouble(args[4]);
         }

         if (args.length == 6) {              //compare the argument length.If it equals to six, update each to the variables below to the argument values respectively by making string arguments to doubles and integers appropriately
           riMinRex  = Double.parseDouble(args[1]);
           riMaxRex  = Double.parseDouble(args[2]);
           riMinImy  = Double.parseDouble(args[3]);
           riMaxImy = Double.parseDouble(args[4]);
           iterations = Integer.parseInt(args[5]);
         }

     new Mandelbrot(riMinRex, riMaxRex, riMinImy, riMaxImy, iterations);       //calling the set Mandelbrot by making a new object of class mandelbrot
    }

  }
}

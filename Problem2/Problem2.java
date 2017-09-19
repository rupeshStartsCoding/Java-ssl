/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;


    abstract class RunnableDemo implements Runnable {
   public double[] a;
   public double[] b;
   public double[] c= new double[5000];
   public double[] d= new double[5000];

   
   public String s1;
   public Thread t;
   

  abstract public void run ();     
   public void start () {
      if (t == null) {
         t = new Thread (this, s1);
         t.start ();
      }
   }

    }

    class MULT extends  RunnableDemo {

MULT(double[] a,double[] b,String s1) {
      this.a= a;
      this.b= b;
      this.s1= s1;
   }

   @Override
   public void run() {
        
             for(int i = 0; i <5000; i++) {
            try{
                
                d[i]=a[i]*b[i];
               
                }catch(Exception e){System.out.println(e);}
                
            }
              
      }
   

    }

    class ADD extends RunnableDemo {


  ADD(double[] a,double[] b,String s1) {
      this.a= a;
      this.b= b;
      this.s1= s1;
   }
   @Override
   public void run() {
        
             for(int i = 0; i <5000; i++) {
            try{
                
                c[i]=a[i]+b[i];

               
                }catch(Exception e){System.out.println(e);}
                
            }
              
      }
   

    }
    public class Problem2 {
   public static void main(String args[]) {
       
           double array1[]= new double[5000];
       double array2[]= new double[5000];
       
       
       for (int i=0; i<5000; i++){
           array1[i]= Math.random();
           array2[i]= Math.random();
       }
      
RunnableDemo a1= new ADD(array1,array2,"Thread1");
RunnableDemo a2= new MULT(array1,array2,"Thread2");
    a1.start();
    a2.start();   
   }
    }   


    /* The observations are as follows:
    Sometimes the multithread takes less time and sometimes the sequential takes less time.This all depends on 
    the running process. The computational speed of parallal processing is definitely greater than sequential. 
    But there is a delay in context switching.And the creation of thread also takes time.Hence this observation.*/
    

   

package org.example.proyectotapbd.componentes;

import java.util.Random;

public class Hilo extends Thread{

    public Hilo(String nombre){
        super(nombre);//asignar nombre al hilo, por medio del padre
    }
    @Override
    public void run() {
        super.run();
        for (int i = 1; i <= 10; i++) {
           try {
             sleep((long) (Math.random() * 3000));
           }catch(InterruptedException e){ //este es mas especifico de manejo de hilos
             throw new RuntimeException();
           }
            System.out.println("El corredor "+ this.getName() + "llego al km "+ i);
        }
    }
}

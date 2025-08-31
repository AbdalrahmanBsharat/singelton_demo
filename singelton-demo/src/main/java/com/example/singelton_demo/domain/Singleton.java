package com.example.singelton_demo.domain;

import java.util.concurrent.locks.ReentrantLock;

public class Singleton {

    private static Singleton instance = null;
    private Object obj;
    private static final ReentrantLock LOCK = new ReentrantLock();
    public static final Integer  Comp1 = 2000;
    public static final Integer Comp3 = 3000;

    private Singleton (){
    }

    public static Singleton getInstance(){
       LOCK.lock();
       try {
           if(instance == null){
               return  new Singleton();
           }
       } finally {
           LOCK.unlock();
       }
       return instance;
    }

}

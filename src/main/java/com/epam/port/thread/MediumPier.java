package com.epam.port.thread;

import com.epam.port.entity.port.BasePier;
import com.epam.port.entity.ship.Ship;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;


/**
 * Created by User on 30.07.2017.
 *
 */
public class MediumPier extends BasePier implements Runnable{
    private Semaphore place1 = new Semaphore(1, true);
    private Semaphore place2 = new Semaphore(1, true);
    private long timeWork;
    private final long ALCOHOLIC = 1000;
    private boolean work = true;

    public MediumPier(long timeWork){
        this.timeWork = timeWork;
    }

    public boolean unloading(Ship ship) {
        try {
            if (place1.tryAcquire(5, TimeUnit.MILLISECONDS)) {
                System.out.println("Средний Пирс: Мы начали разгрузку корабля - " + ship.name() + " !");
                Thread.sleep(timeWork+ship.volume());
                place1.release();
                System.out.println("Средний Пирс: Мы разгрузили "+ ship.name());
                return true;
            }else if (place2.tryAcquire(5, TimeUnit.MILLISECONDS)){
                System.out.println("Средний Пирс: Уы нашали лазгрузку коабля - " + ship.name() + " !");
                Thread.sleep(timeWork + ALCOHOLIC + ship.volume());
                place2.release();
                System.out.println("Средний Пирс: Всё разглузили "+ ship.name());
                return true;
            }else{
                return false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void work(boolean work) {
        this.work = work;
    }

    public void run() {
        while (work){

        }
    }
}

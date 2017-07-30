package com.epam.port.thread;

import com.epam.port.entity.ship.Bark;
import com.epam.port.entity.ship.Galleon;
import com.epam.port.entity.ship.Ship;
import com.epam.port.entity.ship.Sloop;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by User on 27.07.2017.
 */

public class Sea implements Runnable {
    private Random rnd = new Random();
    private Ship ship;
    private int count;
    private int i = -1;
    private final int BIG_COUNT = 1000;
    private final int SMALL_COUNT = 500;
    private BlockingQueue<Ship> queue = new ArrayBlockingQueue<Ship>(1);

    public Sea(int count){
        this.count = count;
    }

    public void run() {
        while (i++ < count) {
            switch (rnd.nextInt(3)) {
                case 0:
                    ship = new Sloop(rnd.nextInt(BIG_COUNT));
                    break;
                case 1:
                    ship = new Bark(SMALL_COUNT + rnd.nextInt(BIG_COUNT));
                    break;
                case 2:
                    ship = new Galleon(BIG_COUNT + rnd.nextInt(BIG_COUNT));
                    break;
            }
            try {
                queue.put(ship);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Ship getShip() {
        try {
            return queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}

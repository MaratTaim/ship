package com.epam.port.entity.port;

import com.epam.port.entity.ship.Ship;
import com.epam.port.thread.BigPier;
import com.epam.port.thread.MediumPier;
import com.epam.port.thread.SmallPier;

import java.util.concurrent.Semaphore;

/**
 * Created by User on 27.07.2017.
 *
 */

public class Port {
    private final static int POOL_SIZE = 6; // размер пула
    private final Semaphore semaphore = new Semaphore(POOL_SIZE, true);
    private BigPier bigPier;
    private MediumPier mediumPier;
    private SmallPier smallPier;
    private final long SLOW = 5000;
    private final long MEDIUM = 2500;
    private final long FAST = 1500;


    // здесь запустит 3 патоков
    public Port() {
        smallPier = new SmallPier(SLOW);
        mediumPier = new MediumPier(MEDIUM);
        bigPier = new BigPier(FAST);
        new Thread(smallPier).start();
        new Thread(mediumPier).start();
        new Thread(bigPier).start();
    }

    public synchronized void getPier(PierType type, Ship ship) {
        if (semaphore.tryAcquire()) {
            switch (type) {
                case BIG:
                    boolean check = bigPier.unloading(ship);
                    if (check) {
                        break;
                    }
                case MEDIUM:
                    check = mediumPier.unloading(ship);
                    if (check){
                        break;
                    }
                case SMALL:
                    smallPier.unloading(ship);
                    break;
            }
        }
    }

    public void returnPier() {
        semaphore.release();
    }

    public void endWork(){
        smallPier.work(false);
        mediumPier.work(false);
        bigPier.work(false);
    }

}

package com.epam.port.entity.ship;

/**
 * Created by User on 27.07.2017.
 * маленький = Шлюп
 * средний = Барк
 * большой = Галеон
 */
public interface Ship {

    long volume();
    String name();
}

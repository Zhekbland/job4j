package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

/**
 * Class RectangleMove creates moving of rectangle.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 2.
 * @since 16.01.2019.
 */
public class RectangleMove implements Runnable {
    private final Rectangle rect;

    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        while (true) {
            if (this.rect.getX() == 300) {
                while (this.rect.getX() != 0) {
                    this.rect.setX(rect.getX() - 1);
                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            } else {
                this.rect.setX(this.rect.getX() + 1);
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
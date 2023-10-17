package rectangles;

import common.Interactable;
import common.MainCanvas;
import common.Sprite;

import java.awt.*;
import java.util.Random;

public class Rectangle extends Sprite implements Interactable {
    private static Random rnd = new Random();
    private final Color color;
    private float velocityX;
    private float velocityY;

    public Rectangle() {
        halfHeight = 20 + (float)(Math.random() * 50f);
        halfWidth = 2f * halfHeight;
        color = new Color(rnd.nextInt());
        velocityX = 130f + (float)(Math.random() * 250f);
        velocityY = 130f + (float)(Math.random() * 250f);
    }

    @Override
    public void render(MainCanvas canvas, Graphics g) {
        g.setColor(color);
        g.fill3DRect((int)getLeft(), (int)getTop(), (int)getWidth(), (int)getHeight(), false);
    }

    @Override
    public void update(MainCanvas canvas, float deltaTime) {
        x += velocityX * deltaTime;
        y += velocityY * deltaTime;

        if(getLeft() < canvas.getLeft()) {
            setLeft(canvas.getLeft());
            velocityX = -velocityX;
        }
        if(getRight() > canvas.getRight()) {
            setRight(canvas.getRight());
            velocityX = -velocityX;
        }
        if(getTop() < canvas.getTop()) {
            setTop(canvas.getTop());
            velocityY = -velocityY;
        }
        if(getBottom() > canvas.getBottom()) {
            setBottom(canvas.getBottom());
            velocityY = -velocityY;
        }
    }
}

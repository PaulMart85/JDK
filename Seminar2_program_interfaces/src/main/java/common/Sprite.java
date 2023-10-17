package common;

import java.awt.*;

/**
 * Абстрактный для описания любого геометрического объекта
 */
public abstract class Sprite implements Interactable {
    protected float x; // координаты центра геомобъекта
    protected float y;
    protected float halfWidth; // границы от центра геомобъекта - размеры
    protected float halfHeight;

    protected float getLeft() { return x - halfWidth; }
    protected void setLeft(float left) { x = left + halfWidth; }
    protected float getRight() { return x + halfWidth; }
    protected void setRight(float right) { x = right - halfWidth; }
    protected float getTop() { return y - halfHeight; }
    protected void setTop(float top) { y = top + halfHeight; }
    protected float getBottom() { return y + halfHeight; }
    protected void setBottom(float bottom) { y = bottom - halfHeight; }

    protected float getWidth() { return 2f * halfWidth; }
    protected float getHeight() { return 2f * halfHeight; }

    // наследники реализуют следующие методы предметно
    public void update(MainCanvas canvas, float deltaTime) {}
    public void render(MainCanvas canvas, Graphics g) {}
}

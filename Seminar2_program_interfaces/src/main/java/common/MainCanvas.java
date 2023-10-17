package common;

import javax.swing.*;
import java.awt.*;

/**
 * Холст заданных размеров и фона, на котором можно рисовать все, что угодно.
 */
public class MainCanvas extends JPanel {

//    private final MainWindow controller;
    private final CanvasRepaintListener controller; // указание конве, на каком окне она расположена,
                                                    // чтобы "дергать" методы перерисовки в этом окне.
    private long lastFrameTime;

    public MainCanvas(CanvasRepaintListener controller) {
        //setBackground(Color.BLUE);
        this.controller = controller;
        lastFrameTime = System.nanoTime();
    }

    /**
     * Рисовка компонент на конве, сообщение об этом основному окну.
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            Thread.sleep(16); // FPS, близкий к 60
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        float deltaTime = (System.nanoTime() - lastFrameTime) * 0.000000001f; // перевод в секунды
        controller.onDrawFrame(this, g, deltaTime);
        lastFrameTime = System.nanoTime();
        repaint(); // перерисовка - зацикливает процесс paintComponent и полностью
                   // нагружает ядро процессора, поэтому стоит задержка 16 миллисек
    }

    public int getLeft() { return 0; }
    public int getRight() { return getWidth() - 1; }
    public int getTop() { return 0; }
    public int getBottom() { return getHeight() - 1; }
}

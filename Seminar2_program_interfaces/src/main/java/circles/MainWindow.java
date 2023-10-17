package circles;

import common.CanvasRepaintListener;
import common.Interactable;
import common.MainCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainWindow extends JFrame implements CanvasRepaintListener, Thread.UncaughtExceptionHandler {
    public static final int POS_X = 400;
    public static final int POS_Y = 200;
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    public static final int FIGURES_NUMBER = 10; // число фигур на канве
//    private final Sprite[] sprites = new Sprite[FIGURES_NUMBER];
    private final Interactable[] interactables = new Interactable[FIGURES_NUMBER+1];
//    private final Background background = new Background();

    private MainWindow() {
        Thread.setDefaultUncaughtExceptionHandler(this); // основное окно и есть - место исключения
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Circles");
        interactables[0] = new Background();
        // наполнение канвы формами, на откуп которым отданы правила перерисовки и обновления
        for (int i = 1; i < interactables.length; i++)
            //sprites[i] = i%2 == 0 ? new Ball() : new rectangles.Rectangle();
            interactables[i] = new Ball();

        MainCanvas canvas = new MainCanvas(this); // передаем канве основное окно, сообщая,
                                                          // что здесь будет находиться и управляться канва

        // на канву установлен слушатель, отлавливающий действия мыши на канве (с уточнением дейтсвия - адаптер MouseAdapter)
        //canvas.addMouseListener(this);
        canvas.addMouseListener(new MouseAdapter() { // как анонимный класс
            @Override
            public void mouseReleased(MouseEvent e) {
                throw new ArrayIndexOutOfBoundsException("Ooooops"); // например, при нажатии мышкой на канву
                                                            // будет выброшено непроверяемое исключение
            }
        });
        add(canvas);
        setVisible(true);
    }

    /**
     * Реакция на то, что на канве canvas что-то нарисовалось.
     * @param canvas канва, на которой происходит действие
     * @param g графика для команд на рисование
     * @param deltaTime время обновления кадра на канве, запрашивается у канвы, как только на ней обновится кадр
     */
    public void onDrawFrame(MainCanvas canvas, Graphics  g, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, g);
    }

    private void update(MainCanvas canvas, float deltaTime) {
        for (Interactable interactable : interactables)
            interactable.update(canvas, deltaTime);
//        background.update(canvas, deltaTime);
    }
    private void render(MainCanvas canvas, Graphics g) {
        for (Interactable interactable : interactables)
            interactable.render(canvas, g);
//        background.render(canvas, g);
    };

    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
                new MainWindow();
//            }
//        });
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        JOptionPane.showMessageDialog(null, e.getMessage(), "Exception!", JOptionPane.ERROR_MESSAGE);
    }

}

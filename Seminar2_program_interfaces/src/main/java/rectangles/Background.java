package rectangles;

import common.Interactable;
import common.MainCanvas;

import java.awt.*;

public class Background implements Interactable {
    private float time;
    private static final float AMPLITUDE = 200f / 2f;
    private Color color;

    @Override
    public void update(MainCanvas canvas, float deltaTime) {
        time += deltaTime;
        int red = Math.round(AMPLITUDE + AMPLITUDE * (float)Math.sin(time * 5f));
        int green = Math.round(AMPLITUDE + AMPLITUDE * (float)Math.sin(time * 4f));
        int blue = Math.round(AMPLITUDE + AMPLITUDE * (float)Math.sin(time * 2f));
        color = new Color(red, green, blue);
    }

    @Override
    public void render(MainCanvas canvas, Graphics g) {
        canvas.setBackground(color);
    }

}

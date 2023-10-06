import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

/**
 * Игровая панель в игровом окне
 */
public class Map extends JPanel {
    private static final Random RANDOM = new Random();
    private static final int DOT_PADDING = 7;

    private int gameOverType;
    private static final int STATE_DRAW = 0;
    private static final int STATE_WIN_HUMAN = 1;
    private static final int STATE_WIN_AI = 2;

    private static final String MSG_WIN_HUMAN = "Победил игрок!";
    private static final String MSG_WIN_AI = "Победил комп!";
    private static final String MSG_DRAW = "Ничья!";

    private final int HUMAN_DOT = 1;
    private final int AI_DOT = 2;
    private final int EMPTY_DOT = 0;
    private int fieldSizeX;
    private int fieldSizeY;
    private int winLength;
    private int[][] field;

    private int panelWidth;
    private int panelHeight;
    private int cellWidth;
    private int cellHeight;

    private boolean isGameOver;
    private boolean isInitialized;
    public Map() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                update(e);
            }
        });

        isInitialized = false;
    }

    private void update(MouseEvent e) {
        if (isGameOver || !isInitialized) return;
        int cellX = e.getX() / cellWidth;
        int cellY = e.getY() / cellHeight;
        if (!isValidCell(cellX, cellY) || !isEmptyCell(cellX, cellY)) return;
        field[cellY][cellX] = HUMAN_DOT;
        if (checkEndGame(HUMAN_DOT, STATE_WIN_HUMAN)) return;
        aiTurn();
        repaint();
        if (checkEndGame(AI_DOT, STATE_WIN_AI)) return;
    }

    private boolean checkEndGame(int dot, int gameOverType) {
        if (checkWin(dot)) {
            this.gameOverType = gameOverType;
            isGameOver = true;
            repaint();
            return true;
        }
        if (isMapFull()) {
            this.gameOverType = STATE_DRAW;
            isGameOver = true;
            repaint();
            return true;
        }
        return false;
    }

    /**
     * Инициализирует старт игры
     * @param mode - режим игры (компьютер-человек, человек-человек)
     * @param fSzX - размер поля по X
     * @param fSzY - размер поля по Y
     * @param wLen - количество крестиков/ноликов для победы (условие победы)
     */
    public void startNewGame(int mode, int fSzX, int fSzY, int wLen) {
        System.out.printf("\nMode: %d;\nSize: x=%d, y=%d;\nWin Length: %d\n",
                mode, fSzX, fSzY, wLen);
        fieldSizeX = fSzX;
        fieldSizeY = fSzY;
        winLength = wLen;
        initMap();
        isGameOver = false;
        isInitialized = true;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    private void render(Graphics g) {
        if (!isInitialized) return;
        panelWidth = getWidth();
        panelHeight = getHeight();
        cellWidth = panelWidth / fieldSizeX;
        cellHeight = panelHeight / fieldSizeY;

        g.setColor(Color.BLACK);
        for (int i = 0; i < fieldSizeX; i++) {
            int x = i * cellWidth;
            g.drawLine(x, 0, x, panelHeight);
        }
        for (int j = 0; j < fieldSizeY; j++) {
            int y = j * cellHeight;
            g.drawLine(0, y, panelWidth, y);
        }

        for (int y = 0; y < fieldSizeY; y++)
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[y][x] == EMPTY_DOT) continue;

                if (field[y][x] == HUMAN_DOT) {
                    g.setColor(Color.BLUE);
                    g.fillOval(x * cellWidth + DOT_PADDING,
                            y * cellHeight + DOT_PADDING,
                            cellWidth - DOT_PADDING * 2,
                            cellHeight - DOT_PADDING * 2);
                } else if (field[y][x] == AI_DOT) {
                    g.setColor(Color.RED);
                    g.fillOval(x * cellWidth + DOT_PADDING,
                            y * cellHeight + DOT_PADDING,
                            cellWidth - DOT_PADDING * 2,
                            cellHeight - DOT_PADDING * 2);
                }
                else {
                    throw new RuntimeException("Unexpected value " + field[y][x] +
                            " in cell: x=" + x + " y=" + y);
                }
            }
        if (isGameOver) showMsgGameOver(g);
    }

    private void showMsgGameOver(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 200, getWidth(), 70);
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Times new roman", Font.BOLD, 48));
        switch (gameOverType) {
            case STATE_DRAW:
                g.drawString(MSG_DRAW, 180, getHeight() / 2); break;
            case STATE_WIN_AI:
                g.drawString(MSG_WIN_AI, 90, getHeight() / 2); break;
            case STATE_WIN_HUMAN:
                g.drawString(MSG_WIN_HUMAN, 70, getHeight() / 2); break;
            default:
                throw new RuntimeException("Unexpected gameOver state: " + gameOverType);
        }
    }

    /**
     * Логика игры
     */
    private void initMap() {
//        fieldSizeX = 3;
//        fieldSizeY = 3;
        field = new int[fieldSizeY][fieldSizeX];
        for (int i = 0; i < fieldSizeY; i++)
            for (int j = 0; j < fieldSizeX; j++)
                field[i][j] = EMPTY_DOT;
    }

    private boolean isValidCell(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    private boolean isEmptyCell(int x, int y) {
        return field[y][x] == EMPTY_DOT;
    }

    private void aiTurn() {
        int x, y;
        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!isEmptyCell(x, y));
        field[y][x] = AI_DOT;
    }

    private boolean checkWin(int c) {
        for (int i = 0; i < fieldSizeY; i++)
            for (int j = 0; j < fieldSizeX; j++)
                if (checkTemplate(i, j, c)) return true;
        return false;
    }

    private boolean checkTemplate(int y, int x, int dot) {

        int cnt = 0;
        for (int i = x; i < x + winLength; i++) { // проверка горизонтали
            if (!isValidCell(i, y) || field[y][i] != dot) break;
            cnt++;
        }
        if (cnt == winLength) return true;

        cnt = 0;
        for (int j = y; j < y + winLength; j++) { // проверка вертикали
            if (!isValidCell(x, j) || field[j][x] != dot) break;
            cnt++;
        }
        if (cnt == winLength) return true;

        cnt = 0;
        for (int i = x, j = y; i < x + winLength; i++, j++) { // проверка диагонали вперед
            if (!isValidCell(i, j) || field[j][i] != dot) break;
            cnt++;
        }
        if (cnt == winLength) return true;

        cnt = 0;
        for (int i = x, j = y; i > x - winLength; i--, j++) { // проверка диагонали назад
            if (!isValidCell(i, j) || field[j][i] != dot) break;
            cnt++;
        }
        return cnt == winLength;
    }

    private boolean isMapFull() {
        for (int i = 0; i < fieldSizeY; i++)
            for (int j = 0; j < fieldSizeX; j++)
                if (field[i][j] == EMPTY_DOT)
                    return false;
        return true;
    }

}

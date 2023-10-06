import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

/**
 * Окно настроек для конкретного игрового окна
 */
public class SettingsWindow extends JFrame {
    public static final int WINDOW_HEIGHT = 230;
    public static final int WINDOW_WIDTH = 350;

    public static final int MIN_FSzX = 3;
    public static final int MIN_FSzY = 3;
    public static final int MAX_FSzX = 10;
    public static final int MAX_FSzY = 10;


    JButton btnStart = new JButton("Start new game");
    JLabel labelFSzX = new JLabel("Размер поля по Х: " + (MIN_FSzX + (MAX_FSzX - MIN_FSzX) / 2));
    JLabel labelFSzY = new JLabel("Размер поля по Y: " + (MIN_FSzY + (MAX_FSzY - MIN_FSzY) / 2));
    JLabel labelWlen = new JLabel("Выберите длину для победы: " + Math.min(MIN_FSzX, MIN_FSzY));
    JSlider sliderFSzX = new JSlider(MIN_FSzX, MAX_FSzX, MIN_FSzX + (MAX_FSzX - MIN_FSzX) / 2);
    JSlider sliderFSzY = new JSlider(MIN_FSzY, MAX_FSzY, MIN_FSzY + (MAX_FSzY - MIN_FSzY) / 2);
    JSlider sliderWlen = new JSlider(Math.min(MIN_FSzX, MIN_FSzY), Math.max(MIN_FSzX + (MAX_FSzX - MIN_FSzX) / 2, MIN_FSzY + (MAX_FSzY - MIN_FSzY) / 2), Math.min(MIN_FSzX, MIN_FSzY));
    ButtonGroup buttonGroup = new ButtonGroup();


    public SettingsWindow(GameWindow gameWindow) {
//        setLocationRelativeTo(gameWindow);
        setLocation(new Point(gameWindow.getLocation().x + (gameWindow.getWidth() - WINDOW_WIDTH) / 2, gameWindow.getLocation().y + (gameWindow.getHeight() - WINDOW_HEIGHT) / 2));
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Settings");

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameWindow.startNewGame(getSelectedRadioButton(buttonGroup), sliderFSzX.getValue(), sliderFSzY.getValue(), sliderWlen.getValue());
                setVisible(false);
            }
        });

        sliderFSzX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                labelFSzX.setText("Размер поля по Х: " + sliderFSzX.getValue());
                sliderWlen.setMaximum(Math.max(sliderFSzX.getValue(), sliderFSzY.getValue()));
                labelWlen.setText("Выберите длину для победы: " + sliderWlen.getValue());
            }
        });
        sliderFSzY.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                labelFSzY.setText("Размер поля по Y: " + sliderFSzY.getValue());
                sliderWlen.setMaximum(Math.max(sliderFSzX.getValue(), sliderFSzY.getValue()));
                labelWlen.setText("Выберите длину для победы: " + sliderWlen.getValue());
            }
        });

        sliderWlen.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                labelWlen.setText("Выберите длину для победы: " + sliderWlen.getValue());
            }
        });

        JPanel panMode = new JPanel(new GridLayout(11, 1));
        panMode.add(new JLabel("Выберите режим игры:"));

        JRadioButton modeHumanComputer = new JRadioButton("Человек против компьютера");
        JRadioButton modeHumanHuman = new JRadioButton("Человек против человека");
        modeHumanComputer.setSelected(true);
        buttonGroup.add(modeHumanComputer);
        buttonGroup.add(modeHumanHuman);
        panMode.add(modeHumanComputer);
        panMode.add(modeHumanHuman);

        panMode.add(new JLabel("Выберите размеры поля"));
        panMode.add(labelFSzX);
        panMode.add(sliderFSzX);
        panMode.add(labelFSzY);
        panMode.add(sliderFSzY);

        panMode.add(labelWlen);
        panMode.add(sliderWlen);

        panMode.add(btnStart);

        add(panMode, BorderLayout.CENTER);
    }

    private int getSelectedRadioButton(ButtonGroup buttonGroup) {

        Enumeration<AbstractButton> abstractButtons = buttonGroup.getElements();
        JRadioButton radioButton = null;
        int radioButtonNumber = 0;

        while (abstractButtons.hasMoreElements()) {
            radioButton = (JRadioButton) abstractButtons.nextElement();
            if (radioButton.isSelected()) {
                break;
            }
            radioButtonNumber++;
        }
        return radioButtonNumber;
    }
}
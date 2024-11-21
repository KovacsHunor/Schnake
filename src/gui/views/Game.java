package gui.views;

import gui.game.FieldGui;
import gui.main.Main;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import logic.field.Field;
import logic.util.Utils;

public final class Game extends JPanel implements ActionListener {
    private final Timer timer;
    private FieldGui fieldGui;
    JPanel fieldPanel = new JPanel(new GridBagLayout());
    private JPanel right = new JPanel();
    private final JLabel pointLabel = new JLabel("0");
    int delta = 0;

    public Game() {
        timer = new Timer(2000/Utils.SPEED, this);
        Field.newInstance(2, 6);

        setLayout(new GridBagLayout());

        fieldGui = new FieldGui();

        fieldPanel.setPreferredSize(new Dimension(1050, 1050));
        fieldPanel.add(fieldGui);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JPanel left = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));

        JButton menuButton = new JButton("Menu");
        menuButton.addActionListener(ae -> {
            Main.toMenu();
            stopTimer();
        });

        pointLabel.setFont(new Font("Serif", Font.BOLD, 64));

        left.add(menuButton);
        pointLabel.setAlignmentX(CENTER_ALIGNMENT);
        right.add(pointLabel);
        right.add(fieldPanel);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(left, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(right, gbc);

    }

    public void updatePointLabel() {
        pointLabel.setText("" + Field.getInstance().getPlayer().getPoint());
    }

    public FieldGui getFieldGui() {
        return fieldGui;
    }

    public void start() {
        fieldGui.newField();
        startTimer();
    }

    public void stopTimer() {
        timer.stop();
    }

    public void startTimer() {
        timer.start();
    }

    private void tick() {
        updatePointLabel();
        fieldGui.tick();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        delta = (delta + 1) % (3);
        if (delta == 0) {
            tick();
        }
        fieldGui.repaint();
    }
}

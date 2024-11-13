package gui.views;

import gui.Resettable;
import gui.game.Field;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import main.Main;

public class Game extends JPanel implements Resettable{
    private Field field;
    private JLabel pointLabel = new JLabel("0");

    public Game() {
        setLayout(new GridBagLayout());

        field = new Field(this);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JPanel left = new JPanel();
        JPanel right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));

        JButton menuButton = new JButton("Menu");
        menuButton.setFont(new Font("Serif", Font.PLAIN, 18));
        menuButton.addActionListener(ae -> {
            Main.switchTo("menu");
            field.reset();
        });

        pointLabel.setFont(new Font("Serif", Font.BOLD, 32));

        left.add(menuButton);
        pointLabel.setAlignmentX(CENTER_ALIGNMENT);
        right.add(pointLabel);
        right.add(field);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(left, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(right, gbc);

        setVisible(true);
    }

    public void setPointLabel(int point){
        pointLabel.setText("" + point); 
    }

    @Override
    public void reset() {
        field.reset();
        field.updatePoint();
    }

    public void start() {
        field.start();
    }
}

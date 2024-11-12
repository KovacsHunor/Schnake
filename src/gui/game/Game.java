package gui.game;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import main.Main;

public class Game extends JPanel {
    JLabel pointLabel = new JLabel("0");

    public Game() {
        setName("Game");
        setLayout(new GridBagLayout());

        Field field = new Field(this);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JPanel upper = new JPanel();

        JButton menuButton = new JButton("Menu");
        menuButton.addActionListener(ae -> {
            Main.switchTo("menu");
            field.reset();
        });
        

        upper.add(menuButton);
        upper.add(pointLabel);

        JPanel lower = new JPanel();
        lower.add(field);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(upper, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lower, gbc);

        setVisible(true);
    }

    public void setPointLabel(int point){
        pointLabel.setText("" + point);
    }
}

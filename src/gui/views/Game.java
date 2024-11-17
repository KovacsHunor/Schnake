package gui.views;

import gui.game.Field;
import gui.game.FieldGui;
import gui.main.Main;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game extends JPanel{
    private FieldGui fieldPanel;
    private JPanel right = new JPanel();
    private final JLabel pointLabel = new JLabel("0");

    public Game() {
        setLayout(new GridBagLayout());

        fieldPanel = new FieldGui();
        fieldPanel.setField(new Field(this, fieldPanel));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JPanel left = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));

        JButton menuButton = new JButton("Menu");
        menuButton.addActionListener(ae -> {
            Main.switchTo("menu");
            fieldPanel.getField().reset();
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

    public void setPointLabel(int point){
        pointLabel.setText("" + point); 
    }

    public FieldGui getFieldGui(){
        return fieldPanel;
    }

    public void reset() {
        fieldPanel.getField().reset();
        fieldPanel.getField().updatePoint();
    }

    public void start() {
        fieldPanel.getField().start();
    }
}

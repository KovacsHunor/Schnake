package gui.views;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static logic.util.Util.buttonFont;
import main.Main;

public class DeathScreen extends JPanel {
    private JLabel scoreDisplay = new JLabel("0");
    private JLabel newRecord = new JLabel("New Personal Record!");

    public DeathScreen() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JPanel score = new JPanel();
        JLabel scoreText = new JLabel("Score:");
        scoreText.setFont(new Font("Serif", Font.PLAIN, 32));
        
        scoreDisplay.setFont(new Font("Serif", Font.BOLD, 64));
        
        score.add(scoreText);
        score.add(scoreDisplay);

        
        newRecord.setFont(new Font("Comic Sans MS", Font.BOLD, 32));
        newRecord.setVisible(false);
        
        JButton button1 = new JButton("Again");
        button1.setFont(buttonFont);
        JButton button2 = new JButton("Menu");
        button2.setFont(buttonFont);
        
        button1.addActionListener(ae -> Main.toGame());
        button2.addActionListener(ae -> Main.switchTo("menu"));
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(score, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(newRecord, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(button1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(button2, gbc);
    }
    public void setScoreLabel(String str){
        scoreDisplay.setText(str);
    }

    public void setHighscoreNotification(boolean isHighScore) {
        newRecord.setVisible(isHighScore);
    }
}
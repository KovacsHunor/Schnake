package gui.views;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import logic.util.Utils;
import main.Main;

public class DeathScreen extends JPanel {
    private final JLabel scoreDisplay = new JLabel("0");
    private final JLabel newRecord = new JLabel("New Personal Record!");

    public DeathScreen() {
        

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        setBackground(Utils.BACKGROUND_COLOR);

        JPanel score = new JPanel();
        JLabel scoreText = new JLabel("Score:");
        scoreText.setFont(new Font("Serif", Font.PLAIN, 32));
        scoreText.setForeground(Utils.FOREGROUND_COLOR);
        score.setBackground(Utils.BACKGROUND_COLOR);
        
        scoreDisplay.setFont(new Font("Serif", Font.BOLD, 64));
        scoreDisplay.setForeground(Utils.FOREGROUND_COLOR);
        
        score.add(scoreText);
        score.add(scoreDisplay);

        
        newRecord.setFont(new Font("Comic Sans MS", Font.BOLD, 32));
        newRecord.setVisible(false);
        newRecord.setForeground(Utils.FOREGROUND_COLOR);
        
        JButton againButton = new JButton("Again");
        againButton.setFont(Utils.BUTTON_FONT);
        againButton.setBackground(Utils.BACKGROUND_COLOR);
        againButton.setForeground(Utils.FOREGROUND_COLOR);
        JButton menuButton = new JButton("Menu");
        menuButton.setFont(Utils.BUTTON_FONT);
        menuButton.setBackground(Utils.BACKGROUND_COLOR);
        menuButton.setForeground(Utils.FOREGROUND_COLOR);
        
        againButton.addActionListener(ae -> Main.toGame());
        menuButton.addActionListener(ae -> Main.switchTo("menu"));
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(score, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(newRecord, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(againButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(menuButton, gbc);
    }
    public void setScoreLabel(String str){
        scoreDisplay.setText(str);
    }

    public void setHighscoreNotification(boolean isHighScore) {
        newRecord.setVisible(isHighScore);
    }
}
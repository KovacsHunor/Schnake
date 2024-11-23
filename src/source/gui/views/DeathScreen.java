package source.gui.views;

import source.gui.main.Main;
import source.logic.util.Utils;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DeathScreen extends JPanel {
    private final JLabel scoreDisplay = new JLabel("0");
    private final JLabel newRecord = new JLabel("New Personal Record!");

    /**
     * The constructor
     */
    public DeathScreen() {
        

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        setBackground(Utils.BACKGROUND_COLOR);

        JPanel score = new JPanel();
        JLabel scoreText = new JLabel("Score:");
        scoreText.setFont(new Font("Serif", Font.PLAIN, 32));
        
        scoreDisplay.setFont(new Font("Serif", Font.BOLD, 64));
        
        score.add(scoreText);
        score.add(scoreDisplay);

        
        newRecord.setFont(new Font("Comic Sans MS", Font.BOLD, 32));
        newRecord.setVisible(false);
        
        JButton againButton = new JButton("Again");
        JButton menuButton = new JButton("Menu");
        
        againButton.addActionListener(ae -> Main.toGame());
        menuButton.addActionListener(ae -> Main.toMenu());
        
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
    
    /**
     * Sets the score label
     * @param score The score to be displayed
     */
    public void setScoreLabel(int score){
        scoreDisplay.setText(Integer.toString(score));
    }

    /**
     * Displayes a notification, if the score of the player is a personal highscore
     * @param isHighScore   Determines whether the player got a highscore or not
     */
    public void setHighscoreNotification(boolean isHighScore) {
        newRecord.setVisible(isHighScore);
    }
}
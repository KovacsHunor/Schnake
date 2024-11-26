package source.gui.main;

import source.gui.views.DeathScreen;
import source.gui.views.Game;
import source.gui.views.Leaderboard;
import source.gui.views.Menu;
import source.logic.leaderboard.HighscoreIO;
import source.logic.leaderboard.User;
import source.logic.util.Utils;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.*;

/**
 * The main class
 */
public class Main {

    
    private static JPanel deck;
    private static User user = new User();
    private static Menu menu;
    private static Game game;
    private static Leaderboard leaderboard;
    private static DeathScreen deathScreen;

    /**
     * Carries out the initial settings
     */
    public static void init() {
        JFrame frame;
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ignored) {
        }

        deck = new JPanel(new CardLayout());
        game = new Game();
        menu = new Menu();
        leaderboard = new Leaderboard();
        deathScreen = new DeathScreen();

        deck.add(menu, "menu");
        deck.add(game, "game");
        deck.add(leaderboard, "leaderboard");
        deck.add(deathScreen, "deathScreen");

        frame = new JFrame("Schnake");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.add(deck);
        guiSettings(deck);
        frame.pack();
        frame.setLocationRelativeTo(deck);
        frame.setVisible(true);

        setUser(user.getUsername());
    }

    /**
     * Recursively sets the visuals for all components of the determined types
     * @param c
     */
    private static void guiSettings(Component c) {
        Font defaulFont = new Font("Serif", Font.PLAIN, 32);
        Color bg = Utils.BACKGROUND_COLOR;
        Color fg = new Color(0xB0B0B0);

        c.setForeground(fg);
        c.setBackground(bg);
        switch (c) {
            case JPanel p -> {
                p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                
                //For debug
                //p.setBorder(BorderFactory.createLineBorder(Color.RED));

                for (Component c2 : p.getComponents()) {
                    guiSettings(c2);
                }
            }
            case JButton b -> {
                b.setFont(defaulFont);
            }
            case JLabel l -> {
                if (l.getFont().getSize() < 32) {
                    l.setFont(defaulFont);
                }
                l.setHorizontalAlignment(SwingConstants.CENTER);
            }
            case JTextField f -> {
                f.setFont(defaulFont);
                f.setHorizontalAlignment(SwingConstants.CENTER);
            }
            case JComboBox box -> {
                box.setPreferredSize(new Dimension(80, 50));
                box.setFont(defaulFont);
            }
            default -> {
            }
        }
    }


    /**
     * Makes the cardlayout show the card with the given name
     * @param name  The name of the card
     */
    public static void switchTo(String name) {
        CardLayout cl = (CardLayout) (deck.getLayout());
        cl.show(deck, name);
    }

    /**
     * @return  The current user
     */
    public static User getUser() {
        return user;
    }

    /**
     * the main function
     * @param args  The arguments of the program (not in use)
     */
    public static void main(String[] args) {
        init();
    }

    /**
     * Switches to the death screen, and performes certain tasks 
     * @param point The score of the player at the time of their death
     */
    public static void toDeathScreen(int point) {
        game.stopTimer();
        if(!leaderboard.getData().getUsers().contains(user)){
            leaderboard.getData().addUser(user);
        }
        boolean isHighScore = point > user.getHighscore();
        if (isHighScore) {
            user.setHighscore(point);
            menu.setPointLabel(point);
            leaderboard.sort();
            HighscoreIO.saveHighscores(leaderboard.getData().getUsers());
        }
        deathScreen.setScoreLabel(point);
        deathScreen.setHighscoreNotification(isHighScore);
        switchTo("deathScreen");
    }

    /**
     * Switches to the game view
     */
    public static void toGame() {
        game.start();
        switchTo("game");
    }

    /**
     * Switches to the leaderboard view
     */
    public static void toLeaderBoard() {
        switchTo("leaderboard");
    }

    /**
     * switches to the menu view
     */
    public static void toMenu() {
        switchTo("menu");
    }

    /**
     * Sets the user based on the given username. If the user does not exist it creates a new one.
     * @param text  The name of the user
     */
    public static void setUser(String text) {
        List<User> list = leaderboard.getData().getUsers();
        for (User u : list) {
            if (u.getUsername().equals(text)) {
                user = u;
                menu.setPointLabel(user.getHighscore());
                return;
            }
        } 
        user = new User(text);
        menu.setPointLabel(user.getHighscore());
    }

    /**
     * returns the game view
     * @return
     */
    public static Game getGame() {
        return game;
    }

}

package gui.main;

import gui.views.DeathScreen;
import gui.views.Game;
import gui.views.Leaderboard;
import gui.views.Menu;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.*;
import logic.leaderboard.HighscoreIO;
import logic.leaderboard.User;
import logic.util.Utils;

public class Main {

    private static JFrame frame;
    private static JPanel deck;
    private static User user = new User();

    private static Menu menu;
    private static Game game;
    private static Leaderboard leaderboard;
    private static DeathScreen deathScreen;

    public static void init() {
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

    public static void switchTo(String name) {
        CardLayout cl = (CardLayout) (deck.getLayout());
        cl.show(deck, name);
    }

    public static User getUser() {
        return user;
    }

    public static void main(String... args) {
        init();
    }

    public static void toDeathScreen(int point) {
        game.stopTimer();
        boolean isHighScore = point > user.getHighscore();
        if (isHighScore) {
            user.setHighscore(point);
            menu.setPointLabel(point);
            leaderboard.sort();
            HighscoreIO.saveHighscores(leaderboard.getData().getList());
        }
        deathScreen.setScoreLabel(point);
        deathScreen.setHighscoreNotification(isHighScore);
        switchTo("deathScreen");
    }

    public static void toGame() {
        game.start();
        switchTo("game");
    }

    public static void toLeaderBoard() {
        switchTo("leaderboard");
    }

    public static void toMenu() {
        switchTo("menu");
    }

    public static void setUser(String text) {
        List<User> list = leaderboard.getData().getList();
        for (User u : list) {
            if (u.getUsername().equals(text)) {
                user = u;
                menu.setPointLabel(user.getHighscore());
                return;
            }
        } 
        user = new User(text);
        list.add(user);
        leaderboard.getData().fireTableRowsInserted(list.size() - 1, list.size() - 1);
        menu.setPointLabel(user.getHighscore());
    }

    public static Game getGame() {
        return game;
    }

}

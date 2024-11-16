package main;

import gui.views.DeathScreen;
import gui.views.Game;
import gui.views.Leaderboard;
import gui.views.Menu;
import java.awt.CardLayout;
import java.util.List;
import javax.swing.*;
import logic.leaderboard.HighscoreIO;
import logic.leaderboard.User;

public class Main {

    private static JFrame frame;
    private static JPanel main;
    private static User user = new User();

    private static Menu menu;
    private static Game game;
    private static Leaderboard leaderboard;
    private static DeathScreen deathScreen;

    public static void init() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ignored) {
        }

        main = new JPanel(new CardLayout());

        menu = new Menu();
        game = new Game();
        leaderboard = new Leaderboard();
        deathScreen = new DeathScreen();

        main.add(menu, "menu");
        main.add(game, "game");
        main.add(leaderboard, "leaderboard");
        main.add(deathScreen, "deathScreen");

        frame = new JFrame("Schnake");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.add(main);
        frame.pack();
        frame.setLocationRelativeTo(main);
        frame.setVisible(true);

        setUser(user.getUsername());
    }

    public static void switchTo(String name) {
        CardLayout cl = (CardLayout) (main.getLayout());
        cl.show(main, name);
    }

    public static User getUser() {
        return user;
    }

    public static void main(String... args) {
        init();
    }

    public static void toDeathScreen(int point) {
        boolean isHighScore = point > user.getHighscore();
        if (isHighScore) {
            user.setHighscore(point);
            leaderboard.sort();
            HighscoreIO.saveHighscores(leaderboard.getData().getList());
        }
        deathScreen.setHighscoreNotification(isHighScore);
        deathScreen.setScoreLabel("" + point);
        switchTo("deathScreen");
    }

    public static void toGame() {
        game.reset();
        game.start();
        switchTo("game");
    }

    public static void toLeaderBoard() {
        switchTo("leaderboard");
    }

    public static void setUser(String text) {
        List<User> list = leaderboard.getData().getList();
        for (User u : list) {
            if (u.getUsername().equals(text)) {
                user = u;
                return;
            }
        }
        user = new User(text);
        list.add(user);
        leaderboard.getData().fireTableRowsInserted(list.size() - 1, list.size() - 1);
    }
}

package main;

import gui.views.DeathScreen;
import gui.views.Game;
import gui.views.Leaderboard;
import gui.views.Menu;
import java.awt.CardLayout;
import javax.swing.*;

public class Main {

    private static JFrame frame;
    private static JPanel main;
    private static String username = "sample_username";

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
    }

    public static void switchTo(String name) {
        CardLayout cl = (CardLayout) (main.getLayout());
        cl.show(main, name);
    }

    public static void setUsername(String name) {
        username = name;
    }

    public static String getUsername() {
        return username;
    }

    public static void main(String... args) {
        init();
    }
    
    public static void toDeathScreen(int point){
        deathScreen.setScoreLabel("" + point);
        switchTo("deathScreen");
    }

    public static void switchToGame() {
        game.reset();
        game.start();
        switchTo("game");
    }
}

package main;

import gui.game.Game;
import gui.leaderboard.Leaderboard;
import gui.menu.Menu;
import java.awt.CardLayout;
import javax.swing.*;

public class Main {
    private static JFrame frame;
    private static JPanel main;
    private static String username = "sample_username";

    public static void init() {
        main = new JPanel(new CardLayout());

        Menu menu = new Menu();
        Game game = new Game();
        Leaderboard leaderboard = new Leaderboard();

        main.add(menu, "menu");
        main.add(game, "game");
        main.add(leaderboard, "leaderboard");

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
}
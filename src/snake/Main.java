package snake;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.HierarchyEvent;

import javax.swing.*;
import leaderboard.Leaderboard;
import menu.Menu;

public class Main {
    public static JPanel window;
    private static Menu menu;
    private static Leaderboard leaderboard;
    private static Field game;

    private static void displayGUI() {
        JFrame frame = new JFrame("Schnake");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        window = new JPanel();
        window.setLayout(new CardLayout());

        menu = new Menu();
        leaderboard = new Leaderboard();
        game = new Field();

        window.addKeyListener(game);

        window.add(menu, "Menu");
        window.add(leaderboard, "Leaderboard");
        window.add(game, "Game");

        frame.addKeyListener(game);
        frame.getContentPane().add(window, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static Field getGame(){
        return game;
    }

    public static void main(String... args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                displayGUI();
            }
        });
    }
}
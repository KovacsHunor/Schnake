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
    private static User user;

    private static Menu menu;
    private static Game game;
    private static Leaderboard leaderboard;
    private static DeathScreen deathScreen;
    private static List<User> highscoreList;

    public static void init() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ignored) {
        }
        
        user = new User();
        highscoreList = HighscoreIO.readHighscores();
        setUser(user.getUsername());

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

    public static User getUser() {
        return user;
    }

    public static void main(String... args) {
        init();
    }
    
    public static void toDeathScreen(int point){
        boolean isHighScore = point > user.getHighscore();
        if (isHighScore) {
            user.setHighscore(point);
            HighscoreIO.saveHighscores(highscoreList);
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
        for (User u : highscoreList) {
            if(u.getUsername().equals(text)){
                user = u;
                return;
            }
        }
        user = new User(text);
        highscoreList.add(user);
    }

    public static List<User> getHighscoreList(){
        return highscoreList;
    }
}

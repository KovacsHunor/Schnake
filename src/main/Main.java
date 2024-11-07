package main;


import gui.menu.Menu;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Main {
    private static JFrame frame;
    private static String username = "sample_username";

    public static void init(JPanel panel) {
        if(frame != null){
            frame.dispose();
        }
        frame = new JFrame("Schnake");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try{
            frame.addKeyListener((KeyListener) panel);
        }catch(Exception e){
            
        }
        
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(panel);
        frame.setVisible(true);
    }

    public static void setUsername(String name){
        username = name;
    }

    public static String getUsername(){
        return username;
    }

    public static void main(String... args) {
        init(new Menu());
    }
}
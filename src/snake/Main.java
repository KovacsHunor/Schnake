package snake;

import javax.swing.*;

class Main {

    private static void initWindow() {
        JFrame window = new JFrame("Schnake");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Field field = new Field();
        window.add(field);
        window.addKeyListener(field);
        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::initWindow);
    }
}
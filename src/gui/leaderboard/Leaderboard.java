package gui.leaderboard;

import gui.menu.Menu;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;
import main.Main;

public class Leaderboard extends JPanel {
    public Leaderboard() {
        setName("Leaderboard");
        setPreferredSize(new Dimension(400, 400));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton button1 = new JButton("Menu");

        button1.addActionListener(ae -> Main.init(new Menu()));

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(button1, gbc);
    }
}

package leaderboard;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import menu.Menu;
import snake.Main;

public class Leaderboard extends JPanel{
    public Leaderboard(){
        setName("Leaderboard");
        setPreferredSize(new Dimension(400, 400));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton button1 = new JButton("Menu");

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.init(new Menu());
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(button1, gbc);
    }
}

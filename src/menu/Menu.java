package menu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import snake.Main;

public class Menu extends JPanel {
    public Menu() {
        setName("Menu");
        setPreferredSize(new Dimension(400, 400));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton button1 = new JButton("Play");
        JButton button2 = new JButton("Leaderboard");
        JButton button3 = new JButton("Exit");

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.init(new snake.Field());
            }
        });
        button2.addActionListener(ae -> {
            Main.init(new leaderboard.Leaderboard());
        });
        button3.addActionListener(ae -> System.exit(0));

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(button1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(button2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(button3, gbc);
        setVisible(true);
    }
}

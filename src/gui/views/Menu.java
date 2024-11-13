package gui.views;

import java.awt.*;
import javax.swing.*;
import main.Main;

public class Menu extends JPanel{
    public Menu() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JTextField input = new JTextField(20);
        input.setFont(new Font("Serif", Font.PLAIN, 18));

        JPanel upper = new JPanel();

        JLabel usernameLabel = new JLabel(Main.getUsername());
        usernameLabel.setFont(new Font("Serif", Font.PLAIN, 18));

        JButton okButton = new JButton("Set username");
        okButton.setFont(new Font("Serif", Font.PLAIN, 18));
        okButton.addActionListener(ae -> {
            String text = input.getText();
            Main.setUsername(text);
            if(!text.equals("")){
                usernameLabel.setText(text);
            }
        });

        upper.add(input);
        upper.add(okButton);
        upper.add(usernameLabel);

        
        JButton button1 = new JButton("Play");
        button1.setFont(new Font("Serif", Font.PLAIN, 18));
        JButton button2 = new JButton("Leaderboard");
        button2.setFont(new Font("Serif", Font.PLAIN, 18));
        JButton button3 = new JButton("Exit");
        button3.setFont(new Font("Serif", Font.PLAIN, 18));
        
        button1.addActionListener(ae -> Main.switchToGame());
        button2.addActionListener(ae -> Main.switchTo("leaderboard"));
        button3.addActionListener(ae -> System.exit(0));
        
        gbc.gridx = 0;
        gbc.gridy = 0;

        add(upper, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;

        add(button1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(button2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(button3, gbc);
        //setVisible(true);
    }
}

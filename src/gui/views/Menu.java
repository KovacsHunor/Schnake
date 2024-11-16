package gui.views;

import java.awt.*;
import javax.swing.*;
import static logic.util.Utils.BUTTON_FONT;
import main.Main;

public class Menu extends JPanel{
    public Menu() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JTextField input = new JTextField(20);
        input.setFont(BUTTON_FONT);

        JPanel upper = new JPanel();

        JLabel usernameLabel = new JLabel(Main.getUser().getUsername());
        usernameLabel.setFont(BUTTON_FONT);

        JButton okButton = new JButton("Set username");
        okButton.setFont(BUTTON_FONT);
        okButton.addActionListener((var ae) -> {
            String text = input.getText();
            if(!text.equals("") && !text.contains(",")){
                Main.setUser(text);
                usernameLabel.setText(text);
            }
        });

        upper.add(input);
        upper.add(okButton);
        upper.add(usernameLabel);

        
        JButton button1 = new JButton("Play");
        button1.setFont(BUTTON_FONT);
        JButton button2 = new JButton("Leaderboard");
        button2.setFont(BUTTON_FONT);
        JButton button3 = new JButton("Exit");
        button3.setFont(BUTTON_FONT);
        
        button1.addActionListener(ae -> Main.toGame());
        button2.addActionListener(ae -> Main.toLeaderBoard());
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
    }
}

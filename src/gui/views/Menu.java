package gui.views;

import gui.main.Main;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import javax.swing.*;

public final class Menu extends JPanel {

    private final JLabel usernameLabel;
    private final JTextField inputField;
    private JLabel pointLabel;

    public void updatePointLabel() {
        pointLabel = new JLabel("" + Main.getUser().getHighscore());
    }

    private void setUsername() {
        String text = inputField.getText();
        if (!text.equals("") && !text.contains(",")) {
            Main.setUser(text);
            usernameLabel.setText(text);
        }
    }

    private Action setUsernameAction() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setUsername();
            }
        };
    }

    public Menu() {
        setLayout(new BorderLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        inputField = new JTextField(20);
        inputField.setFont(new Font("Serif", Font.PLAIN, 32));
        inputField.setMaximumSize(new Dimension(inputField.getMaximumSize().width, 80));

        usernameLabel = new JLabel(Main.getUser().getUsername());
        usernameLabel.setFont(new Font("Serif", Font.PLAIN, 32));

        updatePointLabel();
        pointLabel.setFont(new Font("Serif", Font.PLAIN, 64));

        JButton playButton = new JButton("Play");
        playButton.addActionListener(ae -> Main.toGame());

        JButton leaderboardButton = new JButton("Leaderboard");
        leaderboardButton.addActionListener(ae -> Main.toLeaderBoard());

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(ae -> System.exit(0));

        Integer[] sizes = new Integer[20];
        for (int i = 0; i < sizes.length; i++) {
            sizes[i] = i + 1;
        }
        JLabel fieldLabel = new JLabel("Field size: ");
        JComboBox<Integer> fieldBox = new JComboBox<>(sizes);
        fieldBox.addItemListener((ItemEvent ie) -> {
            //TODO
        });

        fieldBox.setPreferredSize(new Dimension(80, 50));

        JLabel boardLabel = new JLabel("Board size: ");
        JComboBox<Integer> boardBox = new JComboBox<>(sizes);
        boardBox.addItemListener((ItemEvent ie) -> {
            //TODO
        });

        boardBox.setPreferredSize(new Dimension(80, 50));

        JPanel upper = new JPanel(new BorderLayout());
        JPanel lower = new JPanel(new GridBagLayout());

        JPanel info = new JPanel();
        JPanel input = new JPanel();
        JPanel navigate = new JPanel(new GridBagLayout());
        JPanel settings = new JPanel(new GridBagLayout());

        info.setLayout(new GridLayout(2, 1, 0, 0));
        info.add(usernameLabel);
        info.add(pointLabel);

        input.setLayout(new BoxLayout(input, BoxLayout.PAGE_AXIS));
        input.add(Box.createVerticalGlue());
        input.add(inputField);
        input.add(Box.createVerticalGlue());

        gbc.gridx = 0;
        gbc.gridy = 0;
        navigate.add(playButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        navigate.add(leaderboardButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        navigate.add(exitButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        settings.add(fieldLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        settings.add(boardLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        settings.add(fieldBox, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        settings.add(boardBox, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        lower.add(navigate, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        lower.add(settings, gbc);

        upper.add(info, BorderLayout.EAST);
        upper.add(input, BorderLayout.WEST);

        add(upper, BorderLayout.NORTH);
        add(lower, BorderLayout.CENTER);
        /*
        
         */

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), "enter");
        getActionMap().put("enter", setUsernameAction());
    }
}

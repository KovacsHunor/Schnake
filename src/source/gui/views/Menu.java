package source.gui.views;

import source.gui.game.FieldGui;
import source.gui.main.Main;
import source.logic.field.Field;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * The menu view
 */
public final class Menu extends JPanel {

    private final JLabel usernameLabel;
    private final JTextField inputField;
    private final JLabel pointLabel = new JLabel();

    /**
     *      Sets the point display to the given point
     * @param point The point to be displayed
     */
    public void setPointLabel(int point) {
        pointLabel.setText("" + point);
    }

    /**
     * Sets the current user based on the content of the text field
     */
    private void setUsername() {
        String text = inputField.getText().trim();
        if (!text.equals("") && !text.contains(",")) {
            Main.setUser(text);
            usernameLabel.setText(text);
        }
    }

    /**
     * Converts the setUsername function to an Action
     * @return  The Action
     */
    private Action setUsernameAction() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setUsername();
            }
        };
    }

    class JTextFieldLimit extends PlainDocument {
        private int limit;

        JTextFieldLimit(int limit) {
            super();
            this.limit = limit;
        }

        @Override
        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null)
                return;
            if ((getLength() + str.length()) <= limit) {
                super.insertString(offset, str, attr);
            }
        }
    }

    /**
     * The constructor
     */
    public Menu() {
        setLayout(new BorderLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        inputField = new JTextField(20);
        inputField.setDocument(new JTextFieldLimit(20));
        JLabel inputFieldLabel = new JLabel("Username:");
        JLabel fieldLabel = new JLabel("Field size: ");

        usernameLabel = new JLabel(Main.getUser().getUsername());
        pointLabel.setFont(new Font("Serif", Font.PLAIN, 64));

        JButton playButton = new JButton("Play");
        playButton.addActionListener(ae -> Main.toGame());

        JButton leaderboardButton = new JButton("Leaderboard");
        leaderboardButton.addActionListener(ae -> Main.toLeaderBoard());

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(ae -> System.exit(0));

        Integer[] fieldSizes = new Integer[8];
        for (int i = 0; i < fieldSizes.length; i++) {
            fieldSizes[i] = i + 1;
        }

        Integer[] boardSizes = new Integer[8];
        for (int i = 0; i < boardSizes.length; i++) {
            boardSizes[i] = i + 2;
        }
        JComboBox<Integer> fieldBox = new JComboBox<>(fieldSizes);
        fieldBox.addItemListener((ItemEvent ie) -> {
            Game game = Main.getGame();
            FieldGui gui = game.getFieldGui();
            Field.newInstance((Integer) ie.getItem(), Field.getInstance().getTileNum());
            gui.init();
        });

        JLabel boardLabel = new JLabel("Board size: ");
        JComboBox<Integer> boardBox = new JComboBox<>(boardSizes);
        boardBox.addItemListener((ItemEvent ie) -> {
            Game game = Main.getGame();
            FieldGui gui = game.getFieldGui();
            Field.newInstance(Field.getInstance().getBoardNum(), (Integer) ie.getItem());
            gui.init();
        });

        fieldBox.setSelectedItem(2);
        boardBox.setSelectedItem(6);

        JPanel upper = new JPanel(new BorderLayout());
        JPanel lower = new JPanel(new GridBagLayout());

        JPanel info = new JPanel();
        JPanel input = new JPanel();
        JPanel navigate = new JPanel(new GridBagLayout());
        JPanel settings = new JPanel(new GridBagLayout());

        info.setLayout(new GridLayout(2, 1, 0, 0));
        info.add(usernameLabel);
        info.add(pointLabel);

        input.setLayout(new GridLayout(3, 1));
        input.add(inputFieldLabel);
        input.add(inputField);

        gbc.gridx = 0;
        gbc.gridy = 0;
        navigate.add(playButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        navigate.add(leaderboardButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        navigate.add(exitButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        settings.add(fieldLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        settings.add(boardLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        settings.add(fieldBox, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
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

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), "enter");
        getActionMap().put("enter", setUsernameAction());
    }
}

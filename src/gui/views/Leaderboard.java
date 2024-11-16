package gui.views;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.TableRowSorter;
import logic.leaderboard.HighscoreData;
import logic.leaderboard.HighscoreIO;
import logic.util.Utils;
import static logic.util.Utils.BUTTON_FONT;
import main.Main;

public final class Leaderboard extends JPanel {

    private final HighscoreData data = new HighscoreData(HighscoreIO.readHighscores());
    private final JTable table = new JTable(data);
    private final TableRowSorter<HighscoreData> sorter;

    public HighscoreData getData() {
        return data;
    }

    public void sort(){
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(1, SortOrder.DESCENDING));
        sorter.allRowsChanged();
        sorter.setSortKeys(sortKeys);
    }

    public Leaderboard() {
        sorter = new TableRowSorter<>(data);
        setLayout(new GridBagLayout());
        
        table.setRowSorter(sorter);
        table.setFillsViewportHeight(true);

        table.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        table.getTableHeader().setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        table.setRowHeight(32);
        table.setCellSelectionEnabled(false);
        table.setFocusable(false);
        

        JScrollPane scrollPane = new JScrollPane(table);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JPanel left = new JPanel();
        JPanel right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));

        JButton menuButton = new JButton("Menu");
        menuButton.setFont(BUTTON_FONT);
        menuButton.addActionListener(ae -> Main.switchTo("menu"));

        left.add(menuButton);

        right.add(scrollPane);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(left, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(right, gbc);

        for (Component c : left.getComponents()) {
            c.setForeground(Utils.FOREGROUND_COLOR);
            c.setBackground(Utils.BACKGROUND_COLOR);
        }
        for (Component c : right.getComponents()) {
            c.setForeground(Utils.FOREGROUND_COLOR);
            c.setBackground(Utils.BACKGROUND_COLOR);
        }
        for (Component c : getComponents()) {
            setForeground(Utils.FOREGROUND_COLOR);
            setBackground(Utils.BACKGROUND_COLOR);
            c.setForeground(Utils.FOREGROUND_COLOR);
            c.setBackground(Utils.BACKGROUND_COLOR);
        }

        sort();
    }
}

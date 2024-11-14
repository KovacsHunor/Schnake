package gui.views;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.TableRowSorter;
import logic.leaderboard.HighscoreData;
import static logic.util.Util.buttonFont;
import main.Main;

public class Leaderboard extends JPanel {

    HighscoreData data;
    JTable table;

    public Leaderboard() {
        setLayout(new GridBagLayout());
        data = new HighscoreData(Main.getHighscoreList());
        table = new JTable(data);
        TableRowSorter<HighscoreData> sorter = new TableRowSorter<>(data);
        table.setRowSorter(sorter);
        table.setFillsViewportHeight(true);

        table.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        table.getTableHeader().setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        table.setRowHeight(32);
        table.setCellSelectionEnabled(false);
        table.setFocusable(false);

        List<RowSorter.SortKey> sortKeys
                = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(1, SortOrder.DESCENDING));
        sorter.setSortKeys(sortKeys);

        JScrollPane scrollPane = new JScrollPane(table);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JPanel left = new JPanel();
        JPanel right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));

        JButton menuButton = new JButton("Menu");
        menuButton.setFont(buttonFont);
        menuButton.addActionListener(ae -> Main.switchTo("menu"));

        left.add(menuButton);

        right.add(scrollPane);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(left, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(right, gbc);

        setVisible(true);
    }
}

package source.gui.views;

import source.gui.main.Main;
import source.logic.leaderboard.HighscoreData;
import source.logic.leaderboard.HighscoreIO;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.TableRowSorter;

/**
 * The leaderboard view
 */
public final class Leaderboard extends JPanel {

    private final HighscoreData data = new HighscoreData(HighscoreIO.readHighscores());
    private final JTable table = new JTable(data);
    private final TableRowSorter<HighscoreData> sorter;

    /**
     * returns the data of the users
     * @return  the data
     */
    public HighscoreData getData() {
        return data;
    }

    /**
     * sorts the rows in the table by highscore
     */
    public void sort(){
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(1, SortOrder.DESCENDING));
        sorter.allRowsChanged();
        sorter.setSortKeys(sortKeys);
    }

    /**
     * The constructor
     */
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
        menuButton.addActionListener(ae -> Main.toMenu());

        left.add(menuButton);

        right.add(scrollPane);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(left, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(right, gbc);

        sort();
    }
}

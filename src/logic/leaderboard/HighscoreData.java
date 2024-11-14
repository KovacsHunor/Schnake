package logic.leaderboard;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class HighscoreData extends AbstractTableModel {

    private List<User> users = new ArrayList<>();

    public HighscoreData(List<User> users) {
        this.users = users;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public String getColumnName(int column) {
        return switch (column) {
            case 0 ->
                "Username";
            case 1 ->
                "Record";
            default ->
                throw new IllegalArgumentException("Unexpected value: " + column);
        };
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = users.get(rowIndex);
        return switch (columnIndex) {
            case 0 ->
                user.getUsername();
            case 1 ->
                user.getHighscore();
            default ->
                throw new IllegalArgumentException("Unexpected value: " + columnIndex);
        };
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}

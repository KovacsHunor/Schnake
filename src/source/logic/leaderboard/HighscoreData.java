package source.logic.leaderboard;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * data of the highscores for the JTable
 */
public class HighscoreData extends AbstractTableModel {

    private List<User> users = new ArrayList<>();

    /**
     * the constructors
     * @param users a List of the users
     */
    public HighscoreData(List<User> users) {
        this.users = users;
    }

    /**
     * returns the list of the users
     * @return  the list of the users
     */
    public List<User> getUsers(){
        return users;
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
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0:
                return String.class;
            default:
                return Integer.class;

        }
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

    /**
     * changes the list of users to the given list
     * @param users the new list of the users
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     * adds a new user to the list
     * @param user  the new user
     */
    public void addUser(User user){
        users.add(user);
    }

}

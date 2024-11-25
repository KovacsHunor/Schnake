package source.logic.leaderboard;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * handles the saving and reading of user data
 */
public class HighscoreIO {
    /**
     * private constructor for hiding the public one
     */
    private HighscoreIO(){
        
    }

    /**
     * reads the highscores from their csc file
     * @return  the list of the users
     */
    public static List<User> readHighscores() {
        List<User> highscoreList = new ArrayList<>();
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("resources/highscores.csv"));
            String line = reader.readLine();

            while (line != null) {
                String[] data = line.split(",");
                User user = new User(data[0], Integer.parseInt(data[1]));
                highscoreList.add(user);
                line = reader.readLine();
            }

            reader.close();
        } catch (Exception e) {
            System.err.println("Could not properely load the highscore data");
        }

        return highscoreList;
    }

    /**
     * saves the highscores to their csv file
     * @param highscoreList
     */
    public static void saveHighscores(List<User> highscoreList) {
        try (FileWriter myWriter = new FileWriter("resources/highscores.csv")) {
            for (User user : highscoreList) {
                myWriter.write(user.getUsername() + "," + user.getHighscore() + "\n");
            }
        }catch (Exception e) {
            System.err.println("Could not properely save the highscore data");
        }

    }
}

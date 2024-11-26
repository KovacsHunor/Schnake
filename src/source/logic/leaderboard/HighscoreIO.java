package source.logic.leaderboard;

import java.io.BufferedReader;
import java.io.File;
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
    private HighscoreIO() {

    }

    /**
     * reads the highscores from their csc file
     * @return  the list of the users
     */
    public static List<User> readHighscores() {
        List<User> highscoreList = new ArrayList<>();
        BufferedReader reader;

        try {
            File file = new File("resources/highscores.csv");
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();

            while (line != null) {
                String[] data = line.split(",");
                User user = new User(data[0], Integer.parseInt(data[1]));
                highscoreList.add(user);
                line = reader.readLine();
            }

            reader.close();
        } catch (Exception e) {
            // the file didn't exist, but no need to do anything with this fact
        }

        return highscoreList;
    }

    /**
     * saves the highscores to their csv file
     * @param highscoreList
     */
    public static void saveHighscores(List<User> highscoreList) {
        try{
            File file = new File("resources/highscores.csv");
            file.getParentFile().mkdirs();
            FileWriter myWriter = new FileWriter(file);
            for (User user : highscoreList) {
                myWriter.write(user.getUsername() + "," + user.getHighscore() + "\n");
            }
            myWriter.close();
        } catch (Exception e) {
            System.err.println("Could not properely save the highscore data");
        }

    }
}

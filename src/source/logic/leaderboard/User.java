package source.logic.leaderboard;

/**
 * represents a user
 */
public class User {
    private String username = "anonymous";
    private int highscore = 0;
    
    /**
     * the constructor
     * @param username  the username of the user
     * @param highscore the highscore of the user
     */
    public User(String username, int highscore){
        this.username = username;
        this.highscore = highscore;
    }

    /**
     * parameterless public constructor
     */
    public User(){
    }

    /**
     * constructor
     * @param text  the username of the user
     */
    public User(String text) {
        this.username = text;
    }

    /**
     * returns the username of the user
     * @return  the username
     */
    public String getUsername(){
        return username;
    }

    /**
     * returns the highscore of the user
     * @return
     */
    public int getHighscore(){
        return highscore;
    }

    /**
     * sets the username to the given string
     * @param username  the new username
     */
    public void setUsername(String username){
        this.username = username;
    }

    /**
     * sets the highscore to the given value
     * @param highscore the new highscore
     */
    public void setHighscore(int highscore){
        this.highscore = highscore;
    }
}

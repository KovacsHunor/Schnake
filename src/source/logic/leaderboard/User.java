package source.logic.leaderboard;

public class User {
    private String username = "anonymous";
    private int highscore = 0;
    
    public User(String username, int highscore){
        this.username = username;
        this.highscore = highscore;
    }

    public User(){
    }

    public User(String text) {
        this.username = text;
    }

    public String getUsername(){
        return username;
    }
    public int getHighscore(){
        return highscore;
    }

    public void setUsername(String username){
        this.username = username;
    }
    public void setHighscore(int highscore){
        this.highscore = highscore;
    }
}

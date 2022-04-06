package tetris.highScores;

import java.time.LocalDateTime;

public class UserScore {
    private final String userName;
    private final int score;
    private final String dateTime;

    public UserScore(String userName, int score, String dateTime) {
        this.userName = userName;
        this.score = score;
        this.dateTime = dateTime;
    }

    public String getUserName() {
        return userName;
    }

    public int getScore() {
        return score;
    }

    public String getDateTime() {
        return dateTime;
    }
}

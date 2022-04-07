package tetris.highScores;

import java.time.LocalDateTime;

public class UserScore {
    private final String userName;
    private final int score;
    private final LocalDateTime dateTime;

    public UserScore(String userName, int score, LocalDateTime dateTime) {
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}

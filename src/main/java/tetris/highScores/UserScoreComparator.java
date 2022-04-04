package tetris.highScores;

import java.util.Comparator;

public class UserScoreComparator implements Comparator<UserScore> {
    @Override
    public int compare(UserScore o1, UserScore o2) {
        if (o1.getScore() == o2.getScore()){
            if (o1.getDateTime().equals(o2.getDateTime())) {
                return o1.getUserName().compareTo(o2.getUserName());
            }
            return o1.getDateTime().compareTo(o2.getDateTime());
        }
        return Integer.compare(o1.getScore(), o2.getScore());
    }
}

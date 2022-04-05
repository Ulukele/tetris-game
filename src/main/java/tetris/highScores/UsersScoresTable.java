package tetris.highScores;

import Exceptions.HighScoresException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import common.Model;
import common.Publisher;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class UsersScoresTable extends Publisher implements Model<List<UserScore>> {

    private final int maxScoresToShow;
    private final int maxScoresToStore;
    private final Set<UserScore> usersScores = new TreeSet<>(new UserScoreComparator());
    private final DumpWorker dumpWorker = new DumpWorker("high-scores-dump.json");
    private final Gson gson = new Gson();

    public UsersScoresTable(int maxScoresToShow, int maxScoresToStore) {
        this.maxScoresToShow = maxScoresToShow;
        this.maxScoresToStore = maxScoresToStore;
    }

    public void initFromFile() throws HighScoresException {
        try {
            String dumpData = dumpWorker.readDump();

            List<UserScore> usersScoresList;
            Type type = new TypeToken<List<UserScore>>() {}.getType();

            usersScoresList = gson.fromJson(dumpData, type);
            if (usersScoresList != null) {
                usersScores.clear();
                usersScores.addAll(usersScoresList.subList(0, maxScoresToShow));
            }
        } catch (IOException ioException) {
            throw new HighScoresException();
        }
    }

    public void addUserScore(UserScore userScore) throws HighScoresException {
        usersScores.add(userScore);
        dumpData();
        publishNotify();
    }

    public void dumpData() throws HighScoresException {
        List<UserScore> usersScoresList;
        usersScoresList = toUsersScoresList().subList(0, maxScoresToStore);

        String dataDump = gson.toJson(usersScoresList);

        try {
            dumpWorker.writeDump(dataDump);
        } catch (IOException ioException) {
            throw new HighScoresException();
        }
    }

    private List<UserScore> toUsersScoresList() {
        return new ArrayList<>(usersScores);
    }

    @Override
    public List<UserScore> getData() {
        return toUsersScoresList().subList(0, maxScoresToShow);
    }
}

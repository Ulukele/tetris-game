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

    private final int scoresToShow;
    private List<UserScore> usersScoresList;
    private final UserScoreComparator userScoreComparator = new UserScoreComparator();
    private final DumpWorker dumpWorker = new DumpWorker("high-scores-dump.json");
    private final Gson gson = new Gson();

    public UsersScoresTable(int scoresToShow) {
        this.scoresToShow = scoresToShow;
        this.usersScoresList = new ArrayList<>(scoresToShow);
    }

    public void initFromFile() throws HighScoresException {
        try {
            String dumpData = dumpWorker.readDump();
            Type type = new TypeToken<List<UserScore>>() {}.getType();
            List<UserScore> userScoresFromFile = gson.fromJson(dumpData, type);

            if (userScoresFromFile != null) usersScoresList = userScoresFromFile;
        } catch (IOException ioException) {
            throw new HighScoresException();
        }
        publishNotify();
    }

    public void addUserScore(UserScore userScore) throws HighScoresException {
        usersScoresList.add(userScore);
        dumpData();
        publishNotify();
    }

    public void dumpData() throws HighScoresException {
        String dataDump = gson.toJson(usersScoresList);

        try {
            dumpWorker.writeDump(dataDump);
        } catch (IOException ioException) {
            throw new HighScoresException();
        }
    }

    @Override
    public List<UserScore> getData() {
        usersScoresList.sort(userScoreComparator);
        if (usersScoresList.size() > scoresToShow) {
            usersScoresList = usersScoresList.subList(0, scoresToShow);
        }
        return usersScoresList;
    }
}

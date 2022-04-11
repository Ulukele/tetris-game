package tetris.highScores;

import exceptions.DumpWorkerException;
import exceptions.HighScoresException;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import common.Model;
import common.Publisher;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.*;

public class UserScoresTable extends Publisher implements Model<List<UserScore>> {

    private final int scoresToShow;
    private List<UserScore> usersScoresList;
    private final UserScoreComparator userScoreComparator = new UserScoreComparator();
    private final DumpWorker dumpWorker = new DumpWorker("high-scores-dump.json");
    private Gson gson;

    public UserScoresTable(int scoresToShow) {
        this.scoresToShow = scoresToShow;
        this.usersScoresList = new ArrayList<>(scoresToShow);
        configureGson();
    }

    private void configureGson() {
        JsonSerializer<LocalDateTime> serializer =
                (dateTime, type, jsonSerializationContext) ->
                        dateTime == null ? null : new JsonPrimitive(dateTime.toString());

        JsonDeserializer<LocalDateTime> deserializer =
                (json, typeOfT, context) ->
                        json == null ? null : LocalDateTime.parse(json.getAsString());

        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, serializer)
                .registerTypeAdapter(LocalDateTime.class, deserializer)
                .create();
    }

    public void initFromFile() throws HighScoresException {
        try {
            String dumpData = dumpWorker.readDump();
            Type type = new TypeToken<List<UserScore>>() {}.getType();
            List<UserScore> userScoresFromFile = gson.fromJson(dumpData, type);

            if (userScoresFromFile != null) usersScoresList = userScoresFromFile;
        } catch (DumpWorkerException dumpWorkerException) {
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
        } catch (DumpWorkerException dumpWorkerException) {
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

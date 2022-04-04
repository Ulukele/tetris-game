package tetris.highScores;

import java.io.*;

public class DumpWorker {
    private final String filename;

    public DumpWorker(String filename) {
        this.filename = filename;
    }

    public void writeDump(String dumpData) throws IOException {
        Writer writer = new FileWriter(filename);
        writer.write(dumpData);
    }

    public String readDump() throws IOException {
        Reader reader = new BufferedReader(new FileReader(filename));
        StringBuilder sb = new StringBuilder();
        int symbol;
        while (true) {
            symbol = reader.read();
            if (symbol == -1) { // End of stream case
                break;
            }
            char character = (char) symbol;
            sb.append(character);
        }
        return sb.toString();
    }
}

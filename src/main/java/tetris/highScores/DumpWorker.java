package tetris.highScores;

import java.io.*;
import java.net.URL;

public class DumpWorker {
    private final String filename;

    public DumpWorker(String filename) {
        this.filename = filename;
    }

    public void writeDump(String dumpData) throws IOException {
        URL resource = getClass().getClassLoader().getResource(filename);
        if (resource == null) throw new IOException();
        String absolutePath = resource.getFile();

        Writer writer = new FileWriter(absolutePath);
        writer.write(dumpData);
    }

    public String readDump() throws IOException {
        URL resource = getClass().getClassLoader().getResource(filename);
        if (resource == null) throw new IOException();
        String absolutePath = resource.getFile();

        Reader reader = new BufferedReader(new FileReader(absolutePath));
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

package tetris.highScores;

import exceptions.DumpWorkerException;

import java.io.*;
import java.net.URL;

public class DumpWorker {
    private final String filename;

    public DumpWorker(String filename) {
        this.filename = filename;
    }

    public void writeDump(String dumpData) throws DumpWorkerException {
        URL resource = getClass().getClassLoader().getResource(filename);
        if (resource == null) throw new DumpWorkerException();

        String absolutePath = resource.getFile();
        try (Writer writer = new FileWriter(absolutePath)) {
            writer.write(dumpData);
        } catch (IOException ioException) {
            throw new DumpWorkerException();
        }
    }

    public String readDump() throws DumpWorkerException {
        URL resource = getClass().getClassLoader().getResource(filename);
        if (resource == null) throw new DumpWorkerException();
        String absolutePath = resource.getFile();

        try (Reader reader = new BufferedReader(new FileReader(absolutePath))) {
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
        } catch (IOException ioException) {
            throw new DumpWorkerException();
        }
    }
}

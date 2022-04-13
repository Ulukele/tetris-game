package tetris.figures.factory;

import exceptions.FiguresFactoryConfigParsingException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FigureFactoryConfigParser {
    private final List<String> factoryFullNames = new ArrayList<>();

    public void parse(String filename) throws FiguresFactoryConfigParsingException {
        try {
            Document document = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(
                            getClass().getClassLoader().getResourceAsStream(filename)
                    );
            NodeList directories = document.getElementsByTagName("dir");
            for (int directoryIdx = 0; directoryIdx < directories.getLength(); ++directoryIdx) {
                Node directory = directories.item(directoryIdx);
                String directoryName = directory.getAttributes().getNamedItem("path").getNodeValue();
                NodeList fileNames = document.getElementsByTagName("file");
                for (int fileNameIdx = 0; fileNameIdx < fileNames.getLength(); fileNameIdx++) {
                    String fileName = fileNames.item(fileNameIdx).getAttributes().getNamedItem("name").getNodeValue();
                    factoryFullNames.add(directoryName + '.' + fileName);
                }
            }
        } catch (IOException | ParserConfigurationException | SAXException exception) {
            throw new FiguresFactoryConfigParsingException();
        }
    }

    public List<String> getFullNames() {
        return factoryFullNames;
    }
}

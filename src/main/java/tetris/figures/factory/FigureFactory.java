package tetris.figures.factory;

import exceptions.FactoryException;
import exceptions.FiguresFactoryConfigParsingException;
import tetris.common.BlockColor;
import tetris.figures.Figure;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FigureFactory {

    private final List<FigureCreator> figureCreators = new ArrayList<>();

    public Figure create(int figureId, BlockColor color) throws IndexOutOfBoundsException {
        if (figureId < 0 || figureId > getCreatorsCount()) {
            throw new IndexOutOfBoundsException();
        }
        return figureCreators.get(figureId).create(color);
    }

    public void configure() throws FiguresFactoryConfigParsingException, FactoryException {
        FigureFactoryConfigParser figureFactoryConfigParser = new FigureFactoryConfigParser();
        figureFactoryConfigParser.parse("figure-creators.xml");
        List<String> fullNames = figureFactoryConfigParser.getFullNames();

        try {
            for (final String fullName : fullNames) {
                Class<?> creatorClass = Class.forName(fullName);
                FigureCreator creator = (FigureCreator) creatorClass.getDeclaredConstructor().newInstance();
                figureCreators.add(creator);
            }
        } catch (ClassNotFoundException
                | NoSuchMethodException
                | InvocationTargetException
                | InstantiationException
                | IllegalAccessException
                exception) {
            throw new FactoryException();
        }

    }

    public int getCreatorsCount() {
        return figureCreators.size();
    }

    public Figure createRandom() {
        Random random = new Random();
        BlockColor[] colors = BlockColor.values();
        int figureId = random.nextInt(getCreatorsCount());
        BlockColor color = colors[ random.nextInt(colors.length) ];
        return create(figureId, color);
    }
}

package tetris.figures.factory;

import tetris.figures.Figure;

import java.util.Random;

public class StaticFigureFactory {
    private static final OFigureCreator oFigureCreator = new OFigureCreator();
    private static final IFigureCreator iFigureCreator = new IFigureCreator();
    private static final LFigureCreator lFigureCreator = new LFigureCreator();
    private static final JFigureCreator jFigureCreator = new JFigureCreator();
    private static final SFigureCreator sFigureCreator = new SFigureCreator();
    private static final ZFigureCreator zFigureCreator = new ZFigureCreator();

    public static Figure create(int figureId) throws IndexOutOfBoundsException {
        if (figureId < 0 || figureId > 6) {
            throw new IndexOutOfBoundsException();
        }
        return switch (figureId) {
            case (0) -> oFigureCreator.create();
            case (1) -> iFigureCreator.create();
            case (3) -> lFigureCreator.create();
            case (4) -> jFigureCreator.create();
            case (5) -> sFigureCreator.create();
            case (6) -> zFigureCreator.create();
            default -> null;
        };
    }

    public static int getCreatorsCount() {
        return 7;
    }

    public static Figure createRandom() {
        Random random = new Random();
        int figureId = random.nextInt() % getCreatorsCount();
        return create(figureId);
    }
}

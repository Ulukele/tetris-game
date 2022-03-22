package tetris.figures.factory;

import tetris.figures.Figure;

public class StaticFigureFactory {
    private static final OFigureCreator oFigureCreator = new OFigureCreator();
    private static final LFigureCreator lFigureCreator = new LFigureCreator();

    public static Figure create(int figureId) {
        return switch (figureId) {
            case (0) -> oFigureCreator.create();
            case (1) -> lFigureCreator.create();
            default -> null;
        };
    }
}

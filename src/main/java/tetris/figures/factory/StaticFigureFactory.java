package tetris.figures.factory;

import tetris.common.BlockColor;
import tetris.figures.Figure;

import java.util.Random;

public class StaticFigureFactory {
    private static final OFigureCreator oFigureCreator = new OFigureCreator();
    private static final IFigureCreator iFigureCreator = new IFigureCreator();
    private static final LFigureCreator lFigureCreator = new LFigureCreator();
    private static final JFigureCreator jFigureCreator = new JFigureCreator();
    private static final SFigureCreator sFigureCreator = new SFigureCreator();
    private static final ZFigureCreator zFigureCreator = new ZFigureCreator();
    private static final TFigureCreator tFigureCreator = new TFigureCreator();

    public static Figure create(int figureId, BlockColor color) throws IndexOutOfBoundsException {
        if (figureId < 0 || figureId > 6) {
            throw new IndexOutOfBoundsException();
        }
        return switch (figureId) {
            case (0) -> oFigureCreator.create(color);
            case (1) -> iFigureCreator.create(color);
            case (2) -> lFigureCreator.create(color);
            case (3) -> jFigureCreator.create(color);
            case (4) -> sFigureCreator.create(color);
            case (5) -> zFigureCreator.create(color);
            case (6) -> tFigureCreator.create(color);
            default -> null;
        };
    }

    public static int getCreatorsCount() {
        return 7;
    }

    public static Figure createRandom() {
        Random random = new Random();
        BlockColor[] colors = BlockColor.values();
        int figureId = random.nextInt(getCreatorsCount());
        BlockColor color = colors[ random.nextInt(colors.length) ];
        return create(figureId, color);
    }
}

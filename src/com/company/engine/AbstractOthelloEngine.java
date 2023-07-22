package com.company.engine;

public interface AbstractOthelloEngine {

    public AbstractSquareBoard getBoard();

    public Point[] getOutflankPoints(Point point, Colour player);

    // Should return null in event of a tie.
    public Colour getWinner();

}

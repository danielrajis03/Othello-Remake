package com.company.engine;

import java.util.Iterator;

public interface AbstractSquareBoard {
    // class names -- CamelCase
    // method names -- camelCase
    void updateCellState(Point point, CellState cellState);
    CellState getCellState(Point point);
    Iterable<Point> getPoints();
    int size();
}

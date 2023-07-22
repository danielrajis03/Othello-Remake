package com.company.engine;

import java.util.Iterator;

public class SquareBoard implements AbstractSquareBoard {

    // Width and length of board
    private final int n;
    private final CellState[][] board;

    // Creates an n x n board with all cells unfilled
    public SquareBoard(int n) {
        this.n = n;
        this.board = new CellState[n][];
        for (int i = 0; i < n; i++) {
           this.board[i] = new CellState[n];
           for (int j = 0; j < n; j++) {
               this.board[i][j] = CellState.UNFILLED;
           }
        }
    }

    @Override
    public void updateCellState(Point point, CellState cellState) {
        if (point.getX() < 0 || point.getX() >= n || point.getY() < 0 || point.getY() >= n) {
            return;
        }

       this.board[point.getY()][point.getX()] = cellState;
    }

    @Override
    public CellState getCellState(Point point) {
        if (point.getX() < 0 || point.getX() >= n || point.getY() < 0 || point.getY() >= n) {
            return null;
        }
        return board[point.getY()][point.getX()];
    }

    @Override
    public Iterable<Point> getPoints() {
        return new Iterable<Point>() {
            @Override
            public Iterator<Point> iterator() {
                return new Iterator<Point>() {
                    private int x = 0;
                    private int y = 0;
                    @Override
                    public boolean hasNext() {
                        return !(y > n-1);
                    }

                    @Override
                    public Point next() {
                        if (!hasNext()) {
                            throw new UnsupportedOperationException("iterator has now next element");
                        }
                        Point point = new Point(x, y);
                        if (x < n-1) {
                            x += 1;
                        } else {
                            x = 0;
                            y += 1;
                        }
                        return point;
                    }
                };
            }
        };
    }

    @Override
    public int size() {
        return this.n;
    }

}

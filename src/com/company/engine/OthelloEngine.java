package com.company.engine;

import java.util.ArrayList;

public class OthelloEngine implements AbstractOthelloEngine {

    private AbstractSquareBoard board;

    public OthelloEngine(AbstractSquareBoard board) {
        this.board = board;
    }

    @Override
    public AbstractSquareBoard getBoard() {
        return this.board;
    }

    @Override
    public Point[] getOutflankPoints(Point point, Colour colour) {
        if (board.getCellState(point) != CellState.UNFILLED) {
            return new Point[0];
        }

        ArrayList<Point> points = new ArrayList<Point>(0);

        // The directions to go to see if a point is an outflank points.
        Translation[] translations = {
                new Translation(-1, 0),
                new Translation(1, 0),
                new Translation(0, 1),
                new Translation(0, -1),
                new Translation(-1, -1),
                new Translation(-1, 1),
                new Translation(1, -1),
                new Translation(1, 1),
        };

        for (Translation translation : translations) {
            // Copy the points since we will be mutating its state,
            // and for each translation we want to start from the original point.
            Point cursor = point.copy();

            // At the start we aren't on the other colour and there's no outflanked points.
            boolean onOtherColour = false;
            ArrayList<Point> outflankedPoints = new ArrayList<Point>(0);

            // These conditions ensures that we stay on the board.
            // Due to the translations we've specified, we are guaranteed to hit a board edge
            // i.e. the while loop will break.
            while (cursor.getX() >= 0 && cursor.getX() < board.size() && cursor.getY() >= 0 && cursor.getY() < board.size()) {
                cursor = cursor.translate(translation);

                CellState cellState = board.getCellState(cursor);

                // If next cell on path is unfilled then we know there aren't outflank points on the
                // path that we are traversing. So break and try the next translation.
                if (cellState == CellState.UNFILLED) {
                    break;
                }

                // Convert the cell state to a colour.
                Colour cellStateColour;
                if (cellState == CellState.BLACK) {
                    cellStateColour = Colour.BLACK;
                } else {
                    cellStateColour = Colour.WHITE;
                }

                if (cellStateColour == colour) {
                    // If are on the same colour and was on the other colour then we have found
                    // all the outflank points in the direction of travel.
                    if (onOtherColour) {
                        points.addAll(outflankedPoints);
                        break;
                    } else {
                        // If we weren't on the other colour then path wasn't valid.
                        break;
                    }
                } else {
                    // If it's the other colour then add the point to the set of outflanked points.
                    outflankedPoints.add(cursor.copy());
                    onOtherColour = true;
                }
            }
        }

        return points.toArray(new Point[0]);
    }

    @Override
    public Colour getWinner() {
        int whiteCounters = 0;
        int blackCounters = 0;
        for (Point point : this.getBoard().getPoints()) {
           CellState cellState = this.getBoard().getCellState(point);
           if (cellState == CellState.BLACK) {
               blackCounters += 1;
           } else if (cellState == CellState.WHITE) {
               whiteCounters += 1;
           }
        }

        if (blackCounters > whiteCounters) {
            return Colour.BLACK;
        } else if (whiteCounters > blackCounters) {
            return Colour.WHITE;
        } else {
            return null;
        }
    }
}

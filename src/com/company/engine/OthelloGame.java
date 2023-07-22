package com.company.engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class OthelloGame implements AbstractOthelloGame {

    private Colour currentPlayer;
    private OthelloEngine engine;

    public OthelloGame(OthelloEngine engine, Colour colour) {
        this.engine = engine;
        this.currentPlayer = colour;
    }

    @Override
    public Colour getCurrentPlayer() {
        return this.currentPlayer;
    }

    @Override
    public AbstractOthelloEngine getEngine() {
        return this.engine;
    }

    @Override
    public Point[] placePiece(Point target) {
        ArrayList<Point> updatedPoints = new ArrayList<>(0);

        Point[] outflankPoints = engine.getOutflankPoints(target, this.currentPlayer);
        if (outflankPoints.length == 0) {
            return updatedPoints.toArray(new Point[0]);
        }

        updatedPoints.add(target);
        Collections.addAll(updatedPoints, outflankPoints);

        CellState cellState;
        if (this.currentPlayer == Colour.BLACK) {
            cellState = CellState.BLACK;
        } else {
            cellState = CellState.WHITE;
        }

        for (Point updatedPoint : updatedPoints) {
            this.engine.getBoard().updateCellState(updatedPoint, cellState);
        }

        return updatedPoints.toArray(new Point[0]);
    }

    // Determine who the current player is, after the outflank move has been made.
    @Override
    public void nextPlayer() {
        if (this.currentPlayer == null) {
            return;
        }

        Colour otherPlayer;
        if (this.currentPlayer == Colour.BLACK) {
            otherPlayer = Colour.WHITE;
        } else {
            otherPlayer = Colour.BLACK;
        }

        // Check if other player can take a move.
        // If they can, update the current player.
        for (Point point : engine.getBoard().getPoints()) {
            if (engine.getOutflankPoints(point, otherPlayer).length > 0) {
                System.out.println("Current player is now " + otherPlayer);
                this.currentPlayer = otherPlayer;
                return;
            }
        }

        // If the other player can't take a move,
        // check the current player can make another move.
        for (Point point : engine.getBoard().getPoints()) {
            if (engine.getOutflankPoints(point, this.currentPlayer).length > 0) {
                System.out.println("Current player is still " + this.currentPlayer);
                return;
            }

        }

        System.out.println("No players can make a move");
        this.currentPlayer = null;
    }
}

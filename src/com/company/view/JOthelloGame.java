package com.company.view;

import com.company.engine.*;
import com.company.engine.Point;
import static javax.swing.JOptionPane.showMessageDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JOthelloGame  {

    AbstractSquareBoard board;
    OthelloGame game;
    JOthelloBoard[] boards;

    private Point rotate180(Point point) {
        return new Point(7 - point.getX(), 7 - point.getY());
    }

    public JOthelloGame() {
        this.board = new SquareBoard(8);
        JOthelloBoard whiteBoard = new JOthelloBoard(Colour.WHITE);
        JOthelloBoard blackBoard = new JOthelloBoard(Colour.BLACK);
        OthelloEngine engine = new OthelloEngine(board);
        this.game = new OthelloGame(engine, Colour.BLACK);

        JOthelloBoard[] boards = { whiteBoard, blackBoard };
        this.boards = boards;


        // No need to rotate points for the white board
        // as the initial pattern is the same are rotation of 180 degrees
        Point point0 = new Point(3,3);
        board.updateCellState(point0, CellState.WHITE);
        whiteBoard.getSquare(point0).setColour(Colour.WHITE);
        blackBoard.getSquare(point0).setColour(Colour.WHITE);

        Point point1 = new Point(4,3);
        board.updateCellState(point1, CellState.BLACK);
        whiteBoard.getSquare(point1).setColour(Colour.BLACK);
        blackBoard.getSquare(point1).setColour(Colour.BLACK);

        Point point2 = new Point(3,4);
        board.updateCellState(point2, CellState.BLACK);
        whiteBoard.getSquare(point2).setColour(Colour.BLACK);
        blackBoard.getSquare(point2).setColour(Colour.BLACK);

        Point point3 = new Point(4,4);
        board.updateCellState(point3, CellState.WHITE);
        whiteBoard.getSquare(point3).setColour(Colour.WHITE);
        blackBoard.getSquare(point3).setColour(Colour.WHITE);

        for (Point point : this.game.getEngine().getBoard().getPoints()) {
            for (JOthelloBoard board : boards) {
                board.getSquare(point).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Get the current player before placing a piece,
                        // so we know the state to update points to.
                        Colour currentPlayer = game.getCurrentPlayer();

                        // Don't allow white plays to be made on black board and vice versa
                        if (board.targetPlayer() != currentPlayer) {
                            return;
                        }

                        // Since white board is rotated 180 degrees from game engine internal representation,
                        // we need to rotate point 180 degrees before placing piece in game engine.
                        Point mappedPoint = point;
                        if (board.targetPlayer() == Colour.WHITE) {
                            mappedPoint = rotate180(point);
                        }

                        Point[] pointsToUpdate = game.placePiece(mappedPoint);

                        for (JOthelloBoard board1 : boards) {
                            for (Point pointToUpdate : pointsToUpdate) {
                                // Since white board is rotated 180 degrees from game engine internal representation,
                                // we need to rotate point 180 degrees before placing piece on board.
                                if (board1.targetPlayer() == Colour.WHITE) {
                                    pointToUpdate = rotate180(pointToUpdate);
                                }
                                JOthelloSquare square = board1.getSquare(pointToUpdate);
                                square.setColour(currentPlayer);
                                square.repaint();
                            }
                        }

                        game.nextPlayer();



                        if (game.getCurrentPlayer() == null) {
                            Colour winner = engine.getWinner();
                            if (winner == null) {
                                //System.out.println("Game was a draw");
                                showMessageDialog(null, "Game was a draw");
                                return;
                            }
                            //System.out.println("Game was won by " + winner);
                            showMessageDialog(null, "Game was won by " + winner);
                        }
                    }
                });
            }

        }
    }
    //public void greedyAI(){



    //JLabel label = new JLabel (player + " player - " + turnMessage() );

		//guiFrame.add(label, BorderLayout.NORTH);
		//guiFrame.add(panel, BorderLayout.CENTER );
		//guiFrame.add(button, BorderLayout.SOUTH );

    	//guiFrame.pack(); // Resize frame to fit content
    	//guiFrame.setVisible(true); // Display it � until you do it will not appear



    public void play() {
        for (JOthelloBoard board : this.boards) {
            board.render();
        }
    }

    /*@Override
    public void actionPerformed(ActionEvent e) {

    }*/
}

package com.company.view;

import com.company.engine.Colour;
import com.company.engine.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JOthelloBoard implements AbstractOthelloBoard, ActionListener {

    private Colour colour;
    private final int n = 8;
    private JOthelloSquare[][] board;
    private JFrame frame;


    public JOthelloBoard(Colour colour) {
        String textMessage = "Othello ";
        Font font = new Font("Jokerman", Font.PLAIN, 35);
        this.colour = colour;
        this.frame = new JFrame(textMessage);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel buttonPanel = new JPanel();
        JPanel containerPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(8, 8));

        this.board = new JOthelloSquare[n][];

        for (int i = 0; i < n; i++) {
            this.board[i] = new JOthelloSquare[n];
            for (int j = 0; j < n; j++) {
                JOthelloSquare square = new JOthelloSquare();
                this.board[i][j] = square;
                buttonPanel.add(square);
            }
        }

        buttonPanel.setPreferredSize(new Dimension(400, 400));
        containerPanel.add(buttonPanel);
        //game.nextPlayer();



        //JLabel label = new JLabel ( " player - " + currentPlayer );


        //JButton button = new JButton("Greedy AI (play " + Colour getCurrentPlayer + " )" );

        //button.setFont(new Font("Ariel",Font.BOLD, 60));
        //button.addActionListener( this );
        //frame.setVisible(true);

        frame.getContentPane().add(containerPanel);
        frame.pack();
    }


    public JOthelloSquare getSquare(Point point) {
        if (point.getX() < 0 || point.getX() >= n || point.getY() < 0 || point.getY() >= n) {
            return null;
        }
        return board[point.getY()][point.getX()];
    }

    @Override
    public void render() {
        frame.setVisible(true);
    }


    @Override
    public Colour targetPlayer() {
        return this.colour;
    }

    @Override
    public Point getGreedyMove(Colour player) {
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }


}



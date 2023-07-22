package com.company.view;

import com.company.engine.Colour;

import javax.swing.*;
import java.awt.*;

public class JOthelloSquare extends JButton {

    private int borderSize = 1;
    private int gap = 2;
    private int totalGap;
    private Color color;

    public JOthelloSquare() {
        this.totalGap = this.borderSize + this.gap;
    }

    @Override
    protected void paintComponent(Graphics g) {

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.GREEN);
        g.fillRect(borderSize, borderSize, getWidth() -
                borderSize * 2, getHeight() - borderSize * 2);

        if (color != null) {
            g.setColor(this.color);
            g.fillOval(totalGap, totalGap ,getWidth() - 2 * totalGap,getHeight()- 2 * totalGap);
        }

    }

    public void setColour(Colour colour) {
        if (colour == Colour.WHITE) {
            this.color = Color.WHITE;
        } else {
            this.color = Color.BLACK;
        }
    }
}

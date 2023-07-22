package com.company.view;

import com.company.engine.Colour;
import com.company.engine.Point;

public interface AbstractOthelloBoard {
    public JOthelloSquare getSquare(Point point);
    public void render();
    public Colour targetPlayer();
    Point getGreedyMove(Colour player);
}

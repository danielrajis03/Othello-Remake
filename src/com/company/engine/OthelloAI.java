package com.company.engine;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OthelloAI implements AbstractOthelloAI {
    final private AbstractOthelloEngine engine;

    public OthelloAI(AbstractOthelloEngine engine) {
        this.engine = engine;
    }

    @Override
    public Point getGreedyMove(Colour player) {
        // Point which returns the most outflank points.
        Point greedyPoint = null;
        int maxOutflankPoints = 0;

        for (Point point : this.engine.getBoard().getPoints()) {
            int outflankPoints = engine.getOutflankPoints(point, player).length;
            if (outflankPoints > maxOutflankPoints) {
                maxOutflankPoints = outflankPoints;
                greedyPoint = point;
            }
        }

        return greedyPoint;
    }
}

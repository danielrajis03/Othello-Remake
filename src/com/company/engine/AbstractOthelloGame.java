package com.company.engine;

public interface AbstractOthelloGame {
    Colour getCurrentPlayer();
    AbstractOthelloEngine getEngine();

    // Returns the points that were updated as a result of placing the piece.
    // The colour of the update can be inferred from the current player,
    // prior to the piece being placed.
    Point[] placePiece(Point point);

    // Should be called once a piece has been placed.
    // Updates the games current player based on who last played
    // and what available moves are to each player.
    // Returns null if no available moves are available to either player.
    void nextPlayer();

}

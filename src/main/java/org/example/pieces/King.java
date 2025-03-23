package org.example.pieces;

import org.example.Board;
import org.example.exceptions.TakeEnPassantException;

public class King extends Piece {
    public King(COLOR color) {
        super("K", color);
    }

    @Override
    public boolean isValidMove(Board board, int fromX, int fromY, int toX, int toY) throws TakeEnPassantException {
        super.isValidMove(board, fromX, fromY, toX, toY);
        int dx = Math.abs(fromX - toX);
        int dy = Math.abs(fromY - toY);
        return dx <= 1 && dy <= 1;
    }
}

package org.example.pieces;

import org.example.Board;
import org.example.exceptions.TakeEnPassantException;

public class Bishop extends Piece {
    public Bishop(COLOR color) {
        super("B", color);
    }

    @Override
    public boolean isValidMove(Board board, int fromX, int fromY, int toX, int toY) throws TakeEnPassantException {
        int dx = Math.abs(fromX - toX);
        int dy = Math.abs(fromY - toY);
        return dx == dy && dx > 0 && super.isValidMove(board, fromX, fromY, toX, toY);
    }


}

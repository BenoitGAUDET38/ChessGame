package org.example.pieces;

import org.example.Board;
import org.example.exceptions.TakeEnPassantException;

public class Queen extends Piece {
    public Queen(COLOR color) {
        super("Q", color);
    }

    @Override
    public boolean isValidMove(Board board, int fromX, int fromY, int toX, int toY) throws TakeEnPassantException {
        super.isValidMove(board, fromX, fromY, toX, toY);
        int dx = Math.abs(fromX - toX);
        int dy = Math.abs(fromY - toY);
        return (dx == dy && dx > 0) || (dx > 0 && dy == 0) || (dx == 0 && dy > 0);
    }
}

package org.example.pieces;

import org.example.Board;
import org.example.exceptions.TakeEnPassantException;

public class Rook extends Piece {
    public Rook(COLOR color) {
        super("R", color);
    }

    @Override
    public boolean isValidMove(Board board, int fromX, int fromY, int toX, int toY) throws TakeEnPassantException {
        super.isValidMove(board, fromX, fromY, toX, toY);
        int dx = Math.abs(fromX - toX);
        int dy = Math.abs(fromY - toY);
        return (dx > 0 && dy == 0) || (dx == 0 && dy > 0);
    }
}

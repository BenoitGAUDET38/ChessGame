package org.example.pieces;

import org.example.Board;

public class Knight extends Piece {
    public Knight(COLOR color) {
        super("N", color);
    }

    @Override
    public boolean isValidMove(Board board, int fromX, int fromY, int toX, int toY) {
        checkIfIsAnAlliedPiece(board, fromX, fromY, toX, toY);
        int dx = Math.abs(fromX - toX);
        int dy = Math.abs(fromY - toY);
        return dx + dy == 3 && dx > 0 && dy > 0;
    }
}

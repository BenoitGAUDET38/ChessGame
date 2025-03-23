package org.example.pieces;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.Board;
import org.example.exceptions.TakeEnPassantException;

@AllArgsConstructor
@Data
public abstract class Piece {
    private String name;
    private COLOR color;

    public boolean isValidMove(Board board, int fromX, int fromY, int toX, int toY) throws TakeEnPassantException {
        checkIfIsAnAlliedPiece(board, fromX, fromY, toX, toY);
        isTherePieceInTheWay(board, fromX, fromY, toX, toY);
        return false;
    }

    void isTherePieceInTheWay(Board board, int fromX, int fromY, int toX, int toY) {
        int dx = fromX - toX;
        int dy = fromY - toY;
        int x = fromX;
        int y = fromY;
        while (x != toX || y != toY) {
            x += dx > 0 ? -1 : dx < 0 ? 1 : 0;
            y += dy > 0 ? -1 : dy < 0 ? 1 : 0;
            if ((x != toX || y != toY) && board.getBoard()[x][y] != null) {
                throw new IllegalArgumentException("There is the piece " + board.getBoard()[x][y] + " in the way");
            }
        }
    }

    void checkIfIsAnAlliedPiece(Board board, int fromX, int fromY, int toX, int toY) {
        if (board.getPiece(fromX, fromY) != null && board.getPiece(toX, toY) != null && board.getPiece(fromX, fromY).getColor() == board.getPiece(toX, toY).getColor()) {
            throw new IllegalArgumentException("You can't eat your teammates");
        }
    }

    public String toString() {
        return color + " " + name;
    }
}

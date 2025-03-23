package org.example.pieces;

import lombok.Getter;
import lombok.Setter;
import org.example.Board;
import org.example.exceptions.TakeEnPassantException;

public class Pawn extends Piece {
    private boolean hasMoved = false;

    @Setter
    @Getter
    private boolean enPassant = false;

    public Pawn(COLOR color) {
        super("P", color);
    }

    @Override
    public boolean isValidMove(Board board, int fromX, int fromY, int toX, int toY) throws TakeEnPassantException {
        super.isValidMove(board, fromX, fromY, toX, toY);
        int dx = Math.abs(fromX - toX);
        int dy = Math.abs(fromY - toY);

        // Check if pawn moves in the correct direction
        if (getColor().equals(COLOR.WHITE)) {
            if (fromX > toX) {
                return false;
            }
        } else {
            if (fromX < toX) {
                return false;
            }
        }

        if (isEnPassantPossible(board, fromX, fromY, toX, toY)) {
            throw new TakeEnPassantException();
        } else if (!hasMoved && dx == 2 && dy == 0) {
            if (isThereAPieceAtArrivalPosition(board, toX, toY)) {
                throw new IllegalArgumentException("There is a piece at the arrival position of the pawn");
            }
            enPassant = true;
            hasMoved = true;
            return true;
        } else if (isThereAPieceAtArrivalPosition(board, toX, toY)) {
            hasMoved = true;
            return dx == 1 && dy == 1;
        }
        hasMoved = true;
        return dy == 0 && dx == 1;
    }

    private boolean isEnPassantPossible(Board board, int fromX, int fromY, int toX, int toY) {
        int dy = Math.abs(fromY - toY);
        if (dy != 1 || board.getPiece(fromX, toY) == null) {
            return false;
        }
        Piece piece = board.getPiece(fromX, toY);
        if (piece instanceof Pawn pawn) {
            return pawn.isEnPassant();
        }
        return false;
    }

    private boolean isThereAPieceAtArrivalPosition(Board board, int toX, int toY) {
        return board.getPiece(toX, toY) != null;
    }
}

package org.example;

import org.example.pieces.COLOR;

import java.util.ArrayList;
import java.util.List;

public class Game {
    List<Move> moves;
    Board board;

    public Game() {
        moves = new ArrayList<>();
    }

    public void start() {
        board = new Board();
        board.initBoard();
        board.display();
        board.movePiece("A2", "A3", COLOR.WHITE);
        board.display();
        board.movePiece("E7", "E5", COLOR.BLACK);
        board.display();
        board.movePiece("A3", "A4", COLOR.WHITE);
        board.display();
        board.movePiece("E5", "E4", COLOR.BLACK);
        board.display();
        board.movePiece("D2", "D4", COLOR.WHITE);
        board.display();
        board.movePiece("E4", "D3", COLOR.BLACK);
        board.display();
        board.movePiece("A4", "H5", COLOR.WHITE);
        board.display();
    }

    void play(Move move) {
        if (board.movePiece(move.getFrom(), move.getTo(), COLOR.BLACK)) {
            board.display();
            moves.add(move);
        } else {
            throw new IllegalArgumentException("Invalid move");
        }
    }
}

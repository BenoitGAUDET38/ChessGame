package org.example;

import lombok.Data;
import org.example.exceptions.TakeEnPassantException;
import org.example.pieces.Bishop;
import org.example.pieces.COLOR;
import org.example.pieces.King;
import org.example.pieces.Knight;
import org.example.pieces.Pawn;
import org.example.pieces.Piece;
import org.example.pieces.Queen;
import org.example.pieces.Rook;

import java.util.ArrayList;
import java.util.List;

import static org.example.pieces.COLOR.BLACK;
import static org.example.pieces.COLOR.WHITE;

@Data
public class Board {
    private static final int SIZE = 8;
    private static final List<String> COLUMN_NAME = List.of("A", "B", "C", "D", "E", "F", "G", "H");
    private static final List<String> ROW_NAME = List.of("1", "2", "3", "4", "5", "6", "7", "8");
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_WHITE = "\u001B[1;92m";
    private static final String ANSI_BLACK = "\u001B[1;91m";

    private Piece[][] board;
    private List<Pawn> pawns;

    public Board() {
        board = new Piece[SIZE][SIZE];
        pawns = new ArrayList<>();
    }

    public void addPiece(Piece piece, String position) {
        int x = ROW_NAME.indexOf(position.substring(1));
        int y = COLUMN_NAME.indexOf(position.substring(0, 1));
        board[x][y] = piece;
        if (piece instanceof Pawn) {
            pawns.add((Pawn) piece);
        }
    }

    public boolean movePiece(String from, String to, COLOR color) {
        int fromX = ROW_NAME.indexOf(from.substring(1));
        int fromY = COLUMN_NAME.indexOf(from.substring(0, 1));
        int toX = ROW_NAME.indexOf(to.substring(1));
        int toY = COLUMN_NAME.indexOf(to.substring(0, 1));

        if (board[fromX][fromY] == null) {
            throw new IllegalArgumentException("No piece at " + from);
        } else if (board[fromX][fromY].getColor() != color) {
            throw new IllegalArgumentException("You can't move the opponent's piece");
        }

        try {
            if (board[fromX][fromY].isValidMove(this, fromX, fromY, toX, toY)) {
                board[toX][toY] = board[fromX][fromY];
                board[fromX][fromY] = null;
                resetPawnEnPassant(color);
                return true;
            }
        } catch (TakeEnPassantException e) {
            board[toX][toY] = board[fromX][fromY];
            board[fromX][fromY] = null;
            board[fromX][toY] = null;
            return true;
        } catch (Exception e) {
            throw e;
        }

        throw new IllegalArgumentException("Invalid move");
//        return false;
    }

    public Piece getPiece(int x, int y) {
        return board[x][y];
    }

    private void resetPawnEnPassant(COLOR color) {
        pawns.stream()
                .filter(pawn -> !pawn.getColor().equals(color))
                .forEach(pawn -> pawn.setEnPassant(false));
    }

    public void initBoard() {
        // WHITE
        addPiece(new King(WHITE), "E1");
        addPiece(new Queen(WHITE), "D1");
        addPiece(new Rook(WHITE), "A1");
        addPiece(new Rook(WHITE), "H1");
        addPiece(new Bishop(WHITE), "C1");
        addPiece(new Bishop(WHITE), "F1");
        addPiece(new Knight(WHITE), "B1");
        addPiece(new Knight(WHITE), "G1");

        addPiece(new Pawn(WHITE), "A2");
        addPiece(new Pawn(WHITE), "B2");
        addPiece(new Pawn(WHITE), "C2");
        addPiece(new Pawn(WHITE), "D2");
        addPiece(new Pawn(WHITE), "E2");
        addPiece(new Pawn(WHITE), "F2");
        addPiece(new Pawn(WHITE), "G2");
        addPiece(new Pawn(WHITE), "H2");

        // BLACK
        addPiece(new King(BLACK), "E8");
        addPiece(new Queen(BLACK), "D8");
        addPiece(new Rook(BLACK), "A8");
        addPiece(new Rook(BLACK), "H8");
        addPiece(new Bishop(BLACK), "C8");
        addPiece(new Bishop(BLACK), "F8");
        addPiece(new Knight(BLACK), "B8");
        addPiece(new Knight(BLACK), "G8");

        addPiece(new Pawn(BLACK), "A7");
        addPiece(new Pawn(BLACK), "B7");
        addPiece(new Pawn(BLACK), "C7");
        addPiece(new Pawn(BLACK), "D7");
        addPiece(new Pawn(BLACK), "E7");
        addPiece(new Pawn(BLACK), "F7");
        addPiece(new Pawn(BLACK), "G7");
        addPiece(new Pawn(BLACK), "H7");
    }

    public void display() {
        String border = "  |-----|-----|-----|-----|-----|-----|-----|-----|";
        StringBuffer sb = new StringBuffer();
        sb.append("********** NEW CHESS BY PRII AND BENO **********\n");
        sb.append(border).append("\n");
        for (int i = SIZE-1; i >= 0; i--) {
            sb.append(ROW_NAME.get(i)).append(" ");
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == null) {
                    sb.append("|     ");
                } else {
                    String colorCode = board[i][j].getColor() == WHITE ? ANSI_WHITE : ANSI_BLACK;
                    sb.append("|  ").append(colorCode).append(board[i][j].getName()).append(ANSI_RESET).append("  ");
                }
            }
            sb.append("|").append("\n");
            sb.append(border).append("\n");
        }
        sb.append("     A     B     C     D     E     F     G     H\n");
        System.out.println(sb);
    }
}

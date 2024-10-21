package org.example.GameLogic;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TicTacToe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private List<Row> gameBoard = new ArrayList<>();

    private String currentPlayer;
    private boolean gameOver;
    private int turns;

    public TicTacToe() {
        currentPlayer = "X";
        gameOver = false;
        turns = 0;
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            gameBoard.add(new Row());  // Add 3 rows to the board
        }
    }

    public boolean isSpaceAvailable(int row, int col) {
        return gameBoard.get(row).getCells().get(col).equals(" ");
    }

    public boolean makeMove(int row, int col) {
        if (gameOver || !isSpaceAvailable(row, col)) {
            return false;
        }
        gameBoard.get(row).getCells().set(col, currentPlayer); // Update the board
        turns++;

        if (checkWinner()) {
            gameOver = true;
            System.out.println("Player " + currentPlayer + " won!");
        } else if (turns == 9) {
            gameOver = true;
            System.out.println("It's a tie!");
        } else {
            switchPlayer();
        }

        return true;
    }

    public void switchPlayer() {
        currentPlayer = currentPlayer.equals("X") ? "O" : "X";
    }

    private boolean checkWinner() {
        // Check rows
        for (int r = 0; r < 3; r++) {
            if (!gameBoard.get(r).getCells().get(0).equals(" ") && gameBoard.get(r).getCells().get(0).equals(gameBoard.get(r).getCells().get(1)) && gameBoard.get(r).getCells().get(1).equals(gameBoard.get(r).getCells().get(2))) {
                return true;
            }
        }
        // Check columns
        for (int c = 0; c < 3; c++) {
            if (!gameBoard.get(0).getCells().get(c).equals(" ") && gameBoard.get(0).getCells().get(c).equals(gameBoard.get(1).getCells().get(c)) && gameBoard.get(1).getCells().get(c).equals(gameBoard.get(2).getCells().get(c))) {
                return true;
            }
        }
        // Check diagonals
        if (!gameBoard.get(0).getCells().get(0).equals(" ") && gameBoard.get(0).getCells().get(0).equals(gameBoard.get(1).getCells().get(1)) && gameBoard.get(1).getCells().get(1).equals(gameBoard.get(2).getCells().get(2))) {
            return true;
        }
        if (!gameBoard.get(0).getCells().get(2).equals(" ") && gameBoard.get(0).getCells().get(2).equals(gameBoard.get(1).getCells().get(1)) && gameBoard.get(1).getCells().get(1).equals(gameBoard.get(2).getCells().get(0))) {
            return true;
        }
        return false;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void printBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(gameBoard.get(row).getCells().get(col));
                if (col < 2) System.out.print(" | ");
            }
            System.out.println();
            if (row < 2) System.out.println("_+__+_");
        }
    }
}
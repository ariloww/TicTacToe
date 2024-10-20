package org.example.model;

import jakarta.persistence.*;
import org.example.GameLogic.TicTacToe;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient  // We don't want to persist the TicTacToe instance directly
    private TicTacToe ticTacToe;

    public Game() {
        ticTacToe = new TicTacToe();  // Initialize the game logic
    }

    public TicTacToe getTicTacToe() {
        return ticTacToe;
    }

    public void setTicTacToe(TicTacToe ticTacToe) {
        this.ticTacToe = ticTacToe;
    }
}
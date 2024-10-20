package org.example.service;

import org.example.GameLogic.TicTacToe;
import org.example.model.Game;
import org.example.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public Game createGame(String player1, String player2) {
        Game game = new Game();
        return gameRepository.save(game);  // Save the game in the database
    }

    public Game makeMove(Long gameId, int row, int col, String player) {
        Optional<Game> gameOptional = gameRepository.findById(gameId);
        if (!gameOptional.isPresent()) {
            throw new IllegalArgumentException("Game not found");
        }

        Game game = gameOptional.get();
        TicTacToe ticTacToe = game.getTicTacToe();

        // Ensure the move is valid
        if (!ticTacToe.isSpaceAvailable(row, col)) {
            throw new IllegalStateException("Cell already occupied");
        }

        // Make the move and update game state
        ticTacToe.makeMove(row, col);

        return gameRepository.save(game);  // Save updated game state
    }

    public Game getGameState(Long gameId) {
        return gameRepository.findById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("Game not found"));
    }
}
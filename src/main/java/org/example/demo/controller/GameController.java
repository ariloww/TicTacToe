package org.example.controller;

import org.example.model.Game;
import org.example.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    // Start a new game between two players
    @PostMapping("/start")
    public Game startGame(@RequestParam String player1, @RequestParam String player2) {
        return gameService.createGame(player1, player2);
    }
    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }

    // Make a move in the game
    @PostMapping("/move")
    public Game makeMove(@RequestParam Long gameId, @RequestParam int row, @RequestParam int col, @RequestParam String player) {
        return gameService.makeMove(gameId, row, col, player);
    }

    // Retrieve the current game state
    @GetMapping("/{gameId}")
    public Game getGameState(@PathVariable Long gameId) {
        return gameService.getGameState(gameId);
    }
}
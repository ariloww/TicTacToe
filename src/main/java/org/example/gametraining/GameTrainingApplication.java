package org.example.gametraining;
import org.springframework.boot.SpringApplication;
import org.example.GameLogic.TicTacToe;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.example")
public class GameTrainingApplication {

    public static void main(String[] args) {

        SpringApplication.run(GameTrainingApplication.class, args);

    }

}

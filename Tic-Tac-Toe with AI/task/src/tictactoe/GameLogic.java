package tictactoe;

import java.util.Objects;
import java.util.Scanner;

public class GameLogic {
    private final Scanner scanner = new Scanner(System.in);
    private final GameBoard gameBoard;
    private Player player1;
    private Player player2;


    public GameLogic(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        gameStart();
    }

    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard();
        GameLogic gameLogic = new GameLogic(gameBoard);
    }

    private void gameStart() {
        players();
        boolean playFirst = true;
        gameBoard.printState();
        Infos gameState;
        player1.setFirstPlayer(true);
        player2.setFirstPlayer(false);
        do {
            if (playFirst) player1.play(); else player2.play();
            gameBoard.printState();
            gameState = gameBoard.checkState();
            playFirst = !playFirst;

        } while (gameState == GameState.GAME_NOT_FINISHED);
        gameState.print();
    }

    void players() {
        boolean correct = false;
        do {
            try {
                Message.INPUT_COMMAND.print();
                var commands = scanner.nextLine().split(" ");
                var start = commands[0];
                if (Objects.equals(start, "start")) {
                    String choice1 = commands[1];
                    String choice2 = commands[2];
                    player1 = switcher(choice1);
                    player2 = switcher(choice2);
                    correct = true;
                } else throw new IllegalArgumentException();
            } catch (Exception e) {
                Message.BAD_PARAMS.print();
            }

        } while (!correct);
    }

    private Player switcher(String choice) {
        Player player;
        switch (choice) {
            case "user":
                player = new User(gameBoard);
                break;
            case "easy":
                player = new AI(gameBoard);
                break;
            default:
               throw new IllegalArgumentException();
        }
        return player;
    }
}

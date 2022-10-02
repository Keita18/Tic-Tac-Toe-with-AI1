package tictactoe;

import java.util.Scanner;

public class Game {
    private final Scanner scanner = new Scanner(System.in);
    private GameBoard gameBoard;
    private Player firstPlayer;
    private Player secondPlayer;

    public Game(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    void start() {
        readCommands();
    }

    private void readCommands() {
        boolean exit = false;
        do {
            gameBoard = new GameBoard();
            Message.INPUT_COMMAND.print();
            try {
                var commands = scanner.nextLine().split(" ");
                switch (commands[0]) {
                    case "start":
                        switch (commands[1]) {
                            case "user":
                                firstPlayer = new User(gameBoard);
                                break;
                            case "easy":
                                firstPlayer = new AI(gameBoard);
                                break;
                            default:
                                throw new IllegalArgumentException();
                        }
                        firstPlayer.setFirstPlayer(true);
                        switch (commands[2]) {
                            case "user":
                                secondPlayer = new User(gameBoard);
                                break;
                            case "easy":
                                secondPlayer = new AI(gameBoard);
                                break;
                            default:
                                throw new IllegalArgumentException();
                        }
                        secondPlayer.setFirstPlayer(false);
                        startGame();
                        break;
                    case "exit":
                        exit = true;
                        break;
                    default:
                        throw new IllegalArgumentException();
                }

            } catch (Exception e) {
                Message.BAD_PARAMS.print();
            }
        } while (!exit);
    }

    private void startGame() {
        boolean playFirst = true;
        gameBoard.printState();
        Infos gameState;
        do {
            if (playFirst) firstPlayer.play();
            else secondPlayer.play();
            gameBoard.printState();
            gameState = gameBoard.checkState();
            playFirst = !playFirst;

        } while (gameState == GameState.GAME_NOT_FINISHED);
        gameState.print();
    }
}

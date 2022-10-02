package tictactoe;

import java.util.Scanner;

public class Game {
    private final Scanner scanner = new Scanner(System.in);
    private GameBoard gameBoard;
    private Player firstPlayer;
    private Player secondPlayer;

    public Game() {}

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
                        firstPlayer = switcher(commands[1]);
                        firstPlayer.setFirstPlayer(true);
                        secondPlayer = switcher(commands[2]);
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

    private Player switcher(String command) {
        Player player;
        switch (command) {
            case "user":
                player = new User(gameBoard);
                break;
            case "easy":
                player = new AI(gameBoard);
                break;
            case "medium":
                player = new AI(gameBoard);
                player.setLevel(2);
                break;
            case "hard":
                player = new AI(gameBoard);
                player.setLevel(3);
                break;
            default:
                throw new IllegalArgumentException();
        }
        return player;
    }
}
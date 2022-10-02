package tictactoe;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class GameLogic {
    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();
    private final GameBoard gameBoard;

    public GameLogic(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        gameStart();
    }

    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard();
        GameLogic gameLogic = new GameLogic(gameBoard);
    }

    private void gameStart() {
        boolean correct = false;
        try {
            Message.INPUT_COMMAND.print();
            var input = scanner.nextLine().split(" ");
            if (input.length == 3 && Objects.equals(input[0], "Start")) {
                var a = input[1];
                var b = input[2];
                switch (a) {
                    case "easy":

                        break;
                    case "medium":
                        break;
                    case "hard":
                        break;
                    case "user":
                        break;
                    default:
                        Message.BAD_PARAMS.print();
                        correct = false;
                }
                switch (a) {
                    case "easy":
                        break;
                    case "user":
                        break;
                    default:
                        Message.BAD_PARAMS.print();
                        correct = false;
                }
            }
        } catch (Exception e){

        }

        boolean isPlayer1 = true;


        gameBoard.printState();
        Infos gameState;
        do {
//            aiPlay(isPlayer1);

            gameBoard.printState();
            gameState = gameBoard.checkState();
            isPlayer1 = !isPlayer1;

        } while (gameState == GameState.GAME_NOT_FINISHED);
        gameState.print();
    }

    class UserPlay {
        void play(boolean isFirstPlayer) {
            boolean played;
            do {
                Message.ENTER_COORDINATES.print();
                Infos state = userMove(isFirstPlayer);
                played = state == CellState.DONE;
                if (!played) state.print();
            } while (!played);
        }

        private Infos userMove(boolean firstPleyer) {
            String coordinates = scanner.nextLine();
            int xCoordinates;
            int yCordinates;
            try {
                xCoordinates = Integer.parseInt(coordinates.split(" ")[0]);
                yCordinates = Integer.parseInt(coordinates.split(" ")[1]);
            } catch (Exception e) {
                return Message.INVALID_DIGIT;
            }
            if (xCoordinates > 3 || yCordinates > 3 || xCoordinates < 1 || yCordinates < 1)
                return Message.OUT_OF_BOUND;

            return gameBoard.move(xCoordinates, yCordinates, firstPleyer);
        }
    }
    class AiPlay {
        void play(boolean isFirstPlayer) {
            boolean played;
            Message.MAKING_MOVE.print();
            do {
                int aiX = random.nextInt(3) + 1;
                int aiY = random.nextInt(3) + 1;
                var state = gameBoard.move(aiX, aiY, isFirstPlayer);
                played = state == CellState.DONE;
            } while (!played);
        }
    }
}

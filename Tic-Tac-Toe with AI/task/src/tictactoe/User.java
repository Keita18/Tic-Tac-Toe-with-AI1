package tictactoe;

import java.util.Scanner;

public class User implements Player{
    private boolean firstPlayer = false;
    private final Scanner scanner = new Scanner(System.in);
    GameBoard gameBoard;
    public User(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    @Override
    public void play() {
        boolean played;
        do {
            Message.ENTER_COORDINATES.print();
            Infos state = userMove();
            played = state == CellState.DONE;
            if (!played) state.print();
        } while (!played);
    }

    private Infos userMove() {
        String coordinates = scanner.nextLine();
        int xCoordinates;
        int yCoordinates;
        try {
            xCoordinates = Integer.parseInt(coordinates.split(" ")[0]);
            yCoordinates = Integer.parseInt(coordinates.split(" ")[1]);
        } catch (Exception e) {
            return Message.INVALID_DIGIT;
        }
        if (xCoordinates > 3 || yCoordinates > 3 || xCoordinates < 1 || yCoordinates < 1)
            return Message.OUT_OF_BOUND;

        return gameBoard.move(xCoordinates, yCoordinates, firstPlayer);
    }

    @Override
    public void setFirstPlayer(boolean firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    @Override
    public void setLevel(int level) {
//        User don't set level
    }
}

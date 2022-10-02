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
        int yCordinates;
        try {
            xCoordinates = Integer.parseInt(coordinates.split(" ")[0]);
            yCordinates = Integer.parseInt(coordinates.split(" ")[1]);
        } catch (Exception e) {
            return Message.INVALID_DIGIT;
        }
        if (xCoordinates > 3 || yCordinates > 3 || xCoordinates < 1 || yCordinates < 1)
            return Message.OUT_OF_BOUND;

        return gameBoard.move(xCoordinates, yCordinates, firstPlayer);
    }

    public boolean isFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(boolean firstPleyer) {
        this.firstPlayer = firstPleyer;
    }

    @Override
    public void setLevel(int level) {
//        User don't set level
    }
}

package tictactoe;

import java.util.Random;

public class AI implements Player {
    private final Random random = new Random();
    GameBoard gameBoard;

    private boolean firstPlayer = false;

    private int level = 1;

    public AI(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    @Override
    public void play() {
        Message.MAKING_MOVE.print();
        switch (level) {
            case 2:
                mediumLevel();
            case 3:
                hardLevel();
                break;
            default:
                easyLevel();
        }
    }

    private void easyLevel() {
        boolean played;
        do {
            int aiX = random.nextInt(3) + 1;
            int aiY = random.nextInt(3) + 1;
            var state = gameBoard.move(aiX, aiY, firstPlayer);
            played = state == CellState.DONE;
        } while (!played);
    }

    private void mediumLevel() {

    }

    private void hardLevel() {

    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void setFirstPlayer(boolean firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

}

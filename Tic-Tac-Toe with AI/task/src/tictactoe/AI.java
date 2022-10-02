package tictactoe;

import java.util.Random;

public class AI implements Player {
    private final Random random = new Random();
    GameBoard gameBoard;

    private boolean firstPlayer = false;

    public AI(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    @Override
    public void play() {
        Message.MAKING_MOVE.print();
        easyLevel();
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

    public boolean isFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(boolean firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

}

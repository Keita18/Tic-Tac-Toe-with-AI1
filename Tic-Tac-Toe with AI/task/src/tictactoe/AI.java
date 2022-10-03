package tictactoe;

import java.util.Random;

public class AI implements Player {
    private final Random random = new Random();
    GameBoard gameBoard;

    private boolean firstPlayer = false;

    private int level = 1;
    private int smartLevelCounter = 0;

    public AI(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    @Override
    public void play() {
        switch (level) {
            case 2:
                Message.MAKING_MOVE_M.print();
                mediumLevel();
                break;
            case 3:
                Message.MAKING_MOVE_H.print();
                hardLevel();
                break;
            default:
                Message.MAKING_MOVE.print();
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
        boolean isCellFree;
        Infos gameState;
        boolean wined = false;
        boolean bocked = false;
        int xBlock = 0;
        int yBlock = 0;

        GameState toWin = firstPlayer ? GameState.X_WIN : GameState.O_WIN;
        GameState toLoose = !firstPlayer ? GameState.X_WIN : GameState.O_WIN;
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                isCellFree = gameBoard.isCellFree(i, j);
                if (isCellFree) {
                    gameBoard.move(i, j, firstPlayer);
                    gameState = gameBoard.checkState();
                    if (gameState == toWin || gameState == GameState.DRAW) {
                        wined = true;
                    } else {
                        gameBoard.remove(i, j);
                        gameBoard.move(i, j, !firstPlayer);
                        gameState = gameBoard.checkState();
                        if (gameState == toLoose) {
                            gameBoard.remove(i, j);
                            xBlock = i;
                            yBlock = j;
                            bocked = true;
                        } else {
                            gameBoard.remove(i, j);
                        }
                    }
                }
                if (wined) break;
            }
            if (wined) break;
        }
        if (!wined) {
            if (bocked) {
                gameBoard.move(xBlock, yBlock, firstPlayer);
            } else easyLevel();
        }
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

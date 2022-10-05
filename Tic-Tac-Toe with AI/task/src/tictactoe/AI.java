package tictactoe;

import java.util.Random;

public class AI implements Player {
    private final Random random = new Random();
    GameBoard gameBoard;
    GameState toWin = GameState.O_WIN;
    GameState toLoose = GameState.X_WIN;
    private boolean firstPlayer = false;

    private int level = 1;

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
        boolean blocked = false;
        int xBlock = 0;
        int yBlock = 0;

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
                            blocked = true;
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
            if (blocked) {
                gameBoard.move(xBlock, yBlock, firstPlayer);
            } else easyLevel();
        }
    }

    private void hardLevel() {
        int bestScore = -1000;
        int bestRow = 0;
        int bestCol = 0;
//        gameBoard.move(1, 1, false);
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                if (gameBoard.isCellFree(i, j)) {
                    gameBoard.move(i, j, firstPlayer);
                    int moveVal = minimax(0, !firstPlayer);
                    System.out.println("best = " + moveVal +" row-" + i + " col-"+j);

                    gameBoard.remove(i, j);

                    if (moveVal > bestScore) {
                        bestRow = i;
                        bestCol = j;
                        bestScore = moveVal;
                    }
                }
            }
        }
        gameBoard.move(bestRow, bestCol, firstPlayer);
    }

    private int minimax(int depth, boolean isMax) {
        if (gameBoard.checkState() == toWin)
            return 10;
        if (gameBoard.checkState() == toLoose)
            return -10;
        if (gameBoard.checkState() == GameState.DRAW)
            return 0;
        if (isMax) {
            int best = -1000;
            for (int i = 1; i < 4; i++) {
                for (int j = 1; j < 4; j++) {
                    if (gameBoard.isCellFree(i, j)) {
                        gameBoard.move(i, j, true);
                        best = Math.max(best, minimax(depth + 1, false));
                        gameBoard.remove(i, j);
                    }
                }
            }
            return best;
        } else {
            int best = 1000;
            for (int i = 1; i < 4; i++) {
                for (int j = 1; j < 4; j++) {
                    if (gameBoard.isCellFree(i, j)) {
                        gameBoard.move(i, j, false);
                        best = Math.min(best, minimax(depth+1, true));
                        gameBoard.remove(i, j);
                    }
                }
            }
            return best;
        }
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void setFirstPlayer(boolean firstPlayer) {
        this.firstPlayer = firstPlayer;
        if (firstPlayer) {
            toWin = GameState.X_WIN;
            toLoose = GameState.O_WIN;
        }
    }

}

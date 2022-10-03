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
        smartLevelCounter++;
        int xLoose = 0;
        int yLoose = 0;
        boolean stop = false;
        if (smartLevelCounter < 3) {
            easyLevel();
        } else {
            for (int i = 1; i < 4; i++) {
                for (int j = 1; j < 4; j++) {
                    var count = 2;
                    var first = firstPlayer;
                    while (count > 0) {
                        var moved = gameBoard.move(i, j, first);
                        if (moved == CellState.DONE) {
                            var check = (GameState) gameBoard.checkState();
                            switch (check) {
                                case X_WIN:
                                    if (!firstPlayer) {
                                        xLoose = i;
                                        yLoose = j;
                                        gameBoard.remove(i, j);
                                    } else {
                                        stop = true;
                                    }
                                    break;
                                case O_WIN:
                                    if (firstPlayer) {
                                        xLoose = i;
                                        yLoose = j;
                                        gameBoard.remove(i, j);
                                    } else {
                                        stop = true;
                                    }
                                    break;
                                case DRAW:
                                    gameBoard.remove(i, j);
                                    stop = true;
                                    break;
                                case GAME_NOT_FINISHED:
                                    gameBoard.remove(i, j);
                                    break;
                            }
                        }
                        first = !first;
                        count--;
                    }
                    if (stop) break;
                }
                if (stop) break;
            }
            gameBoard.move(xLoose, yLoose, firstPlayer);
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

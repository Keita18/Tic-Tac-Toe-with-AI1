package tictactoe;


public class GameBoard {
    private final char[][] board = new char[][]{{'_', '_', '_'}, {'_', '_', '_'}, {'_', '_', '_'}};
    private int moveCount = 9;

    public GameBoard() {}

    public GameBoard(String initialState) {
        init(initialState);
    }

    private void init(String initialState) {
        int xCount, oCount, index;
        index = xCount = oCount = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (initialState.charAt(index) == 'X') xCount++;
                if (initialState.charAt(index) == 'O') oCount++;
                board[i][j] = initialState.charAt(index);
                index++;
            }
        }
        moveCount -= xCount + oCount;
    }

    public Infos move(int x, int y, boolean isPlayer1) {
        if (board[--x][--y] == '_') {
            if (isPlayer1) board[x][y] = 'X';
            else board[x][y] = 'O';
            moveCount--;
            return CellState.DONE;
        }
        return CellState.OCCUPIED;
    }

    public Infos checkState() {
        GameState gameState = GameState.GAME_NOT_FINISHED;

        for (int i = 0; i < 3; i++) {
            String row = "" + board[i][0] + board[i][1] + board[i][2];
            String col = "" + board[0][i] + board[1][i] + board[2][i];

            if (row.equals("XXX") || col.equals("XXX")) gameState = GameState.X_WIN;
            if (row.equals("OOO") || col.equals("OOO")) gameState = GameState.O_WIN;
        }
        var diag1 = "" + board[0][0] + board[1][1] + board[2][2];
        var diag2 = "" + board[2][0] + board[1][1] + board[0][2];

        if (diag1.equals("XXX") || diag2.equals("XXX")) gameState = GameState.X_WIN;
        if (diag1.equals("OOO") || diag2.equals("OOO")) gameState = GameState.O_WIN;
        if (moveCount <= 0) gameState = GameState.DRAW;
        return gameState;
    }

    public void printState() {
        System.out.println("---------");
        System.out.println("| " + board[0][0] + " " + board[0][1] + " " + board[0][2] + " |");
        System.out.println("| " + board[1][0] + " " + board[1][1] + " " + board[1][2] + " |");
        System.out.println("| " + board[2][0] + " " + board[2][1] + " " + board[2][2] + " |");
        System.out.println("---------");
    }
}
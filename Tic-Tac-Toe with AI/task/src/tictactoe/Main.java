package tictactoe;

public class Main {
    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard();
        Game game = new Game(gameBoard);
        game.start();
    }
}
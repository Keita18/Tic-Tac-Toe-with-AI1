package tictactoe;

enum Message implements Infos {
    INVALID_DIGIT("You should enter numbers!"),
    OUT_OF_BOUND("Coordinates should be from 1 to 3!"),
    ENTER_COORDINATES("Enter the coordinates:"),
    INPUT_COMMAND("Input command:"),
    BAD_PARAMS("Bad parameters!"),
    MAKING_MOVE("Making move level \"easy\""),
    MAKING_MOVE_M("Making move level \"medium\""),
    MAKING_MOVE_H("Making move level \"hard\"");
    String message;

    Message(String message) {
        this.message = message;
    }

    @Override
    public void print() {
        System.out.println(message);
    }
}

enum GameState implements Infos {
    X_WIN("X wins"), O_WIN("O wins"), DRAW("Draw"),
    GAME_NOT_FINISHED("Game not finished");

    private final String state;

    GameState(String state) {
        this.state = state;
    }

    public void print() {
        System.out.println(state);
    }
}

enum CellState implements Infos {
    OCCUPIED("This cell is occupied! Choose another one!"),
    DONE("This cell is Empty, can make move");
    String cellState;
    CellState(String cellState) {
        this.cellState = cellState;
    }
    @Override
    public void print() {
        System.out.println(cellState);
    }
}

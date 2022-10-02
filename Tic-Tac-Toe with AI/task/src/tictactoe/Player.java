package tictactoe;

interface Player {
    void play();
    public boolean isFirstPlayer();

    public void setFirstPlayer(boolean firstPlayer);
}

interface Infos {
    void print();
}

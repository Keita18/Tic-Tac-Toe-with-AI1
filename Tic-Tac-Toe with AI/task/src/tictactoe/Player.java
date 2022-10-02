package tictactoe;

interface Player {
    void play();
    boolean isFirstPlayer();

    void setFirstPlayer(boolean firstPlayer);

    void setLevel(int level);
}

abstract class Playerw {
    boolean play;
    void play() {
        System.out.println("This is not implemented");
    }

}

interface Infos {
    void print();
}

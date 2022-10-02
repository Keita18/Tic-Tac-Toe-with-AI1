package tictactoe;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        char[][] grid = new char[][]{{'_', '_', '_'}, {'_', '_', '_'}, {'_', '_', '_'}};

        print(grid);
        String move;
        String state;
        int isDraw = 9;
        boolean isPlayer1 =true;
        Random random = new Random();
        do {
            do {
                int aiX = random.nextInt(3) + 1 ;
                int aiY = random.nextInt(3) + 1;
                var coordinates = aiX + " " + aiY;
                if (isPlayer1) {
                    System.out.print("Enter the coordinates: ");
                    coordinates =  scanner.nextLine();
                }
                    move = move(grid, coordinates, isPlayer1);
                if (move != null && isPlayer1) System.out.println(move);
            } while (move != null);

            state = checkState(grid);
            if (!isPlayer1) {
                System.out.println("Making move level \"easy\"");
            }
            print(grid);

            isDraw--;
            isPlayer1 = !isPlayer1;

        } while (state == null && isDraw > 0);

        if (state != null) {
            System.out.println(state + " wins");
        } else System.out.println("Draw");

    }

    static String move(char[][] arr, String coordinates, boolean isPlayer1) {
        int col;
        int row;

        try {
            col = Integer.parseInt(coordinates.split(" ")[0]);
            row = Integer.parseInt(coordinates.split(" ")[1]);
        } catch (Exception e) {
            return "You should enter numbers!";
        }
        if (col > 3 || row > 3 || col < 1 || row < 1)
            return "Coordinates should be from 1 to 3!";
        if (arr[--col][--row] == '_') {
            if (isPlayer1) arr[col][row] = 'X';
            else arr[col][row] = 'O';
            return null;
        }
        return "This cell is occupied! Choose another one!";
    }

    static String checkState(char[][] grid) {
        String winner = null;

        for (int i = 0; i < 3; i++) {
            String row = "" + grid[i][0] + grid[i][1] + grid[i][2];
            String col = "" + grid[0][i] + grid[1][i] + grid[2][i];

            if (row.equals("XXX") || col.equals("XXX")) winner = "X";
            if (row.equals("OOO") || col.equals("OOO")) winner = "O";
        }
        var diag1 = "" + grid[0][0] + grid[1][1] + grid[2][2];
        var diag2 = "" + grid[2][0] + grid[1][1] + grid[0][2];

        if (diag1.equals("XXX") || diag2.equals("XXX")) winner = "X";
        if (diag1.equals("OOO") || diag2.equals("OOO")) winner = "O";
        return winner;
    }

    static void print(char[][] input) {
        System.out.println("---------");
        System.out.println("| " + input[0][0] + " " + input[0][1] + " " + input[0][2] + " |");
        System.out.println("| " + input[1][0] + " " + input[1][1] + " " + input[1][2] + " |");
        System.out.println("| " + input[2][0] + " " + input[2][1] + " " + input[2][2] + " |");
        System.out.println("---------");
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        TicTacToe game = new TicTacToe(3);
        boolean gameOver = false;

        while (!gameOver) {
            game.printBoard();
            System.out.printf("Ход игрока %s%n", game.getCurrentPlayer());
            boolean moveIsCompleted = false;
            while (!moveIsCompleted) {
                System.out.println("Введите координаты: 'row col'");
                try {
                    int[] pair = Arrays.stream(reader.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();

                    moveIsCompleted = game.makeMove(pair[0], pair[1]);
                } catch (NumberFormatException e) {
                    System.out.println("Некорректный формат ввода: введите row col через пробел");
                }
            }

            gameOver = game.isOver();
            game.switchPlayer();
        }

        game.printBoard();
        System.out.println("Игра завершена");
    }
}

class TicTacToe {
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';
    private static final char EMPTY = ' ';

    private final char[][] board;
    private char currentPlayer;
    private final int n;
    private int movesCounter = 0;

    public TicTacToe(int n) {
        this.n = n;
        this.board = new char[n][n];
        initBoard();
    }

    private void initBoard() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = EMPTY;
            }
        }
        currentPlayer = PLAYER_X;
    }

    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
        }
    }

    public boolean makeMove(int row, int col) {
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            System.out.println("Неверный ввод. Координаты должны быть от 0 до 2.");
            return false;
        }

        if (board[row][col] != EMPTY) {
            System.out.println("Эта клетка уже занята!");
            return false;
        }

        board[row][col] = currentPlayer;
        movesCounter++;
        return true;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
    }

    public boolean isOver() {
        return movesCounter == n * n;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }
}

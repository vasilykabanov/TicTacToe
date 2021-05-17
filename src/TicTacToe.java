import java.io.IOException;
import java.util.Scanner;

public class TicTacToe {
    private final Scanner scanner = new Scanner(System.in);
    private static final int boardSize = 3;
    private static final char[][] board = new char[boardSize][boardSize];
    private static char player;
    private static char bot;
    private boolean draw = false;

    public void printBoard(char ch) {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                System.out.print(board[i][j] = ch);
                System.out.print("\t");
            }
            System.out.println();
        }
    }

    public void printBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                System.out.print(board[i][j]);
                System.out.print("\t");
            }
            System.out.println();
        }
    }

    public void move() {
        int row, col;
        boolean valid = false;

        do {
            System.out.println("Player " + player + " make your move (row: 0,1,2)");
            row = scanner.nextInt();
            System.out.println("Player " + player + " make your move (col: 0,1,2)");
            col = scanner.nextInt();

            if (row < boardSize && row >= 0 && col < boardSize && col >= 0 && board[row][col] == '*') {
                board[row][col] = player;
                valid = true;
            } else {
                System.out.println("This move at (" + row + "," + col + ") is not valid. Try again...");
            }
        } while (!valid);
        printBoard();
    }

    public void selectPlayer() throws IOException {
        char ignore;
        do {
            System.out.println("Select a player (X or O): ");
            player = (char) System.in.read();
            if (!isValidPlayer(player)) {
                System.out.println("Player is not valid. Please choice X or O.");
            }
            do {
                ignore = (char) System.in.read();
            } while (ignore != '\n');
        } while (!isValidPlayer(player));
        System.out.println("You have selected a player: " + player);
    }

    private boolean isValidPlayer(int ch) {
        return (ch == 'X' | ch == 'O');
    }

    public boolean checkWin() {
        return ((board[0][0] == player && board[0][1] == player && board[0][2] == player) ||
                (board[1][0] == player && board[1][1] == player && board[1][2] == player) ||
                (board[2][0] == player && board[2][1] == player && board[2][2] == player) ||
                (board[0][0] == player && board[1][0] == player && board[2][0] == player) ||
                (board[0][1] == player && board[1][1] == player && board[2][1] == player) ||
                (board[0][2] == player && board[1][2] == player && board[2][2] == player) ||
                (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[2][0] == player && board[1][1] == player && board[0][2] == player) ||

                (board[0][0] == bot && board[0][1] == bot && board[0][2] == bot) ||
                (board[1][0] == bot && board[1][1] == bot && board[1][2] == bot) ||
                (board[2][0] == bot && board[2][1] == bot && board[2][2] == bot) ||
                (board[0][0] == bot && board[1][0] == bot && board[2][0] == bot) ||
                (board[0][1] == bot && board[1][1] == bot && board[2][1] == bot) ||
                (board[0][2] == bot && board[1][2] == bot && board[2][2] == bot) ||
                (board[0][0] == bot && board[1][1] == bot && board[2][2] == bot) ||
                (board[2][0] == bot && board[1][1] == bot && board[0][2] == bot));
    }

    public char getPlayer() {
        return player;
    }

    public void setBot() {
        if (getPlayer() == 'X') {
            bot = 'O';
        } else {
            bot = 'X';
        }
    }

    public char getBot() {
        return bot;
    }

    public void moveBot() {
        setBot();
        try {
            board[Integer.parseInt(String.valueOf(getFreeCells().charAt(0)))]
                    [Integer.parseInt(String.valueOf(getFreeCells().charAt(1)))] = getBot();
        } catch (IllegalArgumentException e) {
            System.out.println("There are no more free cells!");
            draw = true;
            return;
        }
        System.out.println("Opponent's move...");
        printBoard();
    }

    public void play() throws IOException {
        printBoard('*');
        selectPlayer();
        do {
            move();
            if (checkWin()) {
                System.out.println("Congratulations!!! You won!");
                return;
            }
            moveBot();
            if (checkWin()) {
                System.out.println("You lose!!!");
                return;
            }
            if (draw) System.out.println("Draw!");
        } while (!draw);
    }

    private String getFreeCells() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                // тут пока так
                if (board[i][j] == '*') {
                    stringBuilder.append(i);
                    stringBuilder.append(j);
                    break;
                }
            }
        }
        if (stringBuilder.length() == 0)
            throw new IllegalArgumentException();
        return stringBuilder.toString();
    }
}

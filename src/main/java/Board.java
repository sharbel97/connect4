// Board class for our general connect4 game logic
// Initializes internal board
// handles board methods like validMove, move, checkWinner(buggy)
// it also handles the reversal of a move, and starting a new game


public class Board {
    private int[][] board; // 0 = unoccupied, 1 = player1, 2 = player2
    public int currentPlayer = 1;

    public Board() {
        board = new int[6][7];
    }

    public boolean validMove(int row, int col) {
        if (board[row][col]!=0) return false;

        if (row != 5) { // not last row, check if button below it exists, and above is empty
            if (row == 0) { // top row, only check row below it
                if (board[row+1][col] != 0) {
                    return true;
                } else {
                    return false;
                }
            } else { // check row below is occupied, row above is unoccupied
                if (board[row + 1][col] != 0 && board[row - 1][col] == 0) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public void move(int row, int col, int player) {
        board[row][col] = player;
        currentPlayer = switchPlayer(player);
    }

    public int checkWinner() {
        // must write
        int row;
        int col;
        int a;
        int b;
        int c;
        int d;
        int part=0;
        int count=0;

        for(row=0;row<=2;row++){
            for(col=0;col<=3;col++){
                //--Horizontal Check--
                if (board[row][col]!=0) {
                    for (a = col; a <= col + 3; a++) {
                        if ((board[row][a]) == (board[row][col])) {
                            count += 1;
                        }
                    }
                    if (count == 4) {
                        count = 0;
                        return board[row][col];
                    } else {
                        count = 0;
                    }
                    //--Vertical Check--
                    for (b = row; b <= row + 3; b++) {
                        if ((board[b][col]) == (board[row][col])) {
                            count += 1;
                        }
                    }
                    if (count == 4) {
                        count = 0;
                        return board[row][col];
                    } else {
                        count = 0;
                    }
                    //--Diagonal check--
                    for (c = col, d = row; c <= col + 3 && d <= row + 3; c++, d++) {
                        if ((board[c][d]) == (board[row][col])) {
                            count += 1;
                        }
                    }
                    if (count == 4) {
                        count = 0;
                        return board[row][col];
                    } else {
                        count = 0;
                    }
                }
            }
        }
        for(row=5;row>=3;row--){
            for(col=6;col>=3;col--){
                //--Horizontal Check--
                if (board[row][col]!=0) {
                    for (a = col; a >= col - 3; a--) {
                        if ((board[row][a]) == (board[row][col])) {
                            count += 1;
                        }
                    }
                    if (count == 4) {
                        count = 0;
                        return board[row][col];
                    } else {
                        count = 0;
                    }
                    //--Vertical Check--
                    for (b = row; b >= row - 3; b--) {
                        if ((board[b][col]) == (board[row][col])) {
                            count += 1;
                        }
                    }
                    if (count == 4) {
                        count = 0;
                        return board[row][col];
                    } else {
                        count = 0;
                    }
                    //--Diagonal check--
                    for (c = col, d = row; c >= col - 3 && d >= row - 3; c--, d--) {
                        if ((board[c][d]) == (board[row][col])) {
                            count += 1;
                        }
                    }
                    if (count == 4) {
                        count = 0;
                        return board[row][col];
                    } else {
                        count=0;
                    }
                }
            }
        }
        // --no one won--
        return 0;
    }

    public void reverseMove(int x, int y) {
        board[x][y] = 0;
        currentPlayer = switchPlayer(currentPlayer);
    }

    public void newGame() {
        currentPlayer = 1;
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                board[row][col] = 0;
            }
        }
    }



    public int switchPlayer(int x) {
        if (x==1) {
            return 2;
        } else {
            return 1;
        }
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

}

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    private Board board;
    private int player;

    @BeforeEach
    void init() {
        board = new Board();
        player = 1;
    }

    @Test
    void validMoveEmptyBoardTest() {
        // checking if all bottom row buttons are valid initially
        for (int col = 0; col < 7; col++) {
            assertTrue(board.validMove(5,col));
        }
    }

    @Test
    void validMoveFullBoardMinusTopRowTest() {
        for (int row = 5; row>0; row--) {
            for (int col = 0; col < 7; col++) {
                assertTrue(board.validMove(row,col)); // check valid move
                assertEquals(player, board.getCurrentPlayer()); // check current player
                board.move(row, col, board.getCurrentPlayer()); // execute move
                player = board.switchPlayer(player); // switch test class player
                assertEquals(player, board.getCurrentPlayer()); // check if internal player switched
            }
        }
    }

    @Test
    void validMoveTopRowTest(){
        // fill the board until top row
        for (int row = 5; row>0; row--) {
            for (int col = 0; col < 7; col++) {
                assertTrue(board.validMove(row,col)); // check valid move
                assertEquals(player, board.getCurrentPlayer()); // check current player
                board.move(row, col, board.getCurrentPlayer()); // execute move
                assertFalse(board.validMove(row,col));
                player = board.switchPlayer(player); // switch test class player
                assertEquals(player, board.getCurrentPlayer()); // check if internal player switched
            }
        }

        // Test Top Row separately
        for (int col = 0; col < 7; col++) {
            assertTrue(board.validMove(0,col)); // check valid move
            assertEquals(player, board.getCurrentPlayer()); // check current player
            board.move(0, col, board.getCurrentPlayer()); // execute move
            assertFalse(board.validMove(0, col));
            player = board.switchPlayer(player); // switch test class player
            assertEquals(player, board.getCurrentPlayer()); // check if internal player switched
        }
    }

    @Test
    void moveBottomRowTest() {
        for (int col = 0; col < 7; col++) {
            assertTrue(board.validMove(5,col)); // check valid move
            assertEquals(player, board.getCurrentPlayer()); // check current player
            board.move(5, col, board.getCurrentPlayer()); // execute move
            assertFalse(board.validMove(5, col));
            player = board.switchPlayer(player); // switch test class player
            assertEquals(player, board.getCurrentPlayer()); // check if internal player switched
        }
    }

    @Test
    void moveLeftColumnTest() {
        for (int row = 5; row > -1; row--) {
            assertTrue(board.validMove(row,0)); // check valid move
            assertEquals(player, board.getCurrentPlayer()); // check current player
            board.move(row, 0, board.getCurrentPlayer()); // execute move
            assertFalse(board.validMove(row, 0));
            player = board.switchPlayer(player); // switch test class player
            assertEquals(player, board.getCurrentPlayer()); // check if internal player switched
        }
    }

    @Test
    void moveRightColumnTest() {
        for (int row = 5; row > -1; row--) {
            assertTrue(board.validMove(row,6)); // check valid move
            assertEquals(player, board.getCurrentPlayer()); // check current player
            board.move(row, 6, board.getCurrentPlayer()); // execute move
            assertFalse(board.validMove(row, 6));
            player = board.switchPlayer(player); // switch test class player
            assertEquals(player, board.getCurrentPlayer()); // check if internal player switched
        }
    }


    @Test
    void reverseMoveBottomRow() {
        for (int col = 0; col < 7; col++) {
            assertTrue(board.validMove(5,col)); // check valid move
            assertEquals(player, board.getCurrentPlayer()); // check current player
            board.move(5, col, board.getCurrentPlayer()); // execute move
            assertFalse(board.validMove(5, col));
            player = board.switchPlayer(player); // switch test class player
            assertEquals(player, board.getCurrentPlayer()); // check if internal player switched
        }

        assertEquals(2, player);
        assertEquals(2, board.getCurrentPlayer());

        for (int col = 0; col < 7; col++) {
            assertFalse(board.validMove(5, col)); // move should be invalid
            assertEquals(player, board.getCurrentPlayer()); // make sure players are equal
            board.reverseMove(5, col); // reverse the move: should reverse current player
            player = board.switchPlayer(player); // switch test player
            assertEquals(player, board.getCurrentPlayer()); // check if test player and board player match
            assertTrue(board.validMove(5, col)); // check if the same move is valid again
        }
    }

    @Test
    void reverseMoveFullBoardTest() {
        for (int row = 5; row>-1; row--) {
            for (int col = 0; col < 7; col++) {
                assertTrue(board.validMove(row,col)); // check valid move
                assertEquals(player, board.getCurrentPlayer()); // check current player
                board.move(row, col, board.getCurrentPlayer()); // execute move
                assertFalse(board.validMove(row,col));
                player = board.switchPlayer(player); // switch test class player
                assertEquals(player, board.getCurrentPlayer()); // check if internal player switched
            }
        }
        assertEquals(player, board.getCurrentPlayer());

        for (int row = 0; row<6; row++) {
            for (int col = 6; col > -1; col--) {
                board.reverseMove(row,col);
                assertTrue(board.validMove(row,col));
                player = board.switchPlayer(player);
                assertEquals(player, board.getCurrentPlayer());
            }
        }

        for (int row = 5; row>-1; row--) {
            for (int col = 0; col < 7; col++) {
                assertTrue(board.validMove(row,col)); // check valid move
                assertEquals(player, board.getCurrentPlayer()); // check current player
                board.move(row, col, board.getCurrentPlayer()); // execute move
                assertFalse(board.validMove(row,col));
                player = board.switchPlayer(player); // switch test class player
                assertEquals(player, board.getCurrentPlayer()); // check if internal player switched
            }
        }
        assertEquals(player, board.getCurrentPlayer());
    }

    @Test
    void newGameTest() {
        for (int row = 5; row>-1; row--) {
            for (int col = 0; col < 7; col++) {
                assertTrue(board.validMove(row,col)); // check valid move
                assertEquals(player, board.getCurrentPlayer()); // check current player
                board.move(row, col, board.getCurrentPlayer()); // execute move
                assertFalse(board.validMove(row,col));
                player = board.switchPlayer(player); // switch test class player
                assertEquals(player, board.getCurrentPlayer()); // check if internal player switched
            }
        }
        assertEquals(player, board.getCurrentPlayer());

        board.newGame();
        player = 1;
        assertEquals(player, board.getCurrentPlayer());

        for (int row = 5; row>-1; row--) {
            for (int col = 0; col < 7; col++) {
                assertTrue(board.validMove(row,col)); // check valid move
                assertEquals(player, board.getCurrentPlayer()); // check current player
                board.move(row, col, board.getCurrentPlayer()); // execute move
                assertFalse(board.validMove(row,col));
                player = board.switchPlayer(player); // switch test class player
                assertEquals(player, board.getCurrentPlayer()); // check if internal player switched
            }
        }
    }



}

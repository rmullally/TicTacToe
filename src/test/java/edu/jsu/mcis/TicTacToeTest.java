package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;
import edu.jsu.mcis.TicTacToeGUI.Mark;

public class TicTacToeTest {
	@Test
	public void testInitialBoardIsEmpty() {
		TicTacToeGUI board = new TicTacToeGUI();
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				assertEquals(Mark.EMPTY, board.getMark(row, col));
			}
		}
	}
	
	@Test
	public void testMarkXInUpperRightCorner() {
		TicTacToeGUI board = new TicTacToeGUI();
		board.setMark(0, 2);
		assertEquals(Mark.X_MARK, board.getMark(0, 2));
	}
	
	@Test
	public void testMarkOInBottomLeftCorner() {
		TicTacToeGUI board = new TicTacToeGUI();
		board.setMarkO(2, 0);
		assertEquals(Mark.O_MARK, board.getMark(2, 0));
	}
	
	@Test
	public void testMarkXInMiddleAndOInBottomRightCorner() {
		TicTacToeGUI board = new TicTacToeGUI();
		board.setMark(1, 1);
		board.setMark(2, 2);
		if (board.getMark(1, 1) == Mark.X_MARK) {
			if (board.getMark(2, 2) == Mark.O_MARK) {
				assertTrue(true);
			} else {
				assertEquals(0, 1);
			}
		} else {
			assertEquals(0, 2);
		}
	}
	
	@Test
	public void testIfGameIsWonByXOnTopRowAcross() {
		TicTacToeGUI board = new TicTacToeGUI();
		board.setMarkX(0, 0);
		board.setMarkX(0, 1);
		if (board.checkWinner() != Mark.EMPTY) {
			assertEquals(0, 1);
		}
		board.setMarkX(0, 2);
		assertEquals(Mark.X_MARK, board.checkWinner());
	}
	
	@Test
	public void testIfGameIsWonByOOnMiddleColDown() {
		TicTacToeGUI board = new TicTacToeGUI();
		board.setMarkO(0, 1);
		board.setMarkO(1, 1);
		if (board.checkWinner() != Mark.EMPTY) {
			assertEquals(0, 1);
		}
		board.setMarkO(2, 1);
		assertEquals(Mark.O_MARK, board.checkWinner());
	}
	
	@Test
	public void testIfGameIsWonByXDiagonalTopLeftToBottomRight() {
		TicTacToeGUI board = new TicTacToeGUI();
		board.setMarkX(0, 0);
		board.setMarkX(1, 1);
		if (board.checkWinner() != Mark.EMPTY) {
			assertEquals(0, 1);
		}
		board.setMarkX(2, 2);
		assertEquals(Mark.X_MARK, board.checkWinner());
	}
    
    @Test
	public void testIfGameIsWonByODiagonalTopRightToBottomLeft() {
		TicTacToeGUI board = new TicTacToeGUI();
		board.setMarkO(0, 2);
		board.setMarkO(1, 1);
		if (board.checkWinner() != Mark.EMPTY) {
			assertEquals(0, 1);
		}
		board.setMarkO(2, 0);
		assertEquals(Mark.O_MARK, board.checkWinner());
	}
    
    @Test
    public void testIfMarkingInSameSpotTwiceWorksAsItShould() {
        TicTacToeGUI board = new TicTacToeGUI();
        board.setMark(0,0);
        board.setMark(1,1);
        board.setMark(1,1);
        assertEquals(Mark.X_MARK, board.getTurn());
    }
	
	@Test
	public void testIfGameIsTiedAndAllSpacesAreBeingUsed() {
		TicTacToeGUI board = new TicTacToeGUI();
		board.setMark(0, 0);
		board.setMark(0, 2);
		board.setMark(0, 1);
		if (board.checkWinner() != Mark.EMPTY) {
			assertEquals(0, 1);
		}
		board.setMark(1, 0);
		board.setMark(1, 2);
		board.setMark(1, 1);
		if (board.checkWinner() != Mark.EMPTY) {
			assertEquals(0, 2);
		}
		board.setMark(2, 0);
		board.setMark(2, 2);
		board.setMark(2, 1);
		assertEquals(Mark.DRAW, board.checkWinner());
	}
	
	@Test
	public void testIfGameIsWonByXDiagonalTopLeftToBottomRightOnLastPossibleMove() {
		TicTacToeGUI board = new TicTacToeGUI();
		board.setMark(0, 0);
		board.setMark(0, 1);
		board.setMark(0, 2);
		if (board.checkWinner() != Mark.EMPTY) {
			assertEquals(0, 1);
		}
		board.setMark(1, 0);
		board.setMark(1, 1);
		board.setMark(1, 2);
		if (board.checkWinner() != Mark.EMPTY) {
			assertEquals(0, 2);
		}
		board.setMark(2, 1);
		board.setMark(2, 0);
		board.setMark(2, 2);
		assertEquals(Mark.X_MARK, board.checkWinner());
	}
	
	@Test
	public void testGettingMarkInTopLeftAndReturningAsAString() {
		TicTacToeGUI board = new TicTacToeGUI();
		board.setMark(0, 0);
		assertEquals("X", board.getMarkInString(0, 0));
	}
    
    @Test
	public void testGettingMarkInBottomLeftAndReturningAsAString() {
		TicTacToeGUI board = new TicTacToeGUI();
		board.setMarkO(2, 0);
		assertEquals("O", board.getMarkInString(2, 0));
	}
    
    @Test
	public void testGettingMarkThatShouldBeEmptyInMiddleAndReturningAsAString() {
		TicTacToeGUI board = new TicTacToeGUI();
		board.setMark(0, 0);
        board.setMark(2, 2);
		assertEquals(" ", board.getMarkInString(1, 1));
	}
}
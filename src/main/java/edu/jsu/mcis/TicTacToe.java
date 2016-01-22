//package edu.jsu.mcis;

import java.util.Scanner;

public class TicTacToe {
	public final int EMPTY = 0;
	public final int X_MARK = 1;
	public final int O_MARK = 2;
	
	public final int TOP_ROW = 0;
	public final int MIDDLE_ROW = 1;
	public final int BOTTOM_ROW = 2;
	
	public final int LEFT_COL = 0;
	public final int MIDDLE_COL = 1;
	public final int RIGHT_COL = 2;
	
	private int[][] gameBoard = new int[3][3];
	private int turn = X_MARK;
	
	public TicTacToe() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				gameBoard[i][j] = EMPTY;
			}
		}
	}
	
	public void displayBoard() {
		System.out.println();
		printTopRow();
		displayLine();
		printMiddleRow();
		displayLine();
		printBottomRow();
		System.out.println();
	}
	
	public void displayResult() {
		if (whoWon() == X_MARK) {
			System.out.println("X is the winner");
		} else if (whoWon() == O_MARK) {
			System.out.println("O is the winner");
		} else if (draw()) {
			System.out.println("The game is a draw");
		} else {
			System.out.println("No result");
		}
		
		displayBoard();
	}
	
	public void printTopRow() {
		System.out.println("   " + markString(TOP_ROW, LEFT_COL) +"|"+ markString(TOP_ROW, MIDDLE_COL) +"|" + markString(TOP_ROW, RIGHT_COL));
	}
	
	public void printMiddleRow() {
		System.out.println("   " + markString(MIDDLE_ROW, LEFT_COL) +"|"+ markString(MIDDLE_ROW, MIDDLE_COL) +"|" + markString(MIDDLE_ROW, RIGHT_COL));
	}
	
	public void printBottomRow() {
		System.out.println("   " + markString(BOTTOM_ROW, LEFT_COL) +"|"+ markString(BOTTOM_ROW, MIDDLE_COL) +"|" + markString(BOTTOM_ROW, RIGHT_COL));
	}
	
	public void displayLine() {
		System.out.println("   -----");
	}
	
	public String markString(int row, int col) {
		if (getMark(row, col) == X_MARK) {
			return "X";
		} else if (getMark(row, col) == O_MARK) {
			return "O";
		} else if (getMark(row, col) == EMPTY) {
			return " ";
		} else {
			return "BROKEN";
		}
	}
	
	public int enterRow() {
		Scanner keyboard = new Scanner(System.in);
		
		if (turn == X_MARK) {
			System.out.println("");
			System.out.println("X enter a row (0-2)");
			return keyboard.nextInt();
		} else if (turn == O_MARK) {
			System.out.println("");
			System.out.println("O enter a row (0-2)");
			return keyboard.nextInt();
		} else {
			System.out.println("");
			return -1;
		}
	}
	
	public int enterCol() {
		Scanner keyboard = new Scanner(System.in);
		
		if (turn == X_MARK) {
			System.out.println("");
			System.out.println("X enter a col (0-2)");
			return keyboard.nextInt();
		} else if (turn == O_MARK) {
			System.out.println("");
			System.out.println("O enter a col (0-2)");
			return keyboard.nextInt();
		} else {
			System.out.println("");
			return -1;
		}
	}
	
	public int getMark(int row, int col) {
		return gameBoard[row][col];
	}
	
	public void setMark(int row, int col) {
		if (gameBoard[row][col] != EMPTY) {
			System.out.println("Space is taken.");
		}else if (turn == X_MARK) {
			gameBoard[row][col] = X_MARK;
			turn = O_MARK;
		} else if (turn == O_MARK) {
			gameBoard[row][col] = O_MARK;
			turn = X_MARK;
		}
		displayBoard();
	}
	
	public void setMarkX(int row, int col) {
		gameBoard[row][col] = X_MARK;
	}
	
	public void setMarkO(int row, int col) {
		gameBoard[row][col] = O_MARK;
	}
	
	public int whoWon() {
		for(int row = 0; row < 3; row++) {
			if (rowWin(row)) {
				return gameBoard[row][LEFT_COL];
			}
		}
		
		for (int col = 0; col < 3; col++) {
			if (colWin(col)) {
				return gameBoard[TOP_ROW][col];
			}
		}

		if (diagnolWin()) {
			return gameBoard[MIDDLE_ROW][MIDDLE_COL];
		}		
		return EMPTY;
	}
	
	private boolean rowWin(int row) {
		if(gameBoard[row][LEFT_COL] == gameBoard[row][MIDDLE_COL] && gameBoard[row][MIDDLE_COL] == gameBoard[row][RIGHT_COL] && gameBoard[row][LEFT_COL] != EMPTY) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean colWin(int col) {
		if(gameBoard[TOP_ROW][col] == gameBoard[MIDDLE_ROW][col] && gameBoard[MIDDLE_ROW][col] == gameBoard[BOTTOM_ROW][col] && gameBoard[TOP_ROW][col] != EMPTY) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean diagnolWin() {
		if (gameBoard[TOP_ROW][LEFT_COL] == gameBoard[MIDDLE_ROW][MIDDLE_COL] && 
		gameBoard[MIDDLE_ROW][MIDDLE_COL] == gameBoard[BOTTOM_ROW][RIGHT_COL] && 
		gameBoard[BOTTOM_ROW][RIGHT_COL] != EMPTY) {
			return true;
		}else if (gameBoard[TOP_ROW][RIGHT_COL] == gameBoard[MIDDLE_ROW][MIDDLE_COL] &&
		gameBoard[MIDDLE_ROW][MIDDLE_COL] == gameBoard[BOTTOM_ROW][LEFT_COL] &&
		gameBoard[BOTTOM_ROW][LEFT_COL] != EMPTY) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean draw() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (gameBoard[row][col] == EMPTY) {
					return false;
				}
			}
		}
		if (whoWon() == X_MARK) {
			return false;
		} else if (whoWon() == O_MARK) {
			return false;
		} else {
			return true;
		}
		
	}
	
	public static void main(String[] args) {
		
		TicTacToe board = new TicTacToe();
		
		Scanner keyboard = new Scanner(System.in);
		
		int rowInput;
		int colInput;
		
		System.out.println("TicTacToe");
		
		while (board.whoWon() == board.EMPTY && !board.draw()) {
			rowInput = -1;
			colInput = -1;
			while (rowInput > 2 || rowInput < 0) {
				rowInput = board.enterRow();
			}
			while (colInput > 2 || colInput < 0) {
				colInput = board.enterCol();
			}
			board.setMark(rowInput, colInput);
		}
		board.displayResult();
	}
}
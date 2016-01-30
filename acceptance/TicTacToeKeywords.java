import edu.jsu.mcis.*;

public class TicTacToeKeywords {
	private TicTacToeGUI board = new TicTacToeGUI();
	
	public void startNewGame() {
		board = new TicTacToeGUI();
	}
	
	public void markLocation(int row, int col) {
		board.setMark(row, col);
	}
	
	public String getMark(int row, int col) {
		if (board.getMark(row, col) == board.X_MARK) {
			return "X";
		} else if (board.getMark(row, col) == board.O_MARK) {
			return "O";
		} else {
			return "EMPTY";
		}
	}
    

	
	public String getWinner() {
		if (board.whoWon() == board.X_MARK) {
			return "X";
		} else if (board.whoWon() == board.O_MARK) {
			return "O";
		} else if (board.draw()) {
			return "DRAW";
		}else {
			return "EMPTY";
		}
	}
}
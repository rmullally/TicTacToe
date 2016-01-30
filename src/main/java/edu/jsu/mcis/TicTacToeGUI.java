package edu.jsu.mcis;

import java.util.Scanner;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.Font;


public class TicTacToeGUI {
    
	
    public enum Mark {EMPTY, X_MARK, O_MARK, DRAW};
	
	public final int ROWS = 0;
	public final int COLS = 0;
	
	private Mark[][] gameBoard = new Mark[3][3];
    
    private JButton[][] tiles = new JButton[3][3];
	
	private Mark turn = Mark.X_MARK;
	
	public TicTacToeGUI() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				gameBoard[i][j] = Mark.EMPTY;
			}
		}
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tiles[i][j] = new JButton("");
                tiles[i][j].addActionListener(new ButtonListener(i, j));
                tiles[i][j].setFont(new Font("Times New Roman", Font.PLAIN, 35));
                tiles[i][j].setName("Location" + i + j);
            }
        }
	}
	
	public static void main(String[] args) {
        
        JFrame window = new JFrame("TicTacToe");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new GridLayout(3,3));
        window.setSize(600, 600);
        TicTacToeGUI board = new TicTacToeGUI(); 
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                window.add(board.tiles[i][j]);
            }
        }
        window.setVisible(true);	
	}
    
    private class ButtonListener implements ActionListener {
        private int row;
        private int col;
        
        public ButtonListener(int i, int j) {
            row = i;
            col = j;
        }
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            buttonClicked(row, col);
        }
    }
    
    private void buttonClicked(int row, int col) {
        setMark(row, col);
        setButtonLabel(row, col);
        if (checkWinner() == Mark.X_MARK) {
            int delay = 50;
            ActionListener taskPerformer = new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                JOptionPane.showMessageDialog(null, "X Wins!!", "Game Over", JOptionPane.PLAIN_MESSAGE);
                resetGame();
                }
            };
            Timer myTimer = new Timer(delay, taskPerformer);
            myTimer.setRepeats(false);
            myTimer.start();
        } else if (checkWinner() == Mark.O_MARK) {
            int delay = 50;
            ActionListener taskPerformer = new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                JOptionPane.showMessageDialog(null, "O Wins!!", "Game Over", JOptionPane.PLAIN_MESSAGE);
                resetGame();
                }
            };
            Timer gameTimer = new Timer(delay, taskPerformer);
            gameTimer.setRepeats(false);
            gameTimer.start();
        } else if (checkWinner() == Mark.DRAW) {
            int delay = 75;
            ActionListener taskPerformer = new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                JOptionPane.showMessageDialog(null, "This game is a draw!!", "Game Over", JOptionPane.PLAIN_MESSAGE);
                resetGame();
                }
            };
            Timer gameTimer = new Timer(delay, taskPerformer);
            gameTimer.setRepeats(false);
            gameTimer.start();
        } else if (checkWinner() == Mark.EMPTY) {
            
        }
    }
    
    private void setButtonLabel(int row, int col) {
        tiles[row][col].setText(getMarkInString(row, col));
    }
	
    public Mark getTurn() {
        return turn;
    }
    
    public void resetGame() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                gameBoard[row][col] = Mark.EMPTY;
                setButtonLabel(row, col);
            }
        }
        turn = Mark.X_MARK;
    }
	
	public String getMarkInString(int row, int col) {
		if (getMark(row, col) == Mark.X_MARK) {
			return "X";
		} else if (getMark(row, col) == Mark.O_MARK) {
			return "O";
		} else if (getMark(row, col) == Mark.EMPTY) {
			return " ";
		} else {
			return "NONE";
		}
	}
	
	public Mark getMark(int row, int col) {
		return gameBoard[row][col];
	}
	
	public void setMark(int row, int col) {
		if (gameBoard[row][col] != Mark.EMPTY) {
            
		}else if (turn == Mark.X_MARK) {
			gameBoard[row][col] = Mark.X_MARK;
			turn = Mark.O_MARK;
		} else if (turn == Mark.O_MARK) {
			gameBoard[row][col] = Mark.O_MARK;
			turn = Mark.X_MARK;
		}
	}
	
	public void setMarkX(int row, int col) {
		gameBoard[row][col] = Mark.X_MARK;
	}
	
	public void setMarkO(int row, int col) {
		gameBoard[row][col] = Mark.O_MARK;
	}
	
	public Mark checkWinner() {
		for(int row = 0; row < 3; row++) {
			if (rowWin(row)) {
				return gameBoard[row][0];
			}
		}
		
		for (int col = 0; col < 3; col++) {
			if (colWin(col)) {
				return gameBoard[0][col];
			}
		}

		if (diagnolWin()) {
			return gameBoard[1][1];
		}
        
        if (draw()) {
            return Mark.DRAW;
        }
        
        return Mark.EMPTY;

	}
	
	private boolean rowWin(int row) {
		if(gameBoard[row][0] == gameBoard[row][1] && gameBoard[row][1] == gameBoard[row][2] && gameBoard[row][0] != Mark.EMPTY) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean colWin(int col) {
		if(gameBoard[0][col] == gameBoard[1][col] && gameBoard[1][col] == gameBoard[2][col] && gameBoard[0][col] != Mark.EMPTY) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean diagnolWin() {
		if (gameBoard[0][0] == gameBoard[1][1] && 
		gameBoard[1][1] == gameBoard[2][2] && 
		gameBoard[2][2] != Mark.EMPTY) {
			return true;
		}else if (gameBoard[0][2] == gameBoard[1][1] &&
		gameBoard[1][1] == gameBoard[2][0] &&
		gameBoard[2][0] != Mark.EMPTY) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean draw() {
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (gameBoard[row][col] == Mark.EMPTY) {
					return false;
				}
			}
		}
		return true;
	}
}
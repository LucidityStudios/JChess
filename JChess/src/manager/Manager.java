package manager;

import board.Board;

public class Manager {

	private Board board;
	
	public Manager() {
		board = new Board();
	}
	
	public Board getBoard() {
		return board;
	}
	
}

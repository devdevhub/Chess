package chess;

import chess.pieces.ChessPiece;

public class Coordinates {

	private int x = 0, y = 0;
	static char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
	
	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Coordinates(char xLetter, int y) {
		for (int i = 0; i < letters.length; i++) {
			if (letters[i] == xLetter) {
				this.x = (i+1);
			}
		}
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public char getXLetter() {
		return letters[getX()-1];
	}
	
	public int getY() {
		return this.y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public ChessPiece getPiece() {
		return isOutOfBounds() ? null : Board.gameGrid[getY()-1][getX()-1];
	}

	public boolean isOutOfBounds() {
		return (
			this.x > 8 ||
			this.x < 1 ||
			this.y > 8 ||
			this.y < 1
		);
	}
	
	public boolean equals(Coordinates tile) {
		return (this.x == tile.getX() && this.y == tile.getY());
	}
	
}

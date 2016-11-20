package chess.pieces;

import java.util.ArrayList;

import chess.Board;
//import chess.Chess;
import chess.Coordinates;

public class King extends ChessPiece {

	public King(Coordinates tile, chess.Color color) {
		super(tile, color);
	}

	@Override
	protected ArrayList<Coordinates> getMoves() {
		ArrayList<Coordinates> moves = new ArrayList<Coordinates>();
		moves.add(new Coordinates(getX()-1, getY()+1));
		moves.add(new Coordinates(getX(), getY()+1));
		moves.add(new Coordinates(getX()+1, getY()+1));
		moves.add(new Coordinates(getX()-1, getY()));
		moves.add(new Coordinates(getX()+1, getY()));
		moves.add(new Coordinates(getX()-1, getY()-1));
		moves.add(new Coordinates(getX(), getY()-1));
		moves.add(new Coordinates(getX()+1, getY()-1));
		return moves;
	}

	@Override
	protected boolean isBlocked(Coordinates tile) {
		return false;
	}
	
	public boolean isChecked() {
		for (ChessPiece[] row : Board.gameGrid) {
			for (ChessPiece piece : row) {
				if (piece != null && piece.getColor() != this.getColor()) {
					for (Coordinates pieceOption : piece.getRealOptions()) {
						if (this.getTile().equals(pieceOption)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

}

package chess.pieces;

import java.util.ArrayList;

import chess.Coordinates;

public class Knight extends ChessPiece {

	public Knight(Coordinates tile, chess.Color color) {
		super(tile, color);
	}

	@Override
	protected ArrayList<Coordinates> getMoves() {
		ArrayList<Coordinates> moves = new ArrayList<Coordinates>();
		moves.add(new Coordinates(getX()-2, getY()+1));
		moves.add(new Coordinates(getX()-1, getY()+2));
		moves.add(new Coordinates(getX()+1, getY()+2));
		moves.add(new Coordinates(getX()+2, getY()+1));
		moves.add(new Coordinates(getX()-2, getY()-1));
		moves.add(new Coordinates(getX()-1, getY()-2));
		moves.add(new Coordinates(getX()+1, getY()-2));
		moves.add(new Coordinates(getX()+2, getY()-1));
		return moves;
	}

	@Override
	protected boolean isBlocked(Coordinates tile) {
		return false;
	}

}

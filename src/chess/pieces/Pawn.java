package chess.pieces;

import java.util.ArrayList;

import chess.Coordinates;

public class Pawn extends ChessPiece {

	public Pawn(Coordinates tile, chess.Color color) {
		super(tile, color);
	}

	@Override
	protected ArrayList<Coordinates> getMoves() {
		ArrayList<Coordinates> moves = new ArrayList<Coordinates>();
		moves.add(new Coordinates(getX(), getY()-(this.getColor().getId()*2)+1));
		moves.add(new Coordinates(getX(), getY()-(this.getColor().getId()*4)+2));
		moves.add(new Coordinates(getX()-1, getY()-(this.getColor().getId()*2)+1));
		moves.add(new Coordinates(getX()+1, getY()-(this.getColor().getId()*2)+1));
		return moves;
	}

	@Override
	protected boolean isBlocked(Coordinates tile) {
		return (
			(tile.equals(this.getMoves().get(0)) && tile.getPiece() != null)
			|| (tile.equals(this.getMoves().get(1))
				&& (this.getY()-this.getColor().getId()*5 != 2
					|| tile.getPiece() != null
					|| this.getMoves().get(0).getPiece() != null
				)
			)
			|| (tile.equals(this.getMoves().get(2)) && tile.getPiece() == null)
			|| (tile.equals(this.getMoves().get(3)) && tile.getPiece() == null)
		);
	}

}

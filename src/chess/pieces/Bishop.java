package chess.pieces;

import java.util.ArrayList;

import chess.Board;
import chess.Coordinates;

public class Bishop extends ChessPiece {

	public Bishop(Coordinates tile, chess.Color color) {
		super(tile, color);
	}

	@Override
	protected ArrayList<Coordinates> getMoves() {
		ArrayList<Coordinates> moves = new ArrayList<Coordinates>();
		for (int iY = 1; iY <= 8; iY++) {
			for (int iX = 1; iX <= 8; iX++) {
				if ((getX()-iX == getY()-iY)
				|| (getX()-iX == iY-getY())) {
				// coordinates are on one of the diagonals
					moves.add(new Coordinates(iX, iY));
				}
			}
		}
		return moves;
	}

	@Override
	protected boolean isBlocked(Coordinates tile) {
		boolean foundBlockingPiece = false;
		gameGridLoop: for (ChessPiece[] row : Board.gameGrid) {
			for (ChessPiece piece : row) {
				if (piece != null) {
					if ((this.getX()-piece.getX() == this.getY()-piece.getY())
					|| (this.getX()-piece.getX() == piece.getY()-this.getY())) {
					// piece is on one of the diagonals
						if (((tile.getX() < piece.getX() && piece.getX() < this.getX())
							&& (this.getY() < piece.getY() && piece.getY() < tile.getY()))
							// piece is blocking top-left
						|| ((this.getX() < piece.getX() && piece.getX() < tile.getX())
							&& (this.getY() < piece.getY() && piece.getY() < tile.getY()))
							// piece is blocking top-right
						|| ((tile.getX() < piece.getX() && piece.getX() < this.getX())
							&& (tile.getY() < piece.getY() && piece.getY() < this.getY()))
							// piece is blocking bottom-left
						|| ((this.getX() < piece.getX() && piece.getX() < tile.getX())
							&& (tile.getY() < piece.getY() && piece.getY() < this.getY()))) {
							// piece is blocking bottom-right
						
							foundBlockingPiece = true;
							break gameGridLoop;
						}
					}
				}
			}
		}
		return foundBlockingPiece;
	}

}

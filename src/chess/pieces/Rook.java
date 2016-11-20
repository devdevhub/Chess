package chess.pieces;

import java.util.ArrayList;

import chess.Board;
import chess.Coordinates;

public class Rook extends ChessPiece {

	public Rook(Coordinates tile, chess.Color color) {
		super(tile, color);
	}

	@Override
	protected ArrayList<Coordinates> getMoves() {
		ArrayList<Coordinates> moves = new ArrayList<Coordinates>();
		for (int iY = 1; iY <= 8; iY++) {
			for (int iX = 1; iX <= 8; iX++) {
				if (iX == getX() || iY == getY()) {
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
					if (tile.getY() == piece.getY() && piece.getY() == this.getY()) {
					// all three in the same ROW
						if ((tile.getX() < piece.getX() && piece.getX() < this.getX())
						|| (this.getX() < piece.getX() && piece.getX() < tile.getX())) {
							foundBlockingPiece = true;
							break gameGridLoop;
						}
					}
					else if (tile.getX() == piece.getX() && piece.getX() == this.getX()) {
					// all three in the same COLUMN
						if ((this.getY() < piece.getY() && piece.getY() < tile.getY())
						|| (tile.getY() < piece.getY() && piece.getY() < this.getY())) {
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

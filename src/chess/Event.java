package chess;

import chess.pieces.ChessPiece;

public class Event {
	
	private Coordinates tileA, tileB;
	private ChessPiece pieceA, pieceB;
	
	public Event(Coordinates tileA, Coordinates tileB) {
		this.tileA = new Coordinates(tileA.getX(), tileA.getY());
		this.tileB = new Coordinates(tileB.getX(), tileB.getY());
		this.pieceA = tileA.getPiece();
		this.pieceB = tileB.getPiece();
	}

	public Coordinates getTileA() {
		return tileA;
	}

	public Coordinates getTileB() {
		return tileB;
	}

	public ChessPiece getPieceA() {
		return pieceA;
	}

	public ChessPiece getPieceB() {
		return pieceB;
	}
	
}
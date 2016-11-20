package chess;

import chess.pieces.ChessPiece;

public class Console {

	static Coordinates lastTileLogged = null;
	
	public static void logStats() {
		if (Settings.logAllPiecesInfo) {
			for (ChessPiece[] row : Board.gameGrid) {
				for (ChessPiece piece : row) {
					if (piece != null) {
						logTile(piece.getTile());
					}
				}
			}
		}
	}
	
	public static void logTile(Coordinates tile) {
		if (!tile.isOutOfBounds()
		&& (lastTileLogged == null || !tile.equals(lastTileLogged))) {
			if (Settings.logEmptyTiles && tile.getPiece() == null) {
				System.out.printf("%n %12s ("+tile.getXLetter()+", "+tile.getY()+")%n", "Empty Tile");
				lastTileLogged = tile;
			}
			else if (tile.getPiece() != null) {
				
				String name = tile.getPiece().getColor().getName()+" "+tile.getPiece().getClass().getSimpleName();
				
				if ((Settings.logCurrentPlayerPieces && tile.getPiece().getColor() == Chess.getCurrentColor())
						|| Settings.logOtherPlayerPieces && tile.getPiece().getColor() != Chess.getCurrentColor()) {
					
					System.out.printf("%n %12s ("+tile.getXLetter()+", "+tile.getY()+")", name);
					
					if (Settings.logPlayerPieceOptions && tile.getPiece().getColor() == Chess.getCurrentColor()
							|| Settings.logEnemyPieceOptions && tile.getPiece().getColor() != Chess.getCurrentColor()) {
						
						System.out.println(" options:");
						for (Coordinates option : tile.getPiece().getRealOptions()) {
							System.out.printf("%28s%n", "("+option.getXLetter()+", "+option.getY()+")");
						}
						
					}
					else System.out.println();
					
					lastTileLogged = tile;
					
				}
			}
		}
	}
	
	public static void logTurn() {
		if (Settings.logTurn) {
			System.out.println("\nTURN "+Chess.turn+" ~ "+Chess.getCurrentColor().getName());
		}
	}

	public static void logMove(Coordinates tile) {
		if (Settings.logMoves) {
			System.out.printf(" %12s ("+tile.getXLetter()+", "+tile.getY()+")%n%n", "Moved to");
		}
	}

}

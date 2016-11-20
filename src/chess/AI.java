package chess;

import java.util.ArrayList;

import chess.pieces.ChessPiece;

public class AI {
	
	static void movePiece() {
		if (Settings.enableAi && Chess.getCurrentColor() == Color.ENEMY) {
			ArrayList<ChessPiece> pieces = new ArrayList<ChessPiece>();
			for (ChessPiece[] row : Board.gameGrid) {
				for (ChessPiece piece : row) {
					if (piece != null && piece.getColor() == Color.ENEMY) {
						pieces.add(piece);
					}
				}
			}
			int iPiece = 0, pieceRandom = (int)(Math.random()*pieces.size());
			piecesLoop: for (final ChessPiece piece : pieces) {
				if (iPiece == pieceRandom) {
					int iOption = 0, optionRandom = (int)(Math.random()*piece.getRealOptions().size());
					for (final Coordinates optionTile : piece.getRealOptions()) {
						if (iOption == optionRandom) {
//							try {
//								new Thread(new Runnable() {
//									@Override
//									public void run() {
//										try {
//											Thread.sleep(1000);
//										} catch (InterruptedException e) {
//											e.printStackTrace();
//										}
//										piece.moveTo(optionTile, true);
//										Chess.turn++;
//										Console.logTurn();
//									}
//								}).run();
//								test.wait(1000);
////								test.run();
//								piece.moveTo(optionTile, true);
//								Chess.turn++;
//								Console.logTurn();
//							} catch (InterruptedException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//							break piecesLoop;
						
//							try {
//								Thread.sleep(1000);
//							} catch (InterruptedException e) {
//								e.printStackTrace();
//							}
							piece.moveTo(optionTile, true);
							Chess.turn++;
							Console.logTurn();
							break piecesLoop;
						}
						iOption++;
					}
				}
				iPiece++;
			}
			Chess.FRAME.repaint();
		}
	}

}

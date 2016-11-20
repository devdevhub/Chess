package chess;

import java.awt.event.MouseEvent;

import chess.pieces.ChessPiece;

public class Mouse {
	
//	static Coordinates tileA = null, tileB = null;
	static boolean isDraggingPiece = false;
	static Coordinates tileSelected = null;

//	static void onClick(MouseEvent e) {
//		
//	}
	
	static void onPress(MouseEvent e) {
		Coordinates tilePressed = getMouseTile(e);
		if (tilePressed.getPiece() != null && tilePressed.getPiece().getColor() == Chess.getCurrentColor()) {
//			tileA = tilePressed;
			tileSelected = tilePressed;
		}
//		else {
//			tileSelected = null;
//		}
		Console.logTile(tilePressed);
	}
	
	static void onDrag(MouseEvent e) {
		if (tileSelected != null) {
			Coordinates tileHovering = getMouseTile(e);
			ChessPiece piece = tileSelected.getPiece();
			boolean isHoveringOption = false;
			for (Coordinates optionTile : piece.getRealOptions()) {
				if (tileHovering.equals(optionTile)) {
					isHoveringOption = true;
					break;
				}
			}
			if (isHoveringOption) {
			// hovered tile is a valid option for preselected piece
				isDraggingPiece = true;
				piece.setHoverTile(new Coordinates(getMouseTile(e).getX(), getMouseTile(e).getY()));
//				piece.moveTo(tileHovering, false);
//				Console.logMove(tileHovering);
				
//				Chess.FRAME.repaint();
			}
		}
		
		
		
//		if (!tileHovering.isOutOfBounds()) {
//		// hovering tile is NOT out of bounds
//			if (tileSelected != null) {
//			// some tile was already selected
//				if (!tileHovering.equals(tileSelected)) {
//				// clicked tile is the same as preselected tile
//					tileSelected = null;
//				}
//				else {
//				// clicked tile is NOT the same as preselected tile
//					ChessPiece piece = tileSelected.getPiece();
//					boolean optionSelected = false;
//					for (Coordinates optionTile : piece.getRealOptions()) {
//						if (tileClicked.equals(optionTile)) {
//							optionSelected = true;
//							break;
//						}
//					}
//					if (optionSelected) {
//					// clicked tile is a valid option for preselected piece
////						if (e.getID() == MouseEvent.MOUSE_DRAGGED) {
////							piece.getTile().setX(getTileClicked(e).getX());
////							piece.getTile().setY(getTileClicked(e).getY());
////							Chess.FRAME.repaint();
////						}
////						else {
//							piece.moveTo(tileClicked, true);
//							Console.logMove(tileClicked);
////							System.out.println("history.size() "+history.size());
//							tileSelected = null;
//							Chess.turn++;
//							Console.logTurn();
////						}
//					}
//					else {
//					// clicked tile is NOT a valid option for preselected piece
//						if (tileClicked.getPiece() == null
//						|| tileClicked.getPiece().getColor() != Chess.getCurrentColor()) {
//						// clicked tile does NOT contain a piece
//						// OR clicked tile contains ENEMY piece
//							tileSelected = null;
////							if (e.getID() == MouseEvent.MOUSE_CLICKED
////							&& (lastTileLogged == null || !tileClicked.equals(lastTileLogged))) {
////								Console.logTile(tileClicked);
////								lastTileLogged = tileClicked;
////							}
//						}
//						else {
//						// clicked tile contains a FRIENDLY piece
//							updateTileSelection(tileClicked);
//						}
//					}
//				}
//			}
//			else if (tileClicked.getPiece() != null) {
//			// NO tile was preselected
//			// AND clicked tile contains piece
//				if (tileClicked.getPiece().getColor() == Chess.getCurrentColor()) {
//				// piece has the right color
//					updateTileSelection(tileClicked);
//				}
//				else if (e.getID() == MouseEvent.MOUSE_CLICKED
//				&& (Board.lastTileLogged == null || !tileClicked.equals(Board.lastTileLogged))) {
//					Console.logTile(tileClicked);
////					lastTileLogged = tileClicked;
//				}
//			}
//			else {
//			// NO tile was preselected
//			// AND clicked tile contains NO piece
//				if (e.getID() == MouseEvent.MOUSE_CLICKED
//				&& (Board.lastTileLogged == null || !tileClicked.equals(Board.lastTileLogged))) {
//					Console.logTile(tileClicked);
////					lastTileLogged = tileClicked;
//				}
//			}
//		}
	}
	
	static void onRelease(MouseEvent e) {
		if (isDraggingPiece) {
			isDraggingPiece = false;
			Coordinates tileReleased = getMouseTile(e);
			if (tileReleased.isOutOfBounds()) {
			// release tile is out of bounds
				tileSelected = null;
			}
			else {
			// release tile is NOT out of bounds
				ChessPiece piece = tileSelected.getPiece();
				boolean optionSelected = false;
				for (Coordinates optionTile : piece.getRealOptions()) {
					if (tileReleased.equals(optionTile)) {
						optionSelected = true;
						break;
					}
				}
				if (optionSelected) {
				// release tile is a valid option for preselected piece
					// move to release tile
					piece.moveTo(tileReleased, true);
					Console.logMove(tileReleased);
					tileSelected = null;
					Chess.turn++;
					Console.logTurn();
				}
				else {
				// clicked tile is NOT a valid option for preselected piece
					// move to last hovered valid tile
					piece.moveTo(piece.getHoverTile(), true);
					Console.logMove(tileReleased);
					tileSelected = null;
					Chess.turn++;
					Console.logTurn();
				}
				piece.setHoverTile(null);
			}
		}
//		tileSelected = null;
////		onClick(e);
//			if (tileSelected != null) {
//			// some tile was already selected
//				
//			}
//			else if (tileReleased.getPiece() != null) {
//			// NO tile was preselected
//			// AND clicked tile contains piece
//				if (tileReleased.getPiece().getColor() == Chess.getCurrentColor()) {
//				// piece has the right color
//					updateTileSelection(tileReleased);
//				}
//				else if (e.getID() == MouseEvent.MOUSE_CLICKED
//				&& (Board.lastTileLogged == null || !tileReleased.equals(Board.lastTileLogged))) {
//					Console.logTile(tileReleased);
////					lastTileLogged = tileClicked;
//				}
//			}
//			else {
//			// NO tile was preselected
//			// AND clicked tile contains NO piece
//				if (e.getID() == MouseEvent.MOUSE_CLICKED
//				&& (Board.lastTileLogged == null || !tileReleased.equals(Board.lastTileLogged))) {
//					Console.logTile(tileReleased);
////					lastTileLogged = tileClicked;
//				}
//			}
//		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	static void onClick(MouseEvent e) {
		Coordinates tileClicked = getMouseTile(e);
		if (tileClicked.isOutOfBounds()) {
		// clicked tile is out of bounds
			tileSelected = null;
		}
		else {
		// clicked tile is NOT out of bounds
			if (tileSelected != null) {
			// some tile was already selected
//				if (tileClicked.equals(tileSelected)) {
//				// clicked tile is the same as preselected tile
////					tileSelected = null;
//				}
//				else {
				// clicked tile is NOT the same as preselected tile
					ChessPiece piece = tileSelected.getPiece();
					boolean optionSelected = false;
					for (Coordinates optionTile : piece.getRealOptions()) {
						if (tileClicked.equals(optionTile)) {
							optionSelected = true;
							break;
						}
					}
					if (optionSelected) {
					// clicked tile is a valid option for preselected piece
//						if (e.getID() == MouseEvent.MOUSE_DRAGGED) {
//							piece.getTile().setX(getTileClicked(e).getX());
//							piece.getTile().setY(getTileClicked(e).getY());
//							Chess.FRAME.repaint();
//						}
//						else {
							piece.moveTo(tileClicked, true);
							Console.logMove(tileClicked);
//							System.out.println("history.size() "+history.size());
							tileSelected = null;
							Chess.turn++;
							Console.logTurn();
//						}
					}
					else {
					// clicked tile is NOT a valid option for preselected piece
						if (tileClicked.getPiece() == null
						|| tileClicked.getPiece().getColor() != Chess.getCurrentColor()) {
						// clicked tile does NOT contain a piece
						// OR clicked tile contains ENEMY piece
							tileSelected = null;
//							if (e.getID() == MouseEvent.MOUSE_CLICKED
//							&& (lastTileLogged == null || !tileClicked.equals(lastTileLogged))) {
//								Console.logTile(tileClicked);
//								lastTileLogged = tileClicked;
//							}
						}
						else {
						// clicked tile contains a FRIENDLY piece
							updateTileSelection(tileClicked);
						}
					}
				}
//			}
//			else if (tileClicked.getPiece() != null) {
//			// NO tile was preselected
//			// AND clicked tile contains piece
//				if (tileClicked.getPiece().getColor() == Chess.getCurrentColor()) {
//				// piece has the right color
//					updateTileSelection(tileClicked);
//				}
//				else if (e.getID() == MouseEvent.MOUSE_CLICKED
//				&& (Console.lastTileLogged == null || !tileClicked.equals(Console.lastTileLogged))) {
//					Console.logTile(tileClicked);
////					lastTileLogged = tileClicked;
//				}
//			}
//			else {
//			// NO tile was preselected
//			// AND clicked tile contains NO piece
//				if (e.getID() == MouseEvent.MOUSE_CLICKED
//				&& (Console.lastTileLogged == null || !tileClicked.equals(Console.lastTileLogged))) {
//					Console.logTile(tileClicked);
////					lastTileLogged = tileClicked;
//				}
//			}
		}
	}
	
	private static Coordinates getMouseTile(MouseEvent e) {
		double x = (e.getPoint().getX())-8;
		double y = (e.getPoint().getY())-31;
		Board.clickPoint.setLocation(x, y);
		
		// debugging
//		System.out.println("\n\nx = "+x+"  |  y = "+y);
//		System.out.println("\n\nx = "+(int)(Math.ceil((x-Board.offsetX)/Board.squareSize))+"  |  y = "+(8-(int)(Math.floor((y-Board.offsetY)/Board.squareSize))));
//		System.out.println("("+xy.getXLetter()+", "+xy.getY()+")");
		
		return new Coordinates(
//			(int)(Math.ceil((x-offsetX)/squareSize)),
//			(8-(int)(Math.floor((y-offsetY)/squareSize)))
			(int)(Math.ceil((x-Board.getOffsetX())/Board.getSquareSize())),
			(8-(int)(Math.floor((y-Board.getOffsetY())/Board.getSquareSize())))
		);
	}
	
	private static void updateTileSelection(Coordinates tileClicked) {
		tileSelected = tileClicked;
//		if (lastTileSelected == null || !tileSelected.equals(lastTileSelected)) {
		if (Console.lastTileLogged == null || !tileSelected.equals(Console.lastTileLogged)) {
			Console.logTile(tileSelected);
//			lastTileLogged = tileSelected;
		}
//		lastTileSelected = tileSelected;
	}
	
}

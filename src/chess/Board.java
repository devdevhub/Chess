package chess;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import chess.pieces.*;

public class Board {

	static Point clickPoint = new Point();
	public static ChessPiece[][] gameGrid = new ChessPiece[8][8];
	public static ArrayList<Event> history = new ArrayList<Event>();
	
	public static int getOffsetX() {
		return (Chess.FRAME.getWidth()-getBoardSize()-17)/2;
	}
	
	public static int getOffsetY() {
		return (Chess.FRAME.getHeight()-getBoardSize()-40)/2;
	}
	
	public static int getSquareSize() {
		return getBoardSize()/8;
	}
	
	public static int getBoardSize() {
		int widthBasedSize = (Chess.FRAME.getWidth()-Settings.initOffsetX*2-17)/8*8;
		int heightBasedSize = (Chess.FRAME.getHeight()-Settings.initOffsetY*2-40)/8*8;
		if (widthBasedSize <= heightBasedSize) {
			return widthBasedSize < 80 ? 80 : widthBasedSize;
		}
		else {
			return heightBasedSize < 80 ? 80 : heightBasedSize;
		}
	}
	
	static void showClick(Graphics g) {
		if (Settings.showClick) {
			g.setColor(Color.red);
			g.drawRect(clickPoint.x, clickPoint.y, 1, 1);
			g.setColor(Color.black);
		}
	}
	
	static void setup() {
		
		// top side - enemy
		new Rook(	new Coordinates('A', 8), chess.Color.ENEMY);
		new Bishop(	new Coordinates('B', 8), chess.Color.ENEMY);
		new Knight(	new Coordinates('C', 8), chess.Color.ENEMY);
		if (chess.Color.ENEMY.getName().equals("White")) {
			new King(	new Coordinates('D', 8), chess.Color.ENEMY);
			new Queen(	new Coordinates('E', 8), chess.Color.ENEMY);
		}
		else {
			new Queen(	new Coordinates('D', 8), chess.Color.ENEMY);
			new King(	new Coordinates('E', 8), chess.Color.ENEMY);
		}
		new Knight(	new Coordinates('F', 8), chess.Color.ENEMY);
		new Bishop(	new Coordinates('G', 8), chess.Color.ENEMY);
		new Rook(	new Coordinates('H', 8), chess.Color.ENEMY);
		new Pawn(	new Coordinates('A', 7), chess.Color.ENEMY);
		new Pawn(	new Coordinates('B', 7), chess.Color.ENEMY);
		new Pawn(	new Coordinates('C', 7), chess.Color.ENEMY);
		new Pawn(	new Coordinates('D', 7), chess.Color.ENEMY);
		new Pawn(	new Coordinates('E', 7), chess.Color.ENEMY);
		new Pawn(	new Coordinates('F', 7), chess.Color.ENEMY);
		new Pawn(	new Coordinates('G', 7), chess.Color.ENEMY);
		new Pawn(	new Coordinates('H', 7), chess.Color.ENEMY);
		
		// bottom side - player
		new Pawn(	new Coordinates('A', 2), chess.Color.PLAYER);
		new Pawn(	new Coordinates('B', 2), chess.Color.PLAYER);
		new Pawn(	new Coordinates('C', 2), chess.Color.PLAYER);
		new Pawn(	new Coordinates('D', 2), chess.Color.PLAYER);
		new Pawn(	new Coordinates('E', 2), chess.Color.PLAYER);
		new Pawn(	new Coordinates('F', 2), chess.Color.PLAYER);
		new Pawn(	new Coordinates('G', 2), chess.Color.PLAYER);
		new Pawn(	new Coordinates('H', 2), chess.Color.PLAYER);
		new Rook(	new Coordinates('A', 1), chess.Color.PLAYER);
		new Bishop(	new Coordinates('B', 1), chess.Color.PLAYER);
		new Knight(	new Coordinates('C', 1), chess.Color.PLAYER);
		if (chess.Color.PLAYER.getName().equals("Black")) {
			new King(	new Coordinates('D', 1), chess.Color.PLAYER);
			new Queen(	new Coordinates('E', 1), chess.Color.PLAYER);
		}
		else {
			new Queen(	new Coordinates('D', 1), chess.Color.PLAYER);
			new King(	new Coordinates('E', 1), chess.Color.PLAYER);
		}
		new Knight(	new Coordinates('F', 1), chess.Color.PLAYER);
		new Bishop(	new Coordinates('G', 1), chess.Color.PLAYER);
		new Rook(	new Coordinates('H', 1), chess.Color.PLAYER);
		
		//debugging
//		new Queen(	new Coordinates('D', 4), chess.Color.player);
//		new Pawn(	new Coordinates('G', 1), chess.Color.player);
	}
	
	static void update(Graphics g) {
		drawBoard(g);
		drawCoordinates(g);
		drawPieces(g);
		if (Mouse.tileSelected != null) {
			Mouse.tileSelected.getPiece().drawRealOptions(g);
			Mouse.tileSelected.getPiece().drawPiece(g);
		}
	}
	
	private static void drawBoard(Graphics g) {
		
		// background
		g.setColor(new Color(255, 206, 158));
//		g.fillRect(offsetX, offsetY, boardSize, boardSize);
		g.fillRect(getOffsetX(), getOffsetY(), getBoardSize(), getBoardSize());
		
		// tiles
		g.setColor(new Color(209, 139, 71));
		for (int iY = 1; iY <= 8; iY++) {
			for (int iX = 1; iX <= 8; iX++) {
				if ((iY+iX) % 2 == 1) {
					g.fillRect(
//						offsetX+squareSize*(iX-1),
//						offsetY+squareSize*(iY-1),
//						squareSize,
//						squareSize
						getOffsetX()+getSquareSize()*(iX-1),
						getOffsetY()+getSquareSize()*(iY-1),
						getSquareSize(),
						getSquareSize()
					);
				}
			}
		}
		
		// outline
		g.setColor(Color.black);
//		g.drawRect(offsetX, offsetY, boardSize, boardSize);
		g.drawRect(getOffsetX(), getOffsetY(), getBoardSize(), getBoardSize());
	}
	
	private static void drawPieces(Graphics g) {
		for (ChessPiece[] row : gameGrid) {
			for (ChessPiece piece : row) {
				if (piece != null) {
					piece.drawPiece(g);
				}
			}
		}
	}
	
	private static void drawCoordinates(Graphics g) {
		Font font = new Font("Courier New", Font.PLAIN, 16);
		g.setFont(font);
		FontMetrics metrics = g.getFontMetrics(font);
		int height = metrics.getMaxAscent()-metrics.getMaxDescent();
		for (int i = 0; i < 2; i++) {
			int x = 0, y = 0;
			for (char letter : Coordinates.letters) {
				int width = metrics.stringWidth(String.valueOf(letter));
				g.drawString(
					String.valueOf(letter),
					getOffsetX() -width/2 +getSquareSize()/2 +x,
					getOffsetY() +height/2 -getSquareSize()/2 +i*9*getSquareSize()
				);
				x += getSquareSize();
			}
			for (int number = 8; number >= 1; number--) {
				int width = metrics.stringWidth(String.valueOf(number));
				g.drawString(
					String.valueOf(number),
					getOffsetX() -width/2 -getSquareSize()/2 +i*9*getSquareSize(),
					getOffsetY() +height/2 +getSquareSize()/2 +y
				);
				y += getSquareSize();
			}
		}
	}
	
//	static void onTileClicked(MouseEvent e) {
//		Coordinates tileClicked = getTileClicked(e);
//		if (tileClicked.isOutOfBounds()) {
//		// clicked tile is out of bounds
//			tileSelected = null;
//		}
//		else {
//		// clicked tile is NOT out of bounds
//			if (tileSelected != null) {
//			// some tile was already selected
//				if (tileClicked.equals(tileSelected)) {
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
//				&& (lastTileLogged == null || !tileClicked.equals(lastTileLogged))) {
//					Console.logTile(tileClicked);
////					lastTileLogged = tileClicked;
//				}
//			}
//			else {
//			// NO tile was preselected
//			// AND clicked tile contains NO piece
//				if (e.getID() == MouseEvent.MOUSE_CLICKED
//				&& (lastTileLogged == null || !tileClicked.equals(lastTileLogged))) {
//					Console.logTile(tileClicked);
////					lastTileLogged = tileClicked;
//				}
//			}
//		}
//	}
//	
//	private static Coordinates getTileClicked(MouseEvent e) {
//		double x = (e.getPoint().getX())-8;
//		double y = (e.getPoint().getY())-31;
//		clickPoint.setLocation(x, y);
//		
//		// debugging
////		System.out.println("\n\nx = "+x+"  |  y = "+y);
////		System.out.println("\n\nx = "+(int)(Math.ceil((x-Board.offsetX)/Board.squareSize))+"  |  y = "+(8-(int)(Math.floor((y-Board.offsetY)/Board.squareSize))));
////		System.out.println("("+xy.getXLetter()+", "+xy.getY()+")");
//		
//		return new Coordinates(
////			(int)(Math.ceil((x-offsetX)/squareSize)),
////			(8-(int)(Math.floor((y-offsetY)/squareSize)))
//			(int)(Math.ceil((x-getOffsetX())/getSquareSize())),
//			(8-(int)(Math.floor((y-getOffsetY())/getSquareSize())))
//		);
//	}
//	
//	private static void updateTileSelection(Coordinates tileClicked) {
//		tileSelected = tileClicked;
////		if (lastTileSelected == null || !tileSelected.equals(lastTileSelected)) {
//		if (lastTileLogged == null || !tileSelected.equals(lastTileLogged)) {
//			Console.logTile(tileSelected);
////			lastTileLogged = tileSelected;
//		}
////		lastTileSelected = tileSelected;
//	}
	
//	static void onPieceDragged(MouseEvent e) {
//		Coordinates tileClicked = getTileClicked(e);
//		if (tileSelected != null) {
//		// some tile was already selected
//			if (!tileClicked.equals(tileSelected)) {
//			// clicked tile is NOT the same as preselected tile
//				ChessPiece piece = tileSelected.getPiece();
//				boolean optionSelected = false;
//				for (Coordinates optionTile : piece.getRealOptions()) {
//					if (tileClicked.equals(optionTile)) {
//						optionSelected = true;
//						break;
//					}
//				}
//				if (optionSelected) {
//				// clicked tile is a valid option for preselected piece
//					piece.moveTo(getTileClicked(e), false);
//				}
//			}
//		}
//	}
	
	public static void undoEvent() {
		if (history.size() > 0) {
			Event lastEvent = history.get(history.size()-1);
//			System.out.println("A ("+lastEvent.getTileA().getXLetter()+", "+lastEvent.getTileA().getY()+")");
//			System.out.println("B ("+lastEvent.getTileB().getXLetter()+", "+lastEvent.getTileB().getY()+")");
			lastEvent.getPieceA().moveTo(lastEvent.getTileA(), false);
			if (lastEvent.getPieceB() != null) {
				lastEvent.getPieceB().moveTo(lastEvent.getTileB(), false);
			}
			history.remove(history.size()-1);
		}
	}
	
}
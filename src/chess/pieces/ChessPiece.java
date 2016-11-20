package chess.pieces;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import chess.Board;
import chess.Chess;
import chess.Coordinates;
import chess.Event;

public abstract class ChessPiece {
	
	private Coordinates tile;
	private chess.Color color;
	private Image image = null;
	private Coordinates hoverTile = null;
	
	public ChessPiece(Coordinates tile, chess.Color color) {
		this.tile = tile;
		this.color = color;
		try {
			String name = getClass().getSimpleName().toLowerCase();
			this.image = ImageIO.read(
//				getClass().getResourceAsStream("/images/"+name+"_"+Chess.COLORS[color].toLowerCase()+".png")
				getClass().getResourceAsStream("/images/"+name+"_"+color.getName().toLowerCase()+".png")
			);
			Board.gameGrid[getY()-1][getX()-1] = this;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void moveTo(Coordinates nextTile, boolean addToHistory) {
		if (addToHistory) {
			Board.history.add(new Event(this.getTile(), nextTile));
		}
		Board.gameGrid[getY()-1][getX()-1] = null;
		this.tile.setX(nextTile.getX());
		this.tile.setY(nextTile.getY());
		Board.gameGrid[getY()-1][getX()-1] = this;
	}
	
	public Coordinates getTile() {
		return this.tile;
	}
	
	public int getX() {
		return this.tile.getX();
	}
	
	public char getXLetter() {
		return this.tile.getXLetter();
	}
	
	public int getY() {
		return this.tile.getY();
	}
	
	public Coordinates getHoverTile() {
		return this.hoverTile;
	}
	
	public void setHoverTile(Coordinates hoverTile) {
		this.hoverTile = hoverTile;
	}
	
	public int getHoverX() {
		return this.hoverTile == null ? this.tile.getX() : this.hoverTile.getX();
	}
	
	public int getHoverY() {
		return this.hoverTile == null ? this.tile.getY() : this.hoverTile.getY();
	}
	
	public chess.Color getColor() {
		return this.color;
	}
	
	public ArrayList<Coordinates> getRealOptions() {
		ArrayList<Coordinates> options = new ArrayList<Coordinates>();
		for (Coordinates tile : getMoves()) {
			if (!tile.isOutOfBounds() && !isBlocked(tile)
			&& (tile.getPiece() == null || tile.getPiece().getColor() != this.getColor())) {
			// tile is NOT out of bounds
			// AND tile is NOT blocked by ANY piece
			// AND 	EITHER tile contains NO piece
			// 		OR tile contains ENEMY piece
				if (this.getColor() != Chess.getCurrentColor()) {
				// we're checking the enemy's options
				// since it's not their turn, we know their King can't be checked
				// because they couldn't have left their King checked
				// at the end of their turn
				// ~ this check prevents loops
					options.add(tile);
				}
				else if (!kingCheckedAfterMove(tile)) {
					options.add(tile);
				}
			}
		}
		return options;
	}
	
	private boolean kingCheckedAfterMove(Coordinates nextTile) {
		boolean isKingChecked = false;
		moveTo(nextTile, true);
		if (Chess.isCurrentKingChecked()) {
			isKingChecked = true;
		}
		Board.undoEvent();
		return isKingChecked;
	}

	protected abstract ArrayList<Coordinates> getMoves();
	
	protected abstract boolean isBlocked(Coordinates tile);
	
	public Image getImage() {
		return this.image;
	};
	
	public void drawPiece(Graphics g) {
		g.drawImage(
			getImage(),
//				offsetX+squareSize*(piece.getX()-1),
//				offsetY+boardSize-squareSize*(piece.getY()),
//				squareSize,
//				squareSize,
			Board.getOffsetX()+Board.getSquareSize()*(getHoverX()-1),
			Board.getOffsetY()+Board.getBoardSize()-Board.getSquareSize()*(getHoverY()),
			Board.getSquareSize(),
			Board.getSquareSize(),
			new JPanel()
		);
	}
	
	public void drawRealOptions(Graphics g) {
		
		// self
		g.setColor(new Color(0, 0, 255, 128));
		g.fillRect(
//			Board.offsetX+(getX()-1)*Board.squareSize,
//			Board.offsetY+Board.boardSize-(getY())*Board.squareSize,
//			Board.squareSize,
//			Board.squareSize
			Board.getOffsetX()+(getX()-1)*Board.getSquareSize(),
			Board.getOffsetY()+Board.getBoardSize()-(getY())*Board.getSquareSize(),
			Board.getSquareSize(),
			Board.getSquareSize()
		);
		
		// options
		g.setColor(new Color(0, 128, 0, 128));
		for (Coordinates tile : getRealOptions()) {
			g.fillRect(
//				Board.offsetX+(tile.getX()-1)*Board.squareSize,
//				Board.offsetY+Board.boardSize-(tile.getY())*Board.squareSize,
//				Board.squareSize,
//				Board.squareSize
				Board.getOffsetX()+(tile.getX()-1)*Board.getSquareSize(),
				Board.getOffsetY()+Board.getBoardSize()-(tile.getY())*Board.getSquareSize(),
				Board.getSquareSize(),
				Board.getSquareSize()
			);
		}
		g.setColor(Color.black);
	}
	
}
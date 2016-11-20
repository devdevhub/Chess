package chess;

import java.awt.Button;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.*;

import chess.pieces.ChessPiece;
import chess.pieces.King;

public class Chess {
	
	public static int turn = 0;
	private static Timer timer;
	private static Timer simulation;
	public static boolean inAnimation = false;
	public final static JFrame FRAME = new JFrame("Chess");
	
	@SuppressWarnings("serial")
	public static void main(String[] args) {
		
		FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FRAME.setSize(
			Settings.initOffsetX*2+Settings.initSquareSize*8+17,
			Settings.initOffsetY*2+Settings.initSquareSize*8+40
		);
		
		FRAME.setContentPane(new JPanel() {
			public void paint(Graphics g) {
				if (turn == 0) {
					Board.setup();
					turn++;
					Console.logTurn();
				}
				Board.update(g);
				
				AI.movePiece();
				
				// debugging
				Board.showClick(g);
				Console.logStats();
			}
		});
		
//		FRAME.setResizable(false);
		Button resetButton = new Button("RESET");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mouse.tileSelected = null;
				inAnimation = true;
//				final double eventCount = (double)(Board.history.size());
				timer = new Timer(200, new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (Board.history.size() > 0) {
							Board.undoEvent();
							FRAME.repaint();
//							int newDelay = (int)(timer.getDelay()-150/eventCount) >= 10 ? (int)(timer.getDelay()-150/eventCount) : 10;
							int newDelay = timer.getDelay()-5 >= 10 ? timer.getDelay()-5 : 10;
							timer.setDelay(newDelay);
						}
						else {
							timer.stop();
							turn = 1;
							Console.logTurn();
							inAnimation = false;
						}
					}
				});
				timer.start();
			}
		});
		FRAME.add(resetButton);
		FRAME.setVisible(true);
		System.out.println("Running...");
		
		simulation = new Timer(50, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (turn < 250) {
					ArrayList<ChessPiece> pieces = new ArrayList<ChessPiece>();
					for (ChessPiece[] row : Board.gameGrid) {
						for (ChessPiece piece : row) {
							if (piece != null && piece.getColor() == getCurrentColor()) {
								pieces.add(piece);
							}
						}
					}
					int iPiece = 0, pieceRandom = (int)(Math.random()*pieces.size());
					piecesLoop: for (ChessPiece piece : pieces) {
						if (iPiece == pieceRandom) {
							int iOption = 0, optionRandom = (int)(Math.random()*piece.getRealOptions().size());
							for (Coordinates optionTile : piece.getRealOptions()) {
								if (iOption == optionRandom) {
									piece.moveTo(optionTile, true);
									turn++;
									Console.logTurn();
									break piecesLoop;
								}
								iOption++;
							}
						}
						iPiece++;
					}
					FRAME.repaint();
				}
				else {
					simulation.stop();
				}
			}
		});
//		simulation.start();
		
		FRAME.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!inAnimation) {
//					Console.logTile(Board.getTileClicked(e));
//					Board.onTileClicked(e);
					Mouse.onClick(e);
					FRAME.repaint();
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (!inAnimation) {
//					Board.onTileClicked(e);
					Mouse.onPress(e);
					FRAME.repaint();
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (!inAnimation) {
//					Board.onTileClicked(e);
					Mouse.onRelease(e);
					FRAME.repaint();
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (!inAnimation) {
					// code
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (!inAnimation) {
					// code
				}
			}
			
		});
		
		FRAME.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				if (!inAnimation) {
//					Board.onTileClicked(e);
					Mouse.onDrag(e);
					FRAME.repaint();
				}
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				if (!inAnimation) {
					// code
				}
			}
			
		});
		
	}
	
	public static chess.Color getCurrentColor() {
		return (turn+1&1) == 0 ? chess.Color.PLAYER : chess.Color.ENEMY;
	}
	
	public static boolean isCurrentKingChecked() {
		for (ChessPiece[] row : Board.gameGrid) {
			for (ChessPiece piece : row) {
				if (piece != null && piece instanceof King && piece.getColor() == Chess.getCurrentColor()) {
					return ((King)(piece)).isChecked();
				}
			}
		}
		return false;
	}
	
}
package chess;

public class Settings {
	
	
	// EITHER "white" OR "black"
	// case-insensitive
	public static String playerColor = ((int)(Math.random()+0.5) == 0) ? "white" : "black";
	
	public static boolean enableAi			= true;
	

	
	// ======================== LOGS ======================== //
	
	static boolean logTurn 					= true;
	
	static boolean logAllPiecesInfo 		= false;
	
	static boolean logCurrentPlayerPieces 	= true;
	static boolean logOtherPlayerPieces 	= false;
	static boolean logEmptyTiles 			= false;
	
	static boolean logPlayerPieceOptions 	= true;
	static boolean logEnemyPieceOptions 	= false;
	
	static boolean logMoves 				= true;
	
	
	
	// ================== VISUAL DEBUGGING ================== //
	
	static boolean showClick 				= false;
	
	
	
	// ===================== DIMENSIONS ===================== //
	
	static int initOffsetX 					= 100;
	static int initOffsetY 					= 100;
	static int initSquareSize 				= 50;


}
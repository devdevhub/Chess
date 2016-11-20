package chess;

public enum Color {

	PLAYER(0),
	ENEMY(1);
	
	private int id;
	private String name;
	
	Color(int id) {
		this.id = id;
		if (id == 0) {
			this.name = (Settings.playerColor.equalsIgnoreCase("White")) ? "White" : "Black";
		}
		else {
			this.name = (Settings.playerColor.equalsIgnoreCase("White")) ? "Black" : "White";
		}
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
}

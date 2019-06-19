package sprites;

public class Wall extends Sprite {
	/**
	 * Initializes a new Wall with its symbol, row and column number.
	 * 
	 * @param symbol
	 *            a character that specifically belongs to Wall.
	 * @param row
	 *            the row number of Wall.
	 * @param column
	 *            the column number of Wall.
	 */
	public Wall(char symbol, int row, int column) {
		super(symbol, row, column);
	}
}

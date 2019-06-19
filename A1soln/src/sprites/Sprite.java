package sprites;

/**
 * An abstract class.
 * 
 * @author doganakad
 *
 */
public abstract class Sprite {
	protected char symbol;
	protected int row;
	protected int column;

	/**
	 * Initializes a new Sprite with its symbol, row and column
	 * 
	 * @param symbol
	 *            a character specific to Sprite
	 * @param row
	 *            the row number of Sprite
	 * @param column
	 *            the column number
	 */
	public Sprite(char symbol, int row, int column){
		this.symbol = symbol;
		this.row = row;
		this.column = column;
	}
	/**
	 * Returns the symbol of the Sprite.
	 * 
	 * @return symbol a character that belongs to a specific Sprite
	 */
	public char getSymbol() {
		return symbol;
	}

	/**
	 * Returns the row number of Sprite where it belongs to.
	 * 
	 * @return row the x-coordinate of the location of Sprite
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Returns the column number of Sprite.
	 * 
	 * @return column the y-coordinate of the location of Sprite
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * Returns the string representation of Sprite.
	 */
	public String toString() {
		return "Its symbol is " + symbol + "in the" + row + "th row " + column + "th column";
	}

}

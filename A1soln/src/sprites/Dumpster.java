package sprites;

public class Dumpster extends Sprite {
	/**
	 * Initializes a new Dumpster with its symbol, row and column number.
	 * 
	 * @param symbol
	 *            a character that specifically belongs to a Dumpster.
	 * @param row
	 *            the row number of a Dumpster.
	 * @param column
	 *            the column number of a Dumpster.
	 */
	public Dumpster(char symbol, int row, int column) {
		super(symbol, row,column);
	}
	
	public char getSymbol() {
		return this.symbol;
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getColumn() {
		return this.column;
	}

	public String toString() {
		return "It's symbol is " + symbol + row + "th row " + column + "th column";
	}
}

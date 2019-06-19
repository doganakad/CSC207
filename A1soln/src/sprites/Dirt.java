package sprites;

public class Dirt extends Sprite {

	protected int value;

	/**
	 * Initialize a new Dirt
	 * 
	 * @param symbol
	 *            a character that belongs specifically to a Dirt
	 * @param row
	 *            the row number of a Dirt
	 * @param column
	 *            the column number of a Dirt
	 * @param value
	 *            the points you get when you clean a Dirt
	 */
	public Dirt(char symbol, int row, int column, int value) {
		super(symbol, row, column);
		this.value = value;
	}

	/**
	 * Returns the value of the Dirt
	 * 
	 * @return value the value of the Dirt
	 */
	public int getValue() {
		return value;
	}

}

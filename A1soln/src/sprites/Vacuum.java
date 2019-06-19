package sprites;

import game.Constants;
//import sprites.CleanHallway;

public class Vacuum extends Sprite implements Moveable {

	private int score;
	private int capacity;
	private int fullness;
	private Sprite under;

	/**
	 * Initializes a new Vacuum with its symbol,row number, column number and
	 * capacity.
	 * 
	 * @param symbol
	 *            a character that specifically belongs to a Vacuum
	 * @param row
	 *            the row number of the Vacuum
	 * @param column
	 *            the column number of the Vacuum
	 * @param capacity
	 *            the maximum number which a Vacuum can hold
	 */
	public Vacuum(char symbol, int row, int column, int capacity) {
		super(symbol, row, column);
		this.capacity = capacity;
		this.score = game.Constants.INIT_SCORE;
		this.fullness = game.Constants.EMPTY;
		this.under = new sprites.CleanHallway(Constants.CLEAN, row, column);
	}

	public Sprite getUnder() {
		return under;
	}

	public void setUnder(Sprite under) {
		this.under = under;
	}

	/**
	 * Returns the score of a Vacuum
	 * 
	 * @return score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Returns true iff the Vacuum was able to clean the tile
	 * 
	 * @param score
	 * @return boolean True iff the Vacuum was able to clean the tile
	 */
	public boolean clean(int score) {
		boolean cleanable = this.capacity > this.fullness;
		if (cleanable) {
			this.score += score;
			this.fullness += Constants.FULLNESS_INC;
		}
		return cleanable;
	}

	/**
	 * Empty the Vacuum.
	 */
	public void empty() {
		this.fullness = game.Constants.EMPTY;
	}

	@Override
	/**
	 * Move the Vacuum to the rowth row and columnth column.
	 */
	public void moveTo(int row, int column) {
		// TODO Auto-generated method stub
		this.row = row;
		this.column = column;
	}

}

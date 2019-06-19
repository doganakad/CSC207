package sprites;

public class DustBall extends Dirt implements Moveable {

	/**
	 * Initialize a new DustBall
	 * 
	 * @param symbol
	 *            a character specific to a DustBall
	 * @param row
	 *            the row number of a DustBall
	 * @param column
	 *            the column number of a DustBall
	 * @param value
	 *            the points you get when you clean the DustBall
	 */
	public DustBall(char symbol, int row, int column, int value) {
		super(symbol, row, column, value);
	}

	/**
	 * Move a Dustball to the rowth row and columnth column
	 */
	@Override
	public void moveTo(int row, int column) {
		// TODO Auto-generated method stub
		this.row = row;
		this.column = column;
	}
}

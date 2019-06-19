package game;

//import sprites.Sprite;

public class ArrayGrid<T> implements Grid<T> {
	private int numRows;
	private int numColumns;
	//private Object other;
	private Object [][] grid;
	public ArrayGrid(int numRows, int numColumns){
		this.numRows = numRows;
		this.numColumns = numColumns;
		grid = new Object [numRows][numColumns];
		/*
		for (int i=0; i < grid.length; i++){
			for (int j = 0; j < grid[i].length; j++){
				grid[i][j] = game.Constants.CLEAN;
				
			}
		}
		*/
	}
	public int getNumRows() {
		return numRows;
	}
	public int getNumColumns() {
		return numColumns;
	}
	//@Override
	private boolean equals (ArrayGrid<T> other) {
		boolean equality = true;
		// TODO: gradually test it
		if (this.numRows != other.numRows || this.numColumns != other.numColumns) {
			equality = false;
		}
		else {
			for (int i = 0; i < this.numRows; i++) {
				for (int j = 0; j < this.numColumns; j++) {
					T this_cell = this.getCell(i, j);
					T other_cell = other.getCell(i, j);
					if (this_cell != other_cell) {
						equality = false;
					}
				}
			}
		}
		return equality;
	}
	
	
	public boolean equals(Object other){
		return (other instanceof ArrayGrid && this.equals((ArrayGrid<T>) other));
	}
	public void setCell(int row, int column, T item){
		grid[row][column] = item;
	}
	//@Override
	public T getCell(int row, int column) {
		// TODO Auto-generated method stub
		Object x = grid[row][column];
		return (T) x;
	}
	
}

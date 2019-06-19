package game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import sprites.CleanHallway;
import sprites.Dirt;
import sprites.Dumpster;
import sprites.DustBall;
import sprites.Sprite;
import sprites.Vacuum;
import sprites.Wall;

/**
 * A class that represents the basic functionality of the vacuum game.
 * This class is responsible for performing the following operations:
 * 1. At creation, it initializes the instance variables used to store the
 *        current state of the game.
 * 2. When a move is specified, it checks if it is a legal move and makes the
 *        move if it is legal.
 * 3. It reports information about the current state of the game when asked.
 */
public class VacuumGame {

    // a random number generator to move the DustBalls
    private Random random;

    // the grid
    private Grid<Sprite> grid;

    // the first player
    private Vacuum vacuum1;

    /// the second player
    private Vacuum vacuum2;

    // the dirt (both static dirt and mobile dust balls)
    private List<Dirt> dirts;

    // the dumpsters
    private List<Dumpster> dumpsters;

    /**
     * Creates a new VacuumGame that corresponds to the given input text file.
     * Assumes that the input file has one or more lines of equal lengths, and
     * that each character in it (other than newline) is a character that 
     * represents one of the sprites in this game.
     * @param layoutFileName path to the input grid file
     */
    public VacuumGame(String layoutFileName) throws IOException {
        this.dirts = new ArrayList<Dirt>();
        this.dumpsters = new ArrayList<Dumpster>(); // Jen: may not need this
        this.random = new Random();

        // open the file, read the contents, and determine 
        // dimensions of the grid
        int[] dimensions = getDimensions(layoutFileName);
        this.grid = new ArrayGrid<Sprite>(dimensions[0], dimensions[1]);
        
        // open the file again, read the contents, and store them in grid
        Scanner sc = new Scanner(new File(layoutFileName));

	// INITIALIZE THE GRID HERE
        int i = 0;
        int j = 0;
        while (sc.hasNextByte()) {
        	char symbol = (char) sc.nextByte();
        	switch (symbol) {
        	case Constants.CLEAN:
        		grid.setCell(i, j, new CleanHallway(symbol, i, j));
        		break;
        	case Constants.DIRT:
        		Dirt new_dirt = new Dirt(symbol, i, j, Constants.DIRT_SCORE);
        		dirts.add(new_dirt);
        		grid.setCell(i, j, new_dirt);
        		break;
        	case Constants.DUMPSTER:
        		Dumpster new_dumpster = new Dumpster(symbol, i, j);
        		dumpsters.add(new_dumpster);
        		grid.setCell(i, j, new_dumpster);
        		break;
        	case Constants.DUST_BALL:
        		grid.setCell(i, j, new DustBall(symbol, i, j, Constants.DUST_BALL_SCORE));
        		break;
        	case Constants.P1:
        		vacuum1 = new Vacuum(symbol, i, j, Constants.CAPACITY);
        		grid.setCell(i, j, vacuum1);
        		break;
        	case Constants.P2:
        		vacuum2 = new Vacuum(symbol, i, j, Constants.CAPACITY);
        		grid.setCell(i, j, vacuum2);
        		break;
        	case Constants.WALL:
        		grid.setCell(i, j, new Wall(symbol, i, j));
        		break;
        	case '\n':
        		i++;
        		j = -1;
        		break;
        	}
        	j++;
        }

        sc.close();
    }


    public int getNumRows(){
    	return grid.getNumRows();
    }
    public int getNumColumns(){
    	return grid.getNumColumns();
    }
    
    public Grid<Sprite> getGrid() {
		return grid;
	}


	public Vacuum getVacuum1() {
		return vacuum1;
	}


	public Vacuum getVacuum2() {
		return vacuum2;
	}

	public Sprite getSprite(int i, int j) {
		return grid.getCell(i, j);
	}
	
	private boolean inGrid(int i, int j) {
		if (i >= 0 && j >= 0 && i < grid.getNumRows() && j < grid.getNumColumns()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean move(char nextMove) {
		boolean valid_move = true;
		int old_row = -1;
		int old_col = -1;
		int new_row = -1;
		int new_col = -1;
		Vacuum current_vacuum = null;
		int i = 0;
		int j = 0;
		
		switch (nextMove) {
		case Constants.P1_DOWN:
			current_vacuum = vacuum1;
			j++;
			break;
		case Constants.P1_UP:
			current_vacuum = vacuum1;
			j--;
			break;
		case Constants.P1_LEFT:
			current_vacuum = vacuum1;
			i--;
			break;
		case Constants.P1_RIGHT:
			current_vacuum = vacuum1;
			i++;
			break;
		case Constants.P2_UP:
			current_vacuum = vacuum2;
			j--;
			break;
		case Constants.P2_DOWN:
			current_vacuum = vacuum2;
			j++;
			break;
		case Constants.P2_LEFT:
			current_vacuum = vacuum2;
			i--;
			break;
		case Constants.P2_RIGHT:
			current_vacuum = vacuum2;
			i++;
			break;
		default:
			valid_move = false;
		}
		if (valid_move) { // move validity checks
			old_row = current_vacuum.getRow();
			old_col = current_vacuum.getColumn();
			new_row = current_vacuum.getRow() + i;
			new_col = current_vacuum.getColumn() + j;
			if (!(inGrid(new_row, new_col))) {
				valid_move = false;
			}
			if (grid.getCell(new_row, new_col).getSymbol() == Constants.WALL ||
					grid.getCell(new_row, new_col).getSymbol() == Constants.P1 ||
					grid.getCell(new_row, new_col).getSymbol() == Constants.P2) {
				valid_move = false;
			}
			// more move validity testing
		}
		if (valid_move) { // actually moving
			Sprite old_under = current_vacuum.getUnder();
			current_vacuum.moveTo(new_row, new_col);
			grid.setCell(old_row, old_col, old_under);
			Sprite new_under = grid.getCell(new_row, new_col);
			switch (new_under.getSymbol()) {
			case Constants.DUST_BALL:
			case Constants.DIRT:
				current_vacuum.clean(((Dirt) new_under).getValue());
				new_under = new CleanHallway(Constants.CLEAN, new_row, new_col);
				break;
			case Constants.DUMPSTER:
				current_vacuum.empty();
			}
			current_vacuum.setUnder(new_under);
			grid.setCell(new_row, new_col, current_vacuum);
		}
		return valid_move;
	}
	
	public boolean gameOver() {
		boolean over = true;
		for (int i = 0; i < grid.getNumRows(); i++) {
			for (int j = 0; j < grid.getNumColumns(); j++) {
				Sprite cell = grid.getCell(i, j);
				if (cell.getSymbol() == Constants.DIRT || cell.getSymbol() == Constants.DUST_BALL) {
					over = false;
				}
			}
		}
		return over;
	}
	
	public int getWinner() {
		if (vacuum1.getScore() > vacuum2.getScore()) {
			return 1;
		}
		else if (vacuum1.getScore() < vacuum2.getScore()) {
			return 2;
		}
		else {
			return 0; // TODO: check whether 0 actually represents a tie!!!!
		}
	}
	
	/**
     * Returns the dimensions of the grid in the file named layoutFileName.
     * @param layoutFileName path of the input grid file
     * @return an array [numRows, numCols], where numRows is the number
     * of rows and numCols is the number of columns in the grid that
     * corresponds to the given input grid file
     * @throws IOException
     */
    private int[] getDimensions(String layoutFileName) throws IOException {       

        Scanner sc = new Scanner(new File(layoutFileName));

        // find the number of columns
        String nextLine = sc.nextLine();
        int numCols = nextLine.length();

        int numRows = 1;

        // find the number of rows
        while (sc.hasNext()) {
            numRows++;
            nextLine = sc.nextLine();
        }

        sc.close();
        return new int[]{numRows, numCols};
    }
}

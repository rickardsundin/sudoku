package nu.snart.rickard.sudoku.model;

import java.util.Arrays;

public class Grid {

	/**
	 * The size of a grid, changing the size is untested.
	 */
	protected final int GRIDSIZE;
	
	/**
	 * The internal representation of a grid is this array of symbols.
	 * Each square in the grid has a corresponding index in the array
	 * as follows:
	 * <pre>
	00 01 02   03 04 05   06 07 08
	09 10 11   12 13 14   15 16 17
	18 19 20   21 22 23   24 25 26
	
	27 28 29   30 31 32   33 34 35
	36 37 38   39 40 41   42 43 44
	45 46 47   48 49 50   51 52 53
	
	54 55 56   57 58 59   60 61 62
	63 64 65   66 67 68   69 70 71
	72 73 74   75 76 77   78 79 80
	</pre> 
	 */
	protected final Symbol[] symbols;

	public Grid(int gridSize) {
		GRIDSIZE = gridSize;
		symbols = new Symbol[GRIDSIZE*GRIDSIZE];
		Arrays.fill(symbols, Symbol.EMPTY);
	}
	
	public Grid(Grid grid) {
		GRIDSIZE = grid.GRIDSIZE;
		symbols = new Symbol[GRIDSIZE*GRIDSIZE];
		System.arraycopy(grid.symbols, 0, symbols, 0, symbols.length);
	}

	/**
	 * Get the symbol from the specified square in this grid.
	 * @param column column number, 1 is the leftmost column
	 * @param row row number, 1 is the top row
	 * @return the symbol from the specified square
	 */
	public Symbol get(int column, int row) {
		if ((column-1)<0 || (row-1)<0 || (column-1)>=GRIDSIZE || (row-1)>=GRIDSIZE) {
			throw new IllegalArgumentException("Columns may not have negative values");
		}
		return symbols[(row-1)*GRIDSIZE+(column-1)];
	}
	
	public Symbol get(Position position) {
		return get(position.column(), position.row());
	}

	/**
	 * Assign a symbol to the specified square in this grid.
	 * @param symbol
	 * @param column column number, 1 is the leftmost column
	 * @param row row number, 1 is the top row
	 */
	public void set(Symbol symbol, int column, int row) {
		symbols[(row-1)*GRIDSIZE+(column-1)] = symbol;
	}
	
	public void set(Symbol symbol, Position position) {
		set(symbol, position.column(), position.row());
	}

	@Override
	public String toString() {
		String result = "";
		for (int row=1; row<=GRIDSIZE; row++) {
			for (int column=1; column<=GRIDSIZE; column++) {
				result += get(column, row).toString();
			}
			result += System.getProperty("line.separator");
		}
		return result;
	}
}
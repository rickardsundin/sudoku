package nu.snart.rickard.sudoku.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Represents a sudoku grid with 9*9 squares, divided into nine 3*3 sub grids.
 */
public class SudokuGrid extends Grid {

	/**
	 * Create an empty sudoku grid.
	 */
	public SudokuGrid() {
		super(9);
	}
	
	public SudokuGrid(SudokuGrid grid) {
		super(grid);
	}

	/**
	 * A grid is solved if it is valid and no square is empty.
	 * @return true if this grid is solved
	 */
	public boolean isSolved() {
		return !Arrays.asList(symbols).contains(Symbol.EMPTY) && isValid();
	}

	/**
	 * A grid is valid if each symbol only occur once in each row,
	 * once in each column and once in each sub grid. 
	 * @return true if this grid is valid
	 */
	public boolean isValid() {
		for (Row row : rows()) {
			if (!row.isValid()) {
				return false;
			}
		}
		for (Column column : columns()) {
			if (!column.isValid()) {
				return false;
			}
		}
		for (SubGrid subGrid : subGrids()) {
			if (!subGrid.isValid()) {
				return false;
			}
		}
		return true;
	}
	
	private List<Row> rows() {
		List<Row> rows = new ArrayList<Row>(GRIDSIZE);
		for (int row = 0; row < GRIDSIZE; row++) {
			Symbol[] rowArray = new Symbol[GRIDSIZE];
			System.arraycopy(symbols, row*GRIDSIZE,  rowArray, 0, GRIDSIZE);
			rows.add(new Row(rowArray));
		}
		return rows;
	}
	
	private List<Column> columns() {
		List<Column> columns = new ArrayList<Column>(GRIDSIZE);
		for (int col = 0; col < GRIDSIZE; col++) {
			Symbol[] colArray = new Symbol[GRIDSIZE];
			for (int row=0; row<GRIDSIZE; row++) {
				colArray[row] = symbols[row*GRIDSIZE+col];
			}
			columns.add(new Column(colArray));
		}
		return columns;
	}
	
	private List<SubGrid> subGrids() {
		List<SubGrid> subGrids = new ArrayList<SubGrid>(GRIDSIZE);
		for (int subGrid = 0; subGrid < GRIDSIZE; subGrid++) {
			int startIdx = (subGrid/3*27)+(subGrid%3)*3;
			Symbol[] subArray = new Symbol[GRIDSIZE];
			subArray[0] = symbols[startIdx];
			subArray[1] = symbols[startIdx+1];
			subArray[2] = symbols[startIdx+2];
			subArray[3] = symbols[startIdx+GRIDSIZE];
			subArray[4] = symbols[startIdx+GRIDSIZE+1];
			subArray[5] = symbols[startIdx+GRIDSIZE+2];
			subArray[6] = symbols[startIdx+GRIDSIZE*2];
			subArray[7] = symbols[startIdx+GRIDSIZE*2+1];
			subArray[8] = symbols[startIdx+GRIDSIZE*2+2];
			subGrids.add(new SubGrid(subArray));
		}
		return subGrids;
	}
	
	public int numberOfEmptySquares() {
		return Collections.frequency(Arrays.asList(symbols), Symbol.EMPTY);
	}

	class SubGroup {
		private List<Symbol> symbolsSubset;
		SubGroup(Symbol[] symbols) {
			this.symbolsSubset = Arrays.asList(symbols);
		}
		public boolean isValid() {
			for (Symbol symbol : Symbol.values()) {
				if (symbol!=Symbol.EMPTY && Collections.frequency(symbolsSubset, symbol)>1) 
					return false;
			}
			return true;
		}		
	}
	
	class Row extends SubGroup {
		Row(Symbol[] rowSymbols) {
			super(rowSymbols);
		}
	}

	class Column extends SubGroup {
		Column(Symbol[] colSymbols) {
			super(colSymbols);
		}
	}
	
	class SubGrid extends SubGroup {
		SubGrid(Symbol[] subSymbols) {
			super(subSymbols);
		}
	}
}

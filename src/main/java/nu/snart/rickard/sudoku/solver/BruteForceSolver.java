package nu.snart.rickard.sudoku.solver;

import java.util.ArrayList;
import java.util.List;

import nu.snart.rickard.sudoku.model.Grid;
import nu.snart.rickard.sudoku.model.Position;
import nu.snart.rickard.sudoku.model.SudokuGrid;
import nu.snart.rickard.sudoku.model.Symbol;

public class BruteForceSolver implements Solver {

	/**
	 * Return a solved sudoku grid starting with the provided grid.
	 */
	public SudokuGrid calculateSolution(SudokuGrid grid) {
		if (grid.isSolved()) {
			return grid;
		}
		List<SudokuGrid> candidates = new ArrayList<SudokuGrid>();
		candidates.add(grid);
		while (!candidates.get(0).isSolved()) {
			candidates = workCandidates(candidates);
		}
		if (candidates.size()>1) {
			// More than one solution was found. How to handle this?
		}
		return candidates.get(0);
	}
	
	/**
	 * For each sudoku grid in the list:
	 * 
	 * <ol>
	 * <li>Find an empty square
	 * <li>Try each symbol (except EMPTY) in that position
	 * <li>Return the grids that are still valid.
	 * </ol>
	 * @param grids
	 * @return
	 */
	public List<SudokuGrid> workCandidates(List<SudokuGrid> grids) {
		List<SudokuGrid> candidates = new ArrayList<SudokuGrid>();

		for (SudokuGrid grid : grids) {
		
			Position emptyPosition = findFirstEmptySquare(grid);
			for (Symbol symbol: Symbol.values()) {
				if (symbol==Symbol.EMPTY) continue;
				
				SudokuGrid gridCopy = new SudokuGrid(grid);
				gridCopy.set(symbol, emptyPosition);
				if (gridCopy.isValid()) {
					candidates.add(gridCopy);
				}
			}
		}

		return candidates;
	}

	private Position findFirstEmptySquare(Grid grid) {
		for (int row=1; row<=9; row++) {
			for (int column=1; column<=9; column++) {
				if (grid.get(column, row)==Symbol.EMPTY) {
					return new Position(column, row);
				}
			}
		}
		return null;
		
	}
}

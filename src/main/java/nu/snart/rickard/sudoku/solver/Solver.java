package nu.snart.rickard.sudoku.solver;

import nu.snart.rickard.sudoku.model.SudokuGrid;

public interface Solver {
	
	public SudokuGrid calculateSolution(SudokuGrid grid);

}

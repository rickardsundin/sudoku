package nu.snart.rickard.sudoku.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingWorker;

import nu.snart.rickard.sudoku.model.SudokuGrid;
import nu.snart.rickard.sudoku.solver.BruteForceSolver;


public class SolverWorker extends SwingWorker<SudokuGrid, Void> {

	private SudokuGrid data;

	SolverWorker(SudokuGrid startData) {
		data = startData;
	}
	
	@Override
	protected SudokuGrid doInBackground() throws Exception {
		int progress = 81 - data.numberOfEmptySquares();
		BruteForceSolver solver = new BruteForceSolver();
		List<SudokuGrid> candidates = new ArrayList<SudokuGrid>();
		candidates.add(data);
		while (!candidates.get(0).isSolved()) {
			setProgress(progress);
			candidates = solver.workCandidates(candidates);
			progress++;
		}
		return solver.calculateSolution(data);
	}

}

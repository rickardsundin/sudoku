package nu.snart.rickard.sudoku.model;

import static nu.snart.rickard.sudoku.model.Symbol.EIGHT;
import static nu.snart.rickard.sudoku.model.Symbol.FIVE;
import static nu.snart.rickard.sudoku.model.Symbol.FOUR;
import static nu.snart.rickard.sudoku.model.Symbol.NINE;
import static nu.snart.rickard.sudoku.model.Symbol.ONE;
import static nu.snart.rickard.sudoku.model.Symbol.SEVEN;
import static nu.snart.rickard.sudoku.model.Symbol.SIX;
import static nu.snart.rickard.sudoku.model.Symbol.THREE;
import static nu.snart.rickard.sudoku.model.Symbol.TWO;

public class Repository {

	public SudokuGrid partlySolvedHardSudoku() {
		SudokuGrid grid = new SudokuGrid();
		grid.set(ONE, 1, 2);
		grid.set(FOUR, 4, 2);
		grid.set(THREE, 7, 2);
		grid.set(EIGHT, 9, 2);
		grid.set(NINE, 1, 3);
		grid.set(FIVE, 3, 3);
		grid.set(SEVEN, 8, 3);
		grid.set(TWO, 9, 3);
		grid.set(FOUR, 7, 4);
		grid.set(NINE, 9, 4);
		grid.set(SEVEN, 2, 5);
		grid.set(FIVE, 8, 5);
		grid.set(EIGHT, 2, 6);
		grid.set(SIX, 5, 6);
		grid.set(SEVEN, 7, 6);
		grid.set(EIGHT, 4, 7);
		grid.set(TWO, 5, 7);
		grid.set(SIX, 8, 7);
		grid.set(FOUR, 9, 7);
		grid.set(SIX, 3, 8);
		grid.set(FIVE, 5, 8);
		grid.set(FOUR, 6, 8);
		grid.set(TWO, 8, 8);
		return grid;
	}
	
	SudokuGrid partlySolvedSudoku() {
		SudokuGrid grid = new SudokuGrid();
		grid.set(SIX, 3, 1);
		grid.set(TWO, 5, 1);
		grid.set(FIVE, 6, 1);
		grid.set(FOUR, 8, 1);
		grid.set(ONE, 9, 1);
		grid.set(SEVEN, 1, 2);
		grid.set(THREE, 3, 2);
		grid.set(EIGHT, 4, 2);
		grid.set(ONE, 5, 2);
		grid.set(NINE, 6, 2);
		grid.set(FIVE, 2, 3);
		grid.set(THREE, 6, 3);
		grid.set(THREE, 1, 4);
		grid.set(SIX, 5, 4);
		grid.set(FOUR, 6, 4);
		grid.set(ONE, 8, 4);
		grid.set(TWO, 9, 4);
		grid.set(ONE, 2, 5);
		grid.set(FIVE, 4, 5);
		grid.set(TWO, 6, 5);
		grid.set(SEVEN, 7, 5);
		grid.set(EIGHT, 9, 5);
		grid.set(ONE, 4, 6);
		grid.set(SEVEN, 5, 6);
		grid.set(NINE, 8, 6);
		grid.set(TWO, 1, 7);
		grid.set(EIGHT, 3, 7);
		grid.set(SIX, 7, 7);
		grid.set(THREE, 8, 7);
		grid.set(FOUR, 1, 8);
		grid.set(SEVEN, 9, 8);
		grid.set(ONE, 3, 9);
		grid.set(EIGHT, 5, 9);
		grid.set(NINE, 7, 9);
		grid.set(FOUR, 9, 9);
		return grid;
	}
	
	SudokuGrid solvedSudoku() {
		SudokuGrid grid = partlySolvedSudoku();
		grid.set(ONE, 1, 3);
		grid.set(ONE ,7, 8);
		grid.set(ONE, 6, 7);
		
		grid.set(TWO, 7, 2);
		grid.set(TWO, 3, 3);
		grid.set(TWO, 2, 6);
		grid.set(TWO, 4, 8);
		grid.set(TWO, 8, 9);
		
		grid.set(THREE, 7, 1);
		grid.set(THREE, 5, 5);
		grid.set(THREE, 9, 6);
		grid.set(THREE, 2, 8);
		grid.set(THREE, 4, 9);
		
		grid.set(FOUR, 2, 2);
		grid.set(FOUR, 5, 3);
		grid.set(FOUR, 3, 5);
		grid.set(FOUR, 7, 6);
		grid.set(FOUR, 4, 7);
		
		grid.set(FIVE, 8, 2);
		grid.set(FIVE, 7, 4);
		grid.set(FIVE, 3, 6);
		grid.set(FIVE, 9, 7);
		grid.set(FIVE, 5, 8);
		grid.set(FIVE, 1, 9);
		
		grid.set(SIX, 9, 2);
		grid.set(SIX, 4, 3);
		grid.set(SIX, 8, 5);
		grid.set(SIX, 1, 6);
		grid.set(SIX, 6, 8);
		grid.set(SIX, 2, 9);
		
		grid.set(SEVEN, 4, 1);
		grid.set(SEVEN, 8, 3);
		grid.set(SEVEN, 3, 4);
		grid.set(SEVEN, 2, 7);
		grid.set(SEVEN, 6, 9);
		
		grid.set(EIGHT, 1, 1);
		grid.set(EIGHT, 7, 3);
		grid.set(EIGHT, 2, 4);
		grid.set(EIGHT, 6, 6);
		grid.set(EIGHT, 8, 8);
		
		grid.set(NINE, 2, 1);
		grid.set(NINE, 9, 3);
		grid.set(NINE, 4, 4);
		grid.set(NINE, 1, 5);
		grid.set(NINE, 5, 7);
		grid.set(NINE, 3, 8);

		return grid;
	}
}

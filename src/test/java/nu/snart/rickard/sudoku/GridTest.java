package nu.snart.rickard.sudoku;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static nu.snart.rickard.sudoku.model.Symbol.*;

import nu.snart.rickard.sudoku.model.Grid;
import nu.snart.rickard.sudoku.model.SudokuGrid;

import org.junit.Before;
import org.junit.Test;

public class GridTest {

	private SudokuGrid grid;

	@Before
	public void setUp() {
		grid = new SudokuGrid();		
	}
	
	@Test
	public void testConstructor()  {
		assertNotNull(grid);
	}
	
	@Test
	public void allGridsShouldBeEmpty()  {
		for (int column = 1; column < 10; column++) {
			for (int row = 1; row < 10; row++) {
				assertEquals(EMPTY, grid.get(column,row));				
			}
		}
	}
	
	@Test
	public void illegalArgumentExceptionThrownWhenReferringNegativeColumn() {
		try {
			grid.get(-1, 0);
			fail("Should throw exception");
		} catch (IllegalArgumentException expected) {
			// Success
		}
	}
	
	@Test
	public void illegalArgumentExceptionThrownWhenReferringNegativeRow() {
		try {
			grid.get(0, -1);
			fail("Should throw exception");
		} catch (IllegalArgumentException expected) {
			// Success
		}
	}
	
	@Test
	public void illegalArgumentExceptionThrownWhenReferringColumnsLargerThan8() {
		try {
			grid.get(9, 0);
			fail("Should throw exception");
		} catch (IllegalArgumentException expected) {
			// Success
		}
	}
	
	@Test
	public void illegalArgumentExceptionThrownWhenReferringRowsLargerThan8() {
		try {
			grid.get(0, 9);
			fail("Should throw exception");
		} catch (IllegalArgumentException expected) {
			// Success
		}
	}
	
	@Test
	public void setASymbolAndRetreiveItAgain() {
		grid.set(ONE, 1, 1);
		assertEquals(ONE, grid.get(1, 1));
	}
	
	@Test
	public void emptyGridIsNotSolved() {
		assertFalse(grid.isSolved());
	}
	
	@Test
	public void emptyGridIsValid() {
		assertTrue(grid.isValid());
	}
	
	@Test
	public void partlySolvedGridIsValid() {
		SudokuGrid easy = easyGrid();
		assertTrue(easy.isValid());
	}
	
	@Test
	public void partlySolvedGridIsNotSolved() {
		Grid easy = easyGrid();
		assertEquals("  6 25 41\n7 3819   \n 5   3   \n3   64 12\n 1 5 27 8\n   17  9 \n2 8   63 \n4       7\n  1 8 9 4\n", easy.toString());
	}
	
	@Test
	public void gridIsInvalidIfSymbolOccurTwiceInSameRow() {
		SudokuGrid easy = easyGrid();
		easy.set(ONE, 1, 1);
		assertFalse(easy.isValid());
		
		easy = easyGrid();
		easy.set(TWO, 4, 7);
		assertFalse(easy.isValid());
	}

	@Test
	public void gridIsInvalidIfSymbolOccurTwiceInSameColumn() {
		SudokuGrid easy = easyGrid();
		easy.set(THREE, 1, 9);
		assertFalse(easy.isValid());		
	}
	
	@Test
	public void gridIsInvalidIfSymbolOccurTwiceInSameSubGrid() {
		SudokuGrid easy = easyGrid();
		easy.set(FOUR, 7, 2);
		assertFalse(easy.isValid());		
	}
	
	// __6 _25 _41
	// 7_3 819 ___
	// _5_ __3 ___
	// 3__ _64 _12
	// _1_ 5_2 7_8
	// ___ 17_ _9_
	// 2_8 ___ 63_
	// 4__ ___ __7
	// __1 _8_ 9_4
	private SudokuGrid easyGrid() {
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
	
	@Test
	public void gridIsSolvedIfValidAndWithoutEmptySymbol() {
		SudokuGrid grid = easyGrid();
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
		
		assertTrue("Grid should be valid.", grid.isValid());
		assertTrue("Grid should be solved.", grid.isSolved());
	}
	
}

package nu.snart.rickard.sudoku;
import org.junit.Test;

import nu.snart.rickard.sudoku.model.Square;
import nu.snart.rickard.sudoku.model.Symbol;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SquareTest {

	@Test
	public void aNewSquareIsEmpty() {
		assertTrue(Square.create().isEmpty());
	}
	
	@Test
	public void aSquareWithASymbolIsNotEmpty() {
		Square square = Square.create();
		square.set(Symbol.ONE);
		assertFalse(square.isEmpty());
	}
	
	@Test
	public void aSquareCanReturnTheSymbolGivenToIt() {
		Square square = Square.create();
		square.set(Symbol.ONE);
		assertEquals(Symbol.ONE, square.getSymbol());
	}
}

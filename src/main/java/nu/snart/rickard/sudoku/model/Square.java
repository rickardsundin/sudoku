package nu.snart.rickard.sudoku.model;

/**
 * A square is the smallest component in a grid.
 * It can contain one Symbol.
 */
public class Square {

	/**
	 * A factory method that creates an empty square.
	 * @return a square
	 */
	public static Square create() {
		return new Square();
	}

	/** A square can contain a symbol, or be empty. */
	private Symbol symbol;

	/**
	 * A square is empty if it does not contain a symbol.
	 * @return true if this square is empty
	 */
	public boolean isEmpty() {
		return symbol==null;
	}

	/**
	 * Assign a symbol to this square.
	 * If this square is not empty when this method is called,
	 * the previous symbol will be replaced. 
	 * @param symbol
	 */
	public void set(Symbol symbol) {
		this.symbol = symbol;
	}

	/**
	 * Get the symbol assigned to this square.
	 * @return the symbol assigned to this square
	 */
	public Symbol getSymbol() {
		return symbol;
	}	
	
}

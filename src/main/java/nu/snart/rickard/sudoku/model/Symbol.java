package nu.snart.rickard.sudoku.model;

/**
 * A symbol represents the content of a square in the Sudoku grid.
 */
public enum Symbol {
	/** Represents an empty grid. */
	EMPTY(' '),
	ONE('1'),
	TWO('2'),
	THREE('3'),
	FOUR('4'),
	FIVE('5'),
	SIX('6'),
	SEVEN('7'),
	EIGHT('8'),
	NINE('9');	
	
	char ch;
	Symbol(char c) {
		ch = c;
	}
	
	public String toString() {
		return Character.toString(ch);
	}
	
	/**
	 * Return the symbol matching provided character or null if no matching symbol exists.
	 * @param ch
	 * @return the symbol matching provided character
	 */
	public static Symbol forChar(char ch) {
		switch (ch) {
		case '1' : return ONE;
		case '2' : return TWO;
		case '3' : return THREE;
		case '4' : return FOUR;
		case '5' : return FIVE;
		case '6' : return SIX;
		case '7' : return SEVEN;
		case '8' : return EIGHT;
		case '9' : return NINE;
		case ' ' : return EMPTY;
		default: return null;
		}
	}
}

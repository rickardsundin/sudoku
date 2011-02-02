package nu.snart.rickard.sudoku.model;

public class Position {

	private int column;
	private int row;

	public Position(int column, int row) {
		this.column = column;
		this.row = row;
	}
	
	public int column() {
		return column;
	}
	
	public int row() {
		return row;
	}

	@Override
	public boolean equals(Object obj) {
		return this==obj || (obj instanceof Position) && equals((Position) obj);
	}
	
	public boolean equals(Position position) {
		return column==position.column && row==position.row;
	}

	@Override
	public int hashCode() {
		return 256*column+row;
	}
	
	
}

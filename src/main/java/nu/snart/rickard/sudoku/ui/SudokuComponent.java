package nu.snart.rickard.sudoku.ui;

import static nu.snart.rickard.sudoku.model.Symbol.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;

import nu.snart.rickard.sudoku.model.Position;
import nu.snart.rickard.sudoku.model.SudokuGrid;
import nu.snart.rickard.sudoku.model.Symbol;


@SuppressWarnings("serial")
public class SudokuComponent extends JComponent {

	private final int SQUARE_SIZE = 30;
	private final int[] linePoints = new int[] { 
			2+SQUARE_SIZE,
			3+SQUARE_SIZE*2,
			4+SQUARE_SIZE*3,
			5+SQUARE_SIZE*3,
			6+SQUARE_SIZE*4,
			7+SQUARE_SIZE*5,
			8+SQUARE_SIZE*6,
			9+SQUARE_SIZE*6,
			10+SQUARE_SIZE*7,
			11+SQUARE_SIZE*8
		};	
	
	private Font font;
	private SudokuGrid data = new SudokuGrid();
	private Position focus = new Position(1,1);
//	private int focusCol = 1;
//	private int focusRow = 1;

	SudokuComponent() {
		setFocusable(true);
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				repaint();
			}
			@Override
			public void focusLost(FocusEvent e) {
				repaint();
			}
		});
		
		addKeyListener(new KeyAdapterExtension());
	}
	
	@Override
	public Dimension getMinimumSize() {
		int fullSize = SQUARE_SIZE*9+14;
		return new Dimension(fullSize, fullSize);
	}

	@Override
	public Dimension getPreferredSize() {
		return getMinimumSize();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawGrid(g);
		drawNumbers(g);
		if (hasFocus()) {
			drawFocus(g);
		}
	}

	public void setData(SudokuGrid data) {
		this.data = data;
	}
	
	public SudokuGrid getData() {
		return data;
	}
	
	@Deprecated
	public void setFocus(int column, int row) {
		focus = new Position(column, row);
	}
	
	public void setFocus(Position position) {
		focus = position;
	}
	
	public void setFont(Font font) { 
		this.font = font;
	}
	
	public Font getFont() {
		return font;
	}

	private void drawGrid(Graphics g) {
		g.drawRect(0, 0, SQUARE_SIZE*9+13, SQUARE_SIZE*9+13);
		g.drawRect(1, 1, SQUARE_SIZE*9+11, SQUARE_SIZE*9+11);
		
		for (int linePoint : linePoints) {
			g.drawLine(2, linePoint, SQUARE_SIZE*9+11, linePoint);
			g.drawLine(linePoint, 2, linePoint, SQUARE_SIZE*9+11);
		}
	}	

	private void drawNumbers(Graphics g) {
		g.setFont(font);
		for (int row=1; row<=9; row++) 
			for (int col=1; col<=9; col++)
				drawSymbolAt(g, col, row);
	}
	
	private void drawSymbolAt(Graphics g, int column, int row) {
		final int xOffset = 8;
		final int yOffset = 23;
		Symbol symbol = getData().get(column, row);
		if (symbol==EMPTY) {
			return;
		}
		if (!getData().isValid() && column==focus.column() && row==focus.row()) {
			Color color = g.getColor();
			g.setColor(Color.red);
			g.drawString(symbol.toString(), xOffset+getOffset(column), yOffset+getOffset(row));	
			g.setColor(color);
		} else if (getData().isSolved()) {
			Color color = g.getColor();
			g.setColor(Color.darkGray);
			g.drawString(symbol.toString(), xOffset+getOffset(column), yOffset+getOffset(row));	
			g.setColor(color);
		}
		else {
			g.drawString(symbol.toString(), xOffset+getOffset(column), yOffset+getOffset(row));	
		}
	}
	
	private void drawFocus(Graphics g) {
		Color defaultColor = g.getColor();
		g.setColor(Color.red);
		g.drawRect(getPosition(focus.column()), getPosition(focus.row()), SQUARE_SIZE-1, SQUARE_SIZE-1);
		g.setColor(defaultColor);
	}
			
	private int getOffset(int i) {
		switch (i) {
		case 1: return 2;
		case 2: return 2+SQUARE_SIZE;
		case 3: return 3+SQUARE_SIZE*2;
		case 4: return 5+SQUARE_SIZE*3;
		case 5: return 6+SQUARE_SIZE*4;
		case 6: return 7+SQUARE_SIZE*5;
		case 7: return 9+SQUARE_SIZE*6;
		case 8: return 10+SQUARE_SIZE*7;
		case 9: return 11+SQUARE_SIZE*8;
		default: throw new IllegalArgumentException("Invalid value");
		}
	}
	
	private int getPosition(int i) {
		switch (i) {
		case 1: return 2;
		case 2: return 3+SQUARE_SIZE;
		case 3: return 4+SQUARE_SIZE*2;
		case 4: return 6+SQUARE_SIZE*3;
		case 5: return 7+SQUARE_SIZE*4;
		case 6: return 8+SQUARE_SIZE*5;
		case 7: return 10+SQUARE_SIZE*6;
		case 8: return 11+SQUARE_SIZE*7;
		case 9: return 12+SQUARE_SIZE*8;
		default: throw new IllegalArgumentException("Invalid value");
		}
	}		
	
	private final class KeyAdapterExtension extends KeyAdapter {
		
		/**
		 * Pressing the arrow keys moves focus within the component.
		 */
		@Override
		public void keyPressed(KeyEvent e) {
			if (!getData().isValid()) {
				getData().set(EMPTY, focus.column(), focus.row());
			}
			
			switch (e.getKeyCode()) {
			case KeyEvent.VK_RIGHT: moveFocusRight(); break;
			case KeyEvent.VK_LEFT: moveFocusLeft(); break;
			case KeyEvent.VK_DOWN: moveFocusDown(); break;
			case KeyEvent.VK_UP: moveFocusUp(); break;
			default : // Do nothing 
			}
			
			repaint();
		}
		
		void moveFocusRight() {
			if (focus.column()<9) {
				focus = new Position(focus.column()+1, focus.row());
			}
		}
		
		void moveFocusLeft() {
			if (focus.column()>1) {
				focus = new Position(focus.column()-1, focus.row());
			}
		}
		
		void moveFocusDown() {
			if (focus.row()<9) {
				focus = new Position(focus.column(), focus.row()+1);
			}
		}
		
		void moveFocusUp() {
			if (focus.row()>1) {
				focus = new Position(focus.column(), focus.row()-1);
			}
		}

		/**
		 * Typing a number or space sets a symbol in the sudoku grid.
		 */
		@Override
		public void keyTyped(KeyEvent e) {
			Symbol symbol = Symbol.forChar(e.getKeyChar());
			if (symbol==null) {
				return;
			}
			getData().set(symbol, focus.column(), focus.row());
			repaint();
		}
		
	}
}

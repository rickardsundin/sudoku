package nu.snart.rickard.sudoku.ui;

import java.awt.Component;
import java.awt.Font;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JList;

/**
 * This component lets user select a font from a fixed list.
 */
@SuppressWarnings("serial")
public class FontSelector extends JComboBox {
	
	/** Available fonts. */
	private static final Font[] fonts = new Font[] {
			new Font("Casual", Font.PLAIN, 24),
			new Font("Chalkboard", Font.PLAIN, 24),
			new Font("Handwriting - Dakota", Font.PLAIN, 24),
			new Font("Marker Felt", Font.PLAIN, 24)
	};
	
	FontSelector() {
		super(fonts);
		setFont(getSelectedFont().deriveFont(18f));
		setRenderer(new FontListCellRenderer());
	}

	class FontListCellRenderer extends DefaultListCellRenderer {
		@Override
		public Component getListCellRendererComponent(JList list,
				Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			Component listCellRendererComponent = super.getListCellRendererComponent(list, value, index, isSelected,
					cellHasFocus);
			setFont((Font) value);
			setText(((Font) value).getName());
			return listCellRendererComponent;
		}
	}
	
	/**
	 * Returns the currently selected font.
	 * @return the currently selected font
	 */
	public Font getSelectedFont() {
		return (Font) getSelectedItem();
	}

	public static List<Font> getFonts() {
		return Arrays.asList(fonts);
	}
}

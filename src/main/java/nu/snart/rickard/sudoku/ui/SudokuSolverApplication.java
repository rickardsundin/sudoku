package nu.snart.rickard.sudoku.ui;

import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import nu.snart.rickard.sudoku.model.Repository;
import nu.snart.rickard.sudoku.model.SudokuGrid;

@SuppressWarnings("serial")
public class SudokuSolverApplication extends JFrame {

	private final class SolveActionListener implements ActionListener, PropertyChangeListener {
		public void actionPerformed(ActionEvent e) {
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			progressBar.setValue(0);
			progressBar.setString("Solving...");
			progressBar.setStringPainted(true);
			progressBar.setVisible(true);
			pack();
			SudokuGrid data = sudokuComponent.getData();

			SolverWorker solverWorker = new SolverWorker(data);
			solverWorker.addPropertyChangeListener(this);
			solverWorker.execute();
		}

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if ("progress".equals(evt.getPropertyName())) {
				int progress = ((SolverWorker) evt.getSource()).getProgress();
				progressBar.setValue(progress);
			}
			else if ("state".equals(evt.getPropertyName())
	                 && SwingWorker.StateValue.DONE == evt.getNewValue()) {
	             SudokuGrid solution;
				 try {
					solution = ((SolverWorker) evt.getSource()).get();
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
				setData(solution);
				progressBar.setVisible(false);
				pack();
				repaint();
				setCursor(null); // turn off the wait cursor
	         }
		}
	}

	private SudokuComponent sudokuComponent;
	private FontSelector fontSelector;
	private JProgressBar progressBar;

	public static void main(String[] args) {
		SudokuSolverApplication sudokuWindow = new SudokuSolverApplication();
		sudokuWindow.init();
		sudokuWindow.pack();
		sudokuWindow.setData(new Repository().partlySolvedHardSudoku());
		sudokuWindow.setVisible(true);
	}

	private void setData(SudokuGrid data) {
		sudokuComponent.setData(data);
	}

	@Override
	public void setDefaultCloseOperation(int operation) {
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void init() {
		setTitle("Sudoku Solver");
		
		JPanel panel = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		panel.setLayout(layout);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0; 
		constraints.insets = new Insets(5, 20, 5, 20);
		
		JPanel fontPanel = new JPanel();
		fontPanel.add(new JLabel("Font: "));
		
		fontSelector = new FontSelector();
		fontPanel.add(fontSelector);
		layout.setConstraints(fontPanel, constraints);
		panel.add(fontPanel);
		
		sudokuComponent = new SudokuComponent();
		panel.add(sudokuComponent);
		layout.setConstraints(sudokuComponent, constraints);


		progressBar = new JProgressBar(0,81);
		layout.setConstraints(progressBar, constraints);
		progressBar.setVisible(false);
		panel.add(progressBar);
		
		JPanel buttonPanel = createButtonPanel();
		layout.setConstraints(buttonPanel, constraints);
		panel.add(buttonPanel);
		
		add(panel);
		
		sudokuComponent.setFont(fontSelector.getSelectedFont());
		hookListeners();
	}

	private JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel();
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sudokuComponent.setData(new SudokuGrid());
				repaint();
			}			
		});
		buttonPanel.add(resetButton);
		
		JButton solveButton = new JButton("Solve");
		solveButton.addActionListener(new SolveActionListener());
		buttonPanel.add(solveButton);
		return buttonPanel;
	}
	
	private void hookListeners() {
		fontSelector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				sudokuComponent.setFont(fontSelector.getSelectedFont());
				repaint();
			}});
	}
}

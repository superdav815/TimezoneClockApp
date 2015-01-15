package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import model.ClockPanelModel;

public class ClockFrame implements CreateClockEventListener {

	private JFrame frame;
	private ClockPanel clockPanel;
	private CreateClockDialog createClockDialog;
	private ClockPanelModel clockPanelModel;
	private GridLayout gl = new GridLayout(3, 3);

	public ClockFrame() {
		gl.setHgap(3);
		gl.setVgap(3);
		createPartControl();
	}

	public Component[] getClockPanelComponents() {
		return frame.getComponents();
	}

	public void removeClockPanel(int index) {
		frame.getContentPane().remove(index);
	}

	public void removeClockPanel(Component comp) {
		frame.getContentPane().remove(comp);
	}
	
	public void createPartControl() {
		frame = new JFrame("Timezone Clocks");
		createClockDialog = new CreateClockDialog(this);
		createClockDialog.setClockEventListener(this);

		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				frame.getContentPane().removeAll();
				exitProcedure();
			}
		});
		frame.setSize(600, 600);
		frame.setLayout(gl);
		frame.setJMenuBar(createMenuBar());
		// frame.setResizable(false);

		frame.setSize(600, 600);
		frame.setVisible(true);
	}

	public void exitProcedure() {
		frame.dispose();
		System.exit(0);
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");

		JMenuItem createClock = new JMenuItem("New Clock...");
		JMenuItem deleteClock = new JMenuItem("Delete Clock");

		fileMenu.add(createClock);
		fileMenu.add(deleteClock);

		createClock.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				createClockDialog.setVisible(true);
			}
		});

		deleteClock.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (frame.getContentPane().getComponentCount() == 0) {
					JOptionPane.showMessageDialog(null, "No clocks to delete!");
				} else {
					frame.getContentPane().remove(frame.getContentPane().getComponentCount()-1);
					frame.getContentPane().repaint();
				}

			}
		});

		menuBar.add(fileMenu);
		return menuBar;
	}

	@Override
	public void ClockEventOccurred(ClockEvent e) {
		clockPanelModel = e.getModel();
		clockPanel = new ClockPanel(this, clockPanelModel);

		frame.add(clockPanel);
		frame.getContentPane().revalidate();
	}
}

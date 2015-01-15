package view;
import javax.swing.SwingUtilities;

/*
 * Standard class for starting up Swing application
 */
public class App implements Runnable{

	@Override
	public void run() {
		new ClockFrame();
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new App());
	}
	
	
}

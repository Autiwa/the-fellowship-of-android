package graphics;

/**
 * This class aim to be the parent class of all the other windows. This class is not supposed to be used as it. 
 */


import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public abstract class Window extends JFrame implements WindowListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// The container that will receive all the components of the window
	protected Panel contentPane = new Panel();
	
	public Window() {
		super();
		this.addWindowListener(this);
		this.setSize(800, 350);
		this.setContentPane(contentPane);
		
        //We want the window to be centered
        this.setLocationRelativeTo(null);
            
        this.setVisible(true);
        
        main.Main.windowList.add(this);
        
        
	}	



	@Override
	public void windowClosing(WindowEvent e){
		// The cross will act to delete the current window
		this.dispose();
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// We delete the current window from the list when closing. 
		main.Main.windowList.remove(this);
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}
}




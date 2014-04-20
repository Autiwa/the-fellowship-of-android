package graphics;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
 
public class Panel extends JPanel {
 
        /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private int posX = -50;
        private int posY = -50;
        
        public void paintComponent(Graphics g){
            /* The two following lines are here to mask the previous display 
             * and have a nice display when refreshing the components
             */
            g.setColor(Color.white);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());      
    }
        
        public int getPosX() {
                return posX;
        }
 
        public void setPosX(int posX) {
                this.posX = posX;
        }
 
        public int getPosY() {
                return posY;
        }
 
        public void setPosY(int posY) {
                this.posY = posY;
        }
        
}
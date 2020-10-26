package presentacion;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class EventoTeclado extends KeyAdapter {
	public static boolean a,d,left,right;
	public static boolean pause = false;
	    
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if( e.getKeyCode() == KeyEvent.VK_P){
			pause = true;
		}

		if( e.getKeyCode() == KeyEvent.VK_A ){
			a = true;
		}
		
		if( e.getKeyCode() == KeyEvent.VK_D ){
			d = true;
		}
		
		if( e.getKeyCode() == KeyEvent.VK_LEFT){
			left = true;
		}
		
		if( e.getKeyCode() == KeyEvent.VK_RIGHT ){
			right = true;
		}
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if( e.getKeyCode() == KeyEvent.VK_P){
			pause = false;
		}
		
		if( e.getKeyCode() == KeyEvent.VK_A ){
			a = false;
		}
		
		if( e.getKeyCode() == KeyEvent.VK_D ){
			d = false;
		}
		
		if( e.getKeyCode() == KeyEvent.VK_LEFT){
			left = false;
		}
		
		if( e.getKeyCode() == KeyEvent.VK_RIGHT ){
			right = false;
		}
		
		
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

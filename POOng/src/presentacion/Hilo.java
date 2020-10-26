package presentacion;

import java.io.Serializable;

/**
 * clase que realiza el movimiento del juego
 * 
 * @author Miguel Angel Fuquene Arias 
 * @author Ivan Camilo Rincon Saavedra
 * 
 * @version 1.0 20/04/2020
 * */
public class Hilo  extends Thread implements Serializable{
	private static final long serialVersionUID = -5017939998609160484L;
	boolean ejecute; 
	PanelJuego p;
	
	
	/**
	 * Constructor de la clase Hilo
	 * @param p, ingresa el panel en el cual se dibujara el juego 
	 *  
	 * */
	public Hilo( PanelJuego p){
		ejecute = true;
		this.p = p;
		
	}

	public void run(){
		while( ejecute ){
			try{
				p.repaint();
			}
			catch(Exception e){
				
			}
		}
				
	}
	
	public void inicieEjecucion(){
		ejecute = true;
	}
	
	public void pareEjecucion(){
		ejecute = false;
	}
	

}

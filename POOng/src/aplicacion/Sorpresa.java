package aplicacion;
import java.awt.geom.Rectangle2D;


/**
 * @author Miguel Angel Fuquene Arias 
 * @author Ivan Camilo Rincon Saavedra
 * 
 * @version 1.0 20/04/2020
 * clase que representa la clase Sorpresa del juego Poong
 * 
 * Los poderes aparecerán cada cierto tiempo en la zona de juego de manera aleatoria para que los
 *jugadores en ningún momento conozcan cada cuánto aparecerá un poder y de qué tipo será este. 
 * */
public abstract class Sorpresa extends Elemento{
	private static final long serialVersionUID = -4482603314916560438L;
	protected Rectangle2D.Double shape;

	
	/**
	 * constructor de la clase Sorpresa
	 * @param xPos ,double que representa la posicion en x de la sorpresa
	 * @param yPos, double que representa la posicion en y de la sorpresa
	 * */
	public Sorpresa( double xPos, double yPos ){
		super(xPos,yPos,50,50);
		shape = new Rectangle2D.Double(xPosition,yPosition,getAncho(),getAlto());
	}
	

	/**
	 * metodo que se encarga de devolver la figura asociada a la Sorpresa
	 * */
	public Rectangle2D.Double getShape(){
		return shape;
	}
	


	/**
	 * metodo abstracto que llama al poder que se debe realizar  
	 * @param p, que representa el juego POOng
	 * */
	abstract void reaccionar(POOng p);
}

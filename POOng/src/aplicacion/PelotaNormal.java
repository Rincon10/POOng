package aplicacion;

import java.awt.geom.Ellipse2D;


/**
 * clase que representa un tipo de pelota de tenis que tiene el juego POOng
 * En POOng se permitir elegir la velocidad de la pelota: lenta, rápida
 * @author Miguel Angel Fuquene Arias 
 * @author Ivan Camilo Rincon Saavedra
 * 
 * @version 1.0 12/05/2020      
 * */
public class PelotaNormal extends Pelota {
	private static final long serialVersionUID = 5401316657482577856L;


	/**
	 * Constructor de la clase pelota normal
	 * @param xPosition, double que representa la posicion en x de la pelota
	 * @param yPosition, double que representa la posicion en y de la pelota
	 * @param speed, double que representa la velocidad de la velocidad de la pelota
	 * */
	public PelotaNormal(double xPosition, double yPosition, double speed){
		super( xPosition, yPosition,speed);
	}
	
	
	/**
	 * metodo que se encarga de mover la pelota por el tablero 
	 * */
	public void move(){
		if( !( paused) ){
			rebotar();
			xPosition += dx;
			yPosition += dy;
			super.shape = new Ellipse2D.Double(xPosition,yPosition,getAncho(),getAlto() );
		}
	}
}

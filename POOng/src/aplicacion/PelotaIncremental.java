package aplicacion;

import java.awt.geom.Ellipse2D;


/**
 * clase que representa un tipo de pelota de tenis que tiene el juego POOng
 * (aumenta gradualmente durante la partida. Se reinicia cada vez que alguno de los jugadores hace un punto).
 * @author Miguel Angel Fuquene Arias 
 * @author Ivan Camilo Rincon Saavedra
 * 
 * @version 1.0 12/05/2020      
 * */
public class PelotaIncremental extends Pelota {
	private static final long serialVersionUID = 115414507555840859L;
	private static final int increase = 359 ;
	private int wait = 2;
	private double limit;
	
	
	/**
	 * Constructor de la clase pelota incremental
	 * @param xPosition, double que representa la posicion en x de la pelota
	 * @param yPosition, double que representa la posicion en y de la pelota
	 * @param speed, double que representa la velocidad de la velocidad de la pelota
	 * */
	public PelotaIncremental (double xPosition, double yPosition, double speed){
		super( xPosition, yPosition,speed);
		limit = 0.8;
	}
	
	/**
	 * Metodo que que se encarga de mover la pelota incrementando su velocidad
	 * */
	
	public void move(){
		if( !( paused)   ){
			if( (Math.abs(dy) <limit && (increase%wait == 0) ) ){
				increaseSpeed();
				wait = 2;
			}
			wait=( wait < increase )? wait+1:wait;
			super.rebotar();
			xPosition += dx;
			yPosition += dy;
			super.shape = new Ellipse2D.Double(xPosition,yPosition,getAncho(),getAlto() );
			
		}
	}
	
	
	/**
	 * Metodo que que se encarga de incrementar la velocidad de la pelota
	 * */
	
	public void increaseSpeed(){
		dy= ( dy < 0) ? dy+-0.01:dy+0.01;
	}
	
	
	/**
	 * Metodo que que se encarga de disminuir la velocidad de la pelota
	 * */
	public void decreaseSpeed(){
		if( Math.abs(dx) != 1  ){
			dy=  ( dy < 0) ?dy+-0.0001:dy+0.0001;
		}
	}

	

}

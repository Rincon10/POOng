package aplicacion;

/**
 * clase FastBall que representa el poder :
 * Fastball: aumenta la velocidad de la pelota en un 20% 
 * 
 * @author Miguel Angel Fuquene Arias 
 * @author Ivan Camilo Rincon Saavedra
 * @version 1.0 04/05/2020        
 * * */

public class FastBall extends Sorpresa {
	private static final long serialVersionUID = 6943069956371802981L;


	/**
	 * constructor de la clase FastBall 
	 *@param xPos ,double que representa la posicion en x de la sorpresa
	 *@param yPos, double que representa la posicion en y de la sorpresa
	 * */
	public FastBall( double xPos, double yPos ){
		super( xPos,yPos);
	}
	
	
	/**
	 * metodo  que ejecuta al poder que se debe realizar  
	 * @param p, que representa el juego POOng
	 * */
	void reaccionar(POOng p) {
		double cSpeed =p.getPelota(0).getSpeed();
		double newSpeed = cSpeed + (cSpeed*0.2); 
		p.getPelota(0).setSpeed(Math.abs(newSpeed),true);
	}

}

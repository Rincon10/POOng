package aplicacion;


/**
 * clase ColdRacket que representa el poder :
 * Cold Racket: permite que la raqueta del jugador que le pegó al poder convertir a la bola en
 *congeladora. De esta forma cuando golpee al jugador rival, este se congelara por 3s. 
 * 
 * @author Miguel Angel Fuquene Arias 
 * @author Ivan Camilo Rincon Saavedra
 * @version 1.0 14/05/2020        
 * * */

public class ColdRacket extends Sorpresa {
	private static final long serialVersionUID = -662206322390210900L;

	/**
	 * constructor de la clase ColdRacket 
	 *@param xPos ,double que representa la posicion en x de la sorpresa
	 *@param yPos, double que representa la posicion en y de la sorpresa
	 * */
	public ColdRacket ( double xPos, double yPos){
		super(xPos, yPos);
	}
	
	/**
	 * metodo  que ejecuta al poder que se debe realizar  
	 * @param p, que representa el juego POOng
	 * */
	void reaccionar(POOng p) {
		int index = p.getLastPlayer();
		if(!p.paused){
			p.getPersonaje(index).pause();
		}
	}

}

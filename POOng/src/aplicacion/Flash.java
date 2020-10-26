package aplicacion;


/**
 * clase Flash que representa el poder :
 * Flash: acelera la pelota hasta que el jugador contrario la devuelva o la deje ir. Luego de eso seguirá
 *teniendo la velocidad que tenía antes de ser ralentizada. 
 * 
 * @author Miguel Angel Fuquene Arias 
 * @author Ivan Camilo Rincon Saavedra
 * @version 1.0 14/05/2020        
 * * */

public class Flash extends Sorpresa {
	private static final long serialVersionUID = -6826857113621630297L;

	/**
	 * constructor de la clase principal Flash 
	 *@param xPos ,double que representa la posicion en x de la sorpresa
	 *@param yPos, double que representa la posicion en y de la sorpresa
	 * */
	public Flash(double xPos, double yPos){
		super( xPos,yPos);
	}
	
	/**
	 * metodo  que ejecuta al poder que se debe realizar  
	 * @param p, que representa el juego POOng
	 * */
	void reaccionar(POOng p) { 
		p.getPelota(0).setSpeed(0.8,false);
	}

}

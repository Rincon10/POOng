package aplicacion;


/**
 * clase FastBall que representa el poder :
 * Turtle: hace que el jugador contrario se mueva más despacio por cierto tiempo 
 
 * @author Miguel Angel Fuquene Arias 
 * @author Ivan Camilo Rincon Saavedra
 * @version 1.0 12/05/2020        
 * * */
public class Turtle extends Sorpresa {
	private static final long serialVersionUID = 268658280861165149L;

	/**
	 * constructor de la clase Turtle 
	 *@param xPos ,double que representa la posicion en x de la sorpresa
	 *@param yPos, double que representa la posicion en y de la sorpresa
	 * */
	public Turtle(double xPos,double yPos){
		super(xPos, yPos);
	}
	
	/**
	 * metodo que ejecuta al poder que se debe realizar  
	 * @param p, que representa el juego POOng
	 * */
	void reaccionar(POOng p) {
		int index = (p.getLastPlayer()==0)?1:0;
		p.getPersonaje(index).disminuirVelocidad();
	}

}

package aplicacion;


/**
 * clase FreezerBall que representa el poder :
 * Freezer: congela al jugador rival por un tiempo de 2s 
 * 
 * @author Miguel Angel Fuquene Arias 
 * @author Ivan Camilo Rincon Saavedra
 * @version 1.0 04/05/2020        
 * * */
public class Freezer extends Sorpresa {
	private static final long serialVersionUID = 2392886921498443970L;

	/**
	 * constructor de la clase Freezer 
	 *@param xPos ,double que representa la posicion en x de la sorpresa
	 *@param yPos, double que representa la posicion en y de la sorpresa
	 * */
	public Freezer( double xPos, double yPos ){
		super(xPos, yPos);
	}
	
	/**
	 * metodo que ejecuta al poder que se debe realizar  
	 * @param p, que representa el juego POOng
	 * */
	void reaccionar(POOng p){
		int index = (p.getLastPlayer()==0)?1:0;
		if(!p.paused){
			p.getPersonaje(index).pause();
		}
	}
}
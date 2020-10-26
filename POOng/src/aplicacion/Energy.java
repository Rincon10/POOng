package aplicacion;
/**
 * clase Energy que representa el poder :
 * otorga al jugador el 50% de la fortaleza perdida.
 * 
 * @author Miguel Angel Fuquene Arias 
 * @author Ivan Camilo Rincon Saavedra
 * 
 * @version 1.0 04/05/2020        
 * */

public class Energy extends Sorpresa {
	private static final long serialVersionUID = -8455354542040506953L;

	/**
	 * constructor de la clase Energy 
	 *@param xPos ,double que representa la posicion en x de la sorpresa
	 *@param yPos, double que representa la posicion en y de la sorpresa
	 * */
	public Energy(  double xPos, double yPos  ){
		super(xPos, yPos);
		
	}
	
	/**
	 * metodo abstracto que ejecuta al poder que se debe realizar  
	 * @param p, que representa el juego POOng
	 * */

	public void reaccionar(POOng p) {
		int index =  p.lastPlayer;
		double newStrenght = p.getPersonaje(index).getStrenght()/100 + 0.5;
		p.getPersonaje(index).setStrength(newStrenght);

	}

}

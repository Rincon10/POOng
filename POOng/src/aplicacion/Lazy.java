package aplicacion;


/**
 * clase que representa a el personaje de la CPU Lazy  
 * Lazy: su especialidad es ganar con el mínimo gasto de energía
 * 
 * @author Miguel Angel Fuquene Arias 
 * @author Ivan Camilo Rincon Saavedra
 * 
 * @version 1.0 20/05/2020
 * */

public class Lazy extends PersonajeCPU {
	private static final long serialVersionUID = 6818819111023045770L;
	private final int middle = 315;
	
	
	/**
	 * constructor del PersonajeCPU Lazy  del juego POOng
	 *@param xPosition, double que representa la posicion en x del personaje
	 *@param yPosition, double que representa la posicion en y del personaje
	 *@param xMax, double[] que representa lo maximo que podra moverse el personaje en la primera posicion sera lo maximo a a la izquierda y en la segunda sera lo maximo a derecha
	 * */
	public Lazy(double xPosition, double yPosition, double[] xMax){
		super(xPosition,yPosition,xMax);

	}
	
	
	/**
	 * metodo que se encarga de que la cpu evite el bloque
	 * @param game, variable que representa el juego POOng
	 * @param bloque, el bloque a evitar
	 * */
	@Override
	protected void evadirBloque(POOng game ,Bloque bloque ){
		double xBloque = bloque.getXposition();
		boolean valid = Math.abs( xBloque - xPosition) < 60 ; 
		if( valid && xBloque < xPosition ) {
			moveRight();
		}
		else{
			if(valid ){
				moveLeft();
			}
				
		}
		
	}
	
	
	/**
	 * metodo que se encarga que el jugador siga a la pelota 
	 * @param game, variable que representa el juego POOng
	 * @param pelota, la pelota a seguir
	 * */
	
	protected void buscarPelota( POOng game, Pelota pelota){
		int lastPlayer = game.getLastPlayer();
		double xPelota ;
		boolean valid, valid2;
		
		valid = ( lastPlayer == 0 )? pelota.getYposition() >= 265 : pelota.getYposition() < 265;
		valid2  = lastPlayer != gamePosition;
		
		xPelota  = pelota.getXposition();
		
		if( (valid && valid2) || ( lastPlayer == -1 ) ){
			if( xPelota < xPosition ){
				moveLeft();
			}
			else{
				moveRight();
			}	
		}
		else if ( valid2 ) {
			buscarCentro();
		}
	}
		
	private void buscarCentro(){
		if( xPosition < middle ){
			moveRight();
		}
		else if ( xPosition > middle ){
			moveLeft();
		}
	}

}

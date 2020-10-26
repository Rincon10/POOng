package aplicacion;
/**
 * clase que representa a el personaje de la CPU  
 * 
 * @author Miguel Angel Fuquene Arias 
 * @author Ivan Camilo Rincon Saavedra
 * 
 * @version 1.0 15/05/2020
 * @version 2.0 20/05/2020
 * @version 3.0 21/05/2020
 *     
 * */
public  class  PersonajeCPU extends Personaje {
	private static final long serialVersionUID = 9183534352438238869L;
	

	/**
	 * constructor del PersonajeCPU  del juego POOng
	 *@param xPosition, double que representa la posicion en x del personaje
	 *@param yPosition, double que representa la posicion en y del personaje
	 *@param xMax, double[] que representa lo maximo que podra moverse el personaje en la primera posicion sera lo maximo a a la izquierda y en la segunda sera lo maximo a derecha
	 * */
	public PersonajeCPU(double xPosition, double yPosition, double[] xMax) {
		super(xPosition,yPosition,xMax);
	}
	
	
	/**
	 * metodo que se encarga de que el personaje golpee la pelota
	 * @param game, que representa el juego POOng 
	 * @param pelota, representa la pelota actual del juego
	 * */
	public  void golpear( POOng game, Pelota pelota ){
		pelota.reverseD();
	}

	
	/**
	 * metodo que se encarga de mover un personaje de una persona
	 * @param game, que representa el juego POOng 
	 * */
	public void move( POOng game ){	
		Pelota pelota = game.getPelota(0);
		Bloque bloque = game.getBloque();
		boolean valid2  = (bloque.getDy() < 0 );
		
		if( game.getPersonaje(0).isInmune()  ||  game.getPersonaje(1).isInmune() || game.getLastPlayer() == -1 ){
			buscarPelota( game,pelota );
		}
		
		else if( bloque.isActive() && valid2 && gamePosition == 0  ){
			evadirBloque( game,bloque );
		}
		else if( bloque.isActive() && !valid2 && gamePosition == 1  ){
			evadirBloque( game,bloque );
		}
		else if (bloque.isActive() || !bloque.isActive()){
			buscarPelota( game,pelota );
		}
	
	}
	
	
	/**
	 * metodo que se encarga de que la cpu evite el bloque
	 * @param game, que representa el juego POOng 
	 * @param bloque, el bloque a evitar
	 * */
	protected void evadirBloque(POOng game,Bloque bloque ){
		double xBloque = bloque.getXposition();
		if(xBloque-1 < xPosition  ){
			moveRight();
		}
		else{
			if(xPosition <= xBloque ){
				moveLeft();
			}
		}
		
	}
	
	
	/**
	 * metodo que se encarga que el jugador siga a la pelota
	 * @param game, que representa el juego POOng  
	 * @param pelota, la pelota a seguir
	 * */
	protected void buscarPelota( POOng game ,Pelota pelota){
		double xPelota = pelota.getXposition();
		if( xPelota < xPosition ){
			moveLeft();
		}
		else{
			moveRight();
		}		
	}

}

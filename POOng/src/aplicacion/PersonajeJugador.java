package aplicacion;

import presentacion.EventoTeclado;

/**
 * clase que representa a el personaje de  un jugador
 * 
 * @author Miguel Angel Fuquene Arias 
 * @author Ivan Camilo Rincon Saavedra
 * 
 * @version 1.0 15/04/2020
 *     
 * */
public class PersonajeJugador extends Personaje {
	private static final long serialVersionUID = -4038236152897610716L;

	/**
	 * constructor del PersonajeJugador  del juego POOng
	 *@param xPosition, double que representa la posicion en x del personaje
	 *@param yPosition, double que representa la posicion en y del personaje
	 *@param xMax, double[] que representa lo maximo que podra moverse el personaje en la primera posicion sera lo maximo a a la izquierda y en la segunda sera lo maximo a derecha
	 * */
	public PersonajeJugador(double xPosition, double yPosition, double[] xMax) {
		super(xPosition,yPosition,xMax);
	}
	
	
	/**
	 * metodo que se encarga de que el personaje golpee la pelota
	 * @param game, que representa el juego POOng 
	 * @param pelota, representa la pelota actual del juego
	 * */
	public  void golpear( POOng p, Pelota pelota ){
		pelota.reverseD();
	}
	
	
	
	/**
	 * metodo que se encarga de mover un personaje de una persona
	 * @param game, que representa el juego POOng 
	 * */
	public void move( POOng game ){
		if( super.movePersonaje == 0 ){
			moverPersonaje1();
		}
		else{
			moverPersonaje2();
		}
	}
	
	
	
	/**
	 * metodo que se encarga de mover al personaje 1
	 * */
	public void moverPersonaje1(){
		if( EventoTeclado.a ){
			moveLeft();
		}
		
		if( EventoTeclado.d ){
			moveRight();
		}
	}	
	
	/**
	 * metodo que se encarga de mover al personaje 2
	 * */
	public void moverPersonaje2(){
		if( EventoTeclado.left ){
			moveLeft();
		}
		
		if( EventoTeclado.right){
			moveRight();
		}
	}

}

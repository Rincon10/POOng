package aplicacion;

import java.io.Serializable;




/**
 * clase que representa a el jugador que selecciona un personaje  del juego POOng
 * 
 * @author Miguel Angel Fuquene Arias 
 * @author Ivan Camilo Rincon Saavedra
 * 
 * @version 1.0 20/04/2020
 * @version 2.0 23/04/2020
 * @version 3.0 24/04/2020        
 * */
public class Jugador implements Serializable{
	private static final long serialVersionUID = -4656181440290598931L;
	private String name;
	private int score;
	private Personaje personaje;
	
	
	/**
	 * Constructor de la clase jugador
	 * @param name, nombre del jugador que se registro
	 * @param p, personaje que eligio cuando configuro el juego
	 * */
	public Jugador( String name, Personaje p){
		this.name  = name;
		personaje = p;
		score = 0;
		
	}

	/**
	 * metodo que se encarga de actualizar el mejor score
	 * @param newScore, el nuevo score a establecer 
	 * */
	public void setScore( int newScore ){
		score = newScore;
	}
	
	/**
	 * metodo que devuelve el personaje que eligio el jugador
	 * @return Personaje , personaje que elegido por el jugador
	 * */
	public Personaje getPersonaje(){
		return personaje;
	}
	
	
	/**
	 * metodo que devuelve el nombre del jugador
	 * @return String, nombre del personaje
	 * */
	public String getNombre(){
		return name;
	}
	
	
	/**
	 * metodo que se encarga de agregar puntos al puntaje actual
	 * @param score, los nuevos puntos a adicionar al puntaje actual
	 * */
	public void addScore( int score ){
		this.score+=score;
	}
	
	/**
	 * metodo que le suma puntos al jugador
	 * @return boolean, que dice si el jugador ya gano
	 * */
	public boolean punto(){
		score++;
		return winner();
		
	}
	
	/**
	 * metodo que retorna el punntaje actual del jugador 
	 * @param int, score que tiene le jugador actual 
	 * */
	public int getScore(){
		return score;
	}
	

	
	/**
	 * verifica si el jugador llego al numero de rondas maximo establecido maximo establecido
	 * @return boolean, que dice si gano o no
	 * */
	private boolean winner(){
		return ( POOng.maxScore == score );
	
	}
}

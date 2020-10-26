package aplicacion;

import java.awt.geom.Rectangle2D;


/**
 * clase abstracta que representa los personajes del juego POOng
 * 
 * @author Miguel Angel Fuquene Arias 
 * @author Ivan Camilo Rincon Saavedra
 * 
 * @version 1.0 20/04/2020
 * @version 2.0 23/04/2020
 * @version 3.0 24/04/2020  
 * @version 4.0 05/05/2020       
 * */
public abstract class Personaje extends Elemento{
	protected static final long serialVersionUID = 6112243834308839425L;
	protected int movePersonaje;
	protected int gamePosition;
	protected static final double INITIALSPEED=0.8; 
	protected double strength;
	protected double dx;
	protected double[] xMax;
	protected boolean inmunidad;
	protected Rectangle2D.Double shape;
	
	
	/**
	 * constructor del personaje del juego POOng
	 *@param xPosition, double que representa la posicion en x del personaje
	 *@param yPosition, double que representa la posicion en y del personaje
	 *@param xMax, double[] que representa lo maximo que podra moverse el personaje en la primera posicion sera lo maximo a a la izquierda y en la segunda sera lo maximo a derecha
	 * */
	public Personaje( double xPosition, double yPosition, double[] xMax){
		super(xPosition,yPosition,50,80);
		strength = 1;
		dx=INITIALSPEED;
		this.paused = false;
		this.xMax=xMax;
		inmunidad = false;
		shape = new Rectangle2D.Double(xPosition,yPosition,getAncho(),getAlto());
	}
	
	
	/**
	 * metodo el cual establece el indice del personaje en el juego
	 * @param index, el indice que recibio el personaje en el juego
	 * */
	public void setGamePosition( int index ){
		gamePosition = index;
	}
	
	/**
	 * metodo que devuelve el indice del personaje en el juego
	 * @return int, el indice que recibio el personaje en el juego
	 * */
	public int getGamePosition( ){
		return gamePosition;
	}
	
	
	/**
	 * metodo que causa que un personaje sea inmune o no a la caida de un bloque
	 * @param i,boolean que dice el si sera inmune o no 
	 * */
	public void setInmunidad( boolean i){
		inmunidad = i;
	}
	
	/**
	 * metodo que dice si el personaje es inmune o no
	 * @return boolean, diciendo si el personaje es inmune 
	 * */
	public boolean isInmune( ){
		return inmunidad;
		
	}
	
	
	/**
	 * metodo que se encarga de devolver la figura asociada a el personaje
	 * */
	public Rectangle2D.Double getShape(){
		return shape;
	}
	
	
	/**
	 * metodo que establece que personaje se puede mover segun un indice
	 * @param index, entero que representa el indice del personaje que se podra mover
	 * */
	public void setMove( int index ){
		movePersonaje = index;
	}
	
	
	/**
	 * metodo que retorna la figura asociada al personaje
	 * @return Rectangle2D.Double , figura asociada al personaje 
	 * */
	public Rectangle2D.Double  getshape(){
		return shape;
	}
	
	
	/**
	 * metodo que se encarga de que el personaje golpee la pelota
	 * @param game, que representa el juego POOng 
	 * @param pelota, representa la pelota actual del juego
	 * */
	public abstract void golpear( POOng p, Pelota pelota );
	
	/**
	 * metodo abstracto que se encarga de mover al personaje
	 * @param game, que representa el juego POOng 
	 * */
	
	public abstract void move( POOng game );
	
	
	/**
	 * metodo encargado de desplazar el personaje a la izquierda
	 * */
	public void moveLeft() {
		if ( !paused && xPosition-dx > xMax[0] ){
			xPosition-=dx;
			shape = new Rectangle2D.Double(xPosition,yPosition,getAncho(),getAlto());
			this.dicreaseStrenght();
		}
	}
	
	/**
	 * metodo encargado de desplazar el personaje a la derecha
	 * */
	public void moveRight(){
		if ( !paused && xPosition+shape.width+dx < xMax[1] ){
			xPosition+=dx;
			shape = new Rectangle2D.Double(xPosition,yPosition,getAncho(),getAlto());
			this.dicreaseStrenght();
			
		}
	}
	
	/**
	 * metodo encargado de disminuir la fotaleza al personaje
	 * */
	public void dicreaseStrenght(){
		strength-=0.00001;
		//strength-=0.01;
	}
	
	/**
	 * metodo que encarga de actualizar la fotaleza del personaje
	 * @param newStrenght, la nueva fortaleza que tomara el personaje
	 */
	public void setStrength( double newStrenght){
		strength= ( newStrenght > 1 )?1:newStrenght;
		
	}
	
	/**
	 * metodo que dice si el personaje es capaz de seguir en el juego
	 * @return boolean, que nos dice si la fortaleza de este es menor al 50%
	 * */
	public boolean iGiveUp(){
		return  ( strength < 0.5 );
	
	}
	
	
	/**
	 * metodo que retorna la velocidad que tiene el personaje 
	 * @return  double, que representa la velodicidad actual del personaje 
	 * */
	public double getSpeed(){
		return Math.abs(dx);
	}
	
	
	/**
	 * metodo que retorna la fortaleza que tiene el personaje
	 * @return int,returna el porcentaje que le queda al jugador 
	 * */
	public double getStrenght(){
		return Math.ceil(strength *100);
	}
	
	
	/**
	 * metodo que le quita fortaleza a un personaje cuando es golpeado por un bloque
	 * */
	public void chocarConBloque(){
		if( !isInmune() ){
			strength-=0.5;
		}
		inmunidad = false;
		
	}
	
	
	/**
	 * metodo que restablece la velocidad inicial del personaje
	 * */
	public void restablecerVelocidad(){
		dx=INITIALSPEED;
	}
	
	/**
	 * metodo encargado de disminuir la velocidad del personaje
	 * */
	public void disminuirVelocidad(){
		dx=0.4;
	}
	
	/**
	 * metodo que dice si la velocidad del personaje se disminuyo
	 * @return boolean, que dice si a el personaje se le bajo la velocidad
	 * */
	public boolean isSlow(){
		return dx==0.4;
	}
	
	/**
	 * metodo que retorna el sector de la cancha se encuentra el personaje 
	 * @return int, retorna 0,1,2 segun donde se encuntre
	 * */
	public  int getSector(){
		if( xPosition>=130 && xPosition< 315 ){
			return 0;
		}
		else if (xPosition >= 315 && xPosition<= 365 ){
			return 1;
		}
		return 2;
		
	}
	
}

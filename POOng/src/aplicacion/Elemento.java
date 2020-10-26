package aplicacion;
import java.io.Serializable;
import javax.swing.ImageIcon;


/**
 * clase que representa un elemento del juego POOng
 * 
 * @author Miguel Angel Fuquene Arias 
 * @author Ivan Camilo Rincon Saavedra
 * 
 * @version 1.0 05/05/2020
 * @version 2.0 20/05/2020          
 * */
public class Elemento implements Serializable{
	private static final long serialVersionUID = -4130473059137946215L;
	protected int ancho;
	protected int alto;
	protected double xPosition;
	protected double yPosition;
	protected ImageIcon image;
	protected boolean paused;
	
	
	/**
	 * Constructor de la clase Elemento
	 * @param xPos, double que representa la posicion en x del elemento
	 * @param yPos, double que representa la posicion en y del elemento
	 * @param ancho, int que representa el ancho que tomara en el juego
	 * @param alto, int  que representa el largo que tomara en el  juego
	 * */
	public Elemento(double xPos, double yPos,int ancho,int alto){
		this.ancho= ancho;
		this.alto = alto;
		this.xPosition = xPos;
		this.yPosition = yPos;
	}
	
	
	/**
	 * metodo que se encarga de decir que el elemento se dirija a cierta posicion
	 * */
	public void goTo( double newX,double newY){
		xPosition = newX;
		yPosition = newY;
	}
	
	
	/**
	 * metodo que devuelve establece la nueva posicion en x del elemento
	 * */
	public void setXposition( double newX){
		xPosition = newX;
	}
	
	
	/**
	 * metodo que devuelve la posicion en x del elemento
	 * @return double, la posicion en x del elemento
	 * */
	public double getXposition(){
		return xPosition;
	}
	
	/**
	 * metodo que devuelve la posicion en y del eleento
	 * @return double, la posicion en y de la pelota
	 * */
	public double getYposition(){
		return yPosition;	
	}
	
	
	/**
	 * metodo que devuelve la posicion el ancho asignado al elemento
	 * @return int, el ancho asignado al elemento
	 * */
	public int getAncho(){
		return ancho;	
	}
	
	/**
	 * metodo que devuelve la posicion el alto asignado al elemento
	 * @return int, el alto asignado al elemento
	 * */
	public int getAlto(){
		return alto;	
	}
	
	
	/**
	 * metodo que se encarga de establecer la imagen que tomara el Elemento
	 * @param rute, la ruta de la imagen del elemento que desea tomar
	 * */
	
	public void setImage( String rute ){
		image = new ImageIcon(rute );	
	}
	
	/**
	 * metodo que se encarga de establecer la imagen que tomara el Elemento
	 * @param ImageIcon, la nueva imagen Rescalada segun se necesite
	 * */
	
	public void setImage( ImageIcon i ){
		image = i;	
	}
	
	
	/**
	 * metodo que se encarga de deolver la imagen establecida para el Elemento
	 * */
	public ImageIcon getImage(){
		return image;
	}
	
	
	/**
	 * metodo que nos dice si el elemento se puede mover
	 * @boolean,nos dice si el jugar se puede mover
	 * */
	public boolean isPaused(){
		return paused;
	}
		
	/**
	 *metodo encargado de pausar o quitar el pause a un elemento
	 */
	public void pause(){
		paused = !paused;
	}

}

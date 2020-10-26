package aplicacion;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.*;


/**
 * clase que representa la pelota de tenis que tiene el juego POOng
 * 
 * @author Miguel Angel Fuquene Arias 
 * @author Ivan Camilo Rincon Saavedra
 * 
 * @version 1.0 20/04/2020
 * @version 2.0 22/04/2020
 * @version 3.0 24/04/2020
 * @version 3.0 05/05/2020      
 * */
public abstract class Pelota extends Elemento{
	private static final long serialVersionUID = 8790172283313411955L;
	protected double dx =0.1,dy=0.4;
	protected double initialSpeed;
	protected int sali;
	protected Ellipse2D.Double shape;
	
	/**
	 * Constructor de la clase pelota
	 * @param xPosition, double que representa la posicion en x de la pelota
	 * @param yPosition, double que representa la posicion en y de la pelota
	 * */
	public Pelota( double xPosition, double yPosition ){
		super(xPosition, yPosition,12,12);
		this.paused = false;
		shape = new Ellipse2D.Double(xPosition,yPosition,getAncho(),getAlto() );

	}
	
	/**
	 * Constructor de la clase pelota
	 * @param xPosition, double que representa la posicion en x de la pelota
	 * @param yPosition, double que representa la posicion en y de la pelota
	 * @param speed, double que representa la velocidad de la velocidad de la pelota
	 * */
	public Pelota( double xPosition, double yPosition, double speed){
		super(xPosition, yPosition,12,12);
		this.paused = false; 
		shape = new Ellipse2D.Double(xPosition,yPosition,getAncho(),getAlto() );
		dx =0.1; dy =speed;
		initialSpeed = speed;
	}
	
	/**
	 * este metodo hace que la pelota reaparezca cuando sale de los limites
	 * */
	public void reaparecer(){
		Random r = new Random();
		dy = ( yPosition > 520 ) ?-initialSpeed:initialSpeed;
		this.xPosition = (r.nextInt(2) == 1) ?400: 200;
		this.yPosition = 200;
		
		dx =  0.1 + ( 0.2 - 0.1 ) * r.nextDouble();		
		shape = new Ellipse2D.Double(xPosition,yPosition,getAncho(),getAlto() );
	}
	

	
	/**
	 * Metodo que establece las posiciones de la pelota
	 * @param xPosition, double que representa la posicion en x de la pelota
	 * @param yPosition, double que representa la posicion en y de la pelota 
	 * */
	public void setPosition( int newXpos, int newYpos){
		this.xPosition = newXpos;
		this.yPosition = newYpos;
	}
	
	/**
	 * metodo que le establece a la pelota una velocidad dada
	 * @param s, la nueva velocidad que recibira la pelota
	 * @param change, booleano que dice si se desea actualizar la velocidad inicial
	 * */
	public void setSpeed( double s, boolean change){
		if ( change ){
			initialSpeed = s;
		}
		dy=(dy<0)?-s:s;
	}
	
	/**
	 * metodo que le devulve  la velocidad actual de la pelota
	 * @return double , la velocidad que posee la pelota
	 * */
	public double getSpeed(){
		return dy;
	}
	
	/**
	 * metodo que resetea la velocidad a su velocidad inicial 
	 * */
	public void resetToInitialSpeed(){
		dy = ( dy < 0)? -initialSpeed:initialSpeed;
	}
	
	/**
	 * metodo encargado de retornar la figura asociada a la pelota
	 * @return Ellipse2D.Double, figura asociada a la pelota de POOng
	 * */
	public Ellipse2D.Double getShape(){
		return shape;
	
	}
	
	
	/**
	 * metodo que se encarga de decir que el elemento se dirija a cierta posicion
	 * */
	public void goTo( double newX,double newY){
		super.goTo(newX, newY);
		shape = new Ellipse2D.Double(xPosition,yPosition,getAncho(),getAlto() );
	}
	
	/**
	 * metodo que se encarga  de cambiar la posicion de la pelota, ( mover la pelota)
	 * */
	public abstract void move();
	
	
	/**
	 * metodo que se encarga de cambiar la direccion a la cual se dirige la pelota
	 * */
	public void reverseD(){
		/**min + ( max - min ) * rand.nextDouble()**/
		Random r = new Random();
		dx =  0.1 + ( 0.2 - 0.1 ) * r.nextDouble();
		dx = ( r.nextInt(2) == 1) ?-dx:dx;
		dy = -dy;
	}
	
	
	/**
	 * metodo que se encarga que la pelota se dirija a la direccion ingresada
	 * @param newDx, la nueva direccion que tomara la pelota
	 * */
	private void reverseExtreme(double newDx ){
		dx = newDx;
		dy = -dy;
	}
	
	
	/**
	 * metodo que se encarga de elegir una direccion a la pelota golpeada por un extreme
	 * @param sectorP1, representa el sector en el que se encuentra el personaje 1
	 * @param sectorP2, representa el sector en el que se encuentra el personaje 2
	 * @param direction, representa el nuevo dx que tomara la pelota, que podra ser modificada por el metodo
	 * */
	public void golpeadoPorExtreme(int sectorP1, int sectorP2,double direction ){
		if( sectorP1 == 0){
			if( sectorP1 == sectorP2 ){
				reverseExtreme(3*direction);
			}
			else{
				reverseExtreme(0);
			}
		}
		else if( sectorP1 == 1){
			if( sectorP1 == sectorP2 ){
				Random rnd = new Random();
				int random = rnd.nextInt(2);
				reverseExtreme(( random == 1 )?2*direction:-2*direction);
			}
			else if ( sectorP2 == 0){
				reverseExtreme(2*direction);
			}
			else{
				reverseExtreme(-2*direction);
			}
		}
		else{
			if( sectorP1 == sectorP2 ){
				reverseExtreme(-3*direction);
			}
			else{
				reverseExtreme(0);
			}	
		}
	}
	
	/**
	 * metodo que se encarga de elegir una direccion a la pelota golpeada por un Sniper
	 * @param sectorP, representa el sector en el que se encuentra el personaje que le pego a la pelota
	 * @param direction, representa el nuevo dx que tomara la pelota, que podra ser modificada por el metodo
	 * @param game,variable que representa a el juego POOng
	 * */
	public void golpeadoPorSniper(int sectorP,double direction,POOng game ){
		int index = game.getLastPlayer(); 
		double xPos = game.getPersonaje(index).getXposition(); 
		
		if ( xPos >= 125  && xPos<=165 ){
			direccionPelotaSniper( game,  0 ,6*direction );
		}
		else if ( xPos >= 480  && xPos<=520 ){
			direccionPelotaSniper( game,  -6*direction  ,0);
		}
		
		else if( sectorP == 0 ){
			direccionPelotaSniper( game,  -5*direction ,5*direction );
		}
		else if ( sectorP == 1 ){
			direccionPelotaSniper( game, -3*direction ,3*direction );
			
		}
		else{
			direccionPelotaSniper( game, -5*direction, 0 );
		}
		
	}
	
	/**
	 * metodo que se encarga de elegir una direccion a la pelota golpeada por un Greedy
	 * @param sectorP, representa el sector en el que se encuentra el personaje que le pego a la pelota
	 * @param direction, representa el nuevo dx que tomara la pelota, que podra ser modificada por el metodo
	 * @param game,variable que representa a el juego POOng
	 * */
	public void golpeadoPorGreedy(int sectorP,double direction,POOng game ){
		int index = game.getLastPlayer(); 
		double xPos = game.getPersonaje(index).getXposition(); 
		
		if ( xPos >= 300  && xPos<=365 ){
			reverseExtreme(0);
		}		
		else if( sectorP == 0 ){
			reverseExtreme(3*direction);
		}
		else if ( sectorP == 1 ){
			reverseExtreme(0);
			
		}
		else{
			reverseExtreme(-3*direction);
		}
		
	}
	
	/**
	 *metodo que se encarga de elegir una direccion a la pelota que fue golpeada
	 *@param game,variable que representa a el juego POOng
	 *@param case1, cambio de la direccion dx para la pelota en caso que se cumpla el caso1 
	 *@param case2, cambio de la direccion dx para la pelota en caso que se cumpla el caso2
	 * */
	private void direccionPelotaSniper( POOng game,double case1, double case2  ){
		int cont = 0;
		for( int index = 0 ; index < game.getAmountObjetivos(); index++ ){
			Objetivo o = game.getObjetivo(index);
			if( o.getXposition() ==  125){
				reverseExtreme(case1);
			}
			else{
				reverseExtreme(case2);
			}
			cont++;
			if( cont >= 1 ){break;}
		}	
	}
	
	/**
	 * metodo que se encarga de decir si una pelota choco con un personaje
	 * @param shape, la figura asociada al personaje que deseamos saber si lo golpeo
	 * @return boolean, que dice si choco o no con un personaje
	 * */
	public boolean rebotaPersonaje( Rectangle2D.Double shape ){
		
		boolean valid = ((int)shape.getY() == (int)yPosition) ;
		if (valid && this.shape.getBounds2D().intersects(shape.getBounds2D()) ){
			return true;
		}
		return false;
	}
	
	/**
	 * metodo que se encarga de decir si una pelota choco con una sorpresa
	 * @param s, la sorpresa actual que tenga el juego
	 * @param p, la variable que representa el juego
	 * @return boolean, que dice si choco o no con una sorpresa
	 * */
	public boolean  chocarSorpresa( Sorpresa s,POOng p){
		if (this.shape.getBounds2D().intersects(s.getShape().getBounds2D() ) ){
			if( p.getLastSorpresa() != 4 ){
				s.reaccionar(p );
			}
			return true;
		}
		return false;
	}
	
	/**
	 * metodo que se encarga de decir si una pelota choco con un objetivo
	 * @param o ,objetivo el cual se va a revisar
	 * @return boolean, que dice si choco o no con un objetivo
	 * */
	public boolean chocarObjetivo( Objetivo  o){
		if (this.shape.getBounds2D().intersects(o.getShape().getBounds2D() ) ){
			return true;
		}
		return false;
		
	}
	
	
	/**
	 * Metodo encargado de hacer rebotar la pelota con los bordes de la cancha
	 * */
	public void rebotar(){
		sali = -1;
		if ( xPosition < 130 || xPosition > 500){
			dx=-dx;
		}
		if( yPosition < 15 || yPosition > 520){
			sali = (yPosition < 15)?1:0;
		}
		
	}
	
	
	/**
	 * metodo que dice si la pelota se salio en la cancha en la posicion y
	 * @return int,si la pelota se salio en la cancha, -1 si no se salio, 0 si se salio para el jugador 1 , 1 si se salio para el jugador 2 
	 * */
	public int getSali(){
		return sali;
	}
}

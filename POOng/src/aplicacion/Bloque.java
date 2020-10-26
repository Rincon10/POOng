package aplicacion;

import java.awt.geom.Rectangle2D;

/**
 * @author Miguel Angel Fuquene Arias 
 * @author Ivan Camilo Rincon Saavedra
 * 
 * @version 1.0 02/05/2020
 * @version 2.0 05/05/2020
 * @version 3.0 08/05/2020
 * 
 * clase que representa la clase Bloque del juego Poong:
 * Los bloques pueden aparecer en el campo de juego del oponente cuando un jugador golpea una sorpresa.
 *Si un jugador se deja golpear por un bloque pierde el 50% de la fortaleza que tenía en el momento
 * */
public class Bloque extends Elemento{
	private static  double dy;
	private double limit;
	private boolean active;
	private int killPlayer;
	private static final long serialVersionUID = -3949073615457912635L;
	private Rectangle2D.Double shape;
	
	
	/**
	 * constructor de la clase Bloque
	 * @param xPos ,double que representa la posicion en x del bloque
	 * @param yPos, double que representa la posicion en y del bloque
	 * @param index, indice del ultime personaje que activo la sorpresa 
	 * */
	public Bloque( double xPos, double yPos, int index ){
		super(xPos, yPos,50,50);
		killPlayer=index;
		limit = 0;
		active = false;
		shape = new Rectangle2D.Double(xPosition,yPosition,getAncho(),getAlto());
	}
	
	/**
	 * metodo que se encarga de devolver la figura asociada a la Sorpresa
	 * */
	public Rectangle2D.Double getShape(){
		return shape;
	}
	
	/**
	 * metodo que dice si el bloque esta activo o no, para ser dibujado
	 * @return boolean , booleano que dice si el bloque esta activo o no, para ser dibujado
	 * */
	public boolean isActive(){
		return active;
	}
	
	/**
	 * metodo que cambia el estado activo del bloque
	 * */
	public void changeActive(){
		active = !active;
	}
	
	/**
	 * metodo que esablece en que limite el bloque desaparecera si no toca a un jugador
	 * @param p, variable asociada al juego actual de POOng
	 * @param xPos, posicion sobre la cual el bloque debe colocarse para caer 
	 * @param index, indice del jugador a caer  
	 * */
	public void setLimit( POOng p,double xPos,double yPos, int index  ){
		super.xPosition=xPos;
		super.yPosition=yPos;
		killPlayer=index;
		shape = new Rectangle2D.Double(xPosition,yPosition,getAncho(),getAlto());
		Rectangle2D.Double rect = p.getPersonaje(killPlayer).getshape();
		limit = ( killPlayer == 1)?rect.getY()+(double)rect.height:rect.getY()-(double)rect.height;
		dy = ( killPlayer == 0)?-0.3:0.3;
	}
	
	
	/**
	 * metodo que devuelve hacia que direccion caera el bloque
	 * @return double, la direccion en cual caera el bloque
	 * */
	public  double getDy(){
		return dy;
		
	}
	
	
	/**
	 * metodo que dice si el bloque choco con un personaje 
	 * @param p, variable asociada al juego actual de POOng
	 * @param index, el indice del personaje a revisar
	 * */
	public boolean  chocarConPersonaje( POOng p,int index){
		Personaje pe = p.getPersonaje(index);
		if (this.shape.getBounds2D().intersects(pe.getShape().getBounds2D() ) ){
			p.getPersonaje(index).chocarConBloque();
			return true;
			
		}
		return false;
	}
	
	/**
	 * metodo que se encarga de hacer caer un bloque en el tablero 
	 * @param index, el indice del personaje a revisar
	 * */
	public boolean caer( POOng p){
		
		boolean choque= chocarConPersonaje( p,killPlayer );
		
		if(!p.isPaused()){
			yPosition+= dy;
			shape = new Rectangle2D.Double(xPosition,yPosition,getAncho(),getAlto());
		}
		
		if ( killPlayer == 0 && ( yPosition< limit) ||choque ){
			return false;
		}
		if ( killPlayer == 1 && ( yPosition> limit) ||choque){
			return false;
		}
		
		return true;	

		
		
	}

}

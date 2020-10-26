package aplicacion;

import java.awt.geom.Rectangle2D;
import java.util.Random;

/**
 * clase que representa la clase Objetivo del juego Poong
 * Los objetivos tendrán un valor aleatorio entre 2 y el número de puntos totales dividido en dos. 
 * */
public class Objetivo extends Elemento {
	private static final long serialVersionUID = 2194631718840527649L;
	private int allPoints; 
	private Rectangle2D.Double shape;
	
	/**
	 * constructor de la clase Objetivo
	 * @param xPos ,double que representa la posicion en x del Objetivo
	 * @param yPos, double que representa la posicion en y del Objetivo
	 * */
	public Objetivo(double xPos, double yPos){
		super(xPos,yPos,50,50);
		shape = new Rectangle2D.Double(xPosition,yPosition,getAncho(),getAlto());
	}
	
	
	/**
	 * metodo que se encarga de devolver la figura asociada a el Objetivo
	 * */
	public Rectangle2D.Double getShape(){
		return shape;
	}
	

	
	/**
	 * metodo  que causa la entrega de puntos al jugador que lo acciono  
	 * @param p, que representa el juego POOng
	 * */
	public void reaccionar(POOng p){
		Random rnd = new Random();
		int index = p.getLastPlayer();
		allPoints = 0;
		for ( int x=0; x < p.getAmountPersonajes() ;x++){
			allPoints+=p.getJugador(x).getScore();
		}
		int newPoints = rnd.nextInt((int)allPoints/2 + 1);
		newPoints = ( newPoints<2 || newPoints>=p.maxScore )?2:newPoints;
		p.getJugador(index).addScore(newPoints);
		if(p.getJugador(index).getScore()> p.maxScore){
			p.terminar( p.getJugador(index));
		}
		
		
		
		
	}
	
	
}

package aplicacion;

import java.awt.geom.Rectangle2D;
import java.util.Random;

public class SorpresaBlanco extends Sorpresa {
	private static final long serialVersionUID = -3332772749008928974L;
	private int seguir;
	
	/**
	 * constructor de la clase Sorpresa
	 * @param xPos ,double que representa la posicion en x de la sorpresa
	 * @param yPos, double que representa la posicion en y de la sorpresa
	 * */
	public SorpresaBlanco( double xPos, double yPos){
		super(xPos,yPos);
		shape = new Rectangle2D.Double(xPosition,yPosition,getAncho(),getAlto());
		
	}


	/**
	 * metodo que se encarga de reaccionar la sorpresa SorpresaBlanco
	 * @param p, que representa el juego POOng
	 * */
	void reaccionar(POOng game) {
		int index = game.getLastPlayer();
		game.getPersonaje(index).setInmunidad( true );
		Random rnd = new Random();
		seguir = rnd.nextInt(2);
	}
	
	
	/**
	 * metodo que hace que la sorpresa busque a la pelota el 50% de las veces, solo cuando aparece
	 * */
	public void moverSorpresaBlanco(POOng game ){
		if( seguir == 1 ){
			Pelota pelota = game.getPelota(0);
			double xPelota = pelota.getXposition() ;
			if( xPelota < xPosition){
				super.setXposition((xPelota - 2));
			}
			else{
				super.setXposition((xPelota + 2));
			}
			
			shape = new Rectangle2D.Double(xPosition,yPosition,getAncho(),getAlto());
		}
		
	}

}

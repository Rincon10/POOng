package aplicacion;


/**
 * clase que representa a el personaje de la CPU Sniper
 * Sniper: es especialista en pegarle a los blancos. Si no hay objetivos se comporta como un Extreme.
 * 
 * @author Miguel Angel Fuquene Arias 
 * @author Ivan Camilo Rincon Saavedra
 *
 * @version 1.0 21/05/2020
 *     
 * */
public class Sniper extends PersonajeCPU {
	private static final long serialVersionUID = -3644402850921590186L;
	private  final double direction = 0.1;

	/**
	 * constructor del PersonajeCPU Sniper del juego POOng
	 *@param xPosition, double que representa la posicion en x del personaje
	 *@param yPosition, double que representa la posicion en y del personaje
	 *@param xMax, double[] que representa lo maximo que podra moverse el personaje en la primera posicion sera lo maximo a a la izquierda y en la segunda sera lo maximo a derecha
	 * */
	public Sniper(double xPosition, double yPosition, double[] xMax){
		super(xPosition,yPosition,xMax);

	}
	
	/**
	 * metodo que se encarga de que el personaje golpee la pelota
	 * @param game, que representa el juego POOng 
	 * @param pelota, representa la pelota actual del juego
	 * */
	@Override
	public  void golpear( POOng game, Pelota pelota ){
		int sectorP1, sectorP2;
		
		sectorP1 = game.getPersonaje(0).getSector();
		sectorP2 = game.getPersonaje(1).getSector();
		if( game.getAmountObjetivos() == 0){
			pelota.golpeadoPorExtreme(sectorP1, sectorP2, direction);
			
		}
		else{
			int index  = game.getLastPlayer();
			int sectorLastP = game.getPersonaje(index).getSector();
			pelota.golpeadoPorSniper(sectorLastP, direction, game);
			
		}
		
	
		
	}
}

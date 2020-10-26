package aplicacion;


/**
 * clase que representa las excepciones del juego POOng
 * 
 * @author Miguel Angel Fuquene Arias 
 * @author Ivan Camilo Rincon Saavedra
 * 
 * @version 1.0 03/05/2020    
 * */

public class PoongException extends Exception {
	private static final long serialVersionUID = 8379100432364134074L;
	public static final String ERROR_INESPERADO ="Ocurrio un error inesperado al intentar ";
	public static final String NO_EXTENCION_DAT ="El archivo no posee una extension .dat";
	public static final String NO_EXTENCION_TXT ="El archivo no posee una extension .txt";
	public static final String PERSONAJES_IGUALES="Debe seleccionar diferentes personajes";
	public static final String VALOR_NO_NUMERICO="Solo ingresar valores numericos";
	
	public PoongException( String message){
		super(message);
	}
	
	

}

package persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import aplicacion.*;


/**
 * @author Miguel Angel Fuquene Arias 
 * @author Ivan Camilo Rincon Saavedra
 *clase que se encarga de manejar la persistencia del juego POOng
 *
 *@version 1.0 05/05/2020
 *
 * */
public abstract class PoongIO {
	
	/**
	 * @param f, Objeto archivo en el cual se planea verificar la extension  
	 * @throws PoongException, NO_EXTENCION_DAT si el archivo no termina con la extension .dat
	 * */
	public static void endsDat( File f ) throws  PoongException{
		if(!f.getName().endsWith(".dat") ){
			throw new  PoongException(PoongException.NO_EXTENCION_DAT);
		}
	
	}
	
	/**
	 * @param f, Objeto archivo en el cual se planea verificar la extension 
	 * @throws PoongException, NO_EXTENCION_TXT si el archivo no termina con la extension .txt
	 * */
	public static void endsTxt( File f ) throws  PoongException{
		if(!f.getName().endsWith(".txt") ){
			throw new  PoongException(PoongException.NO_EXTENCION_TXT);
		}
	
	}
	
	/**
	 * metodo encargado de salvar un archivo, con el juego establecido
	 * @param f, archivo en el cual se planea guardar el juego
	 * @param poong, juego que se desea guardar
	 * @throws PoongException, ERROR_INESPERADO si ocurre un error inesperado
	 * @throws PoongException, Si ocurrio un error al serializar el archivo.
	 * */
	public static void salvar( File f, POOng poong) throws  PoongException {
		endsDat( f );
		try {
			ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream( f ));
			out.writeObject( poong);
			out.close();
		}
		
		catch(Exception e){
			throw new  PoongException(PoongException.ERROR_INESPERADO+"salvar");
		}	
	}
	
	/**
	 * metodo encargado de abrir un juego
	 * @param f, archivo en el cual contiene al juego
	 * @throws PoongException, ERROR_INESPERADO si ocurre un error inesperado
	 * @throws PoongException, Si ocurrio un error al deserializar el archivo.
	 * */
	public static POOng abrir( File f) throws  PoongException {
		endsDat( f );
		try {
			ObjectInputStream in = new ObjectInputStream( new FileInputStream(f));
			System.out.println(in.readObject());
			System.out.println("pase");
			
			POOng p = ( POOng )in.readObject();
			System.out.println("pase x2");
			in.close();
			return p;
		}
		
		catch(Exception e){
			System.out.println(e.getMessage());
			throw new  PoongException(PoongException.ERROR_INESPERADO+"abrir");
		}	
		
	}

}

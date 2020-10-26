package aplicacion;

import java.util.ArrayList;
import java.util.Random;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.Serializable;
import javax.swing.JOptionPane;
import persistencia.PoongIO;
import presentacion.Hilo;


/**
 * clase que representa la logica del juego POOng
 * 
 * @author Miguel Angel Fuquene Arias 
 * @author Ivan Camilo Rincon Saavedra
 * 
 * @version 1.0 20/04/2020
 * @version 2.0 23/04/2020
 * @version 3.0 24/04/2020
 * @version 4.0 06/05/2020
 * @version 5.0 07/05/2020
 * @version 6.0 17/05/2020        
 * @version 7.0 20/05/2020
 * @version 8.0 21/05/2020    
 * */

public class POOng implements Serializable {
	public Hilo h;
	public static int maxScore,ronda;
	protected int lastPlayer,lastSorpresa; 
	protected final int turnOnSorpresa = 5051; 
	protected boolean paused;
	private boolean cpu1,cpu2; 
	private static transient PoongIO poongIO;
	private ArrayList<Jugador> jugadores;
	private ArrayList<Personaje> personajes; 
	private ArrayList<Pelota> pelotas;
	private ArrayList<Sorpresa> sorpresas;
	private ArrayList<Objetivo> objetivos;
	private String[][] rSorpresa ={{"FastBall","resources/fasterBall.png"},{"Freezer","resources/freezer.png"},{"Energy","resources/energy.png"},{"Turtle","resources/Turtle.png"},{"ColdRacket","resources/ColdRacket.png"},{"Flash","resources/flash.png"},{"SorpresaBlanco","resources/SorpresaBlanco.png"}};
	private Sorpresa currentSorpresa;
	private Bloque bloque;
	private static final long serialVersionUID = 8537988401855254360L;
	

	/**
	 *Constructor del juego POOng
	 * */
	public POOng(){
		maxScore = 10;
		ronda = 3;
		lastPlayer = -1;
		prepareElementos();
	}
	
	/**
	 *Constructor del juego POOng
	 *@param cpu , booleano que dice si el juego tiene una cpu como jugador
	 * */
	public POOng(boolean cpu1,boolean cpu2 ){
		this.cpu1 = cpu1;
		this.cpu2 = cpu2;
		maxScore = 10;
		ronda = 3;
		lastPlayer = -1;
		prepareElementos();
	}
	
	/**
	 * metodo que se encarga de establecer el puntaje maximo a jugar 
	 * @param maxScore, el puntaje maximo acordado para que acabe el juego
	 * */
	public void setMaxScore( int maxScore ){
		this.maxScore = maxScore;
	}
	
	/**
	 * metodo encargado de simular el juego
	 * */
	public void jugar(){
		if ( rebotaPersonaje() ){
			getPersonaje( lastPlayer ).golpear(this, getPelota(0));
			if(currentSorpresa == null && lastSorpresa != -1 ){
				activarPoderesTardios();
			}
		}
		if( getAmountObjetivos()>0 && choqueObjetivo() ){
			int index = 0;
			if(getAmountObjetivos()!=1) {
				index = ( getPelota(0).getXposition() < 315)?0:1;
			}
			getObjetivo(index).reaccionar(this);
			objetivos.remove(index);
		}
		getPelota(0).move();
		activarSorpresas();
		for(int x = 0;x<2;x++){
			if( getPersonaje(x).iGiveUp()){
				terminar( (x==0)?getJugador(1):getJugador(0));
			}
		}
		puntoJugador();
	}
	
	/**
	 * activa los poderes que no se activaron de forma instantanea, coldRacket
	 * */
	private void activarPoderesTardios(){
		if(  lastSorpresa == 4 ){
			ColdRacket s = new ColdRacket(315,265);
			s.reaccionar( this );
			lastSorpresa = -1;
		}
		else if(lastSorpresa == 5 ){
			getPelota(0).move();
			getPelota(0).resetToInitialSpeed();
			lastSorpresa = -1;
		}
	}

	
	/**
	 * metodo que se encarga de actualizar todo cuando un  jugador hace punto
	 * */
	public void puntoJugador(){
		int punto = getPelota(0).getSali(); 
		if ( punto!=-1){
			if(jugadores.get(punto).punto()){
				terminar( jugadores.get(punto));
			}
			
			getPelota(0).reaparecer();
		}
	}
	
	
	
	/**
	 * metodo que activa las sorpresas, y ajusta el bloque a caer
	 * */
	private void activarSorpresas(){
		ronda=( currentSorpresa== null )? ronda +1:ronda ;
		if( (!paused) && (currentSorpresa== null)&& (turnOnSorpresa %ronda   == 0) ){
			currentSorpresa = getRndSorpresa();
			ronda = 3;
		}
		if(  currentSorpresa instanceof SorpresaBlanco ){
			double xSorpresa =sorpresas.get(6).getXposition();
			if( Math.abs( getPelota(0).getXposition() -  xSorpresa ) < 20 ) {
				((SorpresaBlanco) sorpresas.get(6)).moverSorpresaBlanco(this);
			}
			
		}
		if(currentSorpresa!=null  && chocarSorpresa() ){
			currentSorpresa=null;
			double xPos = ( lastPlayer == 0)? getPersonaje(0).getXposition():getPersonaje(1).getXposition();
			bloque.setLimit(this,xPos,250,lastPlayer);
			bloque.changeActive();
			
			
		}
		if (bloque.isActive() ){
			if(!bloque.caer( this ) ){bloque.changeActive();}
		}
	}
	
	/**
	 * metodo que relaciona el juego poong con el thread del juego
	 * */
	public void setHilo( Hilo h){
		this.h = h;
	}
	
	public Hilo getHilo(){
		return h;
	}
	
	/**
	 * metodo que crea todas las posibles sorpresas que puede tener el juego
	 * */
	private void setSorpresas(){ 
		for ( int index = 0;index < rSorpresa.length;index++){
			try{
				Class clase = Class.forName("aplicacion."+rSorpresa[index][0]);
				Sorpresa s = (Sorpresa)clase.getDeclaredConstructor(double.class , double.class).newInstance(315,265);
				s.setImage(rSorpresa[index][1]);
				sorpresas.add(s);
			}
			catch(Exception e){
				JOptionPane.showMessageDialog( null,"Ocurrio un error al establecer las sorpresas",
		                "Error:", JOptionPane.ERROR_MESSAGE );
			}
		}
	}
	
	/**
	 * prepara los elementos que usara Poong
	 * */
	private void prepareElementos(){
		prepareArreglos();
		setSorpresas();
		prepareBloque();
		prepareObjetivos();
	}
	
	/**
	 * prepara los Objetivos que usara Poong
	 * */
	private void prepareObjetivos(){
		double [][] pos = new double[][]{{125,235},{480,235}};
		for( int x =  0; x < 2;x++){
			Objetivo o = new Objetivo(pos[x][0],pos[x][1]);
			o.setImage("resources/objetivo.png");
			objetivos.add(o);
		}
		
	}
	
	/**
	 * prepara el bloque a utilizar en poong
	 * */
	private void prepareBloque(){
		bloque  = new Bloque( 0,250,0);
		bloque.setImage("resources/bloque.png");
		currentSorpresa= getRndSorpresa();
	}
	
	/**
	 * metodo que se encarga de preparar todos los arreglos de Poong
	 * */
	private void prepareArreglos(){
		jugadores = new ArrayList<Jugador>();
		personajes =new ArrayList<Personaje>() ; 
		pelotas= new ArrayList<Pelota>() ;
		sorpresas =new ArrayList<Sorpresa> ();
		objetivos=new ArrayList<Objetivo>() ;
	}
	
	/**
	 * metodo que se encarga de generar una sorpresa de forma aleatoria
	 * @return Sorpresa, la sorpresa aleatoria generada
	 * */
	public Sorpresa getRndSorpresa(){
		Random rnd = new Random();
		int index =rnd.nextInt(rSorpresa.length);
	
		lastSorpresa = index; 
		return sorpresas.get(index);
	}
	
	
	/**
	 * metodo que le pone a el juego una sorpresa especifica
	 * @param index, el  indice de la sorpresa que se desea activar 
	 * */
	public void setCurrentSorpresa(int index){
		lastSorpresa = index;
		currentSorpresa = sorpresas.get(index);
	}
	
	/**
	 * metodo encargado de terminar el juego
	 * @param j, Jugador que gano la partida
	 * */
	public void terminar( Jugador j)  {
		h.pareEjecucion();
		JOptionPane.showMessageDialog(null,"El ganador de la partida fue "+j.getNombre(),"Ganador",0);
	}
	
	/**
	 * metodo que se encarga de decir si una pelota choco con un objetivo
	 * @return boolean, que dice si choco o no con un objetivo
	 * */
	private  boolean choqueObjetivo(){
		boolean found = false;
		for( Objetivo o: objetivos ){
			found = found || getPelota(0).chocarObjetivo(o);
		}
		
		return found;
		
	}
	
	/**
	 * metodo que dice si la pelota fue golpeada por algun personaje
	 * @return boolean, booleando que dice si la pelota fue golpeada por algun personaje 
	 * */
	private boolean  rebotaPersonaje(){
		Pelota p =getPelota(0);
		if( p.rebotaPersonaje(getShapePersonaje( 0)) || p.rebotaPersonaje(getShapePersonaje( 1))){
			lastPlayer = (getPelota(0).rebotaPersonaje(getShapePersonaje( 0)))?0:1;
			return true;
		}
		return false; 
		
	}
	
	/**
	 * metodo que retorna la posicion del personaje, cuyo golpe fue el ultimo en tocar la pelota
	 * @return int,indice del personaje
	 * */
	public int getLastPlayer(){
		return lastPlayer;
	}
	
	/**
	 * metodo que retorna la ultima sorpresa que se dibujo en el juego
	 * @return Sorpresa, ultima sorpresa en aparecer en el tablero
	 * */
	public int getLastSorpresa(){
		return lastSorpresa;
	}
	
	
	/**
	 * metodo que retorna el bloque que se encuentre en el juego
	 * @return Sorpresa, bloque que se encuentre en el juego
	 * */
	public Bloque getBloque(){
		return bloque;
	}
	
	
	/**
	 * metodo que dice si la pelota golpeo una sorpresa
	 * @return boolean, booleando que dice si la pelota golpeo una sorpresa 
	 * */
	private boolean chocarSorpresa(){
		return  getPelota(0).chocarSorpresa(currentSorpresa, this);
	}

	/**
	 * metodo que retorna cuantos personajes hay en el juego
	 * @return int, el numero de jugadores en el juego
	 * */
	public int getAmountPersonajes(){
		return personajes.size();
	
	}
	
	/**
	 * metodo que retorna cuantos sorpresas se pueden colocar en el juego
	 * @return int, el numero de sorpresas
	 * */
	public int getAmountSorpresas(){
		return sorpresas.size();
	
	}
	
	/**
	 * metodo que retorna cuantos objetivos  se pueden colocar en el juego
	 * @return int, el numero de objetivos
	 * */
	public int getAmountObjetivos(){
		return objetivos.size();
	
	}
	
	
	
	/**
	 * metodo que retorna cuantos personajes hay en el juego
	 * @return int, el numero de jugadores en el juego
	 * */
	public void setImagePersonaje( int index, String rute ){
		personajes.get(index).setImage(rute);

	}
	
	
	
	/**
	 * metodo que se encarga de mover un personaje a la izquierda
	 * @param index, indica cual personaje quiere mover
	 * */
	public void moverPersonaje( int index ){
		personajes.get(index).setMove(index);
		personajes.get(index).move(this);
		
		
		
	}
	
	/**
	 * metodo que dice si el juego esta pausado
	 * @return boolean, que dice si el juego se encuentra pausado
	 * */
	public boolean isPaused(){
		return paused;
	}

	/**
	 * metodo encargado de pausar tods los elementos del juego
	 * */
	public void pauseAll(){
		paused = !paused;
		for ( Personaje p: personajes){
			p.pause();
		}
		
		for ( Pelota pe: pelotas){
			pe.pause();
		}
	}
	
	
	/**
	 * clase encargada de devolver la figura de las pelotas que se encuentren en el juego
	 * @param index, indice asociado a la pelota que deseamos mostrar
	 * @retun  Ellipse2D.Double, pelota en el indice indicado en el tablero de juego
	 * */
	public  Ellipse2D.Double getShapePelota( int index ){ 
		return pelotas.get(index).getShape();	
	}
	
	/**
	 * clase encargada de devolver la figura de los personajes que se encuentren en el juego
	 * @param index, indice asociado al personaje que deseamos mostrar
	 * @retun  Rectangle2D.Double, personaje en el indice indicado en el tablero de juego
	 * */
	public Rectangle2D.Double getShapePersonaje( int index){
		return personajes.get(index).getshape();
	}
	
	/**
	 * clase encargada de devolver el personaje que se encuentren en el juego
	 * @param index, indice asociado al personaje que deseamos mostrar
	 * @retun  Personaje , personaje en el indice indicado en el tablero de juego
	 * */
	public  Personaje getPersonaje( int index ){
		return personajes.get(index);
	}
	
	/**
	 * clase encargada de devolver una sorpresa especifica
	 * @param index, indice asociado a la sorpresa que deseamos mostrar
	 * @retun  Sorpresa, sorpresa en el indice indicado en el tablero de juego
	 * */
	public  Sorpresa getSorpresa( int index ){
		return sorpresas.get(index);
	}
	
	
	/**
	 * clase encargada de devolver un objetivo especifico
	 * @param index, indice asociado a el objetivo que deseamos mostrar
	 * @retun  Objetivo, objetivo en el indice indicado en el tablero de juego
	 * */
	public  Objetivo getObjetivo( int index ){
		return objetivos.get(index);
	}
	
	
	/**
	 * metodo que retorna la sorpresa actual en el tablero
	 * @return Sorpresa,retorna la sorpresa actual en el juego  
	 * */
	public Sorpresa getCurrentSorpresa(){
		return currentSorpresa;
	}
	
	/**
	 * clase encargada de llenar la lista de los personajes en el juego
	 * */
	public void establezcaPersonajes(){
		for( Jugador j:jugadores){
			personajes.add( j.getPersonaje() );
		}
	
	}
	
	/**
	 * clase encargada de añadir un nuevo jugador
	 * @param j, nuevo jugador que ingreso al juego
	 * */
	public void addJugador( Jugador j ){
		jugadores.add(j);
	}
	
	/**
	 * clase encargada de añadir una nueva Pelota
	 * @param p,la nueva pelota a añadir
	 * */
	public void addPelota( Pelota p ){
		pelotas.add(p);
	}
	
	/**
	 * metodo encargado de retornar un jugador especifico
	 * @param index, indice que nos dice que jugador especificamente deseamos cosultar
	 * @return Jugador, el jugador deseado
	 * */
	public Jugador getJugador( int index){
		return jugadores.get(index);
	}
	
	/**
	 * clase encargada de retornar la pelota del juego actual
	 * @param index, indice de la pelota que deseamos recuperar
	 * @return Pelota, retorna la pelota en el indice deseado
	 * */
	public Pelota getPelota( int index ){
		return pelotas.get(index);
	}
	
	/**
	 * metodo encargado de salvar un archivo
	 * @param f, archivo en donde se planea guardar el juego
	 * @throwscualquier error que pueda surgir al guardar, 
	 * */
	public void salvar( File f ) throws PoongException{
		poongIO.salvar( f , this );
		
	}
	
	
	public POOng abrir(File f)throws PoongException{
		return poongIO.abrir(f);
	}

}

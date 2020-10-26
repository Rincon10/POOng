package presentacion;


import aplicacion.*;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 *Panel en el  cual se pintan todos los componentes graficos del juego
 * 
 * @author Miguel Angel Fuquene Arias 
 * @author Ivan Camilo Rincon Saavedra
 * 
 * @version 1.0 05/04/2020
 * @version 2.0 25/04/2020
 * @version 3.0 20/05/202]
 * @version 4.0 21/05/202]
 * */
public class PanelJuego extends PanelFondo {
	private transient Image temporal;
	private final String fondo = "resources/pause.png";
	private ImageIcon image;
	private PanelFondo pause;
	private JLabel n1,n2;
	private JPanel panelNombrePoder;
	
	private transient Graphics2D g2;
	private POOng game;
	private PantallaJuego parent ;
	private int wait = 2,stop =997 ; 
	
	/**
	 * Constructor del PanelJuego
	 * @param game , Es el juego al cual se le pintaran todos los componentes gráficos
	 * @param rute , Representa la ruta de fondo que se pintara en el juego.
	 * @param parent , representa la pantalla donde se pondra el panel del juego
	 */
	public PanelJuego( POOng game ,String rute, PantallaJuego parent ){
		super(rute);
		this.parent=parent;
		this.setLayout(null);
		this.addKeyListener(new EventoTeclado() );
		this.setFocusable(true);
		this.game = game;
		preparePanelPoder( );
		pause = new PanelFondo(fondo);
		pause.setBounds(240,520, 200, 100);
		pause.setBackground(Color.BLACK);
		pause.setVisible(false);
		this.add(pause);
		prepareImagenes();
	}
	
	/**
	 * metodo que prepara el panel en cual dira que poder actual se encuentra en el tablero juego
	 * */
	public void  preparePanelPoder(){
		panelNombrePoder = new JPanel();
		panelNombrePoder.setLayout( new GridLayout(3,1,0,0 ));
		
		String name = ( game.getCurrentSorpresa() == null  )?"   No hay sorpresa":"   "+game.getCurrentSorpresa().getClass().getName();
		n1 = new JLabel("Sorpresa actual:"); 
		n1.setForeground(Color.WHITE);
		
		n2 = new JLabel();
		n2.setText(name.replace("aplicacion.",""));
		n2.setForeground(Color.WHITE);
		
		panelNombrePoder.add(n1);
		panelNombrePoder.add(n2);
		panelNombrePoder.setBounds( 34,200 ,110,50);
		panelNombrePoder.setOpaque(false);
		this.add( panelNombrePoder);
		
		
		
		
	}
	
	/**
	 * Metodo que se encarga de crear y preparar todas las imagenes de algunos elementos que se pintaran en el juego.
	 */
	
	public void prepareImagenes(){
		preparePersonajes();
		prepareSorpresas();
		prepareObjetivos();
		prepareBloque();
		
	}
	/**
	 * Este metodo prepara los personajes y las imagenes necesarias para despues pintarlos en el juego.
	 */
	public void preparePersonajes(){
		for( int x= 0 ; x < game.getAmountPersonajes();x++){
			Personaje p = game.getPersonaje(x);
			image = p.getImage();
			temporal = image.getImage().getScaledInstance(p.getAncho(), p.getAlto(), Image.SCALE_SMOOTH);
			p.setImage(new ImageIcon(temporal));
		}
	}
	/**
	 * Este metodo prepara todas las sorpresas que tendra el juego asi como sus imagenes.
	 */
	public void prepareSorpresas(){
		for( int x= 0 ; x < game.getAmountSorpresas();x++){
			Sorpresa s = game.getSorpresa(x);
			image = s.getImage();
			temporal = image.getImage().getScaledInstance(s.getAncho(), s.getAlto(), Image.SCALE_SMOOTH);
			s.setImage(new ImageIcon(temporal));
		}
	}
	/**
	 * Este metodo prepara los objetivos o blancos que dan puntos dentro del juego.
	 */
	public void prepareObjetivos(){
		for( int x= 0 ; x < game.getAmountObjetivos();x++){
			Objetivo o = game.getObjetivo(x);
			image = o.getImage();
			temporal = image.getImage().getScaledInstance(o.getAncho(), o.getAlto(), Image.SCALE_SMOOTH);
			o.setImage(new ImageIcon(temporal));
		}
		
	}
	/**
	 * Este metodo prepara el bloque y la imagen que saldra despues de que un jugador le pegue a una sorpresa.
	 */
	public void prepareBloque(){
		Bloque b= game.getBloque();
		image = b.getImage();
		temporal = image.getImage().getScaledInstance(b.getAncho(), b.getAlto(), Image.SCALE_SMOOTH);
		b.setImage(new ImageIcon(temporal));
	}
	/**
	 * Metodo que se encarga de plasmar los componentes del juego en el panel de fondo y adicionalmente establecer las escalas de estos.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		dibujar(g);
		actualizar();
	}	
	/**
	 * Este metodo se encarga de dibujar cada uno de los elementos que se encuentran en el juego
	 * @param g , Objeto que nos permite dibujar varios tipos de figuras.
	 */
	public void dibujar(Graphics  g){
		g2.setColor(Color.WHITE);
		for ( int x =0; x < game.getAmountPersonajes() ;x++){
			Rectangle2D.Double personaje =game.getPersonaje(x).getshape(); 
			
			temporal = game.getPersonaje(x).getImage().getImage();
			g.drawImage(temporal, (int)personaje.getX(), (int)(personaje.getY()), this);
		}
		for ( int x =0; x < game.getAmountObjetivos() ;x++){
			Rectangle2D.Double objetivo =game.getObjetivo(x).getShape(); 
			temporal = game.getObjetivo(x).getImage().getImage();
			g.drawImage(temporal, (int)objetivo.getX(), (int)(objetivo.getY()), this);
		}
		if (game.getCurrentSorpresa()!=null){
			Rectangle2D.Double s =game.getCurrentSorpresa().getShape(); 
			temporal  = game.getCurrentSorpresa().getImage().getImage();
			g.drawImage(temporal, (int)s.getX(), (int)(s.getY()), this);			
		}
		if(game.getBloque().isActive() ){
			Rectangle2D.Double s =game.getBloque().getShape(); 
			temporal  = game.getBloque().getImage().getImage();
			g.drawImage(temporal, (int)s.getX(), (int)(s.getY()), this);
		}
		
		g2.setColor(new Color(100,221,23));
		g2.fill(game.getShapePelota(0));
	
	
	}
	/**
	 * Metodo que nos sirve para actualizar el juego luego de haber hecho pausa
	 */
	public void actualizar(){
		if( EventoTeclado.pause ){
			game.pauseAll();
			pause.setVisible(game.isPaused());
		}
		game.jugar();
		moverPlataformas();
		actualizarContadores();
		actualizarNombrePoder();
		 	
	}
	/**
	 * Metodo que nos da la capacidad de mover un personaje
	 */
	public void moverPlataformas(){
		for ( int x=0 ; x < 2; x++){
			quitarPoder(x);
			game.moverPersonaje(x);
		}
	}
	
	/**
	 * metodo que actualiza el poder actual 
	 * */
	public void actualizarNombrePoder(){
		String name = ( game.getCurrentSorpresa() == null  )?"No hay sorpresa":game.getCurrentSorpresa().getClass().getName();
		n2.setText(name.replace("aplicacion.",""));
	}
	
	/**
	 * Metodo que nos permite actualizar los contadores, es decir los puntos de los jugadores.
	 */
	public void actualizarContadores(){
		parent.refresqueContadores();
	}
	/**
	 * Metodo que nos permite volver a la normalidad los personajes que se hayan alterado luego de tomar una sorpresa.
	 * @param index , es el indice de del personaje al que se le va a quitar el poder o volver a la normalidad.
	 */
	public void quitarPoder(int index ){
		if(!game.isPaused()&& game.getPersonaje(index).isPaused()){
			if( wait%stop  == 0){
				game.getPersonaje(index).pause();
				wait = 2;
			}
			else{wait=( wait < stop )?wait +1:wait + 0;}
		}
		if(  game.getPersonaje(index).isSlow()) {
			if( wait%stop  == 0){
				game.getPersonaje(index).restablecerVelocidad();
				wait = 2;
			}
			else{wait=( wait < stop )?wait +1:wait + 0;}	
		}
	}
	
}
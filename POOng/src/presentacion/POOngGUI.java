package presentacion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * clase que maneja  la interfaz grafica del juego POOng  
 * 
 * @author Miguel Angel Fuquene Arias 
 * @author Ivan Camilo Rincon Saavedra
 * 
 * @version 1.0 20/05/2020
 * */

public class POOngGUI extends JFrame {
	private static final long serialVersionUID = 8171225485810270940L;
	private static final int ANCHO = 683;
	private static final int ALTO = 384;

	private PanelFondo fondo;
	
	/*llaves de las difenrentes ventanas*/
	private static String inicio="INICIO";
	private static String configuracion ="CONFIG";
	private static String onePlayer ="ONEPLAYER";
	private static String twoPlayer ="twoPlayer";
	private static String instruction ="INSTRUCTION";
	private static String cpuPlayer="CPU";

	/*Ventanas*/
	private JPanel instru;
	private JPanel ventanas, panelBotones;
	private VentanaConfiguracion ventanConfig;
	private JPanel ventanOnePlayer;
	private JPanel ventanTwoPlayer;
	private JPanel ventanCpu;
	
	/*Botones*/
	private JButton empezar,instrucciones ,volver;
	
	/*Menu Bar*/
	private JMenuBar menuBar;
	private JMenu archivo;
	private JMenuItem importar, abrir;
	
	private transient JFileChooser fileChooser;
	
	
	
	/**
	 * constructor de la clase de POOnGUI
	 * */
	public POOngGUI(){
		prepareElementos();
		prepareAcciones();
	}
	
	/**
	 * metodo que preparar todos los elementos visuales necesarios que necesita la interfaz 
	 * */
	public void prepareElementos(){
		this.setTitle("POOng");
		this.setResizable(false);
		this.setSize(ANCHO,ANCHO);
		this.setBackground(new Color(5,36,55));
		this.setLocationRelativeTo(null);
		this.setIconImage(new ImageIcon("resources/icon.png").getImage());
		this.setDefaultCloseOperation( EXIT_ON_CLOSE );
		EventoTeclado p = new EventoTeclado();
		this.addKeyListener( p );
		prepareVentanas();
	    prepareMenuBar();
		
		
		
	}
	
	/**
	 * metodo que prepara todas las ventanas las cuales usara el JFrame
	 * */
	public void prepareVentanas(){
		ventanas = new JPanel();
		ventanas.setLayout( new CardLayout( 0, 0) );
		instru =new PanelFondo("resources/fondoInstrucciones.png");
		instru.setLayout( new FlowLayout());
		volver = new JButton("Back");
		instru.add(volver);
		
		
		fondo = new PanelFondo("resources/fondoInicio.png");
		ventanConfig = new VentanaConfiguracion("resources/fondoConfig.png");
		ventanOnePlayer = ventanConfig.getOnePlayer();
		ventanTwoPlayer = ventanConfig.getTwoPlayer();
		ventanCpu = ventanConfig.getCpu();
		
		fondo.setLayout(null);
		panelBotones = new JPanel();
		panelBotones.setOpaque(false);
		panelBotones.setLayout( new GridLayout(2,1,5,5));
		
		instrucciones = new JButton("Rules");
		empezar = new JButton("Start");
		
		panelBotones.add(instrucciones);
		panelBotones.add(empezar);
		panelBotones.setSize(100, 50);
		panelBotones.setLocation(7*ANCHO/16,13*ANCHO/16);
		fondo.add(panelBotones);
		
		
		ventanas.add(fondo,inicio);
		ventanas.add(ventanConfig,configuracion);
		ventanas.add(ventanOnePlayer,onePlayer);
		ventanas.add( ventanTwoPlayer,twoPlayer);
		ventanas.add( ventanCpu,cpuPlayer);
		ventanas.add( instru,instruction);
		
		this.add(ventanas);
	
	}
	
	/**
	 *metodo que prepara el menu del JFrame en el cual se encontrara, los Items:
	 *Archivo
	 *Importar
	 *Abrir
	 * */
	public void prepareMenuBar(){
		menuBar = new JMenuBar();
		archivo = new JMenu("Archivo");
		importar = new JMenuItem("Importar");
		abrir = new JMenuItem("abrir");
		
		archivo.add(importar);
		archivo.add(abrir);
		
		menuBar.add(archivo);
		
		this.setJMenuBar(menuBar);
	}
	
	
	
	/**
	 * metodo que le asignara a los diferentes botones o demas componentesGUI su Respectivo Listener,
	 * segun corresponda
	 * */
	public void prepareAcciones(){
		empezar.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent event ){
				accionCambiarAJugadores();
			}
		});
		
		instrucciones.addActionListener( new ActionListener(){	
			public void actionPerformed(ActionEvent event ){
				accionCambiarInstrucciones();
			}
		});
		
		volver.addActionListener( new ActionListener(){	
			public void actionPerformed(ActionEvent event ){
				accionCambiarAinicio();
			}
		});
		
		prepareAccionesCongif();
		prepareAccionesIO();
	}
	
	/**
	 * metodo que le asignara a los diferentes botones o demas componentesGUI que esten relacionado a la configuracion de jugadores del juego,
	 * su respectivo listener 
	 * */
	public void prepareAccionesCongif(){
		/* Ventana Configuracion */
		ventanConfig.volverIni.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent event ){
				accionCambiarAinicio();
			}
	
	
		});
		
		ventanConfig.unJug.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent event ){
				accionCambiarUnJugador();
			}
	
	
		});
		
		ventanConfig.dosJug.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent event ){
				accionCambiarDosJugador();
			}
	
	
		});
		
		ventanConfig.cpuVsCpu.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent event ){
				accionCambiarACpu();
			}
	
	
		});
		
		

		
		ventanConfig.volverC1.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent event ){
				accionCambiarAConfig();
			}
	
	
		});
		ventanConfig.volverC2.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent event ){
				accionCambiarAConfig();
			}
		});
		
		ventanConfig.volverC3.addActionListener( new ActionListener(){
			
			public void actionPerformed(ActionEvent event ){
				accionCambiarAConfig();
			}
	
	
		});
	}
	
	/**
	 * metodo que se encarga de preparar las acciones necesarias, al seleccionar una accion Input/Output( Importar, Exportar, Abrir, Guardar)
	 * */
	public void prepareAccionesIO(){
		abrir.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent event ){
				accionAbrir();
			}
		});
		
		
	}
	
	
	/**
	 * metodo que realiza la accion de abrir un archivo de extension .dat
	 * */
	public void accionAbrir(){
		fileChooser = new JFileChooser();
		fileChooser.setVisible(true);
		fileChooser.setFileFilter(new FileNameExtensionFilter("Extension archivo .dat","dat"));
		int answ = fileChooser.showOpenDialog(this);
		if( answ == fileChooser.APPROVE_OPTION){
			ventanConfig.abrir(fileChooser.getSelectedFile());
		}
	}
	
	/**
	 * metodo que se encarga de cambiar a la ventana de opciones de juego
	 * */
	public void accionCambiarAJugadores(){
		CardLayout cLayout=(CardLayout)(ventanas.getLayout());
		cLayout.show( ventanas, configuracion);
	}
	
	
	/**
	 * metodo que se encarga de cambiar a la ventana inicio
	 * */
	public void accionCambiarAinicio(){
		CardLayout cLayout=(CardLayout)(ventanas.getLayout());
		cLayout.show( ventanas, inicio);
	}
	
	/**
	 * metodo que se encarga de cambiar a la ventana de un jugador
	 * */
	public void accionCambiarUnJugador(){
		CardLayout cLayout=(CardLayout)(ventanas.getLayout());
		cLayout.show( ventanas, onePlayer);
	}
	
	/**
	 * metodo que se encarga de cambiar a la ventana de dos jugadores
	 * */
	public void accionCambiarDosJugador(){
		CardLayout cLayout=(CardLayout)(ventanas.getLayout());
		cLayout.show( ventanas, twoPlayer);
	}
	
	
	/**
	 * metodo que se encarga de cambiar a la ventana de juego cpu
	 * */
	public void accionCambiarACpu(){
		CardLayout cLayout=(CardLayout)(ventanas.getLayout());
		cLayout.show( ventanas, cpuPlayer);
	}
	
	
	/**
	 * metodo que cambia a la ventana de configuracion del juego
	 * */
	public void accionCambiarAConfig(){
		
		CardLayout cLayout=(CardLayout)(ventanas.getLayout());
		cLayout.show( ventanas, configuracion);
	}
	
	
	
	/**
	 * metodo que cambia a la ventana de instrucciones del juego
	 * */
	public void accionCambiarInstrucciones(){
		CardLayout cLayout=(CardLayout)(ventanas.getLayout());
		cLayout.show( ventanas, instruction);
		
	}
	
	
	/**
	 * metodo que crea la interfaz grafica de POOng  y la vuelve visible 
	 * */
	public static void main(String[] args){
		POOngGUI gui = new POOngGUI();
		gui.setVisible(true);
	}

}


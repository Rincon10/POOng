package presentacion;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


/**
 *@author Fuquene Angel Fuqene
 *@author Ivan Camilo Ricon Saavedra
 */
public class PanelFondo extends JPanel{
	private static final long serialVersionUID = 8505314207582108129L;
	protected transient Image background;
	public PanelFondo(String ruta) {
		this.setBackground(ruta);
	}
	
	/**
	 * Constructor, constructor de la clase que extiende la Clase JPanel
	 *@param ruta, String que representa la ruta en la que se encuentra la imagen a poner de fondo
	*/
	public void setBackground(String ruta) {
		setOpaque(false);
		this.background = new ImageIcon(ruta).getImage();
		repaint();
	}
	public void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
		super.paintComponent(g);
	}
}
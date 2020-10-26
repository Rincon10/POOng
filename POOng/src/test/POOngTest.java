
package test;

import aplicacion.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;








public class POOngTest {
	public transient POOng game;
	private final double[] xMax = new double[] {130,530};
	
	
	
	private void prepareJuego(){
		game = new POOng( true,false );
		game.addJugador(new Jugador("Fuque", new PersonajeCPU(200,70,xMax) ));
		game.addJugador(new Jugador("Camilo", new PersonajeJugador(200,450,xMax)));
		game.establezcaPersonajes();
		game.addPelota(new PelotaNormal( 200,70,0.5));
	}
	
	private void simulacionChoqueSorpresa(){
		Pelota p = game.getPelota( 0 );
		game.jugar();
		p.goTo(315,265);
		game.jugar();
	}
	
	private void simulacionChoqueObjetivo(){
		Pelota p = game.getPelota( 0 );
		game.jugar();
		p.goTo(480,235);
		game.jugar();
	}
	
	
	private void moverPersonajeCpu( int times ){
		for( int x = 0; x < times;x++){
			game.getPelota(0).move();
			game.moverPersonaje(0);
		}
	}
	
	
	
   
	@Test
	public void deberiaChocarSorpresa() {
		prepareJuego();
		Pelota p = game.getPelota( 0 );
		Sorpresa s = game.getCurrentSorpresa();
	
		assertTrue( s!= null );

		simulacionChoqueSorpresa();
		// las posiciones de las sorpresas siempre son 315,265, por lo que la pelota la chocara
		s = game.getCurrentSorpresa();
		assertTrue( s== null );
		
	}
	
	@Test
	public void deberiaActivarElBloque(){
		prepareJuego();
		Bloque b = game.getBloque();
		assertFalse( true == b.isActive());
		simulacionChoqueSorpresa();
		b = game.getBloque();
		assertEquals( true, b.isActive());
	}
	

	@Test
	public void deberiaDarPoderFastBall(){
		prepareJuego();
		game.setCurrentSorpresa(0);
		Pelota p = game.getPelota( 0 );
		double initSpeed = p.getSpeed();
		simulacionChoqueSorpresa();
		assertTrue( Math.abs(initSpeed) < Math.abs(p.getSpeed()) );
	}
	
	@Test
	public void deberiaDarPoderFreezer(){
		prepareJuego();
		game.setCurrentSorpresa(1);
		Personaje p = game.getPersonaje(1);
		assertTrue( game.isPaused() == false );
		assertTrue( p.isPaused() == false );
		simulacionChoqueSorpresa();
		assertTrue( game.isPaused() == false );
		assertTrue( p.isPaused() == true );
		
	}
	
	@Test
	public void deberiaDarPoderEnergy(){
		prepareJuego();
		game.setCurrentSorpresa(2);
		Personaje p = game.getPersonaje(0);
		p.setStrength(0.6);
		
		assertEquals( (int)p.getStrenght(),(int)60.0);
		
		simulacionChoqueSorpresa();
		 
		assertEquals( (int)p.getStrenght(),(int)100.0);
		
		
		
	}
	
	@Test
	public void deberiaDarPoderTurtle(){
		prepareJuego();
		game.setCurrentSorpresa(3);
		Personaje p = game.getPersonaje(1);
		double initSpeed = p.getSpeed();
		simulacionChoqueSorpresa();
		
		assertFalse( initSpeed <  p.getSpeed() );
	}
	
	
	@Test
	public void deberiaDarPoderColdRacket(){
		//la sorpresa solo se activa si toca a un jugador, cuando ya desaparecio la sorpresa
		
		prepareJuego();
		game.setCurrentSorpresa(4);
		Personaje p = game.getPersonaje(0);
		Pelota pe = game.getPelota(0);
		
		assertTrue( p.isPaused() == false );
		simulacionChoqueSorpresa();
		pe.goTo(200,70);
		game.jugar();		
	
		assertTrue( p.isPaused() == true );
	}
	
	
	@Test
	public void deberiaDarPoderFlash(){
		//la sorpresa solo se activa si toca a un jugador, cuando ya desaparecio la sorpresa
		prepareJuego();
		game.setCurrentSorpresa(4);
		Pelota p = game.getPelota(0);
		double initSpeed = p.getSpeed();
	
		assertEquals( (int)p.getSpeed(),(int)0.5);
		simulacionChoqueSorpresa();
		assertFalse(  Math.abs(initSpeed) >  Math.abs(p.getSpeed()) );
		p.goTo(200,70);
		game.jugar();	
		
		assertEquals( (int)p.getSpeed(),(int)0.5);
		
	}
	
	
	@Test
	public void deberiaMoverPersonajeJugador() {
		prepareJuego();
		
		Personaje p = game.getPersonaje(1);
		double firstXPos = p.getXposition(), firstYPos = p.getYposition();
		
		p.moveLeft();
		assertTrue(firstXPos > p.getXposition() );
		
		
		p.moveRight();
		p.moveRight();
		assertTrue(firstXPos < p.getXposition() );
		
	}		
	
	
	
	@Test
	public void deberiaMoverPersonajeCpu() {
		prepareJuego();
		Personaje p = game.getPersonaje(0);
		double firstXPos = p.getXposition();
		
		moverPersonajeCpu( 10);
		assertTrue(firstXPos != p.getXposition() );
		
		
		
	}
	
	
	@Test
	public void deberiaActualizarCuandoMarcanPunto() {
		prepareJuego();
		
		Pelota p = game.getPelota( 0 );
		int sc1 = game.getJugador(0).getScore(); 
		game.jugar();
		p.goTo(350,525);
		game.jugar();
		int sc2 = game.getJugador(0).getScore();
		assertTrue(sc1 != sc2);		
	}
	
	
	@Test
	public void deberiaPausarJuego() {
		prepareJuego();
		moverPersonajeCpu( 20 );
		game.pauseAll();
		
		assertFalse( game.isPaused() == false );
		
		Pelota pelota  = game.getPelota(0);
		Personaje p1 = game.getPersonaje(0);
		Personaje p2 = game.getPersonaje(1);
		
		assertTrue( pelota.isPaused()) ;
		assertTrue( p1.isPaused()) ;
		assertTrue( p2.isPaused()) ;
		
	}
	
	
	@Test
	public void deberiaChocarObjetivos() {
		prepareJuego();
		int initAmountObjetivos = game.getAmountObjetivos();
		
		simulacionChoqueObjetivo();
		game.jugar();
		
		assertFalse( initAmountObjetivos == game.getAmountObjetivos());
		assertTrue( game.getAmountObjetivos() == initAmountObjetivos - 1 );
		
		
	}
	
	@Test
	public void deberiaDarPuntosAlChocarObjetivos() {
		prepareJuego();
		Jugador j = game.getJugador(0);
		
		assertEquals( j.getScore(),0);
		simulacionChoqueObjetivo();
		
		assertTrue( j.getScore() != 0);
		
	}
	
	
	
	
	@Test
	public void deberiaPararJuego() {
		prepareJuego();
		Pelota p = game.getPelota( 0 );
		Jugador j = game.getJugador(0);
		game.jugar();
		p.goTo(315,265);
		
		game.jugar();
			
		assertTrue(p != null );		
		assertTrue(j != null );		
	}
}					
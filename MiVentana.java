import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.event.*;
import java.lang.*;

class MiVentana extends JFrame implements KeyListener{

//IMAGENES
	BufferedImage imagen;
	BufferedImage tilesImage;
	ImageIcon imagenfondo = new ImageIcon("C:/Users/atond/Desktop/Proyecto/fondo.png");
	ImageIcon imagenpiso = new ImageIcon("C:/Users/atond/Desktop/Proyecto/Piso.png");

//OBJETOS
	Monito monito;
	JLabel fondo;
	Boton boton;
	Boton boton2;
	Contador contador1;
	//Coin chip[];
	Coin chip;

	//pisos
	JLabel piso, piso2;

	//cajas de colision
	JLabel cajaColisionPiso, cajaColisionPiso2;

//PANELES


//VARIABLES NECESARIAS...
	int indiceX=0;
	int i = 0;
	int ix = 5;
	int ax = 0;
	boolean der = false;
	int ib = 0;
	int banderaInicio = -1;

	boolean derPresionandose = false;
	boolean izqPresionandose = false;

	boolean botonPresionado = false;
	boolean boton2Presionado = false;

	boolean finale = false; 

	int contadorNumero = 0;

	public MiVentana()
	{
		//**************************************************CARGAR IMAGENES***********************************************
		try{
			imagen = ImageIO.read(new File("C:/Users/atond/Desktop/Buena.png"));
			tilesImage = ImageIO.read(new File("C:/Users/atond/Desktop/Proyecto/SpritePrueba.png"));
		}catch(Exception e)
		{
			System.out.println("Error al cargar la imagen");
		}


		//***************************************************CREAR OBJETOS**********************************************
		
		monito = new Monito(imagen.getSubimage(100,247,50,76),76,50);

		fondo = new JLabel(imagenfondo,JLabel.CENTER);

		piso = new JLabel(imagenpiso,JLabel.CENTER);

		piso2 = new JLabel(imagenpiso, JLabel.CENTER);

		boton = new Boton();
		boton.setBounds(900,475,64,64);

		boton2 = new Boton();
		boton2.setBounds(120,475,64,64);

		contador1 = new Contador();
		contador1.setBounds(1000,100,64,64);

		chip = new Coin();
		chip.setBounds(400,475,48,48);

		cajaColisionPiso = new JLabel();

		cajaColisionPiso2 = new JLabel();		
		
	//	chip = new Coin[3];
/*
		try{
		for(int c=0;c<chip.length;c++)
		{
			(chip[c]).setBounds(100*c,400,64,64);
		}
		}
		catch(Exception c)
		{
			System.out.println("No se puede hacer esto.");
		}
*/
		monito.setBounds(254,300,80,80);


 		//**************************************************AGREGAR PANELES**********************************************


		fondo.setBounds(0,0,1280,640);
		piso.setBounds(0,529,640,64);
		piso2.setBounds(800,529,640,64);
		cajaColisionPiso.setBounds(0,538,640,64);
		cajaColisionPiso2.setBounds(800,538,640,64);
		monito.imagen = imagen.getSubimage(100,247,50,76);
		monito.setOpaque(false);

 		//*******************************************************VENTANA*************************************************
		this.add(cajaColisionPiso);
		this.add(cajaColisionPiso2);
		this.add(contador1);

/*		try{
		for(int c=0;c<chip.length;c++)
		{
			this.add(chip[c]);
		}
		}
		catch(Exception c)
		{
			System.out.println("No se puede hacer esto.");
		}
*/		this.add(chip);
		chip.setVisible(false);
		this.add(monito);
		this.add(boton2);
		this.add(boton);
		this.add(piso2);
		this.add(piso);
		this.add(fondo);
					
				
		this.setTitle("PRACTICA 4");
		this.setBounds(20,40,1280,640);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.addKeyListener(this);
		monito.repaint();
		monito.imagen = imagen.getSubimage(100,247,50,76);


		while(true){
		
		if (monito.getBounds().intersects(boton.getBounds()) && botonPresionado == false)
		{
			boton.activar();
			contador1.activarUno();
			botonPresionado = true;
			contador1.repaint();
		}
		if (monito.getBounds().intersects(boton2.getBounds()) && boton2Presionado == false)
		{
			boton2.activar();
			contador1.activarUno();
			boton2Presionado = true;
			contador1.repaint();
		}

		if (botonPresionado == true && boton2Presionado == true)
		{
			finalizar();
		}
		gravedad();
		}
		
	//*********************************************************************GRAVEDAD*****************************************
	}
	
	public boolean onGround()
	{
		if (monito.getBounds().intersects(cajaColisionPiso.getBounds()))
		{
			
			monito.repaint();
			return true;
		}
		else if (monito.getBounds().intersects(cajaColisionPiso2.getBounds()))
		{
			monito.repaint();
			return true;
		}
		else
		{
			return false;
		}
	}

	public void gravedad()
	{	
		Point pos = monito.getLocation();
		int x = (int)pos.getX();
		int y = (int)pos.getY();

		while(onGround() == false)
		{
			if(der)
			{
			monito.imagen = imagen.getSubimage(0,154,50,84); // cayendo a la derecha
			monito.repaint();
			y = y + 10;
			monito.retardo(10);
			monito.setLocation(x,y);
			if (derPresionandose)
			{
				x = x + 10;
			}
			else if (izqPresionandose)
			{
				x = x - 10;
			}
			}
			else
			{
			monito.imagen = imagen.getSubimage(50,324,50,84); // cayendo a la izquierda
			monito.repaint();
			y = y + 10;
			monito.retardo(10);
			monito.setLocation(x,y);
			if (derPresionandose)
			{
				x = x + 10;
			}
			else if (izqPresionandose)
			{
				x = x - 10;
			}
			}
		}

	}

	public void finalizar()
	{
		if (monito.getBounds().intersects(chip.getBounds()) && finale == false)
			{
				System.out.println("OKAY");
				chip.morir();
				this.remove(chip);
				chip.setBounds(0,0,0,0);
				finale = true;				
			}
		else
		{
			chip.setVisible(true);
			finale = false;
		}

	}
	//***********************************************************CONTROLES DE ACTOR "MONITO" ********************************


	public void keyPressed(KeyEvent e)
	{

		monito.repaint();
		int t = e.getKeyCode();


		Point pos = monito.getLocation();
		int x = (int)pos.getX();
		int y = (int)pos.getY();
		
		//if (onGround()){
		if(t==68)
		{
			ix=5;
			x = x+8;
			ax=1;
			indiceX = (70*i);
			monito.imagen = imagen.getSubimage(indiceX,77*0,70,77);
			monito.altura = 77;
			monito.ancho = 70;
			i= i + 1;
			if (i == 6)
			{
				i = 0;
			}
			der = true;
			derPresionandose = true;
			izqPresionandose = false;
			ib = 1;
		}

		else if(t==65)
		{
			i = 0;
			ax=1;
			x = x-8;
			indiceX = (70*ix);
			monito.imagen = imagen.getSubimage(indiceX,77*1,70,77);
			monito.altura = 77;
			monito.ancho = 70;
			ix= ix - 1;
			if (ix == -1)
			{
				ix = 5;
			}
			der = false;
			izqPresionandose = true;
			derPresionandose = false;
			ib = 1;
		}

		else if (onGround()){
	 	if(t==83)
		{
			i = 0;
			x = (x-ax);
			ax=0;
			monito.imagen = imagen.getSubimage(197,249,79,77);
			monito.altura = 77;
			monito.ancho = 79;
		}
		}	

		monito.setLocation(x,y);
		//}
	
		System.out.println(monito.getLocation());
	}

	public void keyReleased(KeyEvent e)
	{	

		int t = e.getKeyCode();
		monito.repaint();


		Point pos = monito.getLocation();
		int x = (int)pos.getX();
		int y = (int)pos.getY();

		if(onGround()){
		if (t == 68)
		{
			x=x+1;
			monito.imagen = imagen.getSubimage(94,247,50,77);
			monito.altura = 77;
			monito.ancho = 50;
			System.out.println("Derecha presionada");
			izqPresionandose = false;
			derPresionandose = false;
			der = true;

		}
		
		else if (t == 65)
		{
			x=x-1;
			monito.imagen = imagen.getSubimage(141,247,50,77);
			monito.altura = 77;
			monito.ancho = 50;
			System.out.println("Izquierda presionada");
			izqPresionandose = false;
			derPresionandose = false;
			der = false;
		}

		if (t == 83 )
		{
			if (der){
			x=x+1;
			monito.imagen = imagen.getSubimage(94,247,50,77);
			monito.altura = 77;
			monito.ancho = 50;
			monito.imagen = imagen.getSubimage(94,247,50,77);
			ax = 1;
			izqPresionandose = false;
			derPresionandose = false;
			}
			else if(der==false)
			{
			x=x-1;
			monito.imagen = imagen.getSubimage(141,247,50,77);
			monito.altura = 77;
			monito.ancho = 50;
			monito.imagen = imagen.getSubimage(141,247,50,77);
			ax = -1;
			izqPresionandose = false;
			derPresionandose = false;
			}

		}

		else if(t==87)
		{
		y = y - 160;

			System.out.println("Salto.");
		}
			monito.setLocation(x,y);	
		}

			
	}
		

	public void keyTyped(KeyEvent e)
	{

	}


}
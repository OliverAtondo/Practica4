import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.*;

class Boton extends JPanel implements Runnable{

	BufferedImage imagen;
	int activo = 0;

	public Boton()
	{
		this.setOpaque(false);
		try{
			imagen = ImageIO.read(new File("C:/Users/atond/Desktop/Proyecto/SpritePrueba.png"));
		}catch(Exception e)
		{
			System.out.println("Error al cargar la imagen");
		}
	}

	@Override
	public void run()
	{
		while(true)
		{
			
				
		}

	}

	@Override
	public void paintComponent(Graphics g){

		super.paintComponent(g);
		retardo(120);
		g.drawImage(imagen.getSubimage(64*4,activo,64,64),0,0,64,64,null);
	}

	public void retardo(int ms)
	{
		try
		{
			Thread.sleep(ms);
		}catch(Exception e){
			System.out.println("Error: al ejecuar el sleep.");
		}

	}

	public void activar()
	{
		activo = 64;
		this.repaint();
	}

}
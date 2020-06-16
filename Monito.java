import javax.swing.*;
import java.awt.image.*;
import java.awt.*;

class Monito extends JPanel implements Runnable{

	BufferedImage imagen;
	int altura;
	int ancho;
	int y;

	public Monito(BufferedImage imagen, int altura, int ancho)
	{
		this.imagen = imagen;
		this.altura = altura;
		this.ancho = ancho;
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
		g.drawImage(imagen,0,0,ancho,altura,null);
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

}
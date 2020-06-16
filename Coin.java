import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.*;
import java.util.*;

class Coin extends JPanel implements Runnable{

	BufferedImage imagen;

	public Coin()
	{
		try{
			imagen = ImageIO.read(new File("C:/Users/atond/Desktop/Proyecto/CHIP.png"));
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
		g.drawImage(imagen,0,0,48,48,null);
	}

public void sonidoMoneda(){
ArrayList<String> contenido = new ArrayList<String>();
ArrayList<Integer> contenidoInt = new ArrayList<Integer>();
MiReproductor mr = new MiReproductor();
contenido = Archivo.leerTodo("coin.txt");
try{
mr.inicializar();
for(String i : contenido)
{
String nota = i.substring(0,2);
String duracion = i.substring(3);
int notaInt = Integer.parseInt(nota);
int duracionInt = Integer.parseInt(duracion);
System.out.println("Nota: "+nota+" Duracion: "+duracion);
mr.reproducirNota(notaInt, 1, duracionInt);
}
mr.finalizar();
}
catch (Exception e)
{
System.out.println("Error");
}
}

public void morir()
{
	this.setVisible(false);
	sonidoMoneda();
}

}





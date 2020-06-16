   
class EntornoGrafico{
	public static void main(String[] args) {
	MiVentana mv = new MiVentana();

		try{
contenido = Archivo.leerTodo(txtCampoTexto1.getText());
mk.inicializar();
for( String i : contenido)
{
String nota = i.substring(0, 2);
12
String duracion = i.substring(3);
int notaInt = Integer.parseInt(nota);
int duracionInt = Integer.parseInt(duracion);
System.out.println("Nota: "+nota+" Duracion: "+duracion);
mk.reproducirNota(notaInt, 1, duracionInt);
}
mk.finalizar();
}
catch(Exception e)
{
VentanaError ventanaError2 = new VentanaError();
ventanaError2.setVisible(true);
}
	}
}

import javax.swing.*;
import java.awt.*;

class VentanaPrincipal extends JFrame {
	
	public VentanaPrincipal() {
		
	}
	
	public String[] generarDatos() {
		
		int numero = 0; 
		String[] arreglo = new String[100];
		
		for(int i=0; i < 100; i++) {
			numero = (int)(Math.random() * 100) + 1;
			if( (numero % 2) == 0) {
				arreglo[i] = "SI";
			} else if((numero % 2) != 0) {
				arreglo[i] = "NO";
			}
		}
		return arreglo;
	}
	
}


public class Concurrencia {

	public static void main(String[] args) {
		
	}

}

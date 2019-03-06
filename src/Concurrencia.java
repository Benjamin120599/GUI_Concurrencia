
import javax.swing.*;
import java.awt.*;

class VentanaPrincipal extends JFrame {
	
	JTextArea areaSi, areaNo;
	JButton boton1, boton2;
	
	public VentanaPrincipal() {
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(209, 209, 209));
		setSize(400, 350);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Resultados Encuesta");
		setVisible(true);
		
		//Componentes
		JLabel labelSi = new JLabel("Resultados SI");
			labelSi.setBounds(65, 10, 100, 30);
			labelSi.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		add(labelSi);
		
		areaSi = new JTextArea();
			areaSi.setBounds(30, 40, 150, 200);
			areaSi.setEditable(false);
			areaSi.setBorder(null);
			areaSi.getAutoscrolls();
		add(areaSi);
		
		JLabel labelNo = new JLabel("Resultados NO");
			labelNo.setBounds(245, 10, 100, 30);
			labelNo.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		add(labelNo);
		
		areaNo = new JTextArea();
			areaNo.setBounds(210, 40, 150, 200);
			areaNo.setEditable(false);
			areaNo.setBorder(null);
			areaNo.getAutoscrolls();
		add(areaNo);
		
		boton1 = new JButton("Generar");
			boton1.setBounds(42, 260, 120, 30);
		add(boton1);
		
		boton2 = new JButton("Limpiar");
			boton2.setBounds(222, 260, 120, 30);
		add(boton2);
		
	}
	
	public String[] generarDatos() {
		int numero = 0; 
		String[] arreglo = new String[1000];
		
		for(int i=0; i < 1000; i++) {
			numero = (int)(Math.random() * 100) + 1;
			if( (numero % 2) == 0) {
				arreglo[i] = "SI";
			} else if((numero % 2) != 0) {
				arreglo[i] = "NO";
			}
			System.out.println((i+1)+".- "+arreglo[i]);
		}
		return arreglo;
	}
}

public class Concurrencia {

	public static void main(String[] args) {
		
//		VentanaPrincipal vp = new VentanaPrincipal();
//		vp.generarDatos();
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new VentanaPrincipal();
			}
		});
		
	}

}

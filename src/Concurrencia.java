
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class HiloLlenado implements Runnable {
	VentanaPrincipal vp = new VentanaPrincipal();
	Generador gen = new Generador();
	
	String[] arreglo = gen.generarDatos();
	int cont1 = gen.cont1;
	int cont2 = gen.cont2;
	
	@Override
	public void run() {
		String cadena1 = "";
		for(int i=0; i<cont1; i++ ) {
			cadena1 = cadena1 + "SI \n";
		}
		vp.areaSi.setText(cadena1);
		
		String cadena2 = "";
		for(int i=0; i<cont2; i++ ) {
			cadena2 = cadena2 + "NO \n";
		}
		vp.areaNo.setText(cadena2);
	}
	
}

class Generador {
	
	int numero = 0, cont1=0, cont2=0; 
	
	public String[] generarDatos() {
		
		String[] arreglo = new String[1000];
		
		for(int i=0; i < 1000; i++) {
			numero = (int)(Math.random() * 100) + 1;
			if( (numero % 2) == 0) {
				arreglo[i] = "SI";
				cont1++;
			} else if((numero % 2) != 0) {
				arreglo[i] = "NO";
				cont2++;
			}
			//System.out.println((i+1)+".- "+arreglo[i]);
		}
		return arreglo;
	}
	
}

class VentanaPrincipal extends JFrame implements ActionListener {
	
	JTextArea areaSi, areaNo;
	JButton boton1, boton2;
	int contadorSi = 0, contadorNo = 0;
	
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
			boton1.setBounds(60, 260, 120, 30);
		add(boton1);
		
		boton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Thread hilo1 = new Thread(new HiloLlenado());
				hilo1.start();
			}
		});
				
		boton2 = new JButton("Limpiar");
			boton2.setBounds(210, 260, 120, 30);
		add(boton2);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == "boton1") {
			Thread hilo1 = new Thread(new HiloLlenado());
			hilo1.start();
		}
		
	}

}

public class Concurrencia {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new VentanaPrincipal();
			}
		});
		
	}

}

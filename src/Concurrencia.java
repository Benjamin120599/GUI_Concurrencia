
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class HiloLlenado implements Runnable {
	Generador gen = new Generador();
	
	String[] arreglo = gen.generarDatos();
	
	@Override
	public void run() {
		int cont1=0, cont2=0;
		for(int i=0; i<arreglo.length; i++) {
			if(arreglo[i].equals("SI")) {
				VentanaPrincipal.areaSi.append((cont1+1)+".- "+arreglo[i]+"\n");
				cont1++;
			} else if(arreglo[i].equals("NO")) {
				VentanaPrincipal.areaNo.append((cont2+1)+".- "+arreglo[i]+"\n");
				cont2++;
			}
		}
	}
}

class HiloHistograma implements Runnable {

	@Override
	public void run() {
		
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
		}
		return arreglo;
	}	
}

class VentanaPrincipal extends JFrame {
	
	static JTextArea areaSi, areaNo;
	JButton boton1, boton2;
	int contadorSi = 0, contadorNo = 0;
		
	public void VentanaInicio() {
				
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
			areaSi.setLineWrap(true);
			areaSi.setBounds(30, 40, 150, 200);
			areaSi.setEditable(false);
			areaSi.setBorder(null);
		add(areaSi);
		
		JScrollPane scroll1 = new JScrollPane(areaSi);
			scroll1.setBounds(30, 40, 150, 200);
		add(scroll1);
		
		JLabel labelNo = new JLabel("Resultados NO");
			labelNo.setBounds(245, 10, 100, 30);
			labelNo.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		add(labelNo);
		
		areaNo = new JTextArea();
			areaNo.setLineWrap(true);
			areaNo.setBounds(210, 40, 150, 200);
			areaNo.setEditable(false);
			areaNo.setBorder(null);
		add(areaNo);
		
		JScrollPane scroll2 = new JScrollPane(areaNo);
			scroll2.setBounds(210, 40, 150, 200);
		add(scroll2);
		
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
		
		boton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				areaSi.setText("");
				areaNo.setText("");
			}
		});	
	}
}

public class Concurrencia {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new VentanaPrincipal().VentanaInicio();;
			}
		});
		
	}

}

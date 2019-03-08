
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class HiloLlenado implements Runnable {
	final String[] arreglo = new Generador().generarDatos();
	
	@Override
	public void run() {
		VentanaPrincipal.areaSi.setText("");
		VentanaPrincipal.areaNo.setText("");
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

class HiloHistograma1 implements Runnable {
	int contSi = VentanaPrincipal.areaSi.getLineCount();

	@Override
	public void run() {
		
		for(int i=0; i <= contSi; i++) {
			VentanaPrincipal.barraSi.setValue(i);
			VentanaPrincipal.barraSi.repaint();
			VentanaPrincipal.barraSi.setForeground(new Color(8, 106, 211));
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class HiloHistograma2 implements Runnable {
	int contNo = VentanaPrincipal.areaNo.getLineCount();
		
	@Override
	public void run() {

		for(int i=0; i <= contNo; i++) {
			VentanaPrincipal.barraNo.setValue(i);
			VentanaPrincipal.barraNo.repaint();
			VentanaPrincipal.barraNo.setForeground(new Color(211, 8, 8));
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
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
	
	Generador gen = new Generador();
	static JTextArea areaSi, areaNo;
	static JProgressBar barraSi, barraNo;
	JButton boton1, boton2;
	int contadorSi = 0, contadorNo = 0;
		
	public void VentanaInicio() {
				
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(209, 209, 209));
		setSize(400, 450);
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
		
		JLabel si = new JLabel("Si.");
			si.setFont(new Font("Times New Roman", Font.PLAIN, 26));
			si.setBounds(30, 270, 100, 30);
		add(si);
		
		barraSi = new JProgressBar(0, gen.generarDatos().length);
			barraSi.setBounds(80, 270, 260, 30);
			barraSi.setStringPainted(true);
		add(barraSi);
		
		JLabel no = new JLabel("No.");
			no.setFont(new Font("Times New Roman", Font.PLAIN, 26));
			no.setBounds(30, 310, 100, 30);
		add(no);
		
		barraNo = new JProgressBar(0, gen.generarDatos().length);
			barraNo.setBounds(80, 310, 260, 30);
			barraNo.setStringPainted(true);
		add(barraNo);
		
		boton1 = new JButton("Generar");
			boton1.setBounds(60, 360, 120, 30);
		add(boton1);
		
		boton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Thread hilo1 = new Thread(new HiloLlenado());
				hilo1.start();
				try {
					hilo1.join();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
				Thread hilo2 = new Thread(new HiloHistograma1());
				hilo2.start();
				Thread hilo3 = new Thread(new HiloHistograma2());
				hilo3.start();
				
			}
		});
				
		boton2 = new JButton("Limpiar");
			boton2.setBounds(210, 360, 120, 30);
		add(boton2);
		
		boton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				areaSi.setText("");
				areaNo.setText("");
				barraSi.setValue(0);
				barraNo.setValue(0);
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

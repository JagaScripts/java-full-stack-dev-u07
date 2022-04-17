package t07Ejercicio1;

import java.util.Enumeration;
import java.util.Hashtable;


import javax.swing.JOptionPane;
/**
 * 
 * java-full-stack-dev-u07 - t07Ejercicio1 - Ejercicio1
 *
 * @author Jose Antonio González Alcántara
 * 
 * Fecha de creación 16/04/2022
 */
public class Ejercicio1 {
	
	public static final int NUMERO_NOTAS = 3;

	public static void main(String[] args) {
				
		//declaramos dos diccionarios para los alumnos y las notas y otro para alumnos y medias
		Hashtable<String, Double[]> alumnosNotas = new Hashtable<String, Double[]>();
		Hashtable<String, Double> alumnosMedia = new Hashtable<String, Double>();
		
		//Pedimos los alumnos que queremos generar
		int cantidadAlumnos = proporcionarEntradaAlumnos();
		
		//Pedimos los datos y vamos llenando los diccionarios
		for (int i = 0; i < cantidadAlumnos; i++) {
			
			String nombreAlumno = proporcionarEntradaAlumno(i);
			Double[] notas = new Double[NUMERO_NOTAS];
			
			for (int j = 0; j < NUMERO_NOTAS; j++) {
				
				notas[j] = proporcionarEntradaNota(j);
				
			}
			
			alumnosNotas.put(nombreAlumno, notas);
		}
		
		Enumeration<String> enumerationNombre = alumnosNotas.keys();
		Enumeration<Double[]> enumerationNotas = alumnosNotas.elements();
		
		Double media;
		
		while (enumerationNotas.hasMoreElements()) {
			
			//claculamos la media de las notas del alumno
			media = calcularMedia(enumerationNotas.nextElement());
		
			//creamos diccionario de medias
			alumnosMedia.put(enumerationNombre.nextElement(), media);
			
		}
		
		JOptionPane.showMessageDialog(null, "Las notas medias de los alumnos son " + alumnosMedia);
		
	}
	
	
	//Valida y proporciona una cadena de letras
	public static String proporcionarEntradaAlumno(int numero) {
		
		String entrada;
		String patronLetras = "[a-zA-Z].*";
		
		
		do {
			
			entrada = JOptionPane.showInputDialog("Introduce el nombre del alumno numero " + numero + "\n"
						+ " (solo letras)");
			
			
		} while (!validarEntradaPatron(patronLetras, entrada));
		
		return entrada;

	}

	//Calcula la media de un array de notas
	public static Double calcularMedia(Double[] notas) {
		
		Double media = 0.0;
		
		for (int i = 0; i < notas.length; i++) {
			
			media += notas[i];
			
		}
		
		return media /= notas.length;		
		
	}
	
	//Valida un patron segun una entrada	
	public static boolean validarEntradaPatron(String patron, String entrada) {
		
		boolean esNumero = false;

		if (entrada != null) {

			if (!entrada.isEmpty()) {

				if (entrada.matches(patron)) {

					esNumero = true;

				}

			}

		} else {

			JOptionPane.showMessageDialog(null, "La aplicaciónn se cerrara");
			System.exit(0);

		}

		return esNumero;
	}
	
	//Valida y proporcionar numeros enteros
	public static int proporcionarEntradaAlumnos() {
		
		String entrada;
		String patronEntero = "\\d+";
		
		
		do {
			
			entrada = JOptionPane.showInputDialog("Introduce la cantidad de alumnos \n"
						+ "que quieres generar (tiene que ser un entero positivo)");
			
			
		} while (!validarEntradaPatron(patronEntero, entrada));
		
		return Integer.parseInt(entrada);
		
	}
	
	//Validay proporciona Notas de los alumnos
	public static Double proporcionarEntradaNota(int numero) {
		
		boolean notaValida = false;
		Double nota = 0.0;
		String entrada;
		String patronDecimales = "\\d*(\\.\\d+)?";
		
		do {
			
			entrada = JOptionPane.showInputDialog("Introduce la nota numero " + numero + " de 3\n" 
												+ "entre 0 y 10 (El punto ."
												+ "es separador de decimales)");
			
			notaValida = validarEntradaPatron(patronDecimales, entrada);

			if (notaValida) {
				
				nota = Double.parseDouble(entrada);

				if (nota < 0 || nota > 10) {

					JOptionPane.showMessageDialog(null, "Introduce una nota entre 0 y 10");
					notaValida = false;

				}
				
			}
							
		} while (!notaValida);
		
		return Double.parseDouble(entrada);
		
	}
	
}

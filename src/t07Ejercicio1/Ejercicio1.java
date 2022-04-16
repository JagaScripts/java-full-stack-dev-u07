package t07Ejercicio1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
/**
 * 
 * java-full-stack-dev-u07 - t07Ejercicio1 - Ejercicio1
 *
 * @author Jose Antonio González Alcántara
 * 
 * Fecha de creación 16/04/2022
 */
public class Ejercicio1 {
	
	public static final int NUMERO_ALUMNOS = 20;

	public static void main(String[] args) {
				
		//declaramos dos array list para los alumnos y las notas 
		ArrayList<Integer> alumnos = new ArrayList<Integer>();
		ArrayList<Float[]> notas = new ArrayList<Float[]>();
		
		generarVariablesAlumnosNotas(alumnos, notas);
		
		System.out.println(alumnos);
		System.out.println(notas.get(1)[0]);
		
	}
	
	//Funcion para generar notas aleatorias
	public static int generarNumeroAleatorio(int numBase, int numTope){
		
		Random random;
		int numeroAleatorio; 

		random = new Random();
	    numeroAleatorio = numBase + random.nextInt((numTope+1) - numBase);
		return numeroAleatorio;
		
	}
	
	
	//Método para generar los datos de la aplicación de forma aleatoria
	public static void generarVariablesAlumnosNotas(ArrayList<Integer> alumnos, ArrayList<Float[]> notas) {
		
		Float[] notasAux = new Float[3];
		int decenas,decimal;
		int niu = generarNumeroAleatorio(11111,19999);
		
		//rellenamos la array con numeros identificativos de alumnos generados aleatoriamente
		//y tambien las notas
		for (int i = 0; i < NUMERO_ALUMNOS; i++) {
			
			//Añadimos NIU aleatorio como alumno
			alumnos.add(i,niu + i);
			
			//Generamos array con notas aleatorias
			for (int j = 0; j < notasAux.length; j++) {
				decenas = generarNumeroAleatorio(4, 9);
				decimal = generarNumeroAleatorio(0, 9);
				notasAux[j] = Float.parseFloat(decenas + "." + decimal);
			}
			
			//Añadimos array con notas aleatorias
			notas.add(notasAux);
		}
		
	}

}

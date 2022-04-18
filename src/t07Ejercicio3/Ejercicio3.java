/**
 * java-full-stack-dev-u07 - Ejercicio3 - Ejercicio3
 *
 * @author Jose Antonio González Alcántara
 * 
 * Fecha de creación 16/04/2022
 */
package t07Ejercicio3;

import java.util.Hashtable;
import java.util.Scanner;
import java.util.Enumeration;

/**
 * 
 * java-full-stack-dev-u07 - t07Ejercicio3 - Ejercicio3
 *
 * @author Jose Antonio González Alcántara
 * 
 * Fecha de creación 18/04/2022
 */
public class Ejercicio3 {

	static Scanner scanner = new Scanner(System.in);
	
	static Hashtable<Integer, String> productos = new Hashtable<Integer,String>();
	static Hashtable<Integer, Integer> stock = new Hashtable<Integer, Integer>(); 

	public static void main(String[] args) {
		
		generarProductos();
		
		int opcion;
		
		do {
			
			opcion = proporcionarEntradaMenu();
			
			switch (opcion) {
			case 1:
				listarProductos();
				break;
			case 2:
				agregarProductos();
				break;
			case 3:
				consultarProducto();
				break;
			case 4:
				System.out.println("Se cerrara la aplicación");
				break;
			default:
				System.out.println("Introduce una opción válida");
				break;
			}
			
		}while(opcion != 4);
		
		scanner.close();
		
	}
	
	
	public static void consultarProducto() {
		
		Integer idProducto = proporcionarEntradaStock("Introduce id del producto");
		System.out.println();
		String nombreProducto = productos.get(idProducto);
		Integer cantidadStock = stock.get(idProducto);
		System.out.println("El producto " + nombreProducto + " con ID: " + idProducto + " tiene una cantidad stock de "
				+ cantidadStock);
		
	}

	public static void agregarProductos() {

		
		String nombreProducto = proporcionarEntradaProducto("Introduce el nombre del producto");
		System.out.println();
		Integer cantidadStock = proporcionarEntradaStock("Introduce una cantidad de stock");
		
		Integer idProdcuto = productos.size() + 1;
		
		productos.put(idProdcuto, nombreProducto);
		stock.put(idProdcuto, cantidadStock);
		
		
	}
		
	//listar informacion
	public static void listarProductos() {
		
		Enumeration<Integer> enumeID = productos.keys();
		
		Integer idProducto;
		String nombreProducto;
		Integer cantidadStock;
		
		System.out.println("Lista de productos");
		System.out.println();
		
		while (enumeID.hasMoreElements()) {
			
			idProducto = enumeID.nextElement();
			nombreProducto = productos.get(idProducto);
			cantidadStock = stock.get(idProducto);
			
			System.out.println("El producto " + nombreProducto + " con ID: " + idProducto + " tiene una cantidad stock de "
					+ cantidadStock);
		}
		
		System.out.println();
		
	}

	public static void generarProductos() {

		productos.put(1, "Pantalon");
		productos.put(2, "Camisa");
		productos.put(3, "Zapato");
		productos.put(4, "Gafas de sol");
		productos.put(5, "Zapatilla");
		productos.put(6, "Cinturon");
		productos.put(7, "Gorra");
		productos.put(8, "Camiseta");
		productos.put(9, "Calcetines");
		productos.put(10,"Calzoncilloe");

		stock.put(1, 40);
		stock.put(2, 56);
		stock.put(3, 3);
		stock.put(4, 8);
		stock.put(5, 26);
		stock.put(6, 2);
		stock.put(7, 150);
		stock.put(8, 43);
		stock.put(9, 78);
		stock.put(10, 67);

	}
	
	public static Integer proporcionarEntradaMenu() {

		String entrada;
		String patronEntero = "\\d+";

		do {

			entrada = mostrarMenu();

		} while (!validarEntradaPatron(patronEntero, entrada));

		return Integer.parseInt(entrada);

	}

	
	public static String mostrarMenu() {
		
		String opcion;
		
		System.out.println("Menú gestor de stock de tienda: ");
		System.out.println("1. Listar productos");
		System.out.println("2. Agregar producto");
		System.out.println("3. Consultar producto");
		System.out.println("4. Cerrar aplicacion");
		System.out.println("Elige una opción");
		
		opcion = scanner.next();
		
		return opcion;
		
	}
	
	// Valida un patron segun una entrada
	public static boolean validarEntradaPatron(String patron, String entrada) {

		boolean esNumero = false;

		if (entrada != null) {

			if (!entrada.isEmpty()) {

				if (entrada.matches(patron)) {

					esNumero = true;

				}

			}

		} else {

			System.out.println("La aplicaciónn se cerrara");
			System.exit(0);

		}

		return esNumero;
	}
	
	// Valida y proporcionar numeros enteros
	public static String proporcionarEntradaProducto(String mensaje) {

		String entrada;
		String patronLetras = "[a-zA-Z].*";

		do {

			System.out.println(mensaje);
			entrada = scanner.next();

		} while (!validarEntradaPatron(patronLetras, entrada));

		return String.valueOf(entrada);

	}
	
	public static Integer proporcionarEntradaStock(String mensaje) {

		String entrada;
		String patronEntero = "\\d+";

		do {

			System.out.println(mensaje);
			entrada = scanner.next();


		} while (!validarEntradaPatron(patronEntero, entrada));

		return Integer.parseInt(entrada);

	}
}

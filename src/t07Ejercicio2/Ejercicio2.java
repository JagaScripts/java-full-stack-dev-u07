package t07Ejercicio2;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JOptionPane;

/**
 * java-full-stack-dev-u07 - t0Ejercicio2 - Ejercicio2
 *
 * @author Jose Antonio González Alcántara
 * 
 *         Fecha de creación 16/04/2022
 * 
 */
public class Ejercicio2 {

	static final Double IVA_REDUCIDO = 0.04;
	static final Double IVA_GENERAL = 0.21;
	static Hashtable<String, String> productos = new Hashtable<String, String>();
	static Hashtable<String, Double> ivaProductos = new Hashtable<String, Double>();
	static Hashtable<String, Double> precioProductos = new Hashtable<String, Double>();
	static Hashtable<String, Integer> carrito = new Hashtable<String, Integer>();

	public static void main(String[] args) {

		generarProductos();

		int menu;

		do {

			menu = proporcionarEntradaMenu();

			switch (menu) {
			case 1:
				;
				JOptionPane.showMessageDialog(null,listarProductos());
				break;
			case 2:
				String entrada = verCarrito();
				if (entrada.isEmpty()) {
					entrada =  "Aun no has agregado nada al carrito";
				}
				JOptionPane.showMessageDialog(null, "CARRITO\n" + entrada);
				break;
			case 3:
				agregarProdcutoCarrito();
				break;
			case 4:
				eliminarProdcutoCarrito();
				break;
			case 5:
				pagarProductos();
				break;
			case 6:
				JOptionPane.showMessageDialog(null, "La aplicaciónn se cerrara");
				break;
			default:
				JOptionPane.showMessageDialog(null, "Elige una opción válida");
				break;
			}

		} while (menu != 6);
	}

	public static void pagarProductos() {
				
		if (verCarrito().isEmpty()) {
			
			JOptionPane.showMessageDialog(null,
					"El carrito de la compra esta vacio\n" + "Agrega productos para poder pagar");
			
		}else {
			
			String cuenta[];
			Double importe;
			String listaCuenta;
			double importeTotal;
			
			cuenta = listarCuenta();
			listaCuenta = cuenta[0];
			importeTotal = Double.parseDouble(cuenta[1]);
			
			boolean importeValido = false;
			
			
			do {
				
				importe = proporcionarEntradaCantidadPagar(importeTotal, listaCuenta);
				
				
				if (importe < importeTotal) {
					
					importeValido = false;
					JOptionPane.showMessageDialog(null,
							"Tienes que tener suficiente dinero para poder pagar " + importeTotal + " €");
				}else {
					
					importeValido = true;
					
				}
				
			} while (!importeValido);
			
			double devolucion;
			
			devolucion = importeTotal - importe;
			
			listaCuenta = listaCuenta + " Has pagado " + importe + " € la devolucion es de " + Math.round(devolucion * 100d) / 100d + " € ";
			
			JOptionPane.showMessageDialog(null, listaCuenta);
			
		}
		
		
	}
	

	public static String[] listarCuenta() {

		String listaCuenta[] = new String[2];
		listaCuenta[0] = "";
		String idProducto;
		String nombreProducto;
		int cantidad;
		double ivaProductoAplicado;
		double precioBruto;
		double precioIva;
		double precioTotalBruto = 0.0;
		double precioTotalIVA = 0.0;
		String cantidadProducto;
		String precioProducto;
		String ivaProducto;
		String precioMasIva;

		Enumeration<String> enumeProdcutosId = carrito.keys();

		while (enumeProdcutosId.hasMoreElements()) {

			//Accedemos a los datso de las Hastables
			idProducto = enumeProdcutosId.nextElement();
			nombreProducto = productos.get(idProducto);
			cantidad = carrito.get(idProducto);
			ivaProductoAplicado = ivaProductos.get(idProducto);
			
			
			//Hacemos los calculos necesarios
			precioBruto = cantidad * precioProductos.get(idProducto);
			precioTotalBruto += precioBruto;
			precioIva = precioBruto * (1 + ivaProductoAplicado);
			precioTotalIVA += precioIva;
			
			
			//Parseamos datos a string
			cantidadProducto = String.valueOf(cantidad);
			precioProducto = String.valueOf(precioBruto);
			ivaProducto = String.valueOf(ivaProductoAplicado);
			precioMasIva = String.valueOf((double)Math.round(precioIva * 100d) / 100d);
			
			//Preparamos mensaje de la cuenta
			listaCuenta[0] = listaCuenta[0] + " Nombre producto: " + nombreProducto + "\n Cantidad: " + cantidadProducto
					+ " IVA: " + ivaProducto + " Precio bruto: " + precioProducto + " Precio + IVA: " + precioMasIva
					+ "\n";

		}
		
		if (!listaCuenta[0].isEmpty()) {
			
			listaCuenta[1] = String.valueOf((double)Math.round(precioTotalIVA * 100d) / 100d);
			
			listaCuenta[0] = listaCuenta[0] + "\n" + "TOTAL CUENTA: " + String.valueOf(Math.round(precioTotalBruto * 100d) / 100d)
					+ " TOTAL + IVA: " + listaCuenta[1] + "\n";
			
		}

		return listaCuenta;

	}

	public static void eliminarProdcutoCarrito() {
		
		boolean eliminado = false;
		String idProducto;
		
		do {
			
			idProducto =  proporcionarEntradaProducto("producto", "CARRITO\n" + verCarrito(), "eliminar");
			
			if (carrito.containsKey(idProducto)) {
				
				eliminado = true;
				carrito.remove(idProducto);
				JOptionPane.showMessageDialog(null, "Producto " + productos.get(idProducto) + " eliminado correctamente");
				
			}else {
				
				JOptionPane.showMessageDialog(null, "El Producto con id " + idProducto + " no existe en el carrito");
				
			}
			
			
		} while (!eliminado);
		
	}


	public static void agregarProdcutoCarrito() {

		boolean agregado = false;
		String idProducto;
		String cantidad;
		
		do {
			
			idProducto = proporcionarEntradaProducto("producto", listarProductos(), "añadir");
			
			if (productos.containsKey(idProducto)) {
				
				agregado = true;
				cantidad = proporcionarEntradaProducto("cantidad", " ", "añadir");
				carrito.put(idProducto, Integer.parseInt(cantidad));
				JOptionPane.showMessageDialog(null, "Producto " + productos.get(idProducto) + " agregado correctamente");
				
			}else {
				
				JOptionPane.showMessageDialog(null, "El Producto con id " + idProducto + " no existe en la lista de prodcutos");
				
			}
			
			
		} while (!agregado);
		

	}

	public static String verCarrito() {
		
		String listaProductos = "";
		String idProducto;
		Enumeration<String> enumeProdcutosId = carrito.keys(); 
				
		while (enumeProdcutosId.hasMoreElements()) {
			
			idProducto = enumeProdcutosId.nextElement();
			listaProductos = listaProductos + "Id producto: " + idProducto + " Nombre: " + productos.get(idProducto)
					+ " Cantidad: " + String.valueOf(carrito.get(idProducto)) + "\n";
			
		}
		
		return listaProductos;
		
	}

	public static String listarProductos() {
		
		String listaProductos = "PRODUCTOS\n";
		String idProducto;
		Enumeration<String> enumeProdcutosId = productos.keys(); 
				
		while (enumeProdcutosId.hasMoreElements()) {
			
			idProducto = enumeProdcutosId.nextElement();
			listaProductos = listaProductos + "Id producto: " + idProducto + " Nombre: " + productos.get(idProducto)
					+ " Precio: " + String.valueOf(precioProductos.get(idProducto)) + "\n";
			
		}
		
		return listaProductos;

	}

	// mostrar carrito
	public static String mostrarMenu() {

		String entradaTeclado = JOptionPane.showInputDialog(null,
				"Menú de opciones flujo ventas \n" + "1. Listar productos \n" + "2. Ver carrito \n"
						+ "3. Agregar al carrito \n" + "4. Eliminar del carrito \n" + "5. Pasar por caja \n" + "6. Salir \n"
						+ "Para salir de la apliación cancela \n" + "en las entradas de datos \n\n"
						+ "Elige una opción \n\n");

		return entradaTeclado;

	}

	public static void generarProductos() {

		productos.put("1", "Chocapic");
		productos.put("2", "Pescado");
		productos.put("3", "Pollo");
		productos.put("4", "Jamonero");
		productos.put("5", "Jamon");

		ivaProductos.put("1", IVA_GENERAL);
		ivaProductos.put("2", IVA_REDUCIDO);
		ivaProductos.put("3", IVA_REDUCIDO);
		ivaProductos.put("4", IVA_GENERAL);
		ivaProductos.put("5", IVA_REDUCIDO);

		precioProductos.put("1", 2.69);
		precioProductos.put("2", 3.24);
		precioProductos.put("3", 1.47);
		precioProductos.put("4", 1.92);
		precioProductos.put("5", 2.14);

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

			JOptionPane.showMessageDialog(null, "La aplicaciónn se cerrara");
			System.exit(0);

		}

		return esNumero;
	}

	// Valida y proporcionar numeros enteros
	public static String proporcionarEntradaProducto(String peticion, String listaProductos, String accion) {

		String entrada;
		String patronEntero = "\\d+";

		do {

			entrada = JOptionPane.showInputDialog("Elige el " + peticion + " que quieres " + accion + " \n"
					+ "(tiene que ser un entero positivo)\n" + listaProductos);

		} while (!validarEntradaPatron(patronEntero, entrada));

		return String.valueOf(entrada);

	}

	public static Integer proporcionarEntradaMenu() {

		String entrada;
		String patronEntero = "\\d+";

		do {

			entrada = mostrarMenu();

		} while (!validarEntradaPatron(patronEntero, entrada));

		return Integer.parseInt(entrada);

	}
	
	public static Double proporcionarEntradaCantidadPagar(Double importe, String cuenta) {

		String entrada;
		String patronDouble = "[+-]?\\d*(\\.\\d+)?";

		do {

			entrada = JOptionPane.showInputDialog(cuenta + "Introduce el importe con la que vas a pagar: " + importe + " € "
					+ "\n(el . es el separador decimal)\n");

		} while (!validarEntradaPatron(patronDouble, entrada));

		return Double.parseDouble(entrada);

	}


}

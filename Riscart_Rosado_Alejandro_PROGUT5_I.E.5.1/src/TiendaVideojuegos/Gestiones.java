package TiendaVideojuegos;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * 
 * Clase Gestiones, en esta clase voy a implementar las funcionalidades para la gestion de la tienda
 * @author Ale
 *
 */
public class Gestiones {
	
	static Scanner r;
	/**
	 * Aqui tenemos la coleccion de videojuegos
	 */
	static Map<Integer, Videojuego> videojuegos;
	/**
	 * con este atributo podemos saber si quedan cambios sin guardar
	 */
	static boolean cambiosPendientes = false;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		r = new Scanner(System.in);
		videojuegos = new HashMap<>();
		
		Leer();
		
		String opcion;
		
		do {
			menu();
			opcion = r.nextLine();

			switch (opcion) {
				case "0": exit();
						break;
				case "1": metervideojuegos();
						break;
				case "2": listarlosVideojuegos();
						break;
				case "3": deleteVideojuego();
						break;
				case "4": guardar();
						break;
				case "5": recuperarlosdato();
						break;
				default: System.out.println("ERROR!, Introduzca una opcion valida");
			}

		} while(!opcion.equals("0"));
		
		r.close();
		
	}

	/**
	 * Método menu, que muestra un menú con opciones
	 */
	public static void menu() {
		System.out.println("========================================");
		System.out.println("======== Gestión de Videojuegos ========");
		System.out.println("========================================");
		System.out.println("1.- Añadir un videojuego.");
		System.out.println("2.- Listar videojuegos.");
		System.out.println("3.- Borrar un videojuego.");
		System.out.println("4.- Guardar datos en fichero.");
		System.out.println("5.- Recuperar datos desde fichero.");
		System.out.println("");
		System.out.println("");
		System.out.println("0.- Salir de la aplicación.");
		System.out.println("========================================");
		System.out.println("Introduzca la opción elegida:");
	}
	
	/**
	 * Método Leer donde leemos el fichero "videojuegoss.dat". Si el fichero no esta, se informa de que no existen datos guardados y si existe, carga los datos guardados que haya
	 */
	public static void Leer() {
		try{
			File f = null;
			FileInputStream fe = null;
			ObjectInputStream ois = null;
				try {
					f = new File("videojuegoss.dat");
					if (f.exists()){
						fe = new FileInputStream(f);
						ois = new ObjectInputStream(fe);
						while(true){
							Videojuego t = (Videojuego) ois.readObject();
							videojuegos.put(t.getCodigo(), t);
						}
						
					}
				} catch(EOFException fg) {
					System.out.println(" --------------------------");
				} catch(FileNotFoundException fnf) {
					System.out.println("No existen los datos guardados " + fnf);
				} catch(IOException hn){
					hn.printStackTrace();
				} catch(Throwable hn) {
					hn.printStackTrace();
				} finally {
					//Al cargar los datos guardados podemos controlar el contador para que tenga en cuenta el numero de videojuegos.
					Integer[] cods = videojuegos.keySet().toArray(new Integer[0]); 
					//Por eso metemos esos datos de videojuegos en un array que creamos
					if(cods.length>0) {
						Videojuego.setC(cods[cods.length-1]+1); 
						// El contador sera igual al codigo del ultimo videojuego que hemos metido mas 1 claramente
					}
					if (ois != null) {
						ois.close();
						fe.close();
					}
				}
			} catch(IOException hn){
				hn.printStackTrace();
			}
	}
	
	/**
	 * Método metervideojuegos, que permite registrar el videojuego en la colección de videojuegos.
	 */
	public static void metervideojuegos() {
		System.out.println("Introduzca los datos del videojuego:");
		boolean error = true;
		do {
			error = false;
			try {
				System.out.println("Nombre del videojuego:");
				String nombre = r.nextLine();
				System.out.println("");
				System.out.println("Plataforma principal:");
				Plataformas plataforma = Plataformas.valueOf(r.nextLine());
				System.out.println("");
				System.out.println("Fecha de lanzamiento:");
				LocalDate fechaLanzamiento = LocalDate.parse(r.nextLine());
				System.out.println("");
				
				Videojuego v = new Videojuego();
				v.setNombre(nombre);
				v.setPlataforma(plataforma);
				v.setFechaLanzamiento(fechaLanzamiento);
				videojuegos.put(v.getCodigo(), v);
				System.out.println("Se ha creado el videojuego.");
				cambiosPendientes=true; 
				//cada vez que creamos un nuevo videojuego hay cambios sin guardar por tanto despues tendremos que guardar.
				
			} catch(IllegalArgumentException e) {
				System.err.println("Dato erroneo");
				error=true;
			} catch(DateTimeException d) {
				System.err.println("Fecha erronea");
				error=true;
			}
		} while(error); 
		//Si se ha introducido un dato que no corresponde repetimos la secuencia y pedimos de nuevo los datos.
		System.out.println("");
	}
	
	/**
	 * Método listarlosVideojuegos nos muestra la lista de videojuegos completa.
	 */
	public static void listarlosVideojuegos() {
		if(videojuegos.isEmpty()) {
			System.out.println("videojuegos no añadidos");
		} else {
			videojuegos.values().forEach(System.out::println);
		}
		System.out.println("");
	}
	
	/**
	 * Método deleteVideojuego que nos permite borrar un videojuego mediante el codigo.
	 */
	public static void deleteVideojuego() {
		if(videojuegos.isEmpty()) {
			System.out.println("videojuegos no añadidos");
		} else {
			System.out.println("Introduzca el código del videojuego que desea borrar:");
			int codigo = Integer.parseInt(r.nextLine());
			if(videojuegos.containsKey(codigo)) {
				System.out.println("Se va a borrar de la lista:");
				System.out.println(videojuegos.get(codigo).toString());
				System.out.println("¿Desea continuar con el borrado? (Si/No)");
				String eleccion = r.nextLine().toUpperCase();
				if(eleccion.equals("Si")) {
					videojuegos.remove(codigo);
					System.out.println("Videojuego perfectamente borrado");
					cambiosPendientes = true; //Al borrar el videojuego, existen cambios pendientes de guardar
				} else {
					System.out.println("borrado cancelado");
				}
			} else {
				System.out.println("No existe el videojuego nombrado");
			}
		}
		System.out.println("");
	}
	
	/**
	 * Método guardar es un metodo que hace que si hay cosas sin guardar (cambios) los puedes guardar en el archivo "videojuegoss.dat"
	 */
	public static void guardar() {
		if(cambiosPendientes) {
			try{
				FileOutputStream fs = new FileOutputStream("videojuegoss.dat");
				ObjectOutputStream oos = new ObjectOutputStream(fs);
				for (Videojuego v: videojuegos.values()){
					oos.writeObject(v);
				}
				if (oos != null){
					oos.close();
					fs.close();
				}
				System.out.println("Los datos se han guardado ");
				cambiosPendientes = false; 
				//cuando ya se han guardado los datos no hay mas cambios que guardar
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		System.out.println("");
	}
	
	/**
	 * Método recuperarlosdato nos permite ver la informacion del archivo "videojuegoss.dat".
	 */
	public static void recuperarlosdato() {
		if(cambiosPendientes) { 
			//Si existen cambios pendientes de guardar se avisará al usuario de que los cambios se perderán y se le pedirá confirmación antes de continuar
			System.out.println("Hay cambios sin guardar.");
			System.out.println("Si continúa la carga del archivo se restaurarán los datos de disco y se perderán los cambios no guardados.");
			System.out.println("¿Desea continuar con la carga de datos y restaurar los datos? (Si/No)");
			String eleccion = r.nextLine().toUpperCase();
			if(eleccion.equals("Si")) {
				Leer();
				cambiosPendientes = false;
				System.out.println("Se han recuperado los datos ");
			} else {
				System.out.println("Se ha cancelado la recuperación");
			}
		} else {
			Leer();
			System.out.println("Se han recuperado los datos");
		}
		
		System.out.println("");
	}
	
	/**
	 * Método exit hace que salgamos de la aplicacion
	 */
	
	public static void exit() {
		if(cambiosPendientes) { 
			//Si existen cambios pendientes guardaremos, antes de salir se avisa de que hay cambios sin guardar y que podra perder todos los datos.
			System.out.println("Hay cambios sin guardar");
			System.out.println("¿Desea guardarlos antes de salir? (Si/No)");
			String eleccion = r.nextLine().toUpperCase();
			if(eleccion.equals("Si")) {
				guardar();
			} else {
				System.exit(0);
			}
		}
		System.exit(0);
	}
}
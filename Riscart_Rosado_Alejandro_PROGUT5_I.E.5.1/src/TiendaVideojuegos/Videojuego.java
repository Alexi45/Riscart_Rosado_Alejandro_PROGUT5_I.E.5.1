package TiendaVideojuegos;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase Videojuego
 * 
 * @author Ale
 *
 */
public class Videojuego implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Atributo Código del videojuego
	 */
	private int codigo;
	/**
	 * Atributo estático que cuenta el total de videojuegos 
	 */
	private static int lc = 1;
	/**
	 * Atributo Nombre del videojuego (String)
	 */
	private String nombre;
	/**
	 * Atributo Fecha de lanzamiento del videojuego con el local date
	 */
	private LocalDate fechaLanzamiento;
	/**
	 * Atributo Plataformas las opciones son : DS, WII, XBOX, PS2, PS3, PS4 o PC
	 */
	private Plataformas plataforma;
	
	/**
	 * Constructor predeterminado
	 */
	public Videojuego() {
		this.codigo = lc; 
		//El código viene determinado por el contador de videojuegos. El código será único para cada videojuego 
		lc++;
	}

	/**
	 * Método getNombre
	 * @return Nombre del videojuego
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Método setNombre
	 * @param nombre Nombre del videojuego
	 */
	public void setNombre(String nombre) {
		Validacion.validarelNombre(nombre); 
		//Validamos el nombre
		this.nombre = nombre;	
	}

	/**
	 * Método getFechaLanzamiento
	 * @return Fecha de lanzamiento del videojuego
	 */
	public LocalDate getFechaLanzamiento() {
		return fechaLanzamiento;
	}

	/**
	 * Método setFechaLanzamiento
	 * @param fechaLanzamiento Fecha de lanzamiento del videojuego
	 */
	public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
		Validacion.validarlaFecha(fechaLanzamiento); 
		//Validamos si la fecha es correcta
		this.fechaLanzamiento = fechaLanzamiento;
	}

	/**
	 * Método getPlataforma
	 * @return Plataforma del videojuego
	 */
	public Plataformas getPlataforma() {
		return plataforma;
	}

	/**
	 * Método setPlataforma
	 * @param plataforma Plataforma del videojuego
	 */
	public void setPlataforma(Plataformas plataforma) {
		this.plataforma = plataforma;
	}
	
	/**
	 * Método getCodigo
	 * @return Código
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Método setCodigo
	 * @param codigo Codigo 
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Método getC
	 * @return Contador 
	 */
	public static int getC() {
		return lc;
	}

	/**
	 * Método setC
	 * @param c Contador 
	 */
	public static void setC(int c) {
		Videojuego.lc = c;
	}

	/**
	 * Método toString
	 */
	@Override
	
	public String toString() {
		return "Código del videojuego: " + codigo +
				"\nNombre del videojuego: " + nombre +
				"\nPlataforma: " + plataforma + 
				"\nFecha de lanzamiento: " + fechaLanzamiento;
	}	
	
}


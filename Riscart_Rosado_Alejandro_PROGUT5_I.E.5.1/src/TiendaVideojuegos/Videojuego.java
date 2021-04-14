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
	 * Atributo C�digo del videojuego
	 */
	private int codigo;
	/**
	 * Atributo est�tico que cuenta el total de videojuegos 
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
		//El c�digo viene determinado por el contador de videojuegos. El c�digo ser� �nico para cada videojuego 
		lc++;
	}

	/**
	 * M�todo getNombre
	 * @return Nombre del videojuego
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * M�todo setNombre
	 * @param nombre Nombre del videojuego
	 */
	public void setNombre(String nombre) {
		Validacion.validarelNombre(nombre); 
		//Validamos el nombre
		this.nombre = nombre;	
	}

	/**
	 * M�todo getFechaLanzamiento
	 * @return Fecha de lanzamiento del videojuego
	 */
	public LocalDate getFechaLanzamiento() {
		return fechaLanzamiento;
	}

	/**
	 * M�todo setFechaLanzamiento
	 * @param fechaLanzamiento Fecha de lanzamiento del videojuego
	 */
	public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
		Validacion.validarlaFecha(fechaLanzamiento); 
		//Validamos si la fecha es correcta
		this.fechaLanzamiento = fechaLanzamiento;
	}

	/**
	 * M�todo getPlataforma
	 * @return Plataforma del videojuego
	 */
	public Plataformas getPlataforma() {
		return plataforma;
	}

	/**
	 * M�todo setPlataforma
	 * @param plataforma Plataforma del videojuego
	 */
	public void setPlataforma(Plataformas plataforma) {
		this.plataforma = plataforma;
	}
	
	/**
	 * M�todo getCodigo
	 * @return C�digo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * M�todo setCodigo
	 * @param codigo Codigo 
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * M�todo getC
	 * @return Contador 
	 */
	public static int getC() {
		return lc;
	}

	/**
	 * M�todo setC
	 * @param c Contador 
	 */
	public static void setC(int c) {
		Videojuego.lc = c;
	}

	/**
	 * M�todo toString
	 */
	@Override
	
	public String toString() {
		return "C�digo del videojuego: " + codigo +
				"\nNombre del videojuego: " + nombre +
				"\nPlataforma: " + plataforma + 
				"\nFecha de lanzamiento: " + fechaLanzamiento;
	}	
	
}


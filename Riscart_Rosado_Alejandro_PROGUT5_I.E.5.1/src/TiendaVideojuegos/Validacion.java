package TiendaVideojuegos;


import java.time.DateTimeException;
import java.time.LocalDate;
/**
 * Clase Validacion
 * 
 * @author Ale
 *
 */
public class Validacion {

	/**
	 *  validarlaFecha es un metodo que comprueba si la fecha introducida es posterior a la local si es asi da un mensaje de error
	 * @param fechas 
	 */
    public static void validarlaFecha(LocalDate fechas) {
        if(fechas.isAfter(LocalDate.now())) {
        	throw new DateTimeException("La fecha introduzida es despues de la actual y no es valida");
        }
    }
    
    /**
     *  validadelNombre este metodo comprueba que esta vacio o es nulo sino lanzara un mensaje de error
     * @param nombre 
     */
    public static void validarelNombre(String nombre) {
    	if(nombre.equals("") || nombre==null) {
    		throw new IllegalArgumentException("El nombre es invalido");
    	}
    }
   
}


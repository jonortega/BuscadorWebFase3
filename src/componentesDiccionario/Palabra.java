package componentesDiccionario;

import java.util.ArrayList;

import componentesInternet.ListaWebs;
import componentesInternet.Web;

public class Palabra implements Comparable<Palabra> {

	private String laPalabra;
	private ListaWebs coincidencias;
	
	/**
	 * Constructora de la clase Palabra
	 * @param pal: la palabra
	 */
	public Palabra(String pal) {
		laPalabra = pal;
		coincidencias = new ListaWebs();
	}
	
	/**
	 * Getter para el atributo laPalabra
	 * @return El atributo laPalabra
	 */
	public String getLaPalabra() {
		return laPalabra;
	}
	
	/**
	 * Getter para el atributo coincidencias
	 * @return El atributo coincidencias
	 */
	public ListaWebs getCoincidencias() {
		return coincidencias;
	}
	
	/**
	 * Imprime la lista de las webs que coinciden con la palabra
	 */
	public void imprimirCoincidencias() {
		int i = 0;
		if(coincidencias.tamano() == 0) {
			System.out.print("No se han encontrado coincidencias\n");
		} else {
			ArrayList<Web> listaCoincidencias = coincidencias.getWebs();
			for(Web w : listaCoincidencias) {
				System.out.println(w.getNombre());
				i++;
			}
		}
		
		System.out.println("----------------------");
		System.out.println("Resultados: " + i);
	}

	/**
	 * Implementacion de compareTo para la clase Palabra
	 * @param palabra: la palabra con la que comparar
	 * @return  0 - si la palabra es igual
	 * 		   <0 - si la palabra recibida es mayor
	 * 		   >0 - si la palabra recibida es menor
	 */
	public int compareTo(Palabra p) {
		return (this.laPalabra.compareTo(p.getLaPalabra()));
	}

}

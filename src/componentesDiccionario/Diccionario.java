package componentesDiccionario;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import componentesInternet.Internet;
import componentesInternet.Web;

public class Diccionario {
	
	private static Diccionario instance;
	private InterfacePalabras palabras;
	
	/**
	 * Devuelve la unica instancia de la clase
	 * @return miDiccionario: la unica instancia de la clase Diccionario
	 */
	public static Diccionario getInstance() {
		if(instance == null) {
			instance = new Diccionario();
		}
		return instance;
	}
	
	/**
	 * Setter para el atributo palabras
	 * @param diccionario: La lista de palabras del tipo 
	 * 					   de dato que se le pasa
	 */
	public void setDiccionario(InterfacePalabras diccionario) {
		this.palabras = diccionario;
	}
	
	/**
	* Carga el diccionario desde el fichero indicado
	* @param nomFich: nombre del fichero que contiene el diccionario
	*/
	private void cargarPalabras(String nomFich) {
		try {
			Scanner fe = new Scanner(new FileReader(nomFich));
			
			while(fe.hasNext()) {
				//Obtener la palabra desde el fichero
				String nuevaPalabraString = fe.nextLine().trim();
				
				//Crear una web nueva y añadirla a la lista de webs
				Palabra nuevaPalabra = new Palabra(nuevaPalabraString);
				palabras.anadirPalabra(nuevaPalabra);
			}
			fe.close();
			System.out.println("Palabras cargadas correctamente.");
			
		} catch (FileNotFoundException e) {
			System.out.println("No se ha podido leer el archivo");
		}
	}
	
	/**
	* Asigna a cada palabra del diccionario las webs a las que hace
	* referencia
	* Pre: Internet y el diccionario ya estan cargados
	*/
	private void computarWebsDePalabras() {
		Internet internet = Internet.getInstance();
		System.out.println("Computando webs...");
		for(Web w : internet.getWebs().getWebs()) {
			for(int i=4; i<11; i++) {
				int j = 0;
				while(i+j <= w.getNombre().length()) {
					String substring = w.getNombre().substring(j, i+j);																	//Obtener los substrings
					Palabra palEncontrada = palabras.buscarPalabra(substring);																//Buscar ese substring en el diccionario
					if(palEncontrada != null) palEncontrada.getCoincidencias().anadirWeb(w);																		//Añadir esa web a la lista de coincidencias de la palabra
					j++;
				}//Para cada subpalabra posible dentro de la palabra
			}//Para cada tamano de subpalabra {4, 5, 6, 7, 8, 9, 10}
		}//Para cada palabra del diccionario
		System.out.println("Webs computadas correctamente.");
	}
	
	/**
	* Carga el diccionario desde el fichero indicado y asigna a cada palabra
	* del diccionario las webs a las que hace referencia
	* Pre: Internet ya esta cargado
	* @param nomFich: nombre del fichero que contiene el diccionario
	*/
	public void inicializar(String nomFich) {
		cargarPalabras(nomFich);
		computarWebsDePalabras();
	}
	
	/**
	* Busca una palabra en el diccionario y la devuelve
	* @param sPalabra: texto de la palabra a buscar
	* @return la Palabra (si esta en el diccionario), null en caso contrario
	*/
	public Palabra buscarPalabra(String sPalabra) {
		return palabras.buscarPalabra(sPalabra);
	}

}

package componentesInternet;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import componentesDiccionario.Diccionario;
import componentesDiccionario.Palabra;

public class Internet {
	
	private static Internet miInternet;
	private ListaWebs webs;

	/**
	 * Constructora de la clase Internet.
	 */
	private Internet() {
		webs = new ListaWebs();
	}
	
	/**
	 * Devuelve la unica instancia de la clase
	 * @return miInternet: la unica instancia de la clase Internet
	 */
	public static Internet getInstance() {
		if(miInternet == null) {
			miInternet = new Internet();
		}
		return miInternet;
	}
	
	/**
	 * Getter para el atributo webs
	 * @return El atributo web
	 */
	public ListaWebs getWebs() {
		return webs;
	}
	
	/**
	* Carga las webs contenidas en el fichero indicado
	* @param nomFich: nombre del fichero que contiene las webs
	*/
	private void cargarWebs(String nomFich) {
		try {
			Scanner fe = new Scanner(new FileReader(nomFich));
			
			while(fe.hasNext()) {
				//Obtener el nombre y el indice desde el fichero
				String[] arrayCampos = fe.nextLine().trim().split("\\s+");
				String newName = arrayCampos[0];
				int newIndex = Integer.parseInt(arrayCampos[1]);
				
				//Crear una web nueva y añadirla a la lista de webs
				Web newWeb = new Web(newIndex, newName);
				webs.anadirWeb(newWeb);
			}
			fe.close();
			System.out.println("Webs cargadas correctamente.");
			
		} catch (FileNotFoundException e) {
			System.out.println("No se ha podido leer el archivo.");
		}
	}
	
	/**
	* Carga los enlaces contenidos en el fichero indicado
	* @param nomFich: nombre del fichero que contiene los enlaces
	*/
	private void cargarEnlaces(String nombFich) {
		try {
			Scanner fe = new Scanner(new FileReader(nombFich));
			
			while(fe.hasNext()) {
				//Obtener el indice y los enlaces salientes desde el archivo
				String[] arrayCampos = fe.nextLine().trim().split("\\s+");
				int idWebOrigen = Integer.parseInt(arrayCampos[0]);
				int idWebDestino = Integer.parseInt(arrayCampos[1]);
				
				//Añadir el enlace nuevo a la web indicada
				webs.anadirEnlace(idWebOrigen, idWebDestino);
			}
			fe.close();
			System.out.println("Enlaces cargados correctamente.");
			
		} catch (FileNotFoundException e) {
			System.out.println("No se ha podido leer el archivo.");
		}
	}
	
	/**
	* Inicializa la clase cargando las webs y los enlaces
	* @param nomFichWebs: nombre del fichero que contiene las webs
	* @param nomFichEnlaces: nombre del fichero que contiene los enlaces
	*/
	public void inicializar(String nomFichWebs, String nomFichEnlaces) {
		cargarWebs(nomFichWebs);
		cargarEnlaces(nomFichEnlaces);
	}
	
	/**
	* Dado un string que contiene una palabra, imprime por pantalla las webs
	* que tienen dicha palabra clave
	* @param sPalabra: string con la palabra
	*/
	public void buscadorWeb(String sPalabra) {
		Diccionario diccionario = Diccionario.getInstance();
		Palabra pal = diccionario.buscarPalabra(sPalabra);
		if(pal != null) pal.imprimirCoincidencias();
		else System.out.print("No se han encontrado coincidencias\n");
	}

}

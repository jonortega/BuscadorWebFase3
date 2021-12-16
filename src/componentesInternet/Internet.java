package componentesInternet;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
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
	
	/**
	* Dadas dos URL indica si existe un camino de enlaces desde la URL de
	* origen hasta la de destino
	* @param url1: URL de origen
	* @param url2: URL de destino
	* @return: true si existe el camino, false si no existe
	*/
	public boolean estanConectados(String url1, String url2) {
		boolean encontrado = false;
		Web origen = webs.buscarWebPorURL(url1);
		Web destino = webs.buscarWebPorURL(url2);
		
		if(origen==null || destino==null) System.out.println("No existe camino.");
		else {
			HashSet<Web> visitados = new HashSet<Web>();
			visitados.add(origen);
			Queue<Web> cola = new LinkedList<Web>();
			cola.add(origen);
			while(!cola.isEmpty() && !encontrado) {
				Web laWeb = cola.remove();
				if(laWeb.getNombre().equals(destino.getNombre())) encontrado = true;				
				else {
					Iterator<Web> ite = laWeb.getLinks().getWebs().iterator();
					while(ite.hasNext()) {
						Web aux = ite.next();
						if(!visitados.contains(aux)) {
							cola.add(aux);
							visitados.add(aux);
						}
					}
				}
			}
		}
		return encontrado;
	}

	/**
	* Dadas dos URL imprime el camino mas corto desde la URL de origen hasta
	* la de destino, si es que existe
	* @param url1: URL de origen
	* @param url2: URL de destino
	*/
	public void imprimirCamino(String url1, String url2) {
		Web origen = webs.buscarWebPorURL(url1);
		Web destino = webs.buscarWebPorURL(url2);
		
		if(origen==null || destino==null) System.out.println("No existe camino.");
		else {
			LinkedList<String> resultado = new LinkedList<String>();
			HashMap<Web, Web> visitados = new HashMap<Web, Web>();
			boolean encontrado = false;
			
			visitados.put(origen, null);
			Queue<Web> cola = new LinkedList<Web>();
			cola.add(origen);
			
			while(!cola.isEmpty() && !encontrado) {
				Web laWeb = cola.remove();
				
				if(laWeb.getNombre().equals(destino.getNombre())) encontrado = true;
				else {
					Iterator<Web> ite = laWeb.getLinks().getWebs().iterator();
					while(ite.hasNext()) {
						Web aux = ite.next();
						if(!visitados.containsKey(aux)) {
							cola.add(aux);
							visitados.put(aux, laWeb);
						}
					}
				}
			}
			if(encontrado) {
				Web actual = destino;
				while(actual!=null) {
					resultado.addFirst(actual.getNombre());
					actual = visitados.get(actual);
				}
				System.out.print("<");
				while(resultado.peek()!=null) {
					System.out.print(resultado.remove() + ", ");
					if(resultado.size() == 1) System.out.print(resultado.remove());
				}
				System.out.println(">");
			} else {
				System.out.println("No existe camino.");
			}
		}
	}
}

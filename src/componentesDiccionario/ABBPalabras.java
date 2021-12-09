package componentesDiccionario;

import java.util.LinkedList;

public class ABBPalabras implements InterfacePalabras {
	
	private NodoABBPalabras root;

	/**
	 * Constructora de la clase ABBPalabras
	 */
	public ABBPalabras() {
		this.root = null;
	}
	
	/**
	 * Constructora de la clase ABBPalabras
	 * @param info: el info del nodo raiz
	 */
	public ABBPalabras(Palabra info) {
		this.root = new NodoABBPalabras(info);
	}
	
	/**
	 * Constructora de la clase con parametro
	 * @param root: el nodo raiz
	 */
	public ABBPalabras(NodoABBPalabras root) {
		this.root = root;
	}
	
	/**
	 * Comprueba si el arbol es vacio
	 * @return true - si el arbol es vacio
	 * 		   false - si el arbol no es vacio
	 */
	public boolean isEmpty() {
		return (this.root == null);
	}

	/**
	* Anade una palabra a la lista
	* @param palabra: palabra a anadir
	*/
	@Override
	public void anadirPalabra(Palabra palabra) {
		if(this.isEmpty()) this.root = new NodoABBPalabras(palabra);
		else this.root.anadirPalabra(palabra);
	}

	/**
	* Busca una palabra en la lista y la devuelve
	* @param sPalabra: texto de la palabra a buscar
	* @return la Palabra (si esta en la lista), null en caso contrario
	*/
	@Override
	public Palabra buscarPalabra(String sPalabra) {
		if(this.isEmpty()) return null;
		else return this.root.buscarPalabra(sPalabra);
	}
	
	/**
	* Devuelve una lista con todas aquellas palabras del arbol que no sean
	* palabra clave de ninguna web
	* @return lista con las palabras a eliminar
	*/
	private LinkedList<Palabra> obtenerPalabrasAEliminar() {
		if(this.isEmpty()) return new LinkedList<Palabra>();
		else return root.obtenerPalabrasAEliminar();
	}
	
	/**
	* Elimina del arbol la palabra pasada como parametro
	* Pre: la palabra como mucho esta una vez en el diccionario
	* @param pal: palabra a eliminar
	*/
	private void eliminarPalabra(Palabra pal) {
		if(!this.isEmpty()) this.root.eliminarPalabra(pal);
	}
	
	/**
	* Haciendo uso de los metodos anteriores, obtiene la lista de palabras
	* del arbol a eliminar y elimina cada una de ellas.
	*/
	public void filtrarPalabrasClave() {
		LinkedList<Palabra> lista =  obtenerPalabrasAEliminar();
		for(Palabra p : lista) eliminarPalabra(p);
	}
}

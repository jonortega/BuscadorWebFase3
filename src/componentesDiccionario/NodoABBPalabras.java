package componentesDiccionario;

import java.util.LinkedList;

public class NodoABBPalabras {
	
	Palabra info;
	NodoABBPalabras left;
	NodoABBPalabras right;


	/**
	 * Constructora de la clase
	 * @param info: La informacion del nodo
	 */
	public NodoABBPalabras(Palabra info) {
		this.info = info;
	}
	
	/**
	 * Comprueba si el nodo tiene arbol izquierdo
	 * @return true - si tiene subarbol izquierdo
	 * 		   false - si no tiene subarbol izquierdo
	 */
	public boolean hasLeft() {
		return (this.left != null);
	}
	
	/**
	 * Comprueba si el nodo tiene arbol derecho
	 * @return true - si tiene subarbol derecho
	 * 		   false - si no tiene subarbol derecho
	 */
	public boolean hasRight() {
		return (this.right != null);
	}
	
	/**
	 * Comprueba si el nodo es una hoja del arbol
	 * @return true - si es hoja
	 * 		   false - si no es hoja
	 */
	public boolean isLeaf() {
		return (this.left == null && this.right == null);
	}
	
	/**
	 * Anade una palabra al arbol
	 * @param palabra: palabra a anadir
	 */
	public void anadirPalabra(Palabra palabra) {
		if(palabra.compareTo(this.info) < 0) {	//Ira a la izquierda
			if(this.hasLeft()) this.left.anadirPalabra(palabra);
			else this.left = new NodoABBPalabras(palabra);
		} else {								//Ira a la derecha
			if(this.hasRight()) this.right.anadirPalabra(palabra);
			else this.right = new NodoABBPalabras(palabra);
		}
	}
	
	/**
	 * Busca una palabra en el arbol y la devuelve
	 * @param sPalabra: texto de la palabra a buscar
	 * @return la Palabra (si esta en el arbol), null en caso contrario
	 */
	public Palabra buscarPalabra(String sPalabra) {
		Palabra devol = null;
		if(sPalabra.compareTo(this.info.getLaPalabra()) == 0) devol = this.info;
		else if(sPalabra.compareTo(this.info.getLaPalabra()) < 0) {
			if(this.hasLeft()) devol = this.left.buscarPalabra(sPalabra);
		} else {
			if(this.hasRight()) devol = this.right.buscarPalabra(sPalabra);
		}
		return devol;
	}
	
	/**
	* Devuelve una lista con todas aquellas palabras del arbol que no sean
	* palabra clave de ninguna web
	* @return lista con las palabras a eliminar
	*/
	public LinkedList<Palabra> obtenerPalabrasAEliminar() {
		LinkedList<Palabra> lista = new LinkedList<Palabra>();
		if(this.info.getCoincidencias().tamano() == 0) lista.add(this.info);
		if(this.hasLeft()) lista.addAll(this.left.obtenerPalabrasAEliminar());
		if(this.hasRight()) lista.addAll(this.right.obtenerPalabrasAEliminar());
		return lista;
	}
	
	/**
	 * Elimina el nodo con el menor valor del arbol
	 * @return Clase auxiliar con el valor y la referencia al nodo
	 */
	public ResultadoRemoveMin removeMin() {
		   ResultadoRemoveMin resul = new ResultadoRemoveMin();
		   if(!this.hasLeft()) {//El minimo es el actual
		      resul.elValor = this.info;
		      resul.elNodo = this.right; 
		   }else { //El minimo esta en el subarbol izquierdo
		      ResultadoRemoveMin resulLeft = this.left.removeMin();
		      this.left = resulLeft.elNodo; 
		      resul.elValor = resulLeft.elValor;
		      resul.elNodo = this; 
		    }
		    return resul;
	}
	
	/**
	* Elimina del arbol la palabra pasada como parametro
	* Pre: la palabra como mucho esta una vez en el diccionario
	* @param pal: palabra a eliminar
	*/
	public NodoABBPalabras eliminarPalabra(Palabra pal) {
		int comp = pal.compareTo(this.info);
		if(comp==0) {// this es el nodo a eliminar
			if(!this.hasLeft()) return this.right;
		    else {
		    	if(!this.hasRight()) return this.left;
		    	else {
		    		ResultadoRemoveMin min = this.right.removeMin();
		        	this.right = min.elNodo;
		        	this.info = min.elValor;
		        	return this;
		    	}
		    }
		}else {
			if(comp<0){// El elemento a eliminar, si esta, estaria en el subarbol izquierdo
		    	if(this.hasLeft()) this.left = this.left.eliminarPalabra(pal);
		    }else {// El elemento a eliminar, si esta, estaria en el subarbol derecho
		    	if (this.hasRight()) this.right = this.right.eliminarPalabra(pal);
		    }
			return this;
		}
	}

}

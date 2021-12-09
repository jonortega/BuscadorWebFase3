package componentesDiccionario;

import java.util.ArrayList;

public class ListaPalabras implements InterfacePalabras {

	private ArrayList<Palabra> palabras;
	
	/**
	 * Constructora de la clase ListaPalabras: Genera una lista vacia
	 */
	public ListaPalabras() {
		palabras = new ArrayList<Palabra>();
	}
	
	/**
	* Anade una palabra a la lista
	* @param palabra: palabra a anadir
	*/
	@Override
	public void anadirPalabra(Palabra palabra) {
		palabras.add(palabra);
	}
	
	/**
	* Busca una palabra en la lista y la devuelve. Se hacen con busqueda dicotomica
	* @param sPalabra: texto de la palabra a buscar
	* @return la Palabra (si esta en la lista), null en caso contrario
	*/
	@Override
	public Palabra buscarPalabra(String sPalabra) {
		int izq = 0;
		int der = palabras.size()-1;
		int medio = (izq + der)/2;
		Palabra devol = null;

		while((izq < der) && (!palabras.get(medio).getLaPalabra().equals(sPalabra))) {
			if(palabras.get(medio).getLaPalabra().compareToIgnoreCase(sPalabra) > 0) {
				der = medio-1;
			} else {
				izq = medio+1;
			}
			medio = (izq + der)/2;
		}
		if(palabras.get(medio).getLaPalabra().equals(sPalabra)) devol = palabras.get(medio);
		return devol;
	}

}

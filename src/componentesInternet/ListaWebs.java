package componentesInternet;

import java.util.ArrayList;

public class ListaWebs {
	
	private ArrayList<Web> webs;

	/**
	 * Constructora de la clase ListaWebs
	 */
	public ListaWebs() {
		webs = new ArrayList<Web>();
	}
	
	/**
	 * Devuelve el tamano de la lista
	 * @return El tamano de webs
	 */
	public int tamano() {
		return webs.size();
	}
	
	/**
	 * Getter del atributo webs.
	 * @return un ArrayList con la lista de las webs.
	 */
	public ArrayList<Web> getWebs() {
		return this.webs;
	}
	
	/**
	 * Anade una web a la lista
	 * @param web: la web a anadir
	 * PRE: web no esta en la lista
	 */
	public void anadirWeb(Web web) {
		webs.add(web);
	}
	
	/**
	* Anade un enlace a una web de la lista
	* @param idWebOrigen: id de la web de origen
	* @param idWebDestino: id de la web de destino
	* PRE: las webs con id idWebOrigen e idWebDestino estan en la lista
	*/ 
	public void anadirEnlace(int idWebOrigen, int idWebDestino) {
		webs.get(idWebOrigen).anadirEnlace(idWebDestino);
	}
	
	/**
	* Dada una URL, devuelve la web de la lista que tiene dicha URL
	* @param url: URL a buscar
	* @return: Web con dicha URL (si está en la lista), si no null.
	*/
	public Web buscarWebPorURL(String url) {
		for(Web w : webs) {
			if(url.equals(w.getNombre())) return w;
		}
		return null;
	}

}

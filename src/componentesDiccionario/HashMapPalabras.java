package componentesDiccionario;

import java.util.HashMap;

public class HashMapPalabras implements InterfacePalabras {
	private HashMap<String, Palabra> hm;
	
	/**
	 * Constructora de la clase HashMapPalabras.
	 */
	public HashMapPalabras() {
		hm = new HashMap<String, Palabra>();
	}

	@Override
	public void anadirPalabra(Palabra palabra) {
		hm.put(palabra.getLaPalabra(), palabra);
	}

	@Override
	public Palabra buscarPalabra(String sPalabra) {
		return hm.get(sPalabra);
	}
}

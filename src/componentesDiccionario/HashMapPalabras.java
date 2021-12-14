package componentesDiccionario;

import java.util.HashMap;

public class HashMapPalabras implements InterfacePalabras {
	HashMap<String, Palabra> hm;

	@Override
	public void anadirPalabra(Palabra palabra) {
		hm.put(palabra.getLaPalabra(), palabra);
	}

	@Override
	public Palabra buscarPalabra(String sPalabra) {
		return hm.get(sPalabra);
	}
}

package questao2;

import java.util.HashMap;

public class FiguraFactory{ 
	Figura createFigura(String type) {
		
		HashMap<String, Figura> mapa = new HashMap<String, Figura>();
		mapa.put("circulo", new Circulo());
		mapa.put("quadrado", new Quadrado());
		mapa.put("trequilatero", new TrEquilatero());
		return mapa.get(type.toLowerCase());
	} 
} 

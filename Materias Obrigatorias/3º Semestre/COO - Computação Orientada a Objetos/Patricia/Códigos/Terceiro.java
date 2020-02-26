//import java.util.Map;
//import java.util.TreeMap;
import java.util.*;
class Terceiro{

	static TreeMap <Integer, String> mapa;
	
	public static void main (String [] args) throws InterruptedException{
	
	
		Thread.sleep(2000);
		mapa = new TreeMap <Integer, String>();
		mapa.put (455,"vermelho");
		mapa.put (333,"branco");
		mapa.put (678,"amarelo");
		mapa.put (455,"azul");
		
		mapa.tailMap(455).clear();
		System.out.println (mapa);
	}

}
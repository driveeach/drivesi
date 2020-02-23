class Nove {
	public static void main(String[] args) {
		double pe = 2;
		double pol = 3;
		double jarda = 4;
		double milha = 5;
		double km;
			km = (pe * 12 * 25.3995) / 1000000; // pé - km
				System.out.println (pe+" pé(s) equivalem a " +km+ " quilômetros");
			km = (pol * 25.3995) / 1000000; // pol - km
				System.out.println (pol+" polegada(s) equivalem a " +km+ " quilômetros");
			km = (jarda * 3 * 12 * 25.3995) / 1000000; // jarda - km
				System.out.println (jarda+" jarda(s) equivalem a " +km+ " quilômetros");	
			km = (milha * 1760 * 3 * 12 * 25.3995) / 1000000; // milha - km
				System.out.println (milha+" milha(s) equivalem a " +km+ " quilômetros");
    }  	 
}

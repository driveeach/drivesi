/*
	Programa para calcular a área de uma casa (e seus cômodos) de 3
	cômodos: uma sala de 10X10m, um banheiro e um quarto de 5X7m cada.
*/
class AreaCasa {
/* Calcula área da casa */
	static void areaCasa(float lateral, float cquarto) {
		float areaq; // área do quarto
		float areas; // área da sala
		float areat; // área total

		System.out.println ("Programa para cálculo \nda área da casa");
		// cálculo da área da sala
		areas = lateral*lateral;
		System.out.println ("A área da sala é " +areas);
		// cálculo da área do banheiro
		areaq = cquarto*(lateral/2);
		System.out.println ("A área do banheiro é " +areaq);
		// cálculo da área do quarto
		System.out.println ("A área do quarto é " +areaq);
		// cálculo da área total
		areat = areas + 2*areaq;
		System.out.println ("A área total é " +areat); 
}		
/* Calcula a área da piscina */
	static double areaPiscina (double raio) {
		// cálculo da área da piscina
		return Math.PI*raio*raio;
}	
	public static void main(String[] args) {
		double areap; // área da piscina
		areap = areaPiscina (2);

		areaCasa(11,7);
		areaPiscina(2);
		System.out.println ("A área da piscina é " +areap);
	}
}
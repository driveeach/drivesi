/* Crie a seguinte classe que represente figuras esfericas:
Atributos: Raio
Metodos:
{ area() { calcula a area da superficie da esfera
{ volume() { calcula o volume da esfera */
class FiguraEsferica {
	double raio;
	double area () {
		double area;
		area = 4 * Math.PI * raio * raio;
		return area;
	}
	double volume () {
		double volume;
		volume = 4 * Math.PI * raio * raio * raio / 3;
		return volume;
	}
}	
class Seis {
	public static void main (String [] args) {
		FiguraEsferica a = new FiguraEsferica();
		a.raio = 2;
		System.out.println (a.area());
		System.out.println (a.volume());
	}
}
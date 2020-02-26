public class IntervaloDeTempo {

	private int horas;
	private int minutos;
	private int segundos;
	
	public IntervaloDeTempo(int qtdeSegundos) {
		this.horas = qtdeSegundos / 3600;
		qtdeSegundos = qtdeSegundos % 3600;
		this.minutos = qtdeSegundos / 60;
		this.segundos = qtdeSegundos % 60;
	}

	public String toString() {
		return "(" + horas + "," + minutos + "," + segundos + ")";
	}	
	
	public static void main(String[] args) {
		System.out.println(new IntervaloDeTempo(3500));
		System.out.println(new IntervaloDeTempo(4000));
	}
}

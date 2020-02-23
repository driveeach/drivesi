/*Defina uma classe IntervaloDeTempo cujos objetos representam um intervalo de tempo
em numero de horas, minutos e segundos. O construtor de objetos dessa classe deve
receber como argumento um numero inteiro positivo, representando o numero de segundos
decorridos desde o instante inicial 00:00:00 horas e retornar um objeto da classe
IntervaloDeTempo correspondente.
Por exemplo, a expressao new IntervaloDeTempo(3500) deve retornar um objeto que
represente 0 horas, 58 minutos e 20 segundos. Crie tambem uma classe para testar a
classe defiinida.*/
class IntervaloDeTempo {
	static int minutos;
	static int horas;
	IntervaloDeTempo (int segundos) {
		if (segundos <60) IntervaloDeTempo.segundos(segundos);
		if (segundos >= 60 && segundos < 3600) IntervaloDeTempo.minutos(segundos);
		if (segundos >= 3600) IntervaloDeTempo.horas(segundos);
	}
	static void horas (int segundos) {
		minutos = segundos / 60;
		segundos = segundos % 60;
		horas = minutos / 60;
		minutos = minutos % 60;
		System.out.println (horas +" horas, " +minutos+ " minutos e " +segundos+ " segundos.");
	}
	static void minutos (int segundos) {
		minutos = segundos / 60;
		segundos = segundos % 60;
		System.out.println ("0 horas, " +minutos+ " minutos e " +segundos+ " segundos.");
	}
	static void segundos (int segundos) {
		System.out.println ("0 horas, 0 minutos e " +segundos+ " segundos.");
	}
}
class Treze {
	public static void main (String [] args) {
		IntervaloDeTempo a = new IntervaloDeTempo(3600);
	}
}
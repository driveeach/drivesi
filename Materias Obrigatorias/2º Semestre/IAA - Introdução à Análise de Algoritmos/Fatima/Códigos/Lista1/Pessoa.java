public class Pessoa {

	private String nome;

	private char sexo;

	private int nroId;

	private Data nasc;

	public Pessoa(String nome, char sexo, int nroId, Data nasc) {
		this.nome = nome;
		this.sexo = sexo;
		this.nroId = nroId;
		this.nasc = nasc;
	}

	public int idade(Data atual) {

		int[] dias = new int[] { 0, 31, 59, 90, 120, 151, 181, 212, 243, 273,
				304, 334 };

		int calculo = (atual.getAno() - nasc.getAno()) * 365
				+ dias[atual.getMes() - 1] - dias[nasc.getMes() - 1]
				+ atual.getDia() - nasc.getDia();

		if (calculo < 0) {
			return -1;
		} else {
			return calculo / 365;
		}
	}

	
	@Override
	public String toString() {
		return "(" + nome + "," + sexo + "," + nroId + ")";
	}

	public static void main(String[] args) {
		Pessoa p = new Pessoa("Zé", 'M', 1234, new Data(8, 5, 2000));
		System.out.println(p.idade(new Data(7, 5, 2001)));
		System.out.println(p.idade(new Data(9, 5, 2001)));
		System.out.println(p.idade(new Data(7, 5, 2000)));
	}
}

class Data {
	private int ano;
	private int mes;
	private int dia;

	public Data(int dia, int mes, int ano) {
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
	}

	public int getAno() {
		return ano;
	}

	public int getMes() {
		return mes;
	}

	public int getDia() {
		return dia;
	}
}
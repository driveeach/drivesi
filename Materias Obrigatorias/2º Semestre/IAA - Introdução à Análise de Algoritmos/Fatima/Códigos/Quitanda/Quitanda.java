// Como regra geral, devemos sempre evitar duplicacao de codigo.
// Sempre eh util refatorar (reorganizar) o codigo para evitar 
// repeticao.
//
// Neste caso, iremos manter o codigo de ordenacao em apenas um metodo e colocar em
// outros metodos apenas aquele pequeno trecho que difere de um para outro (criterio 
// de comparacao). Para implementar esta nova solucao, utilizaremos uma interface
// definindo o interface de comparacao:

public class Quitanda {
	Fruta[] frutas = new Fruta[5];

	public Quitanda() {
		frutas[0] = new Fruta("Laranja", 0.5, 100);
		frutas[1] = new Fruta("Maçã", 0.8, 120);
		frutas[2] = new Fruta("Mamão", 1.2, 110);
		frutas[3] = new Fruta("Cereja", 5.0, 20);
		frutas[4] = new Fruta("Jaca", 0.4, 500);
	}

	public void imprime() {
		System.out.println("--------------");
		for (int i = 0; i < frutas.length; i++) {
			frutas[i].imprime();
		}
		System.out.println("--------------");
	}

	public void ordena(ComparadorDeFrutas c) {
		int i, j;
		Fruta AInserir;
		int fim = frutas.length;

		for (i = 1; i < fim; i++) {
			AInserir = frutas[i];
			j = i;
			while ((j > 0) && (!c.ehMenor(frutas[j - 1], AInserir))) {
				frutas[j] = frutas[j - 1];
				j--;
			}
			frutas[j] = AInserir;
		}
	}

	public static void main(String[] args) {
		Quitanda xepa = new Quitanda();
		
		System.out.println("Desordenado");
		xepa.imprime();
		
		System.out.println("Em ordem de valor");
		ComparadorDeFrutas cmp = new ComparaPreco();
		xepa.ordena(cmp);
		xepa.imprime();

		System.out.println("Em ordem de peso");
		cmp = new ComparaPeso();
		xepa.ordena(cmp);
		xepa.imprime();

		System.out.println("Em ordem de alfabetica");
		xepa.ordena(new ComparaNome());
		xepa.imprime();
	}
}

// Importa�cia das interfaces:

// encapsulamento: muda-se a implementacao, sem precisar mudar quem utiliza a
// implementacao
// polimorfismo de comportamento : utilizar em diferentes momentos classes com
// diferentes compoortamento
// com pequeno esforco.
// Divisao de atividades em sistemas grandes: definidas as interfaces, cada
// grupo desenvolve suas classes independentemente. O problema e integrar depois.
// Eliminacao de codigo repetido:
// Subcontratacao de partes do sistema.

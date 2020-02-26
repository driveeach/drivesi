// implementação simples de lista usando vetor
class Lista {

	private int [] valores;
	private int indice;

	// cria uma lista vazia
	public Lista(){

		valores = new int[100];
		indice = 0;
	}

	// adiciona x na lista
	public void adiciona(int x){

		valores[indice] = x;
		indice++;
	}

	// adiciona todos os valores de l na lista
	public void adiciona(Lista l){

		for(int i = 0; i < l.tamanho(); i++){

			adiciona(l.valores[i]);
		}
	}

	// devolve tamanho da lista
	public int tamanho(){

		return indice;
	}

	// imprime conteúdo da lista
	public void imprime(){

		System.out.print("[");
		for(int i = 0; i < indice; i++) System.out.print(" " + valores[i]);
		System.out.println(" ]"); 
	}
}

// implementação alternativa de lista que permite criar uma nova
// lista a partir de uma já existente de modo mais eficiente
class Lista2 {

	int tamanho;
	int cabeca;
	Lista2 cauda;

	public Lista2(int i, Lista2 n){

		cabeca = i;
		cauda = n;

		if(cauda != null) tamanho = cauda.tamanho() + 1;
		else tamanho = 1;
	}

	public int tamanho() {

		return tamanho;
	}

	public void imprime(){

		System.out.print("[");
		imprime_rec();
		System.out.println(" ]");
	}

	public void imprime_rec(){
	
		System.out.print(" " + cabeca);
		if(cauda != null) cauda.imprime_rec();
	}
}

public class Troco {

	// algoritmo guloso que resolve o problema do troco
	public static Lista troco_guloso(int n, int [] v){

		Lista l = new Lista();

		for(int i = 0; i < v.length; i++){
	
			while(n >= v[i]) {

				l.adiciona(v[i]);
				n = n - v[i];
			}
		}

		return l;
	}

	// algoritmo que emprega uma estratégia de tentativa
	// e erro para resolver o problema do troco
	public static Lista troco_rec(int n, int [] v){

		if(n == 0) return new Lista();

		Lista melhor = null;
		int melhor_i = -1;

		for(int i = 0; i < v.length; i++){

			if(n - v[i] >= 0) {

				Lista l = troco_rec(n - v[i], v);

				if(l != null) {

					if(melhor == null || l.tamanho() < melhor.tamanho()){

						melhor = l;
						melhor_i = i;
					}
				}
			}
		}

		if(melhor != null) {

			melhor.adiciona(v[melhor_i]);
		}

		return melhor;
	}

	// algoritmo que emprega programação dinâmica 
	// para resolver o problema do troco
	public static Lista troco_din(int n, int [] v){

		Lista [] tabela = new Lista[n + 1];

		tabela[0] = new Lista();

		for(int x = 1; x <= n; x++){

			Lista melhor = null;
			int melhor_i = -1;

			for(int i = 0; i < v.length; i++){

				int sub = x - v[i];

				if(sub >= 0 && tabela[sub] != null){

					if(melhor == null || tabela[sub].tamanho() < melhor.tamanho()){

						melhor = tabela[sub];
						melhor_i = i;
					}
				}
			}

			if(melhor != null){

				tabela[x] = new Lista();
				tabela[x].adiciona(v[melhor_i]);
				tabela[x].adiciona(melhor);
			}
		}

		return tabela[n];
	}

	// Outra versão que emprega programação dinâmica 
	// para resolver o problema do troco, mas usando
	// uma lista mais eficiente.
	public static Lista2 troco_din2(int n, int [] v){

		Lista2 [] tabela = new Lista2[n + 1];

		tabela[0] = new Lista2(0, null);

		for(int x = 1; x <= n; x++){

			Lista2 melhor = null;
			int melhor_i = -1;

			for(int i = 0; i < v.length; i++){

				int sub = x - v[i];

				if(sub >= 0 && tabela[sub] != null){

					if(melhor == null || tabela[sub].tamanho() < melhor.tamanho()){

						melhor = tabela[sub];
						melhor_i = i;
					}
				}
			}

			if(melhor != null){

				// este trecho fica mais eficiente usando a Lista2
				tabela[x] = new Lista2(v[melhor_i], melhor);
			}
		}

		return tabela[n];
	}

	public static void teste(int n, int [] v){

		System.out.print("Solucao para n = " + n + " e v = {");
		for(int x : v) System.out.print(" " + x);
		System.out.println(" }");

		Lista l1 = troco_guloso(n, v);
		System.out.print("Algoritmo guloso: ");
		l1.imprime();

		Lista l2 = troco_rec(n, v);
		System.out.print("Tentativa e erro: ");
		l2.imprime();
			
		Lista l3 = troco_din(n, v);
		System.out.print("Programacao dinamica: ");
		l3.imprime();

		Lista2 l4 = troco_din2(n, v);
		System.out.print("Programacao dinamica (com lista mais eficiente): ");
		l4.imprime();

		System.out.println();
	}

	public static void main(String [] args){

		int [] v1 = { 4, 3, 1 };
		int [] v2 = { 50, 20, 10, 5 };
		int [] v3 = { 50, 20 };

		teste(5, v1);
		teste(6, v1);
		teste(70, v2);
		teste(80, v2);
		teste(70, v3);
		teste(80, v3);
	}
}

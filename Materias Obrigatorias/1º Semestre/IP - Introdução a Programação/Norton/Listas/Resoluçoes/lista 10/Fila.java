import java.util.Scanner;

class Fila {
	ListaSimples l = new ListaSimples ();
	void cria (){
		Scanner sc = new Scanner (System.in);
		int num = 0;
		System.out.println ("Digite os números para colocar na fila e -1 para parar");
		while (num != -1){
			num = sc.nextInt();
			if (num != -1){
				No novo = new No (num);
				if (l.cabeca == null) {
					l.cabeca = novo;
				}
				else {
					No n = l.cabeca;
					while (n.prox != null){
						n = n.prox;
					}
					n.prox = novo;
				}
			}
		}
	}
	void imprime (){
		No n = l.cabeca;
		while (n != null){
			System.out.print (n.letra + " ");
			n = n.prox;
		}
		System.out.println ();
		
	}
	void insere (int num){
		No novo = new No (num);
		No n = l.cabeca; 
		while (n.prox != null){
			n = n.prox;
		}
		n.prox = novo;
	}
	void exclui(){
		l.cabeca = l.cabeca.prox;
	}
	void esvazia (){
		while (l.cabeca != null){
			l.cabeca = l.cabeca.prox;
		}
	}
	public static void main (String [] args) {
		Fila f = new Fila ();
		f.cria();
		f.imprime();
		f.insere (4);
		f.imprime();
		f.exclui();
		f.imprime();
		f.esvazia();
		f.imprime();
	}
}
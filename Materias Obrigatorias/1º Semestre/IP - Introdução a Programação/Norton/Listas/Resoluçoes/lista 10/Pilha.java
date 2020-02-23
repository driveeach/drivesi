import java.util.Scanner;

class Pilha {
	ListaSimples l = new ListaSimples();
	void cria (){
		Scanner sc = new Scanner (System.in);
		int num = 0;
		System.out.println ("Digite o numero para colocar na pilha e -1 para parar");
		while (num != -1){
			num = sc.nextInt();
			if (num != -1){
				l.insere (num);
			}
		}
	}
	void imprime (){
		No n = l.cabeca;
		while (n != null) {
			System.out.print (n.letra + " ");
			n = n.prox;
		}
		System.out.println();
	}
	void insere (int num){
		No n = new No(num);
		n.prox = l.cabeca;
		l.cabeca = n;
	}
	void exclui (){
		l.cabeca = l.cabeca.prox;
	}
	void esvazia(){
		while (l.cabeca != null) {
			l.cabeca = l.cabeca.prox;
		}
	}
	public static void main (String [] args){
		Pilha p = new Pilha();
		p.cria();
		p.imprime();
		p.insere(4);
		p.imprime();
		p.exclui();
		p.exclui();
		p.imprime();
		p.esvazia();
		p.imprime();
	}
}
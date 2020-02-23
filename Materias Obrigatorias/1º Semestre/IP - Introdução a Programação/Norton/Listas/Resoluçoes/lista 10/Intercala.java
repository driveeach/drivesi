import java.util.Scanner;

class Intercala {
	ListaSimples l = new ListaSimples();
	ListaSimples s = new ListaSimples ();
	void cria (int num) {
		l.insere (num);
		s.insere (num);
	}
	void intercala (){
		No n = l.cabeca;
		No p = s.cabeca;
		No aux = n.prox;
		No aux2 = p.prox;
		while (aux.prox != null || aux2.prox != null){
			n.prox = p;
			p.prox = aux;
			n = aux;
			p = aux2;
			aux = aux.prox;
			aux2 = aux2.prox;
		}
		n.prox = p;
		p.prox = aux;
		aux.prox = aux2;
	}
	public static void main (String [] args) {
		Intercala intercala = new Intercala ();
		int num = 0;
		System.out.println ("Digite o numero para colocar na lista e -1 para parar");
		while (num != -1) {
			Scanner sc = new Scanner (System.in);
			num = sc.nextInt();
			if (num != -1){	
				intercala.cria (num);
			}
		}
		No n = intercala.l.cabeca;
		while (n != null){
			System.out.print (n.letra + " ");
			n = n.prox;
		}
		System.out.println ();
		n = intercala.s.cabeca;
		while (n != null){
			System.out.print (n.letra + " ");
			n = n.prox;
		}
		
		intercala.intercala();
		
		System.out.println ();
		n = intercala.l.cabeca;
		while (n != null){
			System.out.print (n.letra + " ");
			n = n.prox;
		}
	}
}
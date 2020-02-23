import java.util.Scanner;

class Tres {
	ListaSimples l = new ListaSimples();
	ListaSimples s = new ListaSimples ();
	void cria (int num) {
		l.insere (num);
		s.insere (num);
	}
	void imprime () {
		No n = l.cabeca;
		System.out.print ("Lista Ligada 1: ");
		while (n != null){
			System.out.print (n.letra + " ");
			n = n.prox;
		}
		n = s.cabeca;
		System.out.print ("\n Lista Ligada 2: ");
		while (n != null){
			System.out.print (n.letra + " ");
			n = n.prox;
		}
		System.out.println ();
	}
	void inverteLista (){
		No p = l.cabeca.prox;
		No q = p.prox;
		No r = l.cabeca;
		l.cabeca.prox = null;
		p.prox = r;
		while (q != null) {
			if (q.prox == null) {
				l.cabeca = q;
				l.cabeca.prox = p;
				p.prox = r;
				break;
			}
			r = p;
			p = q;
			q = q.prox;
			p.prox = r;
		}
	}
	No referencia (int num){
		No n = l.cabeca;
		while (n != null) {
			if (n.letra == num) {
				return n;
			}
			n = n.prox;
		}
		return null;
	}
	void concatena (ListaSimples l, ListaSimples s){
		No n = l.cabeca;
		while (n.prox != null){
			n = n.prox;
		}
		n.prox = s.cabeca;
	}
	public static void main (String [] args) {
		Tres tres = new Tres ();
		int num = 0;
		System.out.println ("Digite o numero para colocar na lista e -1 para parar");
		while (num != -1) {
			Scanner sc = new Scanner (System.in);
			num = sc.nextInt();
			if (num != -1){	
				tres.cria (num);
			}
		}
		System.out.println ("\t\tLista Ligada Normal");
		tres.imprime();
		tres.inverteLista();
		System.out.println ("\t\tLista Ligada Invertida");
		tres.imprime();
		System.out.println ("\t\tReferencia Elemento Lista Ligada");
		System.out.println ("\n"+tres.referencia (4));
		tres.concatena (tres.l, tres.s);
		System.out.println ("\t\tLista Ligada Concatenada");
		tres.imprime();
		tres.intercala ();
		System.out.println ("\t\tLista Ligada Intercalada");
		tres.imprime();
	}
}
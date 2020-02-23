import java.util.Scanner;

class Ordena {
	ListaSimples l = new ListaSimples ();
	void insere(int num){
		No n = l.cabeca;
		No novo = new No (num);
		while (num < n.letra){
			if (num < n.letra) {
				if (n.prox == null){
					break;
				}
				else {
					if (num <n.prox.letra){
						n = n.prox;
					}
					else {
						break;
					}
				}
			}
		}
		if (num > l.cabeca.letra){
			novo.prox = l.cabeca;
			l.cabeca = novo;
		}
		else {
			No aux = n.prox;
			n.prox = novo;
			novo.prox = aux;
		}
	}
	void imprime (){
		System.out.print ("Lista Ligada: ");
		No n = l.cabeca;
		while (n != null){
			System.out.print (n.letra + " ");
			n = n.prox;
		}
		System.out.println ();
	}
	public static void main (String [] args){
		Ordena o = new Ordena ();
		Scanner sc = new Scanner (System.in);
		int num = 0;
		System.out.println ("Insira o numero que deseja colocar na lista e -1 para parar");
		num = sc.nextInt();
		o.l.insere(num);
		o.imprime();
		while (num != -1){
			num = sc.nextInt();
			if (num != -1){
				o.insere(num);
				o.imprime();
			}
		}
	}
}
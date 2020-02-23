class Imprime {
	public static void main (String [] args){
		ListaSimples l = new ListaSimples ();
		
		l.insere (1);
		l.insere(2);
		l.insere(3);
		
		No n = l.cabeca;

		while (n!=null) {
			System.out.println(n.num);
			n=n.prox;
		}
	}
}


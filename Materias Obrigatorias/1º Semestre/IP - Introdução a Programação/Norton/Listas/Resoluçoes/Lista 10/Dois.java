class Dois {
	ListaSimples l = new ListaSimples();

	No cria (int [] num){
		for (int i = num.length - 1; i>=0; i--){
			l.insere(num[i]);
		}
		return l.cabeca;
	}
	void exclui (int num) {
		No n = l.cabeca;
		int pos = 0;
		while (n != null) {
			if (n.letra == num) {
				if (n == l.cabeca) {
					l.cabeca = l.cabeca.prox;
				}
				else {
					No q = l.cabeca;
					No p = l.cabeca.prox;
					for (int i = 0; i<pos-1;i++){
							q = p;
							p = p.prox;
					}
					if (n.prox == null){
						q.prox = null;
						p = null;
					}
					else {
						q.prox = p.prox;
					}
				}
			}
			else {
				pos++;
			}
			n = n.prox;
		} 		
	}
	void imprime (){
		No n = l.cabeca;
		while (n != null) {
			System.out.print(n.letra +" ");
			n = n.prox;
		}
	}
	public static void main (String [] args){
		Dois dois = new Dois ();
		int [] num = {4,1,2,3,4,5};
		dois.imprime();
		No n = dois.cria(num);
		System.out.println(n.letra);
		dois.exclui (4);
		dois.imprime();
	}
}
public class ListaSimples {
	No cabeca;
	
	public ListaSimples () {
		this.cabeca = null;
	}
	public void insere (int novo) {
		No aux = new No (novo);
		aux.prox = this.cabeca;
		this.cabeca = aux;
	}	
}
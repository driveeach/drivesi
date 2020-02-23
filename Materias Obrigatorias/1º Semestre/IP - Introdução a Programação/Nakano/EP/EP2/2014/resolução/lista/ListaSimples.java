package lista;

import depositos.Deposito;

/**
    Classe que define uma lista ligada simples de depósitos
*/
public class ListaSimples implements ListaDepositos {
    /**
        Início da lista
    */
    private No inicio;
    
    /**
        Constrói uma lista vazia ({@link  ListaSimples#inicio  inicio} = null)
    */
    public ListaSimples() {
        this.inicio = null;
    }

	/**
        Retorna o início da lista
    */
    public No getInicio() {
        return(this.inicio);
    }
    
    /**
        @return O número de elementos da lista
    */
    public int elementos() {
        int cont = 0;
        No p = this.inicio;
        while (p != null) {
            p = p.getProx();
            cont++;
        }
        return(cont);
    }
    
    /**
        Insere um depósito ao final da lista.
        
        @param deposito Depósito a ser inserido
    */
    public void insere(Deposito deposito) {
        No q = new No(deposito);
        if (this.inicio == null) this.inicio = q;
        else {
        	No p = this.inicio;
        	while (p.getProx() != null) p = p.getProx();
        	p.setProx(q);
        }
    }
}

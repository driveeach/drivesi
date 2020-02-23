/*
    Classe que define uma lista ligada simples
*/
public class ListaSimples {
    /*
        Início da lista
    */
    private No cabeca;
    
    /*
        Constrói uma lista vazia
    */
    public ListaSimples() {
        this.cabeca = null;
    }
    
    /*
        Retorna o início da lista
    */
    public No retCabeca() {
        return(this.cabeca);
    }
    
    /*
        insere elemento no início da lista
        
        Param:
            el - elemento a ser inserido
    */
    public void insere(Residencia el) {
        No aux = new No(el);
        aux.mudaProx(this.cabeca);
        this.cabeca = aux;
    }
    
    /*
        Insere um elemento em uma posição da lista. Retorna true se conseguiu inserir, ou false se a posição for inválida
    */
    public boolean insere(Residencia el, int pos) {
        if (pos < 0 || pos > this.elementos()) return(false);
        No q = new No(el);
        if (pos == 0) {
            q.mudaProx(this.cabeca);
            this.cabeca = q;
        }
        else {
            No p = this.cabeca;
            for (int i=0; i<pos-1; i++) p = p.retProx();
            q.mudaProx(p.retProx());
            p.mudaProx(q);
        }
        return(true);
    }
    
    /*
        Retorna o número de elementos da lista
    */
    public int elementos() {
        int cont = 0;
        No p = this.cabeca;
        while (p != null) {
            p = p.retProx();
            cont++;
        }
        return(cont);
    }
    
    /*
        Exclui o elemento da posição i da lista. Retorna true se excluiu e false se o elemento não for atingível
    */
    public boolean exclui(int i) {
        if (i < 0 || i >= this.elementos() || this.cabeca == null) return(false);
        if (i == 0) this.cabeca = this.cabeca.retProx();
        else {
            No q = this.cabeca;
            No p = this.cabeca.retProx();
            for (int j=0; j<(i-1); j++) {
                q = p;
                p = p.retProx();
            }
            q.mudaProx(p.retProx());
        }
        return(true);
    }
}

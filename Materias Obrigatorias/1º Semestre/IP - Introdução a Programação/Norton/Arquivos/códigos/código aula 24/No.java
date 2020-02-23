/*
    Classe que define o nó de uma lista ligada
*/
public class No {
    /* Residência armazenada */
    private Residencia r;
    
    /* Próximo elemento na lista */
    private No prox = null;
    
    /*
        Constrói um nó da lista
        
        Param:
            r - Residência armazenada
    */
    public No(Residencia r) {
        this.r = r;
    }
    
    /*
        Retorna o objeto residência
    */
    public Residencia retRes() {
        return(this.r);
    }
    
    /*
        Retorna o próximo elemento da lista
    */
    public No retProx() {
        return(this.prox);
    }
    
    /*
        Modifica a residência armazenada
    */
    public void mudaRes(Residencia r) {
        this.r = r;
    }
    
    /*
        Modifica o próximo elemento da lista
    */
    public void mudaProx(No prox) {
        this.prox = prox;
    }
}

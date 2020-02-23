package lista;

import depositos.Deposito;

/**
    Classe que define um nó (valor depositado) na lista ligada.
*/
public class No {
    /** Valor depositado */
    private Deposito deposito;
    
    /** Próximo elemento na lista */
    private No prox;
    
    /**
        Constrói um nó da lista
        
        @param valor Valor armazenada
    */
    public No(Deposito deposito) {
        this.deposito = deposito;
        this.prox = null;
    }
    
    /**
        @return Depósito armazenado no nó
    */
    public Deposito getDeposito() {
        return(this.deposito);
    }
    
    /**
        @return Referência para o nó contido em {@link  No#prox  prox}
    */
    public No getProx() {
        return(this.prox);
    }
    
    /**
        Modifica a referência contida em {@link  No#prox  prox}
    */
    public void setProx(No prox) {
        this.prox = prox;
    }
}

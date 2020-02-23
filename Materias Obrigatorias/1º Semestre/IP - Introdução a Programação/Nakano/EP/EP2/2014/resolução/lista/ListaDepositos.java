package lista;

import depositos.Deposito;

/**
	Define uma lista ligada de depósitos
*/
public interface ListaDepositos {
	/**
        Retorna o início da lista
    */
    public No getInicio();
    
    /**
        @return O número de elementos da lista
    */
    public int elementos();
    
    /**
        Insere um depósito ao final da lista.
        
        @param deposito Depósito a ser inserido
    */
    void insere(Deposito deposito);
}

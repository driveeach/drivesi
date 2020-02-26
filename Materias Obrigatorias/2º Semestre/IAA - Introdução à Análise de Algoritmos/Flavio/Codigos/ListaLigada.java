class No {

	private int valor;
	private No proximo;

	public No(int valor){

		this.valor = valor;
		this.proximo = null;
	}

	public void setProximo(No no){ 
	
		proximo = no; 
	}
  
	public No getProximo(){ 

		return proximo; 
	}

	public int getValor(){ 

		return valor; 
	}
}

public class ListaLigada {

	private No primeiro;

	public ListaLigada(){ 

		primeiro = null;
	}

  	public void adiciona(int valor){

    		No novo = new No(valor);

		if(primeiro == null) {

			primeiro = novo;
		}
		else {

			No ultimo = primeiro;
		
			while(ultimo.getProximo() != null) {

				ultimo = ultimo.getProximo();
			}

	    		ultimo.setProximo(novo);
		}
	}

	public int get(int indice){
		
		int i;
		No no;
		
		for(no = primeiro, i = 0; no != null; no = no.getProximo(), i++){

			if(i == indice) return no.getValor();
		}

		throw new IllegalArgumentException("Indice inválido: " + indice);
	}

	public static void main(String [] args){

		ListaLigada lista = new ListaLigada();

		for(int i = 0; i < 10; i++){

 			lista.adiciona(i * 10 + i);
		}

		// Esta forma de percorrer os elementos da lista eh boa?

		for(int i = 0; i < 10; i++){

			System.out.println(lista.get(i));
		}
	}
}

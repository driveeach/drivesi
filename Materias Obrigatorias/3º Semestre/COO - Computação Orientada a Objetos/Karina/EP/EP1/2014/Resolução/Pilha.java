import java.util.LinkedList;

class Pilha <T>{					//Classe de pilha generica
	private LinkedList<T> lista;	//cria uma variavel do tipo LinkedList generica que sera a base da pilha

	Pilha() {		//contrutor
		this.lista = new LinkedList<>();		//cria a LinkedList
		}

	public void push(T i){		//metodo para adicionar elementos
		lista.add(i);				//adiciona na pilha
	}

	public T pop(){				//metodo para retirar elementos
		return lista.removeLast();	//remove e retorna o ultimo elemento
	}

	public boolean isEmpty(){		//verifica se a pilha esta vazia
		return lista.isEmpty();			//retorna se a pilha esta vazia
	}
}
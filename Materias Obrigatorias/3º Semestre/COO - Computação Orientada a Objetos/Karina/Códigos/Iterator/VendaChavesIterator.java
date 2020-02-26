import java.util.Iterator;
public class VendaChavesIterator implements Iterator{
	MenuItem[] itens;
	int posicao = 0;
	static int nroElem = 0;
	
	public VendaChavesIterator(MenuItem[] itens){
		this.itens = itens;
	}
	public Object next(){
		MenuItem menuItem = itens[posicao];
		posicao = posicao+1;
		return menuItem;
	}
	public boolean hasNext(){
		if(posicao >= itens.length || itens[posicao] == null){
			return false;
		}else{
			return true;
		}
	}
	
	public void remove(){
		if(posicao <= 0){
			throw new IllegalStateException("Nao eh possivel remover um elemento sem usar o next() pelo menos uma vez");
		}
		if(itens[posicao-1] != null){
			for(int i = posicao-1;i<itens.length-1;i++){
				itens[i] = itens[i+1];
			}
			nroElem--;
			itens[nroElem] = null;
		}
	}
	
}
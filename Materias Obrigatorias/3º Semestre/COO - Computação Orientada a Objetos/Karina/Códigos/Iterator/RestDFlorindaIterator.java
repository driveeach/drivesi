public class RestDFlorindaIterator implements Iterator{
	ArrayList menuItens;
	int posicao;
	
	public RestDFlorindaIterator(ArrayList menuItens){
		this.menuItens = menuItens;
		posicao = 0;
	}
	
	public Object next(){
		MenuItem menuItem = menuItens.get(posicao);
		posicao++;
		return menuItem;
	}
	
	public boolean hasNext(){
		if(posicao >= menuItens.size() || menuItens.get(posicao) == null){
			return false;
		}
		else{
			return true;
		}
	}
}
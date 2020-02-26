public class VendaChavesMenu implements Menu{
	static final int MAX_ITENS = 6;
	int numeroDeItens = 0;
	MenuItem[] menuItens;
	
	public VendaChavesMenu(){
		menuItens = new MenuItem[MAX_ITENS];
		addItem("Refresco de Limao",1.50);
		addItem("Refresco de Tamarindo",2.0);
		addItem("Refresco de Groselha",1.50);
	}
	
	public void addItem(String nome, double preco){
		if(!(numeroDeItens >= MAX_ITENS)){
			MenuItem novo = new MenuItem(nome,preco);
			menuItens[numeroDeItens] = novo;
			numeroDeItens++;
			VendaChavesIterator.nroElem++;
		}else{
			System.err.println("O menu esta cheio. Nao eh possivel adicionar mais entradas");
		}
		
	}
	
	public Iterator createIterator(){
		return new VendaChavesIterator(menuItens);
	}
}
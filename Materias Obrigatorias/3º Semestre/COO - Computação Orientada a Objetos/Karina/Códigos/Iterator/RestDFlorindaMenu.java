import java.util.List;
import java.util.*;
public class RestDFlorindaMenu implements Menu{
	ArrayList menuItens;
	
	public RestDFlorindaMenu(){
		menuItens = new ArrayList();
		addItem("Panqueca",5.00);
		addItem("Macarrao",10.00);
		addItem("Croquete com Maionese",3.50);
		addItem("Batata Frita",12.00);
	}
	
	public void addItem(String nome, double preco){
		MenuItem menuItem = new MenuItem(nome,preco);
		menuItens.add(menuItem);
	}
	
	public Iterator createIterator(){
		return menuItens.iterator();
	}
}
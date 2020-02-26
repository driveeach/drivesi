import java.util.Iterator;
public class Garconete{
	Menu restDFlorindaMenu;
	Menu vendaChavesMenu;
	
	public Garconete(Menu restDFlorindaMenu, Menu vendaChavesMenu){
		this.restDFlorindaMenu = restDFlorindaMenu;
		this.vendaChavesMenu = vendaChavesMenu;
	}
	public void printMenu(){
		Iterator restIterator = restDFlorindaMenu.createIterator();
		Iterator vendIterator = vendaChavesMenu.createIterator();
		
		System.out.println("MENU\n------\nRefeicoes");
		printMenu(restIterator);
		System.out.println("Refrescos");
		printMenu(vendIterator);
	}
	public void printMenu(Iterator iterator){
		while(iterator.hasNext()){
			MenuItem menuItem = (MenuItem)iterator.next();
			System.out.print(menuItem.getNome()+" - ");
			System.out.println(menuItem.getPreco());
		}
	}
}
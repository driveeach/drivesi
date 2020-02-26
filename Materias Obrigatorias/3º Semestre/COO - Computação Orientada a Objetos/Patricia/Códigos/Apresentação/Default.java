public class Default{
	public static void main(String[] args){
		VendaChavesMenu refrescosMenu = new VendaChavesMenu();
		RestDFlorindaMenu refeicoesMenu = new RestDFlorindaMenu();
		
		Garconete franja = new Garconete(refeicoesMenu,refrescosMenu);
		franja.printMenu();
	}
}
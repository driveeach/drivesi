public class Pizzaiolo{
	public static void main (String [] args){
		Pizza pz1 = new Mussarela();
		System.out.println("Pizza --- Valor \n");
		// Pizza de Mussarela normal
		System.out.println(pz1.getDescription() + " --- " + pz1.cost());
		// Acrescenta tomate na pizza de mussarela
		pz1 = new TomatoDecorator (pz1);
		
		System.out.println(pz1.getDescription() + " --- " + pz1.cost());
		// Acrescenta oregano na pizza de mussarela
		pz1 = new OreganoDecorator (pz1);
		
		System.out.println(pz1.getDescription() + " --- " + pz1.cost());
		// Acrescenta bacon na pizza de mussarela
		pz1 = new BaconDecorator (pz1);
		
		System.out.println(pz1.getDescription() + " --- " + pz1.cost());
		// Acrescenta borda de catupiry na pizza de mussarela
		pz1 = new CreamCheeseEdgeDecorator (pz1);
		
		System.out.println(pz1.getDescription() + " --- " + pz1.cost());
	}
}
public class Oregano extends CondimentDecorator {

	public OreganoDecorator(Pizza pz){
		super(pz);
	}
	
	@Override
	public Strinc getDescription(){
		return pizza.getDescription() + ", Oregano";
	}
	
	@Override
	public double cost(){
		return .50 + super.cost();
	}
}
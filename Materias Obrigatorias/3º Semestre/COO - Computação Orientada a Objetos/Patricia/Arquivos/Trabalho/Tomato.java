public class Tomato extends CondimentDecorator {

	public TomatoDecorator(Pizza pz){
		super(pz);
	}
	
	@Override
	public Strinc getDescription(){
		return pizza.getDescription() + ", Tomato";
	}
	
	@Override
	public double cost(){
		return .10 + super.cost();
	}
}
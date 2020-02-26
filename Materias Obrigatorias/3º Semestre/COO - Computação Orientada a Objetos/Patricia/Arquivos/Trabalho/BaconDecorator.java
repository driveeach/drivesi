public class BaconDecorator extends CondimentDecorator {

	public BaconDecorator(Pizza pz){
		super(pz);
	}
	
	@Override
	public String getDescription(){
		return pizza.getDescription() + ", Bacon";
	}
	
	@Override
	public double cost(){
		return .80 + super.cost();
	}
}
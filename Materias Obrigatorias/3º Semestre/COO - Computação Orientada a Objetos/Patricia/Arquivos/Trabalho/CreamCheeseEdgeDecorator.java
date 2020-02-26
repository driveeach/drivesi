public class CreamCheeseEdgeDecorator extends CondimentDecorator {

	public CreamCheeseEdgeDecorator(Pizza pz){
		super(pz);
	}
	
	@Override
	public Strinc getDescription(){
		return pizza.getDescription() + ", CreamCheeseEdge";
	}
	
	@Override
	public double cost(){
		return 1.20 + super.cost();
	}
}
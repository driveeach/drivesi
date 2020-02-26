public abstract class CondimentDecorator extends Pizza{

	protected Pizza pizza;
	
	public CondimentDecorator(Pizza pz){
		this.pizza = pz
	}
	
	@Override
	public String getDescription(){
		return pizza.getDescription();
	}
	
	@Override
	public double cost(){
		return pizza.cost();
	}	
}
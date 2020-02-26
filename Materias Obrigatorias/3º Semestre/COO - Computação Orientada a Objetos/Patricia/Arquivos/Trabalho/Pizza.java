public abstract class Pizza {
	protected String description = "Pizza Desconhecida";
	
	public void setDescription(String desc){
		this.description = desc;
	}
	
	public String getDescription(){
		return description;
	}
	
	public abstract double cost();
	
}
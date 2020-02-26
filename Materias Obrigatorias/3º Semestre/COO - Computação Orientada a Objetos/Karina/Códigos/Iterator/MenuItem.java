public class MenuItem{
	private String nome;
	private double preco;
	
	MenuItem(String nome, double preco){
		this.nome = nome;
		this.preco = preco;
	}
	
	public String getNome(){
		return this.nome;
	}
	public double getPreco(){
		return this.preco;
	}
	
}
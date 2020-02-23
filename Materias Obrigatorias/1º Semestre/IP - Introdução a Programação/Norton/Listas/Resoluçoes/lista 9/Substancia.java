class Substancia {
	private String nome;
	private int tipo; // 1=simples 2=nobre
	private double preco;
	static int nobjetos = 0;
	final double cotacao = 2;
	
	Substancia () {
		nobjetos++;
	}

	public void setNome(String nome) { 
		this.nome = nome; 
	}
	public String getNome () { 
		return nome; 
	}
	public void setTipo(char tipo) { 
		this.tipo = tipo; 
	}
	public int getTipo () { 
		return tipo;
	}
	public void setPreco(int preco) { 
		this.preco = preco; 
	}
 	public double getPreco () { 
		return preco;
	}
	public void mostraAtributos (){
		System.out.println (nome);
		System.out.println (tipo);
		System.out.println (preco);
		System.out.println (nobjetos);
		System.out.println (cotacao);
	}
}
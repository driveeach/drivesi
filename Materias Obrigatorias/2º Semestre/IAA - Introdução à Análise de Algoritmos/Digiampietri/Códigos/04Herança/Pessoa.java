package heranca;

class Pessoa {
	String nome;
	int RG;
	Pessoa(String nome, int RG){
		this.nome = nome;
		this.RG = RG;
	}
	
	void respirar(){
		System.out.println("Pessoa respirando.");
	}

	void dormir(){
		System.out.println("Pessoa dormindo.");
	}
	
}

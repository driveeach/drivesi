package depositos;

/**
	Classe que define uma Data
*/
public class Data {
	/** Mês da data */
	private int mes;
	/** Ano da data */
	private int ano;
	
	/*
		Constrói uma data
		
		@param mes O mes
		@param ano O ano
	*/
	public Data (int mes, int ano) {
		this.mes = mes;
		this.ano = ano;
	}
	
	/**
		@return O mês da data
	*/
	public int getMes() {
		return(this.mes);
	}

	/**
		@return O ano da data
	*/
	public int getAno() {
		return(this.ano);
	}
	
	/**
		Compara duas datas
		
		@param d2 Data que se deseja comparar a esta
		
		@return 0 se forem iguais, -1 se o parâmetro for maior que esta, e +1 se esta for maior
	*/
	public int maior(Data d2) {
		if (d2 == null) return(1);
		
		if (d2.getAno() > this.ano) return(-1);
		if (d2.getAno() < this.ano) return(1);
		if (d2.getMes() > this.mes) return(-1);
		if (d2.getMes() < this.mes) return(1);
		return(0);
	}
	
	/**
		Calcula o número de meses entre duas datas
		
		@param d2 Data da qual esta será subtraida (faz d2 - esta)
	
		@return Número de meses entre duas datas, ou -1 em caso de erro.
	*/
	public int mesesEntre(Data d2) {
		if (d2 == null) return(-1);
		
		Data maior = (this.maior(d2) == -1) ? d2 : this;
		Data menor = (maior == this) ? d2 : this;
		
		int total = 12*(maior.getAno()-menor.getAno());
		
		total += maior.getMes() - menor.getMes();
		
		return(total);
	}
}

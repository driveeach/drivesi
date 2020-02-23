package swsl;

import java.util.Calendar;

class Profissional {
	private long numeroControle;
	private String nome;
	private String endereco;
	private String profissao;
	private Calendar dataNascimento;
	
	//javabean não usa construtor

	public long getNumeroControle() {
		return numeroControle;
	}

	public void setNumeroControle(long numeroControle) {
		this.numeroControle = numeroControle;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNasc) {
		this.dataNascimento = dataNasc;
	}
}
public class PontoGeografico {

	String NomePonto;
	int IdPonto;
	double graus;
	double minutos;
	double segundos;

	public int getIdPonto() {
		return IdPonto;
	}

	public void setIdPonto(int idPonto) {
		IdPonto = idPonto;
	}

	public String getNomePonto() {
		return NomePonto;
	}

	public void setNomePonto(String nomePonto) {
		NomePonto = nomePonto;
	}

	public double getGraus() {
		return graus;
	}

	public void setGraus(double graus) {
		this.graus = graus;
	}

	public double getMinutos() {
		return minutos;
	}

	public void setMinutos(double minutos) {
		this.minutos = minutos;
	}

	public double getSegundos() {
		return segundos;
	}

	public void setSegundos(double segundos) {
		this.segundos = segundos;
	}
}

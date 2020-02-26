public class ProfessorDoutor extends Professor {
  
  private int anoTitulo;
  
  public ProfessorDoutor(String nome, String curso, int anoTitulo) {
    super(nome, curso);
    this.anoTitulo = anoTitulo;
  }
  
  public int getAnoTitulo() {
    return this.anoTitulo;
  }
  
  public String toString() {
    return this.getNome() + " -- " + this.getCurso() + " -- " + this.getAnoTitulo();
  }
}
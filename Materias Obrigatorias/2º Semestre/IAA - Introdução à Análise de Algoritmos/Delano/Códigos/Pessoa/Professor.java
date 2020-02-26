public class Professor extends Pessoa {
  
  private String curso;
  
  public Professor(String nome, String curso) {
    super(nome);
    this.curso = curso;
  }
  
  public String getCurso() {
    return this.curso;
  }
  
  public String toString() {
    return this.getNome() + " -- " + this.getCurso();
  }
}
public class Aluno extends Pessoa {
  
  private int nroUSP;
  
  public Aluno(String nome, int nroUSP) {
    super(nome);
    this.nroUSP = nroUSP;
  }
  
  public int getNroUSP() {
    return this.nroUSP;
  }
  
  public String toString() {
    return this.getNome() + " -- " + this.getNroUSP();
  }
}
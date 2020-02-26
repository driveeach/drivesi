public class Aluno extends Pessoa {
  private String curso;
  private int anoDeIngresso;
  Aluno(String nome, char sexo, String RG, String CPF, int anoDeNascimento, 
        String curso, int anoDeIngresso) {
    super(nome, sexo, RG, CPF, anoDeNascimento);
    this.curso = curso;
    this.anoDeIngresso = anoDeIngresso;
  }  
  
  void imprimeDados() {
    super.imprimeDados();
    System.out.println("Ingressou no curso "+ curso + " em " + anoDeIngresso);
  }
}
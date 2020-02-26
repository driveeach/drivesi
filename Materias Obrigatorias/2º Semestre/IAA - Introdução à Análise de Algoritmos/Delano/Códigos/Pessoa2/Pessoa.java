public class Pessoa {
  private String nome;
  private char sexo;
  private String RG;
  private String CPF;
  private int anoDeNascimento;
  
  Pessoa(String nome, char sexo, String RG, String CPF, int anoDeNascimento) {
    this.nome = nome;
    this.sexo = sexo;
    this.RG = RG;
    this.CPF = CPF;
    this.anoDeNascimento = anoDeNascimento;
  }
  
  void imprimeDados() {
    if(sexo == 'F') {
      System.out.println("A Sra. " + nome + " nasceu no ano " + anoDeNascimento + 
                         ". CPF: " + CPF + ", RG "+RG);
    }
    else {
      System.out.println("O Sr. " + nome + " nasceu no ano " + anoDeNascimento + 
                         ". CPF: " + CPF + ", RG "+RG);
    }
  }
}

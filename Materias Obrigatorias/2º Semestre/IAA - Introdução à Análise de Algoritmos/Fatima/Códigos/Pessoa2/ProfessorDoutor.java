public class ProfessorDoutor extends Professor {
  
  private String instituicaoDoDoutorado;
  private int anoDeObtencao;
  
  ProfessorDoutor(String nome, char sexo, String RG, String CPF, int anoDeNascimento, 
        String departamento, int anoDeAdmissao, String instituicaoDoDoutorado, int anoDeObtencao) {
    super(nome, sexo, RG, CPF, anoDeNascimento, departamento, anoDeAdmissao);
    this.instituicaoDoDoutorado = instituicaoDoDoutorado;
    this.anoDeObtencao = anoDeObtencao;
  }
  
  void imprimeDados() {
    super.imprimeDados();
    System.out.println("Doutorado obtido em  "+ instituicaoDoDoutorado + " em " + anoDeObtencao);
  }
}
public class Professor extends Pessoa {
  private String departamento;
  private int anoDeAdmissao;

  Professor(String nome, char sexo, String RG, String CPF, int anoDeNascimento, 
            String departamento, int anoDeAdmissao) {
    super(nome, sexo, RG, CPF, anoDeNascimento);
    this.departamento = departamento;
    this.anoDeAdmissao = anoDeAdmissao;
  } 
  
  void imprimeDados() {
    super.imprimeDados();
    System.out.println("Ingressou no depto. "+ departamento + " em " + anoDeAdmissao);
  }
}
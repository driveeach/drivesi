public class Pessoa {
  
  private String nome;
  
  public Pessoa(String nome) {
    this.nome = nome;
  }
  
  public String getNome() {
    return this.nome;
  }

  public static void main(String[] args) {
    Pessoa[] pessoa = new Pessoa[3];
    
    pessoa[0] = new Aluno("Maria Jose", 12345);
    
    pessoa[1] = new Professor("Jose Maria", "GA");
    
    pessoa[2] = new ProfessorDoutor("Fernando Henrique", "GPP", 1982);
    
    for (int i = 0; i < pessoa.length; i++) {
      System.out.println(pessoa[i]);
    }
  }
}

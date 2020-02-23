import java.util.Scanner;

class Turma04 {
  
  int nroSubsequencias(int[] sequencia, int k) {
    int contador = 0;
    for (int i = k - 1; i < sequencia.length; i++) {
      int j = 0;
      while (sequencia[i - j] >= sequencia[i - j - 1] && j < k - 1) {
        j++;
      }
      if (j == k - 1) {
        contador = contador + 1;
        System.out.print("[");
        for (int aux = k-1; aux >= 0; aux--) {
          System.out.print(" " + sequencia[i - aux] + " ");
        }
        System.out.println("]");
      }
    }
    return contador;
  }
  
  boolean ehQuadradoMagico(int[][] a) {
    boolean quadrado = true;
    
    int[][] soma = new int[2][a.length];
    
    for (int i = 0; i < a.length; i++) {
      for (int j = 0; j < a.length; j++) {
        soma[0][i] = soma[0][i] + a[i][j];
        soma[1][j] = soma[1][j] + a[i][j];
      }
    }
    
    for (int i = 0; quadrado && i < a.length; i++) {
      for (int j = 0; quadrado && j < a.length; j++) {
        if (soma[i][j] != soma[0][0]) {
          quadrado = false;
        }
      }
    }
    
    return quadrado;
  }
  
  double amplitude(int n) {
    Scanner sc = new Scanner(System.in);
    
    
    double valor = sc.nextDouble();
    double maior = valor;
    double menor = valor;
    
    for (int i = 2; i <= n; i++) {
      valor = sc.nextDouble();
      if (valor > maior) {
        maior = valor;
      }
      else {
        if (valor < menor) {
          menor = valor;
        }
      }
    }
    return maior - menor;
  }
}

class Aluno {
  private int nroUSP;
  private String nome;
  private double notaFinal;
  
  public Aluno(int nroUSP, String nome, double notaFinal) {
    this.nroUSP    = nroUSP;
    this.nome      = nome;
    this.notaFinal = notaFinal;
  }
  
  public int getNroUSP() {
    return this.nroUSP;
  }
  
  public String getNome() {
    return this.nome;
  }
  
  public double getNotaFinal() {
    return this.notaFinal;
  }
}

class Cadastro {
  private Aluno[] alunos;
  private Aluno[] aprovados;
  private Aluno[] recuperacao;
  private Aluno[] reprovados;
  
  public Cadastro() {
    alunos = new Aluno[6];
    alunos[0] = new Aluno(1, "Ana", 10);
    alunos[1] = new Aluno(2, "Beto", 2);
    alunos[2] = new Aluno(3, "Joao", 4.9);
    alunos[3] = new Aluno(4, "Maria", 7);
    alunos[4] = new Aluno(5, "Ronaldo", 2.5);
    alunos[5] = new Aluno(6, "Sandro", 3);  
  }
  
  public void imprimeListas() {
    this.fazLista();
    System.out.println("Lista de Alunos (Total)");
    this.imprime(alunos);
    System.out.println("Lista de Alunos (Aprovados)");
    this.imprime(aprovados);
    System.out.println("Lista de Alunos (Recuperacao)");
    this.imprime(recuperacao);
    System.out.println("Lista de Alunos (Reprovados)");
    this.imprime(reprovados);
  }
  
  private void fazLista() {
    int nAP = 0;
    int nREC = 0;
    int nRP = 0;
    
    nAP = nREC = nRP = 0;
    
    for (int i = 0; i < alunos.length; i++) {
      if (alunos[i].getNotaFinal() >= 5) {
        nAP++;
      } else if (alunos[i].getNotaFinal() >= 3) {
        nREC++;
      } else {
        nRP++;
      }
    }
    
    aprovados = new Aluno[nAP];
    recuperacao = new Aluno[nREC];
    reprovados = new Aluno[nRP];
    
    nAP = nREC = nRP = 0;
    
    for (int i = 0; i < alunos.length; i++) {
      if (alunos[i].getNotaFinal() >= 5) {
        aprovados[nAP++] = alunos[i];
      } else if (alunos[i].getNotaFinal() >= 3) {
        recuperacao[nREC++] = alunos[i];
      } else {
        reprovados[nRP++] = alunos[i];
      }
    }
  }
    
  public void imprime(Aluno[] alunos) {
    for (int i = 0; i < alunos.length; i++) {
      System.out.println(alunos[i].getNroUSP() + "\t" + alunos[i].getNome() + "\t" + alunos[i].getNotaFinal());
    }
  }
  
  public Aluno[] aprovados() {
    return aprovados;
  }
  
  public Aluno[] emRecuperacao() {
    return recuperacao;
  }
  
  public Aluno[] reprovados() {
    return reprovados;
  }
}
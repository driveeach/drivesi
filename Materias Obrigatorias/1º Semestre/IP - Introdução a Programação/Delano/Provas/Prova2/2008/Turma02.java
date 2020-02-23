import java.util.Scanner;

class Turma02 {
  
  /*
   * QUESTAO 1
   */
  double media(int n) {
    Scanner sc = new Scanner(System.in);
    double soma = 0;
    double valor;
    
    for (int i = 1; i <= n; i++) {
      valor = sc.nextDouble();
      soma += valor;
    }
    return soma/n;
  }
  
  /*
   * QUESTAO 3
   */
  boolean estaNoArray(int[] a, int valor) {
    boolean found = false;
    
    int i = 0;
    while(!found && i < a.length) {
      if (a[i] == valor) {
        found = true;
      }
      i++;
    }
    return found;
  }
  
  boolean temTodos(int[] a, int n) {
    boolean temTodos = true;
    for (int i = 1; temTodos && i <= n; i++) {
      temTodos = temTodos & estaNoArray(a, i);
    }
    return temTodos;
  }
  
  boolean eLatino(int[][] a) {
    boolean latino = true;
    for (int i = 0; latino && i < a.length; i++) {
      latino = latino & temTodos(a[i], a.length);
    }
    
    int[] coluna = new int[a.length];
    for (int j = 0; latino && j < a.length; j++) {
      for (int i = 0; i < a.length; i++) {
        coluna[i] = a[i][j];
      }
      latino = latino & temTodos(coluna, a.length);
    }
    return latino;
  }
  
  int nroSubsequencias(int[] sequencia, int k) {
    int contador = 0;
    for (int i = k - 1; i < sequencia.length; i++) {
      int j = 0;
      while (sequencia[i - j] == sequencia[i - j - 1] && j < k - 1) {
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

}

/**
 * QUESTAO 2
 */
class Cliente {
  private String nome;
  private String cidade;
  private String estado;
  
  public Cliente(String nome, String cidade, String estado) {
    this.nome    = nome;
    this.cidade  = cidade;
    this.estado  = estado;
  }
  
  public String getNome() {
    return this.nome;
  }
  
  public String getCidade() {
    return this.cidade;
  }
  
  public String getEstado() {
    return this.estado;
  }
}

class Cadastro {
  private Cliente[] clientes;
  private Cliente[] capitalSP;
  private Cliente[] estadoSP;
  private Cliente[] outrosEstados;
  
  public Cadastro() {
    clientes = new Cliente[6];
    clientes[0] = new Cliente("Ana", "São Paulo","SP");
    clientes[1] = new Cliente("Beto", "Campinas", "SP");
    clientes[2] = new Cliente("Joao", "Juiz de Fora", "MG");
    clientes[3] = new Cliente("Maria", "São Paulo", "SP");
    clientes[4] = new Cliente("Ronaldo", "São Carlos", "SP");
    clientes[5] = new Cliente("Sandro", "Brasilia", "DF");  
  }
  
  public void imprimeListas() {
    this.fazLista();
    System.out.println("Lista de Clientes (Total)");
    this.imprime(clientes);
    System.out.println("Lista de Clientes (SP/Capital)");
    this.imprime(capitalSP);
    System.out.println("Lista de Clientes (SP/Interior)");
    this.imprime(estadoSP);
    System.out.println("Lista de Clientes (Outros estados)");
    this.imprime(outrosEstados);
  }
  
  private void fazLista() {
    int nSPC = 0;
    int nSPI = 0;
    int nOUT = 0;
    
    nSPC = nSPI = nOUT = 0;
    
    for (int i = 0; i < clientes.length; i++) {
      if (clientes[i].getCidade().equals("São Paulo")) {
        nSPC++;
      } else if (clientes[i].getEstado().equals("SP")) {
        nSPI++;
      } else {
        nOUT++;
      }
    }
    
    capitalSP = new Cliente[nSPC];
    estadoSP = new Cliente[nSPI];
    outrosEstados = new Cliente[nOUT];
    
    nSPC = nSPI = nOUT = 0;
    
    for (int i = 0; i < clientes.length; i++) {
      if (clientes[i].getCidade().equals("São Paulo")) {
        capitalSP[nSPC++] = clientes[i];
      } else if (clientes[i].getEstado().equals("SP")) {
        estadoSP[nSPI++] = clientes[i];
      } else {
        outrosEstados[nOUT++] = clientes[i];
      }
    }
  }
    
  public void imprime(Cliente[] cl) {
    for (int i = 0; i < cl.length; i++) {
      System.out.println(cl[i].getNome() + "\t" + cl[i].getCidade() + "\t" + cl[i].getEstado());
    }
  }

  public Cliente[] capitalSP() {
    return capitalSP;
  }
  
  public Cliente[] estadoSP() {
    return estadoSP;
  }
  
  public Cliente[] outrosEstados() {
    return outrosEstados;
  }
}
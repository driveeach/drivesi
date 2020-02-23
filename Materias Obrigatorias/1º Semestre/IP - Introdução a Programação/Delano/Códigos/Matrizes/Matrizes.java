import java.util.Scanner;

class Matrizes {
  
  int[][] leMatriz() {
    int[][] matriz;
    Scanner sc = new Scanner (System.in);
    
    System.out.println("Entre  com o valor de M");
    int m = sc.nextInt();
    
    System.out.println("Entre  com o valor de N");
    int n = sc.nextInt();
    
    matriz = new int[m][n];
    
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        System.out.print("matriz["+ i +"][" + j + "] ?");
        matriz[i][j] = sc.nextInt();
      }
    }
    return matriz;
  }
  
  void imprimeMatriz(int[][] a) {  
    for (int i = 0; i < a.length; i++) {
      System.out.print("| ");
      for (int j = 0; j < a[i].length; j++) {
        System.out.print(a[i][j] + " ");
      } 
      System.out.println("|");
    }
  }
  
  boolean igual(int[][] a, int[][] b) {
    boolean ok = mesmaEstrutura(a,b);
    
    for (int i = 0; ok && i < a.length; i++) {
      for (int j = 0; ok && j < a[i].length; j++) {
        ok = (a[i][j] == b[i][j]);
      }
    }
    
    return ok;
  }
  
  boolean mesmaEstrutura(int[][] a, int[][] b) {
    boolean ok = (a.length == b.length);
    
    for (int i = 0; ok && i < a.length; i++) {
      ok = (a[i].length == b[i].length);
    }
    
    return ok;
  }
  
  int[][] soma(int[][] a, int[][] b) {
    int[][] c = new int[a.length][];
    for (int i = 0; i < a.length; i++) {
      c[i] = new int[a[i].length];
      for (int j = 0; j < a[i].length; j++) {
        c[i][j] = a[i][j] + b[i][j]; 
      }
    }
    
    return c;
  }
  
  int[][] transposta(int[][] a) {
    int[][] b = new int[a[0].length][a.length];
    
    for (int i = 0; i < a.length; i++) {
      for (int j = 0; j < a[i].length; j++) {
        b[j][i] = a[i][j]; 
      }
    }
    return b;
  }
  
  int[][] produto(int[][] a, int[][] b) {
    int m = a.length;
    int n = b[0].length;
    int l = b.length;
    
    int[][] c = new int[m][n]; 
    
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        c[i][j] = 0;
        for (int k = 0; k < l; k++) {
          c[i][j] += a[i][k] * b[k][j];
        }
      }
    }
    return c;
  }
  
  boolean inversa(int[][] a, int[][] b) {
    return ehIdentidade(produto(a,b));
  }
  
  boolean inversa2(int[][] a, int[][] b) {
    int[][] c = produto(a,b);
    int[][] d = matrizIdentidade(a.length);
    return igual(c,d);
  }
    
  int[][] matrizIdentidade(int n) {
    int[][] c = new int[n][n];
    
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (i == j) {
          c[i][j] = 1;
        }
        else {
          c[i][j] = 0;
        }
      }
    }
    return c;
  }
  
  boolean ehIdentidade(int[][] a) {
    boolean identidade = true;
    
    // verifica se eh matriz quadrada 
    for (int i = 0; identidade && i < a.length; i++) {
      identidade = (a.length == a[i].length);
    }
    
    // verifica se a diagonal eh sempre "1"
    for (int i = 0; identidade && i < a.length; i++) {
      identidade = (a[i][i] == 1);
    }
    
    // verifica se os demais termos sao iguais a "0"
    for (int i = 0; identidade && i < a.length; i++) {
      for (int j = 0; identidade && j < a[i].length; j++) {
        identidade = (i == j || a[i][j] == 0);
      }
    }
    
    return identidade;
  }
}
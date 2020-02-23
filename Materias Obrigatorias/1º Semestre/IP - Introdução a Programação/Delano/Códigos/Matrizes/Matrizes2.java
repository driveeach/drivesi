class Matrizes2 {
  
  void imprimeMatriz(double[][] a) {  
    for (int i = 0; i < a.length; i++) {
      System.out.print("| ");
      for (int j = 0; j < a[i].length; j++) {
        System.out.print(a[i][j] + " ");
      } 
      System.out.println("|");
    }
  }
  
  double[][] matrizCoFator(double[][] a) {
    double[][] b = new double[a.length][a.length];
    
    for (int i = 0; i < a.length; i++) {
      for (int j = 0; j < a.length; j++) {
        b[i][j] = determinante(removeLinhaEColuna(a, i, j));
        if ((i + j) % 2 == 1) {
          b[i][j] = -b[i][j];
        }
      }
    }
    
    return b;
  }
  
  double[][] removeLinhaEColuna(double[][] a, int lin, int col) {
    int n = a.length;
    double[][] c = new double[n-1][n-1];
    int i = 0, j;
    for (int x = 0; x < n; x++) {
      if (x != lin) {
        j = 0;
        for (int y = 0; y < n; y++) {
          if (y != col) {
            c[i][j] = a[x][y];
            j++;
          }
        }
        i++;
      }
    }
    return c;
  }
  
  double determinante(double[][] a) {
    double somaDiagonal1 = 0;
    double somaDiagonal2 = 0;
    int n = a.length;
    
    if (n == 1) {
      return a[0][0];
    } else if (n == 2) {
      return a[0][0] * a[1][1] - a[0][1] * a[1][0];
    }
    
    for (int i = 0; i < n; i++) {
      double produto = 1;
      for (int j = 0; j < n; j++) {
        produto = produto * a[j][(i+j) % n];
      }
      somaDiagonal1 += produto;
    }
    
    for (int i = n - 1; i >= 0; i--) {
      double produto = 1;
      for (int j = 0; j < n; j++) {
        produto = produto * a[j][(i- j + n) % n];
      }
      somaDiagonal2 += produto;
    }
    
    return somaDiagonal1 - somaDiagonal2;
  }
  
  double[][] transposta(double[][] a) {
    double[][] b = new double[a[0].length][a.length];
    
    for (int i = 0; i < a.length; i++) {
      for (int j = 0; j < a[i].length; j++) {
        b[j][i] = a[i][j]; 
      }
    }
    return b;
  }
  
  void multiplica(double[][] a, double x) {
    for (int i = 0; i < a.length; i++) {
      for (int j = 0; j < a[i].length; j++) {
        a[j][i] *= x;
      }
    }
  }
  
  double[][] inversa(double[][] a) {
    double determinante = determinante(a);
    double[][] coFatorT = transposta(matrizCoFator(a));
    multiplica(coFatorT, 1/determinante);
    return coFatorT;
  }
}
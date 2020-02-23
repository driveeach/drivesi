class Matematica {
  
  double epsilon = 1.0e-32;
  
  double pot(double x, int y) {
    double resultado = 1;
    
    if(y < 0) {
      x = 1 / x;
      y = -y;
    }

    while (y > 0) {
      resultado = resultado * x;
      y = y - 1;
    }
    return resultado;
  }
  
  long fat (int n) {
    long mult = 1;
    
    while (n > 0) {
      mult = mult * n;
      n = n - 1;
    }
    
    return mult;
  }
  
  /**
   * sen() -- implementação não otimizada da função seno. Descubra por quê.
   */
  
  double sen (double x) {
    double seno, termo;
    seno = 0;
    int k=0;
    
    // Calcula o primeiro termo
    
    termo = (pot(-1,k)*pot(x,2*k+1))/fat(2*k+1);
    seno = seno + termo;
    
    while (termo * termo > epsilon * epsilon) { // testa se terminou (eleva ao quadrado porque termo pode ser negativo
      k = k + 1;
      termo = (pot(-1,k) * pot(x,2 * k + 1)) / fat(2 * k + 1);
      System.out.println("termo = " + termo);
      seno = seno + termo;
    }
    
    return seno;
  }
  
  /**
   * sen() -- implementação mais otimizada da função seno.
   */
  double sen2(double x) {
    double termo = x;
    double seno = termo;
    int k = 1;
    
    while (termo * termo > epsilon * epsilon) {
      k = k + 2;
      termo = -termo * x * x/((k-1) * k);
      seno = seno + termo; 
    }
    
    return seno;
  } 
}
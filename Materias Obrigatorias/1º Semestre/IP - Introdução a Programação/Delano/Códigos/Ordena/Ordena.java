class Ordena {
  
  /*
   * Melhor caso - 2 comparações
   * Pior caso - 10 comparaçoes
   * 
   * abc - 2 comparações * 1/6 
   * acb - 4 comparações * 1/6
   * bac - 6 comparações * 1/6
   * bca - 8 comparações * 1/6
   * cab - 10 comparações * 1/6
   * cba - 10 comparações * 1/6
   * 
   * Caso médio = 40/6
   */
  void ordena(int a, int b, int c) {
    if (a <= b && b <= c) {
      System.out.println(a + ", " + b + ", " + c);
    } else if (a <= c && c <= b) {
      System.out.println(a + ", " + c + ", " + b);
    } else if (b <= a && a <= c) {
      System.out.println(b + ", " + a + ", " + c);
    } else if (b <= c && c <= a) {
      System.out.println(b + ", " + c + ", " + a);
    } else if (c <= a && a <= b) {
      System.out.println(c + ", " + a + ", " + b);
    } else {
      System.out.println(c + ", " + b + ", " + a);
    }
  }
  
  /*
   * Melhor caso - 3 comparações
   * Pior caso - 4 comparações
   * 
   * abc - 3 comparações * 1/6 
   * acb - 3 comparações * 1/6
   * bac - 4 comparações * 1/6
   * bca - 4 comparações * 1/6
   * cab - 4 comparações * 1/6
   * cba - 4 comparações * 1/6
   * 
   * Caso médio = 22/6
   */
  void ordena2(int a, int b, int c) {
    if (a <= b && a <= c) {
      // "a" e o menor
      if (b <= c) {
        System.out.println(a + ", " + b + ", " + c);
      }
      else {
        System.out.println(a + ", " + c + ", " + b);
      }
    } else if (b <= c) {
      // "b" eh o menor
      if (a <= c) {
        System.out.println(b + ", " + a + ", " + c);
      }
      else {
        System.out.println(b + ", " + c + ", " + a);
      }
    } else {
      // "c" eh o menor
      if (a <= b) {
        System.out.println(c + ", " + a + ", " + b);
      }
      else {
        System.out.println(c + ", " + b + ", " + a);
      }
    }
  }
  
  /*
   * Melhor caso - 3 comparações
   * Pior caso - 3 comparações
   * 
   * abc - 3 comparações * 1/6 
   * acb - 3 comparações * 1/6
   * bac - 3 comparações * 1/6
   * bca - 3 comparações * 1/6
   * cab - 3 comparações * 1/6
   * cba - 3 comparações * 1/6
   * 
   * Caso médio = 3 comparações
   */
  void ordena3(int a, int b, int c) {
    int aux;
    if (a > b) {
      // Troca "a" e "b"
      aux = a;
      a = b;
      b = aux;
    }
    
    if (a > c) {
      // Troca "a" e "c"
      aux = a;
      a = c;
      c = aux;
    }
    
    if (b > c) {
      // Troca "b" e "c"
      aux = b;
      b = c;
      c = aux;
    }
    System.out.println(a + ", " + b + ", " + c);
  }
}
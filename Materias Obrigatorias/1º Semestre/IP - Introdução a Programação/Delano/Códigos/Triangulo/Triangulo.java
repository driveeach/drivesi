class Triangulo {
  
  int ladoA, ladoB, ladoC;

  /**
   * Observação: 3 lados (a,b,c) formam um triangulo, se a soma de dois lados eh maior que o terceiro lado.
   */
  void carregaLados(int a, int b, int c) {
    if (a < b + c && b < a + c && c < a + b) {
      ladoA = a;
      ladoB = b;
      ladoC = c;
    }
  }

  /**
   * Escreva um metodo que classifica triangulo (equilatero, isosceles e escaleno). 
   */
  void classificaTriangulo() { 
    if (ladoA == ladoB && ladoB == ladoC) {
      System.out.println("Triangulo Equilatero");
    } else if (ladoA == ladoB || ladoB == ladoC || ladoA == ladoC) {
      System.out.println("Triangulo Isosceles");
    } else {
      System.out.println("Triangulo Escaleno");
    }
  }
   
  double area() {
    double p = (ladoA + ladoB + ladoC)/2;
    return Math.sqrt(p * (p-ladoA) * (p - ladoB) * (p - ladoC));
  }
    
}
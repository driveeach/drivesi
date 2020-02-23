class Triangulo {
  
  double ladoA, ladoB, ladoC;
  
  public void carregaValores(double a, double b, double c) {
    if (a < b + c && b < a + c && c < a + b) {
      ladoA = a;
      ladoB = b;
      ladoC = c;
    }
    else {
      System.out.println("Não é Triângulo!");
    }
  }
  
  public double area() {
    double p = (ladoA + ladoB + ladoC) / 2;
    double area = Math.sqrt(p * (p - ladoA) * (p - ladoB) * (p - ladoC));
    return area;
  }
  
  public void classifica() {
    
    
    if (ladoA == ladoB && ladoB == ladoC) {
      System.out.println("Triângulo Equilátero");
    } else if (ladoA == ladoB || ladoB == ladoC || ladoA == ladoC) {
      System.out.println("Triângulo Isósceles");
    } else {
      System.out.println("Triângulo Escaleno");
    }
  }
}
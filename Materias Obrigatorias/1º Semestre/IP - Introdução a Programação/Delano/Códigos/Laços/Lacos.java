class Lacos {
  
  int somatoria(int n) {
    int soma = 0;
    int i = 1;
    while (i <= n) {
      soma += i; // soma = soma + i;
      i++; // i = i + 1;
    }
    return soma;
  }
  
  int fatorial(int n) {
    int produto = 1;
    int i = 1;
    while (i <= n) {
      produto *= i; // produto = produto * i;
      i++; // i = i + 1;
    }
    return produto;
  }
  
  void ePotenciadeDois(int n) {
    int cont = 0;
    int valor = n;
    while (n % 2 == 0) {
      n = n / 2;
      cont = cont + 1;
    }
    if (n == 1) {
      System.out.println(valor + " = 2^"+ cont); 
    }
    else {
      System.out.println("Não é Potencia de 2");
    }
  }
  
  void imprimeAsteriscosMesmaLinha(int n) {
    int i = 1;
    while (i <= n) {
      System.out.print("*");
      i = i + 1;
    }
  }
  void imprimeCaracterMesmaLinha(int n, char c) {
    int i = 1;
    while (i <= n) {
      System.out.print(c);
      i = i + 1;
    }
  }
  
  void quadrado(int n) {
    int i = 1;
    while (i <= n) {
      //System.out.println("*");
      imprimeAsteriscosMesmaLinha(n);
      i = i + 1;
    }
  }
  
  void triangulo(int n) {
    int i = 1;
    while (i <= n) {
      imprimeAsteriscosMesmaLinha(i);
      i = i + 1;
    }
  }
  
  void triangulo2(int n) {
    int i = n;
    while (i >= 1) {
      imprimeAsteriscosMesmaLinha(i);
      i = i - 1;
    }
  }
  
  void triangulo3(int n) {
    int i = 1;
    while (i <= n) {
      imprimeCaracterMesmaLinha(n-i,'a');
      imprimeCaracterMesmaLinha(i,'b');
      System.out.println();
      i = i + 1;
    }
  }
}
public class Revisao {
  
  /*
   * Faça um método que retorne o maior múltiplo de um inteiro K, inferior ou 
   * igual um inteiro N. Por exemplo, se N = 100 e K = 13, o método retornaria
   * 91.
   */

  int maiorMultiploInferior(int n, int k) {
    return n / k * k;
  }
  
  /*
   * Faça um método que retorne o menor múltiplo de um inteiro K, maior do que 
   * um inteiro N. Por exemplo, se N = 100 e K = 13, o método retornaria 104.
   */
  int menorMultiploSuperior(int n, int k) {
    return maiorMultiploInferior(n, k) + k;
  }
  
  /*
   * Faça um método que imprimiria por extenso um valor em R$. Por exemplo, se o 
   * valor fosse R$ 3,75, o método imprimiria 3 Reais e 75 centavos.
   */
  void imprimeExtenso(double valor) {
    int reais = ((int)(valor * 100)) / 100;
    int centavos = ((int)(valor * 100)) % 100;
    System.out.println(reais + " reais e " + centavos + " centavos.");
  }
  
  /* No sistema imperial frequentemente são utilizadas as seguintes medidas 
   * lineares:
   * 1 pé = 12 polegadas
   * 1 jarda = 3 pés e 
   * 1 milha = 1760 jardas
   * Baseado no fato de que 1 polegada equivale a aproximadamente 26 milímetros 
   * no sistema métrico, faça um método conversão(int metros) que converte 
   * comprimentos expressos no sistema métrico para o sistema imperial. Por 
   * exemplo 20000 metros é equivalente a 1 milha, 376 jardas, 2 pés e 3 polegadas.
   */ 
  
  void conversao(int metro) {
    int polegada, pe, jarda, milha;
    
    polegada = 1000 * metro / 26;
    pe = polegada / 12;
    polegada = polegada % 12;
    jarda = pe / 3;
    pe = pe % 3;
    milha = jarda / 1760;
    jarda = jarda % 1760;
    
    System.out.print(milha + " milha(s), ");
    System.out.print(jarda + " jarda(s), ");
    System.out.print(pe + " pé(s) e ");
    System.out.println(polegada + " polegada(s).");
  }
  
  /*
   * Um número é dito ser PERFEITO se ele é igual à soma dos seus divisores, inferiores
   * a ele. Por exemplo, 6 é perfeito pois 6 = 1 + 2 + 3. Escreva um método que imprime
   * todos os números perfeitos menores que um inteiro N dado.*/
  
  int somaDivisores(int n) {
    int soma = 0;
    int i = 1;
    while (i <= n / 2) {
      if (n % i == 0) {
        soma = soma + i;
      }
      i = i + 1;
    }
    return soma;
  }
  
  void imprimeDivisores(int n) {
    int i = 1;
    while (i <= n / 2) {
      if (n % i == 0) {
        System.out.print(i + " ");
      }
      i = i + 1;
    }
   System.out.println(" = " + n);
  }
  
  void imprimePerfeito(int n) {
    int i = 1;
    while (i < n) {
      if (somaDivisores(i) == i) {
        System.out.println(i);
      }
      i = i + 1;
    }
  }
  
  /*
   * Escreva um método que retorne o número de digitos de um inteiro N dado.
   */
    
  int digitos(int n) {
    int nroDigitos = 0;
    
    if (n < 0) {
      n = -n;
    }
    
    do {
      n = n / 10;
      nroDigitos ++;
    } while (n > 0);
    return nroDigitos;
  }
  
  /*
   * Escreva um método que retorne o número mínimo de termos que devem ser 
   * somadas na série harmônica  1 + 1/2 + 1/4 + ... para que a soma seja 
   * maior do que um real K, dado.
   */
    
  int somaHarmonica(double k) {
    int i = 0;
    double soma = 0;
    while (soma < k) {
      i = i + 1;
      soma = soma + 1.0 / i;
      System.out.println("soma (" + i + ") = " + soma);
    }
    return i;
  }
  
  /*
   * Escreva um método que imprime todos os pares de números de dois digitos que 
   * apresentam a seguinte propriedade: o produto dos números não se altera se 
   * os digitos são invertidos. (Por exemplo, 93 x 13 = 39 x 31 = 1209).
   */
  
  int inverte2(int n) {
    int d = n / 10;
    int u = n % 10;
    return u * 10 + d;
  }
  
  void imprimePropriedade() {
    int num1, num2, inv1, inv2;
    
    num1 = 10;
    while (num1 <= 99) {
      num2 = 10;
      while (num2 <= 99) {
        inv1 = inverte2(num1);
        inv2 = inverte2(num2);
        if (num1 != inv1 && num2 != inv2 && num1 != inv2 && num2 != inv1 && num1 * num2 == inv1 * inv2) {
          System.out.println(num1 + " x " + num2 + " = " + inv1 + " x " + inv2 + " = " + (inv1 * inv2));
        }
        num2 = num2 + 1;
      }
      num1 = num1 + 1;
    }
  }
}
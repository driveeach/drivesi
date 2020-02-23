/* 
 * Uma loja vende seus produtos no sistema entrada mais duas prestações, 
 * sendo a entrada maior do que ou igual às duas prestações, as quais 
 * devem ser iguais, inteiras e as maiores possíveis. Por exemplo, se o valor
 * da mercadoria for R$ 270,00, a entrada e as duas prestações são iguais a 
 * R$ 90,00; se o valor da mercadoria for R$ 302,00, a entrada é de R$ 102,00 
 * e as duas prestações são a iguais a R$ 100,00. Escreva um metodo que receba
 * o valor da mercadoria e forneça o valor da entrada e das duas prestações, de
 * acordo com as regras acima. Observe que uma justificativa para a adoção desta 
 * regra é que ela facilita a confecção e o conseqüente pagamento dos boletos 
 * das duas prestações.
 */
class Prestacoes {
  int entrada;
  int prestacao1;
  int prestacao2;
  
  void carregaValor(int valor) {
    entrada = valor / 3 + valor % 3;
    prestacao1 = valor / 3;
    prestacao2 = valor / 3;
  }

  void imprime() {
     System.out.print("Entrada = R$ " + entrada + ", ");
     System.out.print("Prestação 1 = R$ " + prestacao1 + ", ");
     System.out.println("Prestação 2 = R$ " + prestacao2);
  }
}
  
class Turma02 {
  
  
  /* 
   * Imprime todos os anos bissextos entre dois anos 
   */
  void imprimeAnosBissextos(int ano1, int ano2) {
    
    while (ano1 <= ano2) {
      if ((ano1 % 4 == 0 && ano1 % 100 != 0) || ano1 % 400 == 0) {
        System.out.println(ano1);
      }
      ano1++;
    }
  }
  
  /**
   * Escreva um método que devolve o número de ocorrências das vogais em uma 
   * frase contida em uma String. Considere que as letras maiúsculas e 
   * minúsculas não estão acentuadas
   */

  int contaVogais(String texto) {
    int qtde = 0;
    int i = 0;
    texto = texto.toLowerCase();
    char c;
    while ( i < texto.length()) {
      c = texto.charAt(i);
      switch(c) {
        case 'a':
        case 'e':
        case 'i':
        case 'o':
        case 'u': {
          qtde++;
          break;
        }
      }
      i++;
    }
    return qtde;
  }
  
  /*
   * Escreva um método mdc que recebe dois números inteiros a1 e a2, estritamente
   * positivos, com a1 >= a2, e devolve o máximo divisor comum entre eles, 
   * utilizando o algoritmo de Euclides.
   * 
   * Breve descrição do algoritmo de Euclides
   * Dados a1 e a2, com a1 >= a2, quero o m.d.c.(a1, a2).
   * Calcule a3 = a1 % a2.
   * Se a3 = 0, fim. A solução é a2.
   * Calcule a4 = a2 % a3.
   * Se a4 = 0, fim. A solução é a3.
   * Calcule a5 = a3 % a4.
   * ...
   */

  int mdc(int a, int b) {
    int dividendo = a;
    int divisor = b;
    int resto = dividendo % divisor;
    
    while (resto != 0) {
      dividendo = divisor;
      divisor = resto;
      resto = dividendo % divisor;
    }
    return divisor;
  }
  
   private void imprimeCaracteres(char ch, int n) {
    int i = 0;
    while (i < n) {
      System.out.print(ch);
      i = i + 1;
    }
  }
  
  private void imprimeCaracteresNL(char ch, int n) {
    imprimeCaracteres(ch, n);
    System.out.println();
  }
  
  void trianguloAsteriscoDireita(int n) {
    int i = 1; 
    while (i <= n) {
      imprimeCaracteres(' ', n-i);
      imprimeCaracteresNL('*', i);
      i = i + 1;
    }
  }
  
  double somaSequencia(int n) {
    int i = 1;
    double num = 1;
    double dem = 2;
    double soma = 0;
    while (i <= n) {
      soma = soma + num/dem;
      num = num + 2;
      dem = dem + 3;
      i = i + 1;
    }
    return soma;
  }
}
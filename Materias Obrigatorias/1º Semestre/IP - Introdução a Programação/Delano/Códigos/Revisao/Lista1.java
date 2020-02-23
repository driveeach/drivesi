public class Lista1 {
  
  /** 
   * Escreva um método que, dados três valores inteiros diferentes entre si, apresente o maior de tais valor 
   */
  int maiorDeTres(int a, int b, int c) {
    if (a > b && b > c) { // a maior
      return a;
    } else if (b > c) { // b maior
      return b;
    } else { // c maior
      return c;
    }
  }
  
  /**
   * Escreva um método void ordena(int a, int b, int c) que, dados três valores inteiros, apresente os três valores em ordem crescente.
   */
    
  void ordena(int a, int b, int c) {
    int aux;
    if (a > b) {
      aux = a;
      a = b; 
      b = aux;
    }
    if (a > c) {
      aux = a;
      a = c; 
      c = aux;
    }
    if (b > c) {
      aux = b;
      b = c; 
      c = aux;
    }
    System.out.println(a + ", " + b + ", " + c);  
  }
  
  /**
   * Escreva um método int inverte(int n) que retorna na ordem inversa um inteiro (na base 10) com quatro algarimos.
   */
  int inverte(int cdu) {
    
    int u = cdu;
    int c = u / 100;
    u = u - c * 100;
    int d = u / 10;
    u = u - d * 10;
    return u * 100 + d * 10 + c;
  }
    
  /**
   * Escreva um método double soma(int n1, int d1, int n2, int d2) que soma duas frações ordinárias (n1/d1 e n2/d2), fornecendo o resultado em 
   * forma de fração (n3/d3), onde n3 = n1 * d2 + n2 *  d1 e d3 = d1 * d2.
   */
  double soma(int n1, int d1, int n2, int d2) {
    int n3 = n1 * d2 + n2 * d1;
    int d3 = d1 * d2;
    
    System.out.println(n1 + "/" + d1 +" + " + n2 + "/" + d2 + " = " + n3 + "/" + d3);
    return ((double)n3)/d3;
  }
  
  /**
   * Escreva um método boolean éPar(int n) que verifica se um inteiro é par ou ímpar. 
   */
  boolean ePar(int n) {
    return n % 2 == 0;
  }
  
  /**
   * Escreva um método void raizes (double a, double b, double c) que determina as raízes, reais ou complexas, de uma equação do segundo grau 
   * (ax^2 + bx + c), dados os seus coeficientes (a, b, c). 
   */
  void raizes (double a, double b, double c) {
    double delta = b * b - 4 * a * c;
    
    if (delta > 0) {
      System.out.println("x'  = " + (-b - Math.sqrt(delta))/(2*a));
      System.out.println("x'' = " + (-b + Math.sqrt(delta))/(2*a));
    }
    else if (delta == 0) {
      System.out.println("x' = x'' = " + (-b/(2*a)));
    }
    else {
        System.out.println("x'  = " + (-b/(2*a)) + " - " + (Math.sqrt(-delta)/(2*a)) + "i");
        System.out.println("x'' = " + (-b/(2*a)) + " + " + (Math.sqrt(-delta)/(2*a)) + "i");
    }
  }
  
  /**
   * Escreva um método classificaTriângulo(double a, double b, double c) que verifica se três números dados podem ser comprimentos dos lados 
   * de um triângulo, classificando o triângulo nos casos afirmativos (equilátero, isósceles e escaleno). Observação: 3 lados (a,b,c) formam um 
   * triângulo, se a soma de dois lados é maior que o terceiro lado.
   */
  void classificaTriangulo(double a, double b, double c) {
    
    if (a < b + c && b < a + c && c < a + b) {
      if (a == b && b == c) {
        System.out.println("Triângulo Equilátero");
      } else if (a == b || b == c || a == c) {
        System.out.println("Triângulo Isósceles");
      } else {
        System.out.println("Triângulo Escaleno");
      }
    }
    else {
      System.out.println("Não é Triângulo!");
    }
  }
  
  
  /**
   * No sistema imperial frequentemente são utilizadas as seguintes medidas lineares:
   * 1 pé = 12 polegadas
   * 1 jarda = 3 pés
   * 1 milha = 1760 jardas
   * 
   * Baseado no fato de que 1 polegada equivale a 25.3995 milímetros no sistema métrico, faça um programa que converta comprimentos 
   * expressos nos sistema imperial para o sistema métrico e vice-versa.
   */
  double pesMetros(double pe) {
    return 12 * 25.3995 / 1000;
  }
  
  double jardasMetros(double jarda) {
    return pesMetros (jarda / 3);
  }
  
  double milhasMetros(double milhas) {
    return jardasMetros (milhas / 1760);
  }
  
  /**
   * Escreva um método que dado a velocidade do vento aferida durante uma tempestade e apresente ao usuário a classificação do furacão caso a tempestade 
   * possa ser classificada como tal.
   */
  void classificaTempestade(int velocidade) {
    if (velocidade >= 62 && velocidade <= 118) {
      System.out.println("Tempestade Tropical");
    } else if (velocidade > 118 && velocidade <= 153) {
      System.out.println("Furacao de categoria 1");
    } else if (velocidade > 153 && velocidade <= 177) {
      System.out.println("Furacao de categoria 2");
    } else if (velocidade > 177 && velocidade <= 209) {
      System.out.println("Furacao de categoria 3");
    } else if (velocidade > 209 && velocidade <= 249) {
      System.out.println("Furacao de categoria 4");
    } else if (velocidade > 249) {
      System.out.println("Furacao de categoria 5");
    }
  }
  
  /**
   * Escreva um método void imprimeDivisoresInteiros(int n} que dado um número positivo $n > 0$, achar todos os seus divisores inteiros 
   * diferentes de n.
   */
  public void imprimeDivisoresInteiros(int n) {
    
    int i = 1;
    while (i <= n / 2) {
      if (n % i == 0) {
        System.out.print(i + " ");
      }
      i = i + 1;
    }
    System.out.println();
  }

  /**
   * O número 3025 possui a seguinte característica: 30 + 25 = 55 e 55^2    = 3025.
   * Escreva um método que imprima todos os números de 4 algarismos que apresentam tal característica.
   */
  public void imprimeCaracteristica() {
    int i = 1000;
    while (i <= 9999) {
      int a = i / 100;
      int b = i % 100;
      if ((a + b) * (a + b) == i) {
        System.out.println(a + " + " + b + " = " + (a + b));
        System.out.println(""+(a + b)+'\u005E'+"2 = " + i);
      }
      i = i + 1;
    }
  }
  
  /**
   * Escreva um método que calcule a soma dos n primeiros números inteiros pares positivos para um n>0 fornecido pelo usuário.
   */ 

  int somaNPares(int n) {
    int soma = 0;
    int i = 1;
    while (i <= n) {
      soma = soma + 2 * i;
      i = i + 1;
    }
    
    return soma;
  }
  
  /**
   * Diz-se que um número inteiro n é um quadrado perfeito se existirem m números ímpares consecutivos a partir do valor 1 cuja soma é 
   * igual a n e n = m * m . Exemplo: 16=1+3+5+7 (16 é igual à soma dos quatro primeiros ímpares a partir de 1) e 16 = 4* 4. Logo 16 representa um 
   * quadrado perfeito. Faça um programa que verifique se um valor inteiro positivo fornecido pelo usuário é um quadrado perfeito ou não. 
   */
  boolean quadradoPerfeito(int n) {
    int i = 0;
    int soma = 0;
    while (soma < n) {
      soma = soma + 2 * i + 1;
      i = i + 1;
      
    }
    if (soma == n) {
      return true;
    } else {
      return false;
    } 
  }
  
  /**
   * Faça um método que, para um dado valor inteiro positivo, verifica se tal valor é potência de 2. Em caso afirmativo, o método
   * deve fornecer o valor da potência. Em caso contrário, uma mensagem apropriada deve ser gerada pelo método para o usuário.
   */
  void ehPotenciaDeDois(int n) {
    int v = n;
    int pot = 0;
    while (v % 2 == 0) {
      v = v / 2; 
      pot = pot + 1;
    }
    if (v == 1) {
      System.out.println(n + " é potência de 2 => 2^" + pot);
    }
    else {
      System.out.println(n + " não é potência de 2");
    }
  } 
  
  /**
   * Escreva um método {\bf boolean éPotência(int n, int p)} que, para um dado valor n inteiro positivo, verifica se tal valor é uma potência
   * de p. Em caso afirmativo, o método deve fornecer o valor da potência. Em caso contrário, uma mensagem apropriada deve ser gerada pelo 
   * método para o usuário.
   */
  void ehPotencia(int n, int p) {
    int v = n;
    int pot = 0;
    while (v % p == 0) {
      v = v / p; 
      pot = pot + 1;
    }
    if (v == 1) {
      System.out.println(n + " é potência de " + p + " => "+ p +"^" + pot);
    }
    else {
      System.out.println(n + " não é potência de " + p);
    }
  }
  
  /** 
   * Em criptografia, a Cifra de César, também conhecida como cifra de troca ou ainda código de César, é uma das mais simples e conhecidas técnicas de 
   * encriptação. É um tipo de cifra de substituição em que cada letra do texto é substituída por outra, que se apresenta no alfabeto  rotacionado à 
   * direita ou esquerda um número fixo de posições k. Por exemplo, com uma troca de 3 posições, A seria substituído por D, B viraria E e assim por diante. 
   * Implemente o método String encripte(int k, String in) que dado uma String, retorna uma outra String com os caracteres presentes na String de 
   * entrada encriptados pela Cifra de César.
   */
  String encripte(int k, String texto) {
    int i = 0;
    StringBuffer sb = new StringBuffer();
    while (i < texto.length()) {
      char in = texto.charAt(i);
      char out;
      if (in >= 'a' && in <= 'z') {
        out = (char) ('a' + (in - 'a' + k) % 26); 
      } else if (in >= 'A' && in <= 'Z') {
        out = (char) ('A' + (in - 'A' + k) % 26); 
      } else {
        out = (char) (in + k);
      }
      sb.append(out);
      i++;
    }
    return sb.toString();
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
  
  /**
   * Faça um método void quadrado(int n) que gere para um valor n >= 1 fornecido pelo usuário, um "quadrado" de n linhas e n colunas que tenha 
   * caracteres '*' nas bordas. Por exemplo, para n=5 o método deve gerar
   *   *****                      
   *   *   *                      
   *   *   *                      
   *   *   *                      
   *   *****
   */
  void quadrado (int n) {
    int i = 1, j; 
    while (i <= n) {
      j = 1;
      while (j <= n) {
        if (i == 1 || i == n || j == 1 || j == n) {
          System.out.print("*");
        } else {
          System.out.print(" ");
        }
        j = j + 1;
      }
      System.out.println();
      i = i + 1;
    }
  }
  
  void quadrado2(int n) {
    int i = 1;
    while (i <= n) {
      if (i == 1 || i == n) {
        imprimeCaracteresNL('*', n);
      }
      else {
        imprimeCaracteres('*', 1);
        imprimeCaracteres(' ', n - 2);
        imprimeCaracteresNL('*', 1);
      }
      i = i + 1;
    }
  }
  
  /**
   * Faça um método void quadradoDiagonal(int n) que gere para um valor n >= 1 fornecido pelo usuário, um "quadrado" de n linhas e n colunas que tenha 
   * caracteres ':' nas posições da diagonais e os caracteres '*' nas demais. Por exemplo, para n=5 o método deve gerar
   *     :***:                      
   *     *:*:*                      
   *     **:**                      
   *     *:*:*                      
   *     :***:
   */
  void quadradoDiagonal (int n) {
    int i = 1, j; 
    while (i <= n) {
      j = 1;
      while (j <= n) {
        if (i == j || i + j == (n + 1)) {
          System.out.print(":");
        } else {
          System.out.print("*");
        }
        j = j + 1;
      }
      System.out.println();
      i = i + 1;
    }
  }
  
  /**
   * Faça um método void linhaCrescenteAsteriscoEsquerda(int n) que gere para um valor n >= 1 fornecido pelo usuário, n linhas que tenha 1 caractere '*' 
   * (da esquerda para direita) na 1a linha, 2 caracteres '*' na 2a linha e n caracteres '*' na enésima linha. Por exemplo, para n = 5 o método deve 
   * gerar
   *     *        
   *     **        
   *     ***         
   *     ****
   *     *****
   */
  
  void linhaCrescenteAsteriscoEsquerda(int n) {
    int i = 1, j; 
    while (i <= n) {
      j = 1;
      while (j <= i) {
        System.out.print("*");
        j = j + 1;
      }
      System.out.println();
      i = i + 1;
    }
  }

  void linhaCrescenteAsteriscoEsquerda2(int n) {
    int i = 1; 
    while (i <= n) {
      imprimeCaracteresNL('*', i);
      i = i + 1;
    }
  }
  
  /**
   * Faça um método void linhaCrescenteAsteriscoDireita(int n) que gere para um valor n >= 1 fornecido pelo usuário, n linhas que tenha 1 caractere '*' 
   * (da direita para esquerda) na 1a linha, 2 caracteres '*' na 2a linha e n caracteres '*' na enésima linha. Por exemplo, para n = 5 o método deve 
   * gerar
   *         *        
   *        **        
   *       ***         
   *      ****
   *     *****
   */
  
  void linhaCrescenteAsteriscoDireita(int n) {
    int i = 1, j; 
    while (i <= n) {
      j = 1;
      while (j <= n) {
        if (j > n - i) {
          System.out.print("*");
        }
        else {
          System.out.print(" ");
        }
        j = j + 1;
      }
      System.out.println();
      i = i + 1;
    }
  }
  
  void linhaCrescenteAsteriscoDireita2(int n) {
    int i = 1; 
    while (i <= n) {
      imprimeCaracteres(' ', n-i);
      imprimeCaracteresNL('*', i);
      i = i + 1;
    }
  }
  
   /**
   * Faça um método void linhaDecrescenteAsteriscoEsquerda(int n) que gere para um valor n >= 1 fornecido pelo usuário, n linhas que tenha n caracteres '*' 
   * (da esquerda para direita) na 1a linha, n - 1 caracteres '*' na 2a linha e 1 caractere '*' na enésima linha. Por exemplo, para n = 5 o método deve 
   * gerar
   *     *****        
   *     ****        
   *     ***         
   *     **
   *     *
   */
  
  void linhaDecrescenteAsteriscoEsquerda(int n) {
    int i = 1, j; 
    while (i <= n) {
      j = 1;
      while (j <= n - i + 1) {
        System.out.print("*");
        j = j + 1;
      }
      System.out.println();
      i = i + 1;
    }
  }

  void linhaDecrescenteAsteriscoEsquerda2(int n) {
    int i = n; 
    while (i >= 1) {
      imprimeCaracteresNL('*', i);
      i = i - 1;
    }
  }
  
  /**
   * Faça um método void linhaDecrescenteAsteriscoDireita(int n) que gere para um valor n >= 1 fornecido pelo usuário, n linhas que tenha n caracteres '*' 
   * (da esquerda para direita) na 1a linha, n - 1 caracteres '*' na 2a linha e 1 caractere '*' na enésima linha. Por exemplo, para n = 5 o método deve 
   * gerar
   *     *****        
   *      ****        
   *       ***         
   *        **
   *         *
   */
  
  void linhaDecrescenteAsteriscoDireita(int n) {
    int i = 1, j; 
    while (i <= n) {
      j = 1;
      while (j <= n) {
        if (j >= i) {
          System.out.print("*");
        }
        else {
          System.out.print(" ");
        }
        j = j + 1;
      }
      System.out.println();
      i = i + 1;
    }
  }
  
  void linhaDecrescenteAsteriscoDireita2(int n) {
    int i = 1; 
    while (i <= n) {
      imprimeCaracteres(' ', i-1);
      imprimeCaracteresNL('*', n-i+1);
      i = i + 1;
    }
  }
  
  /**
   * Faça um método que gere a tabuada de 1 até um valor n>=0 na forma de uma tabela tal que, na posição da linha i e coluna j 
   * da tabela, deve-se encontrar o valor i*j. Por exemplo, para n=6 o método deve gerar
   * 1   2   3   4   5   6
   * 2   4   6   8  10  12
   * 3   6   9  12  15  18
   * 4   8  12  16  20  24
   * 5  10  15  20  25  30
   * 6  12  18  24  30  36
   */ 
  void tabuada(int n) {
    int i = 1, j;
    
    while (i <= n) {
      j = 1;
      while (j <= n) {
        System.out.print(j * i + "\t");
        j = j + 1;
      }
      System.out.println();
      i = i + 1;
    }   
  }
}
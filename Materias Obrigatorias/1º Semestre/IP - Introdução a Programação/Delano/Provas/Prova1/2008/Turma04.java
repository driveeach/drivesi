class GeradorDePrimos {
  
  /**
   * Verifica se um numero inteiro positivo eh primo ou nao. 
   */
   boolean ePrimo(int n) {
     
     if (n < 2) {
       return false;
     }
     
     
     // Guarda o numero de divisores de n. Inicialmente eh 1. Todos os numeros
     // sao divisiveis por 1
     
     int numeroDeDivisores = 1;
     
     // O primeiro candidato a divisor nao trivial eh 2.
     
     int candidatoADivisor = 2;
     
     // Testa a divisao por todos os numeros menores ou iguais a n/2 ou ate 
     // encontrar o primeiro divisor.
     
     while((candidatoADivisor <= Math.sqrt(n)) && (numeroDeDivisores == 1)) {
       if (n % candidatoADivisor == 0) {
         // o resto da divisao eh zero. Logo, candidatoADivisor eh um divisor de n. 
         // Por isso, que o numero de divisores eh incrementado em 1.
         numeroDeDivisores = numeroDeDivisores + 1;
       }
       candidatoADivisor = candidatoADivisor + 1;
     }
     
     if (numeroDeDivisores == 1) {
       return true;
     }
     else {
       return false;
     }
   }
  
   int proximoPrimo(int p) {
     p = p + 1;
     while (!ePrimo(p)) {
       p = p + 1;
     }
     return p;
   } 
}

class MMC { 
  
  int mmc(int a, int b) {
    int mmc = 1;
    GeradorDePrimos gp = new GeradorDePrimos();
    
    int primo = gp.proximoPrimo(1);
    
    while (a != 1 || b != 1) {
      if (a % primo == 0 || b % primo == 0) {
        if (a % primo == 0) {
          a = a / primo;
        }
        if (b % primo == 0) {
          b = b / primo;
        }
        mmc = mmc * primo;
      }
      else {
        primo = gp.proximoPrimo(primo);
      }
    }
    return mmc;
  }
  
}

class Tempo {
  int horas;
  int minutos;
  int segundos;

  void carregaValor(int valorEmSegundos) {
    segundos = valorEmSegundos % 60;
    minutos = (valorEmSegundos / 60) % 60;
    horas = valorEmSegundos / 3600;
  }

  void imprime() {
     System.out.println(horas + " h " + minutos + " min " + segundos + " s");
  }
}

class Turma04 {
  
  void imprimeNumeros(int inicio, int fim) {
    while (inicio <= fim) {
      if ((inicio % 2 == 0 || inicio % 3 == 0) && inicio % 6 != 0) {
        System.out.println(inicio);
      }
      inicio++;
    }
  }
    
  void imprimeVogais(String texto) {
    int i = 0;
    char c;
    while ( i < texto.length()) {
      c = texto.charAt(i);
      switch(c) {
        case 'a':
        case 'A':
        case 'e':
        case 'E':
        case 'i':
        case 'I':
        case 'o':
        case 'O':
        case 'u':
        case 'U':{
          System.out.print(c);
        }
      }
      i++;
    }
    System.out.println();
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
  
  void triangulo(int n) {
    int i = 1; 
    while (i <= n) {
      imprimeCaracteres(' ', n-i);
      imprimeCaracteresNL('*', 2*i);
      i = i + 1;
    }
  }
   
  double somaSequencia(int n) {
    int i = 1;
    double num = 1;
    double dem = 2;
    int sinal = 1;
    double soma = 0;
    while (i <= n) {
      soma = soma + sinal * num/dem;
      num = num + 1;
      dem = dem + 1;
      sinal = -sinal;
      i = i + 1;
    }
    return soma;
  }
}
class GeradorDePrimos {
  
  // Limite inferior para busca de um novo primo;
  
  int limiteInferior = 1;
  
  void carregaLimiteInferior(int limite) {
    limiteInferior = limite;
  }
  
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
  
   int proximoPrimo() {
     limiteInferior = limiteInferior + 1;
     while (!ePrimo(limiteInferior)) {
       limiteInferior = limiteInferior + 1;
     }
     return limiteInferior;
   } 
   
   void imprimePrimos(int quantidade) {
     int i = 1;
     while (i <= quantidade) {
       System.out.println(proximoPrimo());
       i = i + 1;
     }
   }
}
class GeradorDePrimos {
  
  /**
   * Imprime na tela se um numero inteiro positivo eh primo ou nao. 
   */
   void verificaPrimalidade(int n) {
     // Guarda o numero de divisores de n. Inicialmente eh zero
     
     int numeroDeDivisores = 0;
     
     // O primeiro candidato a divisor eh 1.
     
     int candidatoADivisor = 1;
     
     // Testa a divisao por todos os numeros menores ou iguais a n.
     
     while(candidatoADivisor <= n) {
       if (n % candidatoADivisor == 0) {
         // o resto da divisao eh zero. Logo, candidatoADivisor eh um divisor de n. 
         // Por isso, que o numero de divisores eh incrementado em 1.
         numeroDeDivisores = numeroDeDivisores + 1;
       }
       candidatoADivisor = candidatoADivisor + 1;
     }
     
     // Imprime a resposta.
     
     if (numeroDeDivisores == 2) {
       System.out.println(n + " eh primo.");
     }
     else {
       System.out.println(x + " nao eh primo.");
     }
   }
}
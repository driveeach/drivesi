class ManipuladorDeInteiros {
  
  /**
   * Fatora em primos um inteiro > 1
   */
  
  void fatoraInteiros(int x) {
    
    System.out.print(x + " = ");
    
    // Usa um gerador de primos para encontrar os primos menores ou iguais a x.
    GeradorDePrimos gerador = new GeradorDePrimos();
    int primo = gerador.proximoPrimo();
    
    // Continua fatorando o número até que x torne-se 1.
    
    while (x > 1) {
      if (x % primo == 0) {
        System.out.print(" " + primo);
        x = x / primo;
      }
      else
        primo = gerador.proximoPrimo();
    }
   // Imprime um fim de linha no final.
   System.out.println();
  }
}
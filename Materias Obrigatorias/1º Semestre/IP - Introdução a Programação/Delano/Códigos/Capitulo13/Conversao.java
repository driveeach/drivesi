class Conversao {
  
  int binarioParaDecimal(int n){
    int dec = 0;
    int pot2 = 1;
    
    while (n != 0) {
      /* processa um digito binario */
      dec = dec + n % 10 * pot2;
      n = n / 10;
      pot2 = pot2 * 2;
    }
    return dec;
  }

  long decimalParaBinario(int n) {
    int dig;
    long bin = 0;
    int pot = 1;
    
    while (n > 0) {
      /* extrai proximo digito binario menos significativo (mais a direita) */
      dig = n % 2;
      /* remove esse digito do n */
      n = n / 2;
      /* adiciona o digito como o mais significativo ate o momento */
      bin = bin + dig * pot;
      pot = pot * 10;
    }
    
    return bin;
  }
}
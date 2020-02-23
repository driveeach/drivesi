class Conversao2 {
  
  String decimalParaBinario(int n) {
    int dig;
    String bin = "";
    
    while (n > 0) {
      /* extrai proximo digito binario menos significativo (mais a direita) */
      dig = n % 2;
      /* remove esse digito do n */
      n = n / 2;
      /* adiciona o digito como o mais significativo ate o momento */
      bin = dig + bin;
    }
    return bin;
  }
  
  int binarioParaDecimal(String bin) {
    int dec = 0;
    int pot2 = 1;
    char c;
    
    int i = bin.length() - 1;
        
    while (i >= 0) {
      c = bin.charAt(i);
      dec = dec + (c - '0') * pot2;
      pot2 = pot2 * 2;
      i = i - 1;
    }
    return dec;
  }
  
  String decimalParaHexa(int n) {
    int dig;
    String hexa = "";
    
    while (n > 0) {
      /* extrai proximo digito hexadecimal menos significativo (mais a direita) */
      dig = n % 16;
      /* remove esse digito do n */
      n = n / 16;
      /* adiciona o digito como o mais significativo ate o momento */
      if (dig < 9) {
        hexa = (char)('0' + dig) + hexa;
      }
      else {
        hexa = (char)('A' + dig - 10) + hexa;
      }
    }
    return hexa;
  }
  
  int hexaParaDecimal(String hexa) {
    int dec = 0;
    int pot16 = 1;
    char c;
    
    int i = hexa.length() - 1;
    hexa = hexa.toUpperCase(); // garantir que todas estao maiusculas
    
    while (i >= 0) {
      c = hexa.charAt(i);
      
      if (c >= '0' && c <= '9') {
        dec = dec + (c - '0') * pot16;
      } else if (c >= 'A' && c <= 'F') {
        dec = dec + (c - 'A' + 10) * pot16;
      }
      pot16 = pot16 * 16;
      i = i - 1;
    }
    return dec;
  }
  
  String hexaDecimalParaBinario(String hexa) {
    return decimalParaBinario(hexaParaDecimal(hexa));
  }
  
  // Exercicio 1

  int numeroDeDigitos(int n) {
    int noDig = 0;
    
    if (n == 0)
      return 1;
    
    if (n < 0) // Se negativo, torna positivo
       n = -n;
    
    while (n > 0) {
      /* remove um digito de n */
      n = n / 10;
      noDig = noDig + 1;
    }
    
    return noDig;    
  }
  
  // Exercicio 2
  
  boolean existeConsecutivos(int n) {
    boolean existe = false;
    int ultimo;
    int atual;
    
    if (n < 0) { // Se negativo, torna positivo
      n = -n;
    }
    ultimo = -1; // atribui um numero diferente de qualquer outro a ultimo
    
    while (n > 0 && !existe) {
      atual = n % 10;
      n = n / 10;
      if(atual == ultimo) {
        existe = true;   
      }
      ultimo = atual;
    }
    return existe;    
  }
  
  // Exercicio 3 parte 1 -- uma solucao

  boolean testaPalindromo1(int n) {
    int noDig;
    int dig;
    int original = n;
    int inverso = 0;
   
    if (n < 0) { 
      n = -n; 
      original = n;
    }
    
    noDig = numeroDeDigitos(n);
        
    // calculo o inverso
    
    while (n > 0) {
      dig = n % 10;
      n = n / 10;
      inverso = inverso + dig * ((int)java.lang.Math.pow(10,noDig - 1));
      noDig = noDig - 1;
    }
    
    if (inverso == original) {
      return true;
    }
    else {
      return false;
    }
  }
  
  // Exercicio 3 parte 1 -- outra solucao

  boolean testaPalindromo2(int n) {
    int dig;
    String nstr = "";
    String inver = "";
    
    if (n < 0) {
      n = -n;
    }
        
    nstr = nstr + n;
    
    // calculo o inverso
    
    while (n > 0) {
      dig = n % 10;
      n = n/10;
      inver = inver + dig;
    }   

    if (inver.equals(nstr))
      return true;
    else
      return false;
  }
  
  boolean testaPalindromo3(int n) {
    int faixa;
    int dir;
    int esq;
    boolean ehPalindromo = true;
    
    String nstr = "";
    String inver = "";
    
    if (n < 0) n = -n;
        
    nstr = nstr + n;
    
    // pega tamanho do string e coloca em faixa.
    
    faixa = nstr.length();
    
    while (faixa/2 > 0) {
      dir = faixa - 1;
      esq = nstr.length() - faixa;
      if (nstr.charAt(esq) != nstr.charAt(dir)) {
        ehPalindromo = false;
      }
      faixa = faixa - 1;
    }   

    return ehPalindromo;
  }
}
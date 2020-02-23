class Caracteres {
  
  void verificaResposta(char ch) {
    if((ch == 's') || (ch == 'S'))
      System.out.println("A resposta foi sim");
    else if ((ch == 'n') || (ch == 'N'))
      System.out.println("A resposta foi nao");
    else
      System.out.println("Resposta invalida");
  }
  
  void imprimeCaracteres(char ch, int n) {
    int i = 0;
    while (i < n)
    {
      System.out.print (ch);
      i = i+1;
    }
  }
  
  void novaLinha() {
    System.out.println();
  }
  
  void imprimeCaracteresNL(char ch, int n) {
    imprimeCaracteres(ch, n);
    novaLinha();
  }
  
  
 
   void desenhaE() {
    imprimeCaracteresNL('E',15);
    imprimeCaracteresNL('E',14);
    imprimeCaracteresNL('E',3);
    imprimeCaracteresNL('E',3);
    imprimeCaracteresNL('E',13);
    imprimeCaracteresNL('E',13);
    imprimeCaracteresNL('E',3);
    imprimeCaracteresNL('E',3);
    imprimeCaracteresNL('E',14);
    imprimeCaracteresNL('E',15);
  }
   
  void desenhaA() {
    imprimeCaracteresNL('A',15);
    
    imprimeCaracteres('A',3);
    imprimeCaracteres(' ',9);
    imprimeCaracteresNL('A',3);
    
    imprimeCaracteres('A',3);
    imprimeCaracteres(' ',9);
    imprimeCaracteresNL('A',3);
    
    imprimeCaracteres('A',3);
    imprimeCaracteres(' ',9);
    imprimeCaracteresNL('A',3);
    
    imprimeCaracteres('A',3);
    imprimeCaracteres(' ',9);
    imprimeCaracteresNL('A',3);
    
    imprimeCaracteresNL('A',15);
    
    imprimeCaracteres('A',3);
    imprimeCaracteres(' ',9);
    imprimeCaracteresNL('A',3);
    
    imprimeCaracteres('A',3);
    imprimeCaracteres(' ',9);
    imprimeCaracteresNL('A',3);
    
    imprimeCaracteres('A',3);
    imprimeCaracteres(' ',9);
    imprimeCaracteresNL('A',3);
    
    imprimeCaracteres('A',3);
    imprimeCaracteres(' ',9);
    imprimeCaracteresNL('A',3);
    
  }
  
  void desenhaC() {
    imprimeCaracteresNL('C',15);
    imprimeCaracteresNL('C',15);
    imprimeCaracteresNL('C',3);
    imprimeCaracteresNL('C',3);
    imprimeCaracteresNL('C',3);
    imprimeCaracteresNL('C',3);
    imprimeCaracteresNL('C',3);
    imprimeCaracteresNL('C',3);
    imprimeCaracteresNL('C',15);
    imprimeCaracteresNL('C',15);
  }
  
  void desenhaH() {
    
    imprimeCaracteres('H',3);
    imprimeCaracteres(' ',9);
    imprimeCaracteresNL('H',3);
    
    imprimeCaracteres('H',3);
    imprimeCaracteres(' ',9);
    imprimeCaracteresNL('H',3);
    
    imprimeCaracteres('H',3);
    imprimeCaracteres(' ',9);
    imprimeCaracteresNL('H',3);
    
    imprimeCaracteres('H',3);
    imprimeCaracteres(' ',9);
    imprimeCaracteresNL('H',3);
    
    imprimeCaracteres('H',3);
    imprimeCaracteres(' ',9);
    imprimeCaracteresNL('H',3);
    
    imprimeCaracteresNL('H',15);
    imprimeCaracteresNL('H',15);
    
    imprimeCaracteres('H',3);
    imprimeCaracteres(' ',9);
    imprimeCaracteresNL('H',3);
        
    imprimeCaracteres('H',3);
    imprimeCaracteres(' ',9);
    imprimeCaracteresNL('H',3);
    
    imprimeCaracteres('H',3);
    imprimeCaracteres(' ',9);
    imprimeCaracteresNL('H',3);
    
    imprimeCaracteres('H',3);
    imprimeCaracteres(' ',9);
    imprimeCaracteresNL('H',3);
  }
  
  void desenhaU() {
    imprimeCaracteresNL('*',20);
    imprimeCaracteres('U',3);
    imprimeCaracteres(' ',9);
    imprimeCaracteresNL('U',3);
    
    imprimeCaracteres('U',3);
    imprimeCaracteres(' ',9);
    imprimeCaracteresNL('U',3);
    
    imprimeCaracteres('U',3);
    imprimeCaracteres(' ',9);
    imprimeCaracteresNL('U',3);
    
    imprimeCaracteres('U',3);
    imprimeCaracteres(' ',9);
    imprimeCaracteresNL('U',3);
    
    imprimeCaracteres('U',3);
    imprimeCaracteres(' ',9);
    imprimeCaracteresNL('U',3);
    
    imprimeCaracteres('U',3);
    imprimeCaracteres(' ',9);
    imprimeCaracteresNL('U',3);
    
    imprimeCaracteres('U',3);
    imprimeCaracteres(' ',9);
    imprimeCaracteresNL('U',3);
    
    imprimeCaracteres('U',3);
    imprimeCaracteres(' ',9);
    imprimeCaracteresNL('U',3);
    
    imprimeCaracteres('U',3);
    imprimeCaracteres(' ',9);
    imprimeCaracteresNL('U',3);
    
    imprimeCaracteresNL('U',15);
    
  }
  
  void desenhaEACH() {
    desenhaE();
    desenhaA();
    desenhaC();
    desenhaH();
  }
  
  void desenhaEACH2() {
    imprimeCaracteres('E',15); 
    imprimeCaracteres(' ',10); 
    imprimeCaracteres('A',15); 
    imprimeCaracteres(' ',10); 
    imprimeCaracteres('C',15);
    imprimeCaracteres(' ',10); 
    imprimeCaracteres('H',3); 
    imprimeCaracteres(' ',9); 
    imprimeCaracteresNL('H',3); 
    
    imprimeCaracteres('E',14);
    imprimeCaracteres(' ',1);
    imprimeCaracteres(' ',10); 
    imprimeCaracteres('A',3);
    imprimeCaracteres(' ',9);
    imprimeCaracteres('A',3);
    imprimeCaracteres(' ',10); 
    imprimeCaracteres('C',15);
    imprimeCaracteres(' ',10); 
    imprimeCaracteres('H',3);
    imprimeCaracteres(' ',9);
    imprimeCaracteresNL('H',3);
    
    imprimeCaracteres('E',3);
    imprimeCaracteres(' ',12);
    imprimeCaracteres(' ',10);
    imprimeCaracteres('A',3);
    imprimeCaracteres(' ',9);
    imprimeCaracteres('A',3);
    imprimeCaracteres(' ',10); 
    imprimeCaracteres('C',3);
    imprimeCaracteres(' ',12);
    imprimeCaracteres(' ',10);
    imprimeCaracteres('H',3);
    imprimeCaracteres(' ',9);
    imprimeCaracteresNL('H',3);
    
    imprimeCaracteres('E',3);
    imprimeCaracteres(' ',12);
    imprimeCaracteres(' ',10);
    imprimeCaracteres('A',3);
    imprimeCaracteres(' ',9);
    imprimeCaracteres('A',3);
    imprimeCaracteres(' ',10); 
    imprimeCaracteres('C',3);
    imprimeCaracteres(' ',12);
    imprimeCaracteres(' ',10);
    imprimeCaracteres('H',3);
    imprimeCaracteres(' ',9);
    imprimeCaracteresNL('H',3);
    
    imprimeCaracteres('E',13);
    imprimeCaracteres(' ',2);
    imprimeCaracteres(' ',10);
    imprimeCaracteres('A',15);
    imprimeCaracteres(' ',10); 
    imprimeCaracteres('C',3);
    imprimeCaracteres(' ',12);
    imprimeCaracteres(' ',10);
    imprimeCaracteresNL('H',15);    
    imprimeCaracteres('E',13);
    imprimeCaracteres(' ',2);
    imprimeCaracteres(' ',10);
    imprimeCaracteres('A',15);
    imprimeCaracteres(' ',10); 
    imprimeCaracteres('C',3);
    imprimeCaracteres(' ',12);
    imprimeCaracteres(' ',10);
    imprimeCaracteresNL('H',15);
    
    imprimeCaracteres('E',3);
    imprimeCaracteres(' ',12);
    imprimeCaracteres(' ',10);
    imprimeCaracteres('A',3);
    imprimeCaracteres(' ',9);
    imprimeCaracteres('A',3);
    imprimeCaracteres(' ',10); 
    imprimeCaracteres('C',3);
    imprimeCaracteres(' ',12);
    imprimeCaracteres(' ',10);
    imprimeCaracteres('H',3);
    imprimeCaracteres(' ',9);
    imprimeCaracteresNL('H',3);
    
    
    imprimeCaracteres('E',3);
    imprimeCaracteres(' ',12);
    imprimeCaracteres(' ',10);
    imprimeCaracteres('A',3);
    imprimeCaracteres(' ',9);
    imprimeCaracteres('A',3);
    imprimeCaracteres(' ',10); 
    imprimeCaracteres('C',3);
    imprimeCaracteres(' ',12);
    imprimeCaracteres(' ',10);
    imprimeCaracteres('H',3);
    imprimeCaracteres(' ',9);
    imprimeCaracteresNL('H',3);
    
    imprimeCaracteres('E',14);
    imprimeCaracteres(' ',1);
    imprimeCaracteres(' ',10); 
    imprimeCaracteres('A',3);
    imprimeCaracteres(' ',9);
    imprimeCaracteres('A',3);
    imprimeCaracteres(' ',10); 
    imprimeCaracteres('C',15);
    imprimeCaracteres(' ',10); 
    imprimeCaracteres('H',3);
    imprimeCaracteres(' ',9);
    imprimeCaracteresNL('H',3);
    
    imprimeCaracteres('E',14);
    imprimeCaracteres(' ',1);
    imprimeCaracteres(' ',10); 
    imprimeCaracteres('A',3);
    imprimeCaracteres(' ',9);
    imprimeCaracteres('A',3);
    imprimeCaracteres(' ',10); 
    imprimeCaracteres('C',15);
    imprimeCaracteres(' ',10); 
    imprimeCaracteres('H',3);
    imprimeCaracteres(' ',9);
    imprimeCaracteresNL('H',3);
  }
  
}
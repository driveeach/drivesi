import java.lang.Math.*;
import java.util.Random;

class TestaHHash {
 static int m=100; // quantidade de slots
 static int n=50; // quantidade de elementos a inserir
 static double OURO=1.6180339887;
 static int contaColisoes=0;
 static int[] T;  // tabela de hash;
 static Random RNG = new Random ();

 static void emptyTable () {  // esvazia tabela
  T= new int[m];  
 }
 static String geraChave () {
  String Chave= Integer.toString(RNG.nextInt (32),32) + Integer.toString(RNG.nextInt (32),32);
  return Chave;
 }
 static int H (String chave) {
  //int c= (int) chave.charAt(0);
  int c= ((int) chave.charAt(1))*100 + ((int) chave.charAt(0));
  double d=OURO*c;
  //return c % m;
  return (int) ((d- Math.floor(d)) * m);
 }
 public static void main (String[] args) {
  if (args.length != 1) {
   System.out.println ("TestaHHash <nTestes>");
   System.exit (0);
  }

  int nTestes= Integer.parseInt (args[0]);  // quantidade de testes
  long init=System.nanoTime();
  for (int iTeste=0;iTeste<nTestes;iTeste++) {  // testes
   emptyTable();
   for (int i=0;i<n;i++) { // insercoes
    String chave = geraChave();
    int hChave = H(chave);
    System.out.println ("Insere " + chave + " no slot " + hChave);
    if (T[hChave]>0) { // colisao
     contaColisoes++;
    }
    T[hChave]++;
   } // insercoes
  } // testes
  init-=System.nanoTime();

  System.out.println ("Caso \"tipico\"");
  imprimeCaso();
  System.out.println ("Quantidade media de colisoes=" + contaColisoes/(double) nTestes);
  System.out.println ("Tempo médio de execucao=" + -init/(double) nTestes);
 }
 static void imprimeCaso () {
  for (int i=0;i<m;i++) {
   System.out.print (i + ": ");
   for (int j=0;j<T[i];j++) {
    System.out.print ("X");
   }
   System.out.println ();
  }    
 }
}


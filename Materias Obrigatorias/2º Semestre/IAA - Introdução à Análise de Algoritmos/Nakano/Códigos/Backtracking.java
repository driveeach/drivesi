/** Feito especialmente para ilustrar a aula */

class Ponte {
 char Origem, Destino;
 int VisitadaEm=0;
}

class Backtracking {
 static Ponte[] Ps = new Ponte[6];  // uso de 1 a 5
 static void backtracking (char Ilha, int n) {
  if (n==Ps.length) {
   imprime (Ps);
   System.exit (0);
  }
  for (int i=1;i<Ps.length;i++) {
   if (aceita (Ilha, Ps[i])) {
    Ps[i].VisitadaEm=n;
    imprime (Ps);
    backtracking (outraPonta(Ilha, Ps[i]), n+1);
    Ps[i].VisitadaEm=0;   // Tentativa falhou
   }
  }
 }
 static boolean aceita (char Ilha, Ponte P) {
  if ((P.VisitadaEm==0) && ((P.Origem==Ilha)||(P.Destino==Ilha))) return true;
  return false;
 }
 static char outraPonta (char Ilha, Ponte P) {
  if (P.Origem==Ilha) return P.Destino;
  return P.Origem;
 }
 static void imprime (Ponte[] Ps) {
  for (int i=1;i<Ps.length;i++) System.out.println ("Ponte " + i + ":" + Ps[i].Origem + "-" + Ps[i].Destino + ", Visitada em " + Ps[i].VisitadaEm);
  System.out.println();
 }

 public static void main (String[] args) {

  Ps[1]=new Ponte();
  Ps[1].Origem='B';
  Ps[1].Destino='D';
  Ps[2]=new Ponte();
  Ps[2].Origem='C';
  Ps[2].Destino='D';
  Ps[3]=new Ponte();
  Ps[3].Origem='D';
  Ps[3].Destino='B';
  Ps[4]=new Ponte();
  Ps[4].Origem='A';
  Ps[4].Destino='C';
  Ps[5]=new Ponte();
  Ps[5].Origem='A';
  Ps[5].Destino='B';

  backtracking('D', 1);
 }
}


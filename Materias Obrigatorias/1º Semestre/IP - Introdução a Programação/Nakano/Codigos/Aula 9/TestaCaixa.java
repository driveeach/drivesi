import classe3.*;

class Caixa {
    private Triangulo T1, T2, T3;
	public void guardaTriangulo (Triangulo T) {
	    if (T1==null) {
		    T1=T;
		}
	    else {
		    if (T2==null) {
		        T2=T;
			}
            else {
			    if (T3==null) {
		            T3=T;
				}
				else {
					System.out.println ("Caixa cheia");
				}
			}
		}
	}
	public void imprimeTriangulos () {
		if (T1!=null) System.out.println (T1.getCor());
		if (T2!=null) System.out.println (T2.getCor());
		if (T3!=null) System.out.println (T3.getCor());
	}
}

class TestaCaixa {
    public static void main (String args[]) {

       Triangulo T1, T2, T3;
	   //Triangulo.setCor (Cor.VERDE); //erro de contexto
       T1 = new Triangulo();
       T2 = new Triangulo();
       T3 = new Triangulo();
       T1.setCor(Cor.VERMELHO);
       T2.setCor(Cor.AZUL);
       T3.setCor(Cor.MARROM);
       System.out.println (T1.getCor());
       System.out.println (T2.getCor());
       System.out.println (T3.getCor());

	   Caixa CxA, CxB;
       CxA=new Caixa ();
	   CxB=new Caixa ();
	   if (T1.getCor()==Cor.AZUL) {
	       CxA.guardaTriangulo (T1);
	   }
	   else {
	       CxB.guardaTriangulo (T1);
	   }
	   if (T2.getCor()==Cor.AZUL) {
	       CxA.guardaTriangulo (T2);
	   }
	   else {
	       CxB.guardaTriangulo (T2);
	   }
	   if (T3.getCor()==Cor.AZUL) {
	       CxA.guardaTriangulo (T3);
	   }
	   else {
	       CxB.guardaTriangulo (T3);
	   }
	   CxA.imprimeTriangulos ();
	   CxB.imprimeTriangulos ();
    }
}


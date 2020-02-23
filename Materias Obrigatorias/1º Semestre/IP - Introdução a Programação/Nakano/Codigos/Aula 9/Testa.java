class Triangulo {
    int Cor;
}

class Cor {
    public static final int PRETO=0;
    public static final int MARROM=1;
    public static final int VERMELHO=2;
    public static final int LARANJA=3;
    public static final int AMARELO=4;
    public static final int VERDE=5;
    public static final int AZUL=6;
    public static final int VIOLETA=7;
    public static final int CINZA=8;
    public static final int BRANCO=9;
    static String retornaCor (int c) {
        switch (c) {
            case PRETO: return "preto";
            case MARROM: return "marrom";
            case VERMELHO: return "vermelho";
            case LARANJA: return "laranja";
            case AMARELO: return "amarelo";
            case VERDE: return "preto";
            case AZUL: return "azul";
            case VIOLETA: return "violeta";
            case CINZA: return "cinza";
            case BRANCO: return "branco";
        }
        
        return "Erro!";
    }
}

class Caixa {
    static Triangulo T0;
    Triangulo T1, T2, T3;
    boolean T1ocupado, T2ocupado, T3ocupado;
    void guardaTriangulo (Triangulo T) {
        if (!T1ocupado) {
            T1=T;   
            T1ocupado=true;
        }
        else {
            if (!T2ocupado) {
                T2=T;
                T2ocupado=true;
            }
            else {
                if (!T3ocupado) {
                    T3=T;
                    T3ocupado=true;
                }
                else {
                    System.out.println ("Caixa cheia");
                }
            }
        }
    }
    void imprimeConteudo () {
        if (!T1ocupado) {
            System.out.println ("Caixa vazia");
        }
        else {
            System.out.println ("T1, Cor " + Cor.retornaCor(T1.Cor));
        }
        if (T2ocupado) {
            System.out.println ("T2, Cor " + Cor.retornaCor(T2.Cor));
        }
        if (T3ocupado) {
            System.out.println ("T3, Cor " + Cor.retornaCor (T3.Cor));
        }
    }
}

class Testa {
    public static void main (String args[]) {
       Triangulo T1, T2, T3;
       T1 = new Triangulo();
       T2 = new Triangulo();
       T3 = new Triangulo();
       T1.Cor=Cor.VERMELHO;
       T2.Cor=Cor.AZUL;
       T3.Cor=Cor.CINZA;
       System.out.println (T1.Cor);
       System.out.println (T2.Cor);
       System.out.println (T3.Cor);

       Caixa CxA, CxB;
       CxA= new Caixa();
       CxB= new Caixa();
       Caixa.T0=T1;
       if (T1.Cor==Cor.AZUL) {
           CxA.guardaTriangulo(T1);
       }
       else {
           CxB.guardaTriangulo(T1);
       }
       if (T2.Cor==Cor.AZUL) {
           CxA.guardaTriangulo(T2);
       }
       else {
           CxB.guardaTriangulo(T2);
       }
       if (T3.Cor==Cor.AZUL) {
           CxA.guardaTriangulo(T3);
       }
       else {
           CxB.guardaTriangulo(T3);
       }
       CxB.imprimeConteudo ();
       System.out.println (CxB.T0.Cor);
    }
}


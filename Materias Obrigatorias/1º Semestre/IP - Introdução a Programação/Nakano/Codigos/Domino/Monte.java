import java.util.*;   // aqui tem o Random

class Monte extends ListaDePecas {
    Monte (int m) {
        super(m);
    }
    void cria () {
        // Peca a = new Peca ();

        for (int i = 0; i <= 6; i++ ) {
            for (int j = i; j<=6; j++) {
                Peca a = new Peca ();
                a.a=i;
                a.b=j;
                poe (a);
            }
        }
     }
     Peca tira () {
         return tira((int) (Math.random() * tamanho)); 

         //Random r = new Random();
         //Math.abs(r.nextInt()) % tamanho
     }
     public static void main (String[] args) {
        ListaDePecas[] mao = new ListaDePecas[3];
        mao[0]=new ListaDePecas (6);
        mao[1]=new ListaDePecas (6);
        mao[2]=new ListaDePecas (6);
        Monte M = new Monte(28);
        M.cria();
        for (int i=0;i<6;i++) {
            mao[0].poe(M.tira());
            mao[1].poe(M.tira());
            mao[2].poe(M.tira());
        }
        System.out.print ("mao 0");
        mao[0].print();
        System.out.println ();
        System.out.print ("mao 1");
        mao[1].print();
        System.out.println ();
        System.out.print ("mao 2");
        mao[2].print();
        System.out.println ();
        System.out.print ("monte");
        M.print();
     }
}











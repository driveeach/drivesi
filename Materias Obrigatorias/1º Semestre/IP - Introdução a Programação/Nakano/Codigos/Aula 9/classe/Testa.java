class Triangulo {
    int Cor;
}

class Testa {
    public static void main (String args[]) {
       int MARROM=1, VERMELHO=2, VERDE=3;
       Triangulo T1, T2, T3;
       T1 = new Triangulo();
       T2 = new Triangulo();
       T3 = new Triangulo();
       T1.Cor=VERMELHO;
       T2.Cor=VERDE;
       T3.Cor=MARROM;
       System.out.println (T1.Cor);
       System.out.println (T2.Cor);
       System.out.println (T3.Cor);
    }
}

/* O que foi resolvido:
 * 1-) main como método da classe Triangulo;
*/
/* problema criado:
 * nenhum */
/* aviso:
 * duas classes em um mesmo arquivo */

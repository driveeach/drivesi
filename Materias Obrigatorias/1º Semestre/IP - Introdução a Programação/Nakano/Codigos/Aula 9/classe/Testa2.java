enum Cor {MARROM, VERMELHO, VERDE};

class Triangulo {
    Cor C;
}

class Testa2 {
    public static void main (String args[]) {

       Triangulo T1, T2, T3;
       T1 = new Triangulo();
       T2 = new Triangulo();
       T3 = new Triangulo();
       T1.C=Cor.VERMELHO;
       T2.C=Cor.VERDE;
       T3.C=Cor.MARROM;
       // T3.C=1234; // nao funciona.
       System.out.println (T1.C);
       System.out.println (T2.C);
       System.out.println (T3.C);
    }
}

/* O que foi resolvido:
 * 1-) main como método da classe Triangulo;
 * 2-) Cor como um inteiro (embora seja o correto 
 *     em outras linguagens, e seja prática comum,
 *     permite atribuir a Cor valores que não representam 
 *     cores.
*/
/* problema criado:
 * nenhum */
/* aviso:
 * tres classes em um mesmo arquivo */

class Testa3 {
    public static void main (String args[]) {

       Triangulo T1, T2, T3;
       T1 = new Triangulo();
       T2 = new Triangulo();
       T3 = new Triangulo();
       T1.setCor(Cor.VERMELHO);
       T2.setCor(Cor.VERDE);
       T3.setCor(Cor.MARROM);
       System.out.println (T1.getCor());
       System.out.println (T2.getCor());
       System.out.println (T3.getCor());
    }
}

/* O que foi resolvido:
 * aviso:
 * tres classes em um mesmo arquivo */

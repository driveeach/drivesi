enum Cor {MARROM, VERMELHO, VERDE};

class Triangulo {
  private Cor C;
  public void setCor (Cor NovaCor) {
        C=NovaCor;
    }
    Cor getCor () {
        return C;
    }
}

class Testa3 {
    public static void main (String args[]) {

       Triangulo T1, T2, T3;
       T1 = new Triangulo();
       T2 = new Triangulo();
       T3 = new Triangulo();
       T1.setCor(Cor.VERMELHO);
       T2.setCor(Cor.VERDE);
       T3.C=Cor.MARROM;  // Não funciona pois o atributo é private
       System.out.println (T1.getCor());
       System.out.println (T2.getCor());
       System.out.println (T3.getCor());
    }
}

/* O que foi resolvido:
 * 3-) Atributos da classe são diretamente modificáveis,
       o que expõe a implementação. */
/* problema criado:
 * nenhum */
/* aviso:
 * tres classes em um mesmo arquivo */

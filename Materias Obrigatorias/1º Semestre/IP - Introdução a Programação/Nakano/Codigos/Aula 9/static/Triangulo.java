/* a questão do static */
class Triangulo {
  private static Cor C;
  public void setCor (Cor NovaCor) {
        C=NovaCor;
    }
    Cor getCor () {
        return C;
    }
}


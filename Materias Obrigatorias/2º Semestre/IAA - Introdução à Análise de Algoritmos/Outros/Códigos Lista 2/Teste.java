class Teste
{
  public static void main (String[] args)
  {
    Gerente x = new Gerente(22);
    x.defineNome("Joao");
    x.calculaHE(10);
    x.calculaSalarioFinal();
    x.imprimeDados();
    System.out.println();
     Gerente j = new Gerente(40);
    j.defineNome("Maria");
    j.calculaHE(40);
    j.calculaSalarioFinal();
    j.imprimeDados();
    System.out.println();
    Gerente k = new Gerente(-22);
    Gerente a = new Gerente(1);
    a.defineNome("Bob");
    a.calculaHE(0);
    a.calculaSalarioFinal();
    a.imprimeDados();
  }
}

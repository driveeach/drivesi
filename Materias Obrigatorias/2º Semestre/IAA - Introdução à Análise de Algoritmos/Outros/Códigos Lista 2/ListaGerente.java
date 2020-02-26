class ListaGerente
{
  Gerente [] gerentes = new Gerente[5];
  
  ListaGerente()
  {
    gerentes[1] = new Gerente(10);
    gerentes[1].defineNome("Jose");
    gerentes[1].calculaHE(10);
    gerentes[1].calculaSalarioFinal();
    gerentes[2] = new Gerente(20);
    gerentes[2].defineNome("Joao");
    gerentes[2].calculaHE(20);
    gerentes[2].calculaSalarioFinal();
    gerentes[3] = new Gerente(30);
    gerentes[3].defineNome("MAria");
    gerentes[3].calculaHE(30);
    gerentes[3].calculaSalarioFinal();
    gerentes[4] = new Gerente(40);
    gerentes[4].defineNome("CArlos");
    gerentes[4].calculaHE(40);
    gerentes[4].calculaSalarioFinal();
    gerentes[0] = new Gerente(100);
    gerentes[0].defineNome("Santos");
    gerentes[0].calculaHE(100);
    gerentes[0].calculaSalarioFinal();
  }
  
  void imprimeLista()
  {    
    for(int i=0; i < gerentes.length ; i++){
      gerentes[i].imprimeDados();}
    System.out.println();
  }
  
  void ordenaLista(ComparaLista c)
  {
    int ivet, isubv;
    Gerente gerenteAInserir;
    for (ivet=1; ivet < gerentes.length; ivet++)
    {
      gerenteAInserir = gerentes[ivet];
      isubv = ivet;
      while ((isubv > 0) && (c.ehMenor(gerenteAInserir, gerentes [isubv -1]) ))
      {
        gerentes[isubv] = gerentes[isubv - 1];
        isubv--;
      }
      gerentes [isubv] = gerenteAInserir;
    }
  }
  
  public static void main (String [] args)
  {
    ListaGerente prime = new ListaGerente();
    
    System.out.println("Lista desordenada");
    prime.imprimeLista();
    
    System.out.println("Lista por Nome");
    ComparaLista nome = new ComparaNome();
    prime.ordenaLista (nome);
    prime.imprimeLista();
    
    System.out.println("Lista por Salario");
    prime.ordenaLista(new ComparaSalario());
    prime.imprimeLista();
  } 
}
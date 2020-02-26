interface ComparaLista
{
  boolean ehMenor(Gerente a, Gerente b);
}

class ComparaSalario implements ComparaLista
{
  public boolean ehMenor(Gerente a, Gerente b){ return(a.calculaSalarioFinal() < b.calculaSalarioFinal());}
}

class ComparaNome implements ComparaLista
{
  public boolean ehMenor(Gerente a, Gerente b){ return(a.obtemName().compareTo(b.obtemName()) < 0);}
}

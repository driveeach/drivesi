interface ComparadorDeLivro
{
  int livroMenor(Livro livro1, Livro livro2);
}

class ComparaAno implements ComparadorDeLivro
{
  public int livroMenor(Livro livro1, Livro livro2){
    if(livro1.getAno() > livro2.getAno())
      return 1;
    else if (livro1.getAno() < livro2.getAno())
      return -1;
    else
      return 0;
  }
}

class ComparaTitulo implements ComparadorDeLivro
{
  public int livroMenor(Livro livro1, Livro livro2){
    if(livro1.getTitulo().compareTo(livro2.getTitulo()) > 0)
      return 1;
    else if(livro1.getTitulo().compareTo(livro2.getTitulo()) < 0)
      return -1;
    else
      return 0;
  }
}

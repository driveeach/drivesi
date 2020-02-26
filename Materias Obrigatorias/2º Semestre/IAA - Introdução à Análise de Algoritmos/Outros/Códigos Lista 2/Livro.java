class Livro
{
  private String titulo;
  private int ano;
  private int tipo; // valor de 1 a 20 que classifica a categoria de cada livro
  
  void setTitulo (String t) {titulo= t;}
  void setAno (int a) {ano = a;}
  void setTipo (int ti) {tipo = ti;}
  String getTitulo () {return titulo;}
  int getAno () { return ano;}
  int getTipo () { return tipo;}
  void imprime() { System.out.println("Tipo: " + tipo + " / Ano: " + ano + " / Titulo: " + titulo);}
}
public class TestaCatalogo {

 public static boolean ehIgual(Midia[] a, Midia[] b) {
  boolean ehIgual = a.length == b.length;

  for (int i = 0; i < a.length && ehIgual; i++) {
   ehIgual = (a[i] == b[i]);
  }
  return ehIgual;
 }

 public static void realizaTestes() {

  final CD cd1 = new CD("X & Y", 2005, "Cold Play");
  cd1.adicionaFaixa("Square One", 287); // 4:47
  cd1.adicionaFaixa("What If", 297); // 4:57
  cd1.adicionaFaixa("White Shadows", 328); // 5:28
  cd1.adicionaFaixa("Fix You", 294); // 4:54
  cd1.adicionaFaixa("Talk", 311); // 5:11
  cd1.adicionaFaixa("X&Y", 274); // 4:34

  final DVD dvd1 = new DVD("O Senhor dos Anéis - A Sociedade dos Anel", 2001, "Peter Jacson");
  dvd1.adicionaArtista("Elijah Wood", "Frodo Baggins");
  dvd1.adicionaArtista("Viggo Mortensen", "Aragorn");
  dvd1.adicionaArtista("Orlando Bloom", "Legolas Greenleaf");
  dvd1.adicionaArtista("Christopher Lee", "Saruman");
  dvd1.adicionaArtista("Ian McKellen", "Gandalf");

  final Jogo jogo1 = new Jogo("Need For Speed - Underground II", 2011, "Corrida");
  final Jogo jogo3 = new Jogo("Zulu", 2005, "Corrida");

  final CD cd2 = new CD("Bachianas Brasileiras No.2", 2004,
    "Orquestra de Câmara da Universidade de São Paulo");
  cd2.adicionaFaixa("(Prelúdio) O Canto do Capadócio", 512); // 4:32
  cd2.adicionaFaixa("(�?ria) O Canto da Nossa Terra", 389); // 6:29
  cd2.adicionaFaixa("(Dança) Lembranca do Sertão", 324); // 5:24
  cd2.adicionaFaixa("(Tocata) O Trenzinho do Caipira", 284); // 4:44

  final DVD dvd2 = new DVD("Matrix", 1999, "Andy & Larry Wachoski");
  dvd2.adicionaArtista("Keanu Reeves", "Neo");
  dvd2.adicionaArtista("Laurence Fishburne", "Morpheus");
  dvd2.adicionaArtista("Carrie-Anne Moss", "Trinity");
  dvd2.adicionaArtista("Hugo Weaving", "Agente Smith");
  dvd2.adicionaArtista("Gloria Foster", "Óraculo");

  final Jogo jogo2 = new Jogo("Fifa 2008", 2015, "Esporte");

  /**
   * Testa se o assert está habilitado. No Dr. Java o assert é habilitado
   * por padrão. Se o seu teste imprimir "Assertions desabilitados. O
   * programa não foi testado!", algo está errado. Avise o professor.
   */
  int a = 0;
  assert (a++ == 0);
  if (a == 0) {
   System.out.println("Assertions desabilitados. O programa não foi testado!");
   System.exit(-1);
  }

  // Criar uma classe cadastro e tenta buscar uma midia na lista vazia
  Catalogo catalogo = new Catalogo(6);

  assert (catalogo.obtemMidia("Senhor dos Aneis") == null);

  // adiciona 6 midias

  assert (catalogo.adicionaMidia(cd1));
  assert (catalogo.adicionaMidia(dvd1));
  assert (catalogo.adicionaMidia(jogo1));
  assert (catalogo.adicionaMidia(jogo3));
  assert (catalogo.adicionaMidia(dvd2));
  assert (catalogo.adicionaMidia(jogo2));

  // Tenta recuperar cadastros

  assert (catalogo.obtemMidia("Fifa 2008") == jogo2);
  assert (catalogo.obtemMidia("Matrix") == dvd2);
  assert (catalogo.obtemMidia("X & Y") == cd1);
  assert (catalogo.obtemMidia("Need For Speed - Underground II") == jogo1);
  assert (catalogo.obtemMidia("O Senhor dos Anéis - A Sociedade dos Anel") == dvd1);

  // Catalogo cheio
  assert (catalogo.adicionaMidia(jogo2) == false);

  // Verificando lista (todos)

  assert (catalogo.quantidadeDeMidias() == 6);
  
  assert (catalogo.quantidadeDeMidias() == catalogo.quantidadeMaximaDeMidias());



  // Verificando lista (seleciona pelo tipo - CD de música - implementada pela classe CD)





  // Verificando lista (seleciona pelo tipo - DVD de filme - implementada pela classe DVD)

  assert (catalogo.quantidadeDeDVDs() == 2);
  
  assert (ehIgual(catalogo.listaPorTipo(2), new Midia[] { dvd2, dvd1 }));

  // Verificando lista (seleciona pelo tipo - Jogo Eletrônico - implementada pela classe Jogo)

  assert (catalogo.quantidadeDeJogos() == 3);

  assert (ehIgual(catalogo.listaPorTipo(3), new Midia[] { jogo3, jogo1, jogo2 }));
  
  catalogo.imprimeListaPorTipo(3);
 }

 public static void main(String[] args) {
  realizaTestes();
 }
}

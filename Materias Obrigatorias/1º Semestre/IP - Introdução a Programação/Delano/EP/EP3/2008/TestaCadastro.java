public class TestaCadastro {
  
 void realizaTestes() {

  /**
   * Testa se o assert está habilitado. No Dr. Java o assert é habilitado por padrão.
   * Se o seu teste imprimir "Assertions desabilitados. O programa não foi testado!",
   * algo está errado. Avise o professor. 
   */
  int a = 0;
  assert ( a++ == 0 );
  if (a == 0) {
   System.out.println("Assertions desabilitados. O programa não foi testado!");
   System.exit(-1);
  }

  // Criar uma classe cadastro e tenta buscar um nome na lista vazia
  CadastroDeClientes cadastro = new CadastroDeClientes();  
  assert( cadastro.obtemTelefone("Andre").equals("") );
  
  // Adiciona 5 cadastros
  assert ( cadastro.adicionaCliente("Bete", "0299-9999") );
  assert ( cadastro.adicionaCliente("Jose", "0399-9999") );
  assert ( cadastro.adicionaCliente("Andre", "0499-9999") );
  assert ( cadastro.adicionaCliente("Marcio", "0599-9999") );
  assert ( cadastro.adicionaCliente("Alfredo","0199-9999") );
  
  // Tenta recuperar cadastros
  assert( cadastro.obtemTelefone("Andre").equals( "0499-9999" ) );
  assert( cadastro.obtemTelefone("Marcio").equals( "0599-9999" ) );
  assert( cadastro.obtemTelefone("Bete").equals( "0299-9999" ) );

  // Adiciona outros 5 cadastros
  assert ( cadastro.adicionaCliente("Roberta","0699-9999") );
  assert ( cadastro.adicionaCliente("Josue", "0799-9999") );
  assert ( cadastro.adicionaCliente("Wilma", "0899-9999") );
  assert ( cadastro.adicionaCliente("Carlos", "0999-9999") );
  assert ( cadastro.adicionaCliente("Josias", "1099-9999") );

  // Tenta recuperar cadastros
  assert( cadastro.obtemTelefone("Wilma").equals( "0899-9999" ) );
  assert( cadastro.obtemTelefone("Jose").equals( "0399-9999" ) );
  assert( cadastro.obtemTelefone("Alfredo").equals( "0199-9999" ) );
  assert( cadastro.obtemTelefone("Everaldo").equals("") );
  
  // Lista está cheia
  assert ( cadastro.adicionaCliente("Marcela","1199-9999") == false );
  
  CadastroDeClientes cadastro2 = new CadastroDeClientes();

  // Adiciona 5 cadastros
  assert ( cadastro2.adicionaCliente("Gilberto", "1299-9999") );
  assert ( cadastro2.adicionaCliente("Madalena", "1399-9999") );
  assert ( cadastro2.adicionaCliente("Rogerio", "1499-9999") );
  assert ( cadastro2.adicionaCliente("Vinicius", "1599-9999") );
  assert ( cadastro2.adicionaCliente("Gabriela", "1699-9999") );

  // Tenta recuperar cadastros
  assert( cadastro2.obtemTelefone("Gilberto").equals( "1299-9999" ) );
  assert( cadastro2.obtemTelefone("Vinicius").equals( "1599-9999" ) );
  assert( cadastro2.obtemTelefone("Carlos").equals("") );

  // Junta os cadastros
  cadastro.juntaCadastros(cadastro2);
  
  // Recupera cadastros da lista junta
  assert( cadastro.obtemTelefone("Gilberto").equals( "1299-9999" ) );
  assert( cadastro.obtemTelefone("Vinicius").equals( "1599-9999" ) );
  assert( cadastro.obtemTelefone("Carlos").equals("0999-9999") );  
  assert( cadastro.obtemTelefone("Wilma").equals( "0899-9999" ) );
  assert( cadastro.obtemTelefone("Jose").equals( "0399-9999" ) );
  assert( cadastro.obtemTelefone("Alfredo").equals( "0199-9999" ) );
  assert( cadastro.obtemTelefone("Everaldo").equals("") );
  assert( cadastro.obtemTelefone("Andre").equals( "0499-9999" ) );
  assert( cadastro.obtemTelefone("Marcio").equals( "0599-9999" ) );
  assert( cadastro.obtemTelefone("Bete").equals( "0299-9999" ) );
 } 
}

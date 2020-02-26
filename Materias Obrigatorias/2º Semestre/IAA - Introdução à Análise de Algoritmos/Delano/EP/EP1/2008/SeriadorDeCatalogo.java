import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class SeriadorDeCatalogo {

 public void gravaCatalogo(Catalogo catalogoMuseu, String nome) {
  try {
   ObjectOutputStream saida = new ObjectOutputStream(
     new FileOutputStream(nome));
   saida.writeObject(catalogoMuseu);
   saida.close();
  } catch (IOException ioe) {
   System.err.println("Erro ao gravar '" + nome + "'.");
  }
 }

 public Catalogo carregaCatalogo(String nome) {
  Catalogo catalogo = null;
  try {
   ObjectInputStream entrada = new ObjectInputStream(
     new FileInputStream(nome));
   catalogo = (Catalogo) entrada.readObject();
   entrada.close();
  } catch (FileNotFoundException fnfe) {
   System.err.println("Arquivo nao encontrado (" + nome + ").");
  } catch (ClassNotFoundException cnfe) {
   System.err.println("Classe 'Catalogo' nao encontrada.");
  } catch (IOException ioe) {
   System.err.println("Erro ao ler '" + nome + "'.");
  }
  return catalogo;
 }
}

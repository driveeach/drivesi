package cap6;
import java.io.*;
import cap4.MeuItem; /*-- vide Programa@{\it ~\ref{prog:tipoitem}@ --*/
public class CriaArvoreB {
  public static void main (String[] args) throws Exception {
    ArvoreB dicionario = new ArvoreB (2);
    BufferedReader in = new BufferedReader (
                        new InputStreamReader (System.in));
    System.out.println ("Criacao da arvore"); 
    int chave = Integer.parseInt (in.readLine());
    while (chave > 0) {
      MeuItem item = new MeuItem (chave);
      dicionario.insere (item);
      dicionario.imprime ();
      chave = Integer.parseInt (in.readLine());
    }

    System.out.println ("\nPesquisando chaves");
    chave = Integer.parseInt (in.readLine());
    while (chave > 0) {
      MeuItem item = new MeuItem (chave);
      item = (MeuItem)dicionario.pesquisa (item);
      if (item == null) System.out.println ("Item nao encontrado");
      else System.out.println ("Item encontrado");
      chave = Integer.parseInt (in.readLine());
    }

    System.out.println ("\nRemovendo algumas chaves");
    chave = Integer.parseInt (in.readLine());
    while (chave > 0) {
      MeuItem item = new MeuItem (chave);
      dicionario.retira (item);
      dicionario.imprime ();
      chave = Integer.parseInt (in.readLine());
    }
  }
}

package cap5;

import java.io.BufferedReader;
import cap5.listaenc.TabelaHash;
import java.io.InputStreamReader;

public class TestaTabelaHash {
  private static BufferedReader in = new BufferedReader (
                                     new InputStreamReader (System.in));
  public static void main (String[] args) throws Exception {
    TabelaHash tabela = new TabelaHash (7, 7);
    System.out.println ("Inserindo algumas chaves:");
    System.out.print ("  Chave:");
    String chave = in.readLine ();
    while (!chave.equals ("aaaaaa")) {
      tabela.insere (chave, chave);
      System.out.print ("  Chave:");
      chave = in.readLine ();
    }
    System.out.println ("Tabela apos insercao:");
    tabela.imprime ();

    System.out.print ("Pesquisar:");
    chave = in.readLine ();
    while (!chave.equals ("aaaaaa")) {
      Object obj = tabela.pesquisa (chave);
      if (obj == null)
        System.out.println ("pesquisa sem sucesso");
      else System.out.println ("sucesso");
      System.out.print ("Pesquisar:");
      chave = in.readLine ();
    }

    System.out.println ("Retirar seguintes chaves:");
    System.out.print ("  Chave:");
    chave = in.readLine ();
    while (!chave.equals ("aaaaaa")) {
      tabela.retira (chave);
      System.out.print ("  Chave:");
      chave = in.readLine ();
    }

    System.out.println ("Tabela apos retiradas:");
    tabela.imprime ();

    System.out.print ("Pesquisar:");
    chave = in.readLine ();
    while (!chave.equals ("aaaaaa")) {
      Object obj = tabela.pesquisa (chave);
      if (obj == null)
        System.out.println ("pesquisa sem sucesso");
      else System.out.println ("sucesso");
      System.out.print ("Pesquisar:");
      chave = in.readLine ();
    }

    System.out.println ("Inserir de novo os elementos seguintes:");
    System.out.print ("  Chave:");
    chave = in.readLine ();
    while (!chave.equals ("aaaaaa")) {
      tabela.insere (chave, chave);
      System.out.print ("  Chave:");
      chave = in.readLine ();
    }

    System.out.println ("Tabela apos novas insercoes:\n");
    tabela.imprime ();
  }
}

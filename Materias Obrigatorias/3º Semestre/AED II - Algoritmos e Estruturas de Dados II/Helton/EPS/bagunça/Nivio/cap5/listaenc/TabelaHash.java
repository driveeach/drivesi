package cap5.listaenc;
import cap3.autoreferencia.Lista; // @{\it vide Programa~\ref{Fig2.1.5}}@
public class TabelaHash {
  private static class Celula {
    String chave;
    Object item;
    public Celula (String chave, Object item) {
      this.chave = chave; this.item = item;
    }
    public boolean equals (Object obj) {
      Celula cel = (Celula)obj;
      return chave.equals (cel.chave);
    }
    public String toString () {
      return "  " + item.toString ();
    }
  }
  
  private int M; // @{\it tamanho da tabela}@
  private Lista tabela[];
  private int pesos[];

private int[] geraPesos (int n) {
  int p[] = new int[n];
  java.util.Random rand = new java.util.Random ();
  for (int i = 0; i < n; i++) p[i] = rand.nextInt(M) + 1;
  return p;
}

  private int h (String chave, int[] pesos) {
    int soma = 0;
    for (int i = 0; i < chave.length(); i++) 
      soma = soma + ((int)chave.charAt (i)) * pesos[i]; 
    return soma % this.M;
  }
  
  public TabelaHash (int m, int maxTamChave) {
    this.M = m; this.tabela = new Lista[this.M];
    for (int i = 0; i < this.M; i++) this.tabela[i] = new Lista ();
    this.pesos = this.geraPesos (maxTamChave);
  }  
   
  public Object pesquisa (String chave) {
    int i = this.h (chave, this.pesos);
    if (this.tabela[i].vazia()) return null; // @{\it pesquisa sem sucesso}@
    else {
      Celula cel=(Celula)this.tabela[i].pesquisa(new Celula(chave,null));
      if (cel == null) return null; // @{\it pesquisa sem sucesso}@
      else return cel.item;
    }
  }
  
  public void insere (String chave, Object item) {
    if (this.pesquisa (chave) == null) {
      int i = this.h (chave, this.pesos);
      this.tabela[i].insere (new Celula (chave, item));
    }
    else System.out.println ("Registro ja esta presente");    
  }
  
  public void retira (String chave) throws Exception {
    int i = this.h (chave, this.pesos);
    Celula cel = (Celula)this.tabela[i].retira (new Celula (chave,null));
    if (cel == null) System.out.println ("Registro nao esta presente");    
  }
  
  public void imprime () {
    for (int i = 0; i < this.M; i++) {
      if (!this.tabela[i].vazia ()) {
        System.out.println ("Entrada: " + i); 
        this.tabela[i].imprime ();
      }
    }
  }
}

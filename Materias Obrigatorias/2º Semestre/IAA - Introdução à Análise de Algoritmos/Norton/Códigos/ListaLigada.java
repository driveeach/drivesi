/*
 Implementa uma lista ligada. Modificadores de acesso foram simplificados. Não faça realmente assim. Deixe o mais que der private, usando getters e setters.
*/
public class ListaLigada {

 /*
  Nó da lista
 */
 private class No {
  /* Campo chave do nó */
  int chave = -1;
  /* Campo conteudo do nó */
  String conteudo;
  /* Próximo nó da lista */
  No prox;
  
  No (int chave, String conteudo) {
   this.chave = chave;
   this.conteudo = conteudo;
  }
 }

 /* Campos da lista */
 
 /* Nó cabeça */
 No cabeca;

 /* Adiciona elemento no início da lista */
 void adiciona(int chave, String conteudo) {
  No elemento = new No(chave, conteudo);
  elemento.prox = this.cabeca;
  this.cabeca = elemento;
 }
 
 /* Busca um elemento na lista. Retorna sua posição, ou -1 se não encontrar */
 int buscaElemento(int chave) {
  No noAtual = this.cabeca;
  int pos = 0;
  while (noAtual != null) {
   if (noAtual.chave == chave)
    return(pos);
   noAtual = noAtual.prox;
   pos++;
  }
  return(-1);
 }
 
 /* Remove um elemento da lista */
 boolean removeElemento(int chave) {
  // testo a se há cabeça
  if (this.cabeca == null) return(false);
  
  // vejo se não é a cabeça
  if (this.cabeca.chave == chave) {
   this.cabeca = this.cabeca.prox;
   return(true);
  }
  
  // busco no resto da lista
  No noAnterior = this.cabeca;
  No noAtual = this.cabeca.prox;
  while (noAtual != null) {
   if (noAtual.chave == chave) {
    noAnterior.prox = noAtual.prox;
    return(true);
   }
   noAnterior = noAtual;
   noAtual = noAtual.prox;
  }
  return(false);
 }
 
 /* Imprime todos os elementos da lista */
 void imprimeLista() {
  No noAtual = this.cabeca;
  while (noAtual != null) {
   System.out.print("["+noAtual.chave+","+noAtual.conteudo+"] ");
   noAtual = noAtual.prox;
  }
  System.out.println();
 }
 
 
 /* para testes... */
 public static void main(String[] args) {
  // crio a lista
  ListaLigada l = new ListaLigada();
  
  // adiciono elementos
  l.adiciona(0,"Oba");
  l.adiciona(1,"Aba");
  l.adiciona(2,"Eba");
  l.adiciona(3,"Oba");
  l.adiciona(4,"Seu");
  l.adiciona(5,"Meu");
  l.adiciona(6,"Teu");
  l.adiciona(7,"Sua");
  l.adiciona(8,"Tua");
  l.adiciona(9,"Elo");
  l.adiciona(10,"Luz");
  
  // leio a lista
  l.imprimeLista();
  
  // note que os últimos ficam primeiro na lista
  System.out.println("Posicao do 12 = "+l.buscaElemento(12));
  System.out.println("Posicao do 0 = "+l.buscaElemento(0));
  System.out.println("Posicao do 10 = "+l.buscaElemento(10));
  System.out.println("Posicao do -1 = "+l.buscaElemento(-1));
  System.out.println("Posicao do 6 = "+l.buscaElemento(6));
  
  // removendo alguns
  System.out.println(l.removeElemento(6));
  l.imprimeLista();
 }
}

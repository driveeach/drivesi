/** Lista de Peças.
  * tem tres aplicações distintas: Mao, Mesa e Monte.
  * Pode ser melhor criar uma superclasse e três subclasses.
*/
public class ListaDePecas {
    /** armazena as peças */
    Peca[] a;
    /** quantas peças tem armazenadas. */
    int tamanho;

    // construtores
    /** cria lista vazia */
    ListaDePecas () {
        a=null;
        tamanho=0;
    }
    /** cria lista com m espaços */
    ListaDePecas (int m) {
        a=new Peca[m];
        tamanho=0;
    }
    void poe (Peca p) {
        a[tamanho]=p;
        tamanho++;
    }
    Peca tira (int i) {
        if ((i<0) || (i>=tamanho)) {
            System.out.println ("Peca.java: peca inexistente:" + i);
            return null;
        }
        Peca p=a[i];
        tamanho--;
        a[i]=a[tamanho];
        return p;
    }
    /** imprime a lista de peças */
    void print () {
        for (int k=0;k<tamanho;k++) {
            a[k].print();
        }
    }
    public static void main (String[] args) {
        Peca p;
        ListaDePecas lp = new ListaDePecas (28);
        for (int i=0;i<6;i++) {
           p=new Peca();
           p.a=i;
           p.b=i;
           lp.poe(p);
         }
         lp.print();
    }











}



public class Lista {
  Pessoa [] membrosDaUSP;
  int posLivre = 0;
  
  Lista (int tamanho) {
    membrosDaUSP = new Pessoa [ tamanho ];
  }
  
  void inserePessoaNaLista(Pessoa p) {
    if(posLivre < membrosDaUSP.length) { 
      membrosDaUSP[posLivre] = p;
      ++posLivre;
    }
    else {
      System.out.println("Lista Completa.");    
    }
  }
  
  void listaTodos() {
    int i;
    for(i = 0; i < posLivre; i++)
      membrosDaUSP[i].imprimeDados();
  }
  
  public static void main(String args[]) {
   Lista listaPessoas = new Lista(10);
   Pessoa p = new Pessoa("Joao da Silva",'M',"XXX", "YYY",1987);
   listaPessoas.inserePessoaNaLista(p);
   p = new Aluno("Maria da Silva",'F',"XXXZ", "YYYZ",1989,"Sistemas de Informacao", 2005);
   listaPessoas.inserePessoaNaLista(p);
   listaPessoas.inserePessoaNaLista(new ProfessorDoutor("Alan Turing",'M',"XXX", "YYY",1910,
                                                        "EACH",2005,"MIT", 2001));
   listaPessoas.listaTodos();
  }
}
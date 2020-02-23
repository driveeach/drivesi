class Quatro {
  public static void ordenaCodigoSelecaoDireta (Cliente [] clientes) {
    for (int ult = clientes.length-1; ult > 0; ult--){
      for (int i = 0; i<ult; i++) {
        for (int j = 0; j<clientes[i].nome.length(); j++) {
          if (clientes[i].nome.charAt(j) > clientes[i+1].nome.charAt(j)) {
            Cliente aux = clientes[i];
            clientes[i] = clientes[i+1];
            clientes[i+1] = aux;
            break;
          }
        }
      }
    }
     for (int i = 0; i<clientes.length; i++){
     System.out.print(clientes[i].nome +"\t"); 
    }
  }
  public static void ordenaNomeInsercaoDireta (Cliente [] clientes) {
    for (int ult = clientes.length-1; ult > 0; ult--){
      for (int i = 0; i<ult; i++) {
        if (clientes[i].codigo > clientes[i+1].codigo) {
          Cliente aux = clientes[i];
          clientes[i] = clientes[i+1];
          clientes[i+1] = aux;
        }
      }
    }
    for (int i = 0; i<clientes.length; i++){
     System.out.print(clientes[i].codigo+"\t"); 
    }
  }
  public static void main (String [] args) {
   CarteiraDeClientes a = new CarteiraDeClientes ();
   a.clientes = new Cliente [5];
   for (int i = 0; i<a.clientes.length; i++) {
     Cliente b = new Cliente ();
     b.codigo = (int)(i+ Math.random()*10);
     b.nome = "a"+(int)(Math.random()*10);
     b.salario = Math.random();
     a.clientes[i] = b;
   }
   System.out.println ("Array original:");
   for (int i = 0; i<a.clientes.length; i++){
    System.out.print (a.clientes[i].codigo+"\t" +a.clientes[i].nome+"\t"); 
   }
   System.out.println ("\nArray ordenado por codigo:");
   ordenaCodigoSelecaoDireta (a.clientes);
   System.out.println ("\nArray ordenado por nome:");
   ordenaNomeInsercaoDireta(a.clientes);
  }
 }

class Cliente {
    int codigo;
    String nome;
    double salario;
}

class CarteiraDeClientes {
    Cliente [] clientes;
}



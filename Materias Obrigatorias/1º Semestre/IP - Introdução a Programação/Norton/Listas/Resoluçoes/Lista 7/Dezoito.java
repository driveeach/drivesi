import java.util.Scanner;
class Dezoito {
  public static void main (String[]args) {
    Scanner sc = new Scanner (System.in);
    int tamanho = 10;
    int aux = 0;
    int [] lista = new int [tamanho];
    int contador = lista.length-1;
    do {
      if (contador>=0) {
        System.out.println ("Digite o número");
        aux = sc.nextInt();
        lista[contador] = aux;
        contador--;
      }
      else { 
        System.out.println ("Os inteiros adicionais serão ignorados");
        break;
      }
    }
    while (aux!=-1);
    for (int c : lista) {
      if (c!=0 && c!=-1) System.out.println (c);
    }
  }
}
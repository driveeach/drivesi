class Semana {
  
  /**
   * Para qualquer cálculo com datas é essencial que se disponha do "Dia do 
   * Século" para a(s) data(s) em questão. O Dia do século é o número de dias
   * decorridos desde o inicio do século (1 de janeiro de 1901) até a data em
   * questão. Se usa como referência 1901 porque a maioria das datas com as 
   * quais se trabalha ainda são do século passado. Lá por 2040 ou mais poderá 
   * se mudar para 2001.
   */
  int diaDoSeculo(int dia, int mes, int ano) {
    return 
      (ano - 1901) * 365 +
      (ano - 1901) / 4 +
      dia +
      (mes - 1) * 31 -
      ((mes * 4 + 23) / 10) * ((mes + 12) / 15) +
      ((4 - ano % 4) / 4) * ((mes + 12) / 15);
  }
  
  /**
   * Para saber o Dia da Semana basta dividir por 7 e comparar o resto com a tabela abaixo
   * 0  Segunda
   * 1  Terça
   * 2  Quarta
   * 3  Quinta
   * 4  Sexta
   * 5  Sábado
   * 6  Domingo
   */
  void printDiaDaSemana(int dia, int mes, int ano) {
    int valor = diaDoSeculo(dia, mes, ano);
    System.out.print(dia + "/" + mes + "/" + ano + " => ");
    switch(valor % 7) {
      case 0: {
        System.out.println("Segunda");
        break;
      }
      case 1: {
        System.out.println("Terça");
        break;
      }
      case 2: {
        System.out.println("Quarta");
        break;
      }
      case 3: {
        System.out.println("Quinta");
        break;
      }
      case 4: {
        System.out.println("Sexta");
        break;
      }
      case 5: {
        System.out.println("Sábado");
        break;
      }
      case 6: {
        System.out.println("Domingo");
        break;
      }
    }
  }
  
  void printDiaDaSemana2(int dia, int mes, int ano) {
    int valor = diaDoSeculo(dia, mes, ano) % 7;
    System.out.print(dia + "/" + mes + "/" + ano + " => ");
    
    if (valor == 0) {
      System.out.println("Segunda");
    } else if (valor == 1) {
      System.out.println("Terça");
    } else if (valor == 2) {
      System.out.println("Quarta");
    } else if (valor == 3) {
      System.out.println("Quinta");
    } else if (valor == 4) {
      System.out.println("Sexta");
    } else if (valor == 5) {
      System.out.println("Sábado");
    } else if (valor == 6) {
      System.out.println("Domingo");
    }
  }
}
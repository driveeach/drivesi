class TemperaturasDoMes {

  double t1, t2, t3, t4, t5, t6, t7, t9, t10, t11, t12, t13, t14, t15, t16, t17, t19, t20, 
    t21, t22, t23, t24, t25, t26, t27, t29, t30, t31;
  // etc.
}


// Array -- estrutura de dados que permite guardar uma sequencia de valores (numeros, caracteres, ou
// objetos quaisquer) de forma unica e organizada.

class TemperaturaDoMes2 {
  double [] temperaturas = new double [31];
}

// double [] temperaturas; --> define um array que ira conter valores do tipo double. Por enquanto, vazio.

// temperaturas = new double [31]; --> especifica que o array guardara exatamente 31 valores do tipo double.
// Sera reservada a memoria necessaria para guardar esses 31 valores.

class BrincadeirasComArrays {
  String [] diasDaSemana = new String[7];
  
  int [] quadrados = new int [10];
  
  int [] primos = {1, 2, 3,  5, 7, 11, 13, 17, 19, 23};
  
  String [] planetas = {"Mercurio", "Venus", "Terra", "Marte", "Jupiter", "Saturno", "Urano", "Netuno", "Plutao"};
  
  String [] disciplinasEspecificas;
    
  void defineDiasDaSemana () {
    diasDaSemana[0]= "domingo";
    diasDaSemana[1]= "segunda-feira";
    diasDaSemana[2]= "terca-feira";
    diasDaSemana[3]= "quarta-feira";
    diasDaSemana[4]= "quinta-feira";
    diasDaSemana[5]= "sexta-feira";
    diasDaSemana[6]= "sabado";   
  }

  void calculaQuadrados () {
    int i = 0;
    while (i < 10) {
      quadrados[i] = i*i;
      i++;
    }
  }
  
  void listaDiasDaSemana () {
    int i = 0;
    while (i < 7) {
      System.out.println(diasDaSemana[i]);
      i++;
    }
  }
  
  // Array eh um tipo especial de objeto em Java. Todo array ja vem com um atributo pre-definido
  // chamado length e que contem o comprimento do array.
  
  void listaPlanetas() {
    int i = 0;
    while (i < planetas.length) {
      System.out.println(planetas[i]);
      i++;
    }
  }
 
  void listaQuadrados() {
    int i = 0;
    while (i < 10) {
      System.out.println(quadrados[i]);
      i++;
    }
  }
  
  void listaDisciplinasEspecificas() {
    int i = 0;
    while (i < disciplinasEspecificas.length)
    {
      System.out.println(disciplinasEspecificas[i]);
      i++;
    }
  }
  
  void imprimeArray (String [] array) {
    int i = 0;
    while (i < array.length) {
      System.out.println(array[i]);
      i++;
    }
  }
  
  // Para executar um programa na linha de comando ou com o "duplo click" com o mouse em cima do icone
  // do programa, eh necessario incluir um metodo "main" declarado da seguinte forma: "public static main()".
  // Variavel e metodo "static": variaveis e metodos da classe. Variavel e metodo "public": pode ser acessado
  // por outras classes e tambem por subclasses e outros pacotes; sem essa clausula "public", nao pode ser acessado por uma
  // subclasse,
  
  
  public static void main (String [] arg) {
    BrincadeirasComArrays b = new BrincadeirasComArrays();
    b.defineDiasDaSemana();
    b.calculaQuadrados();
    b.listaDiasDaSemana();
    b.listaQuadrados();
    b.imprimeArray(arg);
  }
}
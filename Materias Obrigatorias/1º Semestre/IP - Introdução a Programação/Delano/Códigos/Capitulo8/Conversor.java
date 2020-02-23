public class Conversor {
  
  double celsiusParaFahrenheit (double c) {
    return 9 * c / 5 + 32;
  }
  
  double fahrenheitParaCelsius (double f)
  {
    return 5 * (f - 32) / 9;
  }  
  
  /**
   * Imprime uma tabela de conversao Celsius => Fahrenheit
   */
  void imprimeTabelaCelsiusParaFahrenheit(double inicio, double fim, double incremento) {
    double celsius = inicio;
    double fahr;
    
    while (celsius <= fim) {
      fahr = celsiusParaFahrenheit(celsius);
      System.out.println(celsius + "C = " + fahr + "F");
      celsius = celsius + incremento;
    }
  }
}
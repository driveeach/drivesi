/**
 * Classe que converte de Celsius para Fahrenheit e Fahrenheit para Celsius (versão final)
 * 
 * @author Extraido do Livro: "Introdução à  Ciência da Computação com Java e Orientação a Objetos" - Capitulo 3
 */
public class Conversor4{
  
/**
 * Método que converte de graus Celsius para graus Fahrenheit
 * 
 * @param c - graus celsius a ser convertido
 */
 double celsiusFahrenheit(double c) {
     return 9 * c / 5 +   32;
 } 
 
/**
 * Método que converte de graus Fahrenheit para Celsius
 * 
 * @param f - graus fahrenheit a ser convertido
 */
 double fahrenheitCelsius(double f) {
     return 5 * (f - 32) / 9;
 } 
}
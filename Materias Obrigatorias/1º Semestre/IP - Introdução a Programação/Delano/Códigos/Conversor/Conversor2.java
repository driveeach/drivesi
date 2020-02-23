/**
 * Classe que converte de Celsius para Fahrenheit (versão 2)
 * <p>
 * Limitação: Apenas manipula números inteiros
 * <p>
 * Quando o computador opera com números inteiros, os números são truncados, ou
 * seja "30.3" se torna "30" e "30.9" também se torna 30.
 * 
 * @author Extraido do Livro: "Introdução à  Ciência da Computação com Java e Orientação a Objetos" - Capitulo 3
 */
public class Conversor2{
  
/**
 * Método que converte de graus Celsius para graus Fahrenheit
 * 
 * @param c - graus celsius a ser convertido
 */
  int celsiusFahrenheit(int c) {
    return 9 * c / 5 + 32;
  }
  
}

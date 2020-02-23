/**
 * Classe que representa retangulos (versão 1)
 * 
 * @author Extraido do Livro: "Introdução à  Ciência da Computação com Java e Orientação a Objetos" - Capitulo 5
 */
class Retangulo1 {
  
  int calculaArea(int lado1, int lado2) {
    return lado1 * lado2;
  }
  
  int calculaPerimetro(int lado1, int lado2) {
    return 2 * (lado1 + lado2);
  }
}
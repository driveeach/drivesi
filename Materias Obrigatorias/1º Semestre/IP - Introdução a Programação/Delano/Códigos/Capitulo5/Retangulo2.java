/**
 * Classe que representa retangulos (versão 2)
 * 
 * @author Extraido do Livro: "Introdução à  Ciência da Computação com Java e Orientação a Objetos" - Capitulo 5
 */
class Retangulo2 {
  int lado1;
  int lado2;
  
  int calculaArea() {
    return lado1 * lado2;
  }
  
  int calculaPerimetro() {
    return 2 * (lado1 + lado2);
  }
  
  void carregaLados(int l1, int l2) {
    lado1 = l1;
    lado2 = l2;
  }
}
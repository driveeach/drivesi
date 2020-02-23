/**
 * Classe que representa circulos (versão 2)
 * 
 * @author Extraido do Livro: "Introdução à  Ciência da Computação com Java e Orientação a Objetos" - Capitulo 5
 */
class Circulo2 {
  double raio;
  
  void carregaRaio(double r) {
    raio = r;
  }
  
  double calculaArea() {
    return 3.1415*raio*raio;
  }
  
  double calculaPerimetro() {
    return 3.1415*2.0*raio;
  }
}


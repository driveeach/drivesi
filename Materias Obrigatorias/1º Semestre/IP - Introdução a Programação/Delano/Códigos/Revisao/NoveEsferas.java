/* Revisão sobre comandos de seleção
 * NOVE (9) esferas têm aparência, tamanho e peso exatamente igual, exceto uma 
 * delas que tem um peso maior. Esta diferença é desconhecida e faz parte 
 * integrante do problema. Com apenas duas (2) pesagens, você terá de identificar 
 * a esfera diferente. Não é necessário identificar o valor da diferença.
 */ 
class NoveEsferas {
  
  int A, B, C, D, E, F, G, H, I;
  
  void carregaValores(int esfera) {
    A = B = C = D = E = F = G = H = I = 0;
    
    switch(esfera)  {
      case 1: { A = 1; break; }
      case 2: { B = 1; break; }
      case 3: { C = 1; break; }
      case 4: { D = 1; break; }
      case 5: { E = 1; break; }
      case 6: { F = 1; break; }
      case 7: { G = 1; break; }
      case 8: { H = 1; break; }
      case 9: { I = 1; break; }
    }
  }
  
  int pesa3x3(int e11, int e12, int e13, int e21, int e22, int e23) {
    return (e11 + e12 + e13) - (e21 + e22 + e23);
  }
  
  int pesa1x1(int e11, int e21) {
    return e11 - e21;
  }
  
  void resolveDesafio() {
    int pesagem1, pesagem2;
    
    pesagem1 = pesa3x3(A, B, C, G, H, I);
    switch (pesagem1) {
      case 1: {
        // A + B + C > G + H + I
        // A ou B ou C mais pesado
        pesagem2 = pesa1x1(A, C);
        switch(pesagem2) {
          case 1: {
            // A > C -> A mais pesado
            System.out.println("Esfera \"A\" mais pesada");
            break;
          }
          case 0: {
            // A = C -> B mais pesado
            System.out.println("Esfera \"B\" mais pesada");
            break;
          }
          case -1: {
            // A < C -> C mais pesado
            System.out.println("Esfera \"C\" mais pesada");
            break;
          }
        }
        break;
      }
      case 0: {
        // A + B + C = G + H + I
        // D ou E ou F mais pesado
        pesagem2 = pesa1x1(D, F);
        switch(pesagem2) {
          case 1: {
            // D > F -> D mais pesado
            System.out.println("Esfera \"D\" mais pesada");
            break;
          }
          case 0: {
            // D = F -> E mais pesado
            System.out.println("Esfera \"E\" mais pesada");
            break;
          }
          case -1: {
            // D < F -> F mais pesado
            System.out.println("Esfera \"F\" mais pesada");
            break;
          }
        }
        break;
      }
      case -1: {
        // A + B + C < G + H + I
        // G ou H ou I mais pesado
        pesagem2 = pesa1x1(G, I);
        switch(pesagem2) {
          case 1: {
            // G > I -> G mais pesado
            System.out.println("Esfera \"G\" mais pesada");
            break;
          }
          case 0: {
            // G = I -> H mais pesado
            System.out.println("Esfera \"H\" mais pesada");
            break;
          }
          case -1: {
            // G < I -> I mais pesado
            System.out.println("Esfera \"I\" mais pesada");
            break;
          }
        }
        break;
      }
    }
  }
  
  public static void main(String[] args) {
    NoveEsferas ne = new NoveEsferas();
    int i = 1;
    while (i <= 9) {
      ne.carregaValores(i++);
      ne.resolveDesafio();
    }
  }
}
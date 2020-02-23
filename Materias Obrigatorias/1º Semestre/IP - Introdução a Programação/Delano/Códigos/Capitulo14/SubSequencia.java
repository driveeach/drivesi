/**
 * Escrever um metodo que, para uma dada sequencia de valores inteiros de comprimento maior ou igual a 3, calcule o 
 * numero de triplas (subsequencias de comprimento 3) com valores iguais existentes na sequencia dada. Exemplo: Para a 
 * sequencia 4 2 15 15 15 3 7 7 7 7 2, o método deve retornar 3, pois a sequencia dada contém uma tripla com valores 15 
 * e duas (sobrepostas) com valores 7. 
 */

class SubSequencia {
  
  int nroSubsequenciasTam3(int[] sequencia) {
    int contador = 0;
    for (int i = 2; i < sequencia.length; i++) {
      if (sequencia[i] == sequencia[i-1] && sequencia[i-1] == sequencia[i-2]) {
        contador = contador + 1;
      }
    }
    return contador;
  }
  
  int nroSubsequencias(int[] sequencia, int k) {
    int contador = 0;
    for (int i = k - 1; i < sequencia.length; i++) {
      int j = 0;
      while (sequencia[i - j] == sequencia[i - j - 1] && j < k - 1) {
        j++;
      }
      if (j == k - 1) {
        contador = contador + 1;
      }
    }
    return contador;
  }
  
}
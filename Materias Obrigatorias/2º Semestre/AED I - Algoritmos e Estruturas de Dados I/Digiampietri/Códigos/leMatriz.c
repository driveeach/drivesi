// leMatriz.c
/* Este programa le uma matriz n por n e depois imprimi seu conteudo
   Modo de usar leMatriz.exe < matriz.txt
   O arquivo matriz.txt deve conter na primeira linha o valor n (numero de linhas e colunas da matriz)
   e nas linhas seguintes os valores dos elementos da matriz   */

#include <stdio.h>

int main(){
    
  int n; // numero de linhas e colunas da matriz
  scanf("%d", &n);
  int i,j; char ch;
  int A[n][n];
  for (i=0; i<n; i++){
    for (j=0; j<n; j++) scanf("%d", &A[i][j]);
    //do {scanf("%c", &ch);} while (ch!='\n'); // "limpa" os caracteres que estejam sobrando no final de cada linha (nao eh necessario caso o arquvio matriz.txt esteja bem definido)
  }
  
  // Imprime Matriz:
  for (i=0; i<n; i++){
    for (j=0; j<n; j++) printf("%d ", A[i][j]);
    printf("\n");
  }
    
  return 0;
}

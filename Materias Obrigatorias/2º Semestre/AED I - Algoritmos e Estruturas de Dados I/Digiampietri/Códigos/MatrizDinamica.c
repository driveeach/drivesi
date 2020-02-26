#include <stdio.h>



void imprimeMatriz(int ** MAT, int n){
  int i,j;
  for (i=0; i<n; i++){
    for (j=0; j<n; j++) printf("%d ", MAT[i][j]);
    printf("\n");
  }
}
      


int main(){
  
  int n; // numero de linhas e colunas da matriz
  scanf("%d", &n);
  int i,j; char ch;
  int **A = (int**)malloc(sizeof(int)*n);
  for (i=0; i<n; i++){
      A[i] = (int*)malloc(sizeof(int)*n);
  }

  for (i=0; i<n; i++){
    for (j=0; j<n; j++) scanf("%d", &A[i][j]);
    //do {scanf("%c", &ch);} while (ch!='\n');
    // "limpa" os caracteres que estejam sobrando no final de cada linha 
    //(nao eh necessario caso o arquivo matriz.txt esteja bem definido)
  }


  imprimeMatriz(A,n);
  printf("\n");
 
  // Imprime Matriz:
  for (i=0; i<n; i++){
    for (j=0; j<n; j++) printf("%d ", A[i][j]);
    printf("\n");
  }

  system("pause");
   
  return 0;
}

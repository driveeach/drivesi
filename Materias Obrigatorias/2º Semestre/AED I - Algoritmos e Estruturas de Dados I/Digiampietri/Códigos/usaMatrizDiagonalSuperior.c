/* Arquivo usaMatrizDiagonalSuperior.c */
#include <stdio.h>
#include "matrizDiagonalSuperior.c"

void LeMatriz(PontMDS mat) {
/* Inicializa e lê os elementos de 'a' */
  int lin,i;
  int val;
  scanf("%d\n",&lin);
  inicializarMatriz(lin,mat);
  i=0;
  do {
   scanf("%d",&val);
   i++;
   AtribuiMatriz(i, i, val, mat);
  } while (i<lin);
}

int main() {
  MDS a,b,*c;
  LeMatriz(&a);
  exibirMatriz(&a);
  printf("Eficiencia: %6.1f%%\n",eficiencia(a));
  LeMatriz(&b);
  exibirMatriz(&b);
  printf("Eficiencia: %6.1f%%\n",eficiencia(b));

  c = SomaMatriz(&a,&b);
  if (c==NULL) 
    printf("Soma de matrizes incompativeis\n");
  else {
    exibirMatriz(c);
    printf("Eficiencia: %6.1f%%\n",eficiencia(*c));
    liberarMatriz(c);
  }
  liberarMatriz(&a);
  liberarMatriz(&b);
  return 0;
}

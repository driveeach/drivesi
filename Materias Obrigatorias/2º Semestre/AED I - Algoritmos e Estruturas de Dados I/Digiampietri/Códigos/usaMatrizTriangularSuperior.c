/* Arquivo usaMatrizTriangularSuperior.c */
#include <stdio.h>
#include "matrizTriangularSuperior.c"

void LeMatriz(PontMTS mat) {
/* Inicializa e lê os elementos de 'a' */
  int lin,l,c;
  int val;
  scanf("%d\n",&lin);
  inicializarMatriz(lin,mat);
  c=0;
  l=0;
  do {
    l++;
    do {
     scanf("%d",&val);
     c++;
     AtribuiMatriz(l, c, val, mat);
    } while (c<lin);
    c=0;
  } while (l<lin);
}

int main() {
  MTS a,b,*c;
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

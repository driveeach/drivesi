/* Arquivo usaEsparsasLinhas.c */
#include <stdio.h>
#include "esparsasLinhas.c"

void LeMatriz(MATRIZ *a) {
/* Inicializa e lê os elementos de 'a' */
  int m,n,i,j;
  float x;
  scanf("%d %d\n",&m,&n);
  *a = inicializarMatriz(m,n);
  do {
   scanf("%d %d %f\n",&i,&j,&x);
   if (i!=0)
     AtribuiMatriz(a,i,j,x);
  } while (i!=0);
}

void EscreveMatriz(MATRIZ a) {
  int m,n,i,j;
  float x;
  OrdemMatriz(a,&m,&n);
  printf("%3d %3d\n",m,n);
  for (i=1;i<=m;i++)
    for (j=1;j<=n;j++) {
      x = ValorMatriz(a,i,j);
      if (x!=0.0)
        printf("%3d %3d %6.1f\n",i,j,x);
    }
      
}

float Eficiencia(MATRIZ a) {
/* Calcula a eficiência da representação esparsa em
   comparação com a usual, em percentagem */
  int m,n;
  OrdemMatriz(a,&m,&n);
  if (m*n!=0)
    return 100.0*(NumeroNos(a)*TamanhoNo())
                  /(m*n*sizeof(float));
  else
    return 0;
}

int main() {
  MATRIZ a,b,*c;
  LeMatriz(&a);
  EscreveMatriz(a);
  printf("Eficiencia: %6.1f%%\n",Eficiencia(a));
  LeMatriz(&b);
  EscreveMatriz(b);
  printf("Eficiencia: %6.1f%%\n",Eficiencia(b));

  c = SomaMatriz(a,b);
  if (c==NULL) 
    printf("Soma de matrizes incompativeis\n");
  else {
    EscreveMatriz(*c);
    printf("Eficiencia: %6.1f%%\n",Eficiencia(*c));
    LiberaMatriz(*c);
  }

  LiberaMatriz(a);
  LiberaMatriz(b);
  return 0;
}

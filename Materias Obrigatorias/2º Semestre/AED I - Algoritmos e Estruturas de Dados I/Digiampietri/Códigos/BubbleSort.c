#include <stdio.h>

#define numeroMaximoDeElementos 50

typedef struct
{
  int numeroDeElementos;
  int elemento[numeroMaximoDeElementos];
} meuVetor;

meuVetor ordenar(meuVetor pEntrada){
  int n = pEntrada.numeroDeElementos;
  int i,j;
  for (i=1;i<n;i++){
	for (j=1;j<n-i+1;j++){
  	  if (pEntrada.elemento[j-1] > pEntrada.elemento[j]){
	    int temp = pEntrada.elemento[j];
		pEntrada.elemento[j]=pEntrada.elemento[j-1];
		pEntrada.elemento[j-1]=temp;
	  }
	}
  }
  return pEntrada;
}

int main() {
  
  meuVetor entrada;
  entrada.elemento[0] = 2;
  entrada.elemento[1] = 4;
  entrada.elemento[2] = 1;
  entrada.elemento[3] = 5;
  entrada.elemento[4] = 3;
  entrada.numeroDeElementos = 5;
  		
  meuVetor resultado = ordenar(entrada);

  int i;
  for (i=0;i<entrada.numeroDeElementos;i++){
	printf("%d ", resultado.elemento[i]);
	
  }

  return 0;
}



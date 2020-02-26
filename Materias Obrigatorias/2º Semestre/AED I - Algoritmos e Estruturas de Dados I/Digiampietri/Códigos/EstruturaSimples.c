#include <stdio.h>
#define alturaMaxima 225

typedef struct
{
  int peso; // peso em quilogramas
  int altura; // altura em centimetros
} pesoAltura;

int main() {
  pesoAltura pessoa1;
  pessoa1.peso = 80;
  pessoa1.altura = 185;

  printf("Peso: %d, Altura %d. ", pessoa1.peso, pessoa1.altura);
  if (pessoa1.altura>alturaMaxima) {
    printf("Altura acima da maxima.\n");
  }
  else printf("Altura abaixo da maxima.\n");
 
  return 0;
}

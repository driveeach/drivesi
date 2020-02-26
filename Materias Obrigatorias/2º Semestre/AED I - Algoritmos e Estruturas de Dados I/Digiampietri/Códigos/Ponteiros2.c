#include <stdio.h>
#include <malloc.h>

typedef struct {
  int peso; // peso em quilogramas
  int altura; // altura em centimetros
} pesoAltura;

typedef pesoAltura* PONT;

void atualizaEImprimePeso1(int pesoEntrada){
  int pesoAntigo = pesoEntrada;
  int pesoNovo = pesoAntigo - 5;
  pesoEntrada = pesoNovo;
  printf("   Peso antigo: %d, Peso novo %d.\n", pesoAntigo, pesoNovo);
}

void atualizaEImprimePeso2(PONT ponteiro){
  int pesoAntigo = ponteiro->peso;
  int pesoNovo = pesoAntigo - 5;
  ponteiro->peso = pesoNovo;
  printf("   Peso antigo: %d, Peso novo %d.\n", pesoAntigo, pesoNovo);
}

void atualizaEImprimePeso3(pesoAltura entrada){
  int pesoAntigo = entrada.peso;
  int pesoNovo = pesoAntigo - 5;
  entrada.peso = pesoNovo;
  printf("   Peso antigo: %d, Peso novo %d.\n", pesoAntigo, pesoNovo);
}


int main() {
  PONT p = (PONT) malloc(sizeof(pesoAltura));
  p->peso = 80;
  p->altura = 185;
  
  printf("1. Peso: %d, Altura %d.\n", p->peso, p->altura);
  atualizaEImprimePeso1(p->peso);
  printf("2. Peso: %d, Altura %d.\n", p->peso, p->altura);

  atualizaEImprimePeso2(p);
  printf("3. Peso: %d, Altura %d.\n", p->peso, p->altura);

  pesoAltura pessoa1 = *p;
  atualizaEImprimePeso3(pessoa1);
  printf("4. Peso: %d, Altura %d.\n", p->peso, p->altura);
  printf("5. Peso: %d, Altura %d.\n", pessoa1.peso, pessoa1.altura);
  p->peso = p->peso-5;
  printf("6. Peso: %d, Altura %d.\n", p->peso, p->altura);
  printf("7. Peso: %d, Altura %d.\n", pessoa1.peso, pessoa1.altura);
  free(p);
  return 0;
}

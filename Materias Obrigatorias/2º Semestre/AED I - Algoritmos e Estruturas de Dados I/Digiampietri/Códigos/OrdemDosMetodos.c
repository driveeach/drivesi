 // arquivo OrdemDosMetodos.c
#include "OrdemDosMetodos.h"

void imprimiESubtrai1(int entrada){
  printf("%d ",entrada);
  if (entrada>0){
    imprimiESubtrai2(entrada-1);
  }
}

void imprimiESubtrai2(int entrada){
  printf("%d ",entrada);
  if (entrada>0){
    imprimiESubtrai1(entrada-1);
  }
}

int main(){
    imprimiESubtrai1(10);
    system("pause");
    return 0;
}

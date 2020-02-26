#include <malloc.h>

void* meuMalloc(int numBytes){
      return malloc(numBytes);
}

void meuFree(void* endereco){
      free(endereco);
}

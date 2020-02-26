#include <stdio.h>
#include <malloc.h>

int main(){
    int n = 10;
    int i;
    printf("Tamanho de um 'int' em bytes: %d\n", sizeof(int));
    int *ApVet = (int*) malloc(sizeof(int)*n);
    printf("Endereco em memoria: %d\n", ApVet);   

    // Armazenando valores na memoria

    for (i=0;i<n;i++){
      ApVet[i] = i+1;
    } 

    int *posicaoDeArmazenamento = ApVet;
    //Imprimindo valores armazenados

    for (i=0;i<n;i++){
      printf("Posicao: %ld,  valor: %ld\n", posicaoDeArmazenamento, * posicaoDeArmazenamento);
      posicaoDeArmazenamento++;
    }

    system("pause");

    return 0;
}

/*
Tamanho de um 'int' em bytes: 4
Endereco em memoria: 9310272
Posicao: 9310272,  valor: 1
Posicao: 9310276,  valor: 2
Posicao: 9310280,  valor: 3
Posicao: 9310284,  valor: 4
Posicao: 9310288,  valor: 5
Posicao: 9310292,  valor: 6
Posicao: 9310296,  valor: 7
Posicao: 9310300,  valor: 8
Posicao: 9310304,  valor: 9
Posicao: 9310308,  valor: 10
*/

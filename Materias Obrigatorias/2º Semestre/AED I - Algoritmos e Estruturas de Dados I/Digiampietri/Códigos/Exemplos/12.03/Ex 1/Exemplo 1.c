# include <stdio.h>
# include <malloc.h>
int main(){
	int i;
	int n = 10;
	int mem = n*sizeof(int);
	printf ("Memoria: %d em bytes \n", mem);
	int*vetor = (int*)malloc(mem);
    for (i=0; i<n; i++){
        vetor [i] = i+1;    
    }
    int*p = vetor;
    for(i=0; i<n; i++){
    printf("Posicao: %d, valor %d \n", p, *p);         
    p++;
    }
    free(vetor);
    return 0;
}

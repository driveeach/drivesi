# include <malloc.h>
# include <stdio.h>
# include <conio.h>
# include <stdlib.h>
# include <string.h>
# include <time.h>

# define random(x) (rand() % x)
# define tabela(x) tabela[x]

# define tablesize 500000

void inicializa(int Table[], int TS)
{
     for (int i = 0; i < TS; i++)
     {
         Table[i] = -1;
     }
}

int hl (int c, int TS){ //hashing direto
    return c % TS;
}

int rh_linear (int c, int TS){ //rehashing linear
    return (c + 1) % TS;
}

int insere (int c, int Table[], int TS){
    int E = hl(c, TS);
    while (Table[E] != c && Table[E]!= -1)
    {
          E = rh_linear(E, TS);
    }
    if (Table[E]!=c)
    {
       Table[E] = c;
       return E;
    }
    else
        return -1;                           
}

bool busca (int c, int Table[], int TS){
     int E = hl(c, TS);
     while (Table[E] == c && Table[E]!= -1)
     {
          E = rh_linear(E, TS);
     }
     if (Table[E]==c)
        return true;
     else
         return false;     
}

bool removeZZZZ (int c, int Table[], int TS){
    int p = hl(c, TS);
    while (Table[p] != c && Table[p] != -1){
          p = rh_linear(p, TS);
    }
    if (Table[p] == -1)
    {
       printf("x");
       return false;
    }
    
    int q = rh_linear(p, TS);
    while (hl(Table[q], TS) != p && Table[q] != -1){
          q = rh_linear(q, TS);
    }
    
    if (Table[q] == -1)
    {
       Table[p] = -1;
       printf("y");
       return true;
    }
    
    Table[p] = Table[q];
    Table[q] = -1;
    printf("y");
    return true;                                                          
}


int remove(int c, int Table[], int TS){
    int p = hl(c, TS);
    while(Table[p] != c && Table[p] != -1){
                   p = rh_linear(p, TS);
                   }
     int q;
     if(Table[p] == -1)
                 return -1;
     q = rh_linear(p, TS);
     Table[p] = Table[q];
     while(Table[q] != -1){
                    p = rh_linear(q, TS);
                    Table[q] = Table[p];
                    q = p;
                    }
     return 1;                                                          
}

void imprimir (int vetor[], int max){
     for (int i = 0; i < max; i++)
     {
        if (i%5 == 0) printf("\n");
	    printf("%9d, ", vetor[i]);
     }
}

int main(void)
{
    srand(time(NULL)); //semente aleatoria
    
    
    int registros[tablesize]; //tabela hash vazia
    inicializa(registros, tablesize);
    int digitos = 9999999; //mascara com o numero máximo de digitos
    
    
    clock_t start, end;
    double elapsed;
    
    
    int tamanho = 0; //tamanho da amostra a ser testada
    printf("Defina o tamanho da amostra: ");
    while (tamanho <= 100)
    {
          scanf("%d", &tamanho);
          if (tamanho <= 100) printf("Tamanho da amostra deve ser maior ou igual a 100.");
    }
    //criar uma amostra aleatória de chaves de tamanho 'digitos'
    int amostra[tamanho];
    
    inicializa(amostra, tamanho);
    int i = 0;
    while (i < tamanho)
    {
          int chave = random(digitos);
          bool existe = false;
          
          for (int j = 0; j <= i; j++){
              if (chave == amostra[j])
              {
                 existe = true;
                 break;
              }
          }
          if (existe) continue;
          else
          {
              amostra[i] = chave;
              i++;
          }
    }
    
    for (int i = 0; i < tamanho; i++)
    {
        int reg = random(digitos);
        for (int j = 0; ((amostra[j] != -1) && (j < tamanho)); j++)
        {
              if (amostra[j] == reg) break;;
        }
	    
	}
	
	//criar uma amostra de digitos que nao serao encontrados na busca (caso nao encontrado)
	int amostraNE[tamanho/10];
	i = 0;
	while (i < tamanho/10)
	{
          int chave = random(digitos);
          bool existe = false;
          
          for (int j = 0; j < tamanho; j++){
              if (chave == amostra[j])
              {
                 existe = true;
                 break;
              }
          }
          if (existe) continue;
          else
          {
              amostraNE[i] = chave;
              i++;
          }
    }
    
    
    //imprimir(amostra, tamanho);
    printf("\n");
    //imprimir(amostraNE, tamanho/10);
    
       
    start = clock();
    //inserir a amostra na tabela
    for (int i = 0; i < tamanho; i++)
        insere(amostra[i], registros, tablesize-1);
    end = clock();
    elapsed = ((double) (end - start)) / CLOCKS_PER_SEC/1000;    
    printf("\nTempo de inserção: %f\n", elapsed);  
    
    
    
    start = clock();
    //buscar a amostra bem-sucedida na tabela
    for (int i = 0; i < tamanho; i++)
        busca(amostra[i], registros, tablesize-1);
    end = clock();
    elapsed = ((double) (end - start));    
    printf("\nTempo de busca: %f\n", elapsed);  
        
    start = clock();
    //buscar a amostra mal-sucedida (10% da tabela)
    for (int i = 0; i < tamanho/10; i++)
        busca(amostraNE[i], registros, tablesize-1);
    end = clock();
    elapsed = ((double) (end - start)) / CLOCKS_PER_SEC;    
    printf("\nTempo de busca mal-sucedida: %f\n", elapsed);  
      

    start = clock();
     //remove a tabela
    for (int i = 0; i < tamanho; i++)
        remove(amostra[i], registros, tablesize-1);
    end = clock();
    elapsed = ((double) (end - start)) / CLOCKS_PER_SEC;    
    printf("\nTempo de remoção: %f\n", elapsed);
      
    
    start = clock();
     //tenta remover a tabela
    for (int i = 0; i < tamanho; i++)
        remove(amostra[i], registros, tablesize-1);
    end = clock();
    elapsed = ((double) (end - start)) / CLOCKS_PER_SEC;    
    printf("\nTempo de remoção mal-sucedida: %f\n", elapsed);  
    
    int tam = tablesize * (sizeof(int));
    printf("o tamanho ocupado e de: %d", tam);
    
    /*
    printf("\n\nTabela Hash:");
    imprimir(registros, tablesize-1);
	*/
    
    getch();
    return 0;
}

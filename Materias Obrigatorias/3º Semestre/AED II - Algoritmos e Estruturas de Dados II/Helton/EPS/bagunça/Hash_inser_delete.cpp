# include <malloc.h>
# include <stdio.h>
# include <conio.h>
# include <stdlib.h>
# include <string.h>

#include <time.h>

# define TS 500000

void incializaTable(int Table[]){
     int i;
     for(i=0; i<TS; i++){
              Table[i] = -1;
              }
     }
     
int h (int c){
    return c % TS;
}

int rh(int c){
    return (c + 1) % TS;
}

int insere (int c, int Table[]){
    int E = h(c);
    while(Table[E] != c && Table[E]!= -1){
                   E = rh(E);
                   }
     if(Table[E]!=c){
                     Table[E] = c;
                     return E;
                     }
     else
         return -1;                              
}


int remove(int c, int Table[]){
    int p = h(c);
    while(Table[p] != c && Table[p] != -1){
                   p = rh(p);
                   }
     int q;
     if(Table[p] == -1)
                 return -1;
     q = rh(p);
     Table[p] = Table[q];
     while(Table[q] != -1){
                    p = rh(q);
                    Table[q] = Table[p];
                    q = p;
                    }
     return 1;                                                          
}

void imprimi(int Table[]){
     int i;
     for(i=0; i<TS; i++){
              if(Table[i]!=-1)
                              printf("Posicao:%d, Valor:%d \n", i,Table[i]);
              }
     }

int main(void){
     int table[TS];
     incializaTable(table);
     int i;
    
     clock_t start, end;
     double elapsed;
    
     start = clock();
     
     for(i=0; i<100000; i++){
     insere((3*i), table);
     insere((23*i), table);
     insere((7*i), table);
     insere((17*i), table);
     insere((19*i), table);     
     }
           
     
     end = clock();
     elapsed = ((double) (end - start)) / CLOCKS_PER_SEC;
     printf("O tempo trasncorrido foi: %f segundos", elapsed);
     
     getch();
     }

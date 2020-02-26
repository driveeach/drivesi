// exemplo.c

#include "exemplo.h"
void imprimirA(){
     printf("A\n");     
}
void imprimirB(){
     printf("B\n");     
}
void imprimirC(){
     printf("C\n");
}
typedef struct aux{
     int numUSP;
     int CPF;
     struct aux * conjuge;  
                
} Pessoa;


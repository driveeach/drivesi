#include <stdio.h>

typedef struct{
        int dia, mes, ano;
} data;
int main (void){
    printf ("sizeof (data) = %d\n", sizeof (data));
    printf ("sizeof (data*) = %d\n", sizeof (data*));    
    return 0;
    system("pause");
}

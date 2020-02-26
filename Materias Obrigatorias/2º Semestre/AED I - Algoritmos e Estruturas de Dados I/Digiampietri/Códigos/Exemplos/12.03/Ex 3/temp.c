//temp.c

#include <stdio.h>
#include <malloc.h>
#include "temp.h"
int main(){
    Pessoa p1;
    p1.numUSP = 314;
    Pessoa p2;
    p2.numUSP = 413;
    p1.conjuge = &p2;
    p2.conjuge = &p1;
    Pessoa* p3 = &p1;
    p3 = (Pessoa*)malloc(sizeof(Pessoa));
    p3-> numUSP = 567;
    p3-> conjuge = &p1;
    p2 = *p3;
    free(p3);
    return 0;
    
    
    system("pause");
}

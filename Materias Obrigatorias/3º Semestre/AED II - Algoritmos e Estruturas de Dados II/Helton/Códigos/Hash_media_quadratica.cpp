# include <malloc.h>
# include <stdio.h>
# include <conio.h>
# include <stdlib.h>
# include <string.h>

# define TS 100

int potencia(int base, int expoente)
{
    if (expoente == 0) return 1;
    
    return base * potencia(base, expoente-1);;
}

int quadratica(int c)
{
    int cont = 1;
    int numDig = 1;
    int tamT = TS;
    int q = c*c;
    
    //contadores
    while (q > 10)
    {
          q = q / 10;
          cont++;
          //printf("%d\n", q);
    }
    while (tamT > 10)
    {
          tamT = tamT / 10;
          numDig++;
    }
    printf("Valor quadrado: %d\n", c*c);
    //printf("Numero de digitos: %d\nDigitos da tabela: %d\n", cont, numDig);
    int resp = cont - numDig;
    //printf("Passo 1: %d\n", resp);
    resp = (c*c)/potencia(10, resp/2);
    //printf("Resto do primeiro: %d\n", resp);
    return resp % potencia(10, numDig);
}


int main()
{
    int chave;
    printf("Digite a chave: "); //354
    scanf("%d", &chave);
    
    printf("Hash: %d", quadratica(chave));
    
    getch();
    
    
    return 1;
}

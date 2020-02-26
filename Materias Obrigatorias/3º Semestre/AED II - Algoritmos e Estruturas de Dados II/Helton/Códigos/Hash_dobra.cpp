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

int dobra(int c)
{
    int cont = 1;
    int numDig = 1;
    int tamT = TS;
    int q = c;
    
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
    
    int numero[cont];
    q = c;
    
    for (int i = cont-1; i >= 0; i--)
    { 
        numero[i] = q%10;
        q = q/10;
    }

    printf("%d\n", cont);
    
    for (int i = 0; i < cont; i++)
    {
        printf("%d", numero[i]);
    }
    
    printf("\n");
    
    int numeroDecimal = 0; //a resposta
    int i = 0;
    int j = cont-1;
    
    while (i < cont/2)
    {
          numero[j] = numero[i] + numero[j];
          i++;
          j--;
          if (numero[j] > 9) numero[j] = numero[j]%10;
          printf("%d,", numero[j]);
    }
    
    j = cont-1;
    int x = 0;

    for (int j = cont-1; j > cont/2; j--)
    {
        numeroDecimal = numero[j]*potencia(10, x);
        x++;
    }
    return numeroDecimal;
}




int main()
{
    int chave;
    printf("Digite a chave: "); //354
    scanf("%d", &chave);
    
    printf("\nHash: %d", dobra(chave));
    
    getch();
    
    
    return 1;
}

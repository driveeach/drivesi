class GerArr
{
    final static int TAMANHO_MAXIMO = 30000;
    int[] A;
    int numero;
    
    int[] a(int tamanho) //elementos aleatórios
    {
        int[] A = new int[tamanho];
        
        for (int i = 0; i < A.length; i++)
            A[i] = (int)(TAMANHO_MAXIMO*java.lang.Math.random());
            
        return A;
    }
    
    int[] mc(int tamanho)  //elementos misturados crescentemente
    {
        int[] A = new int[tamanho];
        numero = (int)(TAMANHO_MAXIMO*java.lang.Math.random());
        
        for (int i = 0; i < A.length; i++)
        {
            if ( i%2 == 0 )
                A[i] = numero++;
            else
                A[i] = (int)(TAMANHO_MAXIMO*java.lang.Math.random());
        }
        return A;
    }
    
    int[] md(int tamanho) //elementos misturados decrescentemente
    {
        int[] A = new int[tamanho];
        numero = (int)(TAMANHO_MAXIMO*java.lang.Math.random());
        
        for (int i = A.length-1; i >= 0; i--)
        {
            if ( i%2 == 0 )
                A[i] = numero++;
            else
                A[i] = (int)(TAMANHO_MAXIMO*java.lang.Math.random());;
        }
        return A;
    }
    
    int[] m(int tamanho) //elementos misturados
    {
        int[] A = new int[tamanho];
        numero = (int)(TAMANHO_MAXIMO*java.lang.Math.random());
        int numeroAux = (int)(TAMANHO_MAXIMO*java.lang.Math.random());
        
        for (int i = 0; i < A.length; i++)
        {
            if ( i%2 == 0 )
                A[i] = numero--;
            else
                A[i] = numeroAux++;
        }
        return A;
    }
    
    int[] oc(int tamanho) //elementos ordenados crescentemente
    {
        int[] A = new int[tamanho];
        numero = (int)(TAMANHO_MAXIMO*java.lang.Math.random());
        
        for (int i = 0; i < A.length; i++)
            A[i] = numero++;

        return A;
    }
    
    int[] od(int tamanho) //elementos ordenados decrescentemente
    {
        int[] A = new int[tamanho];
        numero = (int)(TAMANHO_MAXIMO*java.lang.Math.random());
        
        for (int i = 0; i < A.length; i++)
            A[i] = numero--;

        return A;
    }
    
    int[] i(int tamanho) //elementos iguais
    {
        int[] A = new int[tamanho];
        numero = (int)(TAMANHO_MAXIMO*java.lang.Math.random());
        
        for (int i = 0; i < A.length; i++)
            A[i] = numero;
        
        return A;
    }
    
    void print(int[] A)
    {
        for (int i = 0; i < A.length; i++)
            System.out.print(A[i] + " ");
        System.out.println();
    }
    
    public static void main(String[] args)
    {
        GerArr teste = new GerArr();
        teste.print(teste.a(10));
        teste.print(teste.mc(10));
        teste.print(teste.md(10));
        teste.print(teste.m(10));
        teste.print(teste.oc(10));
        teste.print(teste.od(10));
        teste.print(teste.i(10));
    }
}
    
class Aval
{
    static int algoAjustados, avalAjustados, tamaAjustados;
    int[] algoritmos = new int[10]; 
    int[] avaliacoes = new int[10];
    int[] tamanhos = new int[10]; //tamanho do arranjo
    
    //Insertion = 0; Selection = 1, Bubble = 2, Merge = 3, Quick = 4, QuickAleat = 5 e Heap = 6;
    int ajustaAvaliacaoAlgoritmoXXX(int XXX)
    {
        if (XXX >= 0 && XXX <= 6)
        {
            if (algoAjustados < 10)
            {
                algoritmos[algoAjustados] = XXX;
                algoAjustados++;
            }
            else
                System.out.println("Já foram ajustados o máximo de algoritmos permitidos.");
        }
        else
            System.out.println("Insira o código correto, anta!");
        
        return algoAjustados;
    }

    //aleatório = 0, misturado crescentemente = 1, misturado decrescentemente = 2, misturados = 3; ordenado crescentemente = 4, ordenado decrescentemente = 5, iguais = 6
    int ajustaArranjoTipoYYY(int YYY)
    {
        if (YYY >= 0 && YYY <= 6)
        {
            if (avalAjustados < 10)
            {
                algoritmos[avalAjustados] = YYY;
                avalAjustados++;
            }
            else
                System.out.println("Já foram ajustadas o máximo de avaliações permitidas.");
        }
        else
            System.out.println("Insira o código correto, anta!");
        
        return avalAjustados;
    }
    
    int ajustaTamanhoArranjos(int tamanho)
    {
        if (tamaAjustados < 10)
        {
            tamanhos[tamaAjustados] = tamanho;
            tamaAjustados++;
        }
        else
            System.out.println("Já foram ajustados o máximo de tamanhos permitidos.");
        
        return tamaAjustados;
    }
    
    void executaAvaliacao()
    {
    	long t1 = System.nanoTime();
    	long tx = System.nanoTime();
    	Thread.sleep(1000);
    	
        System.out.println(System.currentTimeMillis());
    }
    
    void imprimeResultadoTabela()
    {
    }
}
    
    
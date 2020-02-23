/*
    Programa para calcular a área de uma piscina redonda.
*/
class AreaPiscina 
{
    /* materiais da piscina */
    static final int ALVENARIA = 0;
    static final int VINIL = 1;
    static final int FIBRA = 2;
    static final int PLASTICO = 3;
    /* preços dos materiais */
    static double[] precos = {1500, 1100, 750, 500};
    /* nomes dos materiais */
    static char[][] nomes = {{'A','l','v','e','n','a','r','i','a'},
                             {'V','i','n','i','l'},
                             {'F','i','b','r','a'},
                             {'P','l','á','s','t','i','c','o'}};
    /*
        Calcula a área da piscina
        
        Parâmetros:
            raio - O raio da piscina
    */
    static double area(double raio) 
	{
        return((raio >= 0) ? Math.PI * Math.pow(raio,2) : -1);
    }
    
    /*
        Calcula o valor da construção da piscina, ou negativo em caso de erro
    */
    static double valor(double metragem, int material) 
	{
        if (material<ALVENARIA || material>PLASTICO || metragem<0) return(-1);
        
        return(metragem*precos[material]);//DÚVIDA
    }

    /*
        Carrega os valores das piscinas na matriz de área X material
    */
    public static void carregaVal(double[][] m) 
	{
        for (int i=0; i<m.length; i++) 
		{ // linhas (material)
            for (int j=50; j<=200; j+=50) 
			{ // colunas (áreas)
                m[i][j / 50 - 1] = precos[i] * j;
            }
        }
    }
    
    /*
        Retorna matriz com os preços finais.
        
        Parâmetros:
            val - matriz de valores
            desc - matriz de descontos
    */
    public static double[][] calculaFinal(double[][] val, double[][] desc)
	{
        double[][] saida = new double[val.length][val[0].length];
        
        for (int i=0; i<saida.length; i++)
		{
            for (int j=0; j<saida[0].length; j++) 
			{
                saida[i][j] = val[i][j] * (1-desc[i][j]);
            }
        }
        return(saida);
    }

    public static void main(String[] args) 
	{
        double[][] valores = new double[4][4];
        double[][] descontos = {{0,0,0.2,0.2},
                                {0.05,0.05,0.1,0.15},
                                {0.02,0.04,0.08,0.16},
                                {0,0,0,0.05}};
        double[][] pFinal;
        
        carregaVal(valores);
        
        pFinal = calculaFinal(valores, descontos);
        
        for (double[] l : pFinal) 
		{
            for (double v : l) 
			{
                System.out.print(v+" ");
            }
            System.out.println();
        }
    }
}

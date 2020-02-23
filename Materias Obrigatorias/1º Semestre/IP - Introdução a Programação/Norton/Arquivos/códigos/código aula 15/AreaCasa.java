/*
    Programa para calcular a área de uma casa
    (e seus cômodos) de 3 cômodos: uma sala de
    10X10m, um banheiro e um quarto de 5X7m cada.
*/
class AreaCasa 
{
    /* valor do metro quadrado da casa */
    double valorM2 = 1500;
    
    /*
        Calcula a área da casa
        
        Parâmetros:
            lateral - comprimento da lateral da cabana
            cquarto - lateral maior do quarto
    */
    double area(double lateral, double cquarto) 
	{
        double areat=-1; // área total
        
        if (lateral>=0 && cquarto>=0) 
		{
            areat = lateral*lateral;
            areat += cquarto*lateral;
        }
        return(areat);
    }

    /*
        Calcula o valor total da construção
    */
    double valor(double metragem) 
	{
        if (metragem >= 0) return(valorM2*metragem);
        return(-1);
    }

    
    public static void main(String[] args)
	{
        
    }
}

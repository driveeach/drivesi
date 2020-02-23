package casa;

/*
    Programa para calcular a área de uma casa
*/
public class Casa {
    /* valor do metro quadrado da casa */
    private double valorM2 = 1500;
    
    Casa(){}
    
    /*
        construtor
        
        parametros:
            valorM2 - valor do metro quadrado da casa
    */
    Casa(double valorM2) {
        this.valorM2 = valorM2;
    }
    
    /*
        Calcula o valor total da construção
    */
    double valor(double metragem) {
        if (metragem >= 0) return(this.valorM2*metragem);
        return(-1);
    }
    
    /*
        Coxambre para tornar area visível às subclasses
    */
    public double area() {
        return(-1);
    }
}

/*
    Programa para calcular a área de uma casa
*/
class Casa {
	/* valor do metro quadrado da casa */
    double valorM2 = 1500;
    
    /* comprimento da lateral da casa (ou quarto) */
    double lateral = 10;
    
    /*
        Calcula o valor total da construção
    */
    double valor(double metragem) {
        if (metragem >= 0) return(this.valorM2*metragem);
        return(-1);
    }
    
    /*
        Coxambre para tornar area visível às subclasses
    *
    double area() {
        return(-1);
    }*/
}

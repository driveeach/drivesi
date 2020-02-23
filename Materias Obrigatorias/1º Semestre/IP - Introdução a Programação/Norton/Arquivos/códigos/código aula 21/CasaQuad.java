/*
    Programa para calcular a área de uma casa
    quadrada de 4 cômodos.
*/
class CasaQuad extends Casa {
    /*
        Constrói cabana padrão
    */
    CasaQuad() {}
    
    /*
        Constrói casa com dimensões específicas
    */
    CasaQuad(double lateral) {
        this.lateral = lateral;
    }
    
    /*
        Constrói casa com dimensões e custo para m2 específicos
    */
    CasaQuad(double lateral, double valorM2) {
        this(lateral);
        this.valorM2 = valorM2;
    }
    
    /*
        Calcula a área da casa
        
        Parâmetros:
            lateral - comprimento da lateral da cabana
            cquarto - lateral maior do quarto
    */
    double area() {
        double areat=-1; // área total
        
        if (this.lateral>=0) {
            areat = this.lateral*this.lateral;
        }
        return(areat);
    }

    public static void main(String[] args) {
        
    }
}

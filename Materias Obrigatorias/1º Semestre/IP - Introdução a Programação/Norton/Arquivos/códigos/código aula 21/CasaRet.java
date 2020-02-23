/*
    Programa para calcular a área de uma casa
    (e seus cômodos) de 3 cômodos: uma sala de
    10X10m, um banheiro e um quarto de 5X7m cada.
*/
class CasaRet extends Casa {
    /* comprimento da lateral maior do quarto */
    double cquarto = 10;
    
    /*
        Constrói cabana padrão
    */
    CasaRet() {}
    
    /*
        Constrói casa com novo custo para m2
    */
    CasaRet(double valorM2) {
        this.valorM2 = valorM2;
    }
    
    /*
        Constrói casa com dimensões específicas
    */
    CasaRet(double lateral, double cquarto) {
        this.lateral = lateral;
        this.cquarto = cquarto;
    }
    
    /*
        Constrói casa com dimensões e custo para m2 específicos
    */
    CasaRet(double lateral, double cquarto, double valorM2) {
        this(lateral, cquarto);
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
        
        if (this.lateral>=0 && this.cquarto>=0) {
            areat = this.lateral*this.lateral;
            areat += this.cquarto*this.lateral;
        }
        return(areat);
    }
    
    public static void main(String[] args) {
        
    }
}

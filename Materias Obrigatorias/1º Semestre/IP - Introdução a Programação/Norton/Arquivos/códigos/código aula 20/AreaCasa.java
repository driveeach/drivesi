/*
    Programa para calcular a área de uma casa
    (e seus cômodos) de 3 cômodos: uma sala de
    10X10m, um banheiro e um quarto de 5X7m cada.
*/
class AreaCasa {
    /* valor do metro quadrado da casa */
    double valorM2 = 1500;
    
    /* comprimento da lateral da sala */
    double lateral = 10;
    
    /* comprimento da lateral maior do quarto */
    double cquarto = 10;
    
    /*
        Constrói cabana padrão
    */
    AreaCasa() {}
    
    /*
        Constrói casa com novo custo para m2
    */
    AreaCasa(double valorM2) {
        this.valorM2 = valorM2;
    }
    
    /*
        Constrói casa com dimensões específicas
    */
    AreaCasa(double lateral, double cquarto) {
        this.lateral = lateral;
        this.cquarto = cquarto;
    }
    
    /*
        Constrói casa com dimensões e custo para m2 específicos
    */
    AreaCasa(double lateral, double cquarto, double valorM2) {
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

    /*
        Calcula o valor total da construção
    */
    double valor(double metragem) {
        if (metragem >= 0) return(this.valorM2*metragem);
        return(-1);
    }

    
    public static void main(String[] args) {
        AreaCasa a = new AreaCasa(1270);
        AreaCasa a2 = new AreaCasa(10,20);
        
        System.out.println(a.area());
        System.out.println(a2.area());
    }
}

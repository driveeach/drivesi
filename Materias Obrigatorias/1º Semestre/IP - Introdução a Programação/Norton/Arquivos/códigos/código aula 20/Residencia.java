/*
    Classe para criação de um projeto arquitetônico
*/
class Residencia {
    /* A casa */
    AreaCasa casa;
    /* A piscina associada */
    AreaPiscina piscina;
    
    /*
        Constrói uma residência a partir de uma casa e piscina
    */
    Residencia(AreaCasa casa, AreaPiscina piscina) {
        this.casa = casa;
        this.piscina = piscina;
    }
    
    /*
        Compara essa residência a outra, comb ase em sua área total.
        
        Retorna 0 se forem iguais, um número negativo se esta form menor que a outra, e um número positivo se for maior.
        
        Param:
            outra - a outra residência
    */
    public int comparaRes(Residencia outra) {
        if (outra == null) return(1); // esta é maior
        return((int)(this.area() - outra.area()));
    }
    
    /*
        Retorna a área total da residência
    */
    public double area() {
        double resp = 0;
        if (this.casa != null)
            resp += this.casa.area();
        if (this.piscina != null)
            resp += this.piscina.area();
        return(resp);
    }
    
    
    
    public static void main(String[] args) {
        AreaCasa c = new AreaCasa();
        AreaPiscina p = new AreaPiscina();
        Residencia r1 = new Residencia(c,p);
        
        c = new AreaCasa();
        p = new AreaPiscina(11);
        Residencia r2 = new Residencia(c,p);
        
        System.out.println(r1.comparaRes(r2));
    }
}

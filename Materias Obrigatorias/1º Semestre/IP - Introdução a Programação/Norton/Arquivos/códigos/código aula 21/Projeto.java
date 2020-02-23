import java.util.Scanner;

/*
    Classe para criação de um condomínio de casas
*/
class Projeto {
    /* Representação do condomínio */
    Residencia[] condominio;
    /* Índice da última residência alocada */
    int ultimo = -1;
    
    /*
        Adiciona, uma residência ao condomínio. Retorna true se conseguiu e false se o condomínio já está cheio
    */
    boolean adicionaRes(Residencia r) {
        if (this.ultimo < this.condominio.length-1) {
            ultimo++;
            this.condominio[ultimo] = r;
            return(true);
        }
        return(false);
    }
    
    /*
        Constrói o condomínio sem casas
    */
    Projeto(int tam) {
        condominio = new Residencia[tam];
    }
    
    
    public static void main(String[] args) {
        CasaRet cr = new CasaRet(10,5,1320);
        CasaQuad cq = new CasaQuad(10,1523);
        
        Residencia r1 = new Residencia(cr, null);
        Residencia r2 = new Residencia(cq, null);
        
        System.out.println("m2 (r1): "+r1.casa.valorM2);
        System.out.println("m2 (r2): "+r2.casa.valorM2);
        System.out.println("Área (r1): "+r1.casa.area());
    }
}

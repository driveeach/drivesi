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
        Scanner entrada = new Scanner(System.in);
        
        System.out.print("Quantas residências há no condomínio? ");
        String sTotal = entrada.nextLine();
        int nRes = Integer.parseInt(sTotal);
        Projeto proj = new Projeto(nRes);
        
        for (int i=0; i<nRes; i++) {
            Residencia res = new Residencia(new AreaCasa(), new AreaPiscina());
            proj.adicionaRes(res);
        }
        
        System.out.println(proj.condominio.length);
        System.out.println(proj.ultimo);
    }
}

import java.util.Scanner;
import casa.CasaRet;
import casa.CasaQuad;
import casa.Casa;
import piscina.AreaPiscina;

/*
    Classe para criação de um condomínio de casas
*/
class Projeto {
    /* Representação do condomínio */
    ListaSimples condominio;
    
    /*
        Adiciona, uma residência ao condomínio.
    */
    void adicionaRes(Residencia r) {
        this.condominio.insere(r);
    }
    
    /*
        Constrói o condomínio sem casas
    */
    Projeto() {
        this.condominio = new ListaSimples();
    }
    
    
    public static void main(String[] args) {
        CasaRet cr = new CasaRet(10,5,1320);
        CasaQuad cq = new CasaQuad(10,1523);
        CasaQuad cq2 = new CasaQuad(8,1523);
        
        Residencia r1 = new Residencia(cr, null);
        Residencia r2 = new Residencia(cq, null);
        Residencia r3 = new Residencia(cq2, null);
        
        Projeto p = new Projeto();
        
        p.adicionaRes(r1);
        p.adicionaRes(r2);
        p.adicionaRes(r3);
        
        
        Residencia r4 = new Residencia(new CasaQuad(0,1523), null);
        p.condominio.insere(r4,3);
        
        No prox = p.condominio.retCabeca();
        while (prox != null) {
            System.out.println(prox.retRes().casa.area());
            prox = prox.retProx();
        }
        System.out.println(p.condominio.elementos());
    }
}

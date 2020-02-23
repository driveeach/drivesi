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
    
    // método que funciona apenas com arranjos, não com listas
    static void bolha(Residencia[] v, Comparador c) {
        for (int ult = v.length-1; ult>0; ult--) {
            for (int i=0; i<ult; i++) {
                if (c.compara(v[i],v[i+1]) > 0) {
                    Residencia aux = v[i];
                    v[i] = v[i+1];
                    v[i+1] = aux;
                }
            }
        }
    }
    
    public static void main(String[] args) {
        CasaRet cr = new CasaRet(10,5,1320);
        CasaQuad cq = new CasaQuad(10,1523);
        
        Residencia r1 = new Residencia(cr, null);
        Residencia r2 = new Residencia(cq, null);
        
        System.out.println("Área r1: "+r1.area());
        System.out.println("Área r2: "+r2.area());
        Comparador c = new ComparaArea();
        System.out.println("Comparação: "+c.compara(r1,r2));
        
        System.out.println();
        
        System.out.println("Valor casa r1: "+r1.casa.valor(r1.casa.area()));
        System.out.println("Valor casa r2: "+r2.casa.valor(r2.casa.area()));
        c = new ComparaValor();
        System.out.println("Comparação: "+c.compara(r1,r2));
    }
}

import casa.Casa;
import piscina.AreaPiscina;

/*
    Classe para criação de um projeto arquitetônico
*/
class Residencia {
    /* A casa */
    Casa casa;
    /* A piscina associada */
    AreaPiscina piscina;
    
    /*
        Constrói uma residência a partir de uma casa e piscina
    */
    Residencia(Casa casa, AreaPiscina piscina) {
        this.casa = casa;
        this.piscina = piscina;
    }
}

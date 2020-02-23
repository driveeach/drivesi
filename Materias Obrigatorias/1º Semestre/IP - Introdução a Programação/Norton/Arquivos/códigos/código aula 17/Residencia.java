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
}

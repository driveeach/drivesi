interface Comparador {
    /*
        Compara uma residência a outra.
        
        Retorna 0 se forem iguais, um número negativo se a primeira for menor que a segunda, e um número positivo se for maior.
        
        Param:
            a - primeira residência
            b - segunda residência
    */
    int compara(Residencia a, Residencia b);
}

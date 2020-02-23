/*
    Classe para criação de um projeto arquitetônico
*/
class Projeto {

    /*
        Calcula a área de uma casa com piscina
        
        param:
            casa - a casa
            piscina - piscina associada
    */
    static double area(AreaCasa casa, AreaPiscina piscina) {
        return(casa.area() + piscina.area());
    }

    public static void main(String[] args) {
        AreaCasa casaPrinc = new AreaCasa(10,5);
        AreaPiscina piscina1 = new AreaPiscina(10);
        
        System.out.println(area(casaPrinc, piscina1));
    }
}

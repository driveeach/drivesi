/*
    Classe para criação de um projeto arquitetônico
*/
class Projeto 
	{

    /*
        Calcula a área de uma casa com piscina
        
        param:
            lateral - lateral da cozinha
            cquarto - lateral do quarto
            raio - raio da piscina
    *
    static double area(double lateral, double cquarto, double raio) {
        return(AreaCasa.area(lateral,cquarto) + AreaPiscina.area(raio));
    }*/

    public static void main(String[] args) 
	{
        /*System.out.println( AreaCasa.valor( AreaCasa.area(15,10) ) );
        AreaCasa.valorM2 = 1270;
        System.out.println( AreaCasa.valor( AreaCasa.area(18,8) ) );
        */
        
        AreaCasa casa1 = new AreaCasa();
        AreaCasa casa2 = new AreaCasa();
        
        casa2.valorM2 = 1270;
        
        System.out.println( casa1.valor( casa1.area(15,10) ) );
        System.out.println( casa2.valor( casa2.area(18,8) ) );
    }
}

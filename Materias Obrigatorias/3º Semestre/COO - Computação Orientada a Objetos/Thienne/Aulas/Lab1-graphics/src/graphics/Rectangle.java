package graphics;

/** Classe concreta para computar a area de uma figura rectangular: 
* conhece a sua largura e e altura e a formula para calcular a sua area.
**/

public class Rectangle extends Shape
{
private double _altura;
private double _largura;

/**
* Inicializa esse objeto Rectangle com os valores dados para a sua 
* largura e altura.
*/
public Rectangle(double largura, double altura)
{
_altura = altura;
_largura = largura;
}


/**
* retorna a area desse objeto Rectangle.
*/
public double getArea()
{
return _altura * _largura;
}
}


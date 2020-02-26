package graphics;


/**
* Classe concreta para computar a area de uma figura circular: conhece
* o seu raio e a formula para calcular sua area.
**/

public class Circle extends Shape
{

private double _raio;

/**
* Initializa esse objeto Circle com um raio dado.
**/
public Circle(double raio)
{

_raio = raio;
}

/**
* @retorna a area desse objeto Circle.
*/
public double getArea()
{
return Math.PI * _raio * _raio;
}

}
 

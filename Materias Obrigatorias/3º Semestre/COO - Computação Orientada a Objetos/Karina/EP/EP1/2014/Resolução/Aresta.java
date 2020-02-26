import java.lang.Math;

class Aresta{		//classe Aresta
	double peso;	//peso da aresta
	int vInicio;	//vertice de inicio da aresta
	int vFim;		//vertice de fim da aresta

	Aresta(int ini, int fim){	//construtor
		vInicio = ini;			//atribui o vertice de inicio em sua variavel global
		vFim = fim;				//atribui o vertice de final em sua variavel global
	}

	void calculaPeso(Vertice v1, Vertice v2){		//metodo para calcular peso da aresta
		int x = Math.abs(v1.coordx - v2.coordx);	//obtem a diferenca em x
		int y = Math.abs(v1.coordy - v2.coordy);	//obtem a diferenca em y

		peso = Math.sqrt(x*x + y*y);				//calcula o peso atraves do Teorema de Pitagoras
	}
}
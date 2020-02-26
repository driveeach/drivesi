// by rafaelcavazin@gmail.com

package questao2;

public class Questao2_Main {

	public static void main(String[] args) {

		FiguraFactory ffact = new FiguraFactory();
		Figura fig;

		fig = ffact.createFigura("circulo");
		fig.area(10, 0);
		System.out.println(fig.toString());
		
		fig = ffact.createFigura("trEquilatero");
		fig.area(10, 15);
		System.out.println(fig.toString());
		
		fig = ffact.createFigura("quadrado");
		fig.area(7, 5);
		System.out.println(fig.toString());
	}
}

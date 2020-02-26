package demo;

import graphics.Circle;
import graphics.Rectangle;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * Crie, em um outro pacote demo, uma classe que funcione como um aplicativo simples, 
 * que forneça a área de um círculo ou retângulo, de acordo com os valores de parâmetros 
 * dados como entrada pelo usuário. Para isso, você deve importar as definições de classes 
 * Circle e Rectangle que estão no pacote graphics.
 * Deve-se tratar as exceções decorrentes da entrada de dados, que deve ser do tipo inteiro.
 * 
 */
public class Principal {

	public static void CalculaAreaCirculo(){
		Scanner sc = new Scanner(System.in);
		while (true){
			System.out.println("Calculo de area do Circulo");
			try{
				System.out.println("Digite o valor do raio:");
				double raio = sc.nextDouble();
				if (raio < 0 ) {
					throw new NumeroNegativo();
				}
				else if (raio == 0) {
					throw new ValorZero();
				}
				else {
				Circle c = new Circle(raio);
				double area = c.getArea();
				System.out.println ("Area do circulo é "+area);
				break;
				}
			}
			catch (NumeroNegativo e){
				e.printStackTrace();
			}
			catch (ValorZero e){
				e.printStackTrace();
			}
			catch (InputMismatchException e){
				e.printStackTrace();
			}
			sc.reset();
		}

	}
	

	public static void CalculaAreaRetangulo(){
		Scanner sc = new Scanner(System.in);
		while (true){
			System.out.println("Calculo de area do Retangulo");
			try {
				System.out.println("Digite o valor da base:");
				double base = sc.nextDouble();
				System.out.println("Digite o valor da altura:");
				double altura = sc.nextDouble();
				if ((base < 0) || (altura <0)) {
					throw new NumeroNegativo();
				}
				else if ((base == 0)|| (altura == 0)) {
					throw new ValorZero();
				}
				else {
					Rectangle r = new Rectangle(base, altura);
					double area = r.getArea();
					System.out.println ("Area do retangulo é "+area);
					break;
				}
			}
			catch (NumeroNegativo e){
				e.printStackTrace();
			}
			catch (ValorZero e){
				e.printStackTrace();
			}
			catch (InputMismatchException e){
				e.printStackTrace();
			}
			sc.reset();
		}
	}

	
	public static void main(String[] args) {

		while (true){
			try{
				System.out.println("Programa para calculo de area");
				System.out.println("Escolha 1 para circulo, 2 para retangulo e 3 para sair");
				Scanner sc = new Scanner (System.in);
				int opcao = sc.nextInt();
				if (opcao == 1) {
					CalculaAreaCirculo();
				} 	
				else if (opcao == 2) {
					CalculaAreaRetangulo();
				}
				else if (opcao == 3) {
					break;
				}
				else {
					sc.reset();
					throw new ErroEntrada();
				}
			}
			catch (ErroEntrada e){
				System.out.println(e);
				System.out.println("Tente novamente");
			}
		}
	}

}

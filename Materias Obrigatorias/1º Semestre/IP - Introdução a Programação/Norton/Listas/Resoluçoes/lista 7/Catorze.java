import java.util.Scanner;
class Catorze {
	public static void main (String [] args){
		Scanner sc = new Scanner (System.in);
		double media = 0;
		String resposta = null;
		int num = 0;
		do {
		System.out.println ("Digite o numero");
		media += sc.nextDouble();
		num ++;
		System.out.println ("Quer continuar?");
		resposta = sc.next();
		}
		while (resposta.equals("sim"));
		media /= num;
		System.out.println ("Media: " +media);
	}
}
import java.util.Scanner;
class Dezesseis {
	public static void main (String [] args){
		double a = 0;
		double mediaProvas = 0;
		double mediaTrabalhos = 0;
		double mediaFinal = 0;
		Scanner sc = new Scanner (System.in);
		System.out.println ("Digite a nota da P1");
		mediaProvas += sc.nextDouble() * 0.4;
		System.out.println ("Digite a nota da P2");
		mediaProvas += sc.nextDouble() * 0.6;
		System.out.println ("Digite a nota do EP1");
		mediaTrabalhos += sc.nextDouble();
		System.out.println ("Digite a nota do EP2");
		mediaTrabalhos += sc.nextDouble();
		System.out.println ("Digite a nota do EP3");
		mediaTrabalhos += sc.nextDouble();
		
		mediaTrabalhos /= 3;
		mediaFinal = 0.8 * mediaProvas + 0.2 * mediaTrabalhos;
		System.out.println ("Media final: "+mediaFinal);
		mediaFinal *= 10;
		System.out.println ("Aproveitamento: "+mediaFinal+"%");		
	}
}
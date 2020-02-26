public class Divisoes {

	   // demonstra o lançamento de uma exceção quando ocorre uma divisão por zero 
	   public static int quociente( int numerador, int denominador )
	   {
	      return numerador / denominador; // possivel divisao por zero
	   } // fim de método quociente 

	   public static void main( String args[] )
	   {
	      Scanner scanner = new Scanner( System.in ); // scanner para entrada

	      System.out.print("Entre com um numerador inteiro:");
	      int numerador = scanner.nextInt();
	      System.out.print( "Entre com um denominador inteiro: " );
	      int denominador = scanner.nextInt();
	      int result = quociente( numerador, denominador );
	      System.out.printf( 
	         "\nResult: %d / %d = %d\n", numerador, denominador, result );
    	  
	      
	   } // fim de main
	} // fim da classe DivideByZeroNoExceptionHandling 

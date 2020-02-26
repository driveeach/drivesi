/**
 * 
 */
package myExceptions;

/**
 * @author chaim
 *
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class DivideByZero {

	/**
	 * 
	 */
	public DivideByZero() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static void throwException() throws Exception
	   {
		try { // lança uma exceção e a captura em main
	         System.out.println( "Metodo throwException" );
	         throw new Exception(); // gera a exceção
	      } // fim de try
	      catch ( RuntimeException runtimeException ) { // captura tipo incorreto
	         System.err.println("Excecao tratada no metodo throwException" );
	      } // fim de catch
	      finally { // o bloco finally sempre executa 
	         System.err.println( "Finally e sempre executado" );
	      } // fim de finally 
	   } // fim de método throwException 
	

	public static int quociente( int numerador, int denominador )throws ArithmeticException
	   {
	      return numerador / denominador; // possivel divisao por zero
	   } // fim de método quociente 
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner( System.in ); // scanner para entrada
	      boolean continueLoop = true; // determina se mais tentativas são necessárias
		  try 
	      { 
	         throwException(); // chama método throwException 
	      } // fim de try
	      catch ( Exception exception ) // exceção lançada por throwException
	      {
	         System.err.println( "Exception handled in main" );
			 System.exit(1);
	      } // fim de catch
	      do                                                                  
	      {                                                                   
	         try { // lê dois números e calcula o quociente             
	            System.out.print("Entre com um numerador inteiro: ");
	            int numerador = scanner.nextInt();                            
	            System.out.print( "Entre com um denominador inteiro: " );  
	            int denominador = scanner.nextInt();                          
	            int result = quociente( numerador, denominador );              
	            System.out.printf( "\nResult: %d / %d = %d\n", numerador,     
	               denominador, result );                                     
	            continueLoop = false; // entrada bem-sucedida; fim de loop
	         } // fim de try
			 catch ( InputMismatchException inputMismatchException )          
	         {                                                                
	            System.err.printf( "\nException: %s\n", inputMismatchException );                                  
	            scanner.nextLine(); // descarta entrada para o usuário tentar novamente
	            System.out.println("Deve-se entrar com numeros inteiros. Tente de novo.\n");          
	         } // fim de catch
	         catch ( ArithmeticException arithmeticException )                
	         {                                                                
	           System.err.printf( "\nException: %s\n", arithmeticException );
	           System.out.println("Zero e um denominador invalido. Tente de novo.\n" );   
	         } // fim de catch
	      } while ( continueLoop ); // fim de do...while 		 
	}

}


public class ThrowExceptionClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		throwException3();
		}
		catch(Exception e)
		{
			System.out.println("Executado no metodo main");
		}
	}
	public static void throwException() throws Exception
	   {
	      try // lança uma exceção e imediatamente a captura
	      { 
	         System.out.println( "Metodo throwException" );
	         throw new Exception(); // gera a exceção
	      } // fim de try
	      catch ( Exception exception ) // captura exceção lançada em try
	      {
	         System.err.println("Excecao tratada no metodo throwException");
	      } // fim de catch
	      finally // executa independentemente do que ocorre em try...catch
	      {                                                             
	         System.err.println( "Finally executado em throwException" );
	      } // fim de finally 
	   } // fim de método throwException
	
	public static void throwException2() throws Exception
	   {
	      try { // lança uma exceção e imediatamente a captura
	         System.out.println( "Metodo throwException" );
	         throw new Exception(); // gera a exceção
	      } // fim de try
	      catch ( Exception exception ) // captura exceção lançada em try
	      {
	         System.err.println("Excecao tratada no metodo throwException" );
	         throw exception; // lança novamente para processamento adicional
		   } // fim de catch
	      finally // executa independentemente do que ocorre em try...catch
	      {                                                             
	         System.err.println( "Finally executado in throwException" );
	      } // fim de finally 
	   } // fim de método throwException 
	
//	 throwException lança exceção que não é capturada nesse método
	   public static void throwException3() throws Exception
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
}

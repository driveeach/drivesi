
public class Using {
	public static void main( String args[] )
	   {
	      try 
	      { 
	         method1(); // chama method1 
	      } // fim de try
	      catch ( Exception exception ) // captura a exceção lançada em method1
	      { 
	         System.err.printf( "%s\n\n", exception.getMessage());
	         exception.printStackTrace(); // imprime rastreamento de pilha da exceção 

	         // obtém informações de rastreamento de pilha
	         StackTraceElement[] traceElements = exception.getStackTrace();

	         System.out.println( "\nStack trace from getStackTrace:" );
	         System.out.println( "Class\t\tFile\t\t\tLine\tMethod" );

	         // faz um loop por traceElements para obter a descrição da exceção
	         for ( StackTraceElement element : traceElements ) 
	         {
	            System.out.printf( "%s\t", element.getClassName());
	            System.out.printf( "%s\t", element.getFileName());
	            System.out.printf( "%s\t", element.getLineNumber());
	            System.out.printf( "%s\n", element.getMethodName());
	         } // for final
	      } // fim de catch
	   }
	      public static void method1()throws Exception
	      {
	         method2();
	      } // fim de método method1 

	      public static void method2()throws Exception
	      {
	         method3();
	      } // fim de método method2 

	      public static void method3()throws Exception
	      {
	         throw new Exception( "Exception thrown in method3" );
	      } // fim de método method3 
}

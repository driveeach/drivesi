/**
 * 
 */
package myTestFiles;
import java.io.File;
/**
 * @author chaim
 *
 */
public class FileDemonstration {

	/**
	 * 
	 */
	public FileDemonstration() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileDemonstration f = new FileDemonstration();
		
		f.analyzePath(args[0]);
	}
//	 apresenta informações sobre um arquivo/diretório específico
	   public void analyzePath( String path ){
	      // cria um objeto File basedo na entrada do usuário
	      File name = new File( path );

	      if ( name.exists() ) { // 
	         // apresenta informação do arquivo/diretório         			 
			  System.out.println(name.getName()+ " existe");
			  System.out.println((name.isFile() ? "é um arquivo" : "não é um arquivo"));
			  System.out.println((name.isDirectory() ? "é um diretório" :"não é um diretório" ));
			  System.out.println((name.isAbsolute() ? "é um caminho absoluto":"não é um caminho absoluto" )); 
			  System.out.println("Tamanho: "+name.length());
			  //System.out.printf("\n%s%s\n%s%s\n%s%s\n%s%s", 
			  System.out.println( "Caminho: "+name.getPath()); 
			  System.out.println("Absolute path: "+ name.getAbsolutePath());
			  System.out.println("Pai: "+ name.getParent() );
			  
	           if ( name.isDirectory() ) // imprime o conteúdo de um diretório
		         {
		            String directory[] = name.list();
		            System.out.println( "\n\nConteúdo do diretório:\n" );
		   
		            for ( String directoryName : directory )
		               System.out.printf( "%s\n", directoryName );
		         } // fim if
		      } // fim if externo
		      else // imprime mensagem de erro
		      {
		         System.out.printf( "%s %s", path, "não existe." );
		      } // fim else 
		   } // fim metodo analyzePath
}

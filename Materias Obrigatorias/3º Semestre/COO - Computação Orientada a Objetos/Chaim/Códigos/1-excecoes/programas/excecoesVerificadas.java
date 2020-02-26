import java.io.*;
public class ReadCaracteres{
  public static void main(String[] args){
    int i;
    try{
    FileReader entrada = new FileReader(“ exemplo.txt” );
        while (true){
                  i = entrada.read();
                  if ( i == -1 ) break; 
                 char c = (char) i;
                 System.out.print( c );
                          }
         System.out.println();
         entrada.close();
           }
    catch (IOException e){
      System.err.println(e);
    }
}
}

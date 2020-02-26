 public class TrataExcecao {   
 public static void main (String [] args) throws ArrayIndexOutOfBoundsException  {                                               
 try{                                                           
 int vetor[] = new int[100]; 
 vetor[99] = 1;      
 throw new ArrayIndexOutOfBoundsException();
 System.out.println("Dentro bloco try...");        
 }    
 catch(ArrayIndexOutOfBoundsException e){
 System.out.println("Ocorreu uma excecao " );      
 }        
 finally {             System.out.println("Em finally");   
 }       
 System.out.println("Após o tratamento Ex.");   
 } 
 } 

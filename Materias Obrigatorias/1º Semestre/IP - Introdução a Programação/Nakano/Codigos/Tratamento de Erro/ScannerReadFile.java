import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.Scanner; 
 
 
public class ScannerReadFile { 
    
	public static void main(String[] args) { 
        
		// 
		// Create an instance of File for data.txt file. 
		// 
		if (args.length != 1) {
			System.out.println ("Uso: java ScannerReadFile <nome_do_arquivo>");
		}
		else {
			File file = new File(args[0]); 

			try { 
       
				// Create a new Scanner object which will read the data  
				// from the file passed in. To check if there are more  

				// line to read from it we check by calling the  
				// scanner.hasNextLine() method. We then read line one  
				// by one till all line is read. 
				// 
				Scanner scanner = new Scanner(file); 
				while (scanner.hasNextLine()) { 
					String line = scanner.nextLine(); 
					System.out.println("sai capeta!!" + line); 
				} 
			} catch (FileNotFoundException e) { 
				System.out.println ("Meu tratamento de erro");
				e.printStackTrace(); 
			}
		}
	} 
}

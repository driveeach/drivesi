/* não funciona */
import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.Scanner; 
 
 
public class ScannerRead{ 
    
	public static void main(String[] args) throws FileNotFoundException { 
        
		// 
		// Create an instance of File for data.txt file. 
		// 
		if (args.length!=1) {
			System.out.println ("Uso: java ScannerRead <nome_do_arquivo>.");
		}
		else {
			File file = new File(args[0]); 
			Scanner scanner = new Scanner(file); 
			while (scanner.hasNextLine()) { 
				String line = scanner.nextLine(); 
				System.out.println("sai capeta!!" + line); 
			}
		}
	} 
}

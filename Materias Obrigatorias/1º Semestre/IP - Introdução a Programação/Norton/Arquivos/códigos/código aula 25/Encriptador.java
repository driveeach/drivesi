import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Encriptador {
    public static void main(String[] args) {
        File arq = new File("oba");
        
        try {
            File arq2 = new File("oba2");
            FileOutputStream writer = new FileOutputStream(arq2,false);
            FileInputStream reader = new FileInputStream(arq);
            int b;
            while ((b = reader.read()) != -1) {
                b = ~b;
                writer.write(b);
            }
            writer.close();
            reader.close();
            arq.delete();
            arq2.renameTo(arq);
        }
        catch(IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
}

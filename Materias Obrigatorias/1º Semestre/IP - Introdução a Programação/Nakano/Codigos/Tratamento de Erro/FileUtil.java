import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileUtil {

	public void writeLinesToFile(String filename,
								String[] linesToWrite,
								boolean appendToFile) {

		PrintWriter pw = null;

		try {
			if (appendToFile) {
				//If the file already exists, start writing at the end of it.
				pw = new PrintWriter(new FileWriter(filename, true));
			}
			else {
				pw = new PrintWriter(new FileWriter(filename));
				//this is equal to:
				//pw = new PrintWriter(new FileWriter(filename, false));
			}
			for (int i = 0; i < linesToWrite.length; i++) {
				pw.println(linesToWrite[i]);
			}
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			//Close the PrintWriter
			if (pw != null)
			pw.close();
		}
	}

	public static void main(String[] args) {
		FileUtil util = new FileUtil();
		util.writeLinesToFile("myfile.txt", new String[] {"Line 1", 
														"Line 2",
														"Line 3"}, true);
	}
}
 


package myTestFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class ReadBytes {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int c, count = 0;
		
	    File name = new File( args[0] );
	    try {
	    FileInputStream fluxo = new FileInputStream (name);
	    while((c=fluxo.read()) != -1)
	    		count++;
	    		
	    fluxo.close();
	    
	    System.out.println(args[0]+": "+count+" bytes");
	    }
	    catch(IOException ioe) {
	    	System.out.println(ioe);
	    }
	}
}

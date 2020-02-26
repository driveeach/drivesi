package myTestFiles;

import java.io.*;

public class SerializationOfObjects implements Serializable {

	/**
	 * @param args
	 */
	 String nome;
	private int unidades;  // estoque em unidades
	private double custo;   // custo unitário   
	public SerializationOfObjects(){
	 this(" ", 0 , 0.0);
	 }  
	SerializationOfObjects(String nome, int unidades, double custo)
	{
		this.nome=nome; this.unidades=unidades; this.custo=custo;
	}

	static void writeSerializationOfObjects()
	{
	SerializationOfObjects item = 
		new SerializationOfObjects("livro java", 10, 148.50);
	try {
	  FileOutputStream arq = new FileOutputStream("item.dat");
	  ObjectOutputStream objarq = new ObjectOutputStream(arq);
	  objarq.writeObject(item);
	  objarq.close();
	}
	catch(IOException e) {
	  System.out.println(e.getMessage());
	  e.printStackTrace();
	}
	}
	static void readSerializationOfObjects()
	{
		SerializationOfObjects item1 = new SerializationOfObjects();
		try {
		  FileInputStream arq = new FileInputStream("item.dat");
		  ObjectInputStream objarq = new ObjectInputStream(arq);
		  item1 = (SerializationOfObjects) objarq.readObject();
		  objarq.close();
		  System.out.println(item1.nome);
		}
		catch(IOException e) {
		  System.out.println(e.getMessage());
		  e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			  System.out.println(e.getMessage());
			  e.printStackTrace();
			}	
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		writeSerializationOfObjects();
		readSerializationOfObjects();
	}

}

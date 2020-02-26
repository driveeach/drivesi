package myTestFiles;
import java.io.*;

public class RandomAccess {
	int nroconta;
    String nome;
    double saldo;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		RandomAccessFile saida = new RandomAccessFile("dados.bin","rw");
		for (int i = 0; i < 15; i ++) {
			saida.writeInt(i); 
			saida.writeChars("Myself");
			saida.writeDouble(1000000);
		}
		saida.close();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	public void escrever(RandomAccessFile f) throws IOException {
		f.writeInt(nroconta);
		StringBuffer b = new StringBuffer(nome);
		b.setLength( 15 );
		f.writeChars( b.toString() );
		f.writeDouble(saldo);
		}
	
	public void ler(RandomAccessFile f) throws IOException {
		nroconta = f.readInt();
		char letras[] = new char [15];
		for(int i=0;i<15;i++) 
		letras[i] = f.readChar();
		nome = new String( letras ).replace( '\0', ' ' );
		 		saldo = f.readDouble();
		}
	public int tamanho () {
		return (
		(Integer.SIZE/8) + 
		15 * 2 + 				// string de 15 chars.
		(Double.SIZE/8)
		);
//		 ou simplesmente return(4+30+8);
		}

}

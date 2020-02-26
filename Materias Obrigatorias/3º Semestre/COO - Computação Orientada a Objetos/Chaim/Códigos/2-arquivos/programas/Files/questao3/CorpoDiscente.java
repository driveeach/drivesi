package questao3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class CorpoDiscente {
	List<Aluno> l = new ArrayList<Aluno>();

	void insereAluno(Aluno a) {
		l.add(a);
		System.out.println(l.size());
	}

	Aluno retiraAluno() {
		return l.remove(0);
	}

	boolean existeAluno() {
		System.out.println(l.isEmpty());
		return !l.isEmpty();
	}
	public static void main(String[] args) {
		CorpoDiscente cd = new CorpoDiscente();
		//cd.insereAluno(new Aluno(1,"joao",10.5));
		//cd.insereAluno(new Aluno(2,"pedro",11.5));
		//cd.salvaTodosAlunos("/home/chaim/Desktop/uni.bin");
		cd.leTodosAlunos("/home/chaim/Desktop/uni.bin");
	}
	void salvaTodosAlunos(String arqnome) {
		try {
			DataOutputStream outfile = new DataOutputStream(
					new FileOutputStream(arqnome));
			while (existeAluno()) {
				escreverAluno(outfile, retiraAluno());
			}
			outfile.close();
		} catch (IOException ioe) {
			System.out.println(ioe);
			ioe.printStackTrace();
		}
	}

	public void escreverAluno(DataOutputStream f, Aluno a) throws IOException {

		
		f.writeInt(a.identificador);

		StringBuffer nm = new StringBuffer(a.nome);
		nm.setLength(35);
		f.writeChars(nm.toString());

		f.writeDouble(a.mensalidade);
	}

	void leTodosAlunos(String arqnome) {
		DataInputStream inputfile;
		try {
			inputfile = new DataInputStream(
					new FileInputStream(arqnome));
			while (true) {
				insereAluno(leAluno(inputfile));
			}
			
		} catch (IOException ioe) {
		//	inputfile.close();
			System.out.println(ioe);
		}
		//catch (EOFException eofe) {
			
		//}
	}

	Aluno leAluno(DataInputStream infile) throws IOException{
			int id;
			char letras[] = new char[35];
			double mens;
			
			id = infile.readInt();
			
			for(int i=0;i<35;i++) 
				letras[i] = infile.readChar();
			
			String nome = new String( letras ).replace( '\0', ' ' );
				 		
			mens = infile.readDouble();
			
			System.out.println("id:" +id+" "+nome+" mens :" + mens);
			return new Aluno(id, nome, mens);

	}
}

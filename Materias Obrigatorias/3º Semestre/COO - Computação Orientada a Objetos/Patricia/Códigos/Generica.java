import java.util.*;

class Estudante{
	int nusp;
	String nome;
	Estudante(int n1, String nome){
		this.nusp=n1;
		this.nome=nome;
	}
}
class CE implements Comparator<Estudante>{
	public int compare(Estudante e1,Estudante e2){
		if(e1.nusp>e2.nusp) return 1;
		else if (e1.nusp==e2.nusp) return 0;
		else return -1;
	}
}
class Lista extends CE{
	
}
class Generica{
	public static void main(String args[]){
		Estudante e1=new Estudante(8516775, "Geraldo");
		Estudante e2=new Estudante(8516156, "Leonardo");
		Lista comparador=new Lista();
		
		if( comparador.compare(e1,e2) > 0 ){
			System.out.println("Iguais");
		}
	}
}
import classe3.Cor;

class TestaTriangulo2D {
    public static void main (String args[]) {
	    Triangulo2D T1, T2;
		T1=new Triangulo2D();
		T1.setA(0,0);
		T1.setB(1,3);
		T1.setC(5,15);
        System.out.println (T1.ehDegenerado ());
	}
}



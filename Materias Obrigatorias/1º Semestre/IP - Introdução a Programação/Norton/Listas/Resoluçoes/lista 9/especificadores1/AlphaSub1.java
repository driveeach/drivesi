package especificadores1;

public class AlphaSub1 extends Alpha{
	public static void main(String[]args) {
		Alpha.alphaPublic();
		Alpha.alphaProtected();
		Alpha.alphaNada();
		// NÃO RODA PORQUE É PRIVATE  Alpha.alphaPrivate();
	}
}
package especificadores2;
import especificadores1.Alpha;

public class AlphaSub2 extends especificadores1.Alpha{
	public static void main(String[]args) {
		Alpha.alphaPublic();
		Alpha.alphaProtected();
		// ERRO PORQUE É "NADA" Alpha.alphaNada();
		// ERRO PORQUE É PRIVATE Alpha.alphaPrivate();
	}
}
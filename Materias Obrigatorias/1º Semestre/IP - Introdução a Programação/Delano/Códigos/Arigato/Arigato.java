import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Arigato {
  public static void main (String[] argv) throws Exception{
    PrintStream out = new PrintStream(System.out, true, "UTF-8");
    out.println("\u3042\u308A\u304C\u3068\u3045");
    out.println("\u305D\u308C\u3067\u306F\u307E\u305F");
    out.println("a\u0041A");
  }
}

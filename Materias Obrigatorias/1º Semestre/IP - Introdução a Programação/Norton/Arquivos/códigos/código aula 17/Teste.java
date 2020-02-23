import java.util.Scanner;

class Teste {
    public static void main(String[] args) {
        String meuString = "3 tokens 5,3 true";
        
        Scanner sc = new Scanner (meuString);
        
        int i = sc.nextInt(); // 3
        String str = sc.next(); // tokens
        double d = sc.nextDouble(); //5.3
        boolean b = sc.nextBoolean(); //true
        
        System.out.println(i);
        System.out.println(str);
        System.out.println(d);
        System.out.println(b);
    }
}
